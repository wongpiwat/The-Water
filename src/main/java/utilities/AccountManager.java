package utilities;

import models.Account;

public class AccountManager {
    private static Account account;

    public void setAccount(Account account) {
        this.account = account;
    }

    public static Account getAccount() {
        return account;
    }
}
