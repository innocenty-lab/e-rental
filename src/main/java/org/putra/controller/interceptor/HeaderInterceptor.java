//package org.putra.controller.interceptor;
//
//import org.putra.constant.UrlMapping;
//import org.putra.exception.UnauthorizedException;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestClientException;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Component
//public class HeaderInterceptor implements HandlerInterceptor {
//    private final RestTemplate restTemplate;
//
//    public HeaderInterceptor(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//    @Value("${service.auth.url}")
//    private String authServiceUrl;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if (request.getRequestURI().contains(UrlMapping.AUTH_URL)) {
//            return true;
//        }
//
//        try {
//            String tokenHeader = request.getHeader("Authorization");
//            String[] tokenBearer = tokenHeader.split(" ");
//            ResponseEntity<String > msg = restTemplate.getForEntity(authServiceUrl + "?token=" + tokenBearer[1], String.class);
//
//            System.out.println(msg);
//            System.out.println(tokenBearer[1]);
//            return true;
//        } catch (RestClientException e) {
//            throw new UnauthorizedException();
//        }
//    }
//}
