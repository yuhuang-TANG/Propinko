package com.example.tangyuhuang.propinko;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class DepotAnnonce extends AppCompatActivity {

    String ownerId;
    String mail;
    String password;
    String accountDate;
    String etat;

    static final String[] CHOIX = new String[]{" ","Categorie A", "Categorie B", "Categorie C"};

    // UI references.
    EditText etTitreDepotAnnonce;
    EditText etDescriptionDepotAnnonce;
    EditText etPrix1DepotAnnonce;
    EditText etPrix3DepotAnnonce;
    EditText etPrix7DepotAnnonce;
    EditText etPrix15DepotAnnonce;

    Button btnAjouterPrixDepotAnnonce;
    Button btnAjouterPhotoDepotAnnonce;
    Button btnDeposerDepotAnnonce;

    Spinner spinnerCategorieDepotAnnonce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depot_annonce);

        Intent intent = getIntent();
        ownerId = intent.getStringExtra("ownerId").toString();
        mail = intent.getStringExtra("mail").toString();
        password = intent.getStringExtra("password").toString();
        accountDate = intent.getStringExtra("accountDate").toString();
        etat = intent.getStringExtra("etat").toString();

        etTitreDepotAnnonce = (EditText) findViewById(R.id.etTitreDepotAnnonce);
        etDescriptionDepotAnnonce = (EditText) findViewById(R.id.etDescriptionDepotAnnonce);
        etPrix1DepotAnnonce = (EditText) findViewById(R.id.etPrix1DepotAnnonce);
        etPrix3DepotAnnonce = (EditText) findViewById(R.id.etPrix3DepotAnnonce);
        etPrix7DepotAnnonce = (EditText) findViewById(R.id.etPrix7DepotAnnonce);
        etPrix15DepotAnnonce = (EditText) findViewById(R.id.etPrix15DepotAnnonce);

        spinnerCategorieDepotAnnonce = (Spinner) findViewById(R.id.spinnerCategorieDepotAnnonce);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, CHOIX);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategorieDepotAnnonce.setAdapter(adapter);

        btnAjouterPrixDepotAnnonce = (Button) findViewById(R.id.btnAjouterPrixDepotAnnonce);
        btnAjouterPrixDepotAnnonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajouterPrix();
            }
        });

        btnAjouterPhotoDepotAnnonce = (Button) findViewById(R.id.btnAjouterPhotoDepotAnnonce);
        btnAjouterPhotoDepotAnnonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajouterPhoto();
            }
        });

        btnDeposerDepotAnnonce = (Button) findViewById(R.id.btnDeposerDepotAnnonce);
        btnDeposerDepotAnnonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deposserAnnonce();
            }
        });
    }

    private void ajouterPrix(){
        //TODO write the code for add a price
    }

    private void ajouterPhoto(){
        //TODO write the code for add a photo
    }

    private void deposserAnnonce(){

        //TODO write the code for upload ad

        finish();
        Intent monIntent = new Intent(this, MessageReussi.class);
        monIntent.putExtra("ownerId",ownerId);
        monIntent.putExtra("mail", mail);
        monIntent.putExtra("password", password);
        monIntent.putExtra("accountDate",accountDate);
        monIntent.putExtra("etat",etat);
        monIntent.putExtra("page", "DepotAnnonce");
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
                new AlertDialog.Builder(this).setMessage("Voulez-vous vraiment annuler le dépôt d'annonce ?")
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
