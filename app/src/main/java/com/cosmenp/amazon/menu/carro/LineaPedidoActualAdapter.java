package com.cosmenp.amazon.menu.carro;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.cosmenp.amazon.LoadImage;
import com.cosmenp.amazon.MainActivity;
import com.cosmenp.amazon.R;
import com.cosmenp.amazon.db.AppDatabase;
import com.cosmenp.amazon.db.AppDatabaseSingleton;
import com.cosmenp.amazon.entity.LineaPedido;
import com.cosmenp.amazon.entity.Producto;
import com.cosmenp.amazon.view_model.MainViewModel;

public class LineaPedidoActualAdapter extends RecyclerView.Adapter<LineaPedidoActualAdapter.LineaPedidosActualAdapterViewHolder>{

    private Context context;
    private LayoutInflater inflater;
    private MainViewModel model;
    private TextView tvTotal;

    private AppDatabase db;

    public LineaPedidoActualAdapter(Context context, MainViewModel model) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.model = model;
        this.tvTotal = ((FragmentActivity)context).findViewById(R.id.tvTotalAct);

    }

    @NonNull
    @Override
    public LineaPedidosActualAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.linea_pedido_actual_list,parent,false);
        return new LineaPedidosActualAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LineaPedidosActualAdapterViewHolder holder, final int position) {
        holder.tvTitulo.setText(model.getLineasPedidoCarrito().get(position).getIdProducto());
        holder.tvPrecio.setText(model.getLineasPedidoCarrito().get(position).getPvp()*(1+ model.getLineasPedidoCarrito().get(position).getTipoIva()/100) +"€ x "+ model.getLineasPedidoCarrito().get(position).getUnidades()+" unidades");
        holder.btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.addLineaPedidoCarrito(position);
                calculateTotal();
                notifyDataSetChanged();
            }
        });
        holder.btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.deleteLineaPedidoCarrito(position);
                calculateTotal();
                notifyDataSetChanged();
            }
        });
        holder.lProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setSelectedProd(model.getLineasPedidoCarrito().get(position).getIdProducto());
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.nav_detalleProducto);
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                db = AppDatabaseSingleton.getInstance(context).getAppDatabase();
                Producto producto = db.productosDAO().get(model.getLineasPedidoCarrito().get(position).getIdProducto());
                if (producto != null) {
                    LoadImage loadImage = (LoadImage) new LoadImage(holder).execute(producto.getRutaFoto());
                }
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

    @Override
    public int getItemCount() {
        return model.getLineasPedidoCarrito().size();
    }


    public class LineaPedidosActualAdapterViewHolder extends RecyclerView.ViewHolder  implements LoadImage.Listener{
        private TextView tvTitulo;
        private TextView tvPrecio;
        private ConstraintLayout lProductos;
        private ImageView ivProd;
        private Button btAdd;
        private Button btRemove;

        public LineaPedidosActualAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTituloLinPedAct);
            tvPrecio = itemView.findViewById(R.id.tvPrecioLinPedAct);
            lProductos = itemView.findViewById(R.id.ILineaPedidosAct);
            ivProd = itemView.findViewById(R.id.ivListLinPedAct);
            btAdd = itemView.findViewById(R.id.btAddAct);
            btRemove = itemView.findViewById(R.id.btRemoveAct);
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
