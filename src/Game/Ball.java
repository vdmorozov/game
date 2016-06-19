package Game;

public class Ball {
	private Position position;
	private Field field;
	
	Ball(Position start, Field field){
		if(!field.isEmpty(start)){
			throw new IllegalArgumentException("Cannot create ball: point is not empty");
		}
		this.position = start;
		this.field = field;
	}
	
	boolean up(){
		Position up = new Position(position.i-1, position.j);
		if (position.i == 0)
			return false;
		else if(field.isEmpty(up)){
			position.i--;
			return true;
		}
		else
			return false;
	}
	boolean down(){
		Position down = new Position(position.i+1, position.j);
		if ( position.i+1 == field.getRow())
			return false;
		else if(field.isEmpty(down)){
			position.i++;
			return true;
		}
		else return false;
	}
	boolean left(){
		Position left = new Position(position.i, position.j-1);
		if (position.j == 0) return false;
		else if(field.isEmpty(left)){
			position.j--;
			return true;
		}
		else
			return false;
	}
	boolean right(){
		Position right = new Position(position.i, position.j+1);
		if ( position.j +1 == field.getCol())
			return false;
		else if(field.isEmpty(right)){
			position.j++;
			return true;
		}
		else return false;
	}
	Position getPosition(){
		return position;
	}
	Field getField(){
		return field;
	}
}
