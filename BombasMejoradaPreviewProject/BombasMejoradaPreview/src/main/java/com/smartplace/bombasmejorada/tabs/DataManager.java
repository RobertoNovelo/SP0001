package com.smartplace.bombasmejorada.tabs;

import android.widget.RelativeLayout;

/**
 * Created by RoNo on 25/07/13.
 */
public class DataManager {

    public int lpm;
    public int psi;

    public String EnergySource;

    public int hSalidas;
    public int hDistVertical;
    public int hLongSalida;
    public int hPresionSalida;

    public double hFactorPorSalida;
    public double hPorcentajePerdidas;
    public double hGastoPico;
    public double hCargaDinamica;
    public double hDiametroTubo;

    public String Edificio;

    public int iLongSalida;
    public int iDesnivel;
    public int iPresion;
    public double iGastoPico;
    public double iPerdidas;
    public double iCargaDinamica;
    public int iGasto;
    public double iDiamPrincipal;
    public double iDiamCircuito;

    public boolean iCheckBox1;
    public boolean iCheckBox2;
    public boolean iCheckBox3;
    public boolean iCheckBox4;
    public boolean iproteccionCorrecta;

    public String Grupo;
    public String Uso;
    public String iProteccion;

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
    public String screenSize;



    public DataManager()
    {
        lpm=10;
        psi=5;

        hSalidas =10;
        hDistVertical=10;
        hLongSalida=5;
        hPresionSalida=15;

        hFactorPorSalida=0;
        hPorcentajePerdidas=0;
        hGastoPico=0;
        hCargaDinamica=0;
        hDiametroTubo=0;

        iCheckBox1=false;
        iCheckBox2=false;
        iCheckBox3=false;
        iCheckBox4=false;

        iLongSalida=5;
        iDesnivel=5;
        iPresion=0;
        iGastoPico=0;
        iPerdidas=0;
        iCargaDinamica=0;
        iGasto=0;
        iDiamPrincipal=0;
        iDiamCircuito=0;

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
        screenSize = null;
    }

}
