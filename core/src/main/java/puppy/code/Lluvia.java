amandaaaaa
amandaaaaa3906
En una llamada

pipe
 ha añadido a 
amandaaaaa
 al grupo. — 27/09/2025 23:37
pipe
 ha iniciado una llamada que ha durado 27 minutos. — 27/09/2025 23:37
pipe — 27/09/2025 23:38
Tipo de archivo adjunto: acrobat
Reporte Técnico Final del Proyecto tema 24.pdf
197.34 KB
https://docs.google.com/document/d/1yxnVsKn7_C_6_qG8zg5JGHigFPfR6lX8CDvzMv8myB0/edit?usp=sharing
Google Docs
Informe SIA FINAL
Mirandaandres — 27/09/2025 23:40
Imagen
Imagen
pipe — 27/09/2025 23:41
Imagen
amandaaaaa — 27/09/2025 23:44
Tipo de archivo adjunto: acrobat
Informe_Proyecto_SIA_Detallado.pdf
11.80 KB
pipe — 27/09/2025 23:46
https://docs.google.com/document/d/1uHiWdJYbePIobzb3IR4qpfEfzEwGAHEeF9N-uLS9PcY/edit?usp=sharing
Google Docs
Informe_Proyecto_SIA_Detallado
PONTIFICIA UNIVERSIDAD CATÓLICA DE VALPARAÍSO FACULTAD DE INGENIERÍA ESCUELA DE INGENIERÍA INFORMÁTICA Informe Técnico Final del Proyecto Sistema de Información (SIA) Asignatura: Sistemas de Información Profesor: Claudio Cubillos Integrantes: Andrés Miranda, Amanda Ramírez, Felipe Silva F...
Imagen
amandaaaaa — 28/10/2025 20:25
alo alo
Mirandaandres — 28/10/2025 20:25
hola
voy por algo de comer y voy
amandaaaaa — 28/10/2025 20:25
okss
Mirandaandres — 28/10/2025 20:34
toy listo
amandaaaaa
 ha iniciado una llamada que ha durado 2 horas. — 28/10/2025 20:34
pipe — 28/10/2025 20:34
voy en 10 min
que estoy estudiando algo
amandaaaaa — 28/10/2025 20:35
yaya
https://docs.google.com/document/d/1cg0enPBW2EecLIStwbMSR_DHU-22IfrklT0HYJ0UXSU/edit?usp=sharing
Google Docs
Informe del proyecto de Programación Avanzada
Universidad Católica de Valparaíso Escuela de Ingeniería Informática  Informe Proyecto Videojuego (GM) Avance  Profesor: Claudio Cubillos Integrantes: Andrés Miranda, Amanda Ramírez, Felipe Silva Fecha: 31-10-2025 Resumen Este informe presenta el desarrollo del Proyecto Sistema de Informac...
Imagen
Mirandaandres — 28/10/2025 20:55
https://github.com/Miranda-andres/Videojuego.git
GitHub
GitHub - Miranda-andres/Videojuego
Contribute to Miranda-andres/Videojuego development by creating an account on GitHub.
Contribute to Miranda-andres/Videojuego development by creating an account on GitHub.
pipe — 28/10/2025 20:58
felipe.silva.r@mail.pucv.cl
amandaaaaa — 28/10/2025 21:27
Imagen
Mirandaandres
 ha iniciado una llamada. — 18:05
Mirandaandres — 18:30
package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
Expandir
4 KB
package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
Expandir
13 KB
package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
Expandir
4 KB
Imagen
Imagen
﻿
package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Lluvia {
    // Gotas activas
    private final Array<Gota> gotas = new Array<>();

    // Nivel
    private Nivel nivel;

    // Texturas
    private Texture gotaAzul;
    private Texture gotaRoja;
    private Texture gotaVerde;
    private Texture gotaAmarilla;

    // Sonidos y musica
    private Sound dropSound;   // para gota azul/recoger
    private Music rainMusic;

    private long lastSpawnNs;

    public Lluvia(Texture azul, Texture roja, Texture verde, Texture amarilla, Sound dropSound, Music rainMusic) {
        this.gotaAzul = azul;
        this.gotaRoja = roja;
        this.gotaVerde = verde;
        this.gotaAmarilla = amarilla;
        this.dropSound = dropSound;
        this.rainMusic = rainMusic;
    }

    public void crear() {
        nivel = new Nivel(1);
        if (rainMusic != null) {
            rainMusic.setLooping(true);
            rainMusic.play();
        }
        lastSpawnNs = TimeUtils.nanoTime();
    }

    public boolean actualizarMovimiento(Tarro tarro) {
        float delta = Gdx.graphics.getDeltaTime();
        float ancho = 800f, alto = 480f;

        // Actualizar timers del tarro (inmunidad/boost)
        tarro.updateTimers(delta);

        // Spawn por ticks (cada ~100ms chequea probabilidades)
        if (TimeUtils.nanoTime() - lastSpawnNs > 100_000_000) {
            List<Gota> nuevas = nivel.spawnTick(
                    new Nivel.Textures(gotaAzul, gotaRoja, gotaVerde, gotaAmarilla), ancho, alto
            );
            for (Gota g : nuevas) gotas.add(g);
            lastSpawnNs = TimeUtils.nanoTime();
        }

        // Update de gotas
        for (Gota g : gotas) {
            g.update(delta, ancho, alto);
        }

        // Colisiones
        Rectangle bTarro = tarro.getArea();
        for (Iterator<Gota> it = gotas.iterator(); it.hasNext();) {
            Gota g = it.next();
            if (!g.isActiva()) {
                it.remove();
                continue;
            }
            if (g.getBounds().overlaps(bTarro)) {
                g.aplicar(tarro);
                nivel.onRecogerGota(g);
                if (g instanceof GotaAzul && dropSound != null) dropSound.play();
                it.remove();
            }
        }

        // Cambio de nivel
        if (nivel.completo()) {
            int n = nivel.getNumero();
            if (n < 3) {
                nivel = new Nivel(n + 1);
            } else {
                // Reinicia al completar nivel 3
                nivel = new Nivel(1);
            }
            gotas.clear();
        }

        // Game over
        return !tarro.estaMuerto();
    }

    public void actualizarDibujoLluvia(SpriteBatch batch) {
        for (Gota g : gotas) {
            g.render(batch);
        }
    }

    // HUD
    public int getNivelNumero() { return nivel.getNumero(); }
    public int getObjetivoAzules() { return nivel.getObjetivoAzules(); }
    public int getAzulesRecogidas() { return nivel.getAzulesRecogidas(); }

    // ========= Tipos anidados para cumplir GM1.4 y GM1.5 sin crear archivos nuevos =========

    public interface EfectoSobreTarro {
        void aplicar(Tarro tarro);
    }

    public interface Movimiento {
        void actualizar(Gota gota, float delta, float ancho, float alto);
    }

    public static class MovimientoVertical implements Movimiento {
        @Override
        public void actualizar(Gota gota, float delta, float ancho, float alto) {
            gota.setY(gota.getY() - gota.getVelocidadY() * delta);
            if (gota.getY() + gota.getAlto() < 0) {
                gota.setActiva(false);
            }
        }
    }

    public static class MovimientoDiagonal implements Movimiento {
        @Override
        public void actualizar(Gota gota, float delta, float ancho, float alto) {
            float nx = gota.getX() + gota.getVelocidadX() * delta;
            float ny = gota.getY() - gota.getVelocidadY() * delta;

            if (nx < 0) {
                nx = 0;
                gota.setVelocidadX(Math.abs(gota.getVelocidadX()));
            } else if (nx + gota.getAncho() > ancho) {
                nx = ancho - gota.getAncho();
                gota.setVelocidadX(-Math.abs(gota.getVelocidadX()));
            }

            gota.setX(nx);
            gota.setY(ny);

            if (gota.getY() + gota.getAlto() < 0) {
                gota.setActiva(false);
            }
        }
    }

    public static abstract class Gota implements EfectoSobreTarro {
        private float x, y;
        private float velocidadX;
        private float velocidadY;
        private int ancho = 64, alto = 64;
        private Texture textura;
        private boolean activa = true;
        private Movimiento movimiento;

        protected Gota(float x, float y, float velocidadX, float velocidadY, Texture textura, Movimiento movimiento) {
            this.x = x;
            this.y = y;
            this.velocidadX = velocidadX;
            this.velocidadY = velocidadY;
            this.textura = textura;
            this.movimiento = movimiento;
        }

        public void update(float delta, float w, float h) {
            if (!activa) return;
            movimiento.actualizar(this, delta, w, h);
        }

        public void render(SpriteBatch batch) {
            if (!activa || textura == null) return;
            batch.draw(textura, x, y, ancho, alto);
        }

        public Rectangle getBounds() {
            return new Rectangle(x, y, ancho, alto);
        }

        // Getters / Setters
        public float getX() { return x; }
        public void setX(float x) { this.x = x; }
        public float getY() { return y; }
        public void setY(float y) { this.y = y; }
        public float getVelocidadX() { return velocidadX; }
        public void setVelocidadX(float velocidadX) { this.velocidadX = velocidadX; }
        public float getVelocidadY() { return velocidadY; }
        public void setVelocidadY(float velocidadY) { this.velocidadY = velocidadY; }
        public int getAncho() { return ancho; }
        public void setAncho(int ancho) { this.ancho = ancho; }
        public int getAlto() { return alto; }
        public void setAlto(int alto) { this.alto = alto; }
        public boolean isActiva() { return activa; }
        public void setActiva(boolean activa) { this.activa = activa; }
    }

    public static class GotaRoja extends Gota {
        public GotaRoja(float x, float y, float vy, Texture textura, Movimiento movimiento) {
            super(x, y, 0f, vy, textura, movimiento);
        }

        @Override
        public void aplicar(Tarro tarro) {
            tarro.recibirDano(1); // -1 vida + invulnerabilidad 1.5s
            setActiva(false);
        }
    }

    public static class GotaVerde extends Gota {
        public GotaVerde(float x, float y, float vy, Texture textura, Movimiento movimiento) {
            super(x, y, 0f, vy, textura, movimiento);
        }

        @Override
        public void aplicar(Tarro tarro) {
            tarro.curar(1); // +1 vida y aumenta vidaMax hasta 5
            setActiva(false);
        }
    }

    public static class GotaAmarilla extends Gota {
        public GotaAmarilla(float x, float y, float vy, Texture textura, Movimiento movimiento) {
            super(x, y, 0f, vy, textura, movimiento);
        }

        @Override
        public void aplicar(Tarro tarro) {
            tarro.aplicarBoostVelocidad(0.25f, 8f); // +25% por 8s
            setActiva(false);
        }
    }

    public static class GotaAzul extends Gota {
        public GotaAzul(float x, float y, float vy, Texture textura, Movimiento movimiento) {
            super(x, y, 0f, vy, textura, movimiento);
        }

        @Override
        public void aplicar(Tarro tarro) {
            tarro.sumarPuntos(1); // 1 punto
            setActiva(false);
        }
    }

    public static class Nivel {
        private final int numero;
        private final int objetivoAzules; // 30, 45, 55
        private int azulesRecogidas = 0;

        public Nivel(int numero) {
            this.numero = numero;
            this.objetivoAzules = switch (numero) {
                case 1 -> 30;
                case 2 -> 45;
                default -> 55;
            };
        }

        public boolean completo() {
            return azulesRecogidas >= objetivoAzules;
        }

        public void onRecogerGota(Gota g) {
            if (g instanceof GotaAzul) azulesRecogidas++;
        }

        public List<Gota> spawnTick(Textures t, float ancho, float alto) {
            List<Gota> nuevas = new ArrayList<>();
            // Ajusta probabilidades a gusto
            if (MathUtils.randomBoolean(0.06f)) nuevas.add(crearAzul(t, ancho, alto));
            if (MathUtils.randomBoolean(0.03f)) nuevas.add(crearRoja(t, ancho, alto));
            if (numero >= 2 && MathUtils.randomBoolean(0.015f)) nuevas.add(crearAmarilla(t, ancho, alto));
            if (numero >= 2 && MathUtils.randomBoolean(0.015f)) nuevas.add(crearVerde(t, ancho, alto));
            return nuevas;
        }

        private Movimiento movimientoParaAzulYRoja() {
            // Nivel 3: mezcla de vertical y diagonal
            if (numero >= 3 && MathUtils.randomBoolean(0.5f)) return new MovimientoDiagonal();
            return new MovimientoVertical();
        }

        private Movimiento vertical() { return new MovimientoVertical(); }

        private Gota crearAzul(Textures t, float ancho, float alto) {
            float x = MathUtils.random(0, ancho - 64);
            float vy = MathUtils.random(160f, 240f);
            Movimiento m = movimientoParaAzulYRoja();
            GotaAzul g = new GotaAzul(x, alto, vy, t.azul, m);
            if (m instanceof MovimientoDiagonal) {
                g.setVelocidadX(MathUtils.randomSign() * MathUtils.random(80f, 120f));
            }
            return g;
        }

        private Gota crearRoja(Textures t, float ancho, float alto) {
            float x = MathUtils.random(0, ancho - 64);
            float vy = MathUtils.random(180f, 270f);
            Movimiento m = movimientoParaAzulYRoja();
            GotaRoja g = new GotaRoja(x, alto, vy, t.roja, m);
            if (m instanceof MovimientoDiagonal) {
                g.setVelocidadX(MathUtils.randomSign() * MathUtils.random(110f, 150f));
            }
            return g;
        }

        private Gota crearAmarilla(Textures t, float ancho, float alto) {
            float x = MathUtils.random(0, ancho - 64);
            float vy = MathUtils.random(150f, 220f);
            return new GotaAmarilla(x, alto, vy, t.amarilla, vertical());
        }

        private Gota crearVerde(Textures t, float ancho, float alto) {
            float x = MathUtils.random(0, ancho - 64);
            float vy = MathUtils.random(140f, 200f);
            return new GotaVerde(x, alto, vy, t.verde, vertical());
        }

        public int getNumero() { return numero; }
        public int getObjetivoAzules() { return objetivoAzules; }
        public int getAzulesRecogidas() { return azulesRecogidas; }

        public static class Textures {
            public Texture azul, roja, verde, amarilla;
            public Textures(Texture azul, Texture roja, Texture verde, Texture amarilla) {
                this.azul = azul; this.roja = roja; this.verde = verde; this.amarilla = amarilla;
            }
        }
    }
}