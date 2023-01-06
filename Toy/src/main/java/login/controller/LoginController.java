package login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import login.service.face.MemberService;

@Controller
public class LoginController {

	//로그 객체
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	//서비스 객체
	@Autowired private MemberService memberService;

}
