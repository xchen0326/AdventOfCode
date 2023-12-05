import java.io.*;
import java.util.*;

public class Day4 {

    public static int getOutputPart1() throws IOException {
        FileInputStream fstream = new FileInputStream("./inputs/AoCDay4.txt");

        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String str;
        int ans = 0;

        while ((str = br.readLine()) != null)   {
            Set<String> set = new HashSet<>();
            int points = 0;
            String[] winningCards = str.split(" \\| ")[0].split(": ")[1].split(" ");
            for (int i = 0; i < winningCards.length; i++) {
                if (!winningCards[i].equals("")) {
                    set.add(winningCards[i]);
                }
            }
            String[] cardNumbers = str.split(" \\| ")[1].split(" ");
            for (int i = 0; i < cardNumbers.length; i++) {
                if (set.contains(cardNumbers[i])) {
                    if (points == 0) points = 1;
                    else points *= 2;
                }
            }
            ans += points;
        }
        return ans;
    }

    public static int getOutputPart2() throws IOException {
        FileInputStream fstream = new FileInputStream("./inputs/AoCDay4.txt");

        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String str;
        int ans = 0;
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();
        while ((str = br.readLine()) != null) {
            Set<String> set = new HashSet<>();
            int copyNum = 0;
            String[] winningCards = str.split(" \\| ")[0].split(": ")[1].split(" ");
            String[] cardInfo = str.split(" \\| ")[0].split(": ")[0].split(" ");
            int cardNum = Integer.parseInt(cardInfo[cardInfo.length-1]);
            for (int i = 0; i < winningCards.length; i++) {
                if (!winningCards[i].equals("")) {
                    set.add(winningCards[i]);
                }
            }
            String[] cardNumbers = str.split(" \\| ")[1].split(" ");
            for (int i = 0; i < cardNumbers.length; i++) {
                if (set.contains(cardNumbers[i])) {
                    copyNum ++;
                }
            }
            map.put(cardNum, copyNum);
            for (int i = 1; i <= copyNum; i++) {
                queue.add(cardNum+i);
            }
            ans ++;
        }
        while (!queue.isEmpty()) {
            int card = queue.poll();
            ans ++;
            for (int i = 1; i <= map.get(card); i++) {
                queue.add(card+i);
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        int res = getOutputPart2();
        System.out.println(res);
    }
}
