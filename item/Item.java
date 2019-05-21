package game.item;

import game.GameObject;
import game.physics.BoxCollider;
import game.renderer.Renderer;

public class Item extends GameObject {
    public Item() {
        renderer = new Renderer("assets/images/items/power-up-blue.png");
        hitBox = new BoxCollider(this, 12, 12);
        position.set(175, 50);
    }

    public void run() {
        super.run();
        position.y += 5;
        this.deactiveIfNeeded();
    }

    public void deactiveIfNeeded() {
        if (position.y < 50) {
            this.deactive();
        }
    }
}