package Game;

import java.util.concurrent.TimeUnit;

public class DownCommand extends ScriptExpression{
	int steps;
	
	public DownCommand(int steps){
		if(steps < 1){
			throw new IllegalArgumentException("аргумент меньше единицы!");
		}
		this.steps = steps;
	}
	
	@Override
	public void interpret(Ball ball) {
		for (int i = 0; i < steps; i++){
			if(!ball.down()){
				throw new IndexOutOfBoundsException("внизу нет места!");
			}
			try {
				TimeUnit.SECONDS.sleep(DELAY);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
