/**
 * 
 */
package coop.ekologia.presentation.request;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * @author imie
 *
 */
@RequestScoped
public class TestRequestScope {
	
	@Inject HttpServletRequest request;

	/**
	 * 
	 */
	public TestRequestScope() {
		// TODO Auto-generated constructor stub
	}
	
	public void printUri(){
		System.out.println(request.getRequestURI());
	}

}
