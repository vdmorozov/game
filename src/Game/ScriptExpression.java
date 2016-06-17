package Game;

public abstract class ScriptExpression {
	final static int DELAY = 1; //задержка в секундах
	public abstract void interpret(Ball ball);
}
