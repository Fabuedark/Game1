package com.mygdx.game.Characters;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.GameScreen;

public abstract class GameCharaters {
    protected static GameScreen gameScreen;
    Texture hpline;
    Texture texture;

    float speed;
    float attackTimer;




    public abstract void update(float dt) throws InterruptedException;

    {
    }




}






