package com.simplelecture.main.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.simplelecture.main.R;
import com.simplelecture.main.activities.interfaces.OnItemClickListener;
import com.simplelecture.main.model.viewmodel.DemoTutorialResponseModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.simplelecture.main.R.id.item_layout;

/**
 * Created by Raos on 3/29/2016.
 */
public class DemoTutorialAdapter extends RecyclerView.Adapter<DemoTutorialAdapter.MyViewHolder> {

    List<DemoTutorialResponseModel> demoTutorialResponseModelLstArray;
    Activity activity;

    OnItemClickListener mItemClickListener;

    public DemoTutorialAdapter(Activity activty, List<DemoTutorialResponseModel> demoTutorialResponseModelLstAray) {
        this.activity = activty;
        this.demoTutorialResponseModelLstArray = demoTutorialResponseModelLstAray;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_view, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        try {

            if (!demoTutorialResponseModelLstArray.get(position).getcIcon().equals("") || demoTutorialResponseModelLstArray.get(position).getcIcon() != null) {
                Picasso.with(activity)
                        .load(demoTutorialResponseModelLstArray.get(position).getcIcon())
                        .placeholder(R.mipmap.loading)   // optional
                        .error(R.mipmap.app_icon)      // optional
                                //.resize(250, 200)                        // optional
                                //.rotate(90)                             // optional
                        .into(holder.courseimageView);
            } else {
                holder.courseimageView.setImageResource(R.mipmap.app_icon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.textView.setText(demoTutorialResponseModelLstArray.get(position).getcName());

    }

    @Override
    public int getItemCount() {
        return demoTutorialResponseModelLstArray.size();
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView;
        ImageView courseimageView;

        LinearLayout itemLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_subject);
            courseimageView = (ImageView) itemView.findViewById(R.id.courseimageView);
            itemLayout = (LinearLayout) itemView.findViewById(item_layout);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(view, getPosition());
            }
        }
    }
}