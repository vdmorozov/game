package Game;

public class Ball {
	private Position position;
	private Position finish;
	private boolean finished;
	private Field field;
	private Game game;

	private final static Object lock = new Object();
	
	Ball(Position start, Position finish, Game game){
		if(!game.getLevel().isEmpty(start) || start.equals(finish)){
			throw new IllegalArgumentException("start point must be empty and not equal to finish");
		}
		this.position = start;
		this.finish = finish;
		this.game = game;
		finished = false;
	}
	
	boolean up(){
		synchronized (lock) {
			//System.out.println("'up' called");
			Position up = new Position(position.i - 1, position.j);
			if (!finished && game.isEmpty(up)) {
				position.i--;
				finished = position.equals(finish);
				if (finished) {
					game.ballFinishedHandler();
				}
				return true;
			} else
				return false;
		}
	}
	boolean down(){
		synchronized (lock) {
			//System.out.println("'down' called");
			Position down = new Position(position.i + 1, position.j);
			if (!finished && game.isEmpty(down)) {
				position.i++;
				finished = position.equals(finish);
				if (finished) {
					game.ballFinishedHandler();
				}
				return true;
			} else return false;
		}
	}
	boolean left(){
		synchronized (lock) {
			//System.out.println("'left' called");
			Position left = new Position(position.i, position.j - 1);
			if (!finished && game.isEmpty(left)) {
				position.j--;
				finished = position.equals(finish);
				if (finished) {
					game.ballFinishedHandler();
				}
				return true;
			} else
				return false;
		}
	}
	boolean right(){
		synchronized (lock) {
			//System.out.println("'right' called");
			Position right = new Position(position.i, position.j + 1);
			if (!finished && game.isEmpty(right)) {
				position.j++;
				finished = position.equals(finish);
				if (finished) {
					game.ballFinishedHandler();
				}
				return true;
			} else return false;
		}
	}
	Position getPosition(){
		return position;
	}
}
