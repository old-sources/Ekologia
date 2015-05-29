package coop.ekologia.service.utils;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

@Stateless
public class FileUploadService implements FileUploadServiceInterface {

	@Override
	public void uploadFile(String FileName, HttpServletRequest request){
		
	}

}
