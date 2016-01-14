package com.example.tangyuhuang.propinko;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GererCompte extends AppCompatActivity {

    String TAG;
    String[] CHOIX;
    String ownerId;
    String mail;
    String password;
    String accountDate;
    String etat;
    String choixDeCivilite;

    // UI references.
    TextView etValueEmailGererCompte;
    TextView ValueprenomGererCompte;
    TextView ValuenomGererCompte;
    TextView etValueTelephoneParDefautGererCompte;
    TextView etValueNumeroDeRueGererCompte;
    TextView etValueNomDeRueGererCompte;
    TextView etValueCodePostaleGererCompte;
    TextView etValueVilleGererCompte;
    TextView etValueMotSurVousGererCompte;

    Button btnEnregistrerGererCompte;

    Spinner spinnerValueCiviliteGererCompte;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerer_compte);

        Intent intent = getIntent();
        ownerId = intent.getStringExtra("ownerId").toString();
        mail = intent.getStringExtra("mail").toString();
        password = intent.getStringExtra("password").toString();
        accountDate = intent.getStringExtra("accountDate").toString();
        etat = intent.getStringExtra("etat").toString();
        //Toast.makeText(GererCompte.this, ownerId + " " + mail + " " + password + " " + accountDate, Toast.LENGTH_SHORT).show();

        CHOIX = new String[]{intent.getStringExtra("Civilite").toString(), "M.", "Mme.", "Mlle."};

        etValueEmailGererCompte = (TextView) findViewById(R.id.etValueEmailGererCompte);
        ValueprenomGererCompte = (TextView) findViewById(R.id.prenomValueGererCompte);
        ValuenomGererCompte = (TextView) findViewById(R.id.nomValueGererCompte);
        etValueTelephoneParDefautGererCompte = (TextView) findViewById(R.id.etValueTelephoneParDefautGererCompte);
        etValueNumeroDeRueGererCompte = (TextView) findViewById(R.id.etValueNumeroDeRueGererCompte);
        etValueNomDeRueGererCompte = (TextView) findViewById(R.id.etValueNomDeRueGererCompte);
        etValueCodePostaleGererCompte = (TextView) findViewById(R.id.etValueCodePostaleGererCompte);
        etValueVilleGererCompte = (TextView) findViewById(R.id.etValueVilleGererCompte);
        etValueMotSurVousGererCompte = (TextView) findViewById(R.id.etValueMotSurVousGererCompte);

        etValueEmailGererCompte.setText(intent.getStringExtra("Email").toString());
        ValueprenomGererCompte.setText(intent.getStringExtra("Prenom").toString());
        ValuenomGererCompte.setText(intent.getStringExtra("Nom").toString());
        etValueTelephoneParDefautGererCompte.setText(intent.getStringExtra("TelephoneParDefaut").toString());
        etValueNumeroDeRueGererCompte.setText(intent.getStringExtra("NumeroDeRue").toString());
        etValueNomDeRueGererCompte.setText(intent.getStringExtra("NomDeRue").toString());
        etValueCodePostaleGererCompte.setText(intent.getStringExtra("CodePostale").toString());
        etValueVilleGererCompte.setText(intent.getStringExtra("Ville").toString());
        etValueMotSurVousGererCompte.setText(intent.getStringExtra("MotSurVous").toString());

        spinnerValueCiviliteGererCompte = (Spinner) findViewById(R.id.spinnerValueCiviliteGererCompte);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, CHOIX);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerValueCiviliteGererCompte.setAdapter(adapter);
        spinnerValueCiviliteGererCompte.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choixDeCivilite = CHOIX[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnEnregistrerGererCompte=(Button) findViewById(R.id.btnEnregistrerGererCompte);
        btnEnregistrerGererCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            modifierInfos();
            }
        });


        }

    public void modifierInfos() {

        //get the modifications
        String newOwnerId = ownerId;
        String newPrenom = ValueprenomGererCompte.getText().toString();
        String newMail = mail;
        String newTelephoneParDefaut = etValueTelephoneParDefautGererCompte.getText().toString();
        String newPassword = password;
        String newNom = ValuenomGererCompte.getText().toString();
        String newMotSurVous = etValueMotSurVousGererCompte.getText().toString();
        String newNumeroDeRue_ = etValueNumeroDeRueGererCompte.getText().toString();
        int newNumeroDeRue = Integer.parseInt(newNumeroDeRue_);
        String newNomDeRue = etValueNomDeRueGererCompte.getText().toString();
        String newVille = etValueVilleGererCompte.getText().toString();
        String newCodePostale_ = etValueCodePostaleGererCompte.getText().toString();
        int newCodePostale = Integer.parseInt(newCodePostale_);
        String newCivilite = choixDeCivilite;
        String newAccountDate = accountDate;

        //do the modification
        new loadPostTask().execute("http://www.lenkho.fr/propinko/index.php", ownerId, password, "updateOwner",
                    newOwnerId + "_" + newPrenom + "_" + newMail + "_" + newTelephoneParDefaut + "_" + newPassword + "_" +
                    newNom + "_" + newMotSurVous + "_" + newNumeroDeRue + "_" + newNomDeRue + "_" + newVille + "_" +
                    newCodePostale + "_" + newCivilite + "_" + newAccountDate);

        //go to the page MessageReussi
        finish();
        Intent monIntent = new Intent(this, MessageReussi.class);
        monIntent.putExtra("ownerId",ownerId);
        monIntent.putExtra("mail", mail);
        monIntent.putExtra("password", password);
        monIntent.putExtra("accountDate",accountDate);
        monIntent.putExtra("etat",etat);
        monIntent.putExtra("page", "GererCompte");
        startActivity(monIntent);
    }

    public void allerPageCompte() {
        finish();
        Intent monIntent = new Intent(this, Compte.class);
        monIntent.putExtra("ownerId",ownerId);
        monIntent.putExtra("mail", mail);
        monIntent.putExtra("password", password);
        monIntent.putExtra("accountDate",accountDate);
        monIntent.putExtra("etat",etat);
        startActivity(monIntent);
    }

    //set the fonction of button "back" on the telephone
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                new AlertDialog.Builder(this).setMessage("Voulez-vous vraiment annuler les modifications ?")
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
     * send the modifications of onwer to the web service
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
