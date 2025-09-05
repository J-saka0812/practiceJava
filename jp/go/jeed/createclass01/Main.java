package jp.go.jeed.createclass01;

import java.util.Scanner;

public class Main {

	static boolean gameSet = false;
	static int enemyDaiCount = 0;

	public static void main(String[] args) {

		Skill hSkill = new Skill("断空剣", 70, 100);
		//		Skill hSkill = new Skill("ヒール", 70, 100);
		//		Skill hSkill = new Skill("断空剣", 70, 100);

		// --- キャラクターと武器の生成 ---
		Character[] charaArray = new Character[4];
		charaArray[0] = new Hero("勇者", '\0', 100, 100, 30, hSkill, false);
		charaArray[1] = new Goblin("ゴブリン", 'A', 70, 100, 20, null, false);
		charaArray[2] = new Goblin("ゴブリン", 'B', 70, 100, 20, null, false);
		charaArray[3] = new Wizard("ウィザード", '\0', 100, 100, 30, null, false);

		int wizDaiCount = 0;
		int gobADaiCount = 0;
		int gobBDaiCount = 0;
		int charalen = charaArray.length;

		Scanner scan = new Scanner(System.in);
		String command = "";

		System.out.println("敵が現れた！");

		for (int i = 1; i < charalen; i++) {
			System.out.println(charaArray[i].name + "A" + " HP: " + charaArray[i].hp);
			System.out.println("--------------------");
		}

		do {
			// --- 3体の敵がすべて倒されたかチェック ---
			if (enemyDaiCount == charaArray.length - 1) {
				gameSet = true;
				break; // ループを抜ける
			}

			System.out.println("勇者は考えている: attack or run or skill");
			command = scan.nextLine();

			if (command.equals("attack") || command.equals("skill")) {

				if (command.equals("attack")) {
					// --- 勇者の攻撃 ---
					System.out.println(charaArray[0].name + "の攻撃");
					// 修正点: hero, gobA, gobB, wizを引数として渡す
					for (int i = 1; i < charalen; i++) {
						Attack.Attack(charaArray[0], charaArray[i]);
					}
					System.out.println("敵全体に" + charaArray[0].damage + "のダメージを与えた");
				}
				if (command.equals("skill")) {

					if (charaArray[0].mp < charaArray[0].skill.mp) {
						// スキルアタック不可
						System.out.println("MPが足りない‥");
						continue;
					} else {
						// スキルアタック
						System.out.println(charaArray[0].name + "の" + charaArray[0].skill.name);
						for (int i = 1; i < charalen; i++) {
							hSkill.skillAttack(charaArray[0], charaArray[i]);
						}
						System.out.println("敵全体に" + charaArray[0].skill.damage + "のダメージを与えた");
					}
				}

				// 攻撃後のHP表示
				for (int i = 1; i < charalen; i++) {
					if (charaArray[i].hp > 0 && charaArray[i].daiJudge == false) { // 倒されていなければHP表示
						System.out.println(
								charaArray[i].name + (charaArray[i].suffix == '\0' ? "" : charaArray[i].suffix)
										+ "のHP: " + Math.max(0, charaArray[i].hp));
					} else if (charaArray[i].hp <= 0 && charaArray[i].daiJudge == false) {
						charaArray[i] = Character.Dai(charaArray[i]);
						enemyDaiCount++;
					}
				}

				System.out.println("--------------------");


				// --- 敵の攻撃ターン ---
				// 3体の敵がすべて倒されていなければ攻撃を受ける
				if (enemyDaiCount != 3) {
					System.out.println("敵の攻撃！");

					// 修正点: 生きている敵だけが攻撃するように修正
					for (int i = 1; i < charalen; i++) {
						if (charaArray[i].daiJudge == false) {
							System.out.println(charaArray[i].name
									+ (charaArray[i].suffix == '\0' ? "" : charaArray[i].suffix) + "の攻撃");
							Attack.Attack(charaArray[i], charaArray[0]);
							System.out.println(charaArray[i].damage + "のダメージ");
							System.out.println("--------------------");
						}
						if (charaArray[0].hp <= 0) {
							// 修正点: heroDaiメソッドの戻り値を受け取る
							charaArray[0] = Character.Dai(charaArray[0]);
							gameSet = true;
							break;
						}
					}

					// 勇者が倒れたか判定
					if (charaArray[0].hp > 0) {
						System.out.println(charaArray[0].name + "のHP: " + charaArray[0].hp);
						System.out.println("--------------------");
					}
				}
			} else if (command.equals("run")) {
				Character.run(charaArray[0]);
				System.out.println("あなたの負け");
				break;
			} else {
				//                System.out.println("attack,run,skillを入力してください。");
			}
		} while (!gameSet);

		// --- ゲーム結果の表示 ---
		if (charaArray[0].daiJudge == true) {
			System.out.println("あなたの負け");
		} else if (enemyDaiCount == charaArray.length - 1) {
			System.out.println("あなたの勝ち！");
		}
	}
}