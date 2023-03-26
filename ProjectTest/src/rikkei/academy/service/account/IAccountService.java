package rikkei.academy.service.account;

import rikkei.academy.model.account.Account;
import rikkei.academy.model.account.Role;
import rikkei.academy.model.account.RoleName;
import rikkei.academy.service.IGeneric;

import java.util.List;
import java.util.Set;

public interface IAccountService extends IGeneric<Account> {
    boolean existedByUsername(String username);
    boolean existedByEmail(String email);
    boolean checkLogin(String username, String password);
    List<Account> findByRole(RoleName... roleNames);
    void changeStatus(int id);
    void changeRole(int id, Set<Role> roles);

    Account findByUsername(String username);
   Account getCurrentAccount();


}
