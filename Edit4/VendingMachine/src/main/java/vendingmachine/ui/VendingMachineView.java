/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingmachine.ui;

import VendingMachineChange.Change;
import VendingMachineChange.changeCalculate;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import vendingmachine.dao.VendingMachineDao;
import vendingmachine.dao.VendingMachineDaoException;
import vendingmachine.dto.Item;

/**
 *
 * @author 15874
 */
public class VendingMachineView {
    
    private UserIO io;
    //这里io输出
   public VendingMachineView(UserIO io) {
    this.io = io;
}
    
    private VendingMachineDao dao;
    
    String userInput = "";

    /*这里请勿无效劳动：
    public VendingMachineView(UserIO myIo) {
    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/
    
    public void showMenu(){
        
        io.print("All items and their prices are listed below:");
        io.print("");
        io.print("1. Cola - 1.75 Dollars");
        io.print("2. Sprite - 1.73 Dollars");
        io.print("3. Water - 1.06 Dollar");
        io.print("4. Exit");
        io.print("");
        io.print("Please put in some amount of money before you start selecting from the above choices.");
//        Scanner sc = new Scanner(System.in);
//        String colaMoney = sc.nextLine();
//        System.out.println("You inserted " + colaMoney + " dallor.");
//        BigDecimal money = new BigDecimal(colaMoney);
//        return money;
        
    }
    
    public String moneyInfo(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    
    public void showMoney(){
        System.out.println("You inserted " + moneyInfo() + " dallor.");
    }
    
    public BigDecimal getUserInput(){
        BigDecimal money = new BigDecimal(moneyInfo());
        System.out.println("there is " + money);
        return money;       
    }
    
    public int showMoneyAndGetSelection(){
        io.print("All items and their prices are listed below:");
        io.print("");
        io.print("1. Cola - 1.75 Dollars");
        io.print("2. Sprite - 1.73 Dollars");
        io.print("3. Water - 1.06 Dollar");
        io.print("4. Exit");
        io.print("");
        io.print("Please put in some amount of money before you start selecting from the above choices.");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        return io.readInt("Please start selecting from the above choices.", 1, 4);
        
    }
    
    public Item colaInfo(){
        String itemId = "1";
        String itemName = "cola";
        String itemPrice = "1.75";
        String itemQuantity;
        Item currentCola = new Item(itemId);
        currentCola.setItemName(itemName);
        currentCola.setItemPrice(itemPrice);
        return currentCola;
    }
    
    public void colaChange() throws VendingMachineDaoException{
        System.out.println("Please enter the amount of money to comfirm the money you inserted.");
        Scanner sc = new Scanner(System.in);
        String price = sc.nextLine();
        BigDecimal amountMoney = new BigDecimal(price);
        Item nowCola = dao.getItem("1");
        String colaPrice = nowCola.getItemPrice();
        BigDecimal colaNowPrice = new BigDecimal(colaPrice);
        System.out.println("Total change is " + (amountMoney.subtract(colaNowPrice)).toString() + " dollars.");
        changeCalculate myMath = new changeCalculate();
        System.out.println("Here are your changes : " + myMath.calculate(Change.QUARTER, amountMoney, colaNowPrice) + " Quarter " + myMath.calculate(Change.DIME, amountMoney, colaNowPrice) + " Dime " + myMath.calculate(Change.NICKEL, amountMoney, colaNowPrice) + " Nickel " + myMath.calculate(Change.PENNY, amountMoney, colaNowPrice) + " Penny.");
    }
    
    public Item spriteInfo(){
        String itemId = "2";
        String itemName = "sprite";
        String itemPrice = "1.73";
        Item currentSprite = new Item(itemId);
        currentSprite.setItemName(itemName);
        currentSprite.setItemPrice(itemPrice);
        return currentSprite;
    }
    
    public void spriteChange(){
        System.out.println("Please enter the amount of money to comfirm the money you inserted.");
        Scanner sc = new Scanner(System.in);
        String price = sc.nextLine();
        BigDecimal amountMoney = new BigDecimal(price);
        Item nowSprite = spriteInfo();
        String spritePrice = nowSprite.getItemPrice();
        BigDecimal spriteNowPrice = new BigDecimal(spritePrice);
        System.out.println("Total change is " + (amountMoney.subtract(spriteNowPrice)).toString() + " dollars.");
        changeCalculate myMath = new changeCalculate();
        System.out.println("Here are your changes : " + myMath.calculate(Change.QUARTER, amountMoney, spriteNowPrice) + " Quarter " + myMath.calculate(Change.DIME, amountMoney, spriteNowPrice) + " Dime " + myMath.calculate(Change.NICKEL, amountMoney, spriteNowPrice) + " Nickel " + myMath.calculate(Change.PENNY, amountMoney, spriteNowPrice) + " Penny.");
    }
    
    public Item waterInfo(){
        String itemId = "3";
        String itemName = "water";
        String itemPrice = "1.06";
        Item currentWater = new Item(itemId);
        currentWater.setItemName(itemName);
        currentWater.setItemPrice(itemPrice);
        return currentWater;
    }
    
    public void waterChange(){
        System.out.println("Please enter the amount of money to comfirm the money you inserted.");
        Scanner sc = new Scanner(System.in);
        String price = sc.nextLine();
        BigDecimal amountMoney = new BigDecimal(price);
        Item nowWater = waterInfo();
        String waterPrice = nowWater.getItemPrice();
        BigDecimal waterNowPrice = new BigDecimal(waterPrice);
        System.out.println("Total change is " + (amountMoney.subtract(waterNowPrice)).toString() + " dollars.");
        changeCalculate myMath = new changeCalculate();
        System.out.println("Here are your changes : " + myMath.calculate(Change.QUARTER, amountMoney, waterNowPrice) + " Quarter " + myMath.calculate(Change.DIME, amountMoney, waterNowPrice) + " Dime " + myMath.calculate(Change.NICKEL, amountMoney, waterNowPrice) + " Nickel " + myMath.calculate(Change.PENNY, amountMoney, waterNowPrice) + " Penny.");
    }
    
    public Item exitInfo(){
        String itemId = "0";
        String itemName = "exit";
        String itemPrice = "0";
        Item currentExit = new Item(itemId);
        currentExit.setItemName(itemName);
        currentExit.setItemPrice(itemPrice);
        return currentExit;
    }
    
    public void returnMoney(){
        System.out.println("Please enter the amount of money to comfirm the money you inserted to get full refund.");
        Scanner sc = new Scanner(System.in);
        String price = sc.nextLine();
        BigDecimal amountMoney = new BigDecimal(price);
        Item nowExit = exitInfo();
        String exitPrice = nowExit.getItemPrice();
        BigDecimal exitNowPrice = new BigDecimal(exitPrice);
        System.out.println("Total change is " + (amountMoney.subtract(exitNowPrice)).toString() + " dollars.");
        changeCalculate myMath = new changeCalculate();
        System.out.println("Here are your changes : " + myMath.calculate(Change.QUARTER, amountMoney, exitNowPrice) + " Quarter " + myMath.calculate(Change.DIME, amountMoney, exitNowPrice) + " Dime " + myMath.calculate(Change.NICKEL, amountMoney, exitNowPrice) + " Nickel " + myMath.calculate(Change.PENNY, amountMoney, exitNowPrice) + " Penny.");
    }
    
    public void displayExitBanner(){
        io.print("GoodBye!!!");
    }
    
    public void displayUnknownCommandBanner(){
        io.print("Unknown COmmand!!!");
    }
    
    public VendingMachineView(UserIO io, VendingMachineDao dao){
        this.io = io;
        this.dao = dao;
    }
    
    
    public void displayItemList(List<Item> itemList){
        for(Item currentItem : itemList){
            String itemInfo = String.format("#%s : %s %s", currentItem.getItemId(), currentItem.getItemName(), currentItem.getItemPrice());
            io.print(itemInfo);
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayErrorMessage(String errorMsg){
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
    
    
}
