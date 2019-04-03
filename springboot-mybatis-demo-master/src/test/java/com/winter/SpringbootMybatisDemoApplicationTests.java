package com.winter;

import com.winter.mapper.OrdersMapper;
import com.winter.model.Orders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMybatisDemoApplicationTests {

	@Autowired
	private OrdersMapper ordersMapper;

	@Test
	public void contextLoads() {
		Orders orders = ordersMapper.selectById(1532079296609471L);
		System.out.print(orders);
	}

}
