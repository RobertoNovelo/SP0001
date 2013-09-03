package com.smartplace.bombasmejorada.tabs.incendios;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smartplace.assets.PDFHandler;
import com.smartplace.bombasmejorada.R;
import com.smartplace.bombasmejorada.tabs.DataManager;
import com.smartplace.bombasmejorada.tabs.TabsMainActivity;

/**
 * Created by uidv6409 on 2/09/13.
 */
public class IncendiosFragment5 extends Fragment {

    String pdfName = new String();
    String[] pdfLabel   = new String[30];
    int pdfResultsCount = 0;
    private DataManager dataManager;
    int i;
    PDFHandler pdfs = new PDFHandler(Environment.getExternalStorageDirectory() + "/Android/data/com.smartplace.bombasmejorada/");


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        dataManager = ((TabsMainActivity)getActivity()).getDataManager();

        return inflater.inflate(R.layout.tab_incendios_5, container, false);

    }// end onCreateView


    @Override
    public void onResume ()
    {
        super.onResume();

        pdfResultsCount=0;

        TextView label;

        getResults(dataManager);

        label = (TextView) getActivity().findViewById(R.id.rEncontrados);
        label.setText(String.format("%s",pdfResultsCount));

        LinearLayout resultsContainer = (LinearLayout) getActivity().findViewById(R.id.resultsContainer);

        if (pdfResultsCount > 0)
        {
            View v;
            v = getActivity().findViewById(R.id.rpdfIncendios);

            v.setVisibility(View.VISIBLE);

            for (i =0;i<pdfResultsCount;i++)
            {
                v = resultsContainer.getChildAt(i);
                v.setVisibility(View.VISIBLE);


                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        pdfName = ((TextView) ((LinearLayout) ((RelativeLayout) view).getChildAt(0)).getChildAt(1)).getText().toString();

                        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create(); //Read Update
                        alertDialog.setTitle("Opciones");

                        alertDialog.setButton("Enviar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {


                                DataManager dataManager = ((TabsMainActivity)getActivity()).getDataManager();
                                Intent i = new Intent(Intent.ACTION_SEND);
                                i.setType("message/rfc822");
//                                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"enlinea@bombasmejorada.com"});
                                i.putExtra(Intent.EXTRA_SUBJECT, Html.fromHtml("Solicito Informaci&oacute;n v&iacute;a Android").toString());
                                i.putExtra(Intent.EXTRA_TEXT,
                                        "Bombas - Hoja de cálculo: " + Html.fromHtml("<br/><br/>") +
                                                "Instalación: " + Html.fromHtml("<br/>") + dataManager.Grupo + Html.fromHtml("<br/><br/>") +
                                                "Protección con: " + Html.fromHtml("<br/>") + dataManager.iProteccion + Html.fromHtml("<br/><br/>") +
                                                "Longitud de tubería máxima: " + Html.fromHtml("<br/>") + dataManager.iLongSalida + " mts" + Html.fromHtml("<br/><br/>") +
                                                "Desnivel total máximo: " + Html.fromHtml("<br/>") + dataManager.iDesnivel + " mts" + Html.fromHtml("<br/><br/>") +
                                                "Presión de trabajo: " + Html.fromHtml("<br/>") + dataManager.iPresion + " psi" + Html.fromHtml("<br/><br/>") +
                                                "Pérdidas de presión por fricción: " + Html.fromHtml("<br/>") + String.valueOf(String.format("%.2f", dataManager.iPerdidas)) + " %" + Html.fromHtml("<br/><br/>") +
                                                "Gasto total: " + Html.fromHtml("<br/>") + String.valueOf(String.format("%.2f", (dataManager.iGastoPico/3.785))) + " gpm" + Html.fromHtml("<br/><br/>") +
                                                "Carga dinámica total: " + Html.fromHtml("<br/>") + String.valueOf(String.format("%.2f", dataManager.iCargaDinamica)) + " psi" + Html.fromHtml("<br/><br/>") +
                                                "Diámetro de tubo recomendado en la linea principal: " + Html.fromHtml("<br/>") + String.valueOf(String.format("%.2f", dataManager.iDiamPrincipal)) + " \"" + Html.fromHtml("<br/><br/>") +
                                                "Diámetro de tubo con circuito: " + Html.fromHtml("<br/>") + String.valueOf(String.format("%.2f", dataManager.iDiamCircuito)) + " \"" + Html.fromHtml("<br/><br/>") +
                                                "Enviado desde mi Android");

                                i.putExtra(Intent.EXTRA_STREAM, Uri.parse(Environment.getExternalStorageDirectory() + "/Android/data/com.smartplace.bombasmejorada/" + findPDF(pdfName)));
                                try {
                                    startActivity(Intent.createChooser(i, "Send mail..."));
                                } catch (android.content.ActivityNotFoundException ex) {
                                    Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                        alertDialog.setButton2("Ver", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // here you can add functions
                                pdfs.OpenPDF(getActivity(), findPDF(pdfName));

                            }
                        });

                        alertDialog.show();  //<-- See This!

//                        pdfs.OpenPDF(getActivity(),findPDF( ((TextView) ((LinearLayout) ((RelativeLayout) view).getChildAt(0)).getChildAt(1)).getText().toString()));

                    }
                });

                v= ((LinearLayout) ((RelativeLayout) resultsContainer.getChildAt(i)).getChildAt(0)).getChildAt(1);
                ((TextView) v).setText(pdfLabel[i]);


            }
        }
        else
        {
            RelativeLayout notFound = (RelativeLayout) getActivity().findViewById(R.id.notFoundBombas);
            notFound.setVisibility(View.VISIBLE);
        }


    }//end onResume

    @Override
    public void onPause ()
    {
        super.onPause();
        pdfResultsCount=0;
    }

    String findPDF(String label)
    {
        if (label == "CI2P20AU2P36Y220PP")
        {
            pdfName = "zCI2P20AU2P36Y220PP.pdf";
        }

        else if (label == "CI2P30AU2P47Y220PP")
        {
            pdfName = "zCI2P30AU2P47Y220PP.pdf";
        }

        else if (label == "CI2P20AU2P36Y220PP")
        {
            pdfName = "zCI2P20AU2P36Y220PP.pdf";
        }

        else if (label == "CI3P40AU3P55Y220PP")
        {
            pdfName = "zCI3P40AU3P55Y220PP.pdf";
        }

        else if (label == "CI15P10AU15P18B220PP")
        {
            pdfName = "zCI15P10AU15P18B220PP.pdf";
        }

        else if (label == "CI15P15AU15P22Y220PP")
        {
            pdfName = "zCI15P15AU15P22Y220PP.pdf";
        }

        return pdfName;
    }

    public void getResults(DataManager resultsDataManager)
    {

        if (dataManager.iGastoPico <= 400)
        {
            if (dataManager.iPresion <= 70)
            {
                pdfLabel[pdfResultsCount] = "CI15P15AU15P22Y220PP";
                pdfResultsCount++;
            }
        }

        if (dataManager.iGastoPico <= 1050)
        {
            if (dataManager.iPresion <= 40)
            {
                pdfLabel[pdfResultsCount] = "CI2P20AU2P36Y220PP";
                pdfResultsCount++;
            }
        }

        if (dataManager.iGastoPico <= 1250)
        {
            if (dataManager.iPresion <= 50)
            {
                pdfLabel[pdfResultsCount] = "CI2P30AU2P47Y220PP";
                pdfResultsCount++;
            }
        }

        if (dataManager.iGastoPico <= 1000)
        {
            if (dataManager.iPresion <= 85)
            {
                pdfLabel[pdfResultsCount] = "CI2P20AU2P36Y220PP";
                pdfResultsCount++;
            }
        }

        if (dataManager.iGastoPico <= 2500)
        {
            if (dataManager.iPresion <= 5)
            {
                pdfLabel[pdfResultsCount] = "CI3P40AU3P55Y220PP";
                pdfResultsCount++;
            }
        }

        if (dataManager.iGastoPico <= 460)
        {
            if (dataManager.iPresion <= 50)
            {
                pdfLabel[pdfResultsCount] = "CI15P10AU15P18B220PP";
                pdfResultsCount++;
            }
        }
    }

}
