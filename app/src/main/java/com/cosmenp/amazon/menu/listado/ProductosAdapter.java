package com.cosmenp.amazon.menu.listado;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;
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
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ProductosAdapterViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private MainViewModel model;

    public ProductosAdapter(Context context) {
        model = ViewModelProviders.of(((MainActivity)context)).get(MainViewModel.class);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ProductosAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.producto_list,parent,false);
        return new ProductosAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductosAdapterViewHolder holder, final int position) {
        holder.tvTitulo.setText(model.getProductoActivo(position).getIdProducto());
        holder.tvPrecio.setText(model.getProductoActivo(position).getPvp()*(1+model.getProductoActivo(position).getTipoIva()/100) + "€");
        holder.ivProd.setImageResource(R.drawable.cargando);
        LoadImage loadImage = (LoadImage) new LoadImage(holder).execute(model.getProductoActivo(position).getRutaFoto());
        holder.lProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setSelectedProd(model.getProductoActivo(position).getIdProducto());
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.nav_detalleProducto);
            }
        });
        holder.btComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Producto p = model.getProductoActivo(position);
                LineaPedido l = new LineaPedido(0,"",p.getIdProducto(),1,"compra del articulo",p.getPvp(), (int) p.getTipoIva(),true);
                model.addLineaPedidoCarrito(l);
            }
        });
       
//        holder.lProductos.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                model.setSelectedPed(position);
//                ((MainActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.containerDown,new MonumentDetails()).commit();
//            }
//        });
    }

    public void removeItem(final int position) {

        Snackbar.make(((MainActivity)context).findViewById(R.id.rvListProd), "Por favor, confirme", Snackbar.LENGTH_LONG)
                .setAction("Excluir", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // removo o produto do banco de dados
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                AppDatabase db = AppDatabaseSingleton.getInstance(context).getAppDatabase();
                                model.getProducto(model.getProductoActivo(position)).setActivo(0);
                                db.productosDAO().update(model.getProducto(model.getProductoActivo(position)));
                                model.getProductosActivo().remove(position);
                                ((MainActivity)context).runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        notifyItemRemoved(position);
                                    }
                                });

                            }
                        }).start();

                    }
                }).addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                super.onDismissed(transientBottomBar, event);
                notifyItemChanged(position);
            }
        }).show();

    }

    @Override
    public int getItemCount() {
        return model.getProductosActivo().size();
    }

    public class ProductosAdapterViewHolder extends RecyclerView.ViewHolder implements LoadImage.Listener {
        private TextView tvTitulo;
        private TextView tvPrecio;
        private Button btComprar;
        private ConstraintLayout lProductos;
        private ImageView ivProd;

        public ProductosAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTituloProd);
            tvPrecio = itemView.findViewById(R.id.tvPrecioProd);
            lProductos = itemView.findViewById(R.id.lProductos);
            btComprar = itemView.findViewById(R.id.btComprarProd);
            ivProd = itemView.findViewById(R.id.ivListProd);
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
