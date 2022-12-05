/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author lionwife
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao{
    private Map<String, Product> products = new HashMap<>();
    public final String PRODUCT_FILE="inventory.txt";
    public static final String DELIMITER = "::";
    
    @Override
    public List<Product> getAllProducts() throws VendingMachineDaoException {
         loadInventory();
    return new ArrayList<Product>(products.values());
    }

    @Override
    public void dispenseProduct( Product product) throws VendingMachineDaoException {
         product.setQuantity(product.getQuantity() - 1);
         products.put(product.getId(), product);
         writeInventory();
         
    }
    
     @Override
    public Product getProduct(String id) throws VendingMachineDaoException{
       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        loadInventory();
        return products.get(id);
    }
      private Product unmarshallProduct(String productAsText){
    // studentAsText is expecting a line read in from our file.
    // For example, it might look like this:
    // 1234::Ada::Lovelace::Java-September1842
    //
    // We then split that line on our DELIMITER - which we are using as ::
    // Leaving us with an array of Strings, stored in studentTokens.
    // Which should look like this:
    // ________________________________________________
    // |    |           |     |                       |
    // |ID  |ProductName|Price|left numbers of procuct|
    // |    |           |     |                       |
    // ------------------------------------------------
    //  [0]  [1]    [2]         [3]
    String[] productTokens = productAsText.split(DELIMITER);

    // Given the pattern above, the student Id is in index 0 of the array.
    String productId = productTokens[0];

    // Which we can then use to create a new Student object to satisfy
    // the requirements of the Student constructor.
    Product productFromFile = new Product(productId);

    // However, there are 3 remaining tokens that need to be set into the
    // new student object. Do this manually by using the appropriate setters.

    // Index 1 - FirstName
    productFromFile.setName(productTokens[1]);

    // Index 2 - LastName
    productFromFile.setPrice(new BigDecimal(productTokens[2]));

    // Index 3 - Cohort
    productFromFile.setQuantity(Integer.parseInt(productTokens[3]));

    // We have now created a student! Return it!
    return productFromFile;
}
    
    private void loadInventory() throws VendingMachineDaoException {
    Scanner scanner;

    try {
        // Create Scanner for reading the file
        scanner = new Scanner(
                new BufferedReader(
                        new FileReader(PRODUCT_FILE)));
    } catch (FileNotFoundException e) {
        throw new VendingMachineDaoException(
                "-_- Could not load product data into memory.", e);
    }
    // currentLine holds the most recent line read from the file
    String currentLine;
    // currentStudent holds the most recent student unmarshalled
    Product currentProduct;
    // Go through ROSTER_FILE line by line, decoding each line into a 
    // Student object by calling the unmarshallStudent method.
    // Process while we have more lines in the file
    while (scanner.hasNextLine()) {
        // get the next line in the file
        currentLine = scanner.nextLine();
        // unmarshall the line into a Student
        currentProduct = unmarshallProduct(currentLine);

        // We are going to use the student id as the map key for our student object.
        // Put currentStudent into the map using student id as the key
        products.put(currentProduct.getId(), currentProduct);
    }
    // close scanner
    scanner.close();
}
    
    private String marshallProduct(Product aProduct){
    // We need to turn a Student object into a line of text for our file.
    // For example, we need an in memory object to end up like this:
    // 4321::Charles::Babbage::Java-September1842

    // It's not a complicated process. Just get out each property,
    // and concatenate with our DELIMITER as a kind of spacer. 

    // Start with the student id, since that's supposed to be first.
    String productAsText = aProduct.getId()+ DELIMITER;

    // add the rest of the properties in the correct order:

    // FirstName
    productAsText += aProduct.getName()+ DELIMITER;

    // LastName
    productAsText += aProduct.getPrice()+ DELIMITER;

    // Cohort - don't forget to skip the DELIMITER here.
    productAsText += aProduct.getQuantity();

    // We have now turned a student to text! Return it!
    return productAsText;
}

   

    

 
private void writeInventory() throws VendingMachineDaoException {
    // NOTE FOR APPRENTICES: We are not handling the IOException - but
    // we are translating it to an application specific exception and 
    // then simple throwing it (i.e. 'reporting' it) to the code that
    // called us.  It is the responsibility of the calling code to 
    // handle any errors that occur.
    PrintWriter out;

    try {
        out = new PrintWriter(new FileWriter(PRODUCT_FILE));
    } catch (IOException e) {
        throw new VendingMachineDaoException(
                "Could not save Inventory data.", e);
    }

    // Write out the Student objects to the roster file.
    // NOTE TO THE APPRENTICES: We could just grab the student map,
    // get the Collection of Students and iterate over them but we've
    // already created a method that gets a List of Students so
    // we'll reuse it.
    String productAsText;
    List<Product> productList = new ArrayList<Product>(products.values());
    for (Product currentProduct : productList) {
        // turn a Student into a String
        productAsText = marshallProduct(currentProduct);
        // write the Student object to the file
        out.println(productAsText);
        // force PrintWriter to write line to the file
        out.flush();
    }
    // Clean up
    out.close();
} 
    
}
