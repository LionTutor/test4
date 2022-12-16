/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoException;
import com.sg.vendingmachine.dto.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lionwife
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao{
    private Map<String, Product> products = new HashMap<>();
    private VendingMachineDao testDao;
    public Product onlyProduct;
    public VendingMachineDaoStubImpl(Product testProduct){
         this.onlyProduct = testProduct;
     }

    @Override
    public List<Product> getAllProducts() throws VendingMachineDaoException {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
       // List<Product> productList = new ArrayList<>();
       // return productList;
       return new ArrayList(products.values());
    }

    @Override
    public Product getProduct(String id) throws VendingMachineDaoException, NoItemInventoryException {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    /*if (id.equals(onlyProduct.getId())) {
            return onlyProduct;
        } else {
            return null;
        }  */
    return products.get(id);
    }

    @Override
    public Product dispenseProduct(Product product) throws VendingMachineDaoException, InsufficientFundsException {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        int oldQuantriy = onlyProduct.getQuantity();
            testDao.dispenseProduct(onlyProduct);
        int newQuantriy = onlyProduct.getQuantity();    
        if (oldQuantriy == newQuantriy+1) {
           return onlyProduct;
        } else {
            return null;
        }       
    }
    
}
