package com.york.recycleviewcollection.countrypick;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.york.recycleviewcollection.R;

import java.util.List;


public class CountryPickerAdapter extends PyAdapter<RecyclerView.ViewHolder> {

    private OnItemClicK onItemClicK;

    public CountryPickerAdapter(List<? extends PyEntity> entities) {
        super(entities);
    }

    public void setOnItemClicK(OnItemClicK onItemClicK){
        this.onItemClicK =onItemClicK;
    }

    @Override
    public RecyclerView.ViewHolder onCreateLetterHolder(ViewGroup parent, int viewType) {
        return new LetterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_letter, parent, false));
    }

    @Override
    public RecyclerView.ViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country_large_padding, parent, false));
    }

    @Override
    public void onBindHolder(RecyclerView.ViewHolder holder, PyEntity entity, int position) {
        VH vh = (VH)holder;
        final Country country = (Country)entity;
        vh.ivFlag.setImageResource(country.flag);
        vh.tvName.setText(country.name);
        vh.tvCode.setText("+" + country.code);
        holder.itemView.setOnClickListener(v -> {
            if (onItemClicK!=null){
                onItemClicK.OnTtemClick(country.code);
            }
        });
    }

    @Override
    public void onBindLetterHolder(RecyclerView.ViewHolder holder, LetterEntity entity, int position) {
        ((LetterHolder)holder).textView.setText(entity.letter.toUpperCase());
    }

    class VH extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvCode;
        ImageView ivFlag;

        VH(View itemView) {
            super(itemView);
            ivFlag = (ImageView) itemView.findViewById(R.id.iv_flag);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvCode = (TextView) itemView.findViewById(R.id.tv_code);
        }
    }

    public interface OnItemClicK{
        void OnTtemClick(int position);
    }

    class LetterHolder extends RecyclerView.ViewHolder {
        public final TextView textView;
        public LetterHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }
}
