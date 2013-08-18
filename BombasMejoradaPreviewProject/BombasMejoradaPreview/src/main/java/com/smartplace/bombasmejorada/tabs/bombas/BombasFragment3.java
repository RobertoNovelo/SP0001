package com.smartplace.bombasmejorada.tabs.bombas;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Fragment;

import com.smartplace.assets.PDFHandler;
import com.smartplace.bombasmejorada.R;
import com.smartplace.bombasmejorada.tabs.DataManager;
import com.smartplace.bombasmejorada.tabs.TabsMainActivity;

/**
 * Created by ROBERTO on 26/06/13.
 */
public class BombasFragment3 extends Fragment {

    String[] pdfLabel   = new String[30];
    int pdfResultsCount = 0;
    int i;

    PDFHandler pdfs = new PDFHandler(Environment.getExternalStorageDirectory() + "/BombasMejorada/");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.tab_bombas_3, container, false);

    }


    @Override
    public void onResume ()
    {
        super.onResume();

        DataManager dataManager = ((TabsMainActivity)getActivity()).getDataManager();
        TextView label;

        getResults(dataManager);

        label = (TextView) getActivity().findViewById(R.id.instalacionLabel);
        label.setText(dataManager.EnergySource);

        label = (TextView) getActivity().findViewById(R.id.gastoLabel);
        label.setText(String.format("%s lpm",dataManager.lpm));

        label = (TextView) getActivity().findViewById(R.id.presionLabel);
        label.setText(String.format("%s psi",dataManager.psi));

        label = (TextView) getActivity().findViewById(R.id.rEncontrados);
        label.setText(String.format("%s",pdfResultsCount));


        LinearLayout resultsContainer = (LinearLayout) getActivity().findViewById(R.id.resultsContainer);

        if (pdfResultsCount > 0)
        {
            View v;
            v = getActivity().findViewById(R.id.rpdfbombas);
            v.setVisibility(View.VISIBLE);

            for (i =0;i<pdfResultsCount;i++)
            {
                v = resultsContainer.getChildAt(i);
                v.setVisibility(View.VISIBLE);


                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        pdfs.OpenPDF(getActivity(),findPDF( ((TextView) ((LinearLayout) ((RelativeLayout) view).getChildAt(0)).getChildAt(1)).getText().toString()));

                    }
                });

                v= ((LinearLayout) ((RelativeLayout) resultsContainer.getChildAt(i)).getChildAt(0)).getChildAt(1);
                ((TextView) v).setText(pdfLabel[i]);


            }
        }
        else
        {
            label = (TextView) getActivity().findViewById(R.id.notFoundBombas);
            label.setVisibility(View.VISIBLE);
        }


//        RelativeLayout modelo3 = (RelativeLayout) getActivity().findViewById(R.id.modelo3);
//
//        modelo3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                RelativeLayout modelo3 = (RelativeLayout) getActivity().findViewById(R.id.modelo3);
//                PDFHandler pdfs = new PDFHandler(Environment.getExternalStorageDirectory() + "/BombasMejorada/");
//                pdfs.OpenPDF(getActivity(),"Modelo3P.pdf");
//                modelo3.setPressed(true);
//
//                Toast.makeText(getActivity(),"modelo 3", Toast.LENGTH_SHORT).show();
//
//
//                if ( getActivity().getResources().getDrawable(R.drawable.custom_button_green)== modelo3.getBackground())
//                {
//
//                    modelo3.setBackgroundResource(R.drawable.custom_button_dark_green);
//                    modelo3.bringToFront();
//
//                    Toast.makeText(getActivity(),"darkgreen set", Toast.LENGTH_SHORT).show();
//
//                }
//                else
//                {
//
//                    modelo3.setBackground( getActivity().getResources().getDrawable(R.drawable.custom_button_green));
//
//                }
//
//            }
//        });
//        RelativeLayout modelo4 = (RelativeLayout) getActivity().findViewById(R.id.modelo4);
//
//        modelo4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                RelativeLayout modelo4 = (RelativeLayout) getActivity().findViewById(R.id.modelo4);
//                PDFHandler pdfs = new PDFHandler(Environment.getExternalStorageDirectory() + "/BombasMejorada/");
//                pdfs.OpenPDF(getActivity(),"Modelo4P.pdf");
//                if ( getActivity().getResources().getDrawable(R.drawable.custom_button_green)== modelo4.getBackground())
//                {
//
//                    modelo4.setBackground( getActivity().getResources().getDrawable(R.drawable.custom_button_dark_green));
//
//                }
//                else
//                {
//
//                    modelo4.setBackground( getActivity().getResources().getDrawable(R.drawable.custom_button_green));
//
//                }
//            }
//        });
//
//        Button btnEnviar = (Button) getActivity().findViewById(R.id.enviarbutton);
//
//        btnEnviar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent i = new Intent(Intent.ACTION_SEND);
//                i.setType("message/rfc822");
//                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"robertod.novelo@gmail.com"});
//                i.putExtra(Intent.EXTRA_SUBJECT, Html.fromHtml("Solicito Informaci&oacute;n v&iacute;a Android").toString());
//                i.putExtra(Intent.EXTRA_TEXT, Html.fromHtml("Bombas - Hoja de c&aacute;lculo:<br/>" +
//                        "<br/>" +
//                        "1.Fuente de energ&iacute;a: <br/>" +
//                        "2.Gasto pico m&aacute;ximo: <br/>" +
//                        "3.Presi&oacute;n:           <br/>" +
//                        "<br/><br/>" +
//                        "Enviado desde mi Android").toString());
//                try {
//                    startActivity(Intent.createChooser(i, "Send mail..."));
//                } catch (android.content.ActivityNotFoundException ex) {
//                    Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
    } //end onResume

    String findPDF(String label)
    {
        String pdfName=null;

        if (label == "Modelo 3")
        {
            pdfName = "Modelo3.pdf";
        }

        else if (label == "Modelo 4")
        {
            pdfName = "Modelo4P.pdf";
        }

        else if (label == "Modelo 5")
        {
            pdfName = "Modelo5.pdf";
        }

        else if (label == "Modelo 6")
        {
            pdfName = "Modelo6.pdf";
        }

        else if (label == "Modelo 7")
        {
            pdfName = "Modelo7.pdf";
        }

        else if (label == "Modelo 8")
        {
            pdfName = "Modelo8.pdf";
        }

        else if (label == "Modelo 1P")
        {
            pdfName = "Modelo1P.pdf";
        }

        else if (label == "Modelo 1.5P")
        {
            pdfName = "Modelo1.5P.pdf";
        }

        else if (label == "Modelo 2P")
        {
            pdfName = "Modelo2P.pdf";
        }

        else if (label == "Modelo 3P")
        {
            pdfName = "Modelo3P.pdf";
        }

        else if (label == "Modelo 4P")
        {
            pdfName = "Modelo4P.pdf";
        }

        else if (label == "Modelo M4P")
        {
            pdfName = "Modelo4P.pdf";
        }

        else if (label == "Modelo 5P")
        {
            pdfName = "Modelo5.pdf";
        }

        else if (label == "Modelo 5T")
        {
            pdfName = "Modelo5.pdf";
        }

        else if (label == "Modelo 6P")
        {
            pdfName = "Modelo6P.pdf";
        }

        else if (label == "Modelo 6M")
        {
            pdfName = "Modelo6M.pdf";
        }

        else if (label == "Modelo 8P")
        {
            pdfName = "Modelo8P.pdf";
        }

        return pdfName;

    }

    public void getResults(DataManager resultsDataManager)
    {
        int gastoLpm = resultsDataManager.lpm;
        int presion = resultsDataManager.psi;

        pdfResultsCount=0;


        if (gastoLpm < 180)
        {
            if ((presion >= 1) && (presion <= 38))
            {
                pdfLabel[pdfResultsCount] = "Modelo 3";
                pdfResultsCount++;
            }
        }

        if (gastoLpm <= 240)
        {
            if ((presion >= 1) && (presion <= 20))
            {
//                [myArray addObject:[NSString stringWithFormat:@"Modelo 4"]];
                pdfLabel[pdfResultsCount] = "Modelo 4";
                pdfResultsCount++;
            }
        }

        if ((gastoLpm >= 180) && (gastoLpm <= 340))
        {
            if ((presion >= 1) && (presion <= 42))
            {
//                [myArray addObject:[NSString stringWithFormat:@"Modelo 5"]];
                pdfLabel[pdfResultsCount] = "Modelo 5";
                pdfResultsCount++;
            }
        }

        if ((gastoLpm >= 350) && (gastoLpm <= 650))
        {
            if ((presion >= 1) && (presion <= 44))
            {
//                [myArray addObject:[NSString stringWithFormat:@"Modelo 6"]];
                pdfLabel[pdfResultsCount] = "Modelo 6";
                pdfResultsCount++;
            }
        }

        if ((gastoLpm >= 550) && (gastoLpm <= 1000))
        {
            if ((presion >= 1) && (presion <= 38))
            {
//                [myArray addObject:[NSString stringWithFormat:@"Modelo 7"]];
                pdfLabel[pdfResultsCount] = "Modelo 7";
                pdfResultsCount++;
            }
        }

        if ((gastoLpm >= 900) && (gastoLpm <= 1400))
        {
            if ((presion >= 1) && (presion <= 36))
            {
//                [myArray addObject:[NSString stringWithFormat:@"Modelo 8"]];
                pdfLabel[pdfResultsCount] = "Modelo 8";
                pdfResultsCount++;
            }
        }

        if ((gastoLpm >= 110) && (gastoLpm <= 220))
        {
            if ((presion >= 40) && (presion <= 90))
            {
//                [myArray addObject:[NSString stringWithFormat:@"Modelo 1P"]];
                pdfLabel[pdfResultsCount] = "Modelo 1P";
                pdfResultsCount++;
            }
        }

        if ((gastoLpm >= 200) && (gastoLpm <= 400))
        {
            if ((presion >= 40) && (presion <= 90))
            {
//                [myArray addObject:[NSString stringWithFormat:@"Modelo 1.5P"]];
                pdfLabel[pdfResultsCount] = "Modelo 1.5P";
                pdfResultsCount++;
            }
        }

        if ((gastoLpm >= 600) && (gastoLpm <= 1000))
        {
            if ((presion >= 35) && (presion <= 110))
            {
//                [myArray addObject:[NSString stringWithFormat:@"Modelo 2P"]];
                pdfLabel[pdfResultsCount] = "Modelo 2P";
                pdfResultsCount++;
            }
        }

        if ((gastoLpm >= 900) && (gastoLpm <= 1350))
        {
            if ((presion >= 45) && (presion <= 120))
            {
//                [myArray addObject:[NSString stringWithFormat:@"Modelo 3P"]];
                pdfLabel[pdfResultsCount] = "Modelo 3P";
                pdfResultsCount++;
            }
        }

        if ((gastoLpm >= 1200) && (gastoLpm <= 1650))
        {
            if ((presion >= 45) && (presion <= 120))
            {
//                [myArray addObject:[NSString stringWithFormat:@"Modelo 4P"]];
                pdfLabel[pdfResultsCount] = "Modelo 4P";
                pdfResultsCount++;
            }
        }

        if ((gastoLpm >= 1700) && (gastoLpm <= 2400))
        {
            if ((presion >= 45) && (presion <= 120))
            {
//                [myArray addObject:[NSString stringWithFormat:@"Modelo M4P"]];
                pdfLabel[pdfResultsCount] = "Modelo M4P";
                pdfResultsCount++;
            }
        }

        if ((gastoLpm >= 900) && (gastoLpm <= 1500))
        {
            if ((presion >= 20) && (presion <= 70))
            {
//                [myArray addObject:[NSString stringWithFormat:@"Modelo 5P"]];
                pdfLabel[pdfResultsCount] = "Modelo 5P";
                pdfResultsCount++;
            }
        }

        if ((gastoLpm >= 2000) && (gastoLpm <= 3000))
        {
            if ((presion >= 20) && (presion <= 55))
            {
//                [myArray addObject:[NSString stringWithFormat:@"Modelo 5T"]];
                pdfLabel[pdfResultsCount] = "Modelo 5T";
                pdfResultsCount++;
            }
        }

        if ((gastoLpm >= 2200) && (gastoLpm <= 3400))
        {
            if ((presion >= 20) && (presion <= 70))
            {
//                [myArray addObject:[NSString stringWithFormat:@"Modelo 6P"]];
                pdfLabel[pdfResultsCount] = "Modelo 6P";
                pdfResultsCount++;
            }
        }

        if ((gastoLpm >= 1800) && (gastoLpm <= 5000))
        {
            if ((presion >= 1) && (presion <= 25))
            {
//                [myArray addObject:[NSString stringWithFormat:@"Modelo 6M"]];
                pdfLabel[pdfResultsCount] = "Modelo 6M";
                pdfResultsCount++;
            }
        }

        if ((gastoLpm >= 2800) && (gastoLpm <= 5000))
        {
            if ((presion >= 20) && (presion <= 70))
            {
//                [myArray addObject:[NSString stringWithFormat:@"Modelo 8P"]];
                pdfLabel[pdfResultsCount] = "Modelo 8P";
                pdfResultsCount++;
            }
        }
    }



}
