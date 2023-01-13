package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UpgradeStats {
    private static SpriteBatch batch;
    private static Texture board;





    public static void create() {
        batch = new SpriteBatch();
        board= new Texture("board.jpg"); //317x450



    }

public static void render() {

        batch.begin();
        if (GameScreen.a == 1) {
            batch.setColor(1,1,1,1);
            //batch.draw(board, 65, 200);  //65=0*317+1*65
            //batch.draw(board, 447, 200); //447=1*317+2*65
            //batch.draw(board, 829, 200); //829=2*317+3*65
            batch.draw(board, (GameScreen.WightScreen-(317*3))/4, Gdx.graphics.getHeight()*0.27f);
            batch.draw(board, ((GameScreen.WightScreen-(317*3))/4)*2+317, Gdx.graphics.getHeight()*0.27f);
            batch.draw(board, ((GameScreen.WightScreen-(317*3))/4)*3+317*2, Gdx.graphics.getHeight()*0.27f);
            batch.setColor(1,1,1,1);
            batch.end();
        }

}
public static void update(float dt) {

    }
}



