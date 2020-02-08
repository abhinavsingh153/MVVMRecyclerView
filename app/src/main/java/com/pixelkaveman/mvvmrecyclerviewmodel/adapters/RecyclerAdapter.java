package com.pixelkaveman.mvvmrecyclerviewmodel.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.pixelkaveman.mvvmrecyclerviewmodel.R;
import com.pixelkaveman.mvvmrecyclerviewmodel.models.NicePlace;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<NicePlace> nicePlaces= new ArrayList<>();
    private Context context;

    public RecyclerAdapter( Context context, List<NicePlace> nicePlaces) {
        this.nicePlaces = nicePlaces;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent , false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        // set the name if teh nice place
        // Set the name of the 'NicePlace'
        ((ViewHolder)holder).mName.setText(nicePlaces.get(position).getTitle());

        // set the image
        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);
        Glide.with(context)
                .setDefaultRequestOptions(defaultOptions)
                .load(nicePlaces.get(position).getImageUrl())
                .into(((ViewHolder)holder).mImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(context , Integer.valueOf(position).toString(),Toast.LENGTH_SHORT).show();
                Toast.makeText(context , nicePlaces.get(position).getImageUrl(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return nicePlaces.size();
    }


    private class ViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView mImage;
        private TextView mName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image);
            mName = itemView.findViewById(R.id.image_name);
        }
    }
}
