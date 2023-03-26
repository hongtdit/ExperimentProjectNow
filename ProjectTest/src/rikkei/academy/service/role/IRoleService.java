package rikkei.academy.service.role;

import rikkei.academy.model.account.Role;
import rikkei.academy.model.account.RoleName;

import java.util.List;

public interface IRoleService<T> {
    List<T> findAll();

    Role findByName(RoleName roleName);
}
