/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChangeServiceLayer;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Administrator
 */
public class changeCalculate {
    public BigDecimal calculate(Change type, BigDecimal totalAmount, BigDecimal selectedItemPrice){
        BigDecimal quarter = new BigDecimal("0.25");
        BigDecimal dime = new BigDecimal("0.1");
        BigDecimal nickel = new BigDecimal("0.05");
        BigDecimal penny = new BigDecimal("0.01");
        
        switch(type){
            case QUARTER:
                return (totalAmount.subtract(selectedItemPrice)).divide(quarter, 0, RoundingMode.DOWN);
            case DIME:
                return (totalAmount.subtract(selectedItemPrice).subtract((totalAmount.subtract(selectedItemPrice)).divide(quarter, 0, RoundingMode.DOWN).multiply(quarter))).divide(dime, 0, RoundingMode.DOWN);
            case NICKEL:
                return (totalAmount.subtract(selectedItemPrice).subtract((totalAmount.subtract(selectedItemPrice)).divide(quarter, 0, RoundingMode.DOWN).multiply(quarter)).subtract((totalAmount.subtract(selectedItemPrice).subtract((totalAmount.subtract(selectedItemPrice)).divide(quarter, 0, RoundingMode.DOWN).multiply(quarter))).divide(dime, 0, RoundingMode.DOWN).multiply(dime))).divide(nickel, 0, RoundingMode.DOWN);
            case PENNY:
                return (totalAmount.subtract(selectedItemPrice).subtract((totalAmount.subtract(selectedItemPrice)).divide(quarter, 0, RoundingMode.DOWN).multiply(quarter)).subtract((totalAmount.subtract(selectedItemPrice).subtract((totalAmount.subtract(selectedItemPrice)).divide(quarter, 0, RoundingMode.DOWN).multiply(quarter))).divide(dime, 0, RoundingMode.DOWN).multiply(dime)).subtract((totalAmount.subtract(selectedItemPrice).subtract((totalAmount.subtract(selectedItemPrice)).divide(quarter, 0, RoundingMode.DOWN).multiply(quarter)).subtract((totalAmount.subtract(selectedItemPrice).subtract((totalAmount.subtract(selectedItemPrice)).divide(quarter, 0, RoundingMode.DOWN).multiply(quarter))).divide(dime, 0, RoundingMode.DOWN).multiply(dime))).divide(nickel, 0, RoundingMode.DOWN).multiply(nickel))).divide(penny, 0, RoundingMode.DOWN);
            default:
                throw new UnsupportedOperationException("Unknown Math Operator");
        }
    }
}
