package Game;

public abstract class ScriptExpression {
	final static int DELAY = 500; //�������� � �������������
	protected boolean stopped = false;
	public abstract void interpret(Ball ball);

	public boolean isStopped(){
		return stopped;
	}
}
