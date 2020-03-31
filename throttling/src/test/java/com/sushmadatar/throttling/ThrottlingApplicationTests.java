package com.sushmadatar.throttling;

import static org.junit.Assert.assertEquals;

import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.sushmadatar.throttling.controller.ApiController;

@RunWith(SpringRunner.class)
@WebMvcTest(ApiController.class)
public class ThrottlingApplicationTests {

	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();

	@Test
	public void contextLoads() {
	}

	@Test
	public void testgetServerMessage() {
		for (int i = 0; i < 11; i++) {
			try {
				URL url = new URL("http://localhost:8080/message/throttled/address/get");
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.setConnectTimeout(5000);
				int status = con.getResponseCode();
				if (i < 10) {
					assertEquals(HttpStatus.OK.value(), status);
				} else {
					assertEquals(HttpStatus.TOO_MANY_REQUESTS.value(), status);
				}
				System.out.println(status);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
