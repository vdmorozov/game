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

	//возвращает true, если удалось перейти вверх
	boolean up(){
		synchronized (lock) {
			//System.out.println("'up' called");
			Position up = new Position(position.i - 1, position.j);
			if(!finished){
				//если мяч еще не достиг финиша, пытаемся двигаться
				if(game.isEmpty(up)){
					//если вверху есть место - идем вверх
					position.i--;
					finished = position.equals(finish);
					if (finished) {
						//если достигли финиша - оповещаем игру
						game.ballStoppedHandler(true);
					}
					return true;
				} else {
					//если вверху нет места - останавливаемся и оповещаем игру
					game.ballStoppedHandler(false);
					return false;
				}
			} else{
				//если мяч уже на финише - остаемся на месте и никого не оповещаем
				return false;
			}
		}
	}

	//возвращает true, если удалось перейти вниз
	boolean down(){
		synchronized (lock) {
			//System.out.println("'down' called");
			Position down = new Position(position.i + 1, position.j);
			if(!finished){
				//если мяч еще не достиг финиша, пытаемся двигаться
				if(game.isEmpty(down)){
					//если внизу есть место - идем вниз
					position.i++;
					finished = position.equals(finish);
					if (finished) {
						//если достигли финиша - оповещаем игру
						game.ballStoppedHandler(true);
					}
					return true;
				} else {
					//если внизу нет места - останавливаемся и оповещаем игру
					game.ballStoppedHandler(false);
					return false;
				}
			} else{
				//если мяч уже на финише - остаемся на месте и никого не оповещаем
				return false;
			}
		}
	}

	//возвращает true, если удалось перейти влево
	boolean left(){
		synchronized (lock) {
			//System.out.println("'left' called");
			Position left = new Position(position.i, position.j - 1);
			if(!finished){
				//если мяч еще не достиг финиша, пытаемся двигаться
				if(game.isEmpty(left)){
					//если слева есть место - идем влево
					position.j--;
					finished = position.equals(finish);
					if (finished) {
						//если достигли финиша - оповещаем игру
						game.ballStoppedHandler(true);
					}
					return true;
				} else {
					//если слева нет места - останавливаемся и оповещаем игру
					game.ballStoppedHandler(false);
					return false;
				}
			} else{
				//если мяч уже на финише - остаемся на месте и никого не оповещаем
				return false;
			}
		}
	}

	//возвращает true, если удалось перейти вправо
	boolean right(){
		synchronized (lock) {
			//System.out.println("'right' called");
			Position right = new Position(position.i, position.j + 1);
			if(!finished){
				//если мяч еще не достиг финиша, пытаемся двигаться
				if(game.isEmpty(right)){
					//если справа есть место - идем вправо
					position.j++;
					finished = position.equals(finish);
					if (finished) {
						//если достигли финиша - оповещаем игру
						game.ballStoppedHandler(true);
					}
					return true;
				} else {
					//если справа нет места - останавливаемся и оповещаем игру
					game.ballStoppedHandler(false);
					return false;
				}
			} else{
				//если мяч уже на финише - остаемся на месте и никого не оповещаем
				return false;
			}
		}
	}

	Position getPosition(){
		return position;
	}
}
