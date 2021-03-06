import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {

    void run() {
        String inputData = Helper.getInputFromFile("input.c");
        inputData = Helper.removeQuotes(inputData);
        inputData = Helper.removeSinglelineComments(inputData);
        inputData = Helper.removeMultilineComments(inputData);

        cyclo(inputData);
    }

    void cyclo(String inputData) {
        String pattern = "(void|int|float|char|double)\\s+(.*)\\(.*?\\)\\s*\\{";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(inputData);
        while (m.find()) {
            int openingIndex = m.end();
            int closingIndex = findMatching(inputData, openingIndex);
            int noDecisionPoints = findDecisionPoints(inputData.substring(openingIndex, closingIndex));
            System.out.println(m.group(2) + " " + noDecisionPoints);
        }
    }

    int findDecisionPoints(String data) {
        int count = 0;
        String pattern = "(if|while|for)\\s*\\(.*?\\)";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(data);
        while (m.find()) {
            //System.out.println(m.group(0));
            count++;
        }
        return count;
    }

    int findMatching(String inputData, int openingIndex) {
        int i = openingIndex;
        int count = 1;
        int matchingIndex = -1;
        while (true) {
            if (inputData.charAt(i) == '{') {
                count++;
            } else if (inputData.charAt(i) == '}') {
                count--;
            }
            if (count == 0) {
                matchingIndex = i;
                break;
            }
            i++;
        }
        return matchingIndex;
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
