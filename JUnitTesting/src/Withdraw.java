import java.util.Date;

public class Withdraw {
    private double amount;
    private Date date;
    private String account;

    Withdraw(double amount, Date date, String account){
        this.amount = amount;
        this.date = date;
        this.account = account;
    }
    //Requires: nothing
    //Modifies: nothing
    //Effects: returns amount withdrawn, the date, and the account withdrawn from
    public String toString(){
        return "Withdrawl of: $" + amount + " Date: " + date + " from account: " + account;
    }
    public double getAmount() {
        return amount;
    }
    public Date getDate() {
        return date;
    }
    public String getAccount() {
        return account;
    }
}
