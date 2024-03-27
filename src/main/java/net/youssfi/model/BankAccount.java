package net.youssfi.model;
import java.util.Objects;
import java.util.UUID;

public abstract class BankAccount extends Object {
    public String accountId=null;
    public double balance=0;
    public String currency=null;
    public AccountStatus status;

    public BankAccount(){
        this.accountId = UUID.randomUUID().toString();
        this.status = AccountStatus.CREATED;
    }

    public BankAccount(double balance, String currency ){
        this();
        this.balance = balance;
        this.currency = currency;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "\n BankAccount{" +
                "accountId='" + accountId + '\'' +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                ", status=" + status +
                '}';
    }
    public abstract String getType();
}
