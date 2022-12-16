package org.putra.controller;

import org.modelmapper.ModelMapper;
import org.putra.constant.UrlMapping;
import org.putra.entity.Customer;
import org.putra.entity.UserCredential;
import org.putra.entity.request.UserCredentialReq;
import org.putra.entity.response.SuccessResponse;
import org.putra.service.AuthServ;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(UrlMapping.AUTH_URL)
@Validated
public class AuthController {
    private final AuthServ iCustomerServ;
    private final ModelMapper modelMapper;

    public AuthController(AuthServ iCustomerServ, ModelMapper modelMapper) {
        this.iCustomerServ = iCustomerServ;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody Customer customer) throws Exception {
        Customer newCustomer = iCustomerServ.register(customer);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success register User", newCustomer));
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody UserCredentialReq userCredentialReq) throws Exception {
        UserCredential mappedUsercredential = modelMapper.map(userCredentialReq, UserCredential.class);
        String token = iCustomerServ.login(mappedUsercredential);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success logout user", token));
    }

    @GetMapping("/logout")
    public ResponseEntity logout(@RequestParam String token) throws Exception {
        iCustomerServ.logout(token);
        return ResponseEntity.status(HttpStatus.OK).body("Success Logout User");
    }

//    @GetMapping("/token-validation")
//    public ResponseEntity doTokenValidation(@RequestParam String token) throws Exception {
//        if (iCustomerServ.validateToken(token)) {
//            return ResponseEntity.status(HttpStatus.OK).body("Token Is Valid");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token Is Invalid");
//        }
//    }

    @GetMapping
    public ResponseEntity getAllCustomer() throws Exception {
        List<Customer> customerList = iCustomerServ.showAllCustomer();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success show all user", customerList));
    }
}
