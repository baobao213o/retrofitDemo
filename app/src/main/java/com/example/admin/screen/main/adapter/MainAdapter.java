package com.example.admin.screen.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.screen.R;
import com.example.admin.screen.main.entity.MainBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 2016/12/12.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ItemViewHolder> {

    public interface ItemListener{
        void onItemClick(int position);
    }

    private ArrayList<MainBean> mDataSet = new ArrayList<>();
    private Context context;
    private ItemListener listener;

    public MainAdapter(Context context) {
        this.context = context;
    }

    public void setList(ArrayList<MainBean> mDataSet){
        this.mDataSet=mDataSet;
    }

    public void setOnItemLisenter(ItemListener listener){
        this.listener=listener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_main, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, final int i) {
        MainBean data = mDataSet.get(i);
        itemViewHolder.content.setText(data.getContent());
        int drawable=data.getDrawable();
        itemViewHolder.iv_pic.setImageDrawable(context.getResources().getDrawable(drawable));
        itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.main_name)
        TextView content;
        @BindView(R.id.main_pic)
        ImageView iv_pic;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
