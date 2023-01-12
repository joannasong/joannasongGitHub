package sample;

import java.io.*;
import java.util.ArrayList;

public class loadItems {
    private static String name;
    private static String quantity;
    private static String total;
    private static FileReader fr;
    private static BufferedReader br;
    private static FileReader frTemp;
    private static BufferedReader brTemp;
    private static ArrayList<Item> a = new ArrayList<>();
    private static double cost;

    /*reads the contents of a txt file that has item information stored inside
    and puts it into a listview
     */
    public static ArrayList loadListItems(String file) throws IOException {
        a.clear();
        cost = 0.00;
        fr = new FileReader(file);
        br = new BufferedReader(fr);
        String line;
        String itemInfo = "";
        while((line = br.readLine()) != null){
            if(!line.equals(";")){
                itemInfo += line;
            }
            else{
                //creates a new Item object in an Item array list
                parseItem(itemInfo);
                itemInfo = "";
            }
        }
        return a;
    }

    //takes a string of item information and adds it to an Item array list
    private static void parseItem(String string) {
        int commaOne = 0;
        int commaTwo = 0;
        name = "";
        quantity = "";
        total = "";

        for(int i = 0; i < string.length(); i++){
            if(string.charAt(i) == ','){
                if(commaOne == 0){
                    commaOne = i;
                }
                else if(commaTwo==0){
                    commaTwo = i;
                }
            }
        }
        name = string.substring(0,commaOne);
        quantity = string.substring(commaOne+1,commaTwo);
        total = string.substring(commaTwo+1);
        cost +=  Double.parseDouble(total);
        a.add(new Item(name, Integer.parseInt(quantity),Double.parseDouble(total)));
    }

    public static double getCost() {
        return cost;
    }

    /*
    reads StoredItems.txt and searches for the bought item
    copies the contents of StoredItems.txt minus the bought item's information to temp.txt
    deletes content of StoredItems.txt and replaces it with contents of temp.txt
     */
    public static void copyToTemp(String file, String deletedItem) throws IOException {
        delete("temp.txt");
        FileWriter fwTemp = new FileWriter("temp.txt", true);
        BufferedWriter bwTemp = new BufferedWriter(fwTemp);
        FileWriter fwStored = new FileWriter("StoredItems.txt", true);
        BufferedWriter bwStored = new BufferedWriter(fwStored);
        fr = new FileReader(file);
        br = new BufferedReader(fr);
        String line;
        String itemInfo = "";
        while((line = br.readLine()) != null) {
            if (!line.equals(";")) {
                itemInfo = itemInfo + line + "\r";
            }
            else {
                //scans for 'bought' item
                if (itemInfo.equals(deletedItem)) {
                    itemInfo = "";
                }
                else{
                    itemInfo += ";\r";
                    bwTemp.write(itemInfo);
                    itemInfo ="";
                }
            }
        }
        bwTemp.close();
        delete("StoredItems.txt");
        //copies temp.txt's content to StoredItems.txt
        frTemp = new FileReader("temp.txt");
        brTemp = new BufferedReader(frTemp);
        while((line = brTemp.readLine()) != null){
            if(!line.equals(";")){
                itemInfo = itemInfo + line + "\r";
            }
            else{
                itemInfo += ";\r";
                bwStored.write(itemInfo);
                itemInfo="";
            }

        }
        bwStored.close();
    }

    //deletes the contents of a txt file
    public static void delete(String file) throws IOException{
        new FileWriter(file, false).close();
    }



}
