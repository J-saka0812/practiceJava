package jp.go.jeed.createclass01;

public class Skill extends Attack {

	String name;
	int damage;
	int mp;

	public Skill(String name, int damage, int mp) {

		this.name = name;
		this.damage = damage;
		this.mp = mp;

	}

	public void skillAttack(Character attackChara, Character damageChara) {

		// 受け取ったキャラクターのHPを直接減らす
		damageChara.hp -= attackChara.skill.damage;
		attackChara.mp -= attackChara.skill.mp;
	}
}
