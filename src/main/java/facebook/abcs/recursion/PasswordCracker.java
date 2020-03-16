package facebook.abcs.recursion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * There are N users registered on a website CuteKittens.com. Each of them has a unique password represented by pass[1],
 * pass[2], ..., pass[N]. As this a very lovely site, many people want to access those awesomely cute pics of the
 * kittens. But the adamant admin does not want the site to be available to the general public, so only those people who
 * have passwords can access it.
 * <p>
 * Yu, being an awesome hacker finds a loophole in the password verification system. A string which is a concatenation
 * of one or more passwords, in any order, is also accepted by the password verification system. Any password can appear
 * or more times in that string. Given access to each of the  passwords, and also have a string
 * <i>loginAttempt,determinwhetherthisstringbeacceptedbythepasswordverificationsystemofthewebsite
 * .Ifallofthe</i>. loginAttempt$ string can be created by concatenating password strings, it is accepted.
 * <p>
 * For example, if there are 3 users with <i>passwords = [abra, ka, dabra]</i>, then some of the valid combinations
 * are <i>abra <b>(passwords[1])</b></i>, <i>kaabra</i> (<b>passwords[2] + passwords[1]</b>), <i>kadabraka</i>
 * (<b>passwords[2] + passwords[3] + passwords[2]</b>), <i>kadabraabra</i> (<b>passwords[2] + passwords[3] +
 * passwords[1]</b>)  and so on. Supplying <i>abra ka dabra</i>, concatenated, passes authentication.
 *
 * <b>Function Description</b>
 * Complete the passwordCracker function in the editor below. It should return the passwords as a single string in the
 * order required for the password to be accepted, each separated by a space. If it is not possible to form the string,
 * return the string <i>WRONG PASSWORD</i>.
 * <p>
 * passwordCracker has the following parameters:
 * <ul>
 *   <li>passwords: a list of password strings</li>
 *   <li>loginAttempt: the string to attempt to create</li>
 * </ul>
 *
 * <b>Input Format</b>
 * The first line contains an integer <i>t</i>, the total number of test cases.
 * <p>
 * Each of the next <i>t</i> sets of three lines is as follows:
 * <ul>
 *   <li>The first line of each test case contains n, the number of users with passwords.</li>
 *   <li>The second line contains n space-separated strings, passwords[i], that represent the passwords of each user.</li>
 *   <li>The third line contains a string, loginAttempt, which Yu must test for acceptance.</li>
 * </ul>
 *
 * <b>Constraints</b>
 * <ul>
 *   <li>1 <= t <= 10</li>
 *   <li>1 <= n <= 10</li>
 *   <li><i>passwords[i]</i> != <i>passwords[j]</i>, 1 <= i < j <= N</></li>
 *   <li>1 <= <i>|passwords[i]|</i> <= 10, where i <i>e</i> [1,n]</li>
 *   <li>1 <= |loginAttempt| <= 2000</li>
 *   <li>loginAttempt and passwords[i] contain only lowercase latin characters ('a'-'z').</li>
 * </ul>
 *
 * <b>Output Format</b>
 * For each valid string, Yu has to print the actual order of passwords, separated by space, whose concatenation results
 * into loginAttempt. If there are multiple solutions, print any of them. If <i>loginAttempt</i> can't be accepted by the
 * password verification system, then print WRONG PASSWORD.
 *
 * <b>Sample Input 0</b>
 * 3
 * 6
 * because can do must we what
 * wedowhatwemustbecausewecan
 * 2
 * hello planet
 * helloworld
 * 3
 * ab abcd cd
 * abcd
 *
 * <b>Sample Output 0</b>
 * we do what we must because we can
 * WRONG PASSWORD
 * ab cd
 * <b>Explanation 0</b>
 * Sample Case #00: "wedowhatwemustbecausewecan" is the concatenation of passwords {"we", "do", "what", "we", "must",
 * "because", "we", "can"}. That is
 * <p>loginAttempt = pass[5] + pass[3] + pass[6] + pass[5] +  pass[4] + pass[1] + pass[5] + pass[2]</p>
 * Note that any password can repeat any number of times.
 * Sample Case #01: We can't create string "helloworld" using the strings {"hello", "planet"}.
 * Sample Case #02: There are two ways to create loginAttempt ("abcd"). Both pass[2] = "abcd" and
 * pass[1] + pass[3] = "ab cd" are valid answers.
 *
 * <b>Sample Input 1</b>
 * 3
 * 4
 * ozkxyhkcst xvglh hpdnb zfzahm
 * zfzahm
 * 4
 * gurwgrb maqz holpkhqx aowypvopu
 * gurwgrb
 * 10
 * a aa aaa aaaa aaaaa aaaaaa aaaaaaa aaaaaaaa aaaaaaaaa aaaaaaaaaa
 * aaaaaaaaaab
 * <b>Sample Output 1</b>
 * zfzahm
 * gurwgrb
 * WRONG PASSWORD
 */
public class PasswordCracker {
  static class Result {

    /*
     * Complete the 'passwordCracker' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING_ARRAY passwords
     *  2. STRING loginAttempt
     */

    private static final String NO_PASSWORD_FOUND = "WRONG PASSWORD";
    private static Set<String> unattainableSubstringSet = new HashSet<>();

    public static String passwordCracker(List<String> passwords, String loginAttempt) {
      if (unattainableSubstringSet.contains(loginAttempt)) {
        return NO_PASSWORD_FOUND;
      }
      // Write your code here
      for (String password : passwords) {
        if (loginAttempt.startsWith(password)) {
          if (loginAttempt.equals(password)) {
            return password;
          }
          String remainingString = loginAttempt.substring(password.length());
          String resultString = passwordCracker(passwords, remainingString);
          if (!resultString.endsWith(NO_PASSWORD_FOUND)) {
            resultString = password + " " + resultString;
            return resultString.trim();
          } else {
            unattainableSubstringSet.add(remainingString);
          }
        }
      }
      return NO_PASSWORD_FOUND;
    }

    public static class Solution {
//      public static void main(String[] args) {
//        String[] passwordArray = new String[]{"because", "can", "do", "must", "we", "what"};
//        List<String> passwords = Arrays.asList(passwordArray);
//        String loginAttempt = "wedowhatwemustbecausewecan";
//        String result = Result.passwordCracker(passwords, loginAttempt);
//        System.out.println(result);
//
//        passwordArray = new String[]{"hello", "planet"};
//        passwords = Arrays.asList(passwordArray);
//        loginAttempt = "helloworld";
//        result = Result.passwordCracker(passwords, loginAttempt);
//        System.out.println(result);
//
//        passwordArray = new String[]{"a", "b", "ab"};
//        passwords = Arrays.asList(passwordArray);
//        loginAttempt = "abbababbaabababbababaabbbbaabababbaabaaaabaabbbabababbbaababbabababbabbbbbababaabaabaabbabbabaababbababbabbbbaababaaabbababaaababababbababbaabbabaaabbabaabbababaaaaaaabbaaababababaababaabbabaaabbababbababaaababaababaababbababaabbaabaabaababaabbabbabaabbaabaabbababaacbbaaabbababbaaababaaaabbabababbbbababababbaabbaaababababbbabbaabbaabababaabaaababaaaababbbaabababababbabababbaaababbaabaabaababababbaaabababaabbabaababbbababbaabbbababaababababaababbbbabaaabababaabbabaabaabbbaabababaababbaaabbabaababbbabaabbaababaaabbababbabaaabababababaaababbabababbababaaabababababbabbabaabaabbabaabaabaabababbbabbabababaaabbbaabaababbbaaaabaabababaaaabbababbaabababbbababababababaababaaabbbabababbaaaababbbabbaababababababababababaabbaaabbbabbababaaaabaaabbabaaaababbaabaaaabbababbabbabbbabbabbababaabababbaabbababbabbabbaabaabbabbbabababaababbaababbaababaabbabbbbbababbabbabaabaababbabbaaababbbabababbababaaabaabbababababbababbababaababaababababbbabaaaababababaaabbabbbabababbabbbabababbaabbabbabbabababaaabbabababababbaabababababaabaababbbabaababbbabaabbbbababbbabaabbabaabaabbbabbbbababbabbabaaabaababbbabababaaabbaabaaabbbbaaabbbabaaabbbbbbabababbaaaabbabababaabbabbbaaabbbababbaababbbababaaababbababbabababaaabaabaabaaaababbbabaabbabaaabbbabbabababababaaaabbaabababbbabababbbaabbaababbaaababbaabaaaabbabababaaabaababbbaabaabaabaaababaababaababababbbbabbbbababbaababaaabaababababbbabbabababababaaabababaabbbabbbabababaabbabababbbaabababbaaababbaabbbabababbabbababbababaababaabaaaabbaababababbababbbababbbbabbbaababaaaababbabbabaabbbabbaabababababbbabababbbbabaabababbbabaaaababababbbabaaabbaabababbbbaabaababbababaaababbabaabaabbabababababababbababababaababaaababaabababbababababaabbabaabaaabbbabbabaabaaabababaabaabababbbbbbbbababababaabbabbabbaababbaabbababaabbbabababaabaabbaaabbabbaaabbbbabaabbabbbbabbabaabbaabaaabbbababbbbbbababaababbaabababbababababbaaabaabbabaaaaaaabbababaabaabbababbabbabbaaabbabbabaabaaababaabbbaabbabbbabababaabaaaabbbabaaaababaabaababaaababbaabaabbbaababababbbbaaabababbbaaabaaaba";
//        result = Result.passwordCracker(passwords, loginAttempt);
//        System.out.println(result);
//      }

      public static void main(String[] args) throws IOException {
        BufferedReader questionReader = new BufferedReader(new FileReader("D:\\code\\fictional-octo-barnacle\\src" +
            "\\main\\java\\facebook\\abcs\\recursion\\Password-Cracker-input27.txt"));
        BufferedReader answerReader = new BufferedReader(new FileReader("D:\\code\\fictional-octo-barnacle\\src" +
            "\\main\\java\\facebook\\abcs\\recursion\\Password-Cracker-output27.txt"));
        int t = Integer.parseInt(questionReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
          try {
            Integer.parseInt(questionReader.readLine().trim());
            List<String> passwords = Stream.of(questionReader.readLine().replaceAll("\\s+$", "").split(" "))
                .collect(toList());
            String loginAttempt = questionReader.readLine();
            String answer = answerReader.readLine();
            String result = Result.passwordCracker(passwords, loginAttempt);
            System.out.print(Instant.now().toString() + " - Test " + tItr + " is finished");
            if (!answer.equals(result)) {
              System.out.println(" - Wrong");
              System.out.println("Answer='" + result + "'");
            } else {
              System.out.println(" - Correct");
            }

          } catch (IOException ex) {
            throw new RuntimeException(ex);
          }
        });
      }
    }
  }
}
