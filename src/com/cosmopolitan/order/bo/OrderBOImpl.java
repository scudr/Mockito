package com.cosmopolitan.order.bo;

import java.sql.SQLException;

import com.cosmopolitan.order.bo.exception.BOException;
import com.cosmopolitan.order.dao.OrderDAO;
import com.cosmopolitan.order.dto.Order;

public class OrderBOImpl implements OrderBO {
	
	private OrderDAO dao;

	@Override
	public boolean placeOrder(Order order) throws BOException {
		
		try {
			int result =dao.create(order);
			if(result==0)
			{
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new BOException(e);
		}
		return true;
	}

	@Override
	public boolean cancelOrder(int id) throws BOException{

		try {
			Order order = dao.read(id);
			order.setStatus("canceled");
			int result =dao.update(order);
			if(result ==0){
				
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new BOException(e);
		}
		
		
		return true;
	}

	@Override
	public boolean deleteOrder(int id)throws BOException {

	 try {
		int result =dao.delete(id);
		if(result==0){
			return false;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return false;
	}

	public void setDao(OrderDAO dao){
		this.dao = dao;
	}
	
	public OrderDAO getDao(){
		return dao;
		
	}
}
