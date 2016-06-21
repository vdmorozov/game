package Game;

import javax.swing.*;

/**
 * Created by Юыќур on 21.06.2016.
 */
public class EndGameAdapter implements EndGameObserver {

    private GamePanel gamePanel;

    public EndGameAdapter(GamePanel g){
        this.gamePanel=g;
    }

    @Override
    public void EndGame() {
        JOptionPane.showMessageDialog(null,"WIN!");
    }
}
