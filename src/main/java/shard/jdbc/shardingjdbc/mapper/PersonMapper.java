package shard.jdbc.shardingjdbc.mapper;


import org.apache.ibatis.annotations.Param;
import shard.jdbc.shardingjdbc.entity.OrderNewInfoEntity;
import shard.jdbc.shardingjdbc.entity.PersonInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * @author snowalker
 * @version 1.0
 * @date 2019/3/23 10:52
 * @className OrderNewMapper
 * @desc 订单 new Mapper
 */
public interface PersonMapper {

    int addPerson(PersonInfoEntity personInfoEntity);

    Map<String,Object> getPerson(@Param("pid")int pid);
}
