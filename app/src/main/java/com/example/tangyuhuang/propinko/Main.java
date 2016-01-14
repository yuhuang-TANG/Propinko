package com.example.tangyuhuang.propinko;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tangyuhuang.propinko.PartieJeDepose.Connexion;
import com.example.tangyuhuang.propinko.PatieJeCherche.SearchProducts;

public class Main extends AppCompatActivity implements View.OnClickListener{

    // UI references.
    Button btnChercheMain;
    Button btnDeposeMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChercheMain = (Button) findViewById(R.id.btnChercheMain);
        btnChercheMain.setOnClickListener(this);

        btnDeposeMain = (Button) findViewById(R.id.btnDeposeMain);
        btnDeposeMain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            //go to the page ConseilGeneral
            case R.id.btnChercheMain:
                Intent monIntent = new Intent(this, SearchProducts.class);
                startActivity(monIntent);
                break;
            //go to the page Connexion
            case R.id.btnDeposeMain:
                Intent intent  = new Intent(this, Connexion.class);
                startActivity(intent);
                break;

        }
    }

}
