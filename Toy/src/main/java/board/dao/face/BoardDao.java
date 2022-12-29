package board.dao.face;

import java.util.List;

import board.dto.Board;
import board.util.Paging;

public interface BoardDao {

	//게시판 전체 행 갯수 조회
	public int selectCntAll();

	//페이징 적용하여 게시판 목록 조회
	public List<Board> selectList(Paging paging);

}
