package login.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class KakaoController {

		
	@RequestMapping(value = "/login/getKakaoAuthUrl")
		public @ResponseBody String getKakaoAuthUrl(
				HttpServletRequest request) throws Exception {
			String reqUrl = 
					"https://kauth.kakao.com/oauth/authorize"
					+ "?client_id=5bdc6dfedf31b8681764363a598eef7f"
					+ "&redirect_uri=http://localhost:8888/login/oauth_kakao"
					+ "&response_type=code";
			
			return reqUrl;
		}
	
	// 카카오 연동정보 조회
	@RequestMapping(value = "/login/oauth_kakao")
	public String oauthKakao(
			@RequestParam(value = "code", required = false) String code
			, Model model, HttpServletRequest req) throws Exception {

		System.out.println("#########" + code);
        String access_Token = getAccessToken(code);
        System.out.println("###access_Token#### : " + access_Token);
        
        
        HashMap<String, Object> userInfo = getUserInfo(access_Token);
        System.out.println("###access_Token#### : " + access_Token);
        System.out.println("###userInfo#### : " + userInfo.get("email"));
        System.out.println("###nickname#### : " + userInfo.get("nickname"));
       
        JSONObject kakaoInfo =  new JSONObject(userInfo);
        model.addAttribute("kakaoInfo", kakaoInfo);
        
        HttpSession session = req.getSession();
        
        session.setAttribute("login", true);
		session.setAttribute("member_id", kakaoInfo.get("email"));
		session.setAttribute("member_nick", kakaoInfo.get("email"));
		
		/* DB에 카카오 유저 저장 or 조회하여 멤버넘버 가져오기 */ 
		//int memberno = 0;
		//session.setAttribute("member_no", memberno );
        
        return "/mainpage"; 
	}

	
}
