import org.junit.Before;
import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class JUnitTesting {
    Customer testCustomer;
    Date testDate = new Date();

    @Before
    public void setup(){
        testCustomer = new Customer();
    }

    @Test
    public void customerDepositTest(){
        //reset balance
        testCustomer.setCheckBalance(0.0);
        testCustomer.setSavingBalance(0.0);
        //deposit amt
        testCustomer.deposit(10.0, testDate, "Checking");
        //check amt has been added to balance
        assertEquals(testCustomer.getCheckBalance(), 10.0, 0.0);
        //check deposit was added to deposits array
        assertEquals(testCustomer.getDeposits().size(), 1);
        //compare amount, date, and account values from deposits array to inputted values
        assertEquals(testCustomer.getDeposits().get(0).getAmount(), 10.0, 0.0);
        assertEquals(testCustomer.getDeposits().get(0).getDate(), testDate);
        assertEquals(testCustomer.getDeposits().get(0).getAccount(), "Checking");
        //repeat process with other account
        testCustomer.deposit(10.0, testDate, "Saving");
        assertEquals(testCustomer.getSavingBalance(), 10.0, 0.0);
        assertEquals(testCustomer.getDeposits().size(), 2);
        assertEquals(testCustomer.getDeposits().get(1).getAmount(), 10.0, 0.0);
        assertEquals(testCustomer.getDeposits().get(1).getDate(), testDate);
        assertEquals(testCustomer.getDeposits().get(1).getAccount(), "Saving");
    }

    @Test
    public void customerWithdrawTest(){
        //reset balance
        testCustomer.setCheckBalance(0.0);
        //deposit amt
        testCustomer.deposit(100.0, testDate, "Checking");
        //check deposit has been added to balance
        assertEquals(testCustomer.getCheckBalance(), 100.0, 0.0);
        //withdraw amt
        testCustomer.withdraw(90.0, testDate, "Checking");
        //check amt has been withdrawn from balance
        assertEquals(testCustomer.getCheckBalance(), 10.0, 0.0);
        //check withdrawal has been added to withdraws array
        assertEquals(testCustomer.getWithdraws().size(), 1);
        //compare amount, date, and account from withdraws array to inputted values
        assertEquals(testCustomer.getWithdraws().get(0).getAmount(), 90.0, 0.0);
        assertEquals(testCustomer.getWithdraws().get(0).getDate(), testDate);
        assertEquals(testCustomer.getWithdraws().get(0).getAccount(), "Checking");
        //reset balance
        testCustomer.setCheckBalance(0.0);
        //withdraw amt past overdraft limit
        testCustomer.withdraw(150.0, testDate, "Checking");
        //check balance has not gone past overdraft limit
        assertEquals(testCustomer.getCheckBalance(), -100.0, 0.0);
        //check withdrawal has been added to withdraws array
        assertEquals(testCustomer.getWithdraws().size(), 2);
        //compare amount, date, and account from withdraws array to inputted values
        assertEquals(testCustomer.getWithdraws().get(1).getAmount(), 100.0, 0.0);
        assertEquals(testCustomer.getWithdraws().get(1).getDate(), testDate);
        assertEquals(testCustomer.getWithdraws().get(1).getAccount(), "Checking");

        //repeat with other account
        testCustomer.setSavingBalance(0.0);
        testCustomer.deposit(100.0, testDate, "Saving");
        assertEquals(testCustomer.getSavingBalance(), 100.0, 0.0);
        testCustomer.withdraw(90.0, testDate, "Saving");
        assertEquals(testCustomer.getSavingBalance(), 10.0, 0.0);
        assertEquals(testCustomer.getWithdraws().size(), 3);
        assertEquals(testCustomer.getWithdraws().get(2).getAmount(), 90.0, 0.0);
        assertEquals(testCustomer.getWithdraws().get(2).getDate(), testDate);
        assertEquals(testCustomer.getWithdraws().get(2).getAccount(), "Saving");
        testCustomer.setSavingBalance(0.0);
        testCustomer.withdraw(150.0, testDate, "Saving");
        assertEquals(testCustomer.getSavingBalance(), -100.0, 0.0);
        assertEquals(testCustomer.getWithdraws().size(), 4);
        assertEquals(testCustomer.getWithdraws().get(3).getAmount(), 100.0, 0.0);
        assertEquals(testCustomer.getWithdraws().get(3).getDate(), testDate);
        assertEquals(testCustomer.getWithdraws().get(3).getAccount(), "Saving");
    }

    @Test
    public void withdrawToStringTest(){
        //create new withdrawal
        Withdraw testCheckWithdraw = new Withdraw(10.0, testDate, "Checking");
        //check withdraw.toString equals expected result
        assertEquals(testCheckWithdraw.toString(), "Withdrawl of: $10.0 " + "Date: " + testDate + " from account: Checking");
        //repeat with other account
        Withdraw testSavingWithdraw = new Withdraw(20.0, testDate, "Saving");
        assertEquals(testSavingWithdraw.toString(), "Withdrawl of: $20.0 " + "Date: " + testDate + " from account: Saving");



    }

    @Test
    public void depositToStringTest(){
        //create new deposit
        Deposit testCheckDeposit = new Deposit(10.0, testDate, "Checking");
        //check deposit.toString equals expected result
        assertEquals(testCheckDeposit.toString(), "Deposit of: $10.0 " + "Date: " + testDate + " into account: Checking");
        //repeat with other account
        Deposit testSavingDeposit = new Deposit(20.0, testDate, "Saving");
        assertEquals(testSavingDeposit.toString(), "Deposit of: $20.0 " + "Date: " + testDate + " into account: Saving");
    }
}
