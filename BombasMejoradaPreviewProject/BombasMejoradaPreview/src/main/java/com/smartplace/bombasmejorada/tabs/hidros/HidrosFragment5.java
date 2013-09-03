package com.smartplace.bombasmejorada.tabs.hidros;

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
 * Created by uidv6409 on 1/09/13.
 */
public class HidrosFragment5 extends Fragment {

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

        return inflater.inflate(R.layout.tab_hidros_5, container, false);

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
            v = getActivity().findViewById(R.id.rpdfHidros);

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
                                                "Instalación: " + Html.fromHtml("<br/>") + dataManager.Edificio + Html.fromHtml("<br/><br/>") +
                                                "Salidas: " + Html.fromHtml("<br/>") + dataManager.hSalidas + Html.fromHtml("<br/><br/>") +
                                                "Factor por salida: " + Html.fromHtml("<br/>") + dataManager.hFactorPorSalida + " lpm/salida" + Html.fromHtml("<br/><br/>") +
                                                "Desnivel total máximo: " + Html.fromHtml("<br/>") + dataManager.hDistVertical + " mts" + Html.fromHtml("<br/><br/>") +
                                                "Longitud de tubería máxima: " + Html.fromHtml("<br/>") + dataManager.hLongSalida + " mts" + Html.fromHtml("<br/><br/>") +
                                                "Presión en Salida: " + Html.fromHtml("<br/>") + dataManager.hPresionSalida + " psi" + Html.fromHtml("<br/><br/>") +
                                                "Porcentaje máximo de de pérdidas en la conducción: " + Html.fromHtml("<br/>") + String.valueOf(String.format("%.2f",dataManager.hPorcentajePerdidas)) + "%" + Html.fromHtml("<br/><br/>") +
                                                "Gasto pico máximo: " + Html.fromHtml("<br/>") + String.valueOf(String.format("%.2f",dataManager.hGastoPico)) + " lpm" + Html.fromHtml("<br/><br/>") +
                                                "Carga dinámica total: " + Html.fromHtml("<br/>") + String.valueOf(String.format("%.2f",dataManager.hCargaDinamica)) + " psi" + Html.fromHtml("<br/><br/>") +
                                                "Diámetro de tubo recomendado en la linea principal: " + Html.fromHtml("<br/>") + String.valueOf(String.format("%.2f",dataManager.hDiametroTubo)) + '"' + Html.fromHtml("<br/><br/>") +
                                                "Enviado desde mi Android" );

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
        String pdfName=null;

        if (label == "Presurizador BM")
        {
            pdfName = "yPresurizadorBM.pdf";
        }

        else if (label == "EM3150MECW")
        {
            pdfName = "yEM3152CS220V2G.pdf";
        }

        else if (label == "EM3200MECW")
        {
            pdfName = "yEM3202CS220V2G.pdf";
        }

        else if (label == "EM3300MECS")
        {
            pdfName = "yEM3302CS220V2G.pdf";
        }

        else if (label == "EM3300MECW")
        {
            pdfName = "yEM3302CS220V2G.pdf";
        }

        else if (label == "EM5300MECW")
        {
            pdfName = "yEMA71.5CS3220V.pdf";
        }

        else if (label == "EM5500MEAU")
        {
            pdfName = "yEMA71.5CS5220V.pdf";
        }

        else if (label == "EM5500MECS")
        {
            pdfName = "yEMA71.5CS5220V.pdf";
        }

        else if (label == "EM1P500MEAU")
        {
            pdfName = "yEM1Bombas.pdf";
        }

        //Multipresurizador EM 2 Bombas
        else if (label == "VF2EM3150CW220N")
        {
            pdfName = "yEM2y3Bombas.pdf";
        }

        else if (label == "VF2EM3150CW220P")
        {
            pdfName = "yEM2y3Bombas.pdf";
        }

        else if (label == "VF2EM3200CWN220")
        {
            pdfName = "yEM2y3Bombas.pdf";
        }

        else if (label == "VF2EM3200CWP220")
        {
            pdfName = "yEM2y3Bombas.pdf";
        }

        else if (label == "VF2EM3300CWN220")
        {
            pdfName = "yEM2y3Bombas.pdf";
        }

        else if (label == "VF2EM3300CWP220")
        {
            pdfName = "yEM2y3Bombas.pdf";
        }

        else if (label == "VF2EM5300CWN220")
        {
            pdfName = "yEM2y3Bombas.pdf";
        }

        else if (label == "VF2EM5500AUN220")
        {
            pdfName = "yEM2y3Bombas.pdf";
        }

        else if (label == "VF2EM5500AUP220")
        {
            pdfName = "yEM2y3Bombas.pdf";
        }

        else if (label == "VF2EM1P500AUN220")
        {
            pdfName = "yEM2y3Bombas.pdf";
        }

        else if (label == "VF2EM1P500AUP220")
        {
            pdfName = "yEM2y3Bombas.pdf";
        }

        //Multipresurizador EM 3 Bombas
        else if (label == "VF3EM5300CWN220E")
        {
            pdfName = "yEM2y3Bombas.pdf";
        }

        else if (label == "VF3EM5300CWP220")
        {
            pdfName = "yEM2y3Bombas.pdf";
        }

        else if (label == "VF3EM5500AUN220E")
        {
            pdfName = "yEM2y3Bombas.pdf";
        }

        else if (label == "VF3EM5500AUP220")
        {
            pdfName = "yEM2y3Bombas.pdf";
        }

        else if (label == "VF3EM1P500AUN220")
        {
            pdfName = "yEM2y3Bombas.pdf";
        }

        else if (label == "VF3EM1P500AUP220")
        {
            pdfName = "yEM2y3Bombas.pdf";
        }

        //Modelo EfiMax 1 Bomba
        else if (label == "EF5752AUP440V")
        {
            pdfName = "yEfiMax1.pdf";
        }

        else if (label == "EF6752AUN440V")
        {
            pdfName = "yEfiMax1.pdf";
        }

        else if (label == "EF61002AUP440V")
        {
            pdfName = "yEfiMax1.pdf";
        }

        else if (label == "EF7752AUP440V")
        {
            pdfName = "yEfiMax1.pdf";
        }

        else if (label == "EF71002AUP440V")
        {
            pdfName = "yEfiMax1.pdf";
        }

        else if (label == "EF71502AUP440V")
        {
            pdfName = "yEfiMax1.pdf";
        }

        else if (label == "EF81502AUP220V")
        {
            pdfName = "yEfiMax1.pdf";
        }

        else if (label == "EF1P502AUP220V")
        {
            pdfName = "yEfiMax1.pdf";
        }

        else if (label == "EF1P1002AUP220V")
        {
            pdfName = "yEfiMax1.pdf";
        }

        else if (label == "EF1P1502AUP220V")
        {
            pdfName = "yEfiMax1.pdf";
        }

        else if (label == "EF1P752AUN220V")
        {
            pdfName = "yEfiMax1.pdf";
        }

        else if (label == "EF15P1002AUP220V")
        {
            pdfName = "yEfiMax1.pdf";
        }

        else if (label == "EF15P1502AUP220V")
        {
            pdfName = "yEfiMax1.pdf";
        }

        else if (label == "EF2P1502CWN440V")
        {
            pdfName = "yEfiMax1.pdf";
        }

        else if (label == "EF2P2002AUP220V")
        {
            pdfName = "yEfiMax1.pdf";
        }

        else if (label == "EF2P2502AUP220V")
        {
            pdfName = "yEfiMax1.pdf";
        }

        else if (label == "EF2P3002AUP440V")
        {
            pdfName = "yEfiMax1.pdf";
        }

        else if (label == "EF3P1502AUN440V")
        {
            pdfName = "yEfiMax1.pdf";
        }

        else if (label == "EF3P2002AUP440V")
        {
            pdfName = "yEfiMax1.pdf";
        }

        else if (label == "EF3P3002CUN440V")
        {
            pdfName = "yEfiMax1.pdf";
        }

        else if (label == "EF4P2002AUP440V")
        {
            pdfName = "yEfiMax1.pdf";
        }

        else if (label == "EF4P2502AUP440V")
        {
            pdfName = "yEfiMax1.pdf";
        }

        else if (label == "EF4P3002AUN440V")
        {
            pdfName = "yEfiMax1.pdf";
        }

        else if (label == "EF4P4002AUP440V")
        {
            pdfName = "yEfiMax1.pdf";
        }

        else if (label == "EF5P2504AUP440V")
        {
            pdfName = "yEfiMax1.pdf";
        }

        else if (label == "EF6P2504AUP440V")
        {
            pdfName = "yEfiMax1.pdf";
        }

        else if (label == "EF6P3004AUP440V")
        {
            pdfName = "yEfiMax1.pdf";
        }

        else if (label == "EF6P4004AUP440V")
        {
            pdfName = "yEfiMax1.pdf";
        }

        //Modelo EfiMax 3 Bombas
        else if (label == "VF3EF5752AUN220")
        {
            pdfName = "yEfiMax2y3.pdf";
        }

        else if (label == "VF3EF61002AUP440")
        {
            pdfName = "yEfiMax2y3.pdf";
        }

        else if (label == "VF3EF61002CUN220E")
        {
            pdfName = "yEfiMax2y3.pdf";
        }

        else if (label == "VF3EF71502AUN220")
        {
            pdfName = "yEfiMax2y3.pdf";
        }

        else if (label == "VF3EF1P502AUP440")
        {
            pdfName = "yEfiMax2y3.pdf";
        }

        else if (label == "VF3EF1P502AUP480")
        {
            pdfName = "yEfiMax2y3.pdf";
        }

        else if (label == "VF3EF1P752AUN220")
        {
            pdfName = "yEfiMax2y3.pdf";
        }

        else if (label == "VF3EF1P1002AUP220")
        {
            pdfName = "yEfiMax2y3.pdf";
        }

        else if (label == "VF3EF1P1502CSN220")
        {
            pdfName = "yEfiMax2y3.pdf";
        }

        else if (label == "VF3EF71502AUN220")
        {
            pdfName = "yEfiMax2y3.pdf";
        }

        return pdfName;

    }//end find PDF

    public void getResults(DataManager resultsDataManager)
    {
        double gastoPico = resultsDataManager.hGastoPico;
        double salidas = resultsDataManager.hSalidas;
        double presion = resultsDataManager.hPresionSalida;
        double cargaDinamica = resultsDataManager.hCargaDinamica;


        pdfResultsCount=0;


        if (gastoPico <= 110)
        {
            if (salidas <= 30)
            {
                if (presion <= 35)
                {
                    pdfLabel[pdfResultsCount] ="Presurizador BM";
                    pdfResultsCount++;
                }
            }

        }

        //Presurizador EM 1 Bomba
        if (gastoPico <= 170)
        {
            if (cargaDinamica <= 21) //mca
            {
                pdfLabel[pdfResultsCount] ="EM3150MECW"; pdfResultsCount++;
                pdfLabel[pdfResultsCount] ="EM3200MECW"; pdfResultsCount++;
            }

            if (cargaDinamica <= 28) //mca
            {
                pdfLabel[pdfResultsCount] ="EM3300MECS"; pdfResultsCount++;
                pdfLabel[pdfResultsCount] ="EM3300MECW"; pdfResultsCount++;
            }
        }


        if ((gastoPico >= 250) && (gastoPico <= 375))
        {
            if ((cargaDinamica >= 21) && (cargaDinamica <= 28))  //mca
            {
                pdfLabel[pdfResultsCount] ="EM5300MECW"; pdfResultsCount++;
            }

            if ((cargaDinamica >= 28) && (cargaDinamica <= 35))  //mca
            {
                pdfLabel[pdfResultsCount] ="EM5500MEAU"; pdfResultsCount++;
                pdfLabel[pdfResultsCount] ="EM5500MECS"; pdfResultsCount++;
            }
        }


        if ((gastoPico >= 170) && (gastoPico <= 375))
        {
            if ((cargaDinamica >= 28) && (cargaDinamica <= 42))  //mca
            {
                pdfLabel[pdfResultsCount] ="EM1P500MEAU"; pdfResultsCount++;
            }
        }

        //MultiPresurizador EM 2 Bombas
        if ((gastoPico >= 250) && (gastoPico <= 340))
        {
            if (cargaDinamica <= 21)  //mca
            {
                pdfLabel[pdfResultsCount] ="VF2EM3150CW220N"; pdfResultsCount++;
                pdfLabel[pdfResultsCount] ="VF2EM3150CW220P"; pdfResultsCount++;
            }

            if ((cargaDinamica >= 21) && (cargaDinamica <= 28))  //mca
            {
                pdfLabel[pdfResultsCount] ="VF2EM3200CWN220"; pdfResultsCount++;
                pdfLabel[pdfResultsCount] ="VF2EM3200CWP220"; pdfResultsCount++;
            }

            if ((cargaDinamica >= 28) && (cargaDinamica <= 35))  //mca
            {
                pdfLabel[pdfResultsCount] ="VF2EM3300CWN220"; pdfResultsCount++;
                pdfLabel[pdfResultsCount] ="VF2EM3300CWP220"; pdfResultsCount++;
            }
        }

        if ((gastoPico >= 340) && (gastoPico <= 750))
        {

            if ((cargaDinamica >= 21) && (cargaDinamica <= 28))  //mca
            {
                pdfLabel[pdfResultsCount] ="VF2EM5300CWN220"; pdfResultsCount++;
            }
        }

        if ((gastoPico >= 500) && (gastoPico <= 750))
        {

            if ((cargaDinamica >= 21) && (cargaDinamica <= 35))  //mca
            {
                pdfLabel[pdfResultsCount] ="VF2EM5500AUN220"; pdfResultsCount++;
                pdfLabel[pdfResultsCount] ="VF2EM5500AUP220"; pdfResultsCount++;
            }

            if ((cargaDinamica >= 35) && (cargaDinamica <= 42))  //mca
            {
                pdfLabel[pdfResultsCount] ="VF2EM1P500AUN220"; pdfResultsCount++;
                pdfLabel[pdfResultsCount] ="VF2EM1P500AUP220"; pdfResultsCount++;
            }
        }

        //Multipresurizador EM 3 Bombas
        if ((gastoPico >= 750) && (gastoPico <= 1125))
        {

            if ((cargaDinamica >= 21) && (cargaDinamica <= 28))  //mca
            {
                pdfLabel[pdfResultsCount] ="VF3EM5300CWN220E"; pdfResultsCount++;
                pdfLabel[pdfResultsCount] ="VF3EM5300CWP220"; pdfResultsCount++;
            }

            if ((cargaDinamica >= 28) && (cargaDinamica <= 35))  //mca
            {
                pdfLabel[pdfResultsCount] ="VF3EM5500AUN220E"; pdfResultsCount++;
                pdfLabel[pdfResultsCount] ="VF3EM5500AUP220"; pdfResultsCount++;
            }

            if ((cargaDinamica >= 35) && (cargaDinamica <= 42))  //mca
            {
                pdfLabel[pdfResultsCount] ="VF3EM1P500AUN220"; pdfResultsCount++;
                pdfLabel[pdfResultsCount] ="VF3EM1P500AUP220"; pdfResultsCount++;
            }

        }


        //Modelo EfiMax 1 Bomba
        if ((gastoPico >= 375) && (gastoPico <= 500))
        {

            if ((cargaDinamica >= 35) && (cargaDinamica <= 42))  //mca
            {
                pdfLabel[pdfResultsCount] ="EF5752AUP440V"; pdfResultsCount++;
            }
        }

        if ((gastoPico >= 500) && (gastoPico <= 650))
        {

            if ((cargaDinamica >= 21) && (cargaDinamica <= 28))  //mca
            {
                pdfLabel[pdfResultsCount] ="EF6752AUN440V"; pdfResultsCount++;
            }

            if ((cargaDinamica >= 35) && (cargaDinamica <= 42))  //mca
            {
                pdfLabel[pdfResultsCount] ="EF61002AUP440V"; pdfResultsCount++;
            }
        }

        if ((gastoPico >= 650) && (gastoPico <= 1000))
        {

            if ((cargaDinamica >= 21) && (cargaDinamica <= 28))  //mca
            {
                pdfLabel[pdfResultsCount] ="EF7752AUP440V"; pdfResultsCount++;
            }
        }

        if ((gastoPico >= 800) && (gastoPico <= 1000))
        {

            if ((cargaDinamica >= 21) && (cargaDinamica <= 28))  //mca
            {
                pdfLabel[pdfResultsCount] ="EF71002AUP440V"; pdfResultsCount++;
            }

            if ((cargaDinamica >= 28) && (cargaDinamica <= 35))  //mca
            {
                pdfLabel[pdfResultsCount] ="EF71502AUP440V"; pdfResultsCount++;
            }
        }

        if ((gastoPico >= 1000) && (gastoPico <= 1250))
        {

            if ((cargaDinamica >= 21) && (cargaDinamica <= 28))  //mca
            {
                pdfLabel[pdfResultsCount] ="EF81502AUP220V"; pdfResultsCount++;
            }
        }

        if ((gastoPico >= 170) && (gastoPico <= 375))
        {

            if ((cargaDinamica >= 35) && (cargaDinamica <= 42))  //mca
            {
                pdfLabel[pdfResultsCount] ="EF1P502AUP220V"; pdfResultsCount++;
            }

            if ((cargaDinamica >= 42) && (cargaDinamica <= 70))  //mca
            {
                pdfLabel[pdfResultsCount] ="EF1P1002AUP220V"; pdfResultsCount++;
            }

            if ((cargaDinamica >= 70) && (cargaDinamica <= 90))  //mca
            {
                pdfLabel[pdfResultsCount] ="EF1P1502AUP220V"; pdfResultsCount++;
            }
        }

        if ((gastoPico >= 170) && (gastoPico <= 250))
        {

            if ((cargaDinamica >= 42) && (cargaDinamica <= 56))  //mca
            {
                pdfLabel[pdfResultsCount] ="EF1P752AUN220V"; pdfResultsCount++;
            }
        }

        if ((gastoPico >= 170) && (gastoPico <= 500))
        {

            if ((cargaDinamica >= 42) && (cargaDinamica <= 63))  //mca
            {
                pdfLabel[pdfResultsCount] ="EF15P1002AUP220V"; pdfResultsCount++;
            }

            if ((cargaDinamica >= 63) && (cargaDinamica <= 77))  //mca
            {
                pdfLabel[pdfResultsCount] ="EF15P1502AUP220V"; pdfResultsCount++;
            }
        }

        if ((gastoPico >= 500) && (gastoPico <= 800))
        {

            if ((cargaDinamica >= 42) && (cargaDinamica <= 56))  //mca
            {
                pdfLabel[pdfResultsCount] ="EF2P1502CWN440V"; pdfResultsCount++;
            }

            if ((cargaDinamica >= 42) && (cargaDinamica <= 49))  //mca
            {
                pdfLabel[pdfResultsCount] ="EF2P2002AUP220V"; pdfResultsCount++;
            }

            if ((cargaDinamica >= 56) && (cargaDinamica <= 70))  //mca
            {
                pdfLabel[pdfResultsCount] ="EF2P2502AUP220V"; pdfResultsCount++;
            }

            if ((cargaDinamica >= 70) && (cargaDinamica <= 84))  //mca
            {
                pdfLabel[pdfResultsCount] ="EF2P3002AUP440V"; pdfResultsCount++;
            }
        }

        if ((gastoPico >= 1000) && (gastoPico <= 1250))
        {

            if ((cargaDinamica >= 35) && (cargaDinamica <= 42))  //mca
            {
                pdfLabel[pdfResultsCount] ="EF3P1502AUN440V"; pdfResultsCount++;
            }

            if ((cargaDinamica >= 42) && (cargaDinamica <= 49))  //mca
            {
                pdfLabel[pdfResultsCount] ="EF3P2002AUP440V"; pdfResultsCount++;
            }

            if ((cargaDinamica >= 49) && (cargaDinamica <= 63))  //mca
            {
                pdfLabel[pdfResultsCount] ="EF3P3002CUN440V"; pdfResultsCount++;
            }
        }

        if ((gastoPico >= 390) && (gastoPico <= 1690))
        {

            if ((cargaDinamica >= 45) && (cargaDinamica <= 55))  //mca
            {
                pdfLabel[pdfResultsCount] ="EF4P2002AUP440V"; pdfResultsCount++;
            }
        }

        if ((gastoPico >= 1500) && (gastoPico <= 1750))
        {

            if ((cargaDinamica >= 28) && (cargaDinamica <= 42))  //mca
            {
                pdfLabel[pdfResultsCount] ="EF4P2502AUP440V"; pdfResultsCount++;
            }

            if ((cargaDinamica >= 42) && (cargaDinamica <= 56))  //mca
            {
                pdfLabel[pdfResultsCount] ="EF4P3002AUN440V"; pdfResultsCount++;
            }

            if ((cargaDinamica >= 56) && (cargaDinamica <= 63))  //mca
            {
                pdfLabel[pdfResultsCount] ="EF4P4002AUP440V"; pdfResultsCount++;
            }
        }

        if ((gastoPico >= 984) && (gastoPico <= 2233))
        {

            if ((cargaDinamica >= 29) && (cargaDinamica <= 53))  //mca
            {
                pdfLabel[pdfResultsCount] ="EF5P2504AUP440V"; pdfResultsCount++;
            }
        }


        if ((gastoPico >= 600) && (gastoPico <= 3597))
        {

            if ((cargaDinamica >= 28) && (cargaDinamica <= 42))  //mca
            {
                pdfLabel[pdfResultsCount] ="EF6P2504AUP440V"; pdfResultsCount++;
            }

            if ((cargaDinamica >= 34) && (cargaDinamica <= 50))  //mca
            {
                pdfLabel[pdfResultsCount] ="EF6P3004AUP440V"; pdfResultsCount++;
            }

            if ((cargaDinamica >= 42) && (cargaDinamica <= 58))  //mca
            {
                pdfLabel[pdfResultsCount] ="EF6P4004AUP440V"; pdfResultsCount++;
            }
        }


        //Modelo EfiMax 3 Bombas
        if ((gastoPico >= 1125) && (gastoPico <= 1500))
        {

            if ((cargaDinamica >= 35) && (cargaDinamica <= 42))  //mca
            {
                pdfLabel[pdfResultsCount] ="VF3EF5752AUN220"; pdfResultsCount++;
            }
        }

        if ((gastoPico >= 1500) && (gastoPico <= 1950))
        {

            if ((cargaDinamica >= 35) && (cargaDinamica <= 42))  //mca
            {
                pdfLabel[pdfResultsCount] ="VF3EF61002AUP440"; pdfResultsCount++;
            }

            if ((cargaDinamica >= 35) && (cargaDinamica <= 42))  //mca
            {
                pdfLabel[pdfResultsCount] ="VF3EF61002CUN220E"; pdfResultsCount++;
            }
        }

        if ((gastoPico >= 1400) && (gastoPico <= 3000))
        {

            if ((cargaDinamica >= 28) && (cargaDinamica <= 35))  //mca
            {
                pdfLabel[pdfResultsCount] ="VF3EF71502AUN220"; pdfResultsCount++;
            }
        }

        if ((gastoPico >= 510) && (gastoPico <= 1125))
        {

            if ((cargaDinamica >= 35) && (cargaDinamica <= 42))  //mca
            {
                pdfLabel[pdfResultsCount] ="VF3EF1P502AUP440"; pdfResultsCount++;
            }

            if ((cargaDinamica >= 35) && (cargaDinamica <= 42))  //mca
            {
                pdfLabel[pdfResultsCount] ="VF3EF1P502AUP480"; pdfResultsCount++;
            }

            if ((cargaDinamica >= 42) && (cargaDinamica <= 56))  //mca
            {
                pdfLabel[pdfResultsCount] ="VF3EF1P752AUN220"; pdfResultsCount++;
            }

            if ((cargaDinamica >= 49) && (cargaDinamica <= 70))  //mca
            {
                pdfLabel[pdfResultsCount] ="VF3EF1P1002AUP220"; pdfResultsCount++;
            }

            if ((cargaDinamica >= 70) && (cargaDinamica <= 90))  //mca
            {
                pdfLabel[pdfResultsCount] ="VF3EF1P1502CSN220"; pdfResultsCount++;
            }
        }

        if ((gastoPico >= 2100) && (gastoPico <= 4500))
        {

            if ((cargaDinamica >= 50) && (cargaDinamica <= 65))  //mca
            {
                pdfLabel[pdfResultsCount] ="VF3EF71502AUN220"; pdfResultsCount++;
            }
        }
    }

}
