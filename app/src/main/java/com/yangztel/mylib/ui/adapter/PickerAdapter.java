package com.yangztel.mylib.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yangztel.mylib.R;
import com.yangztel.mylib.ui.PickerActivity;

import java.util.List;

/**
 * Created by yangzteL on 2018/7/16 0016.
 */

public class PickerAdapter extends RecyclerView.Adapter<PickerAdapter.PickerVH>{

    private final Context content;
    private final List<String> list;

    public PickerAdapter(Context context, List<String> list){
        this.content = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PickerVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(content).inflate(R.layout.item_string,parent,false);
        return new PickerVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PickerVH holder, int position) {
                holder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null? 0:list.size();
    }


    class PickerVH extends RecyclerView.ViewHolder{
        public TextView textView;
        public PickerVH(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_item);
        }
    }

}
