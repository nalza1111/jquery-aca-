package co.micol.prj.emp.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.micol.prj.common.Command;
import co.micol.prj.emp.service.EmpService;
import co.micol.prj.emp.service.impl.EmpServiceImpl;

public class JobSelect implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		EmpService dao = new EmpServiceImpl();
		
		List<HashMap<String, String>> list = new ArrayList<>();
		
		list=dao.jobList();
		
		
		ObjectMapper mapper = new ObjectMapper();
		
		String json = "";
		
		try {
			json = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return "ajax:"+json;
	}

}
