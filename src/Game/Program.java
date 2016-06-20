package Game;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Program {
	final static int ROW = 10;
	final static int COL = 10;
	final static int BALL_NUMBER = 2;


	public static void main(String[] args) {
		//инициализируем поле, м€ч и интерфейс
		/*ArrayList<Position> start = new ArrayList();
		start.add(new Position(6,2));
		start.add(new Position(6,3));
		ArrayList<Position> finish = new ArrayList();
		finish.add(new Position(2,6));
		finish.add(new Position(2,6));

		Level level = new Level(ROW, COL, BALL_NUMBER, start, finish);
		level.setBlock(new Position(0, 0));
		level.setBlock(new Position(0, 2));
		level.setBlock(new Position(2, 0));
		level.setBlock(new Position(5, 5));
		level.setBlock(new Position(9, 9));
		level.setBlock(new Position(5, 9));
		level.setBlock(new Position(9, 5));
		
		Game game = new Game(level);

		GameFrame frame = new GameFrame(game);
		*/

        Loader loader = Loader.getInstance();

	}

}
