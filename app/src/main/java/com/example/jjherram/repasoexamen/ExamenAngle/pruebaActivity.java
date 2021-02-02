package com.example.jjherram.repasoexamen.ExamenAngle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.jjherram.repasoexamen.Producto;
import com.example.jjherram.repasoexamen.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class pruebaActivity extends AppCompatActivity {

    static long DIAS[] = {1,3,8,10,13,20,29};
    static  String DIAS_SEMANA[]={"lun", "jue", "lun", "jue", "sab", "sab", "lun"};
    static double PRECIOS[]={1.2,1.1,1.2,1.1,1.2,1.3,1.2};
    public ArrayList<Producto> prods= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        CreaPojos();
        Map medias = new HashMap();
        Set<String> nombres = new HashSet();
        for (int i=0;i<DIAS.length;i++){
            nombres.add(DIAS_SEMANA[i]);
        }

        ArrayList<Double> mediasLunes = new ArrayList<>();
        ArrayList<Double> mediasJueves = new ArrayList<>();
        ArrayList<Double> mediasSabado = new ArrayList<>();
        for (int i=0;i<DIAS.length;i++){
            if (DIAS_SEMANA[i].equals("lun")){
                mediasLunes.add(PRECIOS[i]);
            }

            if (DIAS_SEMANA[i].equals("jue")){
                mediasJueves.add(PRECIOS[i]);
            }

            if (DIAS_SEMANA[i].equals("sab")){
                mediasSabado.add(PRECIOS[i]);
            }

        }
        double MediaLunes = 0;
        for (int i=0;i<mediasLunes.size()-1;i++){
            MediaLunes += mediasLunes.get(i+1) - mediasLunes.get(i);
        }
        medias.put("MediaLunes", MediaLunes/mediasLunes.size()-1);


        double MediaJueves = 0;
        for (int i=0;i<mediasJueves.size()-1;i++){
            MediaJueves += mediasJueves.get(i+1) - mediasJueves.get(i);
        }
        medias.put("MediaJueves", MediaJueves/mediasJueves.size()-1);


        double MediaSabado = 0;
        for (int i=0;i<mediasSabado.size()-1;i++){

            MediaSabado += mediasSabado.get(i+1) - mediasSabado.get(i);
        }
        medias.put("MediaSabado", MediaSabado/mediasSabado.size()-1);




        //Obtener el día con mayor subida de precio


        Map<String, Double> finalResult = new HashMap<>();
        int maxValue = -1;
        if (MediaJueves/mediasJueves.size() > MediaLunes/mediasLunes.size()){
            if (MediaJueves/mediasJueves.size() > MediaSabado/mediasSabado.size()){
                finalResult.put("jue", MediaJueves/mediasJueves.size());
            }else{
                finalResult.put("sab", MediaSabado/mediasSabado.size());
            }
        }else{
            if (MediaLunes/mediasLunes.size() > MediaSabado/mediasSabado.size()){
                finalResult.put("lun", MediaLunes/mediasLunes.size());
            }else{
                finalResult.put("sab", MediaSabado/mediasSabado.size());
            }
        }

        Log.d("DIA_MAYOR", finalResult.toString());


    }

    public void CreaPojos(){

        for (int i=0;i<DIAS.length;i++){
            prods.add(new Producto(DIAS[i], DIAS_SEMANA[i], PRECIOS[i]));

        }
        Log.d("CreaPojos", prods.toString());
    }
}