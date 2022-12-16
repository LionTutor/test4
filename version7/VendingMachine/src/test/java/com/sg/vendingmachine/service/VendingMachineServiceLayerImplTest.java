/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.sg.vendingmachine.dto.Product;
import java.io.FileWriter;
import java.io.IOException;
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
public class VendingMachineServiceLayerImplTest {
    private VendingMachineServiceLayer service;
    private VendingMachineDao dao;
    public VendingMachineServiceLayerImplTest() {
        //VendingMachineDao dao = new VendingMachineDaoStubImpl();
        //VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();
        this.dao = dao;
       // service = new VendingMachineServiceLayerImpl(dao, auditDao);
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws IOException {
        String testFile = "testInventory.txt";
        new FileWriter(testFile);
        dao = new VendingMachineDaoFileImpl(testFile);
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetAllProducts() throws Exception{
        //fail("The test case is a prototype.");
        //ARRANGE, ACT & ASSERT
        List<Product> allProducts = dao.getAllProducts(); 
        for (int i = 0; i < allProducts.size(); i++) {
        assertEquals( i, dao.getAllProducts().size(),"Should only have "+i+" product(s).");
        assertTrue( dao.getAllProducts().contains(allProducts.indexOf(i)),
                              "The "+i+" product(s) should be "+allProducts.indexOf(i));
        }
    }
    
    @Test
    public void testGetProduct() throws Exception{
       // fail("The test case is a prototype.");
       //ARRANGE, ACT & ASSERT
       List<Product> allProducts = dao.getAllProducts(); 
        for (int i = 0; i < allProducts.size(); i++) {
            String id = String.valueOf(i);
            Product shouldBeitem = dao.getProduct(id);
        assertNotNull(shouldBeitem, "Getting #"+i+" should be not null.");
        assertEquals( allProducts.indexOf(i), shouldBeitem,"Product stored under "+i+" should be "+allProducts.indexOf(i)+".");
        }
    }
    
    @Test
    public void testDispenseProduct() throws Exception{
        //fail("The test case is a prototype.");
        //ARRANGE, ACT & ASSERT
         List<Product> allProducts = dao.getAllProducts();  
        assertNotNull(allProducts, "The list of products must not null");
        for (int i = 0; i < allProducts.size(); i++) {
            String id = String.valueOf(i);
            Product product = new Product(id);
            int oldQuantriy = product.getQuantity();
            dao.dispenseProduct(product);
            assertEquals(i, allProducts.size(),"List of products should have "+i+" products.");         
            assertEquals(oldQuantriy-1, product.getQuantity(),"Checking product quantity.");
        }        
    }
}
