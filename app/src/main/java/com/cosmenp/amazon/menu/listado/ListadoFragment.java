package com.cosmenp.amazon.menu.listado;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cosmenp.amazon.FlotaingManager;
import com.cosmenp.amazon.R;
import com.cosmenp.amazon.db.AppDatabase;
import com.cosmenp.amazon.db.AppDatabaseSingleton;
import com.cosmenp.amazon.entity.Producto;
import com.cosmenp.amazon.view_model.MainViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListadoFragment extends Fragment {
    private AppDatabase db;
    private RecyclerView rvProductos;
    private Bundle savedInstanceState;
    private MainViewModel model;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        return inflater.inflate(R.layout.fragment_listado, container, false);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onStart() {
        super.onStart();


        FloatingActionButton fab=getActivity().findViewById(R.id.fab);
        fab.show();
        model = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        rvProductos = getActivity().findViewById(R.id.rvListProd);
        FlotaingManager.manageRecyclerView(getActivity(),rvProductos);
        final ProductosAdapter productosAdapter = new ProductosAdapter(getContext());

        rvProductos.setAdapter(productosAdapter);
        rvProductos.setLayoutManager(new LinearLayoutManager(getContext()));

        DynamicEventsHelper.DynamicEventsCallback callback = new DynamicEventsHelper.DynamicEventsCallback() {

            @Override
            public void onItemMove(int initialPosition, int finalPosition) {

            }

            @Override
            public void removeItem(int position) {
                productosAdapter.removeItem(position);
            }
        };

        ItemTouchHelper androidItemTouchHelper = new ItemTouchHelper(new DynamicEventsHelper(callback));

        androidItemTouchHelper.attachToRecyclerView(rvProductos);

        if (savedInstanceState == null) {
            model.getProductos().clear();
            if (model.getProductos().size() == 0) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        db = AppDatabaseSingleton.getInstance(getContext()).getAppDatabase();
                            model.setProductos(db.productosDAO().getAll());
                            model.setProductosActivo(db.productosDAO().getAllActivos());

                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    productosAdapter.notifyDataSetChanged();
                                }
                            });

                    }
                }).start();
            }
        }
    }




}