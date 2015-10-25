package edu.pace.csis.phd.sbauth.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.pace.csis.phd.sbauth.dao.SecretDAO;
import edu.pace.csis.phd.sbauth.model.Secret;

public class RedirectURL extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RedirectURL(){
		
	}
	
	/**
	 * 
	 */
        public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
//              response.addHeader("Location", "http://www.google.com");
               // response.setHeader("Location", "http://www.google.com");
                response.setContentType("text/html");
                String password = request.getParameter("pw");
                
                PrintWriter pw = response.getWriter();
                pw.println("<html><head><title>SeedBaseAuth Landing</title></head><body>");
                pw.println("<h1>");
                //getInitParameter function reads the contents ot init-param elements.
               pw.println(getInitParameter(""));
                pw.println("</h1>");
                

                Secret secret = new Secret(password);
    			int userId = 0;
    			userId = SecretDAO.FindUserByChallenge(password);
    			if(userId==0){
    				pw.println("Sorry, Incorrect password, please restart your authentication process");
    			
    			}else{
    				
    				secret.setUserId(userId);
    				SecretDAO.ExpireSecret(password);
    				pw.println("Your password is :  "+ password +"</br>");
    				pw.println( "Hello Handsome, you are" + "VALIDATED, user object is found; we should celebrate userId "+userId);
    			}
    			
                pw.println("</body></html>");
                pw.close();
        }
        
        /**
         * 
         */
        public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            System.out.println("doPost");
           // java.io.ObjectInputStream  objIn = new  java.io.ObjectInputStream(request.getInputStream());

            Secret secret  = null;
            //	secret = (Secret) objIn.readObject();
            	//response.setHeader("Location", "http://localhost:8080/SeedBaseAuth/Login.jsp");
            
                
                PrintWriter pw = response.getWriter();
                pw.println("<html><head><title>Modified Hello World</title></head><body>");
                pw.println("<h1>");
                //getInitParameter function reads the contents ot init-param elements.
                pw.println(getInitParameter("Post message"));
                pw.println("</h1>");
                pw.println("</body></html>");
                pw.close();
              
            System.out.println("Servlet received p:2 ");    
            response.setContentType("text/html");
            response.setHeader("Location", "http://localhost:8080/SeedBaseAuth/Login.jsp");
        }
        /**
         * 
         */

		public String getInitParameter(String userId) {

			String message =" Welcome to SeedBase Authentication " ;
			return message;
		}

}
