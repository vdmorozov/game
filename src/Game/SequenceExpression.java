package Game;

public class SequenceExpression extends ScriptExpression{
	
	private ScriptExpression left;
	private ScriptExpression right;
	
	public SequenceExpression(ScriptExpression left, ScriptExpression right){
		this.left = left;
		this.right = right;
	}
	
	@Override
	public void interpret(Ball ball) {
		left.interpret(ball);
		right.interpret(ball);
	}

}
