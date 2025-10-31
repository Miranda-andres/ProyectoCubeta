package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * Pantalla principal del juego.
 * Controla la lógica de niveles, cambio de fondos y progreso automático.
 */
public class GameScreen implements Screen {

    private final GameLluviaMenu game;
    private SpriteBatch batch;

    private Texture fondo;
    private Texture fondo1, fondo2, fondo3;
    private Texture azul, roja, verde, amarilla;

    private Sound dropSound;
    private Music rainMusic;

    private Tarro tarro;
    private LluviaBase lluvia;
    private int nivelActual = 1;

    public GameScreen(final GameLluviaMenu game) {
        this.game = game;
        batch = new SpriteBatch();

        // Cargar texturas
        fondo1 = new Texture("fondo1.png");
        fondo2 = new Texture("fondo2.png");
        fondo3 = new Texture("fondo3.png");
        fondo = fondo1;

        azul = new Texture("gotaAzul.png");
        roja = new Texture("gotaRoja.png");
        verde = new Texture("gotaVerde.png");
        amarilla = new Texture("gotaAmarilla.png");

        // Cargar sonidos
        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
        rainMusic.setLooping(true);
        rainMusic.play();

        // Crear entidades
        tarro = new Tarro();
        lluvia = new LluviaNormal(azul, roja, verde, amarilla, dropSound, rainMusic);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        batch.begin();
        batch.draw(fondo, 0, 0, 800, 480);
        lluvia.dibujar(batch);
        tarro.dibujar(batch);
        batch.end();

        // Actualizar lógica de juego
        tarro.mover(delta);
        lluvia.actualizar(tarro);

        verificarCambioNivel();
    }

    /**
     * Verifica si se cumplió la meta del nivel y avanza automáticamente.
     */
    private void verificarCambioNivel() {
        int progreso = lluvia.getProgreso();
        int objetivo = lluvia.getObjetivoPuntaje();

        if (progreso >= objetivo) {
            nivelActual++;

            switch (nivelActual) 
            {
            case 2:
            lluvia = new LluviaCurativa(azul, roja, verde, amarilla, dropSound, rainMusic);
            break;
            case 3:
            lluvia = new LluviaDiagonal(azul, roja, verde, amarilla, dropSound, rainMusic);
            break;
            default:
            lluvia = new LluviaNormal(azul, roja, verde, amarilla, dropSound, rainMusic);
                break;
            }

            cambiarEscenario(nivelActual);
            tarro.reiniciarEfectos();
        }
    }

    /**
     * Cambia el fondo de pantalla según el nivel actual.
     */
    private void cambiarEscenario(int nivel) {
        switch (nivel) {
    case 1:
        fondo = fondo1;
        break;
    case 2:
        fondo = fondo2;
        break;
    case 3:
        fondo = fondo3;
        break;
    default:
        fondo = fondo1;
        break;
}
    }

    @Override
    public void dispose() {
        batch.dispose();
        azul.dispose();
        roja.dispose();
        verde.dispose();
        amarilla.dispose();
        fondo1.dispose();
        fondo2.dispose();
        fondo3.dispose();
        rainMusic.dispose();
        dropSound.dispose();
    }

    // Métodos vacíos requeridos por la interfaz Screen
    @Override public void show() {}
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
}
