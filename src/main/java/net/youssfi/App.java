package net.youssfi;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.youssfi.model.BankAccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.youssfi.model.CurrentAccount;
import net.youssfi.model.SavingAccount;

public class App {
    public static void main(String[] args) throws JsonProcessingException {

        BankAccount[] accounts = new BankAccount[5];
        accounts[0] = new CurrentAccount(100, "MAD", 555);
        accounts[1] = new CurrentAccount(200, "USA", 555);
        accounts[2] = new SavingAccount(300, "MAD", 5);
        accounts[3] = new SavingAccount(400, "EU", 6);
        accounts[4] = new CurrentAccount(500, "MAD", 555);

        for (BankAccount acc : accounts) {
            if (acc instanceof CurrentAccount) {
                System.out.println(((CurrentAccount) acc).getType());
                System.out.println("Over Draft " + ((CurrentAccount) acc).getOverDraft());
            } else if (acc instanceof SavingAccount) {
                System.out.println(((SavingAccount) acc).getType());
                System.out.println("Rate " + ((SavingAccount) acc).getInterestRate());
            }
        }

        List<BankAccount> bankAccountsList = new ArrayList<>();
        bankAccountsList.add(new CurrentAccount(700, "MAD", 555));
        bankAccountsList.add(new CurrentAccount(300, "USA", 200));
        bankAccountsList.add(new SavingAccount(400, "MAD", 4));
        bankAccountsList.add(new SavingAccount(500, "EU", 2));

        System.out.println(bankAccountsList);

        Map<String, BankAccount> bankAccountMap = new HashMap<>();
        bankAccountMap.put("acc1", bankAccountsList.get(0));
        bankAccountMap.put("acc2", bankAccountsList.get(1));
        bankAccountMap.put("acc3", bankAccountsList.get(2));

        System.out.println("========= Display the map list using key =========");
        for (String key : bankAccountMap.keySet()) {
            System.out.println(bankAccountMap.get(key));
        }
        System.out.println("========= Display the map list using value =========");
        for (BankAccount b : bankAccountMap.values()) {
            System.out.println(b);
        }
        System.out.println("========= Display the map list using Json =========");
        for (BankAccount b : bankAccountMap.values()) {
            System.out.println(toJson(b));
        }

    }

    public static String toJson(Object o) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
    }


}