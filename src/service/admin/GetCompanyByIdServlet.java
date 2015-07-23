package service.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import system.Config;
import system.Key;
import system.Value;
import controller.CompanyCtrl;

/**
 * @author RaySong
 */

public class GetCompanyByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCompanyByIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject returnJson = new JSONObject();
		
		try{
			String inputStr = request.getParameter(Key.INPUT);
			JSONObject inputJson = (JSONObject) Config.JPARSER.parse(inputStr);
			System.out.println(inputJson.toJSONString());
			
			returnJson = CompanyCtrl.getCompanyById(inputJson);
		}catch(Exception e){
			e.printStackTrace();
			returnJson.put(Key.STATUS, Value.FAIL);
			returnJson.put(Key.MESSAGE, e);
		}
		
		out.println(returnJson.toJSONString());
	}
}
