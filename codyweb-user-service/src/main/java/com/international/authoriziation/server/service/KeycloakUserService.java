///**
// * 
// */
//package com.international.authoriziation.server.service;
//
//import java.util.*;
//
//import javax.ws.rs.core.Response;
//
//import org.keycloak.admin.client.resource.UserResource;
//import org.keycloak.representations.idm.UserRepresentation;
//import org.springframework.stereotype.Service;
//
//import com.international.authoriziation.server.config.KeycloakManager;
//
//import lombok.RequiredArgsConstructor;
//
///**
// * @author Cody Hoang
// *
// */
//@Service
//@RequiredArgsConstructor
//public class KeycloakUserService {
//
//    private final KeycloakManager keyCloakManager;
//
//    public Integer createUser(UserRepresentation userRepresentation) {
//        Response response = keyCloakManager.getKeyCloakInstanceWithRealm().users().create(userRepresentation);
//        return response.getStatus();
//    }
//
//    public void updateUser(UserRepresentation userRepresentation) {
//        keyCloakManager.getKeyCloakInstanceWithRealm().users().get(userRepresentation.getId()).update(userRepresentation);
//    }
//
//
//    public List<UserRepresentation> readUserByEmail(String email) {
//        return keyCloakManager.getKeyCloakInstanceWithRealm().users().search(email);
//    }
//
//
//    public UserRepresentation readUser(String authId) {
//        try {
//            UserResource userResource = keyCloakManager.getKeyCloakInstanceWithRealm().users().get(authId);
//            return userResource.toRepresentation();
//        } catch (Exception e) {
//            throw new RuntimeException("User not found under given ID");
//        }
//    }
//}