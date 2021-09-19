/**
 * 
 */
package com.international.authoriziation.server.model.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.international.authoriziation.server.model.dto.LoginRequest;

import com.international.authoriziation.server.model.entity.UserEntity;

/**
 * @author Cody Hoang
 *
 */
@Component
public class UserMapper extends BaseMapper<UserEntity, LoginRequest>{

	@Override
	public UserEntity convertToEntity(LoginRequest dto, Object... args) {
		UserEntity userEntity = new UserEntity();
        if (dto != null) {
            BeanUtils.copyProperties(dto, userEntity);
        }
        return userEntity;
	}

	@Override
	public LoginRequest convertToDto(UserEntity entity, Object... args) {
		// TODO Auto-generated method stub
		return null;
	}
}
