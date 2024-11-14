package com.app.mariobros.list;

/**
 * La clase List representa un ítem con una imagen, un encabezado y habilidades.
 */
public class List {
    private int titleImage;
    private String heading;
    private String skills;

    /**
     * Constructor para crear una nueva instancia de la clase List.
     * @param titleImage La imagen asociada con este ítem.
     * @param heading El encabezado del ítem.
     * @param skills Las habilidades relacionadas con el ítem.
     */
    public List(int titleImage, String heading, String skills) {
        this.titleImage = titleImage;
        this.heading = heading;
        this.skills = skills;
    }

    /**
     * Obtiene la imagen del título de este ítem.
     * @return La imagen del título.
     */
    public int getTitleImage() {
        return titleImage;
    }

    /**
     * Establece una nueva imagen del título para este ítem.
     * @param titleImage La nueva imagen del título.
     */
    public void setTitleImage(int titleImage) {
        this.titleImage = titleImage;
    }

    /**
     * Obtiene el encabezado de este ítem.
     * @return El encabezado del ítem.
     */
    public String getHeading() {
        return heading;
    }

    /**
     * Establece un nuevo encabezado para este ítem.
     * @param heading El nuevo encabezado.
     */
    public void setHeading(String heading) {
        this.heading = heading;
    }

    /**
     * Obtiene las habilidades relacionadas con este ítem.
     * @return Las habilidades del ítem.
     */
    public String getSkills() {
        return skills;
    }

    /**
     * Establece nuevas habilidades para este ítem.
     * @param skills Las nuevas habilidades.
     */
    public void setSkills(String skills) {
        this.skills = skills;
    }
}
