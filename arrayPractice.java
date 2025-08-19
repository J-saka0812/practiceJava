import java.util.Scanner;

public class arrayPractice {
    public static void main(String[] args) {

        String[] subject = { "国語: ", "数学: ", "英語: " };
        int[] scores = new int[3];
        int scoresLen = scores.length;
        int sum = 0;
        Scanner scan = new Scanner(System.in);

        for (int i = 0; i < scoresLen; i++) {
            System.out.print(subject[i]);
            int score = scan.nextInt();
            scores[i] = score;
            sum = sum + scores[i];
        }
        int avg = sum / scoresLen;
        System.out.println("合計: " + sum);
        System.out.println("平均: " + avg);

    }
}