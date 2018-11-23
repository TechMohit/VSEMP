package com.example.varadhi.addstaff.EmployeeAdd;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.varadhi.addstaff.R;

/**
 * Created by varad_000 on 1/11/2018.
 */

public class ImageViewHolder extends RecyclerView.ViewHolder {
    ImageView showClicekdImage , deletePdf;
    TextView textPdf;
    FrameLayout layoutForPdfname;
    public ImageViewHolder(View itemView) {
        super(itemView);
        showClicekdImage = itemView.findViewById(R.id.clickedImage);
        deletePdf = itemView.findViewById(R.id.imageView_close);
        textPdf = itemView.findViewById(R.id.textViewForPdfName);
        layoutForPdfname = itemView.findViewById(R.id.frameLayoutForPdfName);
    }
}
