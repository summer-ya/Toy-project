package login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import login.dto.Memberinfo;
import login.service.face.MemberinfoService;

@Controller
public class LoginController {

	//로그 객체
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	//서비스 객체
	@Autowired private MemberinfoService memberService;

	//로그인
	@GetMapping("/login")
	public String login(HttpServletRequest req) {
		req.setAttribute("message", "");
		logger.info("/login [GET]성공");
		return "login/login";

	}
	
	@PostMapping("/login")
	public String loginProcess(Memberinfo member, HttpSession session, RedirectAttributes rttr, HttpServletRequest req) {
		
		logger.info("{}", member);
		
		String loginResult = memberService.loginMember(member);
		
		logger.info("loginResult : {}", loginResult);
		
		if(loginResult == null) {

			req.setAttribute("url", "/login");
			rttr.addFlashAttribute("message", "error");

			return "redirect:/login";
			
		} else if(loginResult.equals("N")) {
			
			logger.info("로그인 성공");
			
			member = memberService.memberInfo(member);
			logger.info("{}", member);
			
			session.setAttribute("login", loginResult);
			session.setAttribute("loginid", member.getMemberId());
			session.setAttribute("member_no", member.getMemberNo());

			return "redirect:/main";

		} else {
			logger.info("로그인 실패");
			req.setAttribute("url", "/login");
			rttr.addFlashAttribute("message", "withdrawal");

			return "redirect:/login";
		}

	}
	
		@GetMapping("/loginFail")
		public String loginFail() {
			
	//		logger.info("/loginFail [GET]성공");
			return "login/loginFail";
		
		}
		
	//로그아웃
	@RequestMapping("login/logout")
	
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/mainpage";
	}
	
	

}
