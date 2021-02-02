package com.example.jjherram.repasoexamen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment2 newInstance(String param1, String param2) {
        Fragment2 fragment = new Fragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    //algoritmo
    static long DIAS[] = {1,3,8,10,13,20,29};
    static  String DIAS_SEMANA[]={"lun", "jue", "lun", "jue", "sab", "sab", "lun"};
    static double PRECIOS[]={1.2,1.1,1.2,1.1,1.2,1.3,1.2};
    public ArrayList<Producto> prods= new ArrayList<>();
    //rv
    private RecyclerView recyclerView;
    //adapter
    private AdapterProductos adapterProductos;


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
        View v= inflater.inflate(R.layout.fragment_2, container, false);
        //algoritmo
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


        //RECYCLERView
        recyclerView=v.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        adapterProductos=new AdapterProductos(prods,getContext());
        recyclerView.setAdapter(adapterProductos);



        return v;
    }
    public void CreaPojos(){

        for (int i=0;i<DIAS.length;i++){
            prods.add(new Producto(DIAS[i], DIAS_SEMANA[i], PRECIOS[i]));

        }
        Log.d("CreaPojos", prods.toString());
    }
}