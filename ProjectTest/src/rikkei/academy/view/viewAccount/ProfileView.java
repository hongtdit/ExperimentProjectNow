package rikkei.academy.view.viewAccount;

import rikkei.academy.atherfuntion.Menu;
import rikkei.academy.config.Config;
import rikkei.academy.controller.account.AccountController;
import rikkei.academy.dto.request.SignUpDTO;
import rikkei.academy.model.account.Account;
import rikkei.academy.model.account.Role;
import rikkei.academy.model.account.RoleName;
import rikkei.academy.service.account.AccountServiceIMPL;
import rikkei.academy.view.Main;
import rikkei.academy.view.viewsong.ViewSong;

import java.util.*;
import java.util.regex.Pattern;

public class ProfileView {
    AccountController accountController = new AccountController();
    AccountServiceIMPL accountServiceIMPL = new AccountServiceIMPL();
    private final List<Account> accountList = accountController.getAccountList();
    public void menuView() {
        Menu menu = new Menu();
        Account account = accountController.getCurrenAccount();
        String role = null;
        Iterator<Role> iterator = account.getRoles().iterator();
        while (iterator.hasNext()) {
            role = String.valueOf(iterator.next().getName());
        }
        if (role.equals("ADMIN")) {
            menu.addHeader("Welcome " + role + ": " + account.getName());
            menu.addChoice("1. Change Profile");
            menu.addChoice("2. Change Password");
            menu.addChoice("3. Log Out");
            menu.addChoice("4. User Manage");
            menu.addChoice("5. Home");
            menu.addPaddingLeft(4);
            menu.print();
            int choice = Config.getIntegerInput();
            switch (choice) {
                case 1:
                    formChangeProfile();
                    break;
                case 2:
                    formChangePassword();
                    break;
                case 3:
                    new Config<Account>().writeFile(accountServiceIMPL.PATH_FILE_PRINCIPAL, null);
                    new Main().menu();
                    break;
                case 4:
                    formUserManage();
                    break;
                case 5:
                    new Main().menu();
                    break;
                default:
                    System.out.println("Invalid not found");
                    menuView();
            }
        } else if (role.equals("PM")) {
            menu.addHeader("Welcome " + role + " " + account.getName());
            menu.addChoice("1. Change Profile");
            menu.addChoice("2. Change Password");
            menu.addChoice("3. Log Out");
            menu.addChoice("4. User Manage");
            menu.addChoice("5. Home");
            menu.addPaddingLeft(4);
            menu.print();
            int choice = Config.getIntegerInput();
            switch (choice) {
                case 1:
                    formChangeProfile();
                    break;
                case 2:
                    formChangePassword();
                    break;
                case 3:
                    new Config<Account>().writeFile(accountServiceIMPL.PATH_FILE_PRINCIPAL, null);
                    new Main().menu();
                    break;
                case 4:
                    formUserManage();
                    break;
                case 5:
                    new ViewAfterSignIn().afterMenu();
                    break;
                default:
                    System.out.println("Invalid not found");
                    menuView();
            }
        } else {
            menu.addHeader("Welcome " + role + ": " + account.getName());
            menu.addChoice("1. Change Profile");
            menu.addChoice("2. Change Password");
            menu.addChoice("3. Log Out");
            menu.addChoice("4. Home");
            menu.addPaddingLeft(4);
            menu.print();
            int choice = Config.getIntegerInput();
            switch (choice) {
                case 1:
                    formChangeProfile();
                    break;
                case 2:
                    formChangePassword();
                    break;
                case 3:
                    new Config<Account>().writeFile(accountServiceIMPL.PATH_FILE_PRINCIPAL, null);
                    new Main().menu();
                    break;
                case 4:
                    new ViewAfterSignIn().afterMenu();
                default:
                    System.out.println("Invalid not found");
                    menuView();
            }
        }
    }

   
    private void formChangeProfile() {
        Menu menuChangge = new Menu();
        Account account = accountController.getCurrenAccount();
        String role = null;
        Iterator<Role> iterator = account.getRoles().iterator();
        while (iterator.hasNext()) {
            role = String.valueOf(iterator.next().getName());
        }
        menuChangge.addHeader("welcome: " + role + " " + account.getName());
        menuChangge.addChoice("Name: " + account.getName());
        menuChangge.addChoice("Username: " + account.getUsername());
        menuChangge.addChoice("Email: " + account.getEmail());
        menuChangge.addChoice("1. Change Name");
        menuChangge.addChoice("2. Change Username");
        menuChangge.addChoice("3. Change Email");
        menuChangge.addChoice("4. Back Menu");
        menuChangge.addPaddingLeft(2);
        menuChangge.print();
        int choice = Config.getIntegerInput();
        switch (choice) {
            case 1:
                changeName();
                break;
            case 2:
                changeUsername();
                break;
            case 3:
                changeEmail();
                break;
            case 4:
               new  ProfileView().menuView();
                break;
        }

    }
    private void formUserManage(){
        Account account = accountController.getCurrenAccount();
        Menu menu = new Menu();
        String role = null;
        Iterator<Role> iterator = account.getRoles().iterator();
        while (iterator.hasNext()) {
            role = String.valueOf(iterator.next().getName());
        }if (role.equals("ADMIN")){
            menu.addHeader("Welcome "+role+" "+account.getName());
            menu.addChoice("1. Show list User");
            menu.addChoice("2. Block user");
            menu.addChoice("3. Change role");
            menu.addChoice("4. Delete user");
            menu.addChoice("5. Back menu");
            menu.addPaddingLeft(4);
            menu.print();
            int choice = Config.getIntegerInput();
            switch (choice){
                case 1:
                    showListAccount();
                    break;
                case 2:
                    formBlockUser();
                    break;
                case 3:
                    changeRole();
                    break;
                case 4:
                    deleteUser();
                    
                case 5:
                    new ProfileView().menuView();
                    break;
                default:
                    System.out.println("Invalid not found");
            }
        }else if (role.equals("PM")){
            menu.addHeader("Welcome "+role+" "+account.getName());
            menu.addChoice("1. Show list User");
            menu.addChoice("2. Block User");
            menu.addChoice("3. Delete User");
            menu.addChoice("4. Back Menu");
            menu.addPaddingLeft(4);
            menu.print();
        int choice = Config.getIntegerInput();
        switch (choice) {
            case 1:
                showListAccount();
                break;
            case 2:
                formBlockUser();
                break;
            case 3:
                deleteUser();
                break;
            case 4:
                new ProfileView().menuView();
                break;
            default:
                System.out.println("Invalid not found");
           }
        }
    }

    private void deleteUser() {
        RoleName maxRole = accountController.getCurrenAccount().getMaxRole();
        List<Account> accountList1;
        if (maxRole == RoleName.ADMIN) {
            accountList1 = new ArrayList<>(this.accountList);
            Account current = null;
            for (Account account : accountList1) {
                if (account.getUsername().equals(accountController.getCurrenAccount().getUsername())) {
                    current = account;
                }
            }
            accountList1.remove(current);
        } else {
            accountList1 = accountController.findByRoleName(RoleName.USER);
        }
        System.out.printf("%-5s%-15s%s%n", "ID", "USERNAME", "STATUS");
        accountList1.forEach(account -> {
            System.out.printf("%-5d%-15s%s%n", account.getId(), account.getUsername(), account.isStatus());
        });

        System.out.println("Enter user id to delete");

        int id = Config.getIntegerInput();

        if (!accountList1.contains(accountController.findById(id))) {
            System.out.println("ID not found");
            return;
        }

        accountController.deleteUserById(id);
        Account remove = null;
        for (Account account : accountList1) {
            if (account.getId() == id) {
                remove = account;
            }
        }
        accountList1.remove(remove);

        System.out.printf("%-5s%-15s%s%n", "ID", "USERNAME", "STATUS");
        accountList1.forEach(account -> {
            System.out.printf("%-5d%-15s%s%n", account.getId(), account.getUsername(), account.isStatus());
        });
        new ProfileView().formUserManage();

    }

    private void changeRole() {
        List<Account> accounts = new ArrayList<>(this.accountList);

        Account current = null;
        for (Account account : accounts) {
            if (account.getUsername().equals(accountController.getCurrenAccount().getUsername())) {
                current = account;
            }
        }
        accounts.remove(current);

        System.out.printf("%-5s%-15s%s%n", "ID", "USERNAME", "ROLE");
        accounts.forEach(account -> {
            System.out.printf("%-5d%-15s%s%n", account.getId(), account.getUsername(), account.getMaxRole());
        });

        System.out.println("Enter user id to edit role");
        int id = Config.getIntegerInput();

        if (!accounts.contains(accountController.findById(id))) {
            System.out.println("ID not found");
            return;
        }

        System.out.println("Enter role");
        String role = Config.scanner().nextLine();
        Set<String> strRoles = new HashSet<>();
        strRoles.add(role);
        accountController.setRole(id, strRoles);

        System.out.printf("%-5s%-15s%s%n", "ID", "USERNAME", "ROLE");
        accounts.forEach(account -> {
            System.out.printf("%-5d%-15s%s%n", account.getId(), account.getUsername(), account.getMaxRole());
        });
        new ProfileView().formUserManage();
    }
    private void formBlockUser() {
        RoleName roleName = accountController.getCurrenAccount().getMaxRole();
        List<Account> accountList;
        if (roleName == RoleName.ADMIN){
            accountList = new ArrayList<>(this.accountList);
            Account accCurr = null;
            for (Account account: accountList) {
                if (account.getUsername().equals(accountController.getCurrenAccount().getUsername())){
                    accCurr = account;
                }
            }
            accountList.remove(accCurr);
        }else {
            accountList = accountController.findByRoleName(RoleName.USER);
        } System.out.printf("%-5s%-15s%s%n", "ID", "USERNAME" ,"STATUS");
        accountList.forEach(account -> {
            System.out.printf("%-5s%-15s%s%n",account.getId(),account.getUsername(),account.isStatus());
        });
        System.out.println("Enter Account id to block");
        int id = Config.getIntegerInput();
        if (!accountList.contains(accountController.findById(id))){
            System.out.println("ID not found");
            return;
        }
        accountController.changeStatus(id);
        System.out.println("Account is Blocked");
        System.out.printf("%-5s%-15s%s%n", "ID", "USERNAME" ,"STATUS");
        accountList.forEach(account -> {
            System.out.printf("%-5s%-15s%s%n",account.getId(),account.getUsername(),account.isStatus());
        });
        new ProfileView().formUserManage();
    }

    private void showListAccount() {
        System.out.printf("%-5s%-15s%s%n", "ID", "USERNAME", "ROLE");
       accountList.forEach(account -> {
            System.out.printf("%-5d%-15s%s%n", account.getId(),account.getUsername(), account.getMaxRole());
        });
          new ProfileView().formUserManage();
    }



    private void changeName() {
        Account account = accountController.getCurrenAccount();
        String name;
        boolean validateName;
        while (true) {
            System.out.println("Enter name");
            name = Config.scanner().nextLine();
            validateName = Pattern.matches("[A-Z][a-zA-Z[\\s]]{1,40}", name);
            if (validateName) {
                break;
            } else {
                System.out.println("the name failed! please try again!");

            }
        }
        Account account1 = new Account(account.getId(),name,account.getUsername(),account.getEmail(),account.getPassword(),account.getRoles());
        accountController.updateAccount(account1);
        System.out.println("Change name success!");
        new ProfileView().formChangeProfile();

        }
    private void changeUsername() {
        Account account = accountController.getCurrenAccount();
        String username;
        boolean validateUsername;
        while (true) {
            System.out.println("Enter username to change");
            username = Config.scanner().nextLine();
            validateUsername = Pattern.matches("^[a-z0-9_-]{5,15}$", username);
            if (validateUsername) {
                break;
            } else {
                System.out.println("the name failed! please try again!");

            }
        }
        Account account1 = new Account(account.getId(),account.getName(),username,account.getEmail(),account.getPassword(),account.getRoles());
        Set<String> strRole = new HashSet<>();
        String check_existed = accountController.register(new SignUpDTO(account.getId(),account.getName(),username,account.getEmail(),account.getPassword(),strRole)).getMessenger();
        if (check_existed.equals("username_existed")) {
            System.out.println("the username is existed! Please try again!");
            changeUsername();
        }else {
        accountController.updateAccount(account1);
        System.out.println("Change username success!");
       new ProfileView().formChangeProfile();
    }
    }
    private void changeEmail() {
        Account account = accountController.getCurrenAccount();
        String email;
        boolean validateEmail;
        while (true) {
            System.out.println("Enter email");
            email = Config.scanner().nextLine();
            validateEmail = Pattern.matches("^[A-Za-z]+[A-Za-z0-9._]*@[A-Za-z]+(\\.[a-z]+)$", email);
            if (validateEmail) {
                break;
            } else {
                System.out.println("the name failed! please try again!");

            }
        }
        Account account1 = new Account(account.getId(),account.getName(),account.getUsername(),email,account.getPassword(),account.getRoles());
        Set<String> strRole = new HashSet<>();
        String check_existed = accountController.checkMail(new SignUpDTO(account.getId(),account.getName(),account.getUsername(),email,account.getPassword(),strRole)).getMessenger();
        if (check_existed.equals("email_existed")) {
            System.out.println("the email is existed! Please try again!");
            changeEmail();
        }else if (check_existed.equals("Success!")){
            accountController.updateAccount(account1);
            System.out.println("Change email success!");
            new ProfileView().formChangeProfile();
        }
    }
    private void formChangePassword(){
        Account account = accountController.getCurrenAccount();
        System.out.println("Enter current password");
        String password = Config.scanner().nextLine();
        if (password.equals(account.getPassword())){
            String newPassword;
            boolean validateEmail;
            while (true) {
                final String ANSI_RESET = "\u001B[0m";
                final String ANSI_YELLOW = "\u001B[33m";
                System.out.println("Enter new password");
                System.out.println(ANSI_YELLOW+"Password must contain uppercase letters, lowercase letters, numbers and special characters"+ANSI_RESET);
                newPassword = Config.scanner().nextLine();
                validateEmail = Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?.,:;_&])[A-Za-z\\d@$!%*?.,:;_&]{6,10}$", newPassword);
                if (validateEmail) {
                    break;
                } else {
                    System.err.println("Password format error! Please enter the correct format!");
                }
            }
            Account account1 = new Account(account.getId(),account.getName(),account.getUsername(),account.getEmail(),newPassword,account.getRoles());
            accountController.updateAccount(account1);
            System.out.println("Change password success!");
        }else {
            formChangePassword();
        }
        new ProfileView().menuView();
    }

    

}




