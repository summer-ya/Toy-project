package board.dto;

import lombok.Data;

@Data
public class BoardFile {

	private int fFileno;
	private int bno;
	private String originName;
	private String storedName;
	
	public BoardFile() {}

}
