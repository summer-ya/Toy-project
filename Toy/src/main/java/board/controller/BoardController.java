package board.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import board.dto.Board;
import board.dto.BoardFile;
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
	
	//검색 +정렬
	@PostMapping("/listsS")
	@ResponseBody
	public ModelAndView search(@RequestParam(defaultValue="title") String searchType,
			@RequestParam(defaultValue = "") String keyword, 
			@RequestParam(defaultValue = "0")int curPage, 
			@RequestParam(defaultValue = "newest") String type, 
			ModelAndView mav) {
		
		logger.info("/board/listS");
		
		//검색된 전체 게시물 수 조회
		
		Paging paging = new Paging();
		
		paging.setSearchType(searchType);
		paging.setKeyword(keyword);
		paging.setCurPage(curPage);
		paging.setType(type);
		
		Paging sePaging = boardService.getPagingSearchCnt(paging);
		sePaging.setSearchType(searchType);
		sePaging.setKeyword(keyword);
		sePaging.setType(type);
		
		List<Board> sList = boardService.getList(sePaging);
		
		mav.setViewName("/board/search");
		mav.addObject("sePaging", sePaging);
		mav.addObject("sList", sList);
		mav.addObject("totalCnt", sePaging.getTotalCount());
		
		return mav;
		
		
	}
	
	//게시글 상세보기
	@RequestMapping("/boardView")
	public String view(Board board, Model model) {
		logger.info("/board/boardView");
		
		
		//잘못된 게시글 번호 처리
		if( board.getBno() < 0 ) {
			return "redirect:/board";
		}
		
		//게시글 조회
		board = boardService.view(board);
		logger.info("조회된 게시글 {}", board);
		
		//모델값 전달
		model.addAttribute("board", board);
		
		
		//첨부파일 모델값 전달
		BoardFile boardFile = boardService.getAttachFile(board);
		model.addAttribute("boardFile", boardFile);
		
		return "/board/boardView";
		
	}

}
