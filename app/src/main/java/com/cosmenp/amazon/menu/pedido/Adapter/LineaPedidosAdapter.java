package com.cosmenp.amazon.menu.pedido.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.cosmenp.amazon.LoadImage;
import com.cosmenp.amazon.MainActivity;
import com.cosmenp.amazon.R;
import com.cosmenp.amazon.db.AppDatabase;
import com.cosmenp.amazon.db.AppDatabaseSingleton;
import com.cosmenp.amazon.entity.DateConverter;
import com.cosmenp.amazon.entity.Pedido;
import com.cosmenp.amazon.entity.Producto;
import com.cosmenp.amazon.view_model.MainViewModel;

public class LineaPedidosAdapter extends RecyclerView.Adapter<LineaPedidosAdapter.LineaPedidosAdapterViewHolder>{

    private Context context;
    private LayoutInflater inflater;
    private MainViewModel model;

    AppDatabase db;
    Pedido pedido;

    public LineaPedidosAdapter(final Context context) {
        model = ViewModelProviders.of(((MainActivity)context)).get(MainViewModel.class);
        new Thread(new Runnable() {
            @Override
            public void run() {
                db = AppDatabaseSingleton.getInstance(context).getAppDatabase();
                pedido = db.pedidosDAO().get(model.getSelectedPed().getIdPedido());
                if(model.getLineasPedidosRealizado().size()==0) {
                    model.setLineasPedidosRealizado(db.lineasPedidosDAO().get(model.getSelectedPed().getIdPedido()));
                    ((MainActivity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            LineaPedidosAdapter.this.notifyDataSetChanged();
                        }
                    });
                } }
        }).start();

        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public LineaPedidosAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.linea_pedido_list,parent,false);
        return new LineaPedidosAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LineaPedidosAdapterViewHolder holder, final int position) {
        holder.tvTitulo.setText(model.getLineasPedidosRealizado().get(position).getIdProducto());
        holder.tvPrecio.setText(model.getLineasPedidosRealizado().get(position).getPvp()*(1+ model.getLineasPedidosRealizado().get(position).getTipoIva()/100) +"€ x "+ model.getLineasPedidosRealizado().get(position).getUnidades()+" unidades");
        holder.tvFecha.setText(DateConverter.toTimestamp(pedido.getFechaPedido()));
        holder.ivProd.setImageResource(R.drawable.cargando);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Producto producto = db.productosDAO().get(model.getLineasPedidosRealizado().get(position).getIdProducto());
                if (producto != null) {
                    LoadImage loadImage = (LoadImage) new LoadImage(holder).execute(producto.getRutaFoto());
                }
            }
        }).start();

    }

    @Override
    public int getItemCount() {
        return model.getLineasPedidosRealizado().size();
    }


    public class LineaPedidosAdapterViewHolder extends RecyclerView.ViewHolder  implements LoadImage.Listener{
        TextView tvTitulo;
        TextView tvPrecio;
        TextView tvFecha;
        ConstraintLayout lProductos;
        ImageView ivProd;

        public LineaPedidosAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTituloLinPed);
            tvPrecio = itemView.findViewById(R.id.tvPrecioLinPed);
            tvFecha = itemView.findViewById(R.id.tvFechaLinPed);
            lProductos = itemView.findViewById(R.id.ILineaPedidos);
            ivProd = itemView.findViewById(R.id.ivListLinPed);
        }

        @Override
        public void onImageLoaded(Bitmap bitmap) {
            ivProd.setImageBitmap(bitmap);
        }

        @Override
        public void onError() {

        }
    }
}
