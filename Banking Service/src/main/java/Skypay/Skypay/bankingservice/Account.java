package Skypay.Skypay.bankingservice;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Account {
    private static class Transaction {
        private final String date;
        private final int amount;
        private final int balanceAfter;

        public Transaction(String date, int amount, int balanceAfter) {
            this.date = date;
            this.amount = amount;
            this.balanceAfter = balanceAfter;
        }

        public String getDate() {
            return date;
        }

        public int getAmount() {
            return amount;
        }

        public int getBalanceAfter() {
            return balanceAfter;
        }
    }

    private final List<Transaction> transactions = new ArrayList<>();
    private int balance = 0;

    public void deposit(int amount, String date) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Le montant du dépôt doit être positif");
        }
        balance += amount;
        transactions.add(new Transaction(date, amount, balance));
    }

    public void withdraw(int amount, String date) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Le montant du retrait doit être positif");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Fonds insuffisants");
        }
        balance -= amount;
        transactions.add(new Transaction(date, -amount, balance));
    }

    public void printStatement() {
        System.out.println("DATE       | AMOUNT | BALANCE");
        ListIterator<Transaction> iterator = transactions.listIterator(transactions.size());
        while (iterator.hasPrevious()) {
            Transaction t = iterator.previous();
            System.out.printf("%s | %d | %d%n", t.getDate(), t.getAmount(), t.getBalanceAfter());
        }
    }
}
