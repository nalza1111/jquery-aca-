package co.micol.prj.emp.mapper;

import java.util.HashMap;
import java.util.List;

import co.micol.prj.emp.vo.EmpVO;

public interface EmpMapper {
	List<EmpVO> EmpList();
	EmpVO EmpSelect(EmpVO vo);
	int EmpInsert(EmpVO vo);
	int EmpDelete(EmpVO vo);
	int EmpUdate(EmpVO vo);
	List<HashMap<String, String>> jobList();
	int currval();
}
