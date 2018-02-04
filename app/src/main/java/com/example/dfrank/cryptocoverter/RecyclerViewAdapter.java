package com.example.dfrank.cryptocoverter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dfrank on 1/30/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.viewHolder> {
    private List<Btc> names;

    public RecyclerViewAdapter(List<Btc> names) {
        this.names = names;
    }

    @Override
    public RecyclerViewAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowcard,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.viewHolder holder, int position) {
        holder.country.setText(names.get(position).getCountry());
        holder.shortcode.setText(names.get(position).getShorcode());
        holder.btc.setText(String.valueOf(names.get(position).getBtc()));
        holder.eth.setText(String.valueOf(names.get(position).getEth()));
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        private TextView shortcode, eth, country, btc;

        public viewHolder(View view) {
            super(view);
            shortcode = view.findViewById(R.id.shortcode);
            country = view.findViewById(R.id.country);
            btc = view.findViewById(R.id.btcvalue);
            eth = view.findViewById(R.id.ethvalue);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position!=RecyclerView.NO_POSITION){
                        Intent intent = new Intent(view.getContext(), Conversion.class);
                        intent.putExtra("btc", names.get(position).getBtc())
                                .putExtra("country",names.get(position).getCountry())
                                .putExtra("shortcode",names.get(position).getShorcode())
                                .putExtra("eth",names.get(position).getEth());
                        view.getContext().startActivity(intent);
                    }
                }
            });
        }
    }
}
