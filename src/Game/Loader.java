package Game;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by belyanka on 19.06.2016.
 */
public class Loader {
    private static Loader instance;
    private GameFrame frame;
    private static String listpath = "levels/lvllist.lvl";
    private ArrayList<String> lvllist;
    private Level currentLevel;
    private int currentI;

    private Loader(){
        currentI=0;
        try {
            String buffer;
            BufferedReader in = new BufferedReader(new FileReader(listpath));
            while((buffer=in.readLine())!=null){
                lvllist.add(buffer);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //loadLevel();
    }
    public void setFrame(GameFrame g){
        this.frame=g;
    }

    public void loadLevel(int num){
        if(num!=currentI){

            try {
                FileInputStream fis = new FileInputStream("levels/" + lvllist.get(num));
                ObjectInputStream oin = new ObjectInputStream(fis);
                currentLevel = (Level) oin.readObject();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //TODO:changing frame
        }
        else{
            //TODO:changing frame
        }
    }

    public static Loader getInstance(){
        if(instance==null){
            instance=new Loader();
        }
        return instance;
    }

}
