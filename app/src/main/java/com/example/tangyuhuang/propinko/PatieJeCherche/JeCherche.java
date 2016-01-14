package com.example.tangyuhuang.propinko.PatieJeCherche;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.tangyuhuang.propinko.Model.Product;
import com.example.tangyuhuang.propinko.R;

import java.util.ArrayList;


public class JeCherche extends ActionBarActivity {


    private final static String URL = "http://www.lenkho.fr/propinko/index.php";
    private final static String ACTION = "searchProducts";
    private String PARAMS;

    ArrayList<Product> products;
    ListView list;
    LazyImageLoadAdapter adapter;




        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_je_cherche);

            if (savedInstanceState == null) {
                Bundle extras = getIntent().getExtras();
                if(extras == null) {
                    PARAMS = "";
                } else {
                    PARAMS = extras.getString("search");
                }
            } else {
                PARAMS = (String) savedInstanceState.getSerializable("search");
            }

            list=(ListView)findViewById(R.id.list);


            // met à jour la listview
            new LoadPostTask(this).execute(new Post(URL, ACTION, PARAMS));

            Button b=(Button)findViewById(R.id.button1);
            b.setOnClickListener(listener);
        }

        @Override
        public void onDestroy()
        {
            // Remove adapter refference from list
            list.setAdapter(null);
            super.onDestroy();
        }

        public OnClickListener listener=new OnClickListener(){
            @Override
            public void onClick(View arg0) {

                //Refresh cache directory downloaded images
                adapter.imageLoader.clearCache();
                adapter.notifyDataSetChanged();
            }
        };


        public void onItemClick(int mPosition)
        {
            Intent intent = new Intent(JeCherche.this, Annonce.class);

            intent.putExtra("product", products.get(mPosition).toString());


            startActivity(intent);

        }


    private class LoadPostTask extends AsyncTask<Post,Void,ArrayList<Product>> {
        Activity activity;

        public LoadPostTask(Activity activity){
            this.activity = activity;
        }

        protected void onPreExecute(){
            //Toast.makeText(activity,"pre execution...", Toast.LENGTH_LONG).show();

        }


        protected ArrayList<Product> doInBackground(Post... args){


            return (new LoadPost()).loadPost(args[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Product> p){
            products = p;
            adapter = new LazyImageLoadAdapter(activity, p);
            list.setAdapter(adapter);
            //Toast.makeText(activity,"post executed !", Toast.LENGTH_LONG).show();
        }
    }

}
