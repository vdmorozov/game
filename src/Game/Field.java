package Game;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Field implements Serializable {
	static final int ROW = 10;
	static final int COL = 10;
	
	private int row;
	private int col;
	private ArrayList<Boolean> matrix;
	
	public Field(int row, int col){
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
	
	public void setBlock(Position pos)
	{
		matrix.set(pos.i * col + pos.j, false);
	}	
	public boolean isEmpty(Position pos){
		return matrix.get(pos.i * col + pos.j);
	}
	int getRow(){
		return row;
	}
	int getCol(){
		return col;
	}
}
