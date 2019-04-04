package shard.jdbc.shardingjdbc.mapper;


import org.apache.ibatis.annotations.Param;
import shard.jdbc.shardingjdbc.entity.OrderNewInfoEntity;

import java.util.List;

/**
 * @author snowalker
 * @version 1.0
 * @date 2019/3/23 10:52
 * @className OrderNewMapper
 * @desc 订单 new Mapper
 */
public interface OrderNewMapper {

    List<OrderNewInfoEntity> queryOrderInfoList(OrderNewInfoEntity orderInfo);

    OrderNewInfoEntity queryOrderInfoByOrderId(OrderNewInfoEntity orderInfo);

    List<OrderNewInfoEntity> queryOrderId(@Param("orderId") String orderId);

    int addOrder(OrderNewInfoEntity orderInfo);

    List<OrderNewInfoEntity> addAllOrder();
}
