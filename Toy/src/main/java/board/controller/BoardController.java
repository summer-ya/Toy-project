package board.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import board.dto.Board;
import board.service.face.BoardService;
import board.util.Paging;

@RequestMapping("/board")
@Controller
public class BoardController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Autowired BoardService boardService;
	
	
	//게시판 목록
	@GetMapping("/boardList")
	public void list(@RequestParam(defaultValue = "0") int curPage, Board board, Model model) {
		
		logger.info("/board/boardList");
		
		Paging paging = boardService.getPaging(curPage);
		logger.info("{}", paging);
		model.addAttribute("paging", paging);
		
		List<Board> list = boardService.list(paging);
		
		
		model.addAttribute("list", list);
	}

}
