package com.cosmenp.amazon.menu.carro;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cosmenp.amazon.R;
import com.cosmenp.amazon.db.AppDatabase;
import com.cosmenp.amazon.db.AppDatabaseSingleton;
import com.cosmenp.amazon.entity.LineaPedido;
import com.cosmenp.amazon.entity.Pedido;
import com.cosmenp.amazon.view_model.MainViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class Carrito extends Fragment {

    private TextView tvMensaje;
    private TextView tvTotal;
    private Button btComprarProd;
    private Button btCancelar;
    private Button btComprarPed;
    private RecyclerView rvLineasProd;
    private MainViewModel model;
    private NavController navController;
    private AppDatabase db;

    public Carrito() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_carrito, container, false);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onStart() {
        super.onStart();
        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.hide();
        tvMensaje = getActivity().findViewById(R.id.tvMensajeCarro);
        tvTotal = getActivity().findViewById(R.id.tvTotalAct);
        rvLineasProd = getActivity().findViewById(R.id.rvLineasProdAct);
        btComprarPed = getActivity().findViewById(R.id.btComprarPed);
        btComprarProd = getActivity().findViewById(R.id.btComprarProdAct);
        btCancelar = getActivity().findViewById(R.id.btCancelarPed);
        model = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        navController = Navigation.findNavController(getView());

        btComprarProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_nav_carrito_to_nav_listado);
            }
        });

        if (model.getLineasPedidoCarrito().size() > 0) {
            tvMensaje.setVisibility(View.GONE);
            btComprarProd.setVisibility(View.GONE);
            calculateTotal(); //TODO implementar listener

            LineaPedidoActualAdapter lineasAdapter = new LineaPedidoActualAdapter(getContext(), model);
            rvLineasProd.setAdapter(lineasAdapter);
            rvLineasProd.setLayoutManager(new LinearLayoutManager(getContext()));

            btCancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    model.deleteAllLineaPedidoCarrito();
                    cambiarVista();
                }
            });
            btComprarPed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ejecutarCompra();
                }
            });
        } else {
            cambiarVista();
        }
    }

    private void ejecutarCompra() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                db = AppDatabaseSingleton.getInstance(getContext()).getAppDatabase();
                String valor = db.pedidosDAO().getCod();
                String valor2 = "";
                if (valor == null || valor.isEmpty()) {
                    valor2 = "AAAAAAA";
                } else {

                    boolean correcto;
                    int posicion = 0;
                    do {
                        correcto = true;
                        char letra = (char) (valor.charAt(valor.length() - (1 + posicion)));
                        if (letra == 'Z') {
                            letra = 65;
                            correcto = false;
                            posicion++;
                        } else {
                            letra++;
                        }
                        valor2 = letra + valor2;

                    } while (!correcto && posicion < valor.length());
                    if (valor.length() == posicion) {
                        posicion--;
                    }
                    valor2 = valor.substring(0, valor.length() - (1 + posicion)) + valor2;
                }
                Pedido pedido = null;
                Date fecha = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(fecha);
                calendar.add(Calendar.DAY_OF_YEAR, 7);

                try {
                    pedido = new Pedido(0, valor2, "0", "0",
                            formatter.parse(formatter.format(fecha)), formatter.parse(formatter.format(calendar.getTime())), formatter.parse(formatter.format(fecha)),
                            1, "0", formatter.parse(formatter.format(fecha)), 1, formatter.parse(formatter.format(fecha)), "PayPal",
                            0, true);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                db.pedidosDAO().add(pedido);
                if (model.getPedidos().size() != 0) {
                    model.getPedidos().add(pedido);
                }
                for (LineaPedido l : model.getLineasPedidoCarrito()) {
                    l.setIdPedido(valor2);
                    db.lineasPedidosDAO().add(l);
//                    if(model.getPedidos().size()!=0) {
//                        model.getLineasPedidosRealizado().add(l);
//                    }
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        navController.navigate(R.id.action_nav_carrito_to_nav_compra_finalizada);
                    }
                });
                model.deleteAllLineaPedidoCarrito();

            }
        }).start();
    }

    private void calculateTotal() {
        double total = 0.;
        for (LineaPedido l : model.getLineasPedidoCarrito()) {
            total += l.getPvp() * (1 + l.getTipoIva() / 100) * l.getUnidades();
        }
        tvTotal.setText("Total: " + total + "€");
    }

    private void cambiarVista() {
        tvMensaje.setVisibility(View.VISIBLE);
        tvTotal.setVisibility(View.GONE);
        rvLineasProd.setVisibility(View.GONE);
        btComprarPed.setVisibility(View.GONE);
        btComprarProd.setVisibility(View.VISIBLE);
        btCancelar.setVisibility(View.GONE);

    }
}
