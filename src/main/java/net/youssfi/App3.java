package net.youssfi;

import net.youssfi.business.BankAccountService;
import net.youssfi.business.BankAccountServiceImpl;
import net.youssfi.exceptions.AccountNotFoundException;
import net.youssfi.exceptions.BalanceNotSufficientException;
import net.youssfi.model.BankAccount;
import net.youssfi.model.CurrentAccount;
import net.youssfi.utils.DataTransformationUtils;

import java.util.ArrayList;
import java.util.List;

public class App3 {
    public static void main(String[] args) throws AccountNotFoundException {
        BankAccountService bankAccountService = new BankAccountServiceImpl();
        bankAccountService.addRandomData(20);
        /*System.out.println("==============Display as a Json result======================== ");
        //bankAccountService.getAllAccounts().forEach(bankAccount -> System.out.println(DataTransformationUtils.toJson(bankAccount)));
        bankAccountService.getAllAccounts().stream().map(DataTransformationUtils::toJson).forEach(System.out::println);
        System.out.println("==============Display as a Json result======================== ");*/
        BankAccount bankAccount = new CurrentAccount(100, "MAD", 50);

        bankAccount.setAccountId("CC1");
        bankAccountService.addBankAccount(bankAccount);
        //bankAccountService.getAllAccounts().forEach(System.out::println);

        try {
            BankAccount bankAccount1 = bankAccountService.getAccountById("CC1");
            System.out.println(DataTransformationUtils.toJson(bankAccount1));
            bankAccountService.debit(bankAccount1.getAccountId(),150);
            System.out.println(DataTransformationUtils.toJson(bankAccount1));
        } catch (AccountNotFoundException | BalanceNotSufficientException e) {
            System.out.println(e.getMessage());
        };
        List<BankAccount> savingAccountList = new ArrayList<>();
        savingAccountList = bankAccountService.getSavingAccount();
        System.out.println(DataTransformationUtils.toJson(savingAccountList));
        ;
        System.out.println("================Total Balance======================== ");
        System.out.println(bankAccountService.getTotalBalance());

    }
}
