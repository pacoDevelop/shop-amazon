package com.cosmenp.amazon.menu.insert;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.cosmenp.amazon.R;
import com.cosmenp.amazon.Scrapper.ScrapAmazon;
import com.cosmenp.amazon.db.AppDatabase;
import com.cosmenp.amazon.db.AppDatabaseSingleton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class InsertFragment extends Fragment {
    private AppDatabase db;
    EditText url;
    TextView textView;
    View view;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       view = inflater.inflate(R.layout.fragment_insert, container, false);
        FloatingActionButton fab=getActivity().findViewById(R.id.fab);
        fab.show();
        //insertProducto(view);
        textView = view.findViewById(R.id.tv_banner);
        Button add=view.findViewById(R.id.bt_start);
        url=view.findViewById(R.id.et_url);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (url.getText().toString().contains("http")){
                    textView.setText("Comprobando enlace....");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    textView.setText("Espere mientras se indexa...");
                    insertProducto(view,url.getText().toString());

                }else if (url.getText().toString().equals("cleanPed")){
                    cleanPed();
                    Snackbar.make(v, "Ejecutando funcion oculta", Snackbar.LENGTH_LONG)
                            .show();
                }
                else {
                    Snackbar.make(v, "Introduzca una url valida", Snackbar.LENGTH_LONG)
                            .show();
                }
            }
        });

        return view;
    }

    private void insertProducto(final View view, final String url) {

//       getActivity().runOnUiThread(new Runnable() {
//           @Override
//           public void run() {
               ScrapAmazon.populateProducto(url, getContext(),textView);
//           }
//       });
    }

    private  void cleanPed(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                db = AppDatabaseSingleton.getInstance(getContext()).getAppDatabase();
                db.pedidosDAO().deleteAll();
                db.lineasPedidosDAO().deleteAll();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Snackbar.make(getView(), "Funcion oculta Ejecutada con Exito", Snackbar.LENGTH_LONG)
                                .show();
                    }
                });
            }
        }).start();
    }
}