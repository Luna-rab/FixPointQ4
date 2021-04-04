import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CheckTest {
    public static void main(String[] args){
        Path answer = Paths.get(args[0]);
        try {
            List<String> answers = Files.readAllLines(answer);
            List<String> test_outs = new ArrayList<String>();
            
            InputStreamReader in = new InputStreamReader(System.in, "UTF-8");
            BufferedReader b = new BufferedReader(in);
            String l;
            while (null != (l = b.readLine())) {
                test_outs.add(l);
            }

            if(test_outs.containsAll(answers) && answers.containsAll(test_outs)){
                System.out.println("OK!\nOutput:");
                for(String output: test_outs){
                    System.out.println(output);
                }
            }else{
                System.out.println("NG\nExpected Output:");
                for(String output: answers){
                    System.out.println(output);
                }
                System.out.println("Actual Output:");
                for(String output: test_outs){
                    System.out.println(output);
                }
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
