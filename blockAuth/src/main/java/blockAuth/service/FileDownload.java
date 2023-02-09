package blockAuth.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class FileDownload {
	public void fileDownLoad(String path, String fileName, String ofileName ,HttpServletRequest request, HttpServletResponse response) {
		String RealPath = path;
		String originalFileName = ofileName;
		try {
			originalFileName = URLEncoder.encode(originalFileName,"UTF-8");
		}catch(Exception e) {}
		String storeFileName = fileName;
		response.setContentType("application/octet-stream; charset=utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        File file = new File(RealPath + "/" + storeFileName);
        ServletOutputStream out1 = null; 
        FileInputStream fis = null;
		try {
			out1 = response.getOutputStream();
			fis = new FileInputStream(file);
	        FileCopyUtils.copy(fis, out1);  
	        response.flushBuffer();
	        out1.flush();
	        out1.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}finally {
			 if(fis != null){                 
	                try{ fis.close();}catch(Exception e){}
	            }
		}
	}
}
