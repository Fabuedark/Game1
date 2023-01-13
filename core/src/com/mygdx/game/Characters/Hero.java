package com.mygdx.game.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.GameScreen;
import com.mygdx.game.MainMenu;
import com.mygdx.game.MyGdxGame;


public class Hero extends GameCharaters {



    public static boolean vehaSmash;
    public static boolean vehaTank;
    public static boolean vehaReturn;
    public static boolean vehaDesolate;
    public static boolean vehaDomination;
    public static boolean vehaCrushing;
    public static boolean vehaSharpSword;
    public static boolean vehaLifeCollector;
        public static float LifeCollectorHP;

    static int maxHP;
    static float hp;
    //static float Dmg;
    static float minDmg;
    static float maxDmg;
    static float attackSpeed;
    static float Armor;
    static float chanceCrit;
    static float CritX;
    static float x;
    static float y;
    static float lifesteal;
    static int a1;
    static float CrushX;
    static float Desolate;
    static float takeDamageHeroAmount;

    public static int money;
    private Sound attackSound;
    private Sound critSound;

    private TextureRegion[] regions;
    private Texture textureRun;
    private Texture textureAttack;
    private Texture textureDeath;
    private float animationTimer;
    private float animationAttackTimer;
    private float secOnFrame;
    private int frameIndex;
    static float DamageEffectTimer;
    boolean BeginDeath = false;

    private float dist;
    private boolean HeroLookRight;
    public static boolean PlayerControl;
    private boolean CritProc;
    private StringBuilder stringBuilder;
    private StringBuilder stringBuilderInfo;
    private StringBuilder stringBuilderHP;

    public static String vehaStr;


    public static Stage stage;
    public static Group RestartMenu;


    public static float getX() {return x;}

    public float getY() {return y;}

    //public static float getDmg() {return Dmg;}

    public float getMaxDmg() {return maxDmg;}

    public static float getMinDmg() {return minDmg;}

    public float getArmor() {return Armor;}

    public static float getMaxHP() {return maxHP;}

    public static float getLifesteal() {return lifesteal;}

    public static int getA1() {return a1;}

    public static float getHP() {return hp;}

    public static float getChanceCrit() {return chanceCrit;}
    public static GameScreen getGameScreen() {return gameScreen;}

    public Hero(GameScreen gameScreen) {


        this.gameScreen = gameScreen;
        attackSound = Gdx.audio.newSound(Gdx.files.internal("attack_sound.mp3"));
        critSound = Gdx.audio.newSound(Gdx.files.internal("crit_sound.mp3"));
        this.texture = new Texture("Hero/HeroIdle.png");
        this.textureRun = new Texture(Gdx.files.internal("Hero/HeroRun.png"));
        this.textureAttack = new Texture("Hero/HeroAttack.png");
        this.textureDeath = new Texture("Hero/HeroDeath.png");






        this.hpline = new Texture("hpline.png");
        this.x = 200;
        this.y = Gdx.graphics.getHeight()*0.097f;
        this.maxHP = 1;
        this.hp = maxHP;
        this.speed = 12;
        this.Armor = 2;
        //this.Dmg = 0;
        this.minDmg = 6;
        this.maxDmg = 12;
        lifesteal = 10;
        Desolate = 0;
        chanceCrit = 10;
        CritX = 1.2f;
        this.attackSpeed = 1.7f;
        this.secOnFrame = 0.2f;
        HeroLookRight = true;
        PlayerControl = false;
        vehaSmash = false;
        vehaTank = false;
        vehaReturn = false;
        vehaDesolate = false;
        vehaDomination = false;
        vehaSharpSword = false;
        vehaLifeCollector = false;






    }



    public void render(SpriteBatch batch) {

        if (this.DamageEffectTimer > 0.01) {
            batch.setColor(0.7f, 0.7f - this.DamageEffectTimer, 0.7f - this.DamageEffectTimer, 1);
        } else {
            batch.setColor(0.7f, 0.7f, 0.7f, 1);
        }

        if (hp > maxHP) {
            if (vehaLifeCollector) {
                LifeCollectorHP += hp - maxHP;
            }
            hp = maxHP;
        }
        if (minDmg > maxDmg) {
            float asdf = maxDmg;
            maxDmg = minDmg;
            minDmg = asdf;
        }

        if (hp < 1 && !BeginDeath) {
            BeginDeath = true;
            regions = new TextureRegion(textureDeath).split(560, 560)[0];
            secOnFrame = 0.15f;
            animationTimer = 0;
        }


        if (dist > 200 || Monster.getHp() < 1 || (BeginDeath)) {
            if (!(BeginDeath && frameIndex == 8)) {
                frameIndex = (int) (animationTimer / secOnFrame) % regions.length;
            }
            else {
                //stage.draw();
                GameScreen.GameOver();
            }

        } else {
            frameIndex = (int) (animationAttackTimer / (attackSpeed / 6)) % regions.length;
        }

        regions[frameIndex].flip(!HeroLookRight, false);




        batch.draw(regions[frameIndex], x - 220, y - 220);
        batch.setColor(0, 0, 0, 1);
        if (hp >= 1) {
            batch.draw(hpline, x - 1, y + 180 - 1, 0, 0, 60 + 1, 18 + 3, 2, 1, 0, 0, 0, maxHP, 15, false, false);
            batch.setColor(0.6f, 0, 0, 1);

            batch.draw(hpline, x, y + 180, 0, 0, this.hp / maxHP * 60, 18, 2, 1, 0, 0, 0, 114, 15, false, false);
        }



        if (vehaLifeCollector && hp >= 1) {
            batch.setColor(0, 0, 0, 1);
            batch.draw(hpline, x - 1, y + 210 - 1, 0, 0, 60 + 1, 18 + 3, 2, 1, 0, 0, 0, 60, 15, false, false);
            batch.setColor(0, 0.5f, 0, 1);
            batch.draw(hpline, x - 1, y + 210, 0, 0, 60, 18, 2, 1, 0, 0, 0, 60, 15, false, false);
            GameScreen.fontM24[0].draw(batch, String.valueOf((int) LifeCollectorHP), x, y + 229, 120, 1, false);
        }
        batch.setColor(0.7f, 0.7f, 0.7f, 1);


        stringBuilder = new StringBuilder("Health: " + maxHP + "   Armor: " + (int) Armor + "   Damage: " + (int) (minDmg) + "-" + (int) (maxDmg) + "   Crit chance: " + (int) chanceCrit + "%   crit dmg: " + String.format("%.2f", CritX) + "   lifesteal: " + (int) lifesteal + "%   attack speed: " + String.format("%.2f", attackSpeed) + "\n" + "Desolate: " + String.format("%.2f", Desolate));
        stringBuilderInfo = new StringBuilder("Money: " + money + "\n" + "Room: " + GameScreen.roomNumber);
        stringBuilderHP = new StringBuilder((int) hp + "/" + maxHP);

        GameScreen.fontM24[0].draw(batch, String.valueOf((StringBuilder) stringBuilder), 0, Gdx.graphics.getHeight() * 0.97f, GameScreen.WightScreen, 1, false);
        if (!(vehaStr == null)) {
            GameScreen.fontM24[0].draw(batch, vehaStr, 0, 40, 200, 1, false);
        }
        GameScreen.fontM24[0].draw(batch, String.valueOf((StringBuilder) stringBuilderInfo), 20, Gdx.graphics.getHeight() * 0.93f, 200, 1, false);
        if (hp >= 1) {
            GameScreen.fontM24[0].draw(batch, String.valueOf((StringBuilder) stringBuilderHP), x, y + 199, 120, 1, false);
        }

        GameScreen.fontM24[0].setColor(1,0,0, 10*DamageEffectTimer);
        GameScreen.fontM24[0].draw(batch, String.valueOf((int)takeDamageHeroAmount), x-750+50*DamageEffectTimer, y+250-50*DamageEffectTimer, GameScreen.WightScreen, 1, false);
        GameScreen.fontM24[0].setColor(1,1,1,1);
        batch.setColor(1, 1, 1, 1);

    }

    @Override
    public void update(float dt) {
        //stage.act(dt);

        dist = Math.abs(this.x - GameScreen.getMonster().getX());
        animationTimer += dt;


        if (attackSpeed < 0.3f) {
            attackSpeed = 0.3f;
        }

        if (chanceCrit > 100) {
            chanceCrit = 100;
        }

        if (this.DamageEffectTimer >= 0) {
            this.DamageEffectTimer -= 0.02f;
        } else {
            this.DamageEffectTimer = 0.01f;
        }


if (!BeginDeath) {
    regions = new TextureRegion(textureRun).split(560, 560)[0];
}






        if (PlayerControl) {
            regions = new TextureRegion(texture).split(560, 560)[0];
            if ((Gdx.input.isKeyPressed(Input.Keys.D)) || (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) || (Gdx.input.isTouched(0) && Gdx.input.getX() >= Gdx.graphics.getWidth()/2)) {
                HeroLookRight = true;
                regions = new TextureRegion(textureRun).split(560, 560)[0];
                x += speed * Gdx.graphics.getWidth()/100 * dt;
            }
            if ((Gdx.input.isKeyPressed(Input.Keys.A)) || (Gdx.input.isKeyPressed(Input.Keys.LEFT)) || (Gdx.input.isTouched(0) && Gdx.input.getX() < Gdx.graphics.getWidth()/2)) {
                HeroLookRight = false;
                regions = new TextureRegion(textureRun).split(560, 560)[0];
                x -= speed * Gdx.graphics.getWidth()/100 * dt;
            }


            if ((!Gdx.input.isKeyPressed(Input.Keys.D)) && (!Gdx.input.isKeyPressed(Input.Keys.A)) && (!Gdx.input.isKeyPressed(Input.Keys.LEFT)) && (!Gdx.input.isKeyPressed(Input.Keys.RIGHT))) {

                HeroLookRight = true;
            }
            if ((Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.A)) ||
                    (Gdx.input.isKeyPressed(Input.Keys.D) && Gdx.input.isKeyPressed(Input.Keys.LEFT)) ||
                            (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && Gdx.input.isKeyPressed(Input.Keys.LEFT)) ||
                            (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && Gdx.input.isKeyPressed(Input.Keys.A))) {
                regions = new TextureRegion(texture).split(560, 560)[0];
            }
        }
        else {
            if ( ((dist>=200) || Monster.getHp() < 1) && hp >= 1) {
                HeroLookRight = true;
                x += speed * Gdx.graphics.getWidth()/100 * dt;
            }
        }




      if (x > GameScreen.WightScreen) {
          x = 0;
          GameScreen.roomNumber += 1;
          if (GameScreen.roomNumber % 20 != 0) {
              PlayerControl = false;
              GameScreen.getMonster().RefactorMonster();
              a1 = 0;
          }
          else {
              PlayerControl = true;
              if (GameScreen.getCatTrader() == null) {
                  GameScreen.CreateCatTrader();
              }
              else {
                  GameScreen.getCatTrader().refactorCatTrader();
              }

          }


      }



        if (dist <= 200 && Monster.getHp()>=1 && hp>=1) {
            attackTimer += dt;
            animationAttackTimer += dt;
            HeroLookRight = true;
            regions = new TextureRegion(textureAttack).split(560,560)[0];

            if ((frameIndex == 3) && (attackTimer>=(attackSpeed/6*4))) {
                if (!(Math.random()*100+1<=chanceCrit)) {
                    CritProc = false;
                    critSound.stop();
                    attackSound.stop();
                    attackSound.play();

                }
                else {
                    CritProc = true;
                    attackSound.stop();
                    critSound.stop();
                    critSound.play();
                }

            }
            if ((frameIndex) == 4 && (attackTimer>=(attackSpeed/6*5))) {

                attackTimer = 0;
                if (vehaCrushing && attackSpeed > 1.5f) {
                    CrushX = attackSpeed * 2f;
                }
                else {
                    CrushX = 1;
                }

                Monster.UpArmor( -1 * Hero.Desolate);

                if (vehaSharpSword) {
                    if ((Math.random() * 100 + 1 <= chanceCrit)) {
                        if (CritProc) {
                            Monster.takeDamage(((float) ((((((Math.random() * ((this.maxDmg - this.minDmg) + 1)) + this.minDmg)))) * CritX) * CrushX));
                        } else {
                            Monster.takeDamage(((float) ((((Math.random() * ((this.maxDmg - this.minDmg) + 1)) + this.minDmg))) * CrushX));
                        }
                    } else {
                        if (CritProc) {
                            Monster.takeDamage(((float) ((((((Math.random() * ((this.maxDmg - this.minDmg) + 1)) + this.minDmg)))) * CritX) * (1 - ((0.03f * gameScreen.getMonster().Armor) / (Math.abs(1 + 0.03f * gameScreen.getMonster().Armor))))) * CrushX);
                        } else {
                            Monster.takeDamage(((float) ((((Math.random() * ((this.maxDmg - this.minDmg) + 1)) + this.minDmg))) * (1 - ((0.03f * gameScreen.getMonster().Armor) / (Math.abs(1 + 0.03f * gameScreen.getMonster().Armor))))) * CrushX);
                        }

                    }
                }
                else {
                    if (CritProc) {
                        Monster.takeDamage(((float) ((((((Math.random() * ((this.maxDmg - this.minDmg) + 1)) + this.minDmg)))) * CritX) * (1 - ((0.03f * gameScreen.getMonster().Armor) / (Math.abs(1 + 0.03f * gameScreen.getMonster().Armor))))) * CrushX);
                    } else {
                        Monster.takeDamage(((float) ((((Math.random() * ((this.maxDmg - this.minDmg) + 1)) + this.minDmg))) * (1 - ((0.03f * gameScreen.getMonster().Armor) / (Math.abs(1 + 0.03f * gameScreen.getMonster().Armor))))) * CrushX);
                    }
                }
            }
        }
        else {
            if (!(frameIndex == 0)) {
                animationAttackTimer = 0;
            }
        }

    }




    public static void takeDamage(float amount) {




        takeDamageHeroAmount = amount;
        if (takeDamageHeroAmount < 0) {takeDamageHeroAmount=0;}
        if (!vehaLifeCollector) {
            hp -= takeDamageHeroAmount;

        }
        else {
            if (LifeCollectorHP > 0 && LifeCollectorHP >= takeDamageHeroAmount) {
                LifeCollectorHP -= takeDamageHeroAmount;
            }
            else {
                takeDamageHeroAmount -= LifeCollectorHP;
                LifeCollectorHP = 0;
                hp -= takeDamageHeroAmount;
            }
        }


        if (vehaReturn) {
            Monster.takeDamage(Hero.Armor);
        }

        Monster.hp += Monster.lifesteal * takeDamageHeroAmount / 100;

        DamageEffectTimer += 0.35f;
        if (DamageEffectTimer > 0.7f) {
            DamageEffectTimer = 0.7f;

       }
    }


    public static void setA1() {
        a1++;
    }

    public static void UpHpMax(int amount) {
       Hero.maxHP += amount;
    }

    public static void UpDmg(int amount) {
        Hero.minDmg += amount;
       Hero.maxDmg += amount;
    }

    public static void UpMinDmg(int amount) {
       minDmg += amount;
    }

    public static void UpMaxDmg(int amount) {
        maxDmg += amount;
    }

    public static void UpArmor(float amount) {
        Hero.Armor += amount;
    }

    public static void UpDesolate(float amount) {
        Hero.Desolate += amount;
    }

    public static void UpHeal(int amount) {
        hp += (float) (amount*maxHP/100);
    }

    public static void UpAttackSpeed(float amount) {
        attackSpeed = attackSpeed * (((100-amount*2)/100));
    }

    public static void UpCrit (float chance, float X) {
        chanceCrit += chance;
        CritX += X;
    }
    public static void UpLifesteal (float amount) {
        lifesteal += amount;
    }
}

