package login.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import login.service.face.MemberService;

@Controller
public class LoginController {

	//로그 객체
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	//서비스 객체
	@Autowired private MemberService memberService;

	//로그인
	@GetMapping("/login")
	public String login(HttpServletRequest req) {
		req.setAttribute("message", "");
		logger.info("/login [GET]성공");
		return "login/login";

	}

}
