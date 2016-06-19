package Game;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Program {
	final static int ROW = 10;
	final static int COL = 10;
	final static int BALL_NUMBER = 1;


	public static void main(String[] args) {
		//инициализируем поле, мяч и интерфейс
		ArrayList<Position> start = new ArrayList();
		start.add(new Position(6,2));
		ArrayList<Position> finish = new ArrayList();
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
		
		/*
		//проверяем парсер
		Parser parser = new Parser();
		String commands = "вправо; вправо;	вправо;	вправо;	вправо;	вправо;	вниз;влево;	влево;	вниз;вниз;	вправо;	вверх;	вправо;	вниз;";
		parser.parse(commands);
		
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		parser.start(ball);
		*/
		
		/*
		//создадим выражение "up; down; left; right;"
		ScriptExpression expr1 = new SequenceExpression(
				new UpCommand(),
				new DownCommand()
				);
		ScriptExpression expr2 = new SequenceExpression(
				new LeftCommand(),
				new RightCommand()
				);
		ScriptExpression expr = new SequenceExpression(
				expr1,
				expr2
				);
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		expr.interpret(ball);
		*/
	}

}
