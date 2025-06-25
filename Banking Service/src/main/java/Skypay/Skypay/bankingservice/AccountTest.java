package Skypay.Skypay.bankingservice;

import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class AccountTest {
    @Test
    public void testDepositAndWithdraw() {
        Account account = new Account();
        account.deposit(1000, "10-01-2012");
        account.deposit(2000, "13-01-2012");
        account.withdraw(500, "14-01-2012");

        // Pas de méthode getBalance, donc on test indirectement via exceptions
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(10000, "15-01-2012"));
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0, "16-01-2012"));
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-100, "16-01-2012"));
    }
}
