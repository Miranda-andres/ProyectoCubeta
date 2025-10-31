package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.TimeUtils;

public class Lluvia {
    private final Pool<Rectangle> gotasPool = new Pool<Rectangle>() {
        @Override
        protected Rectangle newObject() {
            return new Rectangle();
        }
    };
    private Array<Rectangle> gotasActivas = new Array<>();
    private long lastDropTime;
    private int puntaje = 0;

    private Texture gotaAzul;
    private Texture gotaRoja;
    private Texture gotaVerde;
    private Texture gotaAmarilla;
    private Sound dropSound;
    private Music rainMusic;

    public Lluvia(Texture azul, Texture roja, Texture verde, Texture amarilla,
                  Sound drop, Music musica) {
        this.gotaAzul = azul;
        this.gotaRoja = roja;
        this.gotaVerde = verde;
        this.gotaAmarilla = amarilla;
        this.dropSound = drop;
        this.rainMusic = musica;
    }

    public void crear() {
        crearGota();
    }

    public void crearDiagonal() {
        crearGotaDiagonal();
    }

    private void crearGota() {
        Rectangle gota = gotasPool.obtain();
        gota.x = MathUtils.random(0, 800 - 64);
        gota.y = 480;
        gota.width = 64;
        gota.height = 64;
        gotasActivas.add(gota);
        lastDropTime = TimeUtils.nanoTime();
    }

    private void crearGotaDiagonal() {
        Rectangle gota = gotasPool.obtain();
        gota.x = MathUtils.random(0, 800 - 64);
        gota.y = 480;
        gota.width = 64;
        gota.height = 64;
        gota.x += MathUtils.random(-200, 200) * Gdx.graphics.getDeltaTime();
        gotasActivas.add(gota);
        lastDropTime = TimeUtils.nanoTime();
    }

    public void actualizarMovimiento(Tarro tarro) {
        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) crear();

        for (Rectangle gota : gotasActivas) {
            gota.y -= 200 * Gdx.graphics.getDeltaTime();
            
            if (gota.overlaps(tarro.getRect())) {
                puntaje += 10;
                dropSound.play();
                gotasActivas.removeValue(gota, true);
                gotasPool.free(gota);
            }
            
            if (gota.y + 64 < 0) {
                gotasActivas.removeValue(gota, true);
                gotasPool.free(gota);
            }
        }
    }

    public void actualizarMovimientoDiagonal(Tarro tarro) {
        if (TimeUtils.nanoTime() - lastDropTime > 1000000000) crearDiagonal();

        for (Rectangle gota : gotasActivas) {
            gota.y -= 200 * Gdx.graphics.getDeltaTime();
            gota.x += MathUtils.random(-200, 200) * Gdx.graphics.getDeltaTime();
            
            if (gota.overlaps(tarro.getRect())) {
                puntaje += 10;
                dropSound.play();
                gotasActivas.removeValue(gota, true);
                gotasPool.free(gota);
            }
            
            if (gota.y + 64 < 0 || gota.x < 0 || gota.x > 800 - 64) {
                gotasActivas.removeValue(gota, true);
                gotasPool.free(gota);
            }
        }
    }

    public void actualizarDibujoLluvia(SpriteBatch batch) {
        for (Rectangle gota : gotasActivas) {
            batch.draw(gotaAzul, gota.x, gota.y);
        }
    }

    public int getPuntaje() {
        return puntaje;
    }
}