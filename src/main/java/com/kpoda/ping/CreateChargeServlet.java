package com.kpoda.ping;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.model.Charge;

/**
* @author 陈嘉镇
* @version 创建时间：2016-2-19 下午6:41:32
*/
public class CreateChargeServlet extends HttpServlet {

	private static final String CHARGE_SUCCESS_URL = "http://skypig555.6655.la/ping/chargeSuccess";
	private static final String APP_ID = "app_5KiHOSDa9OyHzzzP";
	private static final String APP_KEY = "sk_test_90OiHC0qH4u5WvrnH8DG0KaP";
	/**
	 * 
	 */
	private static final long serialVersionUID = -349132860505599594L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/createCharge.jsp");
		//		requestDispatcher.forward(req, resp);

		String line = null;
		StringBuffer sBuffer = new StringBuffer();
		while ((line = req.getReader().readLine()) != null) {
			System.out.println(line);
			sBuffer.append(line);
		}

		Gson g = new Gson();
		Map<String, Object> para = g.fromJson(sBuffer.toString(), Map.class);

		Pingpp.apiKey = APP_KEY;
		Map<String, Object> chargeParams = new HashMap<String, Object>();
		chargeParams.put("order_no", para.get("order_no")==null?"no"+Calendar.getInstance().getTimeInMillis():para.get("order_no"));
		chargeParams.put("amount", ((Double)para.get("amount")).intValue());
		Map<String, String> app = new HashMap<String, String>();
		app.put("id", APP_ID);
		chargeParams.put("app", app);
		chargeParams.put("channel", para.get("channel"));
		chargeParams.put("currency", "cny");
		chargeParams.put("client_ip", "127.0.0.1");
		chargeParams.put("subject", "Your Subject");
		chargeParams.put("body", "Your Body");
		if(para.get("channel").equals("alipay_wap")){
			Map<String, String> extra = new HashMap<String, String>();
		    extra.put("success_url", CHARGE_SUCCESS_URL);
		    chargeParams.put("extra",extra);
		    
		}
		
		if(para.get("channel").equals("upacp_wap")){
			Map<String, String> extra = new HashMap<String, String>();
		    extra.put("result_url", CHARGE_SUCCESS_URL);
		    chargeParams.put("extra",extra);
		    
		}
		
		
		if(para.get("channel").equals("wx_pub")){
			Map<String, String> extra = new HashMap<String, String>();
		    extra.put("open_id", para.get("open_id").toString()==null?"gzboguan":para.get("open_id").toString());
		    chargeParams.put("extra",extra);
		    
		}
	    
		Charge create;
		try {
			create = Charge.create(chargeParams);
			para.put("id", create.getId());
			para.put("credential", create.getCredential());
			System.out.println(create.toString());
		} catch (AuthenticationException | InvalidRequestException | APIConnectionException | APIException e) {
			e.printStackTrace();
		}

		resp.getWriter().write(g.toJson(para));
		resp.getWriter().flush();
		return;
	}

}
