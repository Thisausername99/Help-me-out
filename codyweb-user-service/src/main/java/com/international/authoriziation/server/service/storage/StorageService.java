package com.international.authoriziation.server.service.storage;

import org.springframework.web.multipart.MultipartFile;

import com.international.authoriziation.server.model.entity.PostMedia;

public interface StorageService {
	PostMedia saveMedia(String title, String description, MultipartFile file);

    byte[] downloadMedia(Long id);

//    List<Todo> getAllTodos();
}
