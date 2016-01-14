package com.example.tangyuhuang.propinko;

import android.app.Application;

/**
 * Created by TANG YUHUANG on 2016/1/7.
 */
public class MyApplication extends Application {

    private int nombreDeAnnonces;

    public int getNombreDeAnnonces() {
        return nombreDeAnnonces;
    }

    public void setNombreDeAnnonces(int nombreDeAnnonces) {
        this.nombreDeAnnonces = nombreDeAnnonces;
    }

}
