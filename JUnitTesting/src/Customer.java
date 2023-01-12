import javax.security.sasl.SaslClient;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Customer {
    private int accountNumber;
    private ArrayList<Deposit> deposits = new ArrayList<>();
    private ArrayList<Withdraw> withdraws = new ArrayList<>();
    private double checkBalance;
    private double savingBalance;
    private double savingRate;
    private String name;
    public static final String CHECKING = "Checking";
    public static final String SAVING = "Saving";
    private final int OVERDRAFT = -100;

    Customer(){
        name = "";
        accountNumber = 0;
        checkBalance = 0;
        savingBalance = 0;
    }
    Customer(String name, int accountNumber, double checkDeposit, double savingDeposit){
        this.name = name;
        this.accountNumber = accountNumber;
        checkBalance = checkDeposit;
        savingBalance = savingDeposit;
    }
    public Date currentDate(){
        Date date = new Date();
        return date;
    }
    //Requires: amt >= 0
    //Modifies: this, deposits
    //Effects: deposits amt in specified account, returns 0
    public double deposit(double amt, Date date, String account){
        switch(account){
            case "Checking":
                deposits.add(new Deposit(amt, date, account));
                checkBalance += amt;
                break;
            case "Saving":
                deposits.add(new Deposit(amt, date, account));
                savingBalance += amt;
                break;
        }
        return 0;
    }
    //Requires: amt >= 0
    //Modifies: this, withdraws
    //Effects: checks if amt withdrawn goes past overdraft, if so, withdraws maximum amt possible; if not, withdraws amt from specified account, returns 0
    public double withdraw(double amt, Date date, String account){
        switch(account){
            case "Checking":
                if(checkOverdraft(amt, account)){
                    withdraws.add(new Withdraw((checkBalance - OVERDRAFT), date, account));
                    checkBalance = OVERDRAFT;
                }
                else{
                    withdraws.add(new Withdraw(amt, date, account));
                    checkBalance -= amt;
                }
                break;
            case "Saving":
                if(checkOverdraft(amt, account)){
                    withdraws.add(new Withdraw((savingBalance - OVERDRAFT), date, account));
                    savingBalance = OVERDRAFT;
                }
                else{
                    withdraws.add(new Withdraw(amt, date, account));
                    savingBalance -= amt;
                }
                break;
        }
        return 0;
    }
    //Requires: amt >= 0
    //Modifies: nothing
    //Effects: checks if withdrawing amt will go past OVERDRAFT, returns true if true; returns false if false
    private boolean checkOverdraft(double amt, String account){
        switch(account){
            case "Checking":
                if(checkBalance - amt < OVERDRAFT){
                    return true;
                }
                break;
            case "Saving":
                if(savingBalance - amt < OVERDRAFT){
                    return true;
                }
                break;
        }
        return false;
    }
    //do not modify
    public void displayDeposits(){
        for(Deposit d : deposits){
            System.out.println(d);
        }
    }
    //do not modify
    public void displayWithdraws(){
        for(Withdraw w : withdraws){
            System.out.println(w);
        }
    }
    public ArrayList<Deposit> getDeposits() {
        return deposits;
    }
    public ArrayList<Withdraw> getWithdraws() {
        return withdraws;
    }
    public double getCheckBalance() {
        return checkBalance;
    }
    public void setCheckBalance(double checkBalance) {
        this.checkBalance = checkBalance;
    }
    public double getSavingBalance() {
        return savingBalance;
    }
    public void setSavingBalance(double savingBalance) {
        this.savingBalance = savingBalance;
    }
    public int getOVERDRAFT() {
        return OVERDRAFT;
    }
}
