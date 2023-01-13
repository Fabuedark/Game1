package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Characters.Hero;


public class TextonBuff {


    static int[] y;
    static int[] z;
    private static StringBuilder[] txt;
    private static int[] level;

    static int[] chanceArr = new int[] {50, 80, 90, 95, 100};






    public static int getLevel(int i) {return level[i];}




    public static void createCard() {

        if (txt == null) {
            txt = new StringBuilder[3];
            level = new int[3];
            y = new int[3];
            z = new int[3];
        }



        for (int i = 0; i<3; i++) {
            y[i] = (int) (Math.random() * 100 + 1);

            for (int j = 0; j < chanceArr.length; j++) {
                if ( y[i] <= chanceArr[j]) {
                    level[i] = j+1;
                    break;
                }
            }


/*
                y[i] = (int) (Math.random() * 8 + 1);
                for(int p = 0; p < i; p++){
                    if(y[i] == y[p] && i != p){
                        y[i] = (int) (Math.random() * 8 + 1);
                    }
                }

*/

            boolean check;
            do {
                y[i] = (int) (Math.random() * 8 + 1);
                check = true;
                for (int s = 1; s <= i; s++) {
                    if (y[i] == y[i - s]) {
                        check = false;
                        break;
                    }
                }
            } while (!check);



            if (y[i] == 1) {
                z[i] = (int) (Math.random() * 2 + 1);
                if (z[i] == 1) {
                    txt[i] = new StringBuilder("+" + 5 * level[i] + " " + "max hp");
                }
                if (z[i] == 2) {
                    txt[i] = new StringBuilder("+" + 2 * level[i] + "% " + "max hp");
                }
            }

            if (y[i] == 2) {
                z[i] = (int) (Math.random() * 5 + 3);
                if (z[i] == 3) {
                    txt[i] = new StringBuilder("+" + 1 * level[i] + " " + "dmg");
                }
                if (z[i] == 4) {
                    txt[i] = new StringBuilder("+" + 2 * level[i] + " " + "min dmg");
                }
                if (z[i] == 5) {
                    txt[i] = new StringBuilder("+" + 2 * level[i] + " " + "max dmg");
                }
                if (z[i] == 6) {
                    txt[i] = new StringBuilder("+" + level[i] + "%" + " " + "dmg");
                }
                if (z[i] == 7) {
                    txt[i] = new StringBuilder("+" + 5 * level[i] + " dmg" + "\n" + "-attack speed");
                }

            }

            if (y[i] == 3) {
                txt[i] = new StringBuilder("+" + 1 * level[i] + " " + "armor");
                z[i] = 8;
            }

            if (y[i] == 4) {
                txt[i] = new StringBuilder(20 * level[i] + "%" + " " + "heal");
                z[i] = 9;
            }


            if (y[i] == 5) {
                txt[i] = new StringBuilder("+attack speed");
                z[i] = 10;
            }

            if (y[i] == 6) {
                z[i] = (int) (Math.random() * 3 + 11);
                if (z[i] == 11) {
                    txt[i] = new StringBuilder("+" + 1 * level[i] + "% chance critical strike" + "\n" + "+" + String.format("%.2f", 0.05 * level[i]) + "X critical damage");
                }
                if (z[i] == 12 && Hero.getChanceCrit() < 100) {
                    txt[i] = new StringBuilder("+" + 2 * level[i] + "% chance critical strike");
                }
                if (z[i] == 13) {
                    txt[i] = new StringBuilder("+" + String.format("%.2f", 0.1 * level[i]) + "X critical damage");
                }

            }

            if (y[i] == 7) {
                txt[i] = new StringBuilder("+" + 2 * level[i] + "% Lifesteal");
                z[i] = 14;
            }


            if (y[i] == 8) {
                txt[i] = new StringBuilder("+" + 0.25f * level[i] + " desolate");
                z[i] = 15;
            }
        }






    }
    public static void render(SpriteBatch batch) {

            if (GameScreen.a == 1) {
            batch.begin();

                GameScreen.fontM24[level[0]-1].draw(batch, String.valueOf(txt[0]), (GameScreen.WightScreen-(317*3))/4, Gdx.graphics.getHeight()*0.6f, 300, 1, false);
                GameScreen.fontM24[level[1]-1].draw(batch, String.valueOf(txt[1]), ((GameScreen.WightScreen-(317*3))/4)*2+317, Gdx.graphics.getHeight()*0.6f, 300, 1, false);
                GameScreen.fontM24[level[2]-1].draw(batch, String.valueOf(txt[2]), ((GameScreen.WightScreen-(317*3))/4)*3+317*2, Gdx.graphics.getHeight()*0.6f, 300, 1, false);

            batch.end();
            }
    }
}
