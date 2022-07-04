import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Scrabble {
    private final Tile[] tiles;
    ArrayList<String> list = new ArrayList<>();

    public Scrabble() {
        this.tiles = new Tile[7];
        for (Tile tile : tiles) {
            tile.pickup();
        }
    }

    public Scrabble(Tile[] tiles) {
        this.tiles = tiles;
    }

    public String getLetters() {
        StringBuilder allLetters = new StringBuilder();
        for (Tile tile : tiles) {
            allLetters.append(tile.getValue());
        }
        return allLetters.toString();
    }

    public ArrayList<String> getWords() {
        char[] a = getLetters().toCharArray();
        for (int i=0; i<7; i++) {
            addAll(a, i);
        }
    }

    private void addAll(char[] a, int start) {
        if (start == 7)
            list.add(new String(a));
        else {
            for (int x = start; x < 7; x++) {
                swap(a, start, x);
                addAll(a, start + 1);
                swap(a, start, x);
            }
        }
    }

    private void swap(char[] a, int i, int x) {
        char t = a[i];
        a[i] = a[x];
        a[x] = t;
    }

    public int[] getScores() {
        int[] arr = new int[list.size()];
        int i = 0;
        for (String s : list) {
            int count = 0;
            for(char c : s.toCharArray()){
                count += getScore(c);
            }
            arr[i] = count;
            i++;
        }
        Arrays.sort(arr);
        return arr;
    }

    private int getScore(char c) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('A', 1);
        map.put('B', 3);
        map.put('C', 3);
        map.put('D', 2);
        map.put('E', 1);
        map.put('F', 4);
        map.put('G', 2);
        map.put('H', 4);
        map.put('I', 1);
        map.put('J', 8);
        map.put('K', 5);
        map.put('L', 1);
        map.put('M', 3);
        map.put('N', 1);
        map.put('O', 1);
        map.put('P', 3);
        map.put('Q', 10);
        map.put('R', 1);
        map.put('S', 1);
        map.put('T', 1);
        map.put('U', 1);
        map.put('V', 4);
        map.put('W', 4);
        map.put('X', 8);
        map.put('Y', 4);
        map.put('Z', 10);
        return map.get(c);
    }

    public boolean equals(Scrabble scrabble) {
        return getLetters().equals(scrabble.getLetters());
    }
}