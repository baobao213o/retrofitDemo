package com.example.admin.screen.picture;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.entity.FunPicBean;
import com.example.admin.screen.R;
import com.example.admin.util.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


class FunPicAdapter extends RecyclerView.Adapter<FunPicAdapter.ItemViewHolder> {

    interface ItemListener {
        void onItemClick(View iv, ArrayList<FunPicBean.Data> data, int position);
    }

    private ArrayList<FunPicBean.Data> mDataSet = new ArrayList<>();
    private Context context;
    private ItemListener listener;

    FunPicAdapter(Context context) {
        this.context = context;
    }

    public void setList(ArrayList<FunPicBean.Data> mDataSet) {
        this.mDataSet = mDataSet;
    }

    void setOnItemLisenter(ItemListener listener) {
        this.listener = listener;
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
        final String url = data.getUrl();
        if (url.contains(".gif") || (url.contains(".GIF"))) {
            ImageLoader.getInstance().loadGif(context,url,itemViewHolder.iv_pic);
        } else {
            ImageLoader.getInstance().loadPic(context,url,itemViewHolder.iv_pic);
        }
        itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(itemViewHolder.iv_pic, mDataSet, i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.iv_pic)
        ImageView iv_pic;

        ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
