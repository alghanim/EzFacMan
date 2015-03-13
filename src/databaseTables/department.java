package databaseTables;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ali
 */
public class department {

    private Long FOAPAL_code;
    private String FOAPAL_name, FOAPAL_color;

    public Long getFOAPAL_code() {
        return FOAPAL_code;
    }

    public void setFOAPAL_code(Long FOAPAL_code) {
        this.FOAPAL_code = FOAPAL_code;
    }

    public String getFOAPAL_name() {
        return FOAPAL_name;
    }

    public void setFOAPAL_name(String FOAPAL_name) {
        this.FOAPAL_name = FOAPAL_name;
    }

    public String getFOAPAL_color() {
        return FOAPAL_color;
    }

    public void setFOAPAL_color(String FOAPAL_color) {
        this.FOAPAL_color = FOAPAL_color;
    }
}
