package Game;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by belyanka on 19.06.2016.
 */
public class Loader implements EndGameObserver{
    private static Loader instance;
    private GameFrame frame;
    private static String listpath = "levels/lvllist";
    private ArrayList<String> lvllist;
    private Level currentLevel;
    private int currentI;

    private Loader(){
        currentI=0;
        lvllist=new ArrayList<>();
        try {
            String buffer;
            BufferedReader in = new BufferedReader(new FileReader(listpath));
            while((buffer=in.readLine())!=null){
                lvllist.add(buffer);
                System.out.println(buffer);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame = new GameFrame(this);
        loadLevel(currentI);
    }
    public void setFrame(GameFrame g){
        this.frame=g;
    }

    public void loadSameLevel(){
        System.out.println("Loader is working.");
        loadLevel(currentI);
    }

    public void loadNextLevel(){
        currentI++;
        loadLevel(currentI);
    }

    public void loadLevel(int num){

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
        Game game = new Game(currentLevel);
        game.registerObserver(this);
        frame.setLevel(game);

    }

    public static Loader getInstance(){
        if(instance==null){
            instance=new Loader();
        }
        return instance;
    }

    @Override
    public void EndGame() {
        frame.setEndGameButtons();
    }
}
