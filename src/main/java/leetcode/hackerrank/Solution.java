package leetcode.hackerrank;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result {

    /*
     * Complete the 'droppedRequests' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY requestTime as parameter.
     */

    public static int droppedRequests(List<Integer> requestTime) {
        Map<Integer, Integer> positionToDropped = new HashMap<>();

        Map<Integer, Integer> droppedSeconds = new LinkedHashMap<>();
        int count = 0;
        for (int time : requestTime) {
            Integer i = droppedSeconds.get(time);
            if (i == null) {
                droppedSeconds.put(time, 1);
            } else if (i < 3) {
                droppedSeconds.put(time, i + 1);
            } else {
                positionToDropped.merge(count, 1, Integer::sum);
            }
            count++;
        }

        var list = new ArrayList<>(new TreeSet<>(requestTime));

        var countIn10Seconds = 0;
        var countIn60Seconds = 0;

        for (int j = 0; j < list.size(); j++) {
            int el = list.get(j);

            int index = 0;
            while (el != requestTime.get(index)) {
                index++;
            }

            countIn10Seconds = 0;
            countIn60Seconds = 0;
            for (int i = index; i < requestTime.size(); i++) {
                if (requestTime.get(i) < el + 10) {
                    countIn10Seconds++;
                    if (countIn10Seconds > 20) {
                        positionToDropped.merge(i, 1, Integer::sum);
                    }
                } else {
                    countIn10Seconds = 0;
                }

                if (requestTime.get(i) < el + 60) {
                    countIn60Seconds++;
                    if (countIn60Seconds > 60) {
                        positionToDropped.merge(i, 1, Integer::sum);
                    }
                } else {
                    countIn60Seconds = 0;
                }
            }
        }

        return positionToDropped.keySet().size();
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//
//        int requestTimeCount = Integer.parseInt(bufferedReader.readLine().trim());
//
//        List<Integer> requestTime = IntStream.range(0, requestTimeCount).mapToObj(i -> {
//            try {
//                return bufferedReader.readLine().replaceAll("\\s+$", "");
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
//        })
//            .map(String::trim)
//            .map(Integer::parseInt)
//            .collect(toList());
//
//        int result = Result.droppedRequests(requestTime);
//
//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedReader.close();
//        bufferedWriter.close();

        Result.droppedRequests(List.of(1, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 11, 11, 11, 11));
    }
}
