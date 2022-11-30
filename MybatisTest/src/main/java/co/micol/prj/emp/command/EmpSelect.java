package co.micol.prj.emp.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.micol.prj.common.Command;
import co.micol.prj.emp.service.EmpService;
import co.micol.prj.emp.service.impl.EmpServiceImpl;
import co.micol.prj.emp.vo.EmpVO;

public class EmpSelect implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		EmpService dao = new EmpServiceImpl();
		int empId = Integer.parseInt(request.getParameter("empId"));
		EmpVO vo = new EmpVO();
		vo.setEmployeeId(empId);
		vo = dao.EmpSelect(vo);
		
		System.out.println(vo.toString());
	
		ObjectMapper mapper = new ObjectMapper();
		
		String json = "";
		
		try {
			json = mapper.writeValueAsString(vo);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return "ajax:"+json;
	}

}
