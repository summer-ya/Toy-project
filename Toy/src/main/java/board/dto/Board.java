package board.dto;

import java.sql.Date;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Board {
	
	private int bno;
	private String title;
	private String content;
	private String userid;
	private int hit;
	private Date writeDate;
	
	
	private int commCnt; //댓글수
	private int fileCnt; //첨부파일 유무
	
	
	
	public Board() {}



	public Board(int bno, String title, String content, String userid, int hit, Date writeDate, int commCnt,
			int fileCnt) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.userid = userid;
		this.hit = hit;
		this.writeDate = writeDate;
		this.commCnt = commCnt;
		this.fileCnt = fileCnt;
	}
	
	

}
