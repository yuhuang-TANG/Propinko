package com.example.tangyuhuang.propinko.PartieJeDepose;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;


import android.os.AsyncTask;

import com.example.tangyuhuang.propinko.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Compte extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String TAG = "Test";
    String ownerId;
    String mail;
    String password;
    String accountDate;
    String etat;

    // UI references.
    ImageView imageUserCompte;

    TextView tvValueEmailCompte;
    TextView tvValueCiviliteCompte;
    TextView tvValuePrenomCompte;
    TextView tvValueNomCompte;
    TextView tvValueTelephoneParDefautCompte;
    TextView tvValueNumeroDeRueCompte;
    TextView tvValueNomDeRueCompte;
    TextView tvValueCodePostaleCompte;
    TextView tvValueVilleCompte;
    TextView tvValueMotSurVousCompte;

    Button btnModifierImageUserCompte;
    Button btnModifierInfosCompte;
    Button btnModifierMotDePasseCompte;

    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent =getIntent();
        ownerId = intent.getStringExtra("ownerId").toString();
        mail = intent.getStringExtra("mail").toString();
        password = intent.getStringExtra("password").toString();
        accountDate = intent.getStringExtra("accountDate").toString();
        etat = intent.getStringExtra("etat").toString();
        //Toast.makeText(Compte.this, ownerId + " " + mail + " " + password + " " + accountDate, Toast.LENGTH_SHORT).show();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        imageUserCompte = (ImageView) findViewById(R.id.imageUserCompte);
        //TODO set the image of user

        btnModifierImageUserCompte = (Button) findViewById(R.id.btnModifierImageUserCompte);
        btnModifierImageUserCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifierImage();
            }
        });

        btnModifierInfosCompte = (Button) findViewById(R.id.btnModifierInfosCompte);
        btnModifierInfosCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allerPageGererCompte();
            }
        });

        btnModifierMotDePasseCompte = (Button) findViewById(R.id.btnModifierMotDePasseCompte);
        btnModifierMotDePasseCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allerPageModifPassword();
            }
        });

        tvValueEmailCompte = (TextView) findViewById(R.id.tvValueEmailCompte);
        tvValueCiviliteCompte = (TextView) findViewById(R.id.tvValueCiviliteCompte);
        tvValuePrenomCompte = (TextView) findViewById(R.id.tvValuePrenomCompte);
        tvValueNomCompte = (TextView) findViewById(R.id.tvValueNomCompte);
        tvValueTelephoneParDefautCompte = (TextView) findViewById(R.id.tvValueTelephoneParDefautCompte);
        tvValueNumeroDeRueCompte = (TextView) findViewById(R.id.tvValueNumeroDeRueCompte);
        tvValueNomDeRueCompte = (TextView) findViewById(R.id.tvValueNomDeRueCompte);
        tvValueCodePostaleCompte = (TextView) findViewById(R.id.tvValueCodePostaleCompte);
        tvValueVilleCompte = (TextView) findViewById(R.id.tvValueVilleCompte);
        tvValueMotSurVousCompte = (TextView) findViewById(R.id.tvValueMotSurVousCompte);

        //set the information into the textView
        new loadPostTask().execute("http://www.lenkho.fr/propinko/index.php", "retrieveOwnerByMail", mail);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.compte, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar list_annonces clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_deconnexion) {


            new AlertDialog.Builder(this).setMessage("Voulez-vous vraiment déconnecter ?")
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
                                    allerPageConnexion();
                                }
                            }).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view list_annonces clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_tableauDuBord) {
            finish();
            Intent monIntent = new Intent(this, TableauDuBord.class);
            monIntent.putExtra("ownerId",ownerId);
            monIntent.putExtra("mail", mail);
            monIntent.putExtra("password", password);
            monIntent.putExtra("accountDate",accountDate);
            monIntent.putExtra("etat",etat);
            startActivity(monIntent);
        } else if (id == R.id.nav_annonces) {
            finish();
            Intent monIntent = new Intent(this, Annonces.class);
            monIntent.putExtra("ownerId",ownerId);
            monIntent.putExtra("mail", mail);
            monIntent.putExtra("password", password);
            monIntent.putExtra("accountDate",accountDate);
            monIntent.putExtra("etat",etat);
            startActivity(monIntent);
        } else if (id == R.id.nav_compte) {
            closeOptionsMenu();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void modifierImage(){
        //TODO write the code for change the image
    }

    public void allerPageConnexion() {
        finish();
        Intent monIntent = new Intent(this, Connexion.class);
        monIntent.putExtra("mail", mail);
        monIntent.putExtra("etat",etat);
        startActivity(monIntent);
    }

    public void allerPageModifPassword() {
        finish();
        Intent monIntent = new Intent(this, ChangePassword.class);
        monIntent.putExtra("ownerId",ownerId);
        monIntent.putExtra("mail", mail);
        monIntent.putExtra("password", password);
        monIntent.putExtra("accountDate",accountDate);
        monIntent.putExtra("etat",etat);
        startActivity(monIntent);
    }

    public void allerPageGererCompte() {
        finish();
        Intent monIntent = new Intent(this, GererCompte.class);
        monIntent.putExtra("ownerId",ownerId);
        monIntent.putExtra("mail", mail);
        monIntent.putExtra("password",password);
        monIntent.putExtra("accountDate",accountDate);
        monIntent.putExtra("etat",etat);
        monIntent.putExtra("Email", tvValueEmailCompte.getText().toString());
        monIntent.putExtra("Civilite", tvValueCiviliteCompte.getText().toString());
        monIntent.putExtra("Prenom", tvValuePrenomCompte.getText().toString());
        monIntent.putExtra("Nom", tvValueNomCompte.getText().toString());
        monIntent.putExtra("TelephoneParDefaut", tvValueTelephoneParDefautCompte.getText().toString());
        monIntent.putExtra("NumeroDeRue", tvValueNumeroDeRueCompte.getText().toString());
        monIntent.putExtra("NomDeRue", tvValueNomDeRueCompte.getText().toString());
        monIntent.putExtra("CodePostale", tvValueCodePostaleCompte.getText().toString());
        monIntent.putExtra("Ville", tvValueVilleCompte.getText().toString());
        monIntent.putExtra("MotSurVous", tvValueMotSurVousCompte.getText().toString());
        startActivity(monIntent);
    }

    //stop the physical button 'back'
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    /**
     * get the informations of onwer from the web service
     */
    public String loadPost(String url, String action, String params) {

        try {

            HttpURLConnection c = (HttpURLConnection) (new URL(url)).openConnection();

            c.setRequestMethod("POST");
            c.setDoInput(true);
            c.setDoOutput(true);
            c.connect();

            c.getOutputStream().write(("action=" + action + "&params=" + params).getBytes());

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
            String action = args[1];
            String params = args[2];
            String d = loadPost(url, action, params);

            return d;
        }

        @Override
        protected void onPostExecute(String r){

            //set the informations in the textView
            try{
                JSONObject infoProprietaire = new JSONObject(r);
                tvValueEmailCompte.setText(infoProprietaire.getString("mail"));
                tvValueCiviliteCompte.setText(infoProprietaire.getString("civility"));
                tvValuePrenomCompte.setText(infoProprietaire.getString("lastname"));
                tvValueNomCompte.setText(infoProprietaire.getString("firstname"));
                tvValueTelephoneParDefautCompte.setText(infoProprietaire.getString("defaultPhone"));
                tvValueNumeroDeRueCompte.setText(infoProprietaire.getString("streetNumber"));
                tvValueNomDeRueCompte.setText(infoProprietaire.getString("address"));
                tvValueCodePostaleCompte.setText(infoProprietaire.getString("postalCode"));
                tvValueVilleCompte.setText(infoProprietaire.getString("city"));
                tvValueMotSurVousCompte.setText(infoProprietaire.getString("description"));

            }catch (Throwable throwable){
                Log.d(TAG,"Json format invalid!");
            }

        }

    }

}
