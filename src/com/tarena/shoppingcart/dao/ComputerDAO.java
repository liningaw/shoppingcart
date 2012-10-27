package com.tarena.shoppingcart.dao;

import java.util.List;

import com.tarena.shoppingcart.bean.Computer;

public interface ComputerDAO {
	public List<Computer> findAll() throws Exception;
	public Computer findById(long id) throws Exception;
}