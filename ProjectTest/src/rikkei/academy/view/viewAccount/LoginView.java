package rikkei.academy.view.viewAccount;
import rikkei.academy.atherfuntion.Menu;
import rikkei.academy.config.Config;
import rikkei.academy.controller.account.AccountController;
import rikkei.academy.dto.reponse.ResponseMessenger;
import rikkei.academy.dto.request.SignInDTO;
import rikkei.academy.dto.request.SignUpDTO;
import rikkei.academy.model.account.Account;
import rikkei.academy.service.account.AccountServiceIMPL;
import rikkei.academy.view.Main;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
public class LoginView {
    AccountController accountController = new AccountController();
    List<Account> accountList = accountController.getAccountList();
    AccountServiceIMPL accountServiceIMPL = new AccountServiceIMPL();
    public void menuLogin() {
        Menu menuLogin = new Menu();
        menuLogin.addHeader(" Welcome ");
        menuLogin.addChoice("1. Login Account");
        menuLogin.addChoice("2. Sign Up Account");
        menuLogin.addChoice("3. Back Menu");
        menuLogin.addPaddingLeft(5);
        menuLogin.print();
        int choice = Config.getIntegerInput();
        switch (choice) {
            case 1:
                formLogin();
                break;
            case 2:
                formRegister();
                break;
            case 3:
                new Main().menu();
                break;
            default:
                System.out.println("Invalid choice");
                menuLogin();
        }
    }

    private void formLogin(){
        String username;
        boolean validateUsername;
        while (true){
            System.out.println("Enter the username");
            username = Config.getStringInput();
            validateUsername = Pattern.matches("^[a-z0-9_-]{5,15}$", username);
            if (validateUsername){
                break;
            }else {
                System.out.println("the username failed! please try again!");
            }
        }
        String password;
        boolean validatePassword;
        while (true) {
            System.out.println("Enter Password");
            password = Config.scanner().nextLine();
            validatePassword = Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?.,:;_&])[A-Za-z\\d@$!%*?.,:;_&]{6,10}$", password);
            if (validatePassword) {
                break;
            } else {
                System.out.println("the Password failed! please try again!");
            }
        }
        ResponseMessenger messenger = accountController.login(new SignInDTO(username,password));
        if (messenger.getMessenger().equals("Login failed! - Please try again username or password!")){
            System.out.println("Login failed! - Please try again username or password!");
            formLogin();
        }else {
            new ViewAfterSignIn().afterMenu();
        }
    }
    public void formRegister() {
        System.out.println("===========");
        int id;
        if (accountList.size() == 0) {
            id = 1;
        } else {
            id = accountList.get(accountList.size() - 1).getId() + 1;
        }
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
        String username;
        boolean validateUserName;
        while (true) {
            System.out.println("Enter username");
            username = Config.scanner().nextLine();
            validateUserName = Pattern.matches("^[a-z0-9_-]{5,15}$", username);
            if (validateUserName) {
                break;
            } else {
                System.out.println("the username failed! please try again!");
            }
        }
        String email;
        boolean validateEmail;
        while (true) {
            System.out.println("Enter email");
            email = Config.scanner().nextLine();
            validateEmail = Pattern.matches("^[A-Za-z]+[A-Za-z0-9._]*@[A-Za-z]+(\\.[a-z]+)$", email);
            if (validateEmail) {
                break;
            } else {
                System.out.println("the email failed! please try again!");
            }
        }
        String password;
        boolean validatePassword;
        while (true) {
            System.out.println("Enter Password");
            password = Config.scanner().nextLine();
            validatePassword = Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?.,:;_&])[A-Za-z\\d@$!%*?.,:;_&]{6,10}$", password);
            if (validatePassword) {
                break;
            } else {
                System.out.println("the Password failed! please try again!");
            }
        }
        String role = "user";
        Set<String> strRole = new HashSet<>();
        strRole.add(role);
        SignUpDTO signUpDTO = new SignUpDTO(id, name, username, email, password, strRole);
        String check_existed = accountController.register(signUpDTO).getMessenger();
        if (check_existed.equals("username_existed")) {
            System.out.println("the username is existed! Please try again!");
            formRegister();
        } else if (check_existed.equals("email existed")) {
            System.out.println("the email is existed! Please try again!");
            formRegister();
        } else if (check_existed.equals("Success!")) {
            final String ANSI_RESET = "\u001B[0m";
            final String ANSI_YELLOW = "\u001B[33m";
            System.out.println(ANSI_YELLOW + "Sign up user success!" + ANSI_RESET);
            menuLogin();
        }
    }
//    public void logOut(){
//        new Config<Account>().writeFile(accountServiceIMPL.PATH_FILE_PRINCIPAL, null);
//    }
//    public void showList(){
//        System.out.println(accountController.getAccountList());
//    }

}

