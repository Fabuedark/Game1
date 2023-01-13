package com.mygdx.game.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.GameScreen;


public class Monster extends GameCharaters {
    static float hp;
     int maxHP;
     float Dmg;
     float minDmg;
     float maxDmg;
     static float Armor;
     float attackSpeed;
    static float lifesteal;
     float x;
     float y;
    static float takeDamageMonsterAmount;
     private int pixelSizeForHPbar;
    private static GameScreen gameScreen;

    private TextureRegion[] regions;
    private Texture textureAttack;
    private Texture textureDeath;
    private float animationTimer;
    private float animationAttackTimer;
    private float animationDeathTimer;
    private float secOnFrame;
    private int frameIndex;
    static float DamageEffectTimer;
    boolean BeginDeath = false;

    public static float MonsterLvl;
    private float dist;
    private StringBuilder stringBuilderHP;


    private int MonsterType; //0 - скелет, 1 - горгулья, 2 - гоблин, 3 - гриб, 4 - жнец



    public float getMinDmg() {
        return minDmg;
    }

    public float getMaxDmg() {
        return maxDmg;
    }

    public static float getHp() {return hp;}

    public float getX() {return x;}

    public float getHP() {return hp;}

    public static GameScreen getGameScreen() {return gameScreen;}




    public Monster(GameScreen gameScreen) {
        RefactorMonster();
    }


    public void render(SpriteBatch batch) {

            if (this.DamageEffectTimer > 0.01 && hp >= 1) {
                batch.setColor(0.8f, 0.8f - this.DamageEffectTimer, 0.8f - this.DamageEffectTimer, 1);
            }
            else {
                batch.setColor(0.8f, 0.8f, 0.8f, 1);
            }

             if (hp < 1 && !BeginDeath) {
                 BeginDeath = true;
                 if (MonsterType == 1) {
                     y = Gdx.graphics.getHeight()*0.0985f;
                 }
                 if (MonsterType != 4) {
                     regions = new TextureRegion(textureDeath).split(450, 450)[0];
                     for (int i = 0; i <= 3; i++) {
                         regions[i].flip(true, false);
                     }
                 }
                 else {
                     regions = new TextureRegion(textureDeath).split(560, 372)[0];
                 }

                 secOnFrame = 0.1f;
                 animationTimer = 0;
             }

             if (dist > 200 || Hero.hp < 1 || (BeginDeath)) {

                 if (MonsterType != 4) {
                     if (!(BeginDeath && frameIndex == 3)) {
                         frameIndex = ((int) (animationTimer / secOnFrame) % regions.length);
                     }
                 }
                 else {
                     if (!(BeginDeath && frameIndex == 9)) {
                         frameIndex = ((int) (animationTimer / secOnFrame) % regions.length);
                     }
                 }
             }
             else {
                 frameIndex = ((int) (animationAttackTimer / (attackSpeed/8) ) % regions.length);
             }


            if (frameIndex >= regions.length | frameIndex < 0) {
                animationAttackTimer = 0;
                animationTimer = 0;
                attackTimer = 0;
                frameIndex = 0;
            }


            batch.draw(regions[frameIndex], this.x-145, this.y-145);

            if (hp >= 1) {
                if (MonsterType != 4) {
                    batch.setColor(0, 0, 0, 1);
                    batch.draw(hpline, this.x + 10 - 1, this.y + 180 - 1, 0, 0, 60 + 1, 18 + 3, 2, 1, 0, 0, 0, 114, 15, false, false);
                    batch.setColor(0.6f, 0, 0, 1);
                    batch.draw(this.hpline, this.x + 10, this.y + 180, 0, 0, this.hp / maxHP * 60, 18, 2, 1, 0, 0, 0, 114, 15, false, false);
                    batch.setColor(1, 1, 1, 1);
                    stringBuilderHP = new StringBuilder((int) hp + "/" + maxHP);
                    GameScreen.fontM24[0].draw(batch, String.valueOf((StringBuilder) stringBuilderHP), x + 10, y + 199, 120, 1, false);

                    GameScreen.fontM24[0].setColor(1,0,1, 10*DamageEffectTimer);
                    GameScreen.fontM24[0].draw(batch, String.valueOf((int)takeDamageMonsterAmount), x-625+50*DamageEffectTimer, y+250+50*DamageEffectTimer, GameScreen.WightScreen, 1, false);
                    GameScreen.fontM24[0].setColor(1,1,1,1);
                } else {
                    batch.setColor(0, 0, 0, 1);
                    batch.draw(hpline, this.x + 200 - 1, this.y + 100 - 1, 0, 0, 60 + 1, 18 + 3, 2, 1, 0, 0, 0, 114, 15, false, false);
                    batch.setColor(0.6f, 0, 0, 1);
                    batch.draw(this.hpline, this.x + 200, this.y + 100, 0, 0, this.hp / maxHP * 60, 18, 2, 1, 0, 0, 0, 114, 15, false, false);
                    batch.setColor(1, 1, 1, 1);
                    stringBuilderHP = new StringBuilder((int) hp + "/" + maxHP);
                    GameScreen.fontM24[0].draw(batch, String.valueOf((StringBuilder) stringBuilderHP), x + 200, y + 119, 120, 1, false);
                    GameScreen.fontM24[0].setColor(1,0,1, 10*DamageEffectTimer);
                    GameScreen.fontM24[0].draw(batch, String.valueOf((int)takeDamageMonsterAmount), x-425+50*DamageEffectTimer, y+150+50*DamageEffectTimer, GameScreen.WightScreen, 1, false);
                    GameScreen.fontM24[0].setColor(1,1,1,1);
                }

            }
    }

    @Override
    public void update(float dt) {
        dist = Math.abs(this.x - gameScreen.getHero().getX());
        animationTimer += dt;


            if (this.DamageEffectTimer >= 0) {
                this.DamageEffectTimer -= 0.02f;
            } else {
                this.DamageEffectTimer = 0.01f;
            }

        if (hp >= 1) {



            if (dist <= 200 && Hero.getHP() >= 1) {
                attackTimer += dt;
                animationAttackTimer += dt;
                if (MonsterType != 4) {
                    this.regions = new TextureRegion(textureAttack).split(450,450)[0];
                }
                else {
                    this.regions = new TextureRegion(textureAttack).split(560,372)[0];
                }




                if (MonsterType == 0) {
                    for (int i = 0; i <= 7; i++) {
                        regions[i].flip(true, false);
                    }
                    if ((frameIndex) == 7 && (attackTimer >= (attackSpeed / 8 * 7))) {
                        attackTimer = 0;
                        //Hero.takeDamage(((float) (((Math.random() * ((this.maxDmg - this.minDmg) + 1)) + this.minDmg))) + Dmg - gameScreen.getHero().Armor);
                        Hero.takeDamage(((float) ((((Math.random() * ((this.maxDmg - this.minDmg) + 1)) + this.minDmg))) + Dmg) * (1 - ((0.03f * gameScreen.getHero().Armor) / (1 + 0.03f * gameScreen.getHero().Armor))));
                    }
                }
                else {
                    if (MonsterType == 1) {
                        for (int i = 0; i <= 7; i++) {
                            regions[i].flip(true, false);
                        }
                        if ((frameIndex) == 7 && (attackTimer >= (attackSpeed / 8 * 6))) {
                            attackTimer = 0;
                            //Hero.takeDamage(((float) (((Math.random() * ((this.maxDmg - this.minDmg) + 1)) + this.minDmg))) + Dmg - gameScreen.getHero().Armor);
                            Hero.takeDamage(((float) ((((Math.random() * ((this.maxDmg - this.minDmg) + 1)) + this.minDmg))) + Dmg) * (1 - ((0.03f * gameScreen.getHero().Armor) / (1 + 0.03f * gameScreen.getHero().Armor))));
                        }
                    } else {
                        if (MonsterType == 2) {
                            for (int i = 0; i <= 7; i++) {
                                regions[i].flip(true, false);
                            }
                            if ((frameIndex) == 7 && (attackTimer >= (attackSpeed / 8 * 7))) {
                                attackTimer = 0;
                                //Hero.takeDamage(((float) (((Math.random() * ((this.maxDmg - this.minDmg) + 1)) + this.minDmg))) + Dmg - gameScreen.getHero().Armor);
                                if ((Math.random()*100+1<=20)) {
                                    Hero.takeDamage(((float) ((((Math.random() * ((this.maxDmg - this.minDmg) + 1)) + this.minDmg))) + Dmg) * (1 - ((0.03f * gameScreen.getHero().Armor) / (1 + 0.03f * gameScreen.getHero().Armor))) * 2);
                                }
                                else {
                                    Hero.takeDamage(((float) ((((Math.random() * ((this.maxDmg - this.minDmg) + 1)) + this.minDmg))) + Dmg) * (1 - ((0.03f * gameScreen.getHero().Armor) / (1 + 0.03f * gameScreen.getHero().Armor))));

                                }

                            }
                        }
                        else {
                            if (MonsterType == 3) {
                                for (int i = 0; i <= 7; i++) {
                                    regions[i].flip(true, false);
                                }
                                if ((frameIndex) == 7 && (attackTimer >= (attackSpeed / 8 * 7))) {
                                    attackTimer = 0;
                                    //Hero.takeDamage(((float) (((Math.random() * ((this.maxDmg - this.minDmg) + 1)) + this.minDmg))) + Dmg - gameScreen.getHero().Armor);
                                    Hero.takeDamage(((float) ((((Math.random() * ((this.maxDmg - this.minDmg) + 1)) + this.minDmg))) + Dmg) * (1 - ((0.03f * gameScreen.getHero().Armor) / (1 + 0.03f * gameScreen.getHero().Armor))));
                                }
                            }
                            else {
                                if (MonsterType == 4) {

                                    if ((frameIndex) == 5 && (attackTimer >= (attackSpeed / 8 * 5))) {
                                        attackTimer = 0;
                                        //Hero.takeDamage(((float) (((Math.random() * ((this.maxDmg - this.minDmg) + 1)) + this.minDmg))) + Dmg - gameScreen.getHero().Armor);
                                        Hero.takeDamage(((float) ((((Math.random() * ((this.maxDmg - this.minDmg) + 1)) + this.minDmg))) + Dmg) * (1 - ((0.03f * gameScreen.getHero().Armor) / (1 + 0.03f * gameScreen.getHero().Armor))));
                                    }
                                }
                            }
                        }
                    }
                }

            } else {
                this.x -= speed * Gdx.graphics.getWidth()/100 * dt;;
                if (!(frameIndex == 0)) {
                    animationAttackTimer = 0;
                }
                    }
                }
        else {
            animationDeathTimer += dt;
        }

        if ( ((Math.abs(dist) >= 200) || Hero.hp < 1) && hp >= 1) {
            if (MonsterType != 4) {
                regions = new TextureRegion(texture).split(450, 450)[0];
            }
            else {
                regions = new TextureRegion(texture).split(560, 372)[0];
            }
            if (MonsterType == 0) {
                for (int i = 0; i <= 3; i++) {
                    regions[i].flip(true, false);
                }
            }
            else {
                if (MonsterType != 4) {
                    for (int i = 0; i <= 7; i++) {
                        regions[i].flip(true, false);
                    }
                }
            }
        }
            }






    public static void takeDamage(float amount) {
        takeDamageMonsterAmount = amount;

        if (hp >= 1) {
            if (takeDamageMonsterAmount < 0) {takeDamageMonsterAmount=0;}
            if (Hero.vehaSmash) {
                takeDamageMonsterAmount *= 1.25f;
            }
            if (Hero.vehaTank) {
                takeDamageMonsterAmount += Hero.maxHP * 0.25f;
            }
            hp -= takeDamageMonsterAmount;

            Hero.hp += Hero.lifesteal * takeDamageMonsterAmount / 100;


            DamageEffectTimer += 0.35f;
            if (DamageEffectTimer > 0.7f) {
                DamageEffectTimer = 0.7f;

            }
        }
    }

    public void RefactorMonster() {
        BeginDeath = false;

       // MonsterType = 4;

        MonsterType = (int) (Math.random() * 5);
        if (MonsterType == 0) {
            texture = new Texture("Skeleton/SkeletonWalk.png");
            textureAttack = new Texture("Skeleton/SkeletonAttack.png");
            textureDeath = new Texture("Skeleton/SkeletonDeath.png");
            regions = new TextureRegion(texture).split(450, 450)[0];
            pixelSizeForHPbar = 450;

            for (int i = 0; i <= 3; i++) {
                regions[i].flip(true,false);
            }

            hpline = new Texture("hpline.png");
            x = GameScreen.WightScreen;
            y = Gdx.graphics.getHeight()*0.097f;
            speed = 5;
            gameScreen = gameScreen;

            Armor = (2 * ( ( MonsterLvl * MonsterLvl * 10 + 100)/ 100));
            Dmg = (2 * ( ( MonsterLvl * MonsterLvl * 10 + 100)/ 100));
            minDmg = (2 * ( ( MonsterLvl * MonsterLvl * 5 + 100)/ 100));
            maxDmg = (5 * ( ( MonsterLvl * MonsterLvl * 5 + 100)/ 100));
            maxHP = (int) (20 * ( ( MonsterLvl * MonsterLvl * 10 + 100)/ 100));
            hp = maxHP;
            lifesteal = 0;
            attackSpeed = 2.2f - (0.05f * MonsterLvl);
            if (attackSpeed < 0.4f) {
                attackSpeed = 0.4f;
            }
            secOnFrame = 0.2f;
            animationTimer = 0;
            animationAttackTimer = 0;
            animationDeathTimer = 0;
            attackTimer = 0;
            DamageEffectTimer = 0;
        }
        else {
            if (MonsterType == 1) {
                texture = new Texture("Eye/Flight.png");
                textureAttack = new Texture("Eye/Attack.png");
                textureDeath = new Texture("Eye/Death.png");

                regions = new TextureRegion(texture).split(450, 450)[0];
                pixelSizeForHPbar = 450;

                for (int i = 0; i <= 7; i++) {
                    regions[i].flip(true,false);
                }

                hpline = new Texture("hpline.png");
                x = GameScreen.WightScreen;
                y = 100;
                speed = 5;
                gameScreen = gameScreen;

                Armor =  (1 * ( ( MonsterLvl * MonsterLvl * 10 + 100)/ 100));
                Dmg =  (5 * ( ( MonsterLvl * MonsterLvl * 10 + 100)/ 100));
                minDmg = 0;
                maxDmg = 1;
                maxHP = (int) (15 * ( ( MonsterLvl * MonsterLvl * 10 + 100)/ 100));
                hp = maxHP;
                lifesteal = 0;
                attackSpeed = 1.7f - (0.06f * MonsterLvl);
                if (attackSpeed < 0.35f) {
                    attackSpeed = 0.35f;
                }
                secOnFrame = 0.2f;
                animationTimer = 0;
                animationAttackTimer = 0;
                animationDeathTimer = 0;
                attackTimer = 0;
                DamageEffectTimer = 0;
            }
            else {
                if (MonsterType == 2) {
                    texture = new Texture("Goblin/Run.png");
                    textureAttack = new Texture("Goblin/Attack.png");
                    textureDeath = new Texture("Goblin/Death.png");

                    regions = new TextureRegion(texture).split(450, 450)[0];
                    pixelSizeForHPbar = 450;

                    for (int i = 0; i <= 7; i++) {
                        regions[i].flip(true,false);
                    }

                    hpline = new Texture("hpline.png");
                    x = GameScreen.WightScreen;
                    y = Gdx.graphics.getHeight()*0.097f;
                    speed = 5;
                    gameScreen = gameScreen;

                    Armor =  (1 * ( ( MonsterLvl * MonsterLvl * 10 + 100)/ 100));
                    Dmg =  (5 * ( ( MonsterLvl * MonsterLvl * 5 + 100)/ 100));
                    minDmg = (2 * ( ( MonsterLvl * MonsterLvl * 5 + 100)/ 100));
                    maxDmg = (3 * ( ( MonsterLvl * MonsterLvl * 5 + 100)/ 100));
                    maxHP = (int) (15 * ( ( MonsterLvl * MonsterLvl * 10 + 100)/ 100));
                    hp = maxHP;
                    lifesteal = 0;
                    attackSpeed = 2.2f - (0.05f * MonsterLvl);
                    if (attackSpeed < 0.4f) {
                        attackSpeed = 0.4f;
                    }
                    secOnFrame = 0.2f;
                    animationTimer = 0;
                    animationAttackTimer = 0;
                    animationDeathTimer = 0;
                    attackTimer = 0;
                    DamageEffectTimer = 0;
                }
                else {
                    if (MonsterType == 3) {
                        texture = new Texture("Mushroom/Run.png");
                        textureAttack = new Texture("Mushroom/Attack.png");
                        textureDeath = new Texture("Mushroom/Death.png");

                        regions = new TextureRegion(texture).split(450, 450)[0];
                        pixelSizeForHPbar = 450;

                        for (int i = 0; i <= 7; i++) {
                            regions[i].flip(true,false);
                        }

                        hpline = new Texture("hpline.png");
                        x = GameScreen.WightScreen;
                        y = Gdx.graphics.getHeight()*0.097f;
                        speed = 4;
                        gameScreen = gameScreen;

                        Armor =  (4 * ( ( MonsterLvl * MonsterLvl * 10 + 100)/ 100));
                        Dmg =  (7 * ( ( MonsterLvl * MonsterLvl * 5 + 100)/ 100));
                        minDmg = (4 * ( ( MonsterLvl * MonsterLvl * 5 + 100)/ 100));
                        maxDmg = (6 * ( ( MonsterLvl * MonsterLvl * 5 + 100)/ 100));
                        maxHP = (int) (50 * ( ( MonsterLvl * MonsterLvl * 10 + 100)/ 100));
                        lifesteal = 20;
                        hp = maxHP;
                        attackSpeed = 3.7f - (0.03f * MonsterLvl);
                        if (attackSpeed < 0.5f) {
                            attackSpeed = 0.5f;
                        }
                        secOnFrame = 0.2f;
                        animationTimer = 0;
                        animationAttackTimer = 0;
                        animationDeathTimer = 0;
                        attackTimer = 0;
                        DamageEffectTimer = 0;
                    }

                 else {
                    if (MonsterType == 4) {
                        texture = new Texture("Bringer of death/Run.png");
                        textureAttack = new Texture("Bringer of death/Attack.png");
                        textureDeath = new Texture("Bringer of death/Death.png");

                        regions = new TextureRegion(texture).split(560, 372)[0];
                        pixelSizeForHPbar = 560;

                        hpline = new Texture("hpline.png");
                        x = GameScreen.WightScreen;
                        y = Gdx.graphics.getHeight() * 0.260f;
                        speed = 10;
                        gameScreen = gameScreen;

                        Armor = (1 * ((MonsterLvl * MonsterLvl * 10 + 100) / 100));
                        Dmg = (10 * ((MonsterLvl * MonsterLvl * 5 + 100) / 100));
                        minDmg = (0 * ((MonsterLvl * MonsterLvl * 5 + 100) / 100));
                        maxDmg = (0 * ((MonsterLvl * MonsterLvl * 5 + 100) / 100));
                        maxHP = (int) (40 * ((MonsterLvl * MonsterLvl * 10 + 100) / 100));
                        lifesteal = 10 * ((MonsterLvl * MonsterLvl + 100) / 100);
                        hp = maxHP;
                        attackSpeed = 3.0f - (0.04f * MonsterLvl);
                        if (attackSpeed < 0.5f) {
                            attackSpeed = 0.5f;
                        }
                        secOnFrame = 0.2f;
                        animationTimer = 0;
                        animationAttackTimer = 0;
                        animationDeathTimer = 0;
                        attackTimer = 0;
                        DamageEffectTimer = 0;
                    }
                 }
                }
            }
        }
    }

    public static void lvlUpMonster() {
        MonsterLvl += 0.25f;
    }

    public static void UpArmor(float amount) {
        Monster.Armor += amount;
    }
}
