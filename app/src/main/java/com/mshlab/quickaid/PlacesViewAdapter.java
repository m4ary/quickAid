package com.mshlab.quickaid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mshlab.quickaid.Obj.ResponeSearchPlcaes;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlacesViewAdapter extends RecyclerView.Adapter<PlacesViewAdapter.CustomViewHolder> {
    private List<ResponeSearchPlcaes.VenuesEntity> entityList;
    private Context mContext;

    public PlacesViewAdapter(Context context, List<ResponeSearchPlcaes.VenuesEntity> courceList) {
        this.entityList = courceList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.defalut_list_item, viewGroup, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        ResponeSearchPlcaes.VenuesEntity content = entityList.get(i);
        customViewHolder.textView.setText(content.getName());
        customViewHolder.note.setText(content.getLocation().getDistance() / 1000 + "Km");
        customViewHolder.address.setText(content.getLocation().getAddress());
        String url = content.getCategories().get(0).getIcon().getPrefix().replace("categories_v2", "categories")+"64"+ content.getCategories().get(0).getIcon().getSuffix();
        Log.e("URL", url);
        Picasso.with(mContext).load(url).into(customViewHolder.icon);
    }


    @Override
    public int getItemCount() {
        return (null != entityList ? entityList.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView textView;
        protected TextView note;
        protected ImageView icon;
        protected TextView address;

        public CustomViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(R.id.title);
            this.note = (TextView) view.findViewById(R.id.note);
            this.icon = (ImageView) view.findViewById(R.id.thumbnail);
            this.address = view.findViewById(R.id.ADDRESS); //Or just use Butterknife!


        }


    }


}