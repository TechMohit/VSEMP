package com.example.varadhi.addstaff.EmployeeAdd;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.varadhi.addstaff.R;


import java.util.ArrayList;

/**
 * Created by varad_000 on 1/11/2018.
 */

public class ImageAdpater extends RecyclerView.Adapter<ImageViewHolder>  {
    private ArrayList<ImageModel> arrayList;
    private Context mContext;
    private int requestcode;

    public ImageAdpater(ArrayList<ImageModel> arrayList, Context mContext, int requestcode) {
        this.requestcode = requestcode;
        this.arrayList = arrayList;
        this.mContext = mContext;


    }


    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ImageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_row_document_info,null));

    }

    @Override
    public void onBindViewHolder( ImageViewHolder holder, final int position) {
        ImageModel imageModel = arrayList.get(position);
        if(imageModel.getImage()!=null){
          // holder.showClicekdImage.setImageURI(imageModel.getImage());

            Glide.with(mContext)
                    .load(Uri.parse(imageModel.getImage().toString()))
                    .into(holder.showClicekdImage);

            holder.showClicekdImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   setResourcestoViews(position);
                }
            });
        }
        else {
            holder.showClicekdImage.setVisibility(View.GONE);
            holder.layoutForPdfname.setVisibility(View.VISIBLE);
            holder.textPdf.setText(imageModel.getPdfFileName());
            holder.deletePdf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    arrayList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, arrayList.size());

                }
            });
        }


    }

    private void setResourcestoViews(final int position) {

        final Dialog settingsDialog = new Dialog(mContext);
        settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        settingsDialog.setContentView(LayoutInflater.from(mContext).inflate(R.layout.dialog_for_image_editing
                , null));

        ImageView imageView = settingsDialog.findViewById(R.id.clickedImageShow);
        Button deleteImage = settingsDialog.findViewById(R.id.delete);
        Button closeDialog = settingsDialog.findViewById(R.id.close);
        imageView.setImageURI(arrayList.get(position).getImage());

        deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, arrayList.size());
                settingsDialog.dismiss();
            }
        });

        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsDialog.dismiss();
            }
        });

        settingsDialog.show();

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}
