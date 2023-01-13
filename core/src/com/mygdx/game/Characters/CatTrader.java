package com.mygdx.game.Characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.GameScreen;

public class CatTrader {
    static float x;
    static float y;
    private Texture texture;
    private Texture textureStay;
    private TextureRegion[] regions;
    private int frameIndex;
    private float secOnFrame;
    private float speed;
    private float animationTimer;

    public CatTrader() {
        refactorCatTrader();

    }

 


    public void render(SpriteBatch batch) {


        frameIndex = (int) (animationTimer / secOnFrame) % regions.length;

        batch.draw(regions[frameIndex], x, y);

}


    public void update(float dt) {
        animationTimer += dt;

        if (x >= Gdx.graphics.getWidth()*0.7f) {
            //this.regions = new TextureRegion(texture).split(32,32)[32];
            x -= speed * Gdx.graphics.getWidth()/100 * dt;;
        }
        else {
            this.regions = new TextureRegion(textureStay).split(192,192)[0];
        }
    }
    public void refactorCatTrader() {
        texture = new Texture("Cat/walk0.png");
        textureStay = new Texture("Cat/stay1.png");
        this.regions = new TextureRegion(textureStay).split(192,192)[0];
        for (int i = 0; i<=3; i++) {
            regions[i].flip(true,false);
        }
        this.regions = new TextureRegion(texture).split(192,192)[0];
        for (int i = 0; i<=7; i++) {
            regions[i].flip(true,false);
        }

        x = GameScreen.WightScreen;
        y = Gdx.graphics.getHeight()*0.097f;
        speed = 4;
        secOnFrame = 0.3f;
    }
}

