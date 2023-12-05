import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Day2 {

    public static int getGameNum(String str) {
        String[] list = str.split(":");
        String[] hlist = list[0].split(" ");
        return Integer.parseInt(hlist[1]);
    }
    public static boolean getIfPossible(String str) {
        String[] roundList = str.split(": ")[1].split("; ");
        for(int i = 0; i < roundList.length; i++) {
            String[] ballList = roundList[i].split(", ");
            for (int j = 0; j < ballList.length; j ++) {
                String[] singleSetList = ballList[j].split(" ");
                if (singleSetList[1].equals("green") && Integer.parseInt(singleSetList[0]) > 13) {
                    return false;
                }
                if (singleSetList[1].equals("red") && Integer.parseInt(singleSetList[0]) > 12) {
                    return false;
                }
                if (singleSetList[1].equals("blue") && Integer.parseInt(singleSetList[0]) > 14) {
                    return false;
                }
            }
        }
        return true;
    }
    public static int getPowerOfCubes(String str) {
        String[] roundList = str.split(": ")[1].split("; ");
        int minRed = 0, minGreen = 0, minBlue = 0;
        for(int i = 0; i < roundList.length; i++) {
            String[] ballList = roundList[i].split(", ");
            for (int j = 0; j < ballList.length; j ++) {
                String[] singleSetList = ballList[j].split(" ");
                if (singleSetList[1].equals("green")) {
                    minGreen = Math.max(Integer.parseInt(singleSetList[0]), minGreen);
                }
                if (singleSetList[1].equals("red")) {
                    minRed = Math.max(Integer.parseInt(singleSetList[0]), minRed);
                }
                if (singleSetList[1].equals("blue")) {
                    minBlue = Math.max(Integer.parseInt(singleSetList[0]), minBlue);
                }
            }
        }
        return minGreen * minRed * minBlue;
    }

    public static int getOutputPart2() throws IOException {
        FileInputStream fstream = new FileInputStream("./inputs/AoCDay2.txt");

        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String str;
        int ans = 0;
        while ((str = br.readLine()) != null) {
            ans += getPowerOfCubes(str);
        }
        return ans;
    }

    public static int getOutputPart1() throws IOException {
        FileInputStream fstream = new FileInputStream("./inputs/AoCDay2.txt");

        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String str;
        int ans = 0;

        while ((str = br.readLine()) != null)   {
            int gameNum = getGameNum(str);
            if (getIfPossible(str)) {
                ans += gameNum;
            }
        }
        return ans;
    }


    public static void main(String[] args) throws IOException {
        int res = getOutputPart2();
        System.out.println(res);
    }
}
