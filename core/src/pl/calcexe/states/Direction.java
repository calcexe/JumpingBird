package pl.calcexe.states;

/**
 * Created by Rubin on 2016-02-23.
 */
public enum Direction {
    LEFT(1), RIGHT(-1), TOP(-1), BOTTOM(1);

    private int value;
    Direction(int i) {
        value = i;
    }

    public int getValue() {
        return value;
    }
}
