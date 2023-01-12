package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    //StoredItems.txt: stores all items in the shopping list
    //StoredPurchases.txt: stores all items in the purchased list

    //text fields where users input item's name, price and quantity
    public TextField tfName;
    public TextField tfPrice;
    public TextField tfQuantity;
    /*labels displaying selected shopping list item's name, quantity
    and total price along with cost of all items in shopping list*/
    public Label lbShopName;
    public Label lbShopQuantity;
    public Label lbShopTotal;
    public Label lbShopCost;

    /*labels displaying selected purchased list item's name, quantity
    and total price along with cost of all items in purchased list*/
    public Label lbPurchaseName;
    public Label lbPurchaseQuantity;
    public Label lbPurchaseTotal;
    public Label lbPurchaseCost;

    //shopping and purchased lists
    public ListView <Item> listShop = new ListView<>();
    public ListView <Item> listPurchase = new ListView<>();
    //buy item button
    public Button btnBuy;
    //load previous shopping/purchased list
    public Button btnLoadShopList;
    public Button btnLoadPurchaseList;

    //'buys' selected shopping list item; removes from the shopping list; adds it to purchased list
    public void buyItem(ActionEvent actionEvent) throws IOException{
        Item selectedItem;
        selectedItem = listShop.getSelectionModel().getSelectedItem();
        //adds selected/'bought' item to purchased list
        Item newItem = new Item(selectedItem.name, selectedItem.quantity, selectedItem.total);
        newItem.storePurchase();
        //removes selected/'bought' item from StoredItems.txt
        loadItems.copyToTemp("StoredItems.txt", newItem.name + ",\r" + newItem.quantity + ",\r" + newItem.total + "\r");
        listShop.getItems().clear();
        //loads all items stored in StoredItems.txt into a listview/the shopping list
        ArrayList<Item> shopItems = loadItems.loadListItems("StoredItems.txt");
        for(Item i : shopItems){
            listShop.getItems().add(i);
        }
        lbShopCost.setText(String.valueOf(loadItems.getCost()));
        lbShopName.setText("");
        lbShopQuantity.setText("");
        lbShopTotal.setText("");
        listPurchase.getItems().clear();
        //loads all items stored in StoredPurchases.txt in a listview/the purchased list
        ArrayList<Item> purchaseItems = loadItems.loadListItems("StoredPurchases.txt");
        for(Item i : purchaseItems){
            listPurchase.getItems().add(i);
        }
        lbPurchaseCost.setText(String.valueOf(loadItems.getCost()));
        lbPurchaseName.setText("");
        lbPurchaseQuantity.setText("");
        lbPurchaseTotal.setText("");
    }


    /*takes user inputted text and creates a new 'item' with those properties;
      adds to storedItems.txt;
      adds to and updates shopping list;
     */
    public void addItem(ActionEvent actionEvent) throws IOException {
        double oldCost;
        Item newItem = new Item(tfName.getText(),Integer.parseInt(tfQuantity.getText()),
                Double.parseDouble(tfPrice.getText())*Integer.parseInt(tfQuantity.getText()));
        newItem.storeItem();
        listShop.getItems().add(newItem);
        oldCost = Double.parseDouble(lbShopCost.getText());
        oldCost += Double.parseDouble(tfPrice.getText()) * Integer.parseInt(tfQuantity.getText());
        //lbShopCost.setText(String.valueOf(loadItems.getCost()+ Double.parseDouble(tfPrice.getText())*Integer.parseInt(tfQuantity.getText()) ));
        lbShopCost.setText(String.valueOf(oldCost));
        tfName.clear();
        tfQuantity.clear();
        tfPrice.clear();
    }

    //displays selected shopping list item's information
    public void displayShop(MouseEvent mouseEvent) {
        btnBuy.setDisable(false);
        Item displayItem;
        displayItem = listShop.getSelectionModel().getSelectedItem();
        lbShopName.setText(displayItem.name);
        lbShopQuantity.setText(String.valueOf(displayItem.quantity));
        lbShopTotal.setText(String.valueOf(displayItem.total));
    }

    //displays selected purchased list item's information
    public void displayPurchased(MouseEvent mouseEvent) {
        Item displayItem;
        displayItem = listPurchase.getSelectionModel().getSelectedItem();
        lbPurchaseName.setText(displayItem.name);
        lbPurchaseQuantity.setText(String.valueOf(displayItem.quantity));
        lbPurchaseTotal.setText(String.valueOf(displayItem.total));
    }

    //clears purchased list and StoredPurchases.txt; updates purchased list
    public void deleteAllPurchase(ActionEvent actionEvent) throws IOException {
        loadItems.delete("StoredPurchases.txt");
        listPurchase.getItems().clear();
        ArrayList<Item> purchaseItems = loadItems.loadListItems("StoredPurchases.txt");
        for(Item i : purchaseItems){
            listPurchase.getItems().add(i);
        }
        lbPurchaseCost.setText(String.valueOf(loadItems.getCost()));
        lbPurchaseName.setText("");
        lbPurchaseQuantity.setText("");
        lbPurchaseTotal.setText("");

    }


    //loads StoredItems.txt and updates shopping list
    public void loadShopList(ActionEvent actionEvent) throws IOException {
        listShop.getItems().clear();
        ArrayList<Item> shopItems = loadItems.loadListItems("StoredItems.txt");
        for(Item i : shopItems){
            listShop.getItems().add(i);
        }
        lbShopCost.setText(String.valueOf(loadItems.getCost()));
        lbShopName.setText("");
        lbShopQuantity.setText("");
        lbShopTotal.setText("");
        btnLoadShopList.setDisable(true);
    }

    //loads StoredPurchases.txt and updates purchased list
    public void loadPurchaseList(ActionEvent actionEvent) throws IOException {
        listPurchase.getItems().clear();
        ArrayList<Item> purchaseItems = loadItems.loadListItems("StoredPurchases.txt");
        for(Item i : purchaseItems){
            listPurchase.getItems().add(i);
        }
        lbPurchaseCost.setText(String.valueOf(loadItems.getCost()));
        lbPurchaseName.setText("");
        lbPurchaseQuantity.setText("");
        lbPurchaseTotal.setText("");
        btnLoadPurchaseList.setDisable(true);
    }
}
