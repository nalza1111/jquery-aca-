/*
 * 사용방법 : common-fileUpload라이브러리 필요
 * 객체 생성시 파라메터를 넘겨받아야 함
 * 생성자 Param : request, save Directory
 * return : HashMap<String, Object>로 데이터와 파일명을 담아 돌려줌
 * file : List<FileVO>로 map에 추가되어 있음
 * 단일 또는 다중 파일 업로드 가능
 */

package co.micol.prj.common;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class FileUpload {
	private HttpServletRequest request;
	private String saveDir;
	private String savePath;
	private HashMap<String, Object> map; 
	private int maxFileSize = 1024*1024*10;  //10M
	private String encoding = "UTF-8";
	private FileUpLoadVO fileVO;
	
	
	public FileUpload(HttpServletRequest request, String saveDir) {
		this.request = request;
		this.saveDir = saveDir;
	}
	
	public HashMap<String, Object> setFileUpload() {
		map = new HashMap<String, Object>();
		List<FileUpLoadVO> fileList = new ArrayList<FileUpLoadVO>();
		savePath = request.getServletContext().getRealPath(File.separator + saveDir);
		
		File attachesDir = new File(savePath); 
		
		if(!attachesDir.exists()) {   //저장 폴더가 존재하지 않으면 만든다.
			attachesDir.mkdir();
		}
			 
        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
        fileItemFactory.setRepository(attachesDir);
        fileItemFactory.setSizeThreshold(maxFileSize);
        ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
                
		try {
			List<FileItem> items = fileUpload.parseRequest(request);
			for (FileItem item : items) {
                if (item.isFormField()) {
                	//폼 필드면
                	 String name = item.getFieldName(); // 필드 이름
                     String value = new String((item.getString()).getBytes("8859_1"),encoding); // 필드 값, 한글 인코딩
                	 map.put(name,value);  //필드속성과 값을 담음
                }else { //파일이면
                    if (item.getSize() > 0) {
                    	fileVO = new FileUpLoadVO();
                        int index =  item.getName().lastIndexOf(File.separator);
                        String ofile = item.getName().substring(index  + 1);
                        //파일저장시 파일명 충돌방지를 위해 UUID사용
                        String upFile = UUID.randomUUID().toString() + ofile.substring(ofile.lastIndexOf(".")); 
                        File uploadFile = new File(savePath +  File.separator + upFile);
                        item.write(uploadFile);
                        fileVO.setOfileName(ofile);  //원본파일명
                        fileVO.setPfileName(saveDir +  File.separator + upFile); //물리파일명
                        fileVO.setFileSize(item.getSize()); //파일사이즈
                        fileList.add(fileVO);  //파일정보를 리스트에 담음
                    }
                }
			}
			map.put("attechs", fileList);  //맵에 첨부파일 리스트를 담음		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
