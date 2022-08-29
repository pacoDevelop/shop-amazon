package com.cosmenp.amazon.menu.pedido;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cosmenp.amazon.R;
import com.cosmenp.amazon.db.AppDatabase;
import com.cosmenp.amazon.menu.pedido.Adapter.LineaPedidosAdapter;

public class LineaPedidosFragment extends Fragment {
    private AppDatabase db;
    private View view;
    private String idPedido;


    public LineaPedidosFragment() {

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_linea_pedido, container, false);
        rellenarRecyclerView(view);
        return view;
    }

    private void rellenarRecyclerView(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_reciclerLinePed);
        recyclerView.setAdapter(new LineaPedidosAdapter(getContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager((this.getContext())));
    }

}