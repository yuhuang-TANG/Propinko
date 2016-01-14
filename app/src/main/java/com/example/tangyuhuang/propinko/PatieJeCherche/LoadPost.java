package com.example.tangyuhuang.propinko.PatieJeCherche;

import android.util.Log;

import com.example.tangyuhuang.propinko.Model.Owner;
import com.example.tangyuhuang.propinko.Model.Product;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by guillaumehoudayer on 16/12/15.
 */
public class LoadPost {

    public ArrayList<Product> loadPost(Post post){

        try {
            HttpURLConnection c = (HttpURLConnection) (new URL(post.getUrl())).openConnection();

            c.setRequestMethod("POST");
            c.setDoInput(true);
            c.setDoOutput(true);
            c.connect();

            c.getOutputStream().write(("action=" + post.getAction() + "&params=" + post.getParams()).getBytes());

            InputStream is = c.getInputStream();

            String r = IOUtils.toString(is);
            ArrayList<Product> results = new ArrayList<Product>();

            try{
                JSONArray o = new JSONArray(r);


                if(o != null){
                    for(int i = 0; i<o.length() ; i++){

                        Log.i("search", o.get(i).toString());
                        JSONObject p = new JSONObject(o.get(i).toString());

                        results.add(new Product(p.get("prod_id").toString(),p.get("prod_name").toString(),p.get("prod_category").toString(),p.get("prod_own_id").toString(),p.get("prod_visibility").toString(),p.get("prod_photoExtension").toString(),p.get("prod_description").toString(),p.get("prod_price").toString()));
                    }
                }

                Log.i("search", "json ok");


            } catch(Throwable t){
                Log.e("search", "Mauvais json");
            }

            return results;

        } catch(MalformedURLException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }

        return null;
    }
    public Owner loadOwner(Post post){

        try {
            HttpURLConnection c = (HttpURLConnection) (new URL(post.getUrl())).openConnection();

            c.setRequestMethod("POST");
            c.setDoInput(true);
            c.setDoOutput(true);
            c.connect();

            c.getOutputStream().write(("action=" + post.getAction() + "&params=" + post.getParams()).getBytes());

            InputStream is = c.getInputStream();

            String r = IOUtils.toString(is);
            Owner owner = null;

            try{
                JSONObject o = new JSONObject (r);
                owner = new Owner(o.get("id").toString(),o.get("lastname").toString(),o.get("mail").toString(),o.get("defaultPhone").toString(),o.get("password").toString(),o.get("firstname").toString(),o.get("description").toString(),o.get("streetNumber").toString(),o.get("address").toString(),o.get("city").toString(),o.get("postalCode").toString(), o.get("civility").toString(),o.get("accountDate").toString());

                Log.i("search", "json ok");



            } catch(Throwable t){
                Log.e("search", "Mauvais json");
            }

            return owner;

        } catch(MalformedURLException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }

        return null;
    }




}
