package rikkei.academy.service.account;

import rikkei.academy.config.Config;
import rikkei.academy.model.account.Account;
import rikkei.academy.model.account.Role;
import rikkei.academy.model.account.RoleName;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AccountServiceIMPL implements IAccountService {
    public static String PATH_FILE_PRINCIPAL = "src/rikkei/academy/data/account/userprincipal.txt";
    public static String PATH_FILE ="src/rikkei/academy/data/account/account.txt";
    public   static List<Account> accountList = new Config<Account>().readFile(PATH_FILE);
    public static List<Account> accountPrincipal = new Config<Account>().readFile(PATH_FILE_PRINCIPAL);
    private static final Config<Account> config = new Config<>();

    @Override
    public List<Account> findAll() {
        new Config<Account>().writeFile(PATH_FILE, accountList);
        return accountList;
    }

    @Override
    public void save(Account account) {
        accountList.add(account);
        new Config<Account>().writeFile(PATH_FILE,accountList);
    }

    @Override
    public void remove(int id) {
        accountList.remove(findById(id));
        config.writeFile(PATH_FILE,accountList);

    }

    @Override
    public Account findById(int id) {
        return accountList.stream().filter(account -> account.getId() == id).findAny().orElse(null);
    }

    @Override
    public boolean existedByUsername(String username) {
        for (int i = 0; i < accountList.size(); i++) {
            if (username.equals(accountList.get(i).getUsername())){
                return true;
            }

        }
        return false;
    }

    @Override
    public boolean existedByEmail(String email) {
        for (int i = 0; i < accountList.size(); i++) {
            if (email.equals(accountList.get(i).getEmail())){
                return true;
            }

        }
        return false;
    }


    @Override
    public boolean checkLogin(String username, String password) {
        for (int i = 0; i < accountList.size(); i++) {
            if (username.equals(accountList.get(i).getUsername())&&password.equals(accountList.get(i).getPassword())){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Account> findByRole(RoleName... roleNames) {
        List<Account> find = new ArrayList<>();

        for (Account account : accountList) {
            for (RoleName role : roleNames) {
                if (account.getMaxRole() == role) {
                    find.add(account);
                }
            }
        }
        return find;
    }

    @Override
    public void changeStatus(int id) {
        Account account = findById(id);
        account.setStatus(!account.isStatus());
        new Config<Account>().writeFile(PATH_FILE,accountList);
    }

    @Override
    public void changeRole(int id, Set<Role> roles) {
        findById(id).setRoles(roles);
        new Config<Account>().writeFile(PATH_FILE,accountList);

    }


    @Override
    public  Account findByUsername(String username) {
        for (int i = 0; i < accountList.size(); i++) {
            if (username.equals(accountList.get(i).getUsername())){
                return accountList.get(i);
            }

        }
        return null;
    }

    @Override
    public Account getCurrentAccount() {
        if (new Config<Account>().readFile(PATH_FILE_PRINCIPAL) != null){
            Account account = new Config<Account>().readFile(PATH_FILE_PRINCIPAL).get(0);
            return account;
        }
        return null;
    }

}
