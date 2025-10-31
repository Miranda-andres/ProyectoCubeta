package puppy.code;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Movible {
    void mover(float deltaTiempo);
    void dibujar(SpriteBatch batch);
}
