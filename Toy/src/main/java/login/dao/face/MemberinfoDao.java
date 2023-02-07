package login.dao.face;

import login.dto.Memberinfo;

public interface MemberinfoDao {

	/**
	 * id/pw 가 일치하는 사용자 수를 반환한다
	 * 	-> 로그인 인증에 활용한다
	 * 
	 * @param member - 조회할 id/pw 정보
	 * @return 조회된 행 수
	 */
	public int selectCntMemberinfo(Memberinfo memberinfo);


	/**
	 * 
	 * @param memberId 아이디 중복 체크
	 * @return
	 */
	public int idchk(String memberId);

	/**
	 * 회원 id가 존재하는 값인지 확인한다
	 * 	-> 중복된 id인지 확인
	 * 
	 * @param member - 조회하려는 회원의 id를 가진 객체
	 * @return 존재 여부 (0-없음, 1-있음)
	 */
	public int selectCntById(Memberinfo member);

	/**
	 * 신규 회원 정보를 삽입한다
	 * 
	 * @param member - 신규 회원 정보
	 */
	public void insert(Memberinfo member);


	public String login(Memberinfo member);


	public Memberinfo getMemberById(String memberId);


}
