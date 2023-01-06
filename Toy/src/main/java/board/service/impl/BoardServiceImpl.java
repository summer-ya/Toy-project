package board.service.impl;

import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.dao.face.BoardDao;
import board.dto.Board;
import board.dto.BoardFile;
import board.service.face.BoardService;
import board.util.Paging;

@Service
public class BoardServiceImpl implements BoardService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired BoardDao boardDao;
	@Autowired ServletContext context;
	
	//페이징
	@Override
	public Paging getPaging(int curPage) {

		int totalCount = boardDao.selectCntAll();
		
		Paging paging = new Paging(totalCount, curPage);
		
		return paging;
	}

	
	@Override
	public List<Board> list(Paging paging) {
		
		return boardDao.selectList(paging);
	}
	
	//검색 리스트
	@Override
	public List<Board> getList(Paging paging) {
		
		List<Board> flist = boardDao.selectSearchlist(paging);
		logger.info("paging : {}", paging);
		logger.info("flist : {}", flist);
		
		return flist;
	}


	//검색 리스트 페이징
	@Override
	public Paging getPagingSearchCnt(Paging paging) {
		int totalCount = boardDao.getPagingSearchCnt(paging);
		return new Paging(totalCount, paging.getCurPage());
	}


	@Override
	public Board view(Board board) {
		boardDao.hit(board);
		return boardDao.selectBoard(board);
	}


	@Override
	public BoardFile getAttachFile(Board board) {
		return boardDao.selectFileByBno(board);
	}




}
