/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.vendingmachine.controller;

import ServiceMoney.Changes;
import ServiceMoney.moneyChange;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoException;
import com.sg.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.sg.vendingmachine.dto.Product;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.NoItemInventoryException;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.stream.Collectors;

/**
 *
 * @author lionwife
 */
public class VendingMachineController {
    private VendingMachineView view; 
            //= new VendingMachineView();
    private UserIO io = new UserIOConsoleImpl();
    private VendingMachineDao dao = new VendingMachineDaoFileImpl();
    private VendingMachineServiceLayer service;
    
    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
    this.service = service;
    this.view = view;
}
    
    public void run() throws VendingMachineDaoException, NoItemInventoryException, InsufficientFundsException {
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing) {
           /* io.print("Vending Machine Main Menu: ");
            io.print("1. TWIX - Price: 100 ");
            io.print("2. COKE - Price: 50 ");
            io.print("3. WATER - Price: 30 ");
            io.print("4. SANDWICH - Price: 150 ");
            io.print("5. Exit ");

            menuSelection = io.readInt("Please select from the"
                    + " above choices.", 1, 5);*/
            menuSelection = getMenuSelection();
           /* switch (menuSelection) {
                case 1:
                    io.print("1");
                    break;
                case 2:
                    io.print("2");
                    break;
                case 3:
                    io.print("3");
                    break;
                case 4:
                    io.print("4");
                    break;
                case 5:
                    io.print("5");
                    break;
                case 6:
                    io.print("6");
                    break;    
                case 7:
                    keepGoing = false;
                    break;
                default:
                    io.print("UNKNOWN COMMAND");
            }*/
            if(menuSelection==0){
                keepGoing = false;
            } else {
                Product product = dao.getProduct(String.valueOf(menuSelection)); 
        
                if (product.getQuantity() <= 0 ){
                   io.print("You are purchasing an item that has zero inventory. Please choose other options.");
                }else{
                    BigDecimal money=BigDecimal.valueOf(io.readDouble("Please insert money(Calculated in cents): "));               
                    processOrder(money, product);
                }
            }
            
        }
        io.print("GOOD BYE");
    }
    
    private int getMenuSelection() throws VendingMachineDaoException {
        //return view.printMenuAndGetSelection(dao.getAllProducts());
        return view.printMenuAndGetSelection(dao.getAllProducts().stream().filter((p) -> p.getQuantity() > 0).collect(Collectors.toList()));
    }
    
    private void processOrder(BigDecimal money, Product product) throws VendingMachineDaoException, NoItemInventoryException, InsufficientFundsException {

        BigDecimal subtract = money.subtract(product.getPrice());        
        if(subtract.compareTo(BigDecimal.ZERO) < 0){
          io.print("The money you gave " +money+ " is insufficient.");
        }else{
            dao.dispenseProduct(product);
            Changes changes = new Changes(subtract.intValue());
            io.print("You have deposited $"+subtract+" in change.\nConvert your change to the coins are: ");
            io.print(moneyChange.QUARTERS+"(25Cents): "+changes.getQuarters());
            io.print(moneyChange.DIMES+"(10Cents): "+ changes.getDimes());
            io.print(moneyChange.NICKELS+"(5Cents): "+ changes.getNickles());
            io.print(moneyChange.PENNIES+"(1Cent): "+changes.getPennies()+"\n");
        }
    }
    
}
