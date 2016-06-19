package Game;

public class Ball {
	private Position position;
	private Position finish;
	private boolean finished;
	private Field field;
	private Game game;
	
	Ball(Position start, Position finish, Game game){
		if(!game.getLevel().isEmpty(start) || start.equals(finish)){
			throw new IllegalArgumentException("start point must be empty and not equal to finish");
		}
		this.position = start;
		this.finish = finish;
		this.game = game;
		this.field = game.getLevel();
		finished = false;
	}
	
	boolean up(){
		Position up = new Position(position.i-1, position.j);
		if(!finished && field.isEmpty(up)){
			position.i--;
			finished = position.equals(finish);
			if(finished){
				game.ballFinishedHandler();
			}
			return true;
		}
		else
			return false;
	}
	boolean down(){
		Position down = new Position(position.i+1, position.j);
		if(!finished && field.isEmpty(down)){
			position.i++;
			finished = position.equals(finish);
			if(finished){
				game.ballFinishedHandler();
			}
			return true;
		}
		else return false;
	}
	boolean left(){
		Position left = new Position(position.i, position.j-1);
		if(!finished && field.isEmpty(left)){
			position.j--;
			finished = position.equals(finish);
			if(finished){
				game.ballFinishedHandler();
			}
			return true;
		}
		else
			return false;
	}
	boolean right(){
		Position right = new Position(position.i, position.j+1);
		if(!finished && field.isEmpty(right)){
			position.j++;
			finished = position.equals(finish);
			if(finished){
				game.ballFinishedHandler();
			}
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
