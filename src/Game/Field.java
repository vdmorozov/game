package Game;
import java.util.ArrayList;

public class Field {
	static final int ROW = 10;
	static final int COL = 10;
	
	private int row;
	private int col;
	private ArrayList<Boolean> matrix;
	
	private Field(int row, int col){
		this.row = row;
		this.col = col;
		matrix = new ArrayList<Boolean>(row * col);
		
		for(int i = 0; i < row*col; i++){
			matrix.add(true);
		}
	}
	public Field(){
		this(ROW, COL);
	}
	
	void setBlock(int i, int j){
		matrix.set(i * col + j, false);
	}	
	boolean isEmpty(int i, int j){
		return matrix.get(i * col + j);
	}
	int getRow(){
		return row;
	}
	int getCol(){
		return col;
	}
}
