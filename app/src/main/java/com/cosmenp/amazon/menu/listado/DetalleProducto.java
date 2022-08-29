package com.cosmenp.amazon.menu.listado;


import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cosmenp.amazon.LoadImage;
import com.cosmenp.amazon.R;
import com.cosmenp.amazon.entity.LineaPedido;
import com.cosmenp.amazon.entity.Producto;
import com.cosmenp.amazon.view_model.MainViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleProducto extends Fragment implements LoadImage.Listener {
    private TextView tvTitulo;
    private TextView tvPrecio;
    private TextView tvDescripcion;
    private ImageView ivProducto;
    private Button btComprar;
    private MainViewModel model;

    public DetalleProducto() {
    }

//    public DetalleProducto(Producto producto) {
//        this.producto = producto;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalle_producto, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        model = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        tvTitulo = getActivity().findViewById(R.id.tvTituloDProd);
        tvPrecio = getActivity().findViewById(R.id.tvPrecioDP);
        tvDescripcion = getActivity().findViewById(R.id.tvDescripcionDP);
        ivProducto = getActivity().findViewById(R.id.ivDP);
        btComprar = getActivity().findViewById(R.id.btComprarDP);
        int selectedProd = model.getSelectedProd();

        if (selectedProd != -1) {
            tvTitulo.setText(model.getProducto(selectedProd).getIdProducto());
            tvPrecio.setText(model.getProducto(selectedProd).getPvp() + "€");
            tvDescripcion.setText(model.getProducto(selectedProd).getDescripcion());
            ivProducto.setImageResource(R.drawable.cargando);
            LoadImage loadImage = (LoadImage) new LoadImage(this).execute(model.getProducto(selectedProd).getRutaFoto());
            btComprar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Producto p = model.getProducto(model.getSelectedProd());
                    LineaPedido l = new LineaPedido(0,"",p.getIdProducto(),1,"compra del articulo",p.getPvp(), (int) p.getTipoIva(),true);
                    model.addLineaPedidoCarrito(l);
                }
            });
        }
    }


    @Override
    public void onImageLoaded(Bitmap bitmap) {
        ivProducto.setImageBitmap(bitmap);
    }

    @Override
    public void onError() {

    }
}
