package shard.jdbc.shardingjdbc;

import groovy.util.logging.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import shard.jdbc.shardingjdbc.entity.OrderInfo;

import shard.jdbc.shardingjdbc.mapper.OrderMapper;
import shard.jdbc.shardingjdbc.mapper.UserMapper;
import shard.jdbc.shardingjdbc.service.OrderService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingjdbcApplicationTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShardingjdbcApplication.class);


	@Resource(name = "orderService")
	OrderService orderService;
	@Resource(name = "orderMapper")
	OrderMapper orderMapper;
	@Resource(name = "userMapper")
	UserMapper userMapper;

	@Test
	public void testInsertOrderInfo() {
		for (int i = 0; i < 1000; i++) {
			long userId = 4*i;
//            long orderId = KeyGenerator.getKey();
			long orderId = 4*i + 1;
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setUserName("snowalker");
			orderInfo.setUserId(userId);
			orderInfo.setOrderId(orderId);
			int result = orderService.addOrder(orderInfo);
			if (1 == result) {
				LOGGER.info("入库成功,orderInfo={}", orderInfo);
			} else {
				LOGGER.info("入库失败,orderInfo={}", orderInfo);
			}
		}
	}

	@Test
	public void testInsertOrderInfo2() {

			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setUserName("snowalker");
			orderInfo.setUserId(3L);
			orderInfo.setOrderId(2L);
			int result = orderService.addOrder(orderInfo);
			if (1 == result) {
				LOGGER.info("入库成功,orderInfo={}", orderInfo);
			} else {
				LOGGER.info("入库失败,orderInfo={}", orderInfo);
			}

	}

	/**
	 * 默认规则跨片归并
	 */
	@Test
	public void testQueryList() {
		List<OrderInfo> list = new ArrayList<>();
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setOrderId(2l);
		orderInfo.setUserId(2l);
		list = orderService.queryOrderInfoList(orderInfo);
		LOGGER.info(list.toString());
	}

	/**
	 * 默认规则跨片归并
	 */
	@Test
	public void testQueryList2() {
		List<OrderInfo> list = new ArrayList<>();
		list = orderMapper.queryOrder2();
		for (OrderInfo orderInfo : list) {
			System.out.println(orderInfo);
		}
		LOGGER.info(list.toString());
	}


	@Test
	public void testQueryById() {
		OrderInfo queryParam = new OrderInfo();
		queryParam.setUserId(8l);
		queryParam.setOrderId(8l);
		OrderInfo queryResult = orderService.queryOrderInfoByOrderId(queryParam);
		if (queryResult != null) {
			LOGGER.info("查询结果:orderInfo={}", queryResult);
		} else {
			LOGGER.info("查无此记录");
		}
	}

	/*@Test
	public void testUser() {
		UserInfo userInfo = new UserInfo();
		userInfo.setAge(23);
		userInfo.setName("陈晓红");
		userInfo.setOrderId(100L);
		userInfo.setUserId(100L);
		userMapper.addUser(userInfo);
		UserInfo user = userMapper.getUser(3);
		System.out.println(user.toString());
	}*/

}
