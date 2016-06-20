package Game;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	private final ArrayList<Color> colors;
	private Game game;
	private Revolver<Color> colorRevolver;

	Thread repaintThread;
	
	public GamePanel(Game game){
		this.game = game;
		colors = new ArrayList<>();
		colors.add(Color.BLUE);
		colors.add(Color.GREEN);
		colors.add(Color.MAGENTA);
		colors.add(Color.RED);

		repaintThread = new Thread(this);
		repaintThread.start();
	}
	
	@Override
	public void paintComponent(Graphics g){
		int stepX = this.getWidth()/game.getCol();
		int stepY = this.getHeight()/game.getRow();
		int offsetX = 1;
		int offsetY = 1;
		
		//this.setBackground(Color.WHITE);
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		colorRevolver = new Revolver<Color>(colors);

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
		for(int i = 0; i < game.getRow(); i++){
			for(int j = 0; j < game.getCol(); j++){
				if(!game.getLevel().isEmpty(new Position(i, j))){
					g2d.fillRect(j * stepX + offsetX,  i * stepY + offsetY, stepX, stepY);
				}
			}
		}

		//финиши
		for(Position finish : game.getLevel().getFinish()){
			g2d.setColor(colorRevolver.next());
			g2d.fillRect(finish.j * stepX + offsetX,  finish.i * stepY + offsetY, stepX, stepY);
		}
		//сбрасываем цветовой револьвер
		colorRevolver = new Revolver<Color>(colors);

		//м€чи
		for (Ball ball : game.getBalls()){
			g2d.setColor(colorRevolver.next());
			int smallestStep;
			if(stepX < stepY) smallestStep = stepX;
			else smallestStep = stepY;

			g2d.fillOval(
					ball.getPosition().j * stepX + offsetX + smallestStep*1/8,
					ball.getPosition().i * stepY + offsetY + smallestStep*1/8,
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
