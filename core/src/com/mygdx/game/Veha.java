package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Characters.Hero;


public class Veha {

    static int x;
    static int[] y;
    private static StringBuilder[] txt;


    public static void create() {
        if (txt == null) {
            txt = new StringBuilder[3];
            y = new int[3];
        }

        for (int i=0; i<3; i++) {

            boolean check;
            do {
                x = (int) (Math.random() * 8 + 1);
                y[i] = x;
                check = true;
                for (int s = 1; s < i + 1; s++) {
                    if (y[i] == y[i - s]) {
                        check = false;
                    }


                }
            } while (!check);



            if (x == 1 && !Hero.vehaTank) {
                txt[i] = new StringBuilder("Tank" + "\n" + "+25% damage" + "\n" + "from max hp");
            }

            if (x == 2) {
                txt[i] = new StringBuilder("Balance" + "\n" + "+5 dmg, +25 hp," + "\n " + "+5 armor, +10% lifesteal");
            }

            if (x == 3 && !Hero.vehaSmash) {
                txt[i] = new StringBuilder("Smash" + "\n" + "+25% damage");
            }

            if (x == 4 && !Hero.vehaReturn) {
                txt[i] = new StringBuilder("Counterattack" + "\n" + "deals damage" + "\n" + "equal to armor" + "\n" + "when taking damage");
            }
            //if (x == 5) {
            //    txt1 = new StringBuilder("Desolate" + "\n" + "each attack reduces" + "\n" + " the enemy's armor" + "\n" + "by 1");
            //}
            if (x == 5 && !Hero.vehaDomination) {
                txt[i] = new StringBuilder("Domination" + "\n" + "you get an armor" + "\n" + " equal to min damage");
            }
            if (x == 6 && !Hero.vehaCrushing) {
                txt[i] = new StringBuilder("Crushing" + "\n" + "than slower you attack," + "\n" + " the more damage you have");
            }
            if (x == 7 && !Hero.vehaSharpSword) {
                txt[i] = new StringBuilder("sharp sword" + "\n" + "gives a chance equal" + "\n" + "to the chance of a crit to strike" + "\n" + "a blow passing through armor");
            }
            if (x == 8 && !Hero.vehaLifeCollector) {
                txt[i] = new StringBuilder("Life Collector" + "\n" + "gives a second hp bar" + "\n" + "for stolen health");
            }
            if (txt[i] == null) {
                txt[i] = new StringBuilder("Balance" + "\n" + "+5 dmg, +25 hp," + "\n " + "+5 armor, +10% lifesteal");
                x = 2;
            }
        }
    }
    public static void render(SpriteBatch batch) {

        if (GameScreen.a == 1) {
            batch.begin();

            GameScreen.fontM24[4].draw(batch, String.valueOf(txt[0]), (GameScreen.WightScreen-(317*3))/4, Gdx.graphics.getHeight()*0.6f, 300, 1, false);
            GameScreen.fontM24[4].draw(batch, String.valueOf(txt[1]), ((GameScreen.WightScreen-(317*3))/4)*2+317, Gdx.graphics.getHeight()*0.6f, 300, 1, false);
            GameScreen.fontM24[4].draw(batch, String.valueOf(txt[2]), ((GameScreen.WightScreen-(317*3))/4)*3+317*2, Gdx.graphics.getHeight()*0.6f, 300, 1, false);

            batch.end();
        }




    }
}


