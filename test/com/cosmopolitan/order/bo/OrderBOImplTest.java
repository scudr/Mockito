package com.cosmopolitan.order.bo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.sql.SQLException;

import com.cosmopolitan.order.bo.exception.BOException;
import com.cosmopolitan.order.dao.OrderDAO;
import com.cosmopolitan.order.dto.Order;

public class OrderBOImplTest {
	@Mock
	OrderDAO dao;
	
	private OrderBOImpl bo;
	@Before
	public void setup(){
		//"this" means the class OrderBOImplTest
		MockitoAnnotations.initMocks(this);
		bo = new OrderBOImpl();
		bo.setDao(dao);
		
	}

	@Test
	public void placeOrder_Should_Create_And_Order() throws SQLException, BOException {
		Order order = new Order();
		when(dao.create(order)).thenReturn(new Integer(1));
		
		boolean result =bo.placeOrder(order);
		assertTrue(result);
		
		verify(dao).create(order);
	}
	
	@Test
	public void placeOrder_ShouldNotCreate_And_Order() throws SQLException, BOException {
				
		Order order = new Order();
		when(dao.create(order)).thenReturn(new Integer(0));
		
		boolean result =bo.placeOrder(order);
		assertFalse(result);
		
		verify(dao).create(order);
	}
	
	@Test(expected=BOException.class)
	public void placeOrder_Should_Throw_BOException() throws SQLException, BOException {
				
		Order order = new Order();
		when(dao.create(order)).thenThrow(SQLException.class);
		
		boolean result =bo.placeOrder(order);
		
	}

	@Test
	public void cancelOrder_Should_Cancel_The_Order() throws SQLException, BOException {
		
		Order order = new Order();
		when(dao.read(123)).thenReturn(order);
		when(dao.update(order)).thenReturn(1);
		boolean result =bo.cancelOrder(123);
		
		assertTrue(result);
		
		verify(dao).read(123);
		verify(dao).update(order);
		
	}
	
	@Test
	public void cancelOrder_Should_Not_Cancel_The_Order() throws SQLException, BOException {
		
		Order order = new Order();
		when(dao.read(123)).thenReturn(order);
		when(dao.update(order)).thenReturn(0);
		boolean result =bo.cancelOrder(123);
		
		assertFalse(result);
		
		verify(dao).read(123);
		verify(dao).update(order);
	}
		
	
	@Test(expected = BOException.class)
	public void cancelOrder_ShouldThrowABOExceptionOnRead() throws SQLException, BOException {
		when(dao.read(123)).thenThrow(SQLException.class);
				
		bo.cancelOrder(123);
	}
	
	@Test(expected=BOException.class)
	public void cancelOrder_Should_Throw_A_BOExceptionUpdate() throws SQLException, BOException {
		
		Order order = new Order();
		when(dao.read(123)).thenReturn(order);
		when(dao.update(order)).thenThrow(SQLException.class);
		bo.cancelOrder(123);
	
	}
	
}
