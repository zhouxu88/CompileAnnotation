package com.zx.compileannotation.rv;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zx.annotation.BindView;
import com.zx.compileannotation.R;
import com.zx.inject_api.ButterKnife;

import java.util.List;

public class MyAdapter extends BaseRecyclerViewAdapter<String> {


    public MyAdapter(Context mContext, List<String> mList) {
        super(mContext, mList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateMyViewHolder(ViewGroup parent, int viewType) {
        return new ContentViewHolder(inflater.inflate(R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindMyViewHolder(RecyclerView.ViewHolder holder, int position) {
        setData((ContentViewHolder) holder, position);
    }


    //设置相关数据
    private void setData(ContentViewHolder holder, int position) {
        holder.textView.setText(mList.get(position));
    }


    public class ContentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title_tv)
        TextView textView;

        public ContentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
//            textView = itemView.findViewById(R.id.title_tv);
        }
    }
}
