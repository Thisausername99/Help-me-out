package com.international.authoriziation.server.service.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.international.authoriziation.server.model.BucketName;
import com.international.authoriziation.server.model.entity.PostMedia;
import com.international.authoriziation.server.model.repository.MediaRepository;

import lombok.AllArgsConstructor;

import static org.apache.http.entity.ContentType.*;

import java.io.IOException;
import java.util.*;

@Service
@AllArgsConstructor
public class StorageServiceImpls implements StorageService{
	
	private static final Logger LOG = LoggerFactory.getLogger(StorageServiceImpls.class);

	@Autowired
	private final MediaRepository mediaRepository;
	
	@Autowired
	private final FileStore fileStore;
	
	@Override
	public PostMedia saveMedia(String title, String description, MultipartFile file) {
		if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }
        //Check if the file is an image
        if (!Arrays.asList(IMAGE_PNG.getMimeType(),
                IMAGE_BMP.getMimeType(),
                IMAGE_GIF.getMimeType(),
                IMAGE_JPEG.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("FIle uploaded is not an image");
        }
        //get file metadata
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        //Save Image in S3 and then save Todo in the database
        String path = String.format("%s/%s", BucketName.MEDIA_IMAGE.getBucketName(), UUID.randomUUID());
        String fileName = String.format("%s", file.getOriginalFilename());
        try {
            fileStore.upload(path, fileName, Optional.of(metadata), file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to upload file", e);
        }
        PostMedia media =  PostMedia.builder()
                .description(description)
                .title(title)
                .imagePath(path)
                .imageFileName(fileName)
                .build();
//        LOG.info("media id is: ", media.getId());
        return mediaRepository.save(media);
//        return repository.findByTitle(todo.getTitle());
	}

	@Override
	public byte[] downloadMedia(Long id) {
		PostMedia media = mediaRepository.findById(id).get();
        return fileStore.download(media.getImagePath(), media.getImageFileName());
	}

}
