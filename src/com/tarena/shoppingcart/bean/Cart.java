package com.tarena.shoppingcart.bean;

import java.util.ArrayList;
import java.util.List;

import com.tarena.shoppingcart.dao.ComputerDAO;
import com.tarena.shoppingcart.dao.impl.ComputerDAOImpl;

public class Cart {
	private List<CartItem> items = new ArrayList<CartItem>();

	public boolean add(CartItem cartItem) {
		for (int i = 0; i < items.size(); i++) {
			CartItem item = items.get(i);
			if (item.getComputer().getId() == cartItem.getComputer().getId()) {
				return false;
			}
		}

		items.add(cartItem);
		return true;
	}

	public List<CartItem> list() {
		return items;
	}

	public double cost() {
		double total = 0;

		for (int i = 0; i < items.size(); i++) {
			CartItem item = items.get(i);
			total += item.getQuantity() * item.getComputer().getPrice();
		}

		return total;
	}

	public void clear() {
		items.clear();
	}

	public void delete(long id) {
		for (int i = 0; i < items.size(); i++) {
			CartItem item = items.get(i);
			if (item.getComputer().getId() == id) {
				items.remove(item);
				return;
			}
		}
	}

	public void modify(long id, int quantity) {
		for (int i = 0; i < items.size(); i++) {
			CartItem item = items.get(i);
			if (item.getComputer().getId() == id) {
				item.setQuantity(quantity);
				return;
			}
		}
	}

	/**
	 * @return 备份购物车中的数据，将购物车的产品id和产品数量构造出特定格式的字符串用于保存到cookie当中
	 */
	public String store() {
		StringBuffer stringBuffer = new StringBuffer();

		if (items.size() == 0) {
			stringBuffer.append("0");
		} else {
			for (int i = 0; i < items.size(); i++) {
				CartItem item = items.get(i);
				stringBuffer.append(item.getComputer().getId() + ","
						+ item.getQuantity() + ";");
			}
		}

		if (stringBuffer.length() > 1) {
			stringBuffer.deleteCharAt(stringBuffer.length() - 1);
		}

		return stringBuffer.toString();
	}

	/**
	 * @param content
	 *            备份的逆操作，即根据字符串内容重新构造新的购物车
	 * @throws Exception
	 */
	public void load(String content) throws Exception {
		items.clear();

		if (content == null || content.equals("0")) {
			return;
		}

		String[] strs = content.split(";");
		for (int i = 0; i < strs.length; i++) {
			String str = strs[i];
			String[] str1 = str.split(",");
			long id = Long.parseLong(str1[0]);
			int quantity = Integer.parseInt(str1[1]);

			ComputerDAO dao = new ComputerDAOImpl();
			Computer computer = dao.findById(id);

			CartItem item = new CartItem();
			item.setComputer(computer);
			item.setQuantity(quantity);
			items.add(item);
		}
	}
}