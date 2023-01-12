package sample;

import java.io.*;

public class Item {
    //item name
    public String name;
    //purchased quantity
    public int quantity;
    //item price
    public double total;

    Item(String n, int q, double t){
        name = n;
        quantity = q;
        total = t;
    }

    public String toString(){
        return name;
    }

    //saves item's information to StoredItems.txt
    public void storeItem() throws IOException{
        FileWriter fwStored = new FileWriter("StoredItems.txt", true);
        BufferedWriter bwStored = new BufferedWriter(fwStored);
        bwStored.write(name + ",\r");
        bwStored.write(quantity + ",\r");
        bwStored.write(total + "\r;\r");
        bwStored.close();
    }

    //saves item's information to StoredPurchases.txt
    public void storePurchase() throws IOException{
        FileWriter fwStored = new FileWriter("StoredPurchases.txt", true);
        BufferedWriter bwStored = new BufferedWriter(fwStored);
        bwStored.write(name + ",\r");
        bwStored.write(quantity + ",\r");
        bwStored.write(total + "\r;\r");
        bwStored.close();
    }







}


