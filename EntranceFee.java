import java.util.Scanner;

public class EntranceFee {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("年齢を整数で入力してください");
		System.out.print("年齢->");
		int age = sc.nextInt();

		//改行を吸収する
		sc.nextLine();

		System.out.println("曜日を漢字１文字で入力してください");
		System.out.print("曜日->");
		String week = sc.nextLine();

		//変数feeはif文のブロック外で宣言する（変数のスコープに注意）
		int fee = 0;
		if (age <= 6) {
			fee = 0;
		} else if (age <= 18) {
			fee = 1000;
		} else if (age <= 59) {
			fee = 1800;
		} else {
			fee = 600;
		}

		if (week.equals("水")) {
			fee *= 0.8;
			//複合代入演算子はキャストにより型を変換する（以下の処理と等しい）
			//fee = (int) (fee * 0.8);
		}

		System.out.println("料金は" + fee + "円です");
	}
}