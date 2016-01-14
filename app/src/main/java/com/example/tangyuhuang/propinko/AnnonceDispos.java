package com.example.tangyuhuang.propinko;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AnnonceDispos extends AppCompatActivity {

    String ownerId;
    String mail;
    String password;
    String accountDate;
    String etat;

    TextView tvValueTitreAnnonceDispos;

    Button btnInfoAnnonceDispos;
    Button btnTarifEtPhotoAnnonceDispos;
    Button btnAjouterIndisposAnnonceDispos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonce_dispos);

        Intent intent = getIntent();
        ownerId = intent.getStringExtra("ownerId").toString();
        mail = intent.getStringExtra("mail").toString();
        password = intent.getStringExtra("password").toString();
        accountDate = intent.getStringExtra("accountDate").toString();
        etat = intent.getStringExtra("etat").toString();

        tvValueTitreAnnonceDispos = (TextView) findViewById(R.id.tvValueTitreAnnonceDispos);
        tvValueTitreAnnonceDispos.setText(intent.getStringExtra("TitreDeAnnonce"));

        btnInfoAnnonceDispos = (Button) findViewById(R.id.btnInfoAnnonceDispos);
        btnInfoAnnonceDispos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allerPageAnnonceInfos();
            }
        });

        btnTarifEtPhotoAnnonceDispos = (Button) findViewById(R.id.btnTarifEtPhotoAnnonceDispos);
        btnTarifEtPhotoAnnonceDispos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allerPageAnnonceTatifsEtPhotos();
            }
        });

        btnAjouterIndisposAnnonceDispos = (Button) findViewById(R.id.btnAjouterIndisposAnnonceDispos);
        btnAjouterIndisposAnnonceDispos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajouterIndispos();
            }
        });

    }

    private void ajouterIndispos(){

        //TODO write the code for add new holding time
    }

    private void allerPageAnnonceInfos(){
        finish();
        Intent monIntent = new Intent(this, AnnonceInfos.class);
        monIntent.putExtra("ownerId",ownerId);
        monIntent.putExtra("mail", mail);
        monIntent.putExtra("password", password);
        monIntent.putExtra("accountDate",accountDate);
        monIntent.putExtra("etat",etat);
        monIntent.putExtra("TitreDeAnnonce",tvValueTitreAnnonceDispos.getText().toString());
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
        monIntent.putExtra("TitreDeAnnonce",tvValueTitreAnnonceDispos.getText().toString());
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
