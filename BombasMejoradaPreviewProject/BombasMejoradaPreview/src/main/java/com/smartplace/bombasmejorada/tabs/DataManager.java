package com.smartplace.bombasmejorada.tabs;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.RelativeLayout;

/**
 * Created by RoNo on 25/07/13.
 */
public class DataManager implements Parcelable{

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

    private int mData;


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
        iPresion=65;
        iGastoPico=0;
        iPerdidas=0;
        iCargaDinamica=0;
        iGasto=0;
        iDiamPrincipal=0;
        iDiamCircuito=0;

        EnergySource = "Monofasica 110 V";
        Edificio = "Club";
        Uso = "Asilo";
        Grupo = "Riesgo Ligero";
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

    public int describeContents() {
        return 0;
    }
    /** save object in parcel */
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mData);
    }
    public static final Parcelable.Creator<DataManager> CREATOR
            = new Parcelable.Creator<DataManager>() {
        public DataManager createFromParcel(Parcel in) {
            return new DataManager(in);
        }

        public DataManager[] newArray(int size) {
            return new DataManager[size];
        }
    };
    /** recreate object from parcel */
    private DataManager(Parcel in) {
        mData = in.readInt();
    }
}
