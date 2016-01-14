package com.example.tangyuhuang.propinko.PatieJeCherche;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tangyuhuang.propinko.Model.Owner;
import com.example.tangyuhuang.propinko.Model.Product;
import com.example.tangyuhuang.propinko.R;

import java.io.InputStream;

/**
 * Created by Thais on 09/12/2015.
 */

public class Annonce extends ActionBarActivity {

    private final static String URL = "http://www.lenkho.fr/propinko/index.php";
    private String ACTION;
    private String PARAMS;
    private String mail = null;
    private String defaultPhone = null;

    Button btn_descriptions;
    Button btn_tarifs;
    Button btn_disponibilites;
    Button btn_contacts;

    TextView txt_descriptions;
    TextView txt_tarifs;
    TextView txt_contacts;
    TextView txt_disponibilites;

    TextView text;
    Button btn_voir_num_contacts;
    Button btn_num_contacts;
    Button btn_email_contacts;
    TableLayout tableau_tarifs;
    Button bSearch;
    ImageView picture;

    CalendarView calendar;

    Product produit;
    private final static String PIC_PROD_FULLSIZE = "http://www.lenkho.fr/propinko/Pictures/Products/fullsize/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonce);

        String p;

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                p = "";
            } else {
                p = extras.getString("product");
            }
        } else {
            p = (String) savedInstanceState.getSerializable("product");
        }


        String[] tableau = p.split("_");
        produit = new Product(tableau[0], tableau[1], tableau[2], tableau[3], tableau[4], tableau[5], tableau[6], tableau[7]);
        LoadOwner();// après l'initialisation du produitc car la méthode a besoin de ownerId pour charger le propriétaire


        btn_descriptions = (Button) findViewById(R.id.btn_descriptions);
        btn_tarifs = (Button) findViewById(R.id.btn_tarifs);
        btn_disponibilites = (Button) findViewById(R.id.btn_disponibilites);
        btn_contacts = (Button) findViewById(R.id.btn_contacts);

        txt_descriptions = (TextView) findViewById(R.id.txt_descriptions);
        txt_descriptions.setText(produit.getName());
        txt_tarifs = (TextView) findViewById(R.id.txt_tarifs);
        txt_contacts = (TextView) findViewById(R.id.txt_contacts);
        txt_disponibilites = (TextView) findViewById(R.id.txt_disponibilites);

        calendar = (CalendarView) findViewById(R.id.calendarViewAnnonceDispos);

        text = (TextView) findViewById(R.id.text_descriptions);
        text.setText(produit.getDescription());
        btn_voir_num_contacts = (Button) findViewById(R.id.btn_voir_num_contacts);
        btn_num_contacts = (Button) findViewById(R.id.btn_num_contacts);
        btn_email_contacts = (Button) findViewById(R.id.btn_email_contacts);
        tableau_tarifs = (TableLayout) findViewById(R.id.tableau_tarifs);
        picture = (ImageView) findViewById(R.id.picture);
        new DownloadImageTask().execute(PIC_PROD_FULLSIZE + produit.getId() + produit.getPhotoExtension());


        bSearch = (Button) findViewById(R.id.commonFooter_bSearch);
        bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Annonce.this, SearchProducts.class);
                startActivity(intent);

            }
        });

    }

    public void LoadOwner(){
        ACTION = "retrieveOwnerById";
        PARAMS = produit.getOwnerId();
        new DownloadOwnerTask().execute(URL, ACTION, PARAMS);
}

    public void Disponibilites (View view) {
        txt_descriptions.setVisibility(View.INVISIBLE);
        txt_tarifs.setVisibility(View.INVISIBLE);
        txt_contacts.setVisibility(View.INVISIBLE);
        txt_disponibilites.setVisibility(View.VISIBLE);

        text.setVisibility(View.INVISIBLE);
        btn_voir_num_contacts.setVisibility(View.INVISIBLE);
        btn_num_contacts.setVisibility(View.INVISIBLE);
        btn_email_contacts.setVisibility(View.INVISIBLE);
        tableau_tarifs.setVisibility(View.INVISIBLE);
        calendar.setVisibility(View.VISIBLE);


    }
    public void Envoyer_email (View view) {

        Intent intent = new Intent(Annonce.this, Envoyer_email.class);
        if (mail != null)
        {
            intent.putExtra("mail", mail);
            startActivity(intent);
        }
        else
            Toast.makeText(this, "Chargement propriétaire...", Toast.LENGTH_SHORT);


    }
    public void Descriptions (View view) {

        txt_descriptions.setVisibility(View.VISIBLE);
        txt_tarifs.setVisibility(View.INVISIBLE);
        txt_contacts.setVisibility(View.INVISIBLE);
        calendar.setVisibility(View.INVISIBLE);
        txt_disponibilites.setVisibility(View.INVISIBLE);

        text.setVisibility(View.VISIBLE);
        btn_voir_num_contacts.setVisibility(View.INVISIBLE);
        btn_num_contacts.setVisibility(View.INVISIBLE);
        btn_email_contacts.setVisibility(View.INVISIBLE);
        tableau_tarifs.setVisibility(View.INVISIBLE);
    }
    public void Tarifs (View view) {

        txt_descriptions.setVisibility(View.INVISIBLE);
        txt_tarifs.setVisibility(View.VISIBLE);
        txt_contacts.setVisibility(View.INVISIBLE);
        calendar.setVisibility(View.INVISIBLE);
        txt_disponibilites.setVisibility(View.INVISIBLE);

        text.setVisibility(View.INVISIBLE);
        btn_voir_num_contacts.setVisibility(View.INVISIBLE);
        btn_num_contacts.setVisibility(View.INVISIBLE);
        btn_email_contacts.setVisibility(View.INVISIBLE);
        tableau_tarifs.setVisibility(View.VISIBLE);
    }

    public void Contacts (View view) {

        txt_descriptions.setVisibility(View.INVISIBLE);
        txt_tarifs.setVisibility(View.INVISIBLE);
        txt_contacts.setVisibility(View.VISIBLE);
        calendar.setVisibility(View.INVISIBLE);
        txt_disponibilites.setVisibility(View.INVISIBLE);

        text.setVisibility(View.INVISIBLE);
        btn_voir_num_contacts.setVisibility(View.VISIBLE);
        btn_num_contacts.setVisibility(View.INVISIBLE);
        btn_email_contacts.setVisibility(View.VISIBLE);
        tableau_tarifs.setVisibility(View.INVISIBLE);


    }
    public void Voir_numero_contacts (View view) {
        btn_voir_num_contacts.setVisibility(View.INVISIBLE);
        if (defaultPhone != null)
        {
            btn_num_contacts.setText(defaultPhone);
            btn_num_contacts.setVisibility(View.VISIBLE);
        }
        else
            Toast.makeText(this, "Chargement propriétaire...", Toast.LENGTH_SHORT);



    }
    public void Numero_contacts (View view) {
        btn_voir_num_contacts.setVisibility(View.VISIBLE);
        btn_num_contacts.setVisibility(View.INVISIBLE);

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            picture.setImageBitmap(result);
        }
    }
    private class DownloadOwnerTask extends AsyncTask<String, Void, Owner> {

        protected Owner doInBackground(String... args) {
            return (Owner) (new LoadPost()).loadOwner(new Post(args[0],args[1],args[2]));

        }

        protected void onPostExecute (Owner o){
            mail = o.getMail();
            defaultPhone = o.getDefaultPhone();
        }

    }
}