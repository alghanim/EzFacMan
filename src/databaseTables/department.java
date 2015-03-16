package databaseTables;

/**
 * Generate the setters and getters for Department table
 *
 * @author Ali
 */
public class department {

    private Long FOAPAL_code;
    private String FOAPAL_name, FOAPAL_color;

    /**
     *
     * @return department code
     */
    public Long getFOAPAL_code() {
        return FOAPAL_code;
    }

    /**
     * Set the department code the database
     *
     * @param FOAPAL_code
     */
    public void setFOAPAL_code(Long FOAPAL_code) {
        this.FOAPAL_code = FOAPAL_code;
    }

    /**
     *
     * @return department name
     */
    public String getFOAPAL_name() {
        return FOAPAL_name;
    }

    /**
     * set the department name
     *
     * @param FOAPAL_name
     */
    public void setFOAPAL_name(String FOAPAL_name) {
        this.FOAPAL_name = FOAPAL_name;
    }

    /**
     *
     * @return department color
     */
    public String getFOAPAL_color() {
        return FOAPAL_color;
    }

    /**
     * Set the department color to the database
     *
     * @param FOAPAL_color
     */
    public void setFOAPAL_color(String FOAPAL_color) {
        this.FOAPAL_color = FOAPAL_color;
    }
}
