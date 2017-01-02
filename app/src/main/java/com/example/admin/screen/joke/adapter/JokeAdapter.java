package com.example.admin.screen.joke.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.screen.joke.entity.JokeBean;
import com.example.admin.screen.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 2016/12/12.
 */

public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.ItemViewHolder> {

    private ArrayList<JokeBean.Data> mDataSet = new ArrayList<>();
    private Context context;

    public JokeAdapter(Context context) {
        this.context = context;
    }

    public void setList(ArrayList<JokeBean.Data> mDataSet){
        this.mDataSet=mDataSet;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.listitem_joke, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        JokeBean.Data data = mDataSet.get(i);
        itemViewHolder.content.setText(data.getContent());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.content)
        TextView content;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
