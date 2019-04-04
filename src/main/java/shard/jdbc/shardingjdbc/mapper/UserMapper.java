package shard.jdbc.shardingjdbc.mapper;


import org.apache.ibatis.annotations.Param;
import shard.jdbc.shardingjdbc.entity.UserInfoEntity;

import java.util.Map;

public interface UserMapper {

    int addUser(UserInfoEntity userInfo);

    Map<String,Object> getOrderByUserId(@Param("userId")String userId);

    UserInfoEntity getOrder(@Param("userOutId")String userOutId);

    UserInfoEntity getUserInfo(@Param("userId")String userId);

    UserInfoEntity getUserInfo2(@Param("userId")String userId,@Param("userOutId")String userOutId);
}
