package login.service.face;

import login.dto.Member;

public interface MemberService {
	
	/**
	 * 로그인 인증 처리
	 * 	
	 * @param member - 입력한 ID/PW 정보
	 * @return 로그인 인증 경과
	 */
	public boolean login(Member member);

	/**
	 * 아이디 중복 체크
	 * 
	 * @param memberId
	 * @return
	 * @throws Exception
	 */
	public int idchk(String memberId);

	/**
	 * 신규 회원 가입
	 * 
	 * @param member - 신규 회원의 정보
	 * @return 회원가입 결과
	 */
	public boolean join(Member member);


}
