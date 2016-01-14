package com.example.tangyuhuang.propinko.PartieJeDepose;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tangyuhuang.propinko.PartieJeDepose.AnnonceDispos;
import com.example.tangyuhuang.propinko.PartieJeDepose.AnnonceInfos;
import com.example.tangyuhuang.propinko.PartieJeDepose.Annonces;
import com.example.tangyuhuang.propinko.R;

public class AnnonceTarifsEtPhotos extends AppCompatActivity {

    String ownerId;
    String mail;
    String password;
    String accountDate;
    String etat;

    TextView tvValueTitreAnnonceTarifsEtPhotos;

    Button btnInfoAnnonceTarifsEtPhotos;
    Button btnDispoAnnonceTarifsEtPhotos;
    Button btnAjouterTarifAnnonceTarifsEtPhotos;
    Button btnAjouterPhotoAnnonceTarifsEtPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonce_tarifs_et_photos);

        Intent intent = getIntent();
        ownerId = intent.getStringExtra("ownerId").toString();
        mail = intent.getStringExtra("mail").toString();
        password = intent.getStringExtra("password").toString();
        accountDate = intent.getStringExtra("accountDate").toString();
        etat = intent.getStringExtra("etat").toString();

        tvValueTitreAnnonceTarifsEtPhotos = (TextView) findViewById(R.id.tvValueTitreAnnonceTarifsEtPhotos);
        tvValueTitreAnnonceTarifsEtPhotos.setText(intent.getStringExtra("TitreDeAnnonce"));

        btnInfoAnnonceTarifsEtPhotos = (Button) findViewById(R.id.btnInfoAnnonceTarifsEtPhotos);
        btnInfoAnnonceTarifsEtPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allerPageAnnonceInfos();
            }
        });

        btnDispoAnnonceTarifsEtPhotos = (Button) findViewById(R.id.btnDispoAnnonceTarifsEtPhotos);
        btnDispoAnnonceTarifsEtPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allerPageAnnonceDispos();
            }
        });

        btnAjouterTarifAnnonceTarifsEtPhotos = (Button) findViewById(R.id.btnAjouterTarifAnnonceTarifsEtPhotos);
        btnAjouterTarifAnnonceTarifsEtPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajouterTarif();
            }
        });

        btnAjouterPhotoAnnonceTarifsEtPhotos = (Button) findViewById(R.id.btnAjouterPhotoAnnonceTarifsEtPhotos);
        btnAjouterPhotoAnnonceTarifsEtPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajouterPhoto();
            }
        });

    }

    private void ajouterTarif(){

        //TODO write the code for add a price
    }

    private void ajouterPhoto(){

        //TODO write the code for add a photo
    }

    private void allerPageAnnonceInfos(){
        finish();
        Intent monIntent = new Intent(this, AnnonceInfos.class);
        monIntent.putExtra("ownerId",ownerId);
        monIntent.putExtra("mail", mail);
        monIntent.putExtra("password", password);
        monIntent.putExtra("accountDate",accountDate);
        monIntent.putExtra("etat",etat);
        monIntent.putExtra("TitreDeAnnonce",tvValueTitreAnnonceTarifsEtPhotos.getText().toString());
        startActivity(monIntent);
    }

    private void allerPageAnnonceDispos(){
        finish();
        Intent monIntent = new Intent(this, AnnonceDispos.class);
        monIntent.putExtra("ownerId",ownerId);
        monIntent.putExtra("mail", mail);
        monIntent.putExtra("password", password);
        monIntent.putExtra("accountDate",accountDate);
        monIntent.putExtra("etat",etat);
        monIntent.putExtra("TitreDeAnnonce",tvValueTitreAnnonceTarifsEtPhotos.getText().toString());
        startActivity(monIntent);
    }

    private void allerPageAnnonces(){
        finish();
        Intent monIntent = new Intent(this, Annonces.class);
        monIntent.putExtra("ownerId",ownerId);
        monIntent.putExtra("mail", mail);
        monIntent.putExtra("password", password);
        monIntent.putExtra("accountDate",accountDate);
        monIntent.putExtra("etat",etat);
        startActivity(monIntent);
    }

    public boolean onKeyDown(int keyCode,KeyEvent event){
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK:
                new AlertDialog.Builder(this).setMessage("Voulez-vous vraiment revenir à la page des annonces ?")
                        .setNegativeButton("Non",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                    }
                                })
                        .setPositiveButton("Oui",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int whichButton) {
                                        allerPageAnnonces();
                                    }
                                }).show();
        }
        return super.onKeyDown(keyCode, event);
    }
}
