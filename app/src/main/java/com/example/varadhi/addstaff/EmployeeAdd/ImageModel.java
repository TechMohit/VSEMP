package com.example.varadhi.addstaff.EmployeeAdd;

import android.net.Uri;

/**
 * Created by varad_000 on 1/11/2018.
 */

public class ImageModel {
    Uri image;
    String pdfFileName;

    public ImageModel(Uri image, String pdfFileName) {
        this.image = image;
        this.pdfFileName = pdfFileName;
    }

    public ImageModel(Uri image) {
        this.image = image;
    }

    public ImageModel(String pdfFileName) {
        this.pdfFileName = pdfFileName;
    }

    public String getPdfFileName() {
        return pdfFileName;
    }

    public Uri getImage() {
        return image;
    }
}
