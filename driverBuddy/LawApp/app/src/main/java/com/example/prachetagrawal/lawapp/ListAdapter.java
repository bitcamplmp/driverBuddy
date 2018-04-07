package com.example.prachetagrawal.lawapp;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Lucas on 4/7/2018.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListManager> {

    private List<LawStorage> lawList;


    public ListAdapter(List<LawStorage> list) {
        lawList = list;
    }
    @Override
    public ListManager onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.card_layout, parent, false);

        return new ListManager(itemView);
    }

    @Override
    public void onBindViewHolder(ListManager holder, int i) {
        LawStorage ls = lawList.get(i);
        holder.vTitle.setText(ls.mainTitle);
        //holder.vLogo.setImageResource();
    }

    @Override
    public int getItemCount() {
        return lawList.size();
    }

    public class ListManager extends RecyclerView.ViewHolder {
        private CardView cv;
        private TextView vTitle;
        //private ImageView vLogo;


        public ListManager(View v) {
            super(v);
            cv = v.findViewById(R.id.card);
            vTitle = v.findViewById(R.id.cardTitle);
            //vLogo = v.findViewById(R.id.cardLogo);

            v.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick (View v) {
                    Intent intent = new Intent(v.getContext(), CategoryInfo.class);
                    intent.putExtra("name", vTitle.getText().toString());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
