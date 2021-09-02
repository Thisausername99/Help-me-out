package com.international.codyweb.model.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.international.codyweb.model.dto.PostDto;
import com.international.codyweb.model.entity.PostEntity;

@Component
public class PostMapper extends BaseMapper<PostEntity,PostDto>{

	@Override
	public PostEntity convertToEntity(PostDto dto, Object... args) {
		PostEntity post = new PostEntity();
		if (dto != null) {
			BeanUtils.copyProperties(dto, post);
		}
		return post;
	}

	@Override
	public PostDto convertToDto(PostEntity entity, Object... args) {
		PostDto post = new PostDto();
		if (entity != null) {
			BeanUtils.copyProperties(entity, post);
		}
		return post;
	}

}
