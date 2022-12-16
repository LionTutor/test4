/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Product;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author lionwife
 */
public class VendingMachineView {
    private UserIO io; 
            //= new UserIOConsoleImpl();
     public VendingMachineView(UserIO io) {
    this.io = io;
}
    //1.Show dynamic menu.
    public int printMenuAndGetSelection(List<Product> products) {
        io.print("Lion's Vending Machine Main Menu: \n");
 
        //List<Product> products = Arrays.asList("TWIX", "COKE", "WATER", "SANDWICH", "ToyLion");
        int i = 0;
        io.print("0" +". Exit ");
    
        for (; i < products.size(); i++) {
            io.print(products.get(i).getId()+ ": " + products.get(i).getName()+"     Price: "+products.get(i).getPrice()+" Cents   Quantity: "+products.get(i).getQuantity());
        }

        //io.print((i+1) +". Exit ");        
        return io.readInt("Please select from the above choices.");
    }
    //2.Show a chosen products,let customer pay.
   
    //3.Update inventory left items units data.
    
}
