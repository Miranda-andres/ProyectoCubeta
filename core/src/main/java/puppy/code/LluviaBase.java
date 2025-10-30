package puppy.code;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Clase abstracta base para distintos tipos de lluvia (niveles).
 * Define las operaciones comunes: actualizar y dibujar.
 */
public abstract class LluviaBase {
	protected int nivel;
	protected int objetivoPuntaje;

	public Lluvia(int nivel, int objetivoPuntaje) {
		this.nivel = nivel;
		this.objetivoPuntaje = objetivoPuntaje;
	}

    public abstract void actualizar(Tarro tarro);

    public abstract void dibujar(SpriteBatch batch);

    public abstract int getNivel(){
		return nivel;
	}

    public abstract int getObjetivoPuntaje();

}
