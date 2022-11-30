package co.micol.prj.emp.command;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.micol.prj.common.Command;
import co.micol.prj.emp.service.EmpService;
import co.micol.prj.emp.service.impl.EmpServiceImpl;
import co.micol.prj.emp.vo.EmpVO;

public class EmpInsert implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		EmpService dao = new EmpServiceImpl();
		
		String fName = request.getParameter("first_name");
		String lName = request.getParameter("last_name");
		String email = request.getParameter("email");
		String hDate = request.getParameter("hire_date");
		String jobId = request.getParameter("job_id");
		
		EmpVO vo = new EmpVO();
		vo.setFirstName(fName);
		vo.setEmail(email);
		vo.setLastName(lName);
		vo.setJobId(jobId);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = new java.sql.Date(dateFormat.parse(hDate).getTime());
			System.out.println(date);
			vo.setHireDate(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(vo.toString());
		
		int r = 0;
		r = dao.EmpInsert(vo);
		if(r>0) {
			r = dao.currval();
			System.out.println("시퀀스"+r);
			vo.setEmployeeId(r);
			vo = dao.EmpSelect(vo);
		}
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
