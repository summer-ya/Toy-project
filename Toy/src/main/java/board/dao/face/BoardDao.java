package board.dao.face;

import java.util.List;

import board.dto.Board;
import board.dto.BoardFile;
import board.util.Paging;

public interface BoardDao {

	//게시판 전체 행 갯수 조회
	public int selectCntAll();

	//페이징 적용하여 게시판 목록 조회
	public List<Board> selectList(Paging paging);

	//검색 리스트 페이징
	public int getPagingSearchCnt(Paging paging);

	//검색 리스트
	public List<Board> selectSearchlist(Paging paging);

	//조회한 게시글 조회수 1증가
	public void hit(Board board);

	//게시글 번호 이용하여 게시글 조회
	public Board selectBoard(Board board);

	//파일번호 이용해 첨부파일 정보 조회
	public BoardFile selectFileByBno(Board board);

}
