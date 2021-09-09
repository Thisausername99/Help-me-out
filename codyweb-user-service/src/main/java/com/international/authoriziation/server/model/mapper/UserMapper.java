/**
 * 
 */
package com.international.authoriziation.server.model.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.international.authoriziation.server.model.dto.SignupRequest;

import com.international.authoriziation.server.model.entity.UserEntity;

/**
 * @author Cody Hoang
 *
 */
@Component
public class UserMapper extends BaseMapper<UserEntity, SignupRequest>{

	@Override
	public UserEntity convertToEntity(SignupRequest dto, Object... args) {
		UserEntity userEntity = new UserEntity();
        if (dto != null) {
            BeanUtils.copyProperties(dto, userEntity);
        }
        return userEntity;
	}

	@Override
	public SignupRequest convertToDto(UserEntity entity, Object... args) {
		SignupRequest user = new SignupRequest();
        if (entity != null) {
            BeanUtils.copyProperties(entity, user);
        }
        return user;
	}
	

}
