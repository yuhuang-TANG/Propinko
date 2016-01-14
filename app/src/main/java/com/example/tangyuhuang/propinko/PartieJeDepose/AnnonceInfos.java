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

import com.example.tangyuhuang.propinko.R;

public class AnnonceInfos extends AppCompatActivity {

    String ownerId;
    String mail;
    String password;
    String accountDate;
    String etat;

    TextView tvValueTitreAnnonceInfos;

    Button btnDispoAnnonceInfos;
    Button btnTarifEtPhotoAnnonceInfos;
    Button btnEnregistrerAnnonceInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonce_infos);

        Intent intent = getIntent();
        ownerId = intent.getStringExtra("ownerId").toString();
        mail = intent.getStringExtra("mail").toString();
        password = intent.getStringExtra("password").toString();
        accountDate = intent.getStringExtra("accountDate").toString();
        etat = intent.getStringExtra("etat").toString();

        tvValueTitreAnnonceInfos = (TextView) findViewById(R.id.tvValueTitreAnnonceInfos);
        tvValueTitreAnnonceInfos.setText(intent.getStringExtra("TitreDeAnnonce"));

        btnDispoAnnonceInfos = (Button) findViewById(R.id.btnDispoAnnonceInfos);
        btnDispoAnnonceInfos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allerPageAnnonceDispos();
            }
        });

        btnTarifEtPhotoAnnonceInfos = (Button) findViewById(R.id.btnTarifEtPhotoAnnonceInfos);
        btnTarifEtPhotoAnnonceInfos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allerPageAnnonceTatifsEtPhotos();
            }
        });

        btnEnregistrerAnnonceInfos = (Button) findViewById(R.id.btnEnregistrerAnnonceInfos);
        btnEnregistrerAnnonceInfos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifierInfosAnnonce();
            }
        });

    }

    private void modifierInfosAnnonce(){

        //TODO write the code for modifier the informations of the annonce
    }

    private void allerPageAnnonceDispos(){
        finish();
        Intent monIntent = new Intent(this, AnnonceDispos.class);
        monIntent.putExtra("ownerId",ownerId);
        monIntent.putExtra("mail", mail);
        monIntent.putExtra("password", password);
        monIntent.putExtra("accountDate",accountDate);
        monIntent.putExtra("etat",etat);
        monIntent.putExtra("TitreDeAnnonce",tvValueTitreAnnonceInfos.getText().toString());
        startActivity(monIntent);
    }

    private void allerPageAnnonceTatifsEtPhotos(){
        finish();
        Intent monIntent = new Intent(this, AnnonceTarifsEtPhotos.class);
        monIntent.putExtra("ownerId",ownerId);
        monIntent.putExtra("mail", mail);
        monIntent.putExtra("password", password);
        monIntent.putExtra("accountDate",accountDate);
        monIntent.putExtra("etat",etat);
        monIntent.putExtra("TitreDeAnnonce",tvValueTitreAnnonceInfos.getText().toString());
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
