package game;

import game.renderer.Renderer;
import tklibs.SpriteUtils;

public class Background extends GameObject {

    public Background() {
        // = SpriteUtils.loadImage("assets/images/background/0.png");
        renderer = new Renderer("assets/images/background/0.png");
        position.set(0, 600 - 3109);
    }

    @Override
    public void run() {
        position.y++;
        if (position.y >= 0) {
            position.y = 0;
        }
    }
}
