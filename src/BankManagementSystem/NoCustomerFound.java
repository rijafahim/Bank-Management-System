/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BankManagementSystem;

/**
 *
 * @author Rija
 */
public class NoCustomerFound extends RuntimeException
{
    public NoCustomerFound(String ex)
    {
        super(ex);
    }
}
