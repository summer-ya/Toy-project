package login.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import login.dao.face.MemberinfoDao;
import login.dto.Memberinfo;
import login.service.face.MemberinfoService;

@Service
public class MemberinfoServiceImpl implements MemberinfoService {


	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired MemberinfoDao memberinfoDao;

	//로그인
	@Override
	public boolean login(Memberinfo Memberinfo) {
		int loginChk = memberinfoDao.selectCntMemberinfo(Memberinfo);

		logger.info("loginChk : {}", loginChk);

		if( loginChk > 0 )	return true;

		return false;
	}

	//아이디 중복 체크
	@Override
	public int idchk(String MemberinfoId) {
		return memberinfoDao.idchk(MemberinfoId);
	}

	@Override
	public boolean join(Memberinfo Memberinfo) {
		//중복된 id인지 확인
		if( memberinfoDao.selectCntById(Memberinfo) > 0 ) {
			return false;
		}

		//회원 가입 - DB 삽입
		memberinfoDao.insert(Memberinfo);

		//회원가입 결과 확인
		if( memberinfoDao.selectCntById(Memberinfo) > 0 ) {
			return true;
		}
		return false;

	}

	@Override
	public String loginMember(Memberinfo member) {
		return memberinfoDao.login(member) ;
	}

	@Override
	public Memberinfo memberInfo(Memberinfo member) {
		return memberinfoDao.getMemberById(member.getMemberId());
	}
}