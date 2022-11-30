package co.micol.prj.member.vo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberVO {
	private String id;
	private String passwd;
	private String name;
	private String email;
	private String resposibility;
	private String pfileName;
	private String ofileName;
	
	@JsonFormat(pattern = "yyyy-mm-dd", locale = "Asia/Seoul")
	private Date date;
}
