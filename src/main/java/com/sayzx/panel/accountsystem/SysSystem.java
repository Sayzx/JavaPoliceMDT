package com.sayzx.panel.accountsystem;

import java.util.ArrayList;
import java.util.List;

public class SysSystem {
    private List<Account> accounts = new ArrayList<>();

    public void createAccount(String username, String password) {
        accounts.add(new Account(username, password));
    }


    /**
     * @param username
     * @param password
     * @return
     */
    public boolean login(String username, String password) {
        for (Account account : accounts) {
            if (account.login(username, password)) {
                return true;
            }
        }
        return false;
    }

    public void logout() {
        // logout the user

    }
}
