package com.example.tangyuhuang.propinko;

import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;


public class TableauDuBord extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String TAG = "Test";
    String ownerId;
    String mail;
    String password;
    String accountDate;
    String etat;
    int nombreDeAnnonces;


    // UI references.
    ImageView imageUserTableauDuBord;

    TextView tvUserNameTableauDuBord;
    TextView tvNombreAnnonceTableauDuNord;
    TextView tvNomEtPrenomUserTableauDuBord;
    TextView tv3TableauDuBord;

    Button btnDeposeTableauDuBord;

    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tableau_du_bord);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        mail = intent.getStringExtra("mail").toString();
        password = intent.getStringExtra("password").toString();
        if(intent.getStringExtra("etat") != null)
            etat = intent.getStringExtra("etat").toString();
        //Toast.makeText(TableauDuBord.this, etat, Toast.LENGTH_SHORT).show();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        imageUserTableauDuBord = (ImageView) findViewById(R.id.imageUserTableauDuBord);
        //TODO set the image of user

        btnDeposeTableauDuBord = (Button) findViewById(R.id.btnDeposeTableauDuBord);
        btnDeposeTableauDuBord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allerPageDepotAnnonce();
            }
        });

        tvUserNameTableauDuBord = (TextView) findViewById(R.id.tvInscriptionDureeTableauDuBord);
        tvNombreAnnonceTableauDuNord = (TextView) findViewById(R.id.tvNombreAnnonceTableauDuNord);
        tvNomEtPrenomUserTableauDuBord = (TextView) findViewById(R.id.tvNomEtPrenomUserTableauDuBord);
        tv3TableauDuBord = (TextView) findViewById(R.id.tv3TableauDuBord);

        //set the information into the textView
        new loadPostTask1().execute("http://www.lenkho.fr/propinko/index.php", "retrieveOwnerByMail", mail);

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
        getMenuInflater().inflate(R.menu.tableau_du_bord, menu);
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
            closeOptionsMenu();
        } else if (id == R.id.nav_annonces) {
            finish();
            Intent monIntent = new Intent(this, Annonces.class);
            monIntent.putExtra("ownerId",ownerId);
            monIntent.putExtra("mail", mail);
            monIntent.putExtra("password",password);
            monIntent.putExtra("accountDate",accountDate);
            monIntent.putExtra("etat",etat);
            monIntent.putExtra("nombreDeAnnonces",nombreDeAnnonces);
            startActivity(monIntent);
        } else if (id == R.id.nav_compte) {
            finish();
            Intent monIntent = new Intent(this, Compte.class);
            monIntent.putExtra("ownerId",ownerId);
            monIntent.putExtra("mail", mail);
            monIntent.putExtra("password",password);
            monIntent.putExtra("accountDate",accountDate);
            monIntent.putExtra("etat",etat);
            startActivity(monIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void allerPageDepotAnnonce(){
        finish();
        Intent monIntent = new Intent(this, DepotAnnonce.class);
        monIntent.putExtra("ownerId",ownerId);
        monIntent.putExtra("mail", mail);
        monIntent.putExtra("password",password);
        monIntent.putExtra("accountDate",accountDate);
        monIntent.putExtra("etat",etat);
        startActivity(monIntent);
    }

    private void allerPageConnexion() {
        this.finish();
        Intent monIntent = new Intent(this, Connexion.class);
        monIntent.putExtra("mail", mail);
        monIntent.putExtra("etat",etat);
        startActivity(monIntent);
    }

    //stop the physical button 'back'
    public boolean onKeyDown(int keyCode,KeyEvent event){
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK:return true;
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

    //get the informations of the owner by his mail address
    private class loadPostTask1 extends AsyncTask<String, Void, String> {

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

            try{
                JSONObject infoProprietaire = new JSONObject(r);
                tvNomEtPrenomUserTableauDuBord.setText(infoProprietaire.getString("lastname") + " " + infoProprietaire.getString("firstname"));
                tvUserNameTableauDuBord.setText("Membre depuis le \n" + infoProprietaire.getString("accountDate"));
                ownerId = infoProprietaire.getString("id");
                accountDate = infoProprietaire.getString("accountDate");
                new loadPostTask2().execute("http://www.lenkho.fr/propinko/index.php",
                        "getNumberOfProductsByOwnerId", infoProprietaire.getString("id"));
            }catch (Throwable throwable){
                Log.d(TAG, "Json format invalid!");
            }

        }

    }

    //get the number of adds published by owner id
    private class loadPostTask2 extends AsyncTask<String, Void, String> {

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
            try{
                tvNombreAnnonceTableauDuNord.setText(r + " annonce(s) publiée(s)");
                //nombreDeAnnonces = Integer.parseInt(r);
                MyApplication app = (MyApplication) getApplicationContext();
                String nombre = r.replaceAll("[\n\r]","");
                app.setNombreDeAnnonces(Integer.valueOf(nombre));
            }catch (Throwable throwable){
                Log.d(TAG, "Bug!");
            }


        }

    }
}
