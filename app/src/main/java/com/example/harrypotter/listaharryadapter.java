package com.example.harrypotter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.harrypotter.model.harry;

import java.util.ArrayList;

public class listaharryadapter extends RecyclerView.Adapter<listaharryadapter.ViewHolder> implements  View.OnClickListener{

    private ArrayList<harry> dataset;
    private View.OnClickListener listener;
    private Context context;

    public listaharryadapter(Context context){
        this.context =context;
        dataset =new ArrayList<>();


    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_harry, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        harry p = dataset.get(position);
        holder.nombreTextView.setText(p.getName());

        Glide.with(context)
                .load(p.getImage())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoImageView);



    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaharry(ArrayList<harry> listaharry) {
        dataset.addAll(listaharry);
        notifyDataSetChanged();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;

    }
    @Override
    public void onClick(View v) {
        if(listener!=null)
            listener.onClick(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView fotoImageView;
        private TextView nombreTextView;
        private CardView tarjetas;
        public ViewHolder(View itemView){
            super(itemView);
            fotoImageView = itemView.findViewById(R.id.fotoImageView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
            tarjetas=itemView.findViewById(R.id.tarjetas);
        }



        }
    }

