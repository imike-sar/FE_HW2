package ru.tech_coll.hw2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import static ru.tech_coll.hw2.R.id.iv;

/**
 * Created by imike on 17.11.2016.
 */

public class RVadapter extends RecyclerView.Adapter<RVadapter.ImgViewHolder> {

    List<Imgs> imgs;

    RVadapter(List<Imgs> imgs) {
        this.imgs = imgs;
    }

    @Override
    public ImgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ImgViewHolder ivh = new ImgViewHolder(v);
        return ivh;
    }

    @Override
    public void onBindViewHolder(ImgViewHolder holder, int position) {
        //holder.tvv.setText(imgs.get(position).url);

        String imgurl = imgs.get(position).url;
        Picasso.with(holder.itemView.getContext()).load(imgurl).into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return imgs.size();
    }

    public static class ImgViewHolder extends RecyclerView.ViewHolder {

        ImageView pic;
        //TextView tvv;

        public ImgViewHolder(View itemView) {
            super(itemView);
            pic = (ImageView) itemView.findViewById(iv);
            //tvv = (TextView) itemView.findViewById(R.id.tvv);

        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
