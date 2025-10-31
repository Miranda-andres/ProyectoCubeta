package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Clase que representa al tarro (jugador).
 * Implementa la interfaz Movible para aplicar principios de diseño OO.
 */
public class Tarro implements Movible {

    private Texture imagen;
    private Rectangle rect;
    private float velocidadBase = 200f;
    private float velMultiplicador = 1f;

    private int vidas = 3;
    private final int MAX_VIDAS = 5;

    private boolean invulnerable = false;
    private float tiempoInvulnerable = 2f;
    private float tiempoRestanteInvulnerable = 0f;

    private float tiempoBoost = 8f;
    private float boostRestante = 0f;

    public Tarro() {
        imagen = new Texture("tarro.png");
        rect = new Rectangle(400 - 32, 20, 64, 64);
    }

    @Override
    public void mover(float delta) {
        float velocidadActual = velocidadBase * velMultiplicador;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            rect.x -= velocidadActual * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            rect.x += velocidadActual * delta;
        }

        // Limitar dentro de la pantalla
        if (rect.x < 0) rect.x = 0;
        if (rect.x > 800 - rect.width) rect.x = 800 - rect.width;

        // Contadores de efectos
        if (invulnerable) {
            tiempoRestanteInvulnerable -= delta;
            if (tiempoRestanteInvulnerable <= 0) invulnerable = false;
        }

        if (boostRestante > 0) {
            boostRestante -= delta;
            if (boostRestante <= 0) velMultiplicador = 1f;
        }
    }

    @Override
    public void dibujar(SpriteBatch batch) {
        batch.draw(imagen, rect.x, rect.y);
    }

    // =====================
    // Métodos de efectos
    // =====================

    /** Añade una vida, sin pasar el máximo permitido. */
    public void curar() {
        if (vidas < MAX_VIDAS) vidas++;
    }

    /** Resta una vida, salvo si está invulnerable. */
    public void dañar() {
        if (!invulnerable) {
            vidas--;
            invulnerable = true;
            tiempoRestanteInvulnerable = tiempoInvulnerable;
        }
    }

    /** Activa un boost temporal de velocidad. */
    public void activarBoost() {
        velMultiplicador = 1.8f;
        boostRestante = tiempoBoost;
    }

    /** Reinicia los efectos temporales entre niveles. */
    public void reiniciarEfectos() {
        velMultiplicador = 1f;
        boostRestante = 0f;
        invulnerable = false;
        tiempoRestanteInvulnerable = 0f;
    }

    // =====================
    // Getters y Setters
    // =====================

    public Rectangle getRect() {
        return rect;
    }

    public int getVidas() {
        return vidas;
    }

    public boolean estaVivo() {
        return vidas > 0;
    }

    public void dispose() {
        imagen.dispose();
    }
}
