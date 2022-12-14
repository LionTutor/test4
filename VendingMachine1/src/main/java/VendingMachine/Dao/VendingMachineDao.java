/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VendingMachine.Dao;

import VendingMachine.Dto.Item;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface VendingMachineDao {
    List<Item> getAllItems() throws VendingMachineDaoException;
    
    Item getItem(String itemId) throws VendingMachineDaoException;
    
    void getItemInventory(Item item) throws VendingMachineDaoException;
}
