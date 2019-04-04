package shard.jdbc.shardingjdbc.service;


import groovy.util.logging.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shard.jdbc.shardingjdbc.entity.OrderNewInfoEntity;
import shard.jdbc.shardingjdbc.mapper.OrderNewMapper;

import java.util.List;


/**
 * @author snowalker
 * @version 1.0
 * @date 2019/3/23 10:53
 * @className OrderNewSerivce
 * @desc
 */
@Log4j2
@Service
public class OrderNewSerivce {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderNewSerivce.class);

    @Autowired
    OrderNewMapper orderNewMapper;

    public List<OrderNewInfoEntity> queryOrderInfoList(OrderNewInfoEntity orderInfo) {
        return orderNewMapper.queryOrderInfoList(orderInfo);
    }

    public OrderNewInfoEntity queryOrderInfoByOrderId(OrderNewInfoEntity orderInfo) {
        return orderNewMapper.queryOrderInfoByOrderId(orderInfo);
    }

    public int addOrder(OrderNewInfoEntity orderInfo) {
        LOGGER.info("订单入库开始，orderinfo={}", orderInfo.toString());
        return orderNewMapper.addOrder(orderInfo);
    }
}
