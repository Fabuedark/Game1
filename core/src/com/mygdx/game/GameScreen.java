package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Characters.CatTrader;
import com.mygdx.game.Characters.Hero;
import com.mygdx.game.Characters.Monster;


public class GameScreen {


    private static SpriteBatch batch;
    public static BitmapFont[] fontM24;
    private static Hero hero;
    private static Monster monster;
    private static CatTrader catTrader;
    private Texture img;
    private Texture Shop;
    private boolean veha_selected;
    private boolean money_received;
    private boolean RecreateVeha;

    //private Texture ava;
    //private Texture ramka;
    public static byte a;
    public static int roomNumber;
    public static Stage stageGS;
    public static Group ChoiceMenu;
    private Button Button[];
    
    
    public static Button Button1;
   public static Stage stage1;
   Skin skin1;
   Group RestartMenu;
    
    
    private Music music;
    public Sound ButtonSound;

    public static byte getA() {return a;}

    public static Hero getHero() {
        return hero;
    }

    public static Monster getMonster() {return monster;}

    public static CatTrader getCatTrader() {return catTrader;}

    public GameScreen(SpriteBatch batch) {
        this.batch = batch;
        create();
    }

    public static SpriteBatch getBatch() {
        return batch;
    }



//   static Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();
//    public static int WightScreen = sSize.width;
//   public static int HeightScreen = sSize.height;

    public static int WightScreen = Gdx.graphics.getWidth();
    public static int HeightScreen = Gdx.graphics.getHeight();



    public void create() {
        /*
        BitmapFont[] fontM24 = new BitmapFont[]{
                new BitmapFont(Gdx.files.internal("font24.fnt")),
                new BitmapFont(Gdx.files.internal("font24_uncommon.fnt")),
                new BitmapFont(Gdx.files.internal("font24_rare.fnt")),
                new BitmapFont(Gdx.files.internal("font24_mythic.fnt")),
                new BitmapFont(Gdx.files.internal("font24_legendary.fnt"))
        };
        */
        fontM24 = new BitmapFont[5];
        fontM24[0] = new BitmapFont(Gdx.files.internal("font24.fnt"));
        fontM24[1] = new BitmapFont(Gdx.files.internal("font24_uncommon.fnt"));
        fontM24[2] = new BitmapFont(Gdx.files.internal("font24_rare.fnt"));
        fontM24[3] = new BitmapFont(Gdx.files.internal("font24_mythic.fnt"));
        fontM24[4] = new BitmapFont(Gdx.files.internal("font24_legendary.fnt"));





        //ava = new Texture("knight_Icon.png");
       //ramka = new Texture("ramka.png");

        img = new Texture("Forest.png");
        Shop = new Texture("Shop2.png");
        ButtonSound = Gdx.audio.newSound(Gdx.files.internal("button_sound.mp3"));
        music = Gdx.audio.newMusic(Gdx.files.internal("lmeruqgfxdsi.mp3"));
        music.setLooping(true);
        music.setVolume(0.3f);
        music.play();
        hero = new Hero(this);
        veha_selected = false;
        monster = new Monster(Monster.getGameScreen());


        money_received = false;
        roomNumber = 1;
        stageGS = new Stage();
        Skin skin = new Skin();
        skin.add("continue_button.png", new Texture("continue_button.png"));
        Button.ButtonStyle ButtonStyle = new Button.ButtonStyle();
        ButtonStyle.up = skin.getDrawable("continue_button.png");
        Button[] Button = new Button[] {new Button(ButtonStyle), new Button(ButtonStyle), new Button(ButtonStyle)};


        ChoiceMenu = new Group();
        //for (int i=0; i<3; i++) {
           // final int finalI = i;

        for (int i=0; i<3; i++) {
            final int finalI = i;
            Button[i].addListener(new ClickListener() {
                                      @Override
                                      public void clicked(InputEvent event, float x, float y) {
                                          if (veha_selected) {
                                              a = 0;
                                              if (TextonBuff.z[finalI] == 1) {
                                                  Hero.UpHpMax(5 * TextonBuff.getLevel(finalI));
                                              }
                                              if (TextonBuff.z[finalI] == 2) {
                                                  Hero.UpHpMax((int)Math.ceil(Hero.getMaxHP()/100 * 2 *TextonBuff.getLevel(finalI)));
                                              }
                                              if (TextonBuff.z[finalI] == 3) {
                                                  Hero.UpDmg(1 * TextonBuff.getLevel(finalI));
                                                  if (Hero.vehaDomination){
                                                      Hero.UpArmor(1 * TextonBuff.getLevel(finalI));
                                                  }
                                              }
                                              if (TextonBuff.z[finalI] == 4) {
                                                  Hero.UpMinDmg(2 * TextonBuff.getLevel(finalI));
                                                  if (Hero.vehaDomination){
                                                      Hero.UpArmor(2 * TextonBuff.getLevel(finalI));
                                                  }
                                              }
                                              if (TextonBuff.z[finalI] == 5) {
                                                  Hero.UpMaxDmg(2 * TextonBuff.getLevel(finalI));
                                              }
                                              if (TextonBuff.z[finalI] == 6) {
                                                  Hero.UpDmg((int)Math.ceil(Hero.getMinDmg()/100 * TextonBuff.getLevel(finalI)));
                                                  if (Hero.vehaDomination){
                                                      Hero.UpArmor((int)Math.ceil(Hero.getMinDmg()/100 * TextonBuff.getLevel(finalI)));
                                                  }
                                              }
                                              if (TextonBuff.z[finalI] == 7) {
                                                  Hero.UpDmg(5 * TextonBuff.getLevel(finalI));
                                                  Hero.UpAttackSpeed(-1f * TextonBuff.getLevel(finalI));
                                                  if (Hero.vehaDomination){
                                                      Hero.UpArmor(5 * TextonBuff.getLevel(finalI));
                                                  }
                                              }
                                              if (TextonBuff.z[finalI] == 8) {
                                                  Hero.UpArmor(1 * TextonBuff.getLevel(finalI));
                                              }
                                              if (TextonBuff.z[finalI] == 9) {
                                                  Hero.UpHeal(20 * TextonBuff.getLevel(finalI));
                                              }
                                              if (TextonBuff.z[finalI] == 10) {
                                                  Hero.UpAttackSpeed(TextonBuff.getLevel(finalI));
                                              }
                                              if (TextonBuff.z[finalI] == 11) {
                                                  Hero.UpCrit(1 * TextonBuff.getLevel(finalI), 0.05f * TextonBuff.getLevel(finalI));
                                              }
                                              if (TextonBuff.z[finalI] == 12) {
                                                  Hero.UpCrit(2 * TextonBuff.getLevel(finalI), 0);
                                              }
                                              if (TextonBuff.z[finalI] == 13) {
                                                  Hero.UpCrit(0, 0.1f * TextonBuff.getLevel(finalI));
                                              }
                                              if (TextonBuff.z[finalI] == 14) {
                                                  Hero.UpLifesteal(2 * TextonBuff.getLevel(finalI));
                                              }
                                              if (TextonBuff.z[finalI] == 15) {
                                                  Hero.UpDesolate(0.25f * TextonBuff.getLevel(finalI));
                                              }
                                              //monster = new Monster();
                                              TextonBuff.createCard();
                                              Monster.lvlUpMonster();
                                          }
                                          else {
                                              a = 0;
                                              if (Veha.y[finalI] == 1) {
                                                  Hero.vehaTank = true;
                                                  Hero.UpHpMax(20);
                                                  Hero.UpHeal(100);
                                                  if (Hero.vehaStr == null) {
                                                      Hero.vehaStr = "Tank";
                                                  }
                                              }
                                              if (Veha.y[finalI] == 2) {
                                                  Hero.UpDmg(5);
                                                  Hero.UpHpMax(25);
                                                  Hero.UpArmor(5);
                                                  Hero.UpLifesteal(10);
                                                  Hero.UpHeal(100);
                                                  if (Hero.vehaStr == null) {
                                                      Hero.vehaStr = "Balance";
                                                  }

                                              }

                                              if (Veha.y[finalI] == 3) {
                                                  Hero.vehaSmash = true;
                                                  Hero.UpDmg(10);
                                                  if (Hero.vehaStr == null) {
                                                      Hero.vehaStr = "Smash";
                                                  }
                                              }
                                              if (Veha.y[finalI] == 4) {
                                                  Hero.vehaReturn = true;
                                                  Hero.UpArmor(10);
                                                  if (Hero.vehaStr == null) {
                                                      Hero.vehaStr = "Return";
                                                  }
                                              }
                                              if (Veha.y[finalI] == 5) {
                                                  Hero.vehaDomination = true;
                                                  Hero.UpArmor((Hero.getMinDmg()));
                                                  if (Hero.vehaStr == null) {
                                                      Hero.vehaStr = "Domination";
                                                  }
                                              }
                                              if (Veha.y[finalI] == 6) {
                                                  Hero.vehaCrushing = true;
                                                  if (Hero.vehaStr == null) {
                                                      Hero.vehaStr = "Crushing";
                                                  }
                                              }
                                              if (Veha.y[finalI] == 7) {
                                                  Hero.vehaSharpSword = true;
                                                  if (Hero.vehaStr == null) {
                                                      Hero.vehaStr = "Sharp Sword";
                                                      Hero.UpCrit(10,0.8f);
                                                  }
                                              }
                                              if (Veha.y[finalI] == 8) {
                                                  Hero.vehaLifeCollector = true;
                                                  Hero.UpLifesteal(10);
                                                  if (Hero.vehaStr == null) {
                                                      Hero.vehaStr = "Life Collector";
                                                  }
                                              }
                                              veha_selected = true;
                                          }
                                          RecreateVeha = true;
                                          ButtonSound.play(0.3f);
                                      }
                                  }
            );
        }

            ChoiceMenu.addActor(Button[0]);
            ChoiceMenu.addActor(Button[1]);
            ChoiceMenu.addActor(Button[2]);
      //  }


       // stageGS.addActor(ChoiceMenu);

        //Button[1].setPosition(380,0);
        //Button[2].setPosition(760,0);
        //ChoiceMenu.setPosition(75, 200);

        Button[1].setPosition((GameScreen.WightScreen-(317*3))/4+317,0);
        Button[2].setPosition((GameScreen.WightScreen-(317*3))/4*2+317*2,0);
        ChoiceMenu.setPosition((GameScreen.WightScreen-(317*3))/4+10, HeightScreen*0.27f);

        Gdx.input.setInputProcessor(stageGS);
    }

    public void render() {

        float dt = Gdx.graphics.getDeltaTime();

        update(dt);
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        batch.setColor(1, 1, 1, 1);
        batch.draw(img, -75, 0,WightScreen+75,HeightScreen);
        //batch.draw(img, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        if (roomNumber%20 == 0 && roomNumber != 0) {
            batch.setColor(0.7f, 0.7f, 0.7f, 1);
            batch.draw(Shop, WightScreen-306, HeightScreen*0.097f);
        }

       //batch.draw(ramka, -40, 650, 0, 0, 280, 120, 2, 1, 0, 0, 0, 120, 66, false, false);
        //batch.draw(ava, 0, 670);








        if (!((roomNumber)%20 == 0)) {
            monster.render(batch);
        }
            hero.render(batch);

        if ((roomNumber-1)%20 == 0 && roomNumber != 1) {
            batch.setColor(0.7f, 0.7f, 0.7f, 1);
            batch.draw(Shop, 0, HeightScreen*0.097f, 162, 223, 144, 281,162,223,true,false);
        }



        if (Hero.PlayerControl) {
            catTrader.render(batch);
        }

        batch.setColor(1,1,1,1);
        batch.end();

        if (a == 1) {
            UpgradeStats.render();
            if (veha_selected) {
                TextonBuff.render(batch);
            } else {
                Veha.render(batch);
            }

            stageGS.draw();
        }

    }

    public void update(float dt) {
        stageGS.act(dt);



        if ((a == 1) && (veha_selected)) {
            ChoiceMenu.setVisible(true);
            TextonBuff.render(batch);
            if (money_received == false) {
                Hero.money += Math.random()*2+2;
                money_received = true;
            }
        }

         if (a == 0) {
            hero.update(dt);
            monster.update(dt);
            if (Hero.PlayerControl) {
                catTrader.update(dt);
            }
            money_received = false;
            ChoiceMenu.setVisible(false);
            if (getMonster().getHP() <= 1 && Hero.getA1() == 0) {
                a = 1;
                Hero.setA1();
            }
        }

         if (!veha_selected) {
             a = 1;
             ChoiceMenu.setVisible(true);
             Veha.render(batch);
         }

        if ((roomNumber-1) % 100 == 0 && roomNumber !=1 && RecreateVeha && a == 1) {
            RecreateVeha = false;
            Veha.create();
            veha_selected = false;

        }
    }

    public static void GameOver() {
    hero = new Hero(Hero.getGameScreen());
    Monster.MonsterLvl = 0;
    monster = new Monster(Monster.getGameScreen());
    roomNumber = 1;

    }

    // public static void CreateMonster() {monster = new Monster();}
     public static void CreateCatTrader() {catTrader = new CatTrader();}

}
