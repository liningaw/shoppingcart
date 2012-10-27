package com.tarena.shoppingcart.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tarena.shoppingcart.bean.Cart;
import com.tarena.shoppingcart.bean.CartItem;
import com.tarena.shoppingcart.bean.Computer;
import com.tarena.shoppingcart.dao.ComputerDAO;
import com.tarena.shoppingcart.dao.impl.ComputerDAOImpl;
import com.tarena.shoppingcart.util.CookieUtils;

@SuppressWarnings("serial")
public class ShoppingCartServlet extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));

		if (path.equals("list")) {
			ComputerDAO dao = new ComputerDAOImpl();
			try {
				List<Computer> computers = dao.findAll();
				request.setAttribute("computers", computers);
				request.getRequestDispatcher("computer_list.jsp").forward(
						request, response);
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
		} else if (path.equals("buy")) {
			long id = Long.parseLong(request.getParameter("id"));
			ComputerDAO dao = new ComputerDAOImpl();
			try {
				Computer computer = dao.findById(id);

				CartItem item = new CartItem();
				item.setComputer(computer);
				item.setQuantity(1);

				HttpSession session = request.getSession();
				Cart cart = (Cart) session.getAttribute("cart");
				if (cart == null) {
					cart = new Cart();
					//有可能客户端之前保存过购买商品信息， 需要在浏览器重新开启的时候加载原来的信息
					cart.load(CookieUtils.findCookie("cartInfo", request));
					session.setAttribute("cart", cart);
				}

				boolean flag = cart.add(item);
				if (!flag) {
					request.setAttribute("buy_error_" + id, "已经购买过该商品");
					request.getRequestDispatcher("list.do").forward(request,
							response);
				} else {
					/*
					 * 因为购买操作会影响到cart中的商品，
					 * 所以每次更新操作之后都会将cart上最新的商品信息以cookie的形式保存的客户端
					 */
					CookieUtils.addCookie("cartInfo", cart.store(), response);

					response.sendRedirect("list.do");
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
		} else if (path.equals("delete")) {
			long id = Long.parseLong(request.getParameter("id"));
			HttpSession session = request.getSession();
			Cart cart = (Cart) session.getAttribute("cart");
			cart.delete(id);

			// CookieUtil.deleteCookie("cartInfo", response);

			response.sendRedirect("cart.jsp");
		} else if (path.equals("clear")) {
			HttpSession session = request.getSession();
			Cart cart = (Cart) session.getAttribute("cart");
			cart.clear();

			/*
			 * 每次更新操作之后都会将cart上最新的商品信息以cookie的形式保存的客户端
			 */
			CookieUtils.addCookie("cartInfo", cart.store(), response);

			response.sendRedirect("cart.jsp");
		} else if (path.equals("modify")) {
			long id = Long.parseLong(request.getParameter("id"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));

			HttpSession session = request.getSession();
			Cart cart = (Cart) session.getAttribute("cart");
			cart.modify(id, quantity);

			/*
			 * 每次更新操作之后都会将cart上最新的商品信息以cookie的形式保存的客户端
			 */
			CookieUtils.addCookie("cartInfo", cart.store(), response);

			response.sendRedirect("cart.jsp");
		}
	}
}