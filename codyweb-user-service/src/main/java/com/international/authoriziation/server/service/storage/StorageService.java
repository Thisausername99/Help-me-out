package com.international.codyweb.service;

import org.springframework.web.multipart.MultipartFile;

import com.international.codyweb.model.entity.PostMedia;

public interface StorageService {
	PostMedia saveMedia(String title, String description, MultipartFile file);

    byte[] downloadMedia(Long id);

//    List<Todo> getAllTodos();
}
