package com.community.my.user;

import org.apache.ibatis.annotations.Mapper;

import com.community.my.user.model.UserDMI;
import com.community.my.user.model.UserParam;

@Mapper
public interface UserMapper {
	UserDMI overlapChk(UserParam param);
	int insJoin(UserParam param);
	int updProfile(UserParam param);

}
