/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingmashine.contrllor;

import java.math.BigDecimal;
import vendingmachine.dao.VendingMachineDao;
import vendingmachine.dao.VendingMachineDaoException;
import vendingmachine.dao.VendingMachineDaoImpl;
import vendingmachine.ui.UserIO;
import vendingmachine.ui.UserIOConsoleImpl;
import vendingmachine.ui.VendingMachineView;

/**
 *
 * @author 15874
 */
public class VendingMachineController {
    
    
    private VendingMachineView view;
    private UserIO io = new UserIOConsoleImpl();
    private VendingMachineDao dao;
    
    
    public void run(){
        boolean keeping = true;
        int menuSelection = 0;
        try{
            while (keeping){
            
                menuSelection = getMenuSelection();
            
                switch (menuSelection){
                    case 1:
                        colaSelected();
                        break;
                    case 2:
                        spriteSelected();
                        break;
                    case 3:
                        waterSelected();
                        break;
                    case 4:
                        exitSelected();
                        keeping = false;
                        break;
                    default:
                        unknownCommand();
                    }
                }
            exitMessage();
        } catch (VendingMachineDaoException e){
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    private int getMenuSelection(){
        return view.showMoneyAndGetSelection();
    }
    
    private void viewMenu(){
        view.showMenu();
    }
    
    private void colaSelected() throws VendingMachineDaoException{
        view.colaChange();
    }
    
    private void spriteSelected() throws VendingMachineDaoException{
        view.spriteChange();
    }
    
    private void waterSelected() throws VendingMachineDaoException{
        view.waterChange();
    }
    
    private void exitSelected() throws VendingMachineDaoException{
        view.returnMoney();
    }
    
    public void unknownCommand(){
        view.displayUnknownCommandBanner();
    }
    
    public void exitMessage(){
        view.displayExitBanner();
    }
    
    public VendingMachineController(VendingMachineDao dao, VendingMachineView view){
        this.dao = dao;
        this.view = view;
    }
}
