package Game;

import java.util.Iterator;

/**
 * Created by Морозов on 20.06.2016.
 */
public class Revolver<T>{
    Iterable<T> items;
    Iterator<T> iterator;
    public Revolver(Iterable<T> items){
        this.items = items;
        iterator = items.iterator();
    }

    public T next() {
        //если проитерировали всю коллекцию, сбрасываем итератор
        if (!iterator.hasNext()){
            iterator = items.iterator();
        }
        return iterator.next();
    }
}
