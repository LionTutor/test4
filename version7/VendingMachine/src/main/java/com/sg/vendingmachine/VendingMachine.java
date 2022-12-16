/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VendingMachineController;
import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineAuditDaoFileImpl;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoException;
import com.sg.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.NoItemInventoryException;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.service.VendingMachineServiceLayerImpl;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VendingMachineView;

/**
 *
 * @author lionwife
 */
//This is main App for my Vending Machine
public class VendingMachine {

    public static void main(String[] args) throws VendingMachineDaoException, NoItemInventoryException, InsufficientFundsException {
       UserIO myIo = new UserIOConsoleImpl();
    VendingMachineView myView = new VendingMachineView(myIo);
    VendingMachineDao myDao = new VendingMachineDaoFileImpl();
    VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoFileImpl();
    VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao,myAuditDao);
        VendingMachineController controller = new VendingMachineController(myService, myView);
        controller.run();
    }   
}
