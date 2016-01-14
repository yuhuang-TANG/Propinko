package com.example.tangyuhuang.propinko.PartieJeDepose;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tangyuhuang.propinko.PartieJeDepose.Annonces;
import com.example.tangyuhuang.propinko.PartieJeDepose.Compte;
import com.example.tangyuhuang.propinko.R;

public class MessageReussi extends AppCompatActivity {

    String ownerId;
    String mail;
    String password;
    String accountDate;
    String etat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_reussi);

        Intent intent = getIntent();
        ownerId = intent.getStringExtra("ownerId").toString();
        mail = intent.getStringExtra("mail").toString();
        password = intent.getStringExtra("password").toString();
        accountDate = intent.getStringExtra("accountDate").toString();
        etat = intent.getStringExtra("etat").toString();

        TextView tv1MessageReussi = (TextView) findViewById(R.id.tv1MessageReussi);
        TextView tv2MessageReussi = (TextView) findViewById(R.id.tv2MessageReussi);

        if(intent.getStringExtra("page").equals("DepotAnnonce")){
            tv1MessageReussi.setText("Votre annonce a été déposé.");
            tv2MessageReussi.setText("( Click Ok pour revenir à la page annonces. )");
        }
        else if(intent.getStringExtra("page").equals("GererCompte")){
            tv1MessageReussi.setText("Vos informations ont été modifiés.");
            tv2MessageReussi.setText("( Click Ok pour revenir à la page compte. )");
        }
        else if(intent.getStringExtra("page").equals("ChangePassword")){
            tv1MessageReussi.setText("Votre mot de passe a été modifié.");
            tv2MessageReussi.setText("( Click Ok pour revenir à la page compte. )");
        }

        Button btnOkMessageReussi = (Button) findViewById(R.id.btnOkMessageReussi);
        btnOkMessageReussi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }

    public void back(){

        //TODO Write the code for waiting 10 seconds

        finish();

        //back to the page DepotAnnonce
        if(getIntent().getStringExtra("page").equals("DepotAnnonce")){
            Intent monIntent = new Intent(this, Annonces.class);
            monIntent.putExtra("ownerId",ownerId);
            monIntent.putExtra("mail", mail);
            monIntent.putExtra("password", password);
            monIntent.putExtra("accountDate",accountDate);
            monIntent.putExtra("etat",etat);
            startActivity(monIntent);
        }
        //back to the page Compte
        if(getIntent().getStringExtra("page").equals("GererCompte")){
            Intent monIntent = new Intent(this, Compte.class);
            monIntent.putExtra("ownerId",ownerId);
            monIntent.putExtra("mail", mail);
            monIntent.putExtra("password", password);
            monIntent.putExtra("accountDate",accountDate);
            monIntent.putExtra("etat",etat);
            startActivity(monIntent);
        }
        //back to the page Compte
        else if(getIntent().getStringExtra("page").equals("ChangePassword")){
            Intent monIntent = new Intent(this, Compte.class);
            monIntent.putExtra("ownerId",ownerId);
            monIntent.putExtra("mail", mail);
            monIntent.putExtra("password", password);
            monIntent.putExtra("accountDate",accountDate);
            monIntent.putExtra("etat",etat);
            startActivity(monIntent);
        }

    }


    //stop the physical button 'back'
    public boolean onKeyDown(int keyCode,KeyEvent event){
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK:
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
