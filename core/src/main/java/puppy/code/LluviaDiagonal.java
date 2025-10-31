package puppy.code;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Nivel 3: Lluvia diagonal, gotas normales y dañinas caen en ángulo.
 */
public class LluviaDiagonal extends LluviaBase {

    private Lluvia lluviaInterna;

    public LluviaDiagonal(Texture azul, Texture roja, Texture verde, Texture amarilla,
                          Sound drop, Music musica) {
        super(3, 750);
        lluviaInterna = new Lluvia(azul, roja, verde, amarilla, drop, musica);
        lluviaInterna.crearDiagonal();
    }

    @Override
    public void actualizar(Tarro tarro) {
        lluviaInterna.actualizarMovimientoDiagonal(tarro);
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
