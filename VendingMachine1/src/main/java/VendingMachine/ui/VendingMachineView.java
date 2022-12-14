/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachine.ui;

import ChangeServiceLayer.Change;
import ChangeServiceLayer.changeCalculate;
import VendingMachine.Dao.VendingMachineDaoException;
import VendingMachine.Dto.Item;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Administrator
 */
public class VendingMachineView {
    private VendingMachineIO io;


    
    public int showMoneyAndGetSelection(List<Item> items){
        io.print("");
        io.print("=== Vending Machine ===");
        io.print("All items and their prices are listed below:");
        io.print("");
        for(Item currentItem : items){
            String itemInfo = String.format("#%s : %s %s", currentItem.getItemId(), currentItem.getItemName(), currentItem.getItemPrice());
            io.print(itemInfo);
        }
        io.print("#4 : Exit");
        io.print("");
        io.print("Please put in some amount of money before you start selecting from the above choices.");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        return io.readInt("Please start selecting from the above choices.", 1, 4);
        
    }
    
    
    
    public void itemChange(Item item) throws VendingMachineDaoException{
        System.out.println("Please enter the amount of money to comfirm the money you inserted.");
        Scanner sc = new Scanner(System.in);
        String price = sc.nextLine();
        BigDecimal amountMoney = new BigDecimal(price);
        String itemPrice = item.getItemPrice();
        BigDecimal itemNowPrice = new BigDecimal(itemPrice);
        if(Integer.parseInt(item.getItemId()) == 4){
            io.print("You chose 'Exit'! Please take all the refund from the machine!");
            System.out.println("Total change is " + (amountMoney.subtract(itemNowPrice)).toString() + " dollars.");
            changeCalculate myMath = new changeCalculate();
            System.out.println("Here are your changes : " + myMath.calculate(Change.QUARTER, amountMoney, itemNowPrice) + " Quarter " + myMath.calculate(Change.DIME, amountMoney, itemNowPrice) + " Dime " + myMath.calculate(Change.NICKEL, amountMoney, itemNowPrice) + " Nickel " + myMath.calculate(Change.PENNY, amountMoney, itemNowPrice) + " Penny.");
        }else{
            if(amountMoney.compareTo(itemNowPrice) == -1){
                System.out.println("InsufficientFunds! Please take all the refund from the machine and try again!");
                itemNowPrice = new BigDecimal("0");
                System.out.println("Total change is " + (amountMoney.subtract(itemNowPrice)).toString() + " dollars.");
                changeCalculate myMath = new changeCalculate();
                System.out.println("Here are your changes : " + myMath.calculate(Change.QUARTER, amountMoney, itemNowPrice) + " Quarter " + myMath.calculate(Change.DIME, amountMoney, itemNowPrice) + " Dime " + myMath.calculate(Change.NICKEL, amountMoney, itemNowPrice) + " Nickel " + myMath.calculate(Change.PENNY, amountMoney, itemNowPrice) + " Penny.");
            }else{
                System.out.println("Total change is " + (amountMoney.subtract(itemNowPrice)).toString() + " dollars.");
                changeCalculate myMath = new changeCalculate();
                System.out.println("Here are your changes : " + myMath.calculate(Change.QUARTER, amountMoney, itemNowPrice) + " Quarter " + myMath.calculate(Change.DIME, amountMoney, itemNowPrice) + " Dime " + myMath.calculate(Change.NICKEL, amountMoney, itemNowPrice) + " Nickel " + myMath.calculate(Change.PENNY, amountMoney, itemNowPrice) + " Penny.");  
            }
        }
    }
    
    
    
    
    
    public void displayExitBanner(){
        io.print("GoodBye!!!");
    }
    
    public void displayUnknownCommandBanner(){
        io.print("Unknown COmmand!!!");
    }
    
    public VendingMachineView(VendingMachineIO io){
        this.io = io;
        
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
