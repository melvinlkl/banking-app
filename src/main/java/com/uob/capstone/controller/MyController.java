package com.uob.capstone.controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uob.capstone.dao.AccountRepository;
import com.uob.capstone.dao.RoleRepository;
import com.uob.capstone.dao.TransactionRepository;
import com.uob.capstone.dao.UserRepository;
import com.uob.capstone.entity.Account;
import com.uob.capstone.entity.Role;
import com.uob.capstone.entity.Transaction;
import com.uob.capstone.entity.User;


@Controller
public class MyController {
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private TransactionRepository transactionRepository;

	// Homepage -> Redirect to list of Users
	@RequestMapping("/")
	public String showHome(Principal principal) {
		// Logic
		    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		    String role= auth.getAuthorities().toString();
		    
		    role = role.replaceAll("[\\[\\](){}]","");
		    if (role.equals("ADMIN")) {
		    	return "redirect:/view/users";
		    }else{
		    	String username = auth.getName();
		    	User user = userRepository.getUserByUserName(username);
		    	return "redirect:/view/account/"+ user.getUserid().toString();
		    }
	}

	// Endpoint to viewusers.html (Default page)
	@RequestMapping("/view/users")
	public String showUsers(Model model) {
		List<User> userList = (List<User>) userRepository.findAllByUserRoles_NameAndEnabled("USER", true);
		model.addAttribute("userList", userList);
		return "viewusers";
	}

	// Endpoint to signup form
	@RequestMapping("/signup")
	public String signUp(User user, Model model) {
		model.addAttribute("user", user);
		List<Role> listRoles = (List<Role>) roleRepository.findAll();
		model.addAttribute("listRoles", listRoles);
		return "signup";
	}

	// API to add user
	@GetMapping("/add/users")
	public String addUsers(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String password = user.getPassword();
		user.setEnabled(true);
		user.setPassword(encoder.encode(password));
		userRepository.save(user);
		return "redirect:/view/users";
	}

	// Save user endpoint - from viewaccount.html
	@PutMapping("/save/user")
	public String saveUser(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String password = user.getPassword();
		user.setEnabled(true);
		user.setPassword(encoder.encode(password));
		userRepository.save(user);
		return "redirect:/view/account/" + user.getUserid().toString();
	}

	// Save user endpoint - from viewusers.html
	@PutMapping("/save/users")
	public String saveUsers(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String password = user.getPassword();
		user.setEnabled(true);
		user.setPassword(encoder.encode(password));
		userRepository.save(user);
		return "redirect:/view/users";
	}

	// Delete user
	@RequestMapping("delete/user/{id}")
	public String deleteUser(@PathVariable("id") Long id, User user) {
		user = userRepository.findById(id).orElseThrow();
		user.setEnabled(false);
		userRepository.save(user);
		List<Account> accList = (List<Account>) accountRepository.findByUser(id);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		for (Account a : accList) {

			Transaction tnew = new Transaction();
			tnew.setTransactionDateTime(timestamp);
			tnew.setTransactionAmount(a.getBalance());
			tnew.setEnabled(false);
			tnew.setTransactionType("Withdraw");
			a.setBalance(0d);
			a.setEnabled(false);
			tnew.setAccount(a);
			tnew.setTransactionStatus("Successful");
			List<Transaction> tList = (List<Transaction>) transactionRepository.findByAccountID(a.getId());
			for (Transaction t : tList) {
				t.setEnabled(false);
				transactionRepository.save(t);
			}

			accountRepository.save(a);
			transactionRepository.save(tnew);
		}

		return "redirect:/view/users";
	}

	// Show viewaccount.html
	@RequestMapping("/view/account/{id}")
	public String showAccount(@PathVariable("id") Long userid, Model model) {
		// To display customer specific information identified through userid
		User user = userRepository.findById(userid).orElseThrow();
		model.addAttribute("user", user);

		// To display accounts specific to unique userid as a list
		List<Account> accList = (List<Account>) accountRepository.findAllByUser_UseridAndEnabled(userid, true);
		model.addAttribute("accList", accList);

		// To calculate total balance across all accounts related to specific user
		Integer count = accountRepository.countAccByUserID(userid);
		model.addAttribute("count",count);

		// To calculate total balance across all accounts related to specific user

		Float totBal = accountRepository.findTotalBalByCustomer(userid);
		model.addAttribute("totBal", totBal);
		return "viewaccount";
	}

	// Add account - from accountview
	@RequestMapping("/add/account/{id}")
	public String newAccount(@PathVariable("id") Long id, Account account, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("account", account);
		return "addaccount";
	}

	// Save account - from accountview
	@RequestMapping("/save/account/{id}")
	public String saveAccount(@PathVariable("id") Long id, Account account) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		account.setAccDateTime(timestamp);
		User user = userRepository.findById(id).orElseThrow();
		account.setUser(user);
		account.setEnabled(true);
		account.setAccNumber(accountRepository.count() + 1000000000);
		if (account.getBalance() == null) {
			account.setBalance(0d);
		}
		accountRepository.save(account);
		return "redirect:/view/account/" + id.toString();
	}

	@RequestMapping("/redirect/account")
	public String redirectToAccount(@RequestParam(value = "num") Long num) {
		return "redirect:/view/account/" + num.toString();
	}

	@RequestMapping("/redirect/transaction")
	public String redirectToTransaction(@RequestParam(value = "num1") Long num1) {
		Account account = accountRepository.findById(num1).orElseThrow();
		return "redirect:/view/transaction/" + account.getUser().getUserid() + "/" + num1.toString();
	}

	// Delete account - from accountview
	@RequestMapping("/delete/account/{aid}")
	public String deleteAccount(@PathVariable("aid") Long aid, Account account) {
		account = accountRepository.findById(aid).orElseThrow();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Transaction tnew = new Transaction();
		tnew.setTransactionDateTime(timestamp);
		tnew.setTransactionAmount(account.getBalance());
		tnew.setEnabled(false);
		tnew.setTransactionType("Withdraw");
		account.setEnabled(false);
		account.setBalance(0d);
		tnew.setAccount(account);
		tnew.setTransactionStatus("Successful");

		List<Transaction> transList = transactionRepository.findByAccountID(aid);
		for (Transaction t : transList) {
			t.setEnabled(false);
			transactionRepository.save(t);
		}
		accountRepository.save(account);
		transactionRepository.save(tnew);
		return "redirect:/view/account/" + account.getUser().getUserid().toString();
	}

	// Edit user - from viewaccount
	@RequestMapping("/edit/user/{id}")
	public String editUser(@PathVariable("id") Long userid, Model model) {
		User user = userRepository.findById(userid).orElseThrow();
		model.addAttribute("user", user);
		model.addAttribute("userid", userid);
		return "edituser";
	}

	// Edit user - from viewusers
	@RequestMapping("/edit/users/{id}")
	public String editUsers(@PathVariable("id") Long userid, Model model) {
		User user = userRepository.findById(userid).orElseThrow();
		model.addAttribute("user", user);
		model.addAttribute("userid", userid);
		return "editusers";
	}

	// Show transaction
	@RequestMapping("/view/transaction/{uid}/{aid}")
	public String showTransaction(@PathVariable("aid") Long aid, @PathVariable("uid") Long uid, Model model) {
		List<Transaction> transactions = transactionRepository.findAllByAccount_IdAndEnabled(aid, true);
		model.addAttribute("transactions", transactions);
		model.addAttribute("aid", aid);
		model.addAttribute("uid", uid);
		return "viewtransaction";
	}

	// add transaction
	@RequestMapping("/transactionform/{uid}/{aid}")
	public String doTransaction(@PathVariable("uid") Long uid, @PathVariable("aid") Long aid, Transaction transaction,
			Model model) {

		Account account = accountRepository.findById(aid).orElseThrow();
		model.addAttribute("transaction", transaction);
		model.addAttribute("account", account);
		model.addAttribute("aid", aid);
		model.addAttribute("uid", uid);

		System.out.println(account.getId());
		return "transactionform";
	}

	// save transaction - after deposit/withdraw
	@RequestMapping("/save/transaction/{uid}/{aid}")
	public String saveTransaction(@PathVariable("uid") Long uid, @PathVariable("aid") Long aid, Account account,
			Transaction transaction) {

		Double afterBalance;
		Double currentBalance = account.getBalance();
		System.out.println(account);
		Double transactAmount = transaction.getTransactionAmount();

		if (transaction.getTransactionType().equalsIgnoreCase("withdraw")) {
			if (transactAmount > currentBalance || currentBalance <= 500) {
				transaction.setTransactionStatus("Failed");
			} else {
				afterBalance = currentBalance - transactAmount;
				System.out.println("after: " + afterBalance);
				accountRepository.setBalanceById(afterBalance, aid);
				transaction.setTransactionStatus("Successful");
			}
		}
		if (transaction.getTransactionType().equalsIgnoreCase("deposit")) {
			afterBalance = currentBalance + transactAmount;
			System.out.println("after: " + afterBalance);
			accountRepository.setBalanceById(afterBalance, aid);
			transaction.setTransactionStatus("Successful");
		}
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		transaction.setTransactionDateTime(timestamp);
		transaction.setAccount(account);
		transaction.setEnabled(true);
		transactionRepository.save(transaction);
		System.out.println("1" + account.getId());
		return "redirect:/view/transaction/" + uid.toString() + "/" + aid.toString();
	}

}
