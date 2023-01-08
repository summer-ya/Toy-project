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

	@Override
	public int idchk(String memberId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean join(Member member) {
		// TODO Auto-generated method stub
		return false;
	}

}
