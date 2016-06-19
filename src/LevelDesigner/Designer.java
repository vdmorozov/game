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

        ArrayList<Level> levels = new ArrayList<Level>();

        //level 1
        ArrayList<Position> start0 = new ArrayList<>();
        start0.add(new Position(8,2));
        ArrayList<Position> finish0 = new ArrayList<>();
        finish0.add(new Position(1,5));
        levels.add(new Level(10,10,1,start0, finish0));

        levels.get(0).setBlock(new Position(2,1));
        levels.get(0).setBlock(new Position(3,1));
        levels.get(0).setBlock(new Position(2,6));
        levels.get(0).setBlock(new Position(4,4));
        levels.get(0).setBlock(new Position(4,5));
        levels.get(0).setBlock(new Position(5,6));
        levels.get(0).setBlock(new Position(5,7));
        levels.get(0).setBlock(new Position(6,1));
        levels.get(0).setBlock(new Position(6,2));
        levels.get(0).setBlock(new Position(6,3));
        levels.get(0).setBlock(new Position(7,7));
        levels.get(0).setBlock(new Position(7,8));

        //level2
        ArrayList<Position> start1 = new ArrayList<>();
        start1.add(new Position(8,2));
        start1.add(new Position(8,5));
        ArrayList<Position> finish1 = new ArrayList<>();
        finish1.add(new Position(1,5));
        finish1.add(new Position(1,5));
        levels.add(new Level(10,10,2,start1, finish1));

        levels.get(1).setBlock(new Position(2,1));
        levels.get(1).setBlock(new Position(3,1));
        levels.get(1).setBlock(new Position(2,6));
        levels.get(1).setBlock(new Position(4,4));
        levels.get(1).setBlock(new Position(4,5));
        levels.get(1).setBlock(new Position(5,6));
        levels.get(1).setBlock(new Position(5,7));
        levels.get(1).setBlock(new Position(6,1));
        levels.get(1).setBlock(new Position(6,2));
        levels.get(1).setBlock(new Position(6,3));
        levels.get(1).setBlock(new Position(7,7));
        levels.get(1).setBlock(new Position(7,8));

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
