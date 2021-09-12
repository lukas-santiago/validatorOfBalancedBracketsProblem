import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class App {
    public static void main(String[] args) throws Exception {
        if (args.length > 0) {
            File file = new File(args[0]);
            if (file.exists()) {
                String stringToCheck = readFile(file);
                if (checkBalancedBrackets(stringToCheck)) {
                    System.out.println(stringToCheck + " - Valid");
                } else {
                    System.out.println(stringToCheck + " - Invalid");
                }
            } else {
                System.out.println("File not found. Please. Give a valid file path.");
            }
        } else {
            System.out.println("Is needed to give a file path as argument.");
        }
    }

    private static String readFile(File file) throws FileNotFoundException, IOException {
        String stringToCheck = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file.getPath()))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

            stringToCheck = sb.toString();
        }
        return stringToCheck;
    }

    private static Boolean checkBalancedBrackets(String content) {
        Stack<Character> openerDelimiterStack = new Stack<Character>();

        List<Character> openerDelimiter = Arrays.asList(new Character[] { '(', '[', '{', '<' });
        List<Character> closingDelimiter = Arrays.asList(new Character[] { ')', ']', '}', '>' });

        for (int i = 0; i < content.length(); i++) {
            char character = content.charAt(i);

            if (openerDelimiter.contains(character)) {
                openerDelimiterStack.push(character);
            } else if (closingDelimiter.contains(character)) {
                if (openerDelimiterStack.size() == 0) {
                    return false;
                }
                if (openerDelimiter.indexOf(openerDelimiterStack.lastElement()) == closingDelimiter
                        .indexOf(character)) {
                    openerDelimiterStack.pop();
                } else {
                    return false;
                }
            }
        }
        return openerDelimiterStack.isEmpty();
    }
}
