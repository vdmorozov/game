package LevelDesigner;

/**
 * Created by belyanka on 19.06.2016.
 */
import Game.Level;
import Game.Position;

import java.io.*;
import java.util.ArrayList;

public class Designer {

    public static void main(String[] args) {
        String listpath = "levels/lvllist";

        ArrayList<String> lvlnames = new ArrayList<String>();
        lvlnames.add("level1.lvl");
        lvlnames.add("level2.lvl");
        lvlnames.add("level3.lvl");
        lvlnames.add("level4.lvl");

        ArrayList<Level> levels = new ArrayList<Level>();

        //level 1
        ArrayList<Position> start1 = new ArrayList<>();
        start1.add(new Position(7,3));
        start1.add(new Position(7,6));
        ArrayList<Position> finish1 = new ArrayList<>();
        finish1.add(new Position(2,3));
        finish1.add(new Position(2,6));
        Level level1 = new Level(10,10,2,start1, finish1);
        levels.add(level1);

        //level2
        ArrayList<Position> start2 = new ArrayList<>();
        start2.add(new Position(7,3));
        start2.add(new Position(7,6));
        ArrayList<Position> finish2 = new ArrayList<>();
        finish2.add(new Position(2,3));
        finish2.add(new Position(2,6));
        Level level2 = new Level(10,10,2,start2, finish2);

        for(int j = 0; j < 8; j++) {
            level2.setBlock(new Position(1, j));
            level2.setBlock(new Position(3, j));
        }
        levels.add(level2);

        //level3
        ArrayList<Position> start3 = new ArrayList<>();
        start3.add(new Position(6,3));
        start3.add(new Position(7,6));
        ArrayList<Position> finish3 = new ArrayList<>();
        finish3.add(new Position(3,7));
        finish3.add(new Position(3,2));
        Level level3 = new Level(10,10,2,start3, finish3);

        for(int i = 2; i < 6; i++) {
            level3.setBlock(new Position(i, 1));
            level3.setBlock(new Position(i, 8));
            for(int j = 3; j < 7; j++){
                level3.setBlock(new Position(i, j));
            }
        }
        level3.setBlock(new Position(2,2));
        level3.setBlock(new Position(2,7));
        level3.setBlock(new Position(6,6));
        level3.setBlock(new Position(6,1));
        level3.setBlock(new Position(6,8));
        level3.setBlock(new Position(7,1));
        level3.setBlock(new Position(7,2));
        level3.setBlock(new Position(7,3));
        level3.setBlock(new Position(7,8));
        for(int j = 1; j < 9; j++){
            level3.setBlock(new Position(8,j));
        }
        levels.add(level3);

        //level3
        ArrayList<Position> start4 = new ArrayList<>();
        start4.add(new Position(3,3));
        start4.add(new Position(2,7));
        ArrayList<Position> finish4 = new ArrayList<>();
        finish4.add(new Position(2,3));
        finish4.add(new Position(2,2));
        Level level4 = new Level(10,10,2,start4, finish4);

        for(int j = 1; j < 5; j++){
            level4.setBlock(new Position(1,j));
        }
        level4.setBlock(new Position(2,1));
        level4.setBlock(new Position(3,1));
        for(int i = 3; i < 8; i++){
            level4.setBlock(new Position(i,2));
            level4.setBlock(new Position(i,4));
        }
        level4.setBlock(new Position(5,3));
        level4.setBlock(new Position(7,3));

        levels.add(level4);

        //writing names to lvllist
        try {
            FileWriter writer = new FileWriter(listpath);
            for(String s : lvlnames){
                writer.write(s);
                writer.write("\r\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i=0;i<lvlnames.size();i++) {
            try {
                FileOutputStream fos = new FileOutputStream("levels/"+lvlnames.get(i));
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(levels.get(i));
                oos.flush();
                oos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
