# Banking Application

## Project Description 
This is a modern, yet simple banking application built in Java with Springboot. 

### Core Features of Banking App
- Web Security 
- Users Management 
- Account Management 
- Transaction Management 

## How to Run and Install Project
### Steps to Run
* Build the project using ```mvn clean install```
* Run project with ```mvn spring-boot:run```
* The project can be accessed via https://localhost:8080/
* Use ```username: admin``` , ```password: admin123``` to login into Banking Application

### Requirements
- Java SE 11
- Oracle Database (SQLPlus) with JDBC Connectivity (For Windows Users)
- MySQL and Connector (for Mac Users)

## How to Use
### Navigation Bar
![image](https://user-images.githubusercontent.com/65390582/205539679-a1716b73-76de-4237-b7ac-48b8fa591f80.png)

The navigation bar provides links to relevant pages such as Home, Account, Transaction
and the Logout function. By using a navbar, the user or admin does not have to enter the
URL of the specific webpage which proves to be convenient. The navbar is also dynamic,
and will adapt itself to the user’s viewport/screen. When the screen is minimized, a
hamburger button will appear for the viewer. Clicking on “Midas Bank” or “Home” will
return the to the homepage of View Users. Clicking on “Account” will display a search bar
to input User ID to display View Accounts page for that User. Clicking on “Transaction”
will display a search bar to input Account ID to display View Transaction page for that
Account of the specific user. Clicking on “Logout” will allow the current user to logout of
this application. A prompt of “Are you sure you want to log out?” will be shown. Upon
clicking on “Log Out” button will redirect the page back to Login page.

### View Users
![image](https://user-images.githubusercontent.com/65390582/205539559-047d1f46-75a8-4eca-b99d-d8e5c22e6208.png)

View Users page can only be accessed by Admin. This page gives you immediate access
to the list of customers who have signed up with your bank, together with important
customer details. Details displayed will be as follows: Name, NRIC, Contact Number,
Address, Email Address and Username. This page also includes key functionalities such
as viewing customer specific accounts details by clicking on the “Page” icon, editing
customer details by clicking on the “Pen” icon, and deleting customers by clicking on the
“Bin” icon.

### Add Users
![image](https://user-images.githubusercontent.com/65390582/205539633-27a4a0cf-10a8-47e4-9ba1-c470725062be.png)

New users can also be added to the bank’s database with the “Add User” button which
redirects users to the sign up page, where relevant details should be filled in. Alternatively,
new users can be added manually via Oracle SQL Developer / MySQL.
Add User (See Fig. 2.2)
The signup.html page directs the admin to easily add new users along with their personal
information, contact details and nominee information into the database. This privilege is
given only to the admin. The page includes two main functions: a Register button and a
Cancel button.

The following validation checks has been incorporated to ensure that incorrect data is not
added into the database:
Requirements for User Creation
Username: Must be at least 3 characters
Password: Password must be at least 8 characters and include one number, and one
uppercase and lowercase letter
Roles: The privileges of the new user (Admin/User)
NRIC Number: Has to begin with “S/T/F/G/M” followed by 7 digits and ends with a single
alphabet. This field is case insensitive.
Contact Number: Must be 8 digits
Email: Has to be in email format (Eg a@a.com)
All fields are required for the user to be registered successfully. The eye icon below the
password field allows the admin to toggle the visibility of the password.
The “Register” button would redirect the admin to the view/users page where they would
be able to view all the users after the changes have been made. On the other hand, the
“Cancel” button allows the admin to return to the /view/users page without saving any
changes.

### Edit User
![image](https://user-images.githubusercontent.com/65390582/205539789-ed268d8c-9ba0-4f70-be4b-8bb2de4d538d.png)

The edituser.html page directs you to a page that displays a user's profile, which includes
their personal information, contact details and nominee information. The page includes
two main functions: an Update button and a Cancel button.

Apart from the username, name and NRIC of the user which are in read-only mode, all
other fields can be edited manually by the user and saved via the update button. Upon
clicking on the update button, the user will be redirected to /view/users. The following
validation checks has been added to ensure that incorrect data is not updated into the
database:
Password: Password must be at least 8 characters and include one number, and one
uppercase and lowercase letter.
NRIC Number: Has to begin with “S/T/F/G/M” followed by 7 digits and ends with a single
alphabet. This field is case insensitive.
Contact Number: Must be 8 digits.
Email: Has to be in email format (E.g: alex@hotmail.sg)
On the other hand, the cancel button allows the user to return to the /view/users page
without saving any changes.

### Delete User
Delete Account is a functionality made only available to Admin. Users can be deleted
from the View Users page, where any existing balance in all the accounts under the user
will be withdrawn and returned to users.

### View Accounts
![image](https://user-images.githubusercontent.com/65390582/205539728-2fd15825-56f4-47b2-84d0-bf56b9ecd96f.png)

View Accounts page provides an overview of the accounts relevant to the specific user
as well as the user details. When no existing account under the signed up name is
present, no records will be shown under Account Details. Total Balance functionality will
not be visible when no existing account is present. New accounts can be added with the
“Add Account” button, where the functionality will be further explained in the next section.
Users with existing bank account(s) will be able to view transactions related to that
specific account. The Total Balance of all the accounts registered under the user will be
shown in a card view, giving users an overview of their total balance across all their bank
accounts. An additional functionality is the “Delete Account” button which is only visible
to Admin.

### Add Accounts
![image](https://user-images.githubusercontent.com/65390582/205539816-00ffbc28-d53e-418f-8bb1-37edb7a5c1bd.png)

Add accounts gives the option to create new accounts associated with the respective
user. There will be four types of accounts to choose from: Current, Savings, Fixed
Deposit, and Recurring Deposit.Upon choosing the type of account, the starting deposit
to start the account can be keyed in for the user. The starting deposit has a lower limit of
$0 and an upper limit of $99,999. Deposit amount to be inputted takes up to 2 decimal
places. Account of default value $0 will be used if no value is inputted for the starting
deposit. Once satisfied with the inputs, clicking on the “Add” button will create the account
for the user and return to Views Accounts, with an extra account entry under Account
Details for the user. However, clicking on the “Cancel” button will return to View Accounts
without saving any changes made to the user.


### Delete Accounts
Delete Account is a functionality only available to Admin. Account can be deleted from
the View Account page, where any existing balance in the account will be withdrawn and
returned to users.

### View Transactions
![image](https://user-images.githubusercontent.com/65390582/205539873-55f83e4f-ff72-4b99-a5d6-7ca0462d02d6.png)

View Transaction page provides all the transaction details relevant to the specific account.
The transaction details include the transaction number and which account the transaction
was related to. These details also include the type and amount of the transaction as well
as the time. There is also a detail indicating whether the transaction has passed or failed
with the restrictions in place where there must be a minimum amount of $500 in the check
balance before withdrawing will be allowed. Withdrawal of amount more than amount
available in balance will also be unsuccessful.

Upon clicking the new button, the user will be redirected to the /transactionform page.
The back to account button on the hand would redirect the users back to /view/account
to check on their new balances in that specific account after all the transactions have
been completed by yourself.

### New Transaction
![image](https://user-images.githubusercontent.com/65390582/205539908-b223d2a3-bae8-4247-97ef-70fa5db2aaff.png)
![image](https://user-images.githubusercontent.com/65390582/205539931-44dcf075-5eb7-4f7b-8f63-193bab0a7b83.png) 

The transactionform.html page directs you to a page where you can easily make new transactions by inputting the amount you wish to transact and choosing the type of transaction. The type of transactions that you can make are either withdrawing or depositing. The page includes two main functions: a Submit button and a Cancel button.

Apart from the account number and current balance, the transaction amount and type of
transaction can be keyed in manually by inputting the amount and choosing the type of
transaction from the drop down menu.

Upon clicking the submit button, the user will be redirected to /view/transaction to check
on whether the transaction has been successful. The following validation checks have
been encoded to ensure that incorrect data is not added into the database:
Current Balance: Upon withdrawing, the balance must be more than $500.
Transaction Amount: Upon withdrawing, the amount to be transacted must be more than
the current balance.

The cancel button on the other hand would also redirect the users back to
/view/transaction but no new transactions would be recorded.


## Credits
This is a collaborative effort and I would like to specially mention the team members who worked together to make this project a success. 
- Le Hui
- Aaron 
- Ronald
- Wen Han
- Jin Hui
- Janvi 
- Anupriya 
