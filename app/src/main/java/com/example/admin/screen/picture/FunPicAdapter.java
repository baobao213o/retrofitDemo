package com.example.admin.screen.picture;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.admin.entity.FunPicBean;
import com.example.admin.screen.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 2016/12/12.
 */

public class FunPicAdapter extends RecyclerView.Adapter<FunPicAdapter.ItemViewHolder> {

    public interface ItemListener{
        void onItemClick(View iv,String url);
    }

    private ArrayList<FunPicBean.Data> mDataSet = new ArrayList<>();
    private Context context;
    private ItemListener listener;
    public FunPicAdapter(Context context) {
        this.context = context;
    }

    public void setList(ArrayList<FunPicBean.Data> mDataSet){
        this.mDataSet=mDataSet;
    }
    public void setOnItemLisenter(ItemListener listener){
        this.listener=listener;
    }
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.listitem_pic, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder itemViewHolder, final int i) {
        final FunPicBean.Data data = mDataSet.get(i);
        itemViewHolder.content.setText(data.getContent());
        String url=data.getUrl();
        if(url.contains(".gif")||(url.contains(".GIF"))){
            Glide.with(context).load(url).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(itemViewHolder.iv_pic);
        }else{
            Glide.with(context).load(data.getUrl()).into(itemViewHolder.iv_pic);
        }
        itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(itemViewHolder.iv_pic,data.getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.iv_pic)
        ImageView iv_pic;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
