package Game;

import java.util.concurrent.TimeUnit;

public class Program {

	public static void main(String[] args) {
		//инициализируем поле, мяч и интерфейс
		Field field = new Field();
		field.setBlock(0, 0);
		field.setBlock(0, 2);
		field.setBlock(2, 0);
		field.setBlock(5, 5);
		field.setBlock(9, 9);
		field.setBlock(5, 9);
		field.setBlock(9, 5);
		
		Ball ball = new Ball(6,2,field);
		
		GamePanel game = new GamePanel(field, ball);
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
