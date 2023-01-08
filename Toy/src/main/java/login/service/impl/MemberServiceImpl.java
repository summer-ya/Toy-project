package login.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import login.dao.face.MemberDao;
import login.dto.Member;
import login.service.face.MemberService;

@Service
public class MemberServiceImpl implements MemberService {


	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired MemberDao memberDao;

	//로그인
	@Override
	public boolean login(Member member) {
		int loginChk = memberDao.selectCntMember(member);

		logger.info("loginChk : {}", loginChk);

		if( loginChk > 0 )	return true;

		return false;
	}

	//아이디 중복 체크
	@Override
	public int idchk(String memberId) {
		return memberDao.idchk(memberId);
	}

	@Override
	public boolean join(Member member) {
		//중복된 id인지 확인
		if( memberDao.selectCntById(member) > 0 ) {
			return false;
		}

		//회원 가입 - DB 삽입
		memberDao.insert(member);

		//회원가입 결과 확인
		if( memberDao.selectCntById(member) > 0 ) {
			return true;
		}
		return false;

	}
}