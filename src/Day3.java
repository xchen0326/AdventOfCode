import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Day3 {

    public static List<Character[]> getSchema() throws IOException {
        FileInputStream fstream = new FileInputStream("./inputs/AoCDay3.txt");

        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String str;
        List<Character[]> schema = new ArrayList<>();

        while ((str = br.readLine()) != null) {
            Character[] charArr = new Character[str.length()];
            for (int i = 0; i < str.length(); i++)  {
                charArr[i] = str.charAt(i);
            }
            schema.add(charArr);
        }

        return schema;
    }

    public static boolean isSymbol(Character character) {
        if (Character.isLetterOrDigit(character) || character == ' ' || character == '.') {
            return false;
        }
        return true;
    }

    public static boolean isPartNumberHelper(List<Character[]> schema, int i, int j) {
        if (i > 0) {
            if (isSymbol(schema.get(i-1)[j])) {
                return true;
            }
            if (j > 0) {
                if (isSymbol(schema.get(i-1)[j-1])) {
                    return true;
                }
            }
            if (j < schema.get(0).length - 1) {
                if (isSymbol(schema.get(i-1)[j+1])) {
                    return true;
                }
            }
        }
        if (i < schema.size() - 1) {
            if (isSymbol(schema.get(i+1)[j])) {
                return true;
            }
            if (j > 0) {
                if (isSymbol(schema.get(i+1)[j-1])) {
                    return true;
                }
            }
            if (j < schema.get(0).length - 1) {
                if (isSymbol(schema.get(i+1)[j+1])) {
                    return true;
                }
            }
        }
        if (j > 0) {
            if (isSymbol(schema.get(i)[j-1])) {
                return true;
            }
        }
        if (j < schema.get(0).length - 1) {
            if (isSymbol(schema.get(i)[j+1])) {
                return true;
            }
        }

        return false;
    }

    public static boolean isPartNumber(List<Character[]> schema, int i, int jlo, int jhi) {
        for (int j = jlo; j <= jhi; j++) {
            if (isPartNumberHelper(schema, i, j)) {
                return true;
            }
        }

        return false;
    }

    public static int getOutputPart1() throws IOException {
        List<Character[]> schema = getSchema();
        int ans = 0;
        for (int i = 0; i < schema.size(); i++) {
            for (int j = 0; j < schema.get(i).length; j++) {
                if (Character.isDigit(schema.get(i)[j])) {
                    int jlo = j;
                    int jhi = j + 1;
                    StringBuilder sb = new StringBuilder();
                    sb.append(schema.get(i)[j]);
                    while (jhi < schema.get(i).length) {
                        if (Character.isDigit(schema.get(i)[jhi])) {
                            sb.append(schema.get(i)[jhi]);
                            jhi ++;
                        } else break;
                    }
                    j = jhi;
                    if (isPartNumber(schema, i, jlo, jhi-1)) {
                        ans += Integer.parseInt(sb.toString());
                    }
                }
            }
        }

        return ans;
    }

    public static int getGearRatio(List<Character[]> schema, int i, int j) {
        int count = 0;
        int ans = -1;
        if (i > 0) {
            if (Character.isDigit(schema.get(i-1)[j])) {
                int start = j - 1, end = j + 1;
                StringBuilder sb = new StringBuilder();
                sb.append(schema.get(i-1)[j]);
                while (start >= 0 || end < schema.get(i-1).length) {
                    if (start >= 0 && Character.isDigit(schema.get(i-1)[start])) {
                        sb.insert(0, schema.get(i-1)[start]);
                        start --;
                    }
                    if (end < schema.get(i-1).length && Character.isDigit(schema.get(i-1)[end])) {
                        sb.append(schema.get(i-1)[end]);
                        end ++;
                    }
                    if ((start < 0 && !Character.isDigit(schema.get(i-1)[end]))
                    || (end >= schema.get(i-1).length && !Character.isDigit(schema.get(i-1)[start]))
                    || (start >= 0 && end < schema.get(i-1).length && !Character.isDigit(schema.get(i-1)[start]) && !Character.isDigit(schema.get(i-1)[end]))) {
                        break;
                    }
                }
                count ++;
                ans = Integer.parseInt(sb.toString());
            } else {
                if (j > 0 && Character.isDigit(schema.get(i-1)[j-1])) {
                    int start = j - 2;
                    StringBuilder sb = new StringBuilder();
                    sb.append(schema.get(i-1)[j-1]);
                    while (start >= 0) {
                        if (Character.isDigit(schema.get(i-1)[start])) {
                            sb.insert(0, schema.get(i-1)[start]);
                            start--;
                        } else break;
                    }
                    ans = count > 0 ? ans * Integer.parseInt(sb.toString()) : Integer.parseInt(sb.toString());
                    count++;
                }
                if (j < schema.get(i-1).length && Character.isDigit(schema.get(i-1)[j+1])) {
                    int end = j + 2;
                    StringBuilder sb = new StringBuilder();
                    sb.append(schema.get(i-1)[j+1]);
                    while (end < schema.get(i-1).length) {
                        if (Character.isDigit(schema.get(i-1)[end])) {
                            sb.append(schema.get(i-1)[end]);
                            end++;
                        } else break;
                    }
                    ans = count > 0 ? ans * Integer.parseInt(sb.toString()) : Integer.parseInt(sb.toString());
                    count++;
                }
            }
        }
        if (i < schema.size() - 1) {
            if (Character.isDigit(schema.get(i+1)[j])) {
                if (count == 2) return -1;
                int start = j - 1, end = j + 1;
                StringBuilder sb = new StringBuilder();
                sb.append(schema.get(i+1)[j]);
                while (start >= 0 || end < schema.get(i+1).length) {
                    if (start >= 0 && Character.isDigit(schema.get(i+1)[start])) {
                        sb.insert(0, schema.get(i+1)[start]);
                        start --;
                    }
                    if (end < schema.get(i+1).length && Character.isDigit(schema.get(i+1)[end])) {
                        sb.append(schema.get(i+1)[end]);
                        end ++;
                    }
                    if ((start < 0 && !Character.isDigit(schema.get(i+1)[end]))
                    || (end >= schema.get(i+1).length && !Character.isDigit(schema.get(i+1)[start]))
                    || (start >= 0 && end < schema.get(i+1).length && !Character.isDigit(schema.get(i+1)[start]) && !Character.isDigit(schema.get(i+1)[end]))) {
                        break;
                    }
                }
                ans = count > 0 ? ans * Integer.parseInt(sb.toString()) : Integer.parseInt(sb.toString());
                count ++;
            } else {
                if (j > 0 && Character.isDigit(schema.get(i+1)[j-1])) {
                    if (count == 2) return -1;
                    int start = j - 2;
                    StringBuilder sb = new StringBuilder();
                    sb.append(schema.get(i+1)[j-1]);
                    while (start >= 0) {
                        if (Character.isDigit(schema.get(i+1)[start])) {
                            sb.insert(0, schema.get(i+1)[start]);
                            start--;
                        } else break;
                    }
                    ans = count > 0 ? ans * Integer.parseInt(sb.toString()) : Integer.parseInt(sb.toString());
                    count++;
                }
                if (j < schema.get(i+1).length && Character.isDigit(schema.get(i+1)[j+1])) {
                    if (count == 2) return -1;
                    int end = j + 2;
                    StringBuilder sb = new StringBuilder();
                    sb.append(schema.get(i+1)[j+1]);
                    while (end < schema.get(i+1).length) {
                        if (Character.isDigit(schema.get(i+1)[end])) {
                            sb.append(schema.get(i+1)[end]);
                            end++;
                        } else break;
                    }
                    ans = count > 0 ? ans * Integer.parseInt(sb.toString()) : Integer.parseInt(sb.toString());
                    count++;
                }
            }
        }
        if (j > 0) {
            // left
            if (Character.isDigit(schema.get(i)[j-1])) {
                if (count == 2) return -1;
                int start = j - 2;
                StringBuilder sb = new StringBuilder();
                sb.append(schema.get(i)[j-1]);
                while (start >= 0) {
                    if (Character.isDigit(schema.get(i)[start])) {
                        sb.insert(0, schema.get(i)[start]);
                        start --;
                    } else break;
                }
                ans = count > 0 ? ans * Integer.parseInt(sb.toString()) : Integer.parseInt(sb.toString());
                count ++;
            }
        }
        if (j < schema.get(i).length - 1) {
            // right
            if (Character.isDigit(schema.get(i)[j+1])) {
                if (count == 2) return -1;
                int end = j + 2;
                StringBuilder sb = new StringBuilder();
                sb.append(schema.get(i)[j+1]);
                while (end < schema.get(i).length) {
                    if (Character.isDigit(schema.get(i)[end])) {
                        sb.append(schema.get(i)[end]);
                        end ++;
                    } else break;
                }
                count ++;
                ans = count > 0 ? ans * Integer.parseInt(sb.toString()) : Integer.parseInt(sb.toString());
            }
        }
        if (count < 2) {
            return -1;
        }
        return ans;
    }

    public static int getOutputPart2() throws IOException {
        int ans = 0;
        List<Character[]> schema = getSchema();
        for (int i = 0; i < schema.size(); i++) {
            for (int j = 0; j < schema.get(i).length; j++) {
                if (schema.get(i)[j] == '*') {
                    int gearRatio = getGearRatio(schema, i, j);
                    if (gearRatio != -1) {
                        ans += gearRatio;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        int res = getOutputPart2();
        System.out.println(res);
    }
}
