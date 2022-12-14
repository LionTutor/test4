/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachine.Controller;

import VendingMachine.Dao.VendingMachineDao;
import VendingMachine.Dao.VendingMachineDaoException;
import VendingMachine.Dto.Item;
import VendingMachine.ui.VendingMachineIO;
import VendingMachine.ui.VendingMachineIOConsoleImpl;
import VendingMachine.ui.VendingMachineView;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Administrator
 */
public class VendingMachineController {
    private VendingMachineIO io = new VendingMachineIOConsoleImpl();
    private VendingMachineView view;
    private VendingMachineDao dao;

    public VendingMachineController(VendingMachineView view, VendingMachineDao dao) throws VendingMachineDaoException{
        this.view = view;
        this.dao = dao;
    }
    
    
    public void run() throws VendingMachineDaoException {
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing) {
            menuSelection = getSelection();
            Item item = dao.getItem(Integer.toString(menuSelection));
            getChange(item);
            }
        displayGoodbye();
    }
    
    public int getSelection() throws VendingMachineDaoException{
        return view.showMoneyAndGetSelection(dao.getAllItems().stream().filter((p) -> Integer.parseInt(p.getItemQuantity()) > 0).collect(Collectors.toList()));
    }
    
    public void displayGoodbye(){
        view.displayExitBanner();
    }
    
    public void displayUnknownCommend(){
        view.displayUnknownCommandBanner();
    }
    
    public void selectedExit(){
        view.returnMoney();
    }
    
    public void getChange(Item item) throws VendingMachineDaoException{
        view.itemChange(item);
    }
    
}
