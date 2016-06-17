package Game;

import java.util.concurrent.TimeUnit;

public class UpCommand extends ScriptExpression{
	int steps;
	
	public UpCommand(int steps){
		if(steps < 1){
			throw new IllegalArgumentException("аргумент меньше единицы!");
		}
		this.steps = steps;
	}
	
	@Override
	public void interpret(Ball ball) {
		for (int i = 0; i < steps; i++){
			if(!ball.up()){
				throw new IndexOutOfBoundsException("вверху нет места!");
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
