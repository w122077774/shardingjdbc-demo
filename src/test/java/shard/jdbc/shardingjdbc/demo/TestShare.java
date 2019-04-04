package shard.jdbc.shardingjdbc.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shard.jdbc.shardingjdbc.constant.DbAndTableEnum;
import shard.jdbc.shardingjdbc.entity.OrderInfo;
import shard.jdbc.shardingjdbc.entity.OrderNewInfoEntity;
import shard.jdbc.shardingjdbc.entity.PersonInfoEntity;
import shard.jdbc.shardingjdbc.entity.UserInfoEntity;
import shard.jdbc.shardingjdbc.mapper.OrderMapper;
import shard.jdbc.shardingjdbc.mapper.OrderNewMapper;
import shard.jdbc.shardingjdbc.mapper.PersonMapper;
import shard.jdbc.shardingjdbc.mapper.UserMapper;
import shard.jdbc.shardingjdbc.sequence.KeyGenerator;
import shard.jdbc.shardingjdbc.service.OrderNewSerivce;
import shard.jdbc.shardingjdbc.service.OrderService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: wangjunjie 2019/3/30 14:31
 * @Description:
 * @Version: 1.0.0
 * @Modified By: xxx 2019/3/30 14:31
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestShare {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestShare.class);

    @Resource(name = "orderService")
    OrderService orderService;
    @Autowired
    KeyGenerator keyGenerator;
    @Autowired
    OrderNewSerivce orderNewSerivce;
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderNewMapper orderNewMapper;

    @Test
    public void testAddPerson() {
        PersonInfoEntity personInfoEntity = new PersonInfoEntity();
        personInfoEntity.setOrderId("OD000000011903301549362453500002");
        personInfoEntity.setUserId("UD020000011903301549351073500002");
        personInfoEntity.setWife("aaaaaa");
        personMapper.addPerson(personInfoEntity);
    }

    @Test
    public void testGetPerson() {
        Map<String, Object> person = personMapper.getPerson(1);
        System.out.println(person);
    }


    /**
     * 测试分布式主键生成
     */
    @Test
    public void testGenerateId() {
        // 支付宝或者微信uid
        String outId = "1232132131241241243123";
        LOGGER.info("获取id开始");
        String innerUserId = keyGenerator.generateKey(DbAndTableEnum.T_USER, outId);
        LOGGER.info("外部id={},innerUserId={}", outId, innerUserId);
        String orderId = keyGenerator.generateKey(DbAndTableEnum.T_NEW_ORDER, innerUserId);
        LOGGER.info("外部id={},innerUserId={},orderId={}", outId, innerUserId, orderId);
    }


    /**
     * 测试新的订单入库
     */
    @Test
    public void testNewOrderInsert() {
        // 支付宝或者微信uid
        for (int i = 0; i < 1; i++) {
            String outId = "aaaa232133b" + i;
            LOGGER.info("获取id开始");
            String innerUserId = keyGenerator.generateKey(DbAndTableEnum.T_USER, outId);
            LOGGER.info("外部id={},内部用户={}", outId, innerUserId);
            String orderId = keyGenerator.generateKey(DbAndTableEnum.T_NEW_ORDER, innerUserId);
            LOGGER.info("外部id={},内部用户={},订单={}", outId, innerUserId, orderId);
            OrderNewInfoEntity orderInfo = new OrderNewInfoEntity();
            orderInfo.setUserName("snowalker");
            orderInfo.setUserId(innerUserId);
            orderInfo.setOrderId(orderId);
            orderNewSerivce.addOrder(orderInfo);
            UserInfoEntity userInfoEntity = new UserInfoEntity();
            userInfoEntity.setUserId(innerUserId);
            userInfoEntity.setUserOutId(outId);
            userInfoEntity.setUserName("韩雪");
            userMapper.addUser(userInfoEntity);
        }

    }

    /**
     * 测试订单明细查询
     */
    @Test
    public void testQueryNewOrderById() {
        String orderId = "OD000001011903301536019703500003";
        String userId = "UD020000011903301536012553500001";
        OrderNewInfoEntity orderInfo = new OrderNewInfoEntity();
        orderInfo.setOrderId(orderId);
        orderInfo.setUserId(userId);
        System.out.println(orderNewSerivce.queryOrderInfoByOrderId(orderInfo));
    }

    /**
     * 测试订单列表查询
     */
    @Test
    public void testQueryNewOrderList() {
        String userId = "OD0000001011903311627220083500008";
        OrderNewInfoEntity orderInfo = new OrderNewInfoEntity();
        orderInfo.setUserId(userId);
        List<OrderNewInfoEntity> list = new ArrayList<>();
        list = orderNewSerivce.queryOrderInfoList(orderInfo);
        System.out.println(list);
    }

    /**
     * 测试订单列表查询
     */
    @Test
    public void testQueryNewOrderList2() {
        String orderId = "OD010001011903311617335753500003";
        List<OrderNewInfoEntity> list = orderNewMapper.queryOrderId(orderId);
        System.out.println(list);
    }

    /**
     * 测试订单列表查询
     */
    @Test
    public void testQueryUserOrder() {
        Map<String, Object> order = userMapper.getOrderByUserId("UD020000011903311659064483500009");
        String userId = (String) order.get("user_id");
        String orderId = (String) order.get("order_id");
        System.out.println("userId: "+userId+"   orderId: "+orderId);
    }

    /**
     * 件查询 如果有查询条件 不是分库分表策略，那么会查询所有数据库和所有表
     */
    @Test
    public void testQueryUserOrder3() {
        UserInfoEntity user = userMapper.getOrder("aaaa232133b0");
        System.out.println(user);
    }

    @Test
    public void testQueryUserOrder4() {
        UserInfoEntity user = userMapper.getUserInfo("UD020000011903311659064483500009");
        System.out.println(user);
    }

    /**
     * 多条件查询 如果有查询条件 是分库分表策略，那么就会只会查询 特定数据库的特定表
     */
    @Test
    public void testQueryUserOrder5() {
        UserInfoEntity user = userMapper.getUserInfo2("UD020000011903311659064483500009","aaaa232133b0");
        System.out.println(user);
    }

    /**
     *
     */
    @Test
    public void testQueryNew() {
        List<OrderNewInfoEntity> orderNewInfoEntities = orderNewMapper.addAllOrder();
        for (OrderNewInfoEntity orderNewInfoEntity : orderNewInfoEntities) {
            System.out.println(orderNewInfoEntity);
        }
    }
}
