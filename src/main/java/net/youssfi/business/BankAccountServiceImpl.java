package net.youssfi.business;

import net.youssfi.exceptions.AccountNotFoundException;
import net.youssfi.exceptions.BalanceNotSufficientException;
import net.youssfi.model.AccountStatus;
import net.youssfi.model.BankAccount;
import net.youssfi.model.CurrentAccount;
import net.youssfi.model.SavingAccount;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class BankAccountServiceImpl implements BankAccountService {
    List<BankAccount> bankAccountList = new ArrayList<>();

    @Override
    public BankAccount addBankAccount(BankAccount account) {
        bankAccountList.add(account);
        return account;
    }

    @Override
    public List<BankAccount> getAllAccounts() {
        return bankAccountList;
    }

    @Override
    public BankAccount getAccountById(String id) throws AccountNotFoundException {
        return bankAccountList.stream().filter(bankAccount -> bankAccount.getAccountId().equals(id)).findFirst().orElseThrow(() -> new AccountNotFoundException(String.format("Bank Account %s Not Found ", id)));
       /* l'Ancien methode
       for(BankAccount acc : bankAccountList){
            if(acc.getAccountId().equals(id)){
                return acc;
            }
        }
        throw new AccountNotFoundException("Bank Account Not Found ");
        */
    }

    @Override
    public void addRandomData(int size) {
        AccountStatus[] values = AccountStatus.values();
        Random random = new Random();
        for (int i = 0; i <= size; i++) {
            BankAccount bankAccount;
            if (Math.random() > 0.5) {
                bankAccount = new CurrentAccount(Math.random() * 50000, Math.random() > 0.5 ? "MAD" : "USD", Math.random() * 1000000);
                bankAccount.setStatus(values[random.nextInt(values.length)]);
            } else {
                bankAccount = new SavingAccount(Math.random() * 50000, Math.random() > 0.5 ? "MAD" : "USD", 3 + Math.random() * 7);
                bankAccount.setStatus(values[random.nextInt(values.length)]);
            }
            bankAccountList.add(bankAccount);
        }
    }

    @Override
    public void debit(String accountId, double amount) throws AccountNotFoundException, BalanceNotSufficientException {
        BankAccount accountById = getAccountById(accountId);
        if (amount >= accountById.getBalance()) throw new BalanceNotSufficientException("Balance not sufficient");
        accountById.setBalance(accountById.getBalance() - amount);
    }

    @Override
    public void credit(String accountId, double amount) throws AccountNotFoundException {
        BankAccount accountById = getAccountById(accountId);
        accountById.setBalance(accountById.getBalance() + amount);
    }

    @Override
    public void transfer(String accountSource, String accountDestination, double amount) throws AccountNotFoundException, BalanceNotSufficientException {
        debit(accountSource, amount);
        credit(accountDestination, amount);
    }

    @Override
    public List<BankAccount> getSavingAccount() {
        /////// Declarative Approached ////////
        return bankAccountList.stream().filter(acc -> acc instanceof SavingAccount).toList();
        /////// Imperative Approached ////////
        /*List<BankAccount> savingAccountList = new ArrayList<>();
        for(BankAccount bankAccount : bankAccountList){
            if(bankAccount instanceof SavingAccount){
                savingAccountList.add(bankAccount);
            }
        }
        return savingAccountList;*/
        //return  bankAccountList.stream().map(bankAccount -> b);
    }

    @Override
    public List<BankAccount> getCurrentAccount() {
        return bankAccountList.stream().filter(acc -> acc.getType().equals("CURRENT_ACCOUNT")).toList();
    }

    @Override
    public double getTotalBalance() {
        return bankAccountList.stream().mapToDouble(BankAccount::getBalance).sum();
    }

    @Override
    public List<BankAccount> searchAccounts() {
        return null;
    }
}
