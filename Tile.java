import java.util.Random;

public class Tile {
    private char val;

    public Tile() {
        this.val = ' ';
    }

    public Tile(char val) {
        this.val = val;
    }

    public void pickup() {
        this.val = (char) (new Random().nextInt(26) + 'A');
    }

    public char getValue() {

        return this.val;
    }
}