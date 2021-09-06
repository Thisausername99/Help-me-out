package com.international.codyweb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BucketName {
	MEDIA_IMAGE("codyweb-storage");
	private final String bucketName;
}
