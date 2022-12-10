/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDaoException;
import java.math.BigDecimal;

/**
 *
 * @author lionwife
 */
public interface VendingMachineServiceLayer {
    void processOrder(BigDecimal money, int menuSelection)throws InsufficientFundsException,VendingMachineDaoException;
    int getMenuSelection(int menuSelection)throws NoItemInventoryException,VendingMachineDaoException;
}
