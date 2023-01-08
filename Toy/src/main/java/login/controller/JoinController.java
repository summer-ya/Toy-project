package login.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import login.dto.Member;
import login.service.face.MemberService;

@Controller
public class JoinController {

	//로그 객체
	private static final Logger logger = LoggerFactory.getLogger(JoinController.class);

	//서비스 객체
	@Autowired private MemberService memberService;

	@GetMapping("/join/join")
	public String join() {

		logger.info("/join/join [GET] 성공");

		return "join/join";

	}

	//아이디 중복 체크

	/*
	 * @RequestParam :  Map<String, Object> 
	 * @RequestBody :  DTO (만약 Member객체로 받으려면 해당 어노테이션 이용)
	 * */
	@ResponseBody
	@RequestMapping(value="login/idchk", method = RequestMethod.POST)
	public String idchk(@RequestParam Map<String, Object> paramMap) {
		try{
			String memberId = paramMap.get("memberId").toString();
			int result = memberService.idchk(memberId);
			return Integer.toString(result);	 

		} catch(Exception e){
			e.printStackTrace();
			return "failed";

		}
	}

	@PostMapping("/join")
	public String joinProcess(Member member, HttpSession session) throws Exception {
		int result = memberService.idchk(member.getMemberId());

		logger.info("{}", member);

		boolean joinResult = memberService.join(member);

		try {
			if( joinResult ) {

				logger.info("회원가입 성공");
				return "redirect:/login";

			} else {

				logger.info("회원가입 실패");
				return "redirect:/join";
			}

		} catch (Exception e) {
			throw new RuntimeException();

		}


	}

}

