package com.kpoda.ping;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.pingplusplus.Pingpp;
import com.pingplusplus.model.Charge;

/**
* @author 陈嘉镇
* @version 创建时间：2016-2-19 下午3:11:51
*/
public class DemoTest {

	@Test
	public void testPay() throws Exception {
		
	    Pingpp.apiKey = "sk_test_90OiHC0qH4u5WvrnH8DG0KaP";
	    Map<String, Object> chargeParams = new HashMap<String, Object>();
	    chargeParams.put("order_no",  "123456789");
	    chargeParams.put("amount", 100);
	    Map<String, String> app = new HashMap<String, String>();
	    app.put("id", "app_5KiHOSDa9OyHzzzP");
	    chargeParams.put("app",app);
	    chargeParams.put("channel","alipay");
	    chargeParams.put("currency","cny");
	    chargeParams.put("client_ip","127.0.0.1");
	    chargeParams.put("subject","Your Subject");
	    chargeParams.put("body","Your Body");
	    Charge create = Charge.create(chargeParams);
	    System.out.println(create.toString());
		
	}

}
