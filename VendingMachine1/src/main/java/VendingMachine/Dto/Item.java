/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachine.Dto;

/**
 *
 * @author Administrator
 */
public class Item {
    private String itemId;
    private String itemName;
    private String itemPrice;
    private String itemQuantity;
    
    public Item(String itemId){
        this.itemId = itemId;        
    }
    
    public String getItemName(){
        return itemName;
    }
    
    public void setItemName(String itemName){
        this.itemName = itemName;
    }
    
    public String getItemPrice(){
        return itemPrice;
    }
    
    public void setItemPrice(String itemPrice){
        this.itemPrice = itemPrice;
    }
    
    public String getItemId(){
        return itemId;
    }
    
    public String getItemQuantity(){
        return itemQuantity;
    }
    
    public void setItemQuantity(String itemQuantity){
        this.itemQuantity = itemQuantity;
    }
}
