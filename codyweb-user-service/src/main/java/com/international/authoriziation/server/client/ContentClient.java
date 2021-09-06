//package com.international.authoriziation.server.client;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestPart;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.http.ResponseEntity;
//
//import com.international.authoriziation.server.model.dto.PostDto;
//
//@FeignClient(name = "codyweb-content-service")
//public interface ContentClient {
//	   @PostMapping(path = "/api/v1/post/create")
//	   ResponseEntity<?> uploadPost(@RequestPart("post") PostDto post, @RequestPart("file") MultipartFile file, Long userId);
//}

