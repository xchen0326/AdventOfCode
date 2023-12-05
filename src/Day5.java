import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Day5 {

    Day5() {

    }

    Map<Integer, Integer> seed2SoilMap = new HashMap<>();
    Map<Integer, Integer> soil2FertilizerMap = new HashMap<>();
    Map<Integer, Integer> fertilizer2WaterMap = new HashMap<>();
    Map<Integer, Integer> water2LightMap = new HashMap<>();
    Map<Integer, Integer> light2TemperatureMap = new HashMap<>();
    Map<Integer, Integer> temperature2HumidityMap = new HashMap<>();
    Map<Integer, Integer> humidity2LocationMap = new HashMap<>();
    String[] seedArr;

    public void convertMaps() throws IOException {
        FileInputStream fstream = new FileInputStream("/Users/xiaoweichen/Desktop/AoC/inputs/AoCDay5.txt");

        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String str;
        String mapType = "seed-to-soil";

        while ((str = br.readLine()) != null)   {
            if (!str.equals("") && !Character.isDigit(str.charAt(0))) {
                if (str.split(": ")[0].equals("seeds")) {
                    seedArr = str.split(": ")[1].split(" ");
                } else {
                    mapType = str.split(" map:")[0];
                }
            } else {
                if (str.equals("")) continue;
                String[] mapping = str.split(" ");
                int dest = Integer.parseInt(mapping[0]), source = Integer.parseInt(mapping[1]), range = Integer.parseInt(mapping[2]);
                for (int i = 0; i < range; i++) {
                    if (mapType.equals("seed-to-soil")) {
                        seed2SoilMap.put(source+i, dest+i);
                    } else if (mapType.equals("soil-to-fertilizer")) {
                        soil2FertilizerMap.put(source+i, dest+i);
                    } else if (mapType.equals("fertilizer-to-water")) {
                        fertilizer2WaterMap.put(source+i, dest+i);
                    } else if (mapType.equals("water-to-light")) {
                        water2LightMap.put(source+i, dest+i);
                    } else if (mapType.equals("light-to-temperature")) {
                        light2TemperatureMap.put(source+i, dest+i);
                    } else if (mapType.equals("temperature-to-humidity")) {
                        temperature2HumidityMap.put(source+i, dest+i);
                    } else {
                        humidity2LocationMap.put(source+i, dest+i);
                    }
                    System.out.println("one map conversion is done.");
                }
            }
        }
        System.out.println("all map conversions are done.");
    }

    public int getOutputPart1() throws IOException {
        convertMaps();
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < seedArr.length; i++) {
            int seed = Integer.parseInt(seedArr[i]);
            int soil = seed2SoilMap.containsKey(seed) ? seed2SoilMap.get(seed) : seed;
            int fertilizer = soil2FertilizerMap.containsKey(soil) ? soil2FertilizerMap.get(soil) : soil;
            int water = fertilizer2WaterMap.containsKey(fertilizer) ? fertilizer2WaterMap.get(fertilizer) : fertilizer;
            int light = water2LightMap.containsKey(water) ? water2LightMap.get(water) : water;
            int temperature = light2TemperatureMap.containsKey(light) ? light2TemperatureMap.get(light) : light;
            int humidity = temperature2HumidityMap.containsKey(temperature) ? temperature2HumidityMap.get(temperature) : temperature;
            int location = humidity2LocationMap.containsKey(humidity) ? humidity2LocationMap.get(humidity): humidity;
            ans = Math.min(location, ans);
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        Day5 day5 = new Day5();
        int res = day5.getOutputPart1();
        System.out.println(res);
    }

}
