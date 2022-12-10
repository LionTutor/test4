/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.controller.VendingMachineController;
import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoException;
import com.sg.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.sg.vendingmachine.dto.Product;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author lionwife
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer{
    private VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;
    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
    this.dao = dao;
    this.auditDao = auditDao;
}
    @Override
    public void processOrder(BigDecimal money, int menuSelection) throws InsufficientFundsException, VendingMachineDaoException {
        try {
            Product product = dao.getProduct(String.valueOf(menuSelection));
            InsufficientFunds(money,product);
            // throw new InsufficientFundsException("You don't deposit enough money."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        } catch (NoItemInventoryException ex) {
            Logger.getLogger(VendingMachineServiceLayerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        auditDao.writeAuditEntry("Product #" + menuSelection + " Sold 1.");
    }

    @Override
    public int getMenuSelection(int menuSelection) throws NoItemInventoryException, VendingMachineDaoException {
        Product product = dao.getProduct(String.valueOf(menuSelection));
        NoItemInventory(product);
        throw new NoItemInventoryException("You are purchasing an item that has zero inventory.");// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        //auditDao.writeAuditEntry("Product #" + menuSelection + " Sold empty.");
    }
   
    private void InsufficientFunds(BigDecimal money, Product product) throws InsufficientFundsException{       
        BigDecimal subtract = money.subtract(product.getPrice());        
        if (subtract.compareTo(BigDecimal.ZERO) < 0 ){
                   throw new InsufficientFundsException("You don't deposit enough money.");
                }
            }
    
    private void NoItemInventory(Product product) throws NoItemInventoryException{       
                if (product.getQuantity() <= 0 ){
                   throw new NoItemInventoryException("You are purchasing an item that has zero inventory.");
                }
            }
}
