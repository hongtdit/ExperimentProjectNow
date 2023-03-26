package rikkei.academy.service.role;

import rikkei.academy.model.account.Role;
import rikkei.academy.model.account.RoleName;

import java.util.ArrayList;
import java.util.List;

public class RoleServiceIMPL implements IRoleService{
    public static List<Role> roleList = new ArrayList<>();
    static {
        roleList.add(new Role(1, RoleName.ADMIN));
        roleList.add(new Role(2, RoleName.PM));
        roleList.add(new Role(3,RoleName.USER));
    }

    @Override
    public List findAll() {
        return roleList;
    }

    @Override
    public Role findByName(RoleName roleName) {
        for (int i = 0; i < roleList.size(); i++) {
            if (roleName == roleList.get(i).getName()){
                return roleList.get(i);
            }
        }
        return null;
    }
}
