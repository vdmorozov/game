package Game;

import java.util.ArrayList;

/**
 * Created by Морозов on 19.06.2016.
 */
public class Game implements EndGameSubject {
    private ArrayList<EndGameObserver> observers;

    private Level level;
    private ArrayList<Ball> balls;
    private int ballNumber;
    private int finishedBallNumber;

    public Game(Level level){
        observers = new ArrayList<>();

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
        if(expr != null) {
            expr.interpret(balls.get(i));
        }
    }
    public void ballStoppedHandler(boolean finished){
        if(finished) {
            finishedBallNumber++;
            if (finishedBallNumber == ballNumber) {
                //System.out.println("Congratulations! All balls has been finished!");
                //TODO: notify about win
                notifyObservers();
            }
        }else{
            //TODO: notify about lose
            notifyObservers();
        }
    }
    public boolean isEmpty(Position pos){
        boolean result = level.isEmpty(pos);
        for(Ball b : balls){
            result = result && !b.getPosition().equals(pos);
        }
        return result;
    }

    public int getBallNumber(){return ballNumber;}
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


    @Override
    public void registerObserver(EndGameObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(EndGameObserver o) {
        observers.remove(o);
    }

    private void notifyObservers() {
        for(EndGameObserver o : observers){
            o.EndGame();
        }
    }
}
