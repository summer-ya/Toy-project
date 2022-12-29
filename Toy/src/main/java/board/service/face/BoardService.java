package board.service.face;

import java.util.List;

import board.dto.Board;
import board.util.Paging;

public interface BoardService {
	
	// 게시글 목록 페이징
	public Paging getPaging(int curPage);

	// 게시글 리스트 조회
	public List<Board> list(Paging paging);

}
