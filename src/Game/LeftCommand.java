package Game;

import java.util.concurrent.TimeUnit;

public class LeftCommand extends ScriptExpression{
	int steps;
	
	public LeftCommand(int steps){
		if(steps < 1){
			throw new IllegalArgumentException("�������� ������ �������!");
		}
		this.steps = steps;
	}
	
	@Override
	public void interpret(Ball ball) {
		for (int i = 0; i < steps; i++){
			if(!ball.left()){
				stopped = true;
				break;
			}
			try {
				TimeUnit.MILLISECONDS.sleep(DELAY);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
