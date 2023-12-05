import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1 {
    public static int getOutputPart1() throws IOException {
        FileInputStream fstream = new FileInputStream("./inputs/AoCDay1.txt");

        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String strLine;
        int ans = 0;

        while ((strLine = br.readLine()) != null)   {
            StringBuilder sb = new StringBuilder();
            int i = 0, j = strLine.length() - 1;
            int firstFlag = 0, secondFlag = 0;
            while (i <= j) {
                if (Character.isDigit(strLine.charAt(i)) && firstFlag == 0) {
                    sb.insert(0, strLine.charAt(i));
                    firstFlag = 1;
                }
                if (Character.isDigit(strLine.charAt(j)) && secondFlag == 0) {
                    sb.append(strLine.charAt(j));
                    secondFlag = 1;
                }
                if (firstFlag == 0) {
                    i++;
                }
                if (secondFlag == 0) {
                    j--;
                }
                if (sb.length() == 2) break;
            }
            if (sb.length() == 2) {
                ans += Integer.parseInt(sb.toString());
            }
        }
        return ans;
    }

    public static Map<String, Integer> initMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);
        return map;
    }

    public static int getFirstWordDigitIdx(String str, Map<String, Integer> map) {
        int ans = Integer.MAX_VALUE;
        for (String s : map.keySet()) {
            if (str.contains(s)) {
                ans = Math.min(str.indexOf(s), ans);
            }
        }
        return ans;
    }

    public static int getLastWordDigitIdx(String str, Map<String, Integer> map) {
        int ans = Integer.MIN_VALUE;
        for (String s : map.keySet()) {
            if (str.contains(s)) {
                ans = Math.max(str.lastIndexOf(s), ans);
            }
        }
        return ans;
    }

    public static int getWordDigit(String str, int idx) {
        if (str.charAt(idx) == 'o') {
            return 1;
        } else if (str.charAt(idx) == 't') {
            if (str.charAt(idx + 1) == 'w') {
                return 2;
            } else return 3;
        } else if (str.charAt(idx) == 'f') {
            if (str.charAt(idx + 1) == 'o') {
                return 4;
            } else return 5;
        } else if (str.charAt(idx) == 's') {
            if (str.charAt(idx + 1) == 'i') {
                return 6;
            } else return 7;
        } else if (str.charAt(idx) == 'e') {
            return 8;
        } else return 9;
    }

    public static int getOutputPart2(Map<String, Integer> map) throws IOException {
        FileInputStream fstream = new FileInputStream("./inputs/AoCDay1.txt");

// Get the object of DataInputStream
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String strLine;
        int ans = 0;

//Read File Line By Line
        while ((strLine = br.readLine()) != null)   {
            // Print the content on the console
            StringBuilder sb = new StringBuilder();
            int i = 0, j = strLine.length() - 1;
            int firstFlag = 0, secondFlag = 0;
            int fidx = getFirstWordDigitIdx(strLine, map);
            int lidx = getLastWordDigitIdx(strLine, map);
            while (i <= j) {
                if ((i == fidx || Character.isDigit(strLine.charAt(i))) && firstFlag == 0) {
                    if (i == fidx) {
                        sb.insert(0, getWordDigit(strLine, fidx));
                    }else sb.insert(0, strLine.charAt(i));
                    firstFlag = 1;
                }
                if ((j == lidx || Character.isDigit(strLine.charAt(j))) && secondFlag == 0) {
                    if (j  == lidx) {
                        sb.append(getWordDigit(strLine, lidx));
                    }else sb.append(strLine.charAt(j));
                    secondFlag = 1;
                }
                if (firstFlag == 0) {
                    i++;
                }
                if (secondFlag == 0) {
                    j--;
                }
                if (sb.length() == 2) break;
            }
            if (sb.length() == 2) {
                ans += Integer.parseInt(sb.toString());
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        Map<String, Integer> map = initMap();
        int res = getOutputPart2(map);
        System.out.println(res);
    }
}
