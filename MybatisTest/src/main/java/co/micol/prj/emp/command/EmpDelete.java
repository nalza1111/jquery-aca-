package co.micol.prj.emp.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import co.micol.prj.common.Command;
import co.micol.prj.emp.service.EmpService;
import co.micol.prj.emp.service.impl.EmpServiceImpl;
import co.micol.prj.emp.vo.EmpVO;

public class EmpDelete implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		EmpService dao = new EmpServiceImpl();
		
		int empId = Integer.parseInt(request.getParameter("empId"));
		EmpVO vo = new EmpVO();
		vo.setEmployeeId(empId);
		
		int r = 0;
		
		r = dao.EmpDelete(vo);
		
		System.out.println(vo.toString());
		
		if(r==1) {
			return 	"ajax:"+r;
		} else {
			return "ajax:"+0;
		}
	}

}
