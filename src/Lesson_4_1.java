import com.sun.source.tree.BreakTree;

import java.util.Arrays;
import java.util.Random;

public class Lesson_4_1 {
    Random rand = new Random();

    public static int bossHealth = 2500;
    public static int bossDamage = 50;
    public static String bossDefence ;
    public static int[] heroesHealth = {270, 260, 250, 250, 250};
    public static int[] heroesDamage = {20, 15, 10, 0, 10};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic", "Thor"};
    public static int roundNumber = 1;



    public static void main(String[] args) {
        printStatistics();

        while (!isGameOver()) {
            playRound();
        }
    }

    public static boolean isGameOver() {
        if (bossHealth <= 0) {
            System.out.println("Heroes win!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss win!!!");
            return true;
        }
        return false;
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefence = heroesAttackType[randomIndex];

    }

    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        bossAttack();
        heroesAttack();
        medicBuff();
        printStatistics();
    }

    public static void bossAttack() {
        int indexThor = Arrays.asList(heroesAttackType).indexOf("Thor");

        Random random = new Random();
        boolean stunning = random.nextBoolean();

        for (int i = 0; i < heroesHealth.length; i++) {
            int damage = bossDamage;

                if (heroesHealth[i] > 0) {
                        if (heroesHealth[i] - damage < 0) {
                            heroesHealth[i] = 0;
                        } else {
                            heroesHealth[i] = heroesHealth[i] - damage;
                        }
                }

        }
    }

    public static void heroesAttack() {

        for (int i = 0; i < heroesDamage.length; i++) {

            if (heroesHealth[i] > 0 && bossHealth > 0) {

                int damage = heroesDamage[i];
                if (heroesAttackType[i] == bossDefence) {

                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                    damage = damage * coeff;
                    System.out.println("Critical damage: " + damage);

                }
                if (bossHealth - damage < 0) {
                    bossHealth = 0;

                } else {
                    bossHealth = bossHealth - damage;

                }
            }
        }
    }

    public static void medicBuff() {

        int indexMedic = Arrays.asList(heroesAttackType).indexOf("Medic");

        Random random = new Random();
        int randomRestorationOfHeroesHealth = random.nextInt(150) + 25;

        for (int i = 0; i < heroesHealth.length; i++) {
            if (i != indexMedic
                            && heroesHealth[i] > 0
                            && heroesHealth[i] < 100
                            && heroesHealth[indexMedic] > 0)
            {
                heroesHealth[i] += randomRestorationOfHeroesHealth;
                System.out.println("Medic restored the " + heroesAttackType[i] + "for " + randomRestorationOfHeroesHealth);
                break;
            }
        }
    }




    public static void printStatistics() {
        System.out.println("ROUND " + roundNumber + " ---------------");
        /*String defence;
        if (bossDefence == null) {
            defence = "No defence";
        } else {
            defence = bossDefence;
        }*/
        System.out.println("BOSS health: " + bossHealth + " damage: " + bossDamage
                + " defence: " + (bossDefence == null ? "No defence" : bossDefence));
        for (int i = 0; i < heroesAttackType.length; i++) {
            System.out.println(heroesAttackType[i] +
                    " health: " + heroesHealth[i] +
                    " damage: " + (heroesDamage[i] == 0 ? null : heroesDamage[i]));
        }
    }
}

