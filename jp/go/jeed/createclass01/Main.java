package jp.go.jeed.createclass01;

import java.util.Scanner;

public class Main {

    static boolean gobADai = false;
    static boolean gobBDai = false;
    static boolean wizDai = false;
    static boolean heroDai = false;
    static boolean gameSet = false;

    public static void main(String[] args) {

        Skill hSkill = new Skill("断空剣", 70, 100);

        // --- キャラクターと武器の生成 ---
        Character hero = new Character("勇者", '\0', 100, 100, 30, hSkill); // ダメージを30に調整
        Character gobA = new Character("ゴブリン", 'A', 70, 100, 20, null);
        Character gobB = new Character("ゴブリン", 'B', 70, 100, 20, null);
        Character wiz = new Character("ウィザード", '\0', 100, 100, 30, null);

        // Weapon fSowrd = new Weapon("フレイムソード", 15);
        // Weapon iSowrd = new Weapon("アイスソード", 15);
        // Weapon club = new Weapon("こん棒", 15);
        // Weapon rod = new Weapon("ロッド", 15);
        // --- 敵が倒されたかを管理するカウンター ---
        int wizDaiCount = 0;
        int gobADaiCount = 0;
        int gobBDaiCount = 0;

        Scanner scan = new Scanner(System.in);
        String command = "";

        System.out.println("敵が現れた！");
        System.out.println(gobA.name + "A" + " HP: " + gobA.hp);
        System.out.println(gobB.name + "B" + " HP: " + gobB.hp);
        System.out.println(wiz.name + " HP: " + wiz.hp);
        System.out.println("--------------------");

        do {
            // --- 3体の敵がすべて倒されたかチェック ---
            if (gobADai && gobBDai && wizDai) {
                gameSet = true;
                break; // ループを抜ける
            }

            System.out.println("勇者は考えている: attack or run or skill");
            command = scan.nextLine();

            if (command.equals("attack") || command.equals("skill")) {

                if (command.equals("attack")) {
                    // --- 勇者の攻撃 ---
                    // 修正点: hero, gobA, gobB, wizを引数として渡す
                    Attack.heroAttack(hero, gobA, gobB, wiz);
                }
                if (command.equals("skill")) {

                    if (hero.mp < hSkill.mp) {
                        // スキルアタック不可
                        System.out.println("MPが足りない‥");
                        continue;
                    } else {
                        // スキルアタック
                        Attack.skillAttack(hero, gobA, gobB, wiz, hSkill);
                    }
                }

                // 攻撃後のHP表示
                if (!gobADai) { // 倒されていなければHP表示
                    System.out.println(gobA.name + "AのHP: " + Math.max(0, gobA.hp));
                }
                if (!gobBDai) {
                    System.out.println(gobB.name + "BのHP: " + Math.max(0, gobB.hp));
                }
                if (!wizDai) {
                    System.out.println(wiz.name + "のHP: " + Math.max(0, wiz.hp));
                }
                System.out.println("--------------------");

                // --- 敵が倒れたかどうかの判定 ---
                if (gobA.hp <= 0 && !gobADai) {
                    gobADaiCount = Character.Dai(gobADaiCount, gobA);
                    gobADai = true;
                }
                if (gobB.hp <= 0 && !gobBDai) {
                    gobBDaiCount = Character.Dai(gobBDaiCount, gobB);
                    gobBDai = true;
                }
                if (wiz.hp <= 0 && !wizDai) {
                    wizDaiCount = Character.Dai(wizDaiCount, wiz);
                    wizDai = true;
                }

                // --- 敵の攻撃ターン ---
                // 3体の敵がすべて倒されていなければ攻撃を受ける
                if (!gobADai || !gobBDai || !wizDai) {
                    System.out.println("敵の攻撃！");

                    // 修正点: 生きている敵だけが攻撃するように修正
                    if (!gobADai) {
                        Attack.EnemyAttack(hero, gobA);
                    }
                    if (!gobBDai) {
                        Attack.EnemyAttack(hero, gobB);
                    }
                    if (!wizDai) {
                        Attack.EnemyAttack(hero, wiz);
                    }

                    // 勇者が倒れたか判定
                    if (hero.hp <= 0) {
                        // 修正点: heroDaiメソッドの戻り値を受け取る
                        heroDai = Character.heroDai(hero);
                        gameSet = true;
                    } else {
                        System.out.println(hero.name + "のHP: " + hero.hp);
                        System.out.println("--------------------");
                    }
                }

            } else if (command.equals("run")) {
                Character.run(hero);
                System.out.println("あなたの負け");
                break;
            } else {
                // System.out.println("attackかrunを入力してください。");
            }
        } while (!gameSet);

        // --- ゲーム結果の表示 ---
        if (heroDai) {
            System.out.println("あなたの負け");
        } else if (gobADai && gobBDai && wizDai) {
            System.out.println("あなたの勝ち！");
        }
    }
}
