import java.util.Date;

public class Deposit {
    private double amount;
    private Date date;
    private String account;

    Deposit(double amount, Date date, String account){
        this.amount = amount;
        this.date = date;
        this.account = account;
    }
    //Requires: nothing
    //Modifies: nothing
    //Effects: returns amount deposited, the date, and the account that received the deposit
    public String toString(){
        return "Deposit of: $" + amount + " Date: " + date + " into account: " + account;
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
