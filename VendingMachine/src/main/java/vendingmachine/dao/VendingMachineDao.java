/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingmachine.dao;


import java.util.List;
import vendingmachine.dto.Item;

/**
 *
 * @author 15874
 */
public interface VendingMachineDao {
    
    List<Item> getAllItems() throws VendingMachineDaoException;
    
    Item getItem(String itemId) throws VendingMachineDaoException;
    
    
}
