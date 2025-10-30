package puppy.code;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Clase abstracta base para distintos tipos de lluvia (niveles).
 * Define las operaciones comunes: actualizar y dibujar.
 */
public abstract class LluviaBase {

    /**
     * Actualiza la lógica de caída y colisiones de gotas.
     * @param tarro referencia al jugador
     */
    public abstract void actualizar(Tarro tarro);

    /**
     * Dibuja las gotas en pantalla.
     * @param batch SpriteBatch para renderizar
     */
    public abstract void dibujar(SpriteBatch batch);

    /**
     * Devuelve el número del nivel actual.
     */
    public abstract int getNivel();

    /**
     * Devuelve el puntaje objetivo para pasar de nivel.
     */
    public abstract int getObjetivo();

    /**
     * Devuelve el puntaje actual (gotas azules recolectadas).
     */
    public abstract int getProgreso();
}
