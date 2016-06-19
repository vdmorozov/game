package Game;

import java.util.ArrayList;

/**
 * Created by Морозов on 19.06.2016.
 */
public class Game {
    private Level level;
    private ArrayList<Ball> balls;
    private int ballNumber;
    private int finishedBallNumber;

    public Game(Level level){
        this.level = level;
        ballNumber = level.getBallNumber();
        balls = new ArrayList<>(ballNumber);
        finishedBallNumber = 0;

        //creating balls for this level
        ArrayList<Position> start = level.getStart();
        ArrayList<Position> finish = level.getFinish();
        for(int i = 0; i < ballNumber; i++){
            balls.add(new Ball(start.get(i), finish.get(i), this));
        }
    }

    public void startBall(int i, String commands){
        ScriptExpression expr = Parser.parse(commands);
        expr.interpret(balls.get(i));
    }
    public void ballFinishedHandler(){
        finishedBallNumber++;
        if(finishedBallNumber == ballNumber) {
            //System.out.println("Congratulations! All balls has been finished!");
        }
    }

    public ArrayList<Ball> getBalls(){
        return balls;
    }
    public Level getLevel(){
        return level;
    }
    public int getRow(){
        return level.getRow();
    }
    public int getCol(){
        return level.getCol();
    }


}
