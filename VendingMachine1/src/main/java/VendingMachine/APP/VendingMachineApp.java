/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachine.APP;

import VendingMachine.Controller.VendingMachineController;
import VendingMachine.Dao.VendingMachineDao;
import VendingMachine.Dao.VendingMachineDaoException;
import VendingMachine.Dao.VendingMachineDaoImpl;
import VendingMachine.ui.VendingMachineIO;
import VendingMachine.ui.VendingMachineIOConsoleImpl;
import VendingMachine.ui.VendingMachineView;

/**
 *
 * @author Administrator
 */
public class VendingMachineApp {
    public static void main(String[] args) throws VendingMachineDaoException {
        VendingMachineIO myIo = new VendingMachineIOConsoleImpl();
        VendingMachineView myView = new VendingMachineView(myIo);
        VendingMachineDao myDao = new VendingMachineDaoImpl();
        VendingMachineController controller = new VendingMachineController(myView, myDao);
        controller.run();
    }
}
