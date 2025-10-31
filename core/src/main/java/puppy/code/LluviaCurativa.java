package puppy.code;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Nivel 2: Lluvia con gotas curativas (verdes).
 */
public class LluviaCurativa extends LluviaBase {

    private Lluvia lluviaInterna;

    public LluviaCurativa(Texture azul, Texture roja, Texture verde, Texture amarilla,
                           Sound drop, Music musica) {
        super(2, 500); // meta de 500 puntos
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

    @Override
    public int getProgreso() {
        return lluviaInterna.getPuntaje();
    }
}
