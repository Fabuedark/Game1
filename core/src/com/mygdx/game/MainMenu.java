package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenu {

    public static Stage stage;
    public static Group Menu;
    private static Texture FonMenu;

    public static void create() {
        float dt = Gdx.graphics.getDeltaTime();
        FonMenu = new Texture("fonMenu1.jpg");
        stage = GameScreen.stageGS;
        update(dt);
        Skin skin = new Skin();
        skin.add("continue_button.png", new Texture("continue_button.png"));
        Button.ButtonStyle ButtonStyle = new Button.ButtonStyle();
        ButtonStyle.up = skin.getDrawable("continue_button.png");

        final Button [] Button = new Button[] {new Button(ButtonStyle)};
        Menu = new Group();

        Button[0].addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {


                                    Menu.removeActor(Button[0]);
                                    GameScreen.stageGS.addActor(GameScreen.ChoiceMenu);
                                    MyGdxGame.gameStart();
                                  }
                              }
        );


        Menu.addActor(Button[0]);
        stage.addActor(Menu);
        Menu.setPosition((GameScreen.WightScreen-300)/2, (Gdx.graphics.getHeight()-55)/2);
        Gdx.input.setInputProcessor(stage);

    }


    public void render() {
        GameScreen.getBatch().begin();
        GameScreen.getBatch().draw(FonMenu, -75, 0,Gdx.graphics.getWidth()+75,Gdx.graphics.getHeight());
        GameScreen.getBatch().end();

        stage.draw();

    }

    public static void update(float dt) {
        if (!MyGdxGame.gameActive) {
            stage.act(dt);
        }
    }
}
