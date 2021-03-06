package Game;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Морозов on 19.06.2016.
 */

public class Level extends Field implements Serializable{

    private int ballNumber;
    private ArrayList<Position> start;
    private ArrayList<Position> finish;

    public Level(int row, int col, int ballNumber, Iterable<Position> start, Iterable<Position> finish ){
        super(row, col);

        int count = 0;

        this.start = new ArrayList<>(ballNumber);
        for(Position p : start){
            for(Position toCheck : this.start){
                if (p.equals(toCheck)){
                    throw new IllegalArgumentException("all start positions must be different");
                }
            }
            this.start.add(new Position(p));
            count++;
        }
        if(count != ballNumber){
            throw new IllegalArgumentException("number of balls, start positions and finish positions must be equal");
        }
        count = 0;

        this.finish = new ArrayList<>(ballNumber);
        for(Position p : finish){
            for(Position toCheck : this.finish){
                if (p.equals(toCheck)){
                    throw new IllegalArgumentException("all finish positions must be different");
                }
            }
            for(Position toCheck : this.start){
                if (p.equals(toCheck)){
                    throw new IllegalArgumentException("finish can not be placed at the same position with one of the starts");
                }
            }
            this.finish.add(new Position(p));
            count++;
        }
        if(count != ballNumber){
            throw new IllegalArgumentException("number of balls, start positions and finish positions must be equal");
        }

        this.ballNumber = ballNumber;
    }

    @Override
    public void setBlock(Position block){
        for(Position p : start){
            if(p.equals(block)){
                throw new IllegalArgumentException("this position is already reserved for start/finish");
            }
        }
        for(Position p : finish){
            if(p.equals(block)){
                throw new IllegalArgumentException("this position is already reserved for start/finish");
            }
        }
        super.setBlock(block);
    }

    public ArrayList<Position> getStart(){
        return start;
    }
    public ArrayList<Position> getFinish(){
        return finish;
    }
    public int getBallNumber(){
        return ballNumber;
    }
}
