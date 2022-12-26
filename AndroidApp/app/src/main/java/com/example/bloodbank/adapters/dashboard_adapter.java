package com.example.bloodbank.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bloodbank.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class dashboard_adapter extends RecyclerView.Adapter<dashboard_adapter.dashboard_view_holder> {

    Context context;
    JSONArray jsonArray;

    public dashboard_adapter(Context context, JSONArray jsonArray) {
        this.context = context;
        this.jsonArray = jsonArray;
    }

    @NonNull
    @Override
    public dashboard_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new dashboard_view_holder(LayoutInflater.from(context).inflate(R.layout.dashboard_cards, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull dashboard_view_holder holder, int position) {
        try {
            JSONObject data = jsonArray.getJSONObject(position);
            int img = 0;
            if (data.getString("icon").equals("ic_baseline_local_hospital_24")){
                img = Integer.parseInt(String.valueOf(R.drawable.ic_baseline_local_hospital_24));

            }
            else if (data.getString("icon").equals("ic_baseline_people_24")){
                img = Integer.parseInt(String.valueOf(R.drawable.ic_baseline_people_24));
            }
            else if (data.getString("icon").equals("ic_baseline_people_24")){
                img = Integer.parseInt(String.valueOf(R.drawable.ic_baseline_people_24));
            }

            else if (data.getString("icon").equals("ic_baseline_bloodtype_24")){
                img = Integer.parseInt(String.valueOf(R.drawable.ic_baseline_bloodtype_24));
            }

            int background = 0;
            if (data.getString("background").equals("circlebacgroundpurple")){
                background = Integer.parseInt(String.valueOf(R.drawable.circlebacgroundpurple));

            }
            else if (data.getString("background").equals("circlebackgroundgreen")){
                background = Integer.parseInt(String.valueOf(R.drawable.circlebackgroundgreen));
            }
            else if (data.getString("background").equals("circlebackgroundyellow")){
                background = Integer.parseInt(String.valueOf(R.drawable.circlebackgroundyellow));
            }

            else if (data.getString("background").equals("circlebackgroundred")){
                background = Integer.parseInt(String.valueOf(R.drawable.circlebackgroundred));
            }


//            int drawalResourceId = holder.iv_hospital_img.getContext().getResources().getIdentifier(data.getString("icon"), "drawable", holder.iv_hospital_img.getContext().getPackageName());
//            Glide.with(holder.itemView.getContext()).load(drawalResourceId).into(holder.iv_hospital_img);

            holder.iv_img.setBackgroundResource(background);
            holder.iv_img.setImageResource(img);
            holder.tv_name.setText(data.getString("name"));
            holder.tv_total.setText(data.getString("total"));

        }catch (JSONException e){
            e.getMessage();
        }
    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }


    public class dashboard_view_holder extends RecyclerView.ViewHolder{

        TextView tv_name, tv_total;
        ImageView iv_img;

        public dashboard_view_holder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.name);
            tv_total = itemView.findViewById(R.id.total);
            iv_img = itemView.findViewById(R.id.img);

        }
    }



}
