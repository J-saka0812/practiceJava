package jp.go.jeed.createclass01;

public class Character {

	String name;
	char suffix;
	int hp;
	int mp;
	int damage;
	// Weapon weapon;
	Skill skill;


	public Character(String charName, char suffix, int hp, int mp, int damage, Skill skill) {
		this.name = charName;
		this.suffix = suffix;
		this.hp = hp;
		this.mp = mp;
		this.damage = damage;
		this.skill = skill;
	}

	public static void run(Character chara) {
		System.out.println(chara.name + "は逃げだした");
		System.out.println("");
	}

	public static int Dai(int enemyDaiCount, Character chara) {
		// HPが0未満にならないように調整
		chara.hp = Math.max(0, chara.hp);

		System.out.println(chara.name + (chara.suffix == '\0' ? "" : chara.suffix) + "は倒れた");
		System.out.println("");
		return 1; // 倒れたことを示すために1を返す
	}

	// 修正点: 引数を減らし、戻り値で状態を返すようにする
	public static boolean heroDai(Character hero) {
		hero.hp = 0; // HPを0にする
		System.out.println(hero.name + "のHP: " + hero.hp);
		System.out.println(hero.name + "は倒れた");
		return true; // 勇者が倒れたことを示すtrueを返す
	}
}