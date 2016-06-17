package Game;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	private Field field;
	private ArrayList<Ball> balls;
	Thread repaintThread;
	
	public GamePanel(Field field, Ball... balls){
		//проверим, св€заны ли данные м€чи с данным полем
		for(Ball b : balls){
			if(b.getField() != field){
				throw new IllegalArgumentException("at least one ball is not associated with this field");
			}
		}
		
		this.field = field;
		this.balls = new ArrayList<Ball>(balls.length);
		for(Ball b : balls){
			this.balls.add(b);
		}
		
		repaintThread = new Thread(this);
		repaintThread.start();
	}
	
	public Ball getBall(int index){
		return balls.get(index);
	}
	
	@Override
	public void paintComponent(Graphics g){
		int stepX = this.getWidth()/field.getCol();
		int stepY = this.getHeight()/field.getRow();
		int offsetX = 1;
		int offsetY = 1;
		
		//this.setBackground(Color.WHITE);
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		//сетка
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(2));
		for(int i = 0; i*stepX < this.getWidth(); i++){
			g2d.drawLine(i*stepX + offsetX, 0, i*stepX + offsetX, this.getHeight());
		}
		for(int i = 0; i*stepY < this.getHeight(); i++){
			g2d.drawLine(0, i*stepY + offsetY, this.getWidth(), i*stepY + offsetY);
		}
		
		//блоки
		for(int i = 0; i < field.getRow(); i++){
			for(int j = 0; j < field.getCol(); j++){
				if(!field.isEmpty(i, j)){
					g2d.fillRect(j * stepX + offsetX,  i * stepY + offsetY, stepX, stepY);
				}
			}
		}
		
		//м€чи
		for (Ball ball : balls){
			int smallestStep;
			if(stepX < stepY) smallestStep = stepX;
			else smallestStep = stepY;

			g2d.fillOval(
					ball.getJ() * stepX + offsetX + smallestStep*1/8, 
					ball.getI() * stepY + offsetY + smallestStep*1/8, 
					smallestStep*3/4, 
					smallestStep*3/4
					);
		}
	}

	@Override
	public void run() {
		try {
			for(;;){
				repaint();
				Thread.sleep(1);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
