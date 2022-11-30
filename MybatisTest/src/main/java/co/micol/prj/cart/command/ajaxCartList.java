package co.micol.prj.cart.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.micol.prj.cart.service.CartService;
import co.micol.prj.cart.service.impl.CartServiceImpl;
import co.micol.prj.common.Command;

public class ajaxCartList implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		CartService dao = new CartServiceImpl();
		List<HashMap<String, Object>> carts = new ArrayList<>();
		carts = dao.CartList();
		
		System.out.println(carts);
		
		ObjectMapper mapper = new ObjectMapper();
		
		String json ="";
		
		try {
			json = mapper.writeValueAsString(carts);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return "ajax:"+json;
	}

}
