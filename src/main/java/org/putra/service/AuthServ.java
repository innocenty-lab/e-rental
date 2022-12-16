package org.putra.service;

import org.putra.entity.Customer;
import org.putra.entity.UserCredential;

import java.util.List;

public interface AuthServ {
    Customer register(Customer customer) throws Exception;
    List<Customer> showAllCustomer() throws Exception;
    String login(UserCredential userCredential) throws Exception;
    Boolean logout(String token) throws Exception;
    Boolean validateToken(String token) throws Exception;
}
