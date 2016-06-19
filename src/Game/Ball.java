package Game;

public class Ball {
	private int i;
	private int j;
	private Field field;
	
	Ball(int i, int j, Field field){
		if(!field.isEmpty(new Position(i, j))){
			throw new IllegalArgumentException("Cannot create ball: point is not empty");
		}
		this.i = i;
		this.j = j;
		this.field = field;
	}
	
	boolean up(){
		if (i == 0) return false;
		else if(field.isEmpty(new Position(i-1, j))){
			i--;
			return true;
		}
		else return false;
	}
	boolean down(){
		if ( (i+1) == field.getRow()) return false;
		else if(field.isEmpty(new Position(i+1, j))){
			i++;
			return true;
		}
		else return false;
	}
	boolean left(){
		if (j == 0) return false;
		else if(field.isEmpty(new Position(i, j-1))){
			j--;
			return true;
		}
		else return false;
	}
	boolean right(){
		if ( (j+1) == field.getCol()) return false;
		else if(field.isEmpty(new Position(i, j+1))){
			j++;
			return true;
		}
		else return false;
	}
	int getI(){
		return i;
	}
	int getJ(){
		return j;
	}
	Field getField(){
		return field;
	}
}
