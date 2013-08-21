package com.smartplace.bombasmejorada.tabs;

/**
 * Created by RoNo on 25/07/13.
 */
public class DataManager {

    public String EnergySource;
    public String Edificio;
    public String Grupo;
    public String Uso;
    public int lpm;
    public int psi;
    public int hSalidas;
    public int hDistVertical;
    public int hLongSalida;
    public int hPresionSalida;
    public int iLongSalida;
    public int iDesnivel;
    public String EquipoModelo;
    public String NoDeSerie;
    public String Falla;
    public String Domicilio;
    public String Observaciones;
    public String Nombre;
    public String Empresa;
    public String Puesto;
    public String Telefono;
    public String Celular;
    public String Correo;


    public DataManager()
    {
        lpm=10;
        psi=5;
        hSalidas =10;
        hDistVertical=10;
        hLongSalida=5;
        hPresionSalida=15;
        iLongSalida=5;
        iDesnivel=5;
        EquipoModelo = null;
        NoDeSerie = null;
        Falla = null;
        Domicilio = null;
        Observaciones = null;
        Nombre = null;
        Empresa = null;
        Puesto = null;
        Telefono = null;
        Celular = null;
        Correo = null;

    }

}
