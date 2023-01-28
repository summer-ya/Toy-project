package login.service.face;

import login.dto.Memberinfo;

public interface MemberinfoService {
	
	/**
	 * 로그인 인증 처리
	 * 	
	 * @param Memberinfo - 입력한 ID/PW 정보
	 * @return 로그인 인증 경과
	 */
	public boolean login(Memberinfo Memberinfo);

	/**
	 * 아이디 중복 체크
	 * 
	 * @param MemberinfoId
	 * @return
	 * @throws Exception
	 */
	public int idchk(String MemberinfoId);

	/**
	 * 신규 회원 가입
	 * 
	 * @param Memberinfo - 신규 회원의 정보
	 * @return 회원가입 결과
	 */
	public boolean join(Memberinfo Memberinfo);


}
