package puppy.code;

/**
 * Representa un nivel del juego con sus condiciones.
 */
public class Nivel {
    private int numeroNivel;
    private int objetivoAzules;
    private boolean diagonales;
    private boolean tienePowerUps;

    public Nivel(int numeroNivel, int objetivoAzules, boolean diagonales, boolean tienePowerUps) {
        this.numeroNivel = numeroNivel;
        this.objetivoAzules = objetivoAzules;
        this.diagonales = diagonales;
        this.tienePowerUps = tienePowerUps;
    }

    public int getNumeroNivel() {
        return numeroNivel;
    }

    public int getObjetivoAzules() {
        return objetivoAzules;
    }

    public boolean tieneDiagonal() {
        return diagonales;
    }

    public boolean tienePowerUps() {
        return tienePowerUps;
    }
}
