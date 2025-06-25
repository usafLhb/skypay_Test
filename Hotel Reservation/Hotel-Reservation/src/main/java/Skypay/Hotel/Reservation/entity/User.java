package Skypay.Hotel.Reservation.entity;

public class User {
    private final int userId;
    private int balance;

    public User(int userId, int balance) {
        this.userId = userId;
        this.balance = balance;
    }

    public int getUserId() {
        return userId;
    }

    public int getBalance() {
        return balance;
    }

    public void debit(int amount) {
        if (amount > balance) throw new IllegalArgumentException("Solde insuffisant");
        balance -= amount;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + userId + ", balance=" + balance + '}';
    }
}
