package co.micol.prj.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileUpLoadVO {
	private int keyNum;  //순번
	private String ofileName;  //원본파일명
	private String pfileName;  //물리파일명
	private long fileSize;  //파일사이즈
}
