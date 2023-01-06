package login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import login.service.face.MemberService;

@Controller
public class JoinController {
	
	//로그 객체
	private static final Logger logger = LoggerFactory.getLogger(JoinController.class);
	
	//서비스 객체
	@Autowired private MemberService memberService;
	
	@GetMapping("/join")
	public String join() {
		
		logger.info("/join [GET] 성공");
		
		return "join/join";
	
	}

}
