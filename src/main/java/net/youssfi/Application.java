package net.youssfi;

import net.youssfi.business.BankAccountService;
import net.youssfi.business.BankAccountServiceImpl;
import net.youssfi.exceptions.AccountNotFoundException;
import net.youssfi.model.BankAccount;
import net.youssfi.model.CurrentAccount;
import net.youssfi.model.SavingAccount;

import java.util.List;

public class Application {
    public static void main(String[] args) throws Exception {
        BankAccountService bankAccountService = new BankAccountServiceImpl();
        bankAccountService.addBankAccount(new CurrentAccount(500, "MAD", 200));
        bankAccountService.addBankAccount(new SavingAccount(700, "MAD", 2));
        List<BankAccount> allAccount = bankAccountService.getAllAccounts();
        System.out.println(allAccount);
        allAccount.forEach(System.out::println);

        System.out.println("==========================================");

        BankAccount bankAccount3 = new CurrentAccount(100, "MAD", 50);
        bankAccount3.setAccountId("CC1");
        bankAccountService.addBankAccount(bankAccount3);

        try {
            BankAccount accountById = bankAccountService.getAccountById("CC2");
            System.out.println(accountById.toString());

        } catch (AccountNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        System.out.println("**********************************");
        System.out.println("Programme continue ");
    }
}
