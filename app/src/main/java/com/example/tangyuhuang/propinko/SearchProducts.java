package com.example.tangyuhuang.propinko;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by guillaumehoudayer on 17/12/15.
 */
public class SearchProducts extends ActionBarActivity {
    Button bSearch;
    EditText etSearch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchproducts);

        etSearch = (EditText) findViewById(R.id.etSearch);
        bSearch = (Button) findViewById(R.id.bSearch);

        bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SearchProducts.this, JeCherche.class);

                intent.putExtra("search", etSearch.getText().toString());


                startActivity(intent);

            }
        });
       
    }
}
