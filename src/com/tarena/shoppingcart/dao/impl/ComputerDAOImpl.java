package com.tarena.shoppingcart.dao.impl;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.tarena.shoppingcart.bean.Computer;
import com.tarena.shoppingcart.dao.ComputerDAO;

public class ComputerDAOImpl implements ComputerDAO {
	private static SqlMapClient sqlMapClient = null;

	static {
		try {
			Reader reader = Resources.getResourceAsReader("com/tarena/shoppingcart/ibatis/config/SqlMapConfig.xml");
			sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Computer> findAll() throws Exception {
		return sqlMapClient.queryForList("findAll");
	}

	@Override
	public Computer findById(long id) throws Exception {
		return (Computer) sqlMapClient.queryForObject("findById", id);
	}
}