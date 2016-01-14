package com.example.tangyuhuang.propinko.PartieJeDepose;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tangyuhuang.propinko.R;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class ChangePassword extends AppCompatActivity {

    String ownerId;
    String mail;
    String password;
    String accountDate;
    String etat;

    // UI references.
    TextView ancienPasswordChangePassword;
    TextView nouveauPasswordChangePassword;
    TextView confirNouveauPasswordChangePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        ownerId = intent.getStringExtra("ownerId").toString();
        mail = intent.getStringExtra("mail").toString();
        password = intent.getStringExtra("password").toString();
        accountDate = intent.getStringExtra("accountDate").toString();
        etat = intent.getStringExtra("etat").toString();

        ancienPasswordChangePassword = (TextView) findViewById(R.id.ancienPasswordChangePassword);
        nouveauPasswordChangePassword = (TextView) findViewById(R.id.nouveauPasswordChangePassword);
        confirNouveauPasswordChangePassword = (TextView) findViewById(R.id.confirNouveauPasswordChangePassword);


        Button btnEnregistrerChangePassword = (Button) findViewById(R.id.btnEnregistrerChangePassword);
        btnEnregistrerChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });

    }

    public void allerPageCompte(){
        finish();
        Intent monIntent = new Intent(this, Compte.class);
        monIntent.putExtra("ownerId",ownerId);
        monIntent.putExtra("mail", mail);
        monIntent.putExtra("password", password);
        monIntent.putExtra("accountDate",accountDate);
        monIntent.putExtra("etat",etat);
        startActivity(monIntent);
    }

    public void changePassword(){

        //TODO Write the code for change the password
        //get the new password
        String ancienPassword = ancienPasswordChangePassword.getText().toString();
        String newPassword = nouveauPasswordChangePassword.getText().toString();
        String confirNewPassword = confirNouveauPasswordChangePassword.getText().toString();

        if(!ancienPassword.equals(password)) {
            Toast.makeText(ChangePassword.this, "Ancien mot de passe n'est pas correct !", Toast.LENGTH_SHORT).show();
        }else{
            if(newPassword.equals(confirNewPassword)){
                //do the modification
                new loadPostTask().execute("http://www.lenkho.fr/propinko/index.php", ownerId, password, "changePassword",
                        newPassword + "_" + confirNewPassword);

                //go to the page MessageReussi
                finish();
                Intent monIntent = new Intent(this, MessageReussi.class);
                monIntent.putExtra("ownerId",ownerId);
                monIntent.putExtra("mail", mail);
                monIntent.putExtra("password", password);
                monIntent.putExtra("accountDate",accountDate);
                monIntent.putExtra("etat",etat);
                monIntent.putExtra("page", "ChangePassword");
                startActivity(monIntent);
            }else{
                Toast.makeText(ChangePassword.this, "Nouveau mot de passe et la confirmation ne sont pas identique !", Toast.LENGTH_SHORT).show();
            }
        }

    }

    //set the fonction of button "back" on the telephone
    public boolean onKeyDown(int keyCode,KeyEvent event){
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK:
                new AlertDialog.Builder(this).setMessage("Voulez-vous vraiment annuler ?")
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
                                        allerPageCompte();
                                    }
                                }).show();
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * send the modification of onwer to the web service
     */
    public String loadPost(String url, String id, String password, String action, String params) {

        try {

            HttpURLConnection c = (HttpURLConnection) (new URL(url)).openConnection();

            c.setRequestMethod("POST");
            c.setDoInput(true);
            c.setDoOutput(true);
            c.connect();

            c.getOutputStream().write(("own_id=" + id + "&own_password=" + password + "&action=" + action + "&params=" + params).getBytes());

            InputStream is = c.getInputStream();

            String r = IOUtils.toString(is);
            return r;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private class loadPostTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... args) {

            String url = args[0];
            String id = args[1];
            String password = args[2];
            String action = args[3];
            String params = args[4];
            String d = loadPost(url, id, password,action, params);

            return d;
        }

        @Override
        protected void onPostExecute(String r){

        }

    }
}
