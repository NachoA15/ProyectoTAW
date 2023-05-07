package es.uma.proyectotaw.ui;

import es.uma.proyectotaw.entity.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ignacio Alba - Gestor
 */
public class Filter {
    private String text;
    private List<String> listClientStatus;
    private List<String> listAccountStatus;
    private List<String> listAreas;

    public Filter() {
        text = "";
        listClientStatus = null;
        listAccountStatus = null;
        listAreas = null;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getListClientStatus() {
        return listClientStatus;
    }

    public void setListClientStatus(List<String> listClientStatus) {
        this.listClientStatus = listClientStatus;
    }

    public List<String> getListAccountStatus() {
        return listAccountStatus;
    }

    public void setListAccountStatus(List<String> listAccountStatus) {
        this.listAccountStatus = listAccountStatus;
    }

    public List<String> getListAreas() {
        return listAreas;
    }

    public void setListAreas(List<String> listAreas) {
        this.listAreas = listAreas;
    }
}
