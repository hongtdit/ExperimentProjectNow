package rikkei.academy.controller.account;

import rikkei.academy.config.Config;
import rikkei.academy.dto.reponse.ResponseMessenger;
import rikkei.academy.dto.request.SignInDTO;
import rikkei.academy.dto.request.SignUpDTO;
import rikkei.academy.model.account.Account;
import rikkei.academy.model.account.Role;
import rikkei.academy.model.account.RoleName;
import rikkei.academy.model.music.singer.Band;
import rikkei.academy.service.account.AccountServiceIMPL;
import rikkei.academy.service.account.IAccountService;
import rikkei.academy.service.role.IRoleService;
import rikkei.academy.service.role.RoleServiceIMPL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AccountController {
    private IRoleService roleService = new RoleServiceIMPL();
    private final IAccountService accountService = new AccountServiceIMPL();
    AccountServiceIMPL accountServiceIMPL = new AccountServiceIMPL();
    private List<Account> accountList = new Config<Account>().readFile(AccountServiceIMPL.PATH_FILE);

    public List<Account> getAccountList(){
        return accountService.findAll();
    }
    public Account findById(int id){return accountService.findById(id);}
    public void changeStatus(int id){
        accountService.changeStatus(id);
    }
    public void setRole(int id, Set<String> strRoles){
        Set<Role> roles = new HashSet<>();
        for (String role : strRoles) {
            switch (role) {
                case "admin":
                    roles.add(roleService.findByName(RoleName.ADMIN));
                    break;
                case "pm":
                    roles.add(roleService.findByName(RoleName.PM));
                    break;
                case "user":
                    roles.add(roleService.findByName(RoleName.USER));
                    break;
            }
        }
        accountService.changeRole(id, roles);
    }
    public void saveAccountList(Account account){
        accountService.save(account);
    }
    public ResponseMessenger register(SignUpDTO signUpDTO){
        if(accountService.existedByUsername(signUpDTO.getUsername())){
            System.out.println("====3");
            return new ResponseMessenger("username_existed");

        }
        System.out.println("====");
        if (accountService.existedByEmail(signUpDTO.getEmail())){
            System.out.println("======");
            return new ResponseMessenger("email_existed");
        }
        Set<String> strRole = signUpDTO.getStrRoles();
        Set<Role> roles =new HashSet<>();
        strRole.forEach(role->{
            switch (role){
                case "admin":
                    Role adminRole = roleService.findByName(RoleName.ADMIN);
                    roles.add(adminRole);
                    break;
                case "pm":
                    Role pmRole = roleService.findByName(RoleName.PM);
                    roles.add(pmRole);
                    break;
                default:
                    Role userRole = roleService.findByName(RoleName.USER);
                    roles.add(userRole);
                    break;
            }
        });
        Account account = new Account(signUpDTO.getId(),signUpDTO.getName(),signUpDTO.getUsername(),
                signUpDTO.getEmail(),signUpDTO.getPassword(),roles);
        accountService.save(account);
        getAccountList();
        return new ResponseMessenger("Success!");

    }
    public ResponseMessenger checkMail(SignUpDTO signUpDTO){
        if (accountService.existedByEmail(signUpDTO.getEmail())){
            return new ResponseMessenger("email_existed");
        }
        Set<String> strRole = signUpDTO.getStrRoles();
        Set<Role> roles =new HashSet<>();
        strRole.forEach(role->{
            switch (role){
                case "admin":
                    Role adminRole = roleService.findByName(RoleName.ADMIN);
                    roles.add(adminRole);
                    break;
                case "pm":
                    Role pmRole = roleService.findByName(RoleName.PM);
                    roles.add(pmRole);
                    break;
                default:
                    Role userRole = roleService.findByName(RoleName.USER);
                    roles.add(userRole);
                    break;
            }
        });
        Account account = new Account(signUpDTO.getId(),signUpDTO.getName(),signUpDTO.getUsername(),
                signUpDTO.getEmail(),signUpDTO.getPassword(),roles);
        accountService.save(account);
        getAccountList();
        return new ResponseMessenger("Success!");

    }
    public ResponseMessenger login(SignInDTO signInDTO) {
        if (accountServiceIMPL.checkLogin(signInDTO.getUsername(), signInDTO.getPassword())) {
            Account account = accountServiceIMPL.findByUsername(signInDTO.getUsername());
            List<Account> accountLogin = new ArrayList<>();
            accountLogin.add(account);
            new Config<Account>().writeFile(accountServiceIMPL.PATH_FILE_PRINCIPAL,accountLogin);
            return new  ResponseMessenger("Login Success!");
        }else {
            return new ResponseMessenger("Login failed! - Please try again username or password!");
        }
    }
    public Account getCurrenAccount(){
        return accountService.getCurrentAccount();
    }
    public void updateAccount(Account newAccount){
        Account account = accountService.findById(accountService.getCurrentAccount().getId());
        account.setName(newAccount.getName());
        account.setUsername(newAccount.getUsername());
        account.setEmail(newAccount.getEmail());
        account.setPassword(newAccount.getPassword());
        new Config<Account>().writeFile(AccountServiceIMPL.PATH_FILE,accountList);
        new Config<Account>().writeFile(AccountServiceIMPL.PATH_FILE_PRINCIPAL,accountServiceIMPL.findAll());
    }
    public List<Account> findByRoleName(RoleName... roleNames){
        return accountService.findByRole(roleNames);
    }

    public void deleteUserById(int id) {
        accountService.remove(id);
    }
}
