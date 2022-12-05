package com.uob.capstone.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="users")
public class User {
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userid;
	
	private String name;
	
	private String identityCardNum;
	
	private Integer contactNo;
	
	private String address;
	
	private String email;
	
	private String nomineeName;
	
	private String nomineeidentityCardNum;
	
	@Column(name="username", unique = true)
	private String username;
	
	private String password;
	
	@SuppressWarnings("unused")
	private boolean enabled;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="user", cascade = CascadeType.ALL )
	private Set<Account> accountList = new HashSet<Account>();

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name = "users_roles	",
			joinColumns = @JoinColumn(name="user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
			)
	private Set<Role> userRoles = new HashSet();

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentityCardNum() {
		return identityCardNum;
	}

	public void setIdentityCardNum(String identityCardNum) {
		this.identityCardNum = identityCardNum;
	}

	public Integer getContactNo() {
		return contactNo;
	}

	public void setContactNo(Integer contactNo) {
		this.contactNo = contactNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomineeName() {
		return nomineeName;
	}

	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}

	public String getNomineeidentityCardNum() {
		return nomineeidentityCardNum;
	}

	public void setNomineeidentityCardNum(String nomineeidentityCardNum) {
		this.nomineeidentityCardNum = nomineeidentityCardNum;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(Set<Account> accountList) {
		this.accountList = accountList;
	}
	public boolean isEnabled() {
		return isEnabled();
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Role> getUserRoles() {
		return userRoles;
	}


	public void setUserRoles(Set<Role> userRoles) {
		this.userRoles = userRoles;
	}

	public void addRole(Role role) {
        this.userRoles.add(role);
	}
	
	
	@Override
	public String toString() {
		return "User [userid=" + userid + ", name=" + name + ", identityCardNum=" + identityCardNum + ", contactNo="
				+ contactNo + ", address=" + address + ", email=" + email + ", nomineeName=" + nomineeName
				+ ", nomineeidentityCardNum=" + nomineeidentityCardNum + ", username=" + username + ", password="
				+ password + ", enabled=" + enabled + ", accountList=" + accountList + "]";
	}
	


	public User(Long userid, String name, String identityCardNum, Integer contactNo, String address, String email,
			String nomineeName, String nomineeidentityCardNum, String username, String password, boolean enabled,
			Set<Account> accountList, Set<Role> userRoles) {
		super();
		this.userid = userid;
		this.name = name;
		this.identityCardNum = identityCardNum;
		this.contactNo = contactNo;
		this.address = address;
		this.email = email;
		this.nomineeName = nomineeName;
		this.nomineeidentityCardNum = nomineeidentityCardNum;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.accountList = accountList;
		this.userRoles = userRoles;
	}
	
	public User() {
		super();
	}


	
}
