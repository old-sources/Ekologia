package coop.ekologia.service.utils;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

@Local
public interface FileUploadServiceInterface {

	public void uploadFile(String FileName, HttpServletRequest request);
	
}
