package com.cosmopolitan.order.bo;

import com.cosmopolitan.order.bo.exception.BOException;
import com.cosmopolitan.order.dto.Order;

public interface OrderBO {
	
	boolean placeOrder(Order order) throws BOException;
	boolean cancelOrder(int id) throws BOException;
	boolean deleteOrder(int id) throws BOException;
	

}
