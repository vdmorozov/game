package Game;

import java.util.ArrayList;

/**
 * Created by Морозов on 19.06.2016.
 */
public class Game {
    private Level level;
    private ArrayList<Ball> balls;
    private int ballNumber;

    public Game(Level level){
        this.level = level;
        ballNumber = level.getBallNumber();
        balls = new ArrayList<>(ballNumber);

        for(int i = 0; i < ballNumber; i++){
            balls.add(new Ball());
        }
    }


}
