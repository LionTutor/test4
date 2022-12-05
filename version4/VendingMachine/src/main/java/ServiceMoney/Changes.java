/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServiceMoney;

import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import java.math.BigDecimal;

/**
 *
 * @author lionwife
 */
public class Changes {
    private int nickles;
    private int dimes;
    private int quarters;
    private int pennies;

    public Changes(int subtract) {
        this.quarters = subtract/25;
        this.dimes = (subtract % 25) / 10;
        this.nickles = ((subtract % 25) % 10) / 5;
        this.pennies = (((subtract % 25) % 10)%5) / 1;
    }
    
    public int getNickles() {
        return nickles;
    }

    
    public int getDimes() {
        return dimes;
    }

    
    public int getQuarters() {
        return quarters;
    }

    

    public int getPennies() {
        return pennies;
    }  
}
