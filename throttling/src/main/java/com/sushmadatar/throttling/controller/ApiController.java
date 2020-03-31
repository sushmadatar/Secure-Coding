package com.sushmadatar.throttling.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weddini.throttling.Throttling;
import com.weddini.throttling.ThrottlingType;

@RestController
@EnableAutoConfiguration
public class ApiController {

	/*
	 * The following throttling configuration allows 10 method calls per Minute for
	 * each unique HttpServletRequest#getRemoteAddr().
	 */
	@Throttling(type = ThrottlingType.RemoteAddr, limit = 10, timeUnit = TimeUnit.MINUTES)
	@RequestMapping("/message/throttled/address/get")
	public
	String getRemoteMessage() {
		return "This is throttled API";
	}

	/*
	 * The following throttling configuration allows 1 method calls per minute for
	 * each unique cookie value retrieved from HttpServletRequest#getCookies().
	 */
	@Throttling(type = ThrottlingType.CookieValue, cookieName = "test-cookie", limit = 1, timeUnit = TimeUnit.MINUTES)
	@GetMapping("/message/throttled/cookie/get")
	String getCookieMessage() {
		return "This is throttled API";
	}

	@Throttling(type = ThrottlingType.HeaderValue, headerName = "test-header", limit = 10, timeUnit = TimeUnit.MINUTES)
	@GetMapping("/message/throttled/header/get")
	String getHeaderMessage() {
		return "This is throttled API";
	}

	/*
	 * The following throttling configuration allows 10 method calls per HOUR for
	 * each unique HttpServletRequest#getUserPrincipal().getName().
	 */
	@Throttling(type = ThrottlingType.PrincipalName, limit = 10, timeUnit = TimeUnit.HOURS)
	@GetMapping("/message/throttled/principal/get")
	String getPrincipalMessage() {
		return "This is throttled API";
	}

	/*
	 * This is the api which is not secure from throttling. Server will go in non responding state for this api.
	 */
	@GetMapping("/message/nonthrottled/get")
	String getNonthrotelledMessage() {
		return "This is non-throttled API";
	}
}
