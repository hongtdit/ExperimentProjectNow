package rikkei.academy.model.account;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Account implements Serializable {
    private static  final long serialVersionUID = 1234567L;

    private  int id;
    private String name;
    private String username;
    private String email;
    private String password;
    private boolean status;
    private Set<Role> roles = new HashSet<>();

    public Account() {
    }

    public Account(int id, String name, String username, String email, String password, boolean status, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = status;
        this.roles = roles;
    }

    public Account(int id, String name, String username, String email, String password, Set<Role> roles) {
        this.id =id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password =password;
        this.roles = roles;
    }
    public RoleName getMaxRole() {
        return new ArrayList<>(this.roles).get(0).getName();
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
