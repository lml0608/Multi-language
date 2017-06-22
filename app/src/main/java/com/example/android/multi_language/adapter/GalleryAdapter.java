package com.example.android.multi_language.adapter;

import android.content.Context;

import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.android.multi_language.R;
import com.example.android.multi_language.activity.SlideshowDialogFragment;
import com.example.android.multi_language.model.Image;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zengzhi on 2017/6/22.
 */

public class GalleryAdapter extends  RecyclerView.Adapter<GalleryAdapter.MyViewHolder> {

    private List<Image> images;
    private Context mContext;
    private ClickListener listener;

    public GalleryAdapter(Context mContext,List<Image> images, ClickListener listener) {
        this.images = images;
        this.mContext = mContext;
        this.listener = listener;
    }

    @Override
    public GalleryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(mContext).inflate(R.layout.gally_thumbnail, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GalleryAdapter.MyViewHolder holder, int position) {

        Image image = images.get(position);

        Glide.with(mContext).load(image.getMedium())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView thumbnail;

        public MyViewHolder(View itemView) {
            super(itemView);

            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();


            listener.onClick(position, images);

        }
    }

    public void swapCursor(List<Image> imagelist) {

        images = imagelist;
        notifyDataSetChanged();
    }


    public interface ClickListener {
        void onClick(int position, List<Image> images);
    }

//    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {
//
//        private GestureDetector gestureDetector;
//        private GalleryAdapter.ClickListener clickListener;
//
//        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final GalleryAdapter.ClickListener clickListener) {
//            this.clickListener = clickListener;
//            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
//                @Override
//                public boolean onSingleTapUp(MotionEvent e) {
//                    return true;
//                }
//
//                @Override
//                public void onLongPress(MotionEvent e) {
//                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
//                    if (child != null && clickListener != null) {
//                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
//                    }
//                }
//            });
//        }
//
//        @Override
//        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//
//            View child = rv.findChildViewUnder(e.getX(), e.getY());
//            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
//                clickListener.onClick(child, rv.getChildPosition(child));
//            }
//            return false;
//        }
//
//        @Override
//        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//        }
//
//        @Override
//        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//        }
//    }
}
