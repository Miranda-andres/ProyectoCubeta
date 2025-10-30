package puppy.code;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Lluvia con gotas rojas que quitan vida al tarro.
 */
public class LluviaDañina extends LluviaBase {

    private Lluvia lluviaInterna;

    public LluviaDañina(Texture azul, Texture roja, Texture verde, Texture amarilla,
                        Sound drop, Music musica) {
        super(1, 300);
        lluviaInterna = new Lluvia(azul, roja, verde, amarilla, drop, musica);
        lluviaInterna.crear();
    }

    @Override
    public void actualizar(Tarro tarro) {
        lluviaInterna.actualizarMovimiento(tarro);
    }

    @Override
    public void dibujar(SpriteBatch batch) {
        lluviaInterna.actualizarDibujoLluvia(batch);
    }
}
