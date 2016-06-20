package Game;

/**
 * Created by Морозов on 20.06.2016.
 */
public interface EndGameSubject {
    void registerObserver(EndGameObserver o);
    void removeObserver(EndGameObserver o);
    void notifyObservers();
}
