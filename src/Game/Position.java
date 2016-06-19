package Game;

/**
 * Created by Морозов on 19.06.2016.
 */
public class Position {
    final int i, j;

    Position(int i, int j){
        this.i = i;
        this.j = j;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (i != position.i) return false;
        return j == position.j;

    }

    @Override
    public int hashCode() {
        int result = i;
        result = 31 * result + j;
        return result;
    }
}
