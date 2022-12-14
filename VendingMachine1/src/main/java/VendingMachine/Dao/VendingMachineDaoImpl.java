/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachine.Dao;

import VendingMachine.Dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Administrator
 */
public class VendingMachineDaoImpl implements VendingMachineDao{

    public static final String VENDINGMACHINE_FILE = "vendingmachine.txt";
    public static final String DELIMITER = "::";
    
    private Map<String, Item> items = new HashMap<>();
    
    

    @Override
    public List<Item> getAllItems() throws VendingMachineDaoException{
        loadVendingMachine();
        return new ArrayList<Item>(items.values());
    }

    @Override
    public Item getItem(String itemId) throws VendingMachineDaoException{
        loadVendingMachine();
        return items.get(itemId);
    }
    
    @Override
    public void getItemInventory(Item item) throws VendingMachineDaoException {
        int itemQuality = Integer.parseInt(item.getItemQuantity()) - 1;
        item.setItemQuantity(Integer.toString(itemQuality));
        items.put(item.getItemId(), item);
        writeVendingMachine();
    }

    private Item unmarshallItem(String itemAsText){
        String[] itemTokens = itemAsText.split(DELIMITER);
        String itemId = itemTokens[0];
        Item itemFromFile = new Item(itemId);
        itemFromFile.setItemName(itemTokens[1]);
        itemFromFile.setItemPrice(itemTokens[2]);
        itemFromFile.setItemQuantity(itemTokens[3]);
        return itemFromFile;        
    }
    
    private void loadVendingMachine() throws VendingMachineDaoException{
        Scanner sc;
        try{
            sc = new Scanner (new BufferedReader(new FileReader(VENDINGMACHINE_FILE)));
        } catch (FileNotFoundException e){
            throw new VendingMachineDaoException("Could not load vendingmachine data into momory.", e);
        }
        String currentLine;
        Item currentItem;
        while(sc.hasNextLine()){
            currentLine = sc.nextLine();
            currentItem = unmarshallItem (currentLine);
            items.put(currentItem.getItemId(), currentItem);
        }
        sc.close();
    }
    
    private String marshallItem(Item aItem){
        String itemAsText = aItem.getItemId() + DELIMITER;
        itemAsText += aItem.getItemName() + DELIMITER;
        itemAsText += aItem.getItemPrice() + DELIMITER;
        itemAsText += aItem.getItemQuantity();
        return itemAsText;
    }
    
    private void writeVendingMachine() throws VendingMachineDaoException{
        PrintWriter out;
        try{
            out = new PrintWriter(new FileWriter(VENDINGMACHINE_FILE));
        } catch(IOException e){
            throw new VendingMachineDaoException("Could not save item data.", e);
        }
        String itemAsText;
        List<Item> itemList = this.getAllItems();
        for(Item currentItem : itemList){
            itemAsText = marshallItem(currentItem);
            out.println(itemAsText);
            out.flush();
        }
        out.close();
    }

    
    
}
