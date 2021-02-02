package com.example.jjherram.repasoexamen;

import android.Manifest;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button bMas;
    private Button bIgual;
    public String res = "";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_1, container, false);
        b2 = v.findViewById(R.id.button4);
        b1 = v.findViewById(R.id.button5);
        b3 = v.findViewById(R.id.button8);
        b4 = v.findViewById(R.id.button7);
        bMas = v.findViewById(R.id.button2);
        bIgual = v.findViewById(R.id.button6);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Escribir texto del boton
                res += b2.getText();

                //Permisos HARA FALTA DECLARAR LOS PERMISOS EN EL MANIFEST
                solicitarPermisoLocalizaciones(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,
                        "Se necesita permiso de ubicaión", 1);
                Intent intent = new Intent(getContext(), ServicioLocalizacion.class);
                getActivity().startService(intent);


            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Escribir texto del boton
                res += b4.getText();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Escribir texto del boton
                res += b3.getText();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Escribir texto del boton
                res += b1.getText();

                //Duplicar res
                if (res.length()<5){
                    bIgual.setText(res + res);
                }else{
                    //El boton no debe tener mas de 10 caracteres
                    bIgual.setText("=");
                }

            }
        });



        return v;
    }

    public class ReceptorOperacion extends BroadcastReceiver {
        public static final String ACTION_RESP= "com.example.exempleexam20192.LATITUD_LONGITUD";

        @Override
        public void onReceive(Context context, Intent intent) {
            double latUsu = intent.getDoubleExtra("latitud", 0.0);
            double longUsu = intent.getDoubleExtra("longitud", 0.0);
            bMas.setText(String.valueOf(latUsu) + "/" + String.valueOf(longUsu));
        }
    }

    //Funcion para solicitar los permisos de localizacion HARA FALTA DECLARAR EL SERVICIO EN EL MANIFEST
    public void solicitarPermisoLocalizaciones(final String fine, final String corase, String
            justificacion, final int requestCode/*, final View.OnClickListener actividad*/) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), fine)){
            new AlertDialog.Builder(getContext())
                    .setTitle("Solicitud de permiso")
                    .setMessage(justificacion)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            ActivityCompat.requestPermissions(getActivity()/*(Activity) actividad*/,
                                    new String[]{fine}, requestCode);
                            ActivityCompat.requestPermissions(getActivity()/*(Activity) actividad*/,
                                    new String[]{corase}, requestCode);
                        }}).show();

        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{fine}, requestCode);
        }
    }
}