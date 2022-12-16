/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Product;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author lionwife
 */
public class VendingMachineDaoFileImplTest {
    
    VendingMachineDao testDao;
    
    public VendingMachineDaoFileImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws Exception{
        String testFile = "testInventory.txt";
        new FileWriter(testFile);
        testDao = new VendingMachineDaoFileImpl(testFile);
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    @Test
    public void testGetAllProducts() throws Exception {
       // fail("The test case is a prototype.");
        //Arrange, Act For Stateful Code
       List<Product> allProducts = testDao.getAllProducts();       
       //Assert For Stateful Code
        assertNotNull(allProducts, "The list of products must not null");
        for (int i = 0; i < allProducts.size(); i++) {
            assertEquals(i, allProducts.size(),"List of products should have "+i+" products.");
            assertTrue(testDao.getAllProducts().contains(allProducts.indexOf(i)),
                "The list of produts should include 1:TWIX; 2:COKE; 3:WATER; 4:SANDWICH; 5:ToyLion; 6:LegoBrick."); 
        }       
    }

    @Test
    public void testGetProduct() throws Exception {
        //fail("The test case is a prototype.");
        //Arrange, Act, Assert For Stateful Code
        List<Product> allProducts = testDao.getAllProducts();       
        for (int i = 0; i < allProducts.size(); i++) {
            String id = String.valueOf(i);
            Product product = new Product(id);
            Product retrievedProduct = testDao.getProduct(id);         
            assertEquals(product.getId(),retrievedProduct.getId(),"Checking product id.");
            assertEquals(product.getName(),retrievedProduct.getName(),"Checking product name.");
            assertEquals(product.getPrice(),retrievedProduct.getPrice(),"Checking product price.");
            assertEquals(product.getQuantity(),retrievedProduct.getQuantity(),"Checking product quantity.");
        }              
    }
    
    @Test
    public void testDispenseProduct() throws Exception {
        //fail("The test case is a prototype.");
        List<Product> allProducts = testDao.getAllProducts();  
        assertNotNull(allProducts, "The list of products must not null");
        for (int i = 0; i < allProducts.size(); i++) {
            String id = String.valueOf(i);
            Product product = new Product(id);
            int oldQuantriy = product.getQuantity();
            testDao.dispenseProduct(product);
            assertEquals(i, allProducts.size(),"List of products should have "+i+" products.");         
            assertEquals(oldQuantriy-1, product.getQuantity(),"Checking product quantity.");
        }        
    }
    
}
