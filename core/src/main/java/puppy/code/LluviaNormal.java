package puppy.code;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Implementación concreta de la lluvia básica del juego.
 */
public class LluviaNormal extends LluviaBase {

    private Lluvia lluviaInterna;

    public LluviaNormal(Texture azul, Texture roja, Texture verde, Texture amarilla,
                        Sound drop, Music musica) {
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
    public int getNivel() {
        return lluviaInterna.getNivelNumero();
    }

    @Override
    public int getObjetivo() {
        return lluviaInterna.getObjetivoAzules();
    }

    @Override
    public int getProgreso() {
        return lluviaInterna.getAzulesRecogidas();
    }
}
