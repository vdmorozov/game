package Game;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Морозов on 19.06.2016.
 */
public class Level extends Field{
    private int ballNumber;
    private ArrayList<Position> start;
    private ArrayList<Position> finish;

    public Level(int row, int col, int ballNumber, ArrayList<Position> start, ArrayList<Position> finish ){
        super(row, col);

        if( (ballNumber != start.size()) || (ballNumber != finish.size())){
            throw new IllegalArgumentException("number of balls, start positions and finish positions must be equal");
        }
        this.ballNumber = ballNumber;
        this.start = start;
        this.finish = finish;
    }

    @Override
    void setBlock(Position block){
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

    //TODO: getStart(), getFinish()
}
