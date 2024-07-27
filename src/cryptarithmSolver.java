import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class cryptarithmSolver {
    private static boolean isCryptarithmValid(String word1, String word2, String word3, HashMap<Character, Integer> map) {
        int num1 = getNumber(word1, map);
        int num2 = getNumber(word2, map);
        int num3 = getNumber(word3, map);
        return (num1 + num2 == num3);
    }

    private static int getNumber(String word, HashMap<Character, Integer> map) {
        int number = 0;
        for (char c : word.toCharArray()) {
            number = number * 10 + map.get(c);
        }
        return number;
    }

    private static boolean solve(String word1, String word2, String word3, HashMap<Character, Integer> map, Set<Integer> used, int index, String allChars) {
        if (index == allChars.length()) {
            return isCryptarithmValid(word1, word2, word3, map);
        }

        char c = allChars.charAt(index);
        for (int i = 0; i <= 9; i++) {
            if (!used.contains(i)) {
                map.put(c, i);
                used.add(i);

                if (solve(word1, word2, word3, map, used, index + 1, allChars)) {
                    return true;
                }

                map.remove(c);
                used.remove(i);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println("Cryptarithm Solver\n");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan kata pertama: ");
        String word1 = scanner.nextLine().toUpperCase();
        System.out.print("Masukkan kata kedua: ");
        String word2 = scanner.nextLine().toUpperCase();
        System.out.print("Masukkan kata hasil: ");
        String word3 = scanner.nextLine().toUpperCase();

        Set<Character> uniqueChars = new HashSet<>();
        for (char c : (word1 + word2 + word3).toCharArray()) {
            uniqueChars.add(c);
        }

        if (uniqueChars.size() > 10) {
            System.out.println("Too many unique letters. Cannot solve.");
            return;
        }

        StringBuilder allChars = new StringBuilder();
        for (char c : uniqueChars) {
            allChars.append(c);
        }

        HashMap<Character, Integer> map = new HashMap<>();
        Set<Integer> used = new HashSet<>();

        if (solve(word1, word2, word3, map, used, 0, allChars.toString())) {
            System.out.println("Solution found:");
            for (char c : map.keySet()) {
                System.out.println(c + " = " + map.get(c));
            }
        } else {
            System.out.println("No solution found.");
        }
    }
}
