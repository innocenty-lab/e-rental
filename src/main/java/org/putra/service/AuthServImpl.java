package org.putra.service;

import org.putra.entity.Customer;
import org.putra.entity.UserCredential;
import org.putra.exception.EntityExistException;
import org.putra.exception.NotFoundException;
import org.putra.exception.UnauthorizedException;
import org.putra.repository.ICustomerRepo;
import org.putra.repository.IUserCredentialRepo;
import org.putra.util.JwtUtil;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServImpl implements AuthServ {
    private final ICustomerRepo iCustomerRepo;
    private final IUserCredentialRepo iUserCredentialRepo;
    private final JwtUtil jwtUtil;

    public AuthServImpl(ICustomerRepo iCustomerRepo, IUserCredentialRepo iUserCredentialRepo, JwtUtil jwtUtil) {
        this.iCustomerRepo = iCustomerRepo;
        this.iUserCredentialRepo = iUserCredentialRepo;
        this.jwtUtil = jwtUtil;
    }

    private List<String> tokenStorage = new ArrayList<>();

    @Transactional
    @Override
    public Customer register(Customer customer) throws Exception {
        try {
            UserCredential userCredential = new UserCredential();
            userCredential.setEmail(customer.getUserCredential().getEmail());
            userCredential.setPassword(customer.getUserCredential().getPassword());

            UserCredential existingUserCredential = iUserCredentialRepo.getUserCredential(userCredential.getEmail(), userCredential.getPassword());

            if (existingUserCredential != null) {
                throw new EntityExistException();
            }

            customer.setUserCredential(userCredential);

            return iCustomerRepo.save(customer);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }

    @Override
    public String login(UserCredential userCredential) throws Exception {
        UserCredential existingUserCredential = iUserCredentialRepo.getUserCredential(userCredential.getEmail(), userCredential.getPassword());
        String token = null;

        if (existingUserCredential != null) {
            token = jwtUtil.generateToken("store");
            tokenStorage.add(token);
            return token;
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public Boolean logout(String token) throws Exception {
        try {
            return tokenStorage.remove(token);
        } catch (Exception e) {
            throw new UnauthorizedException("Unsuccess Logut");
        }
    }

    @Override
    public Boolean validateToken(String token) throws Exception {
        String existingToken = null;
        for (String sToken : tokenStorage) {
            if (sToken.equals(token)) {
                existingToken = sToken;
                break;
            }
        }

        if (existingToken == null) {
            throw new UnauthorizedException("Token Is Not Exist");
        }

        if (jwtUtil.validateToken(existingToken)) {
            return true;
        } else {
            throw new UnauthorizedException("Token Invalid");
        }
    }

    @Override
    public List<Customer> showAllCustomer() throws Exception {
        List<Customer> customerList = iCustomerRepo.findAllCustomer();

        if (customerList.isEmpty()) {
            throw new NotFoundException();
        }

        return customerList;
    }
}
