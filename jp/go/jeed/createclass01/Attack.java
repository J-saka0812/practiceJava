package jp.go.jeed.createclass01;

public class Attack {

    public static void heroAttack(Character hero, Character gobA, Character gobB, Character wiz) {


        System.out.println(hero.name + "の攻撃");
        System.out.println("敵全体に" + hero.damage + "のダメージを与えた");

        // 受け取ったキャラクターのHPを直接減らす
        gobA.hp -= hero.damage;
        gobB.hp -= hero.damage;
        wiz.hp -= hero.damage;
    }

    
    // 修正点: 引数でキャラクター情報を受け取るように変更
    public static void EnemyAttack(Character hero, Character enemy) {
        System.out.println(enemy.name + enemy.suffix + "の攻撃");
        System.out.println(enemy.damage + "のダメージを受けた");
        hero.hp -= enemy.damage; // 勇者のHPを直接減らす
    }

    public static void skillAttack(Character hero, Character gobA, Character gobB, Character wiz, Skill skill) {
        System.out.println(hero.name + "の" + skill.name);
        System.out.println("敵全体に" + skill.damage + "のダメージを与えた");

        // 受け取ったキャラクターのHPを直接減らす
        gobA.hp -= skill.damage;
        gobB.hp -= skill.damage;
        wiz.hp -= skill.damage;
        hero.mp -= skill.mp;
    }
}
