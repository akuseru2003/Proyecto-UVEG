package Serrano_Axel_R5_U3;

/**
 * Clase que representa a un corredor en la carrera.
 */
public class Runner {
    private String name;
    private int speed;

    /**
     * Constructor que inicializa el corredor con un nombre y una velocidad aleatoria.
     * @param name El nombre del corredor.
     */
    public Runner(String name) {
        this.name = name;
        // Velocidad aleatoria entre 0 y 30.
        this.speed = (int) (Math.random() * 31);
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }
}
