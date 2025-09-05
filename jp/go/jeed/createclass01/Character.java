package jp.go.jeed.createclass01;

public class Character {

	String name;
	char suffix;
	int hp;
	int mp;
	int damage;
	// Weapon weapon;
	Skill skill;
	boolean daiJudge;

	public Character(String charName, char suffix, int hp, int mp, int damage, Skill skill, boolean daiJudge) {
		this.name = charName;
		this.suffix = suffix;
		this.hp = hp;
		this.mp = mp;
		this.damage = damage;
		this.skill = skill;
		this.daiJudge = daiJudge;
	}

	public static void run(Character chara) {
		System.out.println(chara.name + "は逃げだした");
		System.out.println("");
		chara.daiJudge = true;
	}

	public static Character Dai(Character chara) {
		// HPが0未満にならないように調整
		chara.hp = Math.max(0, chara.hp);

		System.out.println(chara.name + "のHP: " + chara.hp);
		System.out.println(chara.name + (chara.suffix == '\0' ? "" : chara.suffix) + "は倒れた");
		System.out.println("--------------------");
		chara.daiJudge = true;
		return chara;
	}

}