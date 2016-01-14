package com.example.tangyuhuang.propinko.PatieJeCherche;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tangyuhuang.propinko.R;


public class Envoyer_email extends ActionBarActivity {
    EditText Edit_name;
    EditText Edit_email;
    EditText Edit_phone;
    EditText Edit_text;
    Context s_context;
    Boolean edit = true;
    CheckBox checkbox;
    //Button Envoyer;

    private String ownerMail = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envoyer_email);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                ownerMail = "";
            } else {
                ownerMail = extras.getString("mail");
            }
        } else {
            ownerMail = (String) savedInstanceState.getSerializable("mail");
        }

        s_context = this;
        Edit_name = (EditText) findViewById(R.id.Edit_name);
        Edit_email = (EditText) findViewById(R.id.Edit_email);
        Edit_phone = (EditText) findViewById(R.id.Edit_phone);
        Edit_text = (EditText) findViewById(R.id.Edit_text);
        checkbox = (CheckBox) findViewById(R.id.checkbox);
        //Envoyer = (Button) findViewById(R.id.Envoyer)

        }

    //buttons

    public void Envoyer (View view)
    {
        edit = false;
        String strEdit_name = Edit_name.getText().toString();
        final String strEdit_email = Edit_email.getText().toString();
        final String strEdit_text = Edit_text.getText().toString();

        if(TextUtils.isEmpty(strEdit_name)) {
            Edit_name.setError("Remplissez cet espace.");
            edit = true;
            }

            if(TextUtils.isEmpty(strEdit_email) || !strEdit_email.contains("@")) {
              Edit_email.setError("Remplissez cet espace avec un adresse email.");
            edit = true;
            }
            if(TextUtils.isEmpty(strEdit_text)) {
               Edit_text.setError("Remplissez cet espace.");
            edit = true;
            }
             if(!edit)

             {
                 if (checkbox.isChecked())
                 {
                     final Mail m = new Mail("propinko", "groupe_ping_30");

                     new AsyncTask<Void, Void, Void>() {
                         @Override
                         public Void doInBackground(Void... voids) {
                             try {
                                 String[] toArr = {strEdit_email};
                                 m.set_to(toArr);
                                 m.set_from(ownerMail);
                                 m.set_subject("This is an email sent using my Mail JavaMail wrapper from an Android device.");
                                 m.setBody(strEdit_text);
                                 m.send();
                                 if(m.send()) {
                                     Toast.makeText(Envoyer_email.this, "Email was sent successfully.", Toast.LENGTH_LONG).show();
                                 } else {
                                     Toast.makeText(Envoyer_email.this, "Email was not sent.", Toast.LENGTH_LONG).show();
                                 }
                             } catch (Exception e) {
                                 Log.e("SendEmail", e.getMessage(), e);
                             }
                             return null;
                         }
                     }.execute();


                 }

            // get dialog_modifier_trajet.xml view
            LayoutInflater layoutInflater = LayoutInflater.from(s_context);
            View promptView = layoutInflater.inflate(R.layout.dialog_email_envoye, null);
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(s_context);

            // set prompts.xml to be the layout file of the alertdialog builder
            alertDialogBuilder.setView(promptView);


            // setup a dialog window
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int id) {
                             { Intent intent = new Intent(Envoyer_email.this, Annonce.class);
                                 startActivity(intent);
                            }
                        }

                    });


            // create an alert dialog
            AlertDialog alertD = alertDialogBuilder.create();
            alertD.show();
        }
        }

    public void Retour (View view) {
        Intent intent = new Intent(Envoyer_email.this, Annonce.class);
        startActivity(intent);

    }
    public void Chercher (View view) {

        Intent intent = new Intent(Envoyer_email.this, JeCherche.class);
        startActivity(intent);

    }

}
