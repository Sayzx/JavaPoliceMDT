package com.sayzx.panel.accountsystem;


/**
 * Classe représentant un compte utilisateur.
 */

public class Account {
    private String username;
    private String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    /**
     * @param username
     * @param password
     * @return
     */

    public static boolean login(String username, String password) {
        for (Account account : SysSystem.getAccounts()) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
