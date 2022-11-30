package co.micol.prj.emp.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import co.micol.prj.common.DataSource;
import co.micol.prj.emp.mapper.EmpMapper;
import co.micol.prj.emp.service.EmpService;
import co.micol.prj.emp.vo.EmpVO;

public class EmpServiceImpl implements EmpService{
	private SqlSession sqlSession = DataSource.getInstance().openSession(true);
	private EmpMapper map = sqlSession.getMapper(EmpMapper.class);
	
	@Override
	public List<EmpVO> EmpList() {
		return map.EmpList();
	}
	
	@Override
	public EmpVO EmpSelect(EmpVO vo) {
		return map.EmpSelect(vo);
	}

	@Override
	public int EmpInsert(EmpVO vo) {
		return map.EmpInsert(vo);
	}

	@Override
	public int EmpDelete(EmpVO vo) {
		return map.EmpDelete(vo);
	}

	@Override
	public int EmpUdate(EmpVO vo) {
		return map.EmpUdate(vo);
	}

	@Override
	public List<HashMap<String, String>> jobList() {
		return map.jobList();
	}

	@Override
	public int currval() {
		return map.currval();
	}


}
