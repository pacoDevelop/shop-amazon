package com.cosmenp.amazon.menu.pedido.Adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.cosmenp.amazon.MainActivity;
import com.cosmenp.amazon.R;
import com.cosmenp.amazon.db.AppDatabase;
import com.cosmenp.amazon.db.AppDatabaseSingleton;
import com.cosmenp.amazon.entity.DateConverter;
import com.cosmenp.amazon.entity.LineaPedido;
import com.cosmenp.amazon.view_model.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class PedidosAdapter extends RecyclerView.Adapter<PedidosAdapter.PedidosAdapterViewHolder>{
    private Context context;
    private LayoutInflater inflater;
    private AppDatabase db;
    private List<LineaPedido> lineaPedidos;
    private MainViewModel model;
    private NavController navController;

    public PedidosAdapter(final Context context) {
        this.context = context;
        model = ViewModelProviders.of(((MainActivity)context)).get(MainViewModel.class);
       new Thread(new Runnable() {
           @Override
           public void run() {
               db = AppDatabaseSingleton.getInstance(context).getAppDatabase();

               if(model.getPedidos().size()==0) {
                   lineaPedidos = new ArrayList<>();
                   model.setPedidos(db.pedidosDAO().getAll());
                   ((MainActivity) context).runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           notifyDataSetChanged();
                       }
                   });
               }
           }
       }).start();




        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PedidosAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.pedido_list,parent,false);
        return new PedidosAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PedidosAdapterViewHolder holder, final int position) {
        holder.tvTitulo.setText(model.getPedidos().get(position).getIdPedido());
        final Double[] total = {0.0};
        new Thread(new Runnable() {
            @Override
            public void run() {
                lineaPedidos = new ArrayList<>();
                lineaPedidos=db.lineasPedidosDAO().get(model.getPedidos().get(position).getIdPedido());

                for (LineaPedido lineaPedido:lineaPedidos
                ) {
                    total[0] +=lineaPedido.getPvp()*lineaPedido.getUnidades()*(1+ lineaPedido.getTipoIva()/100);
                }
                ((MainActivity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        holder.tvPrecio.setText(total[0] + "€");
                    }
                });

            }
        }).start();

        holder.tvFecha.setText(DateConverter.toTimestamp(model.getPedidos().get(position).getFechaPedido()));

        holder.bt_lineaPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.deleteAllLineaPedidoRealizado();
                model.setSelectedPed(model.getPedidos().get(position));
                navController = Navigation.findNavController(v);
                navController.navigate(R.id.nav_linea_pedido);
                }
        });

    }

    @Override
    public int getItemCount() {
        return model.getPedidos().size();
    }


    public class PedidosAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo;
        TextView tvPrecio;
        TextView tvFecha;

        Button bt_lineaPedido;

        public PedidosAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTituloPed);
            tvPrecio = itemView.findViewById(R.id.tvPrecioPed);
            tvFecha = itemView.findViewById(R.id.tvFechaPed);
            bt_lineaPedido=itemView.findViewById(R.id.bt_linPed);

        }
    }
}
