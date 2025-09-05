package jp.go.jeed.createclass01;

public abstract class Attack {

	public static void Attack(Character attackChara, Character damageChara) {

		// 受け取ったキャラクターのHPを直接減らす
		damageChara.hp -= attackChara.damage;
	}


	public abstract void skillAttack(Character attackChara, Character damageChara);
}
