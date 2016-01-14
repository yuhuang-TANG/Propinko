package com.example.tangyuhuang.propinko.PatieJeCherche;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tangyuhuang.propinko.Model.Product;
import com.example.tangyuhuang.propinko.R;

import java.util.ArrayList;

//Adapter class extends with BaseAdapter and implements with OnClickListener
public class LazyImageLoadAdapter extends BaseAdapter implements OnClickListener{


    private final static String PIC_PROD_THUMBS = "http://www.lenkho.fr/propinko/Pictures/Products/thumb/";
    private Activity activity;
    private ArrayList<Product> products;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader;

    public LazyImageLoadAdapter(Activity a, ArrayList<Product> products) {
        activity = a;
        this.products = products;
        inflater = (LayoutInflater)activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Create ImageLoader object to download and show image in list
        // Call ImageLoader constructor to initialize FileCache
        imageLoader = new ImageLoader(activity.getApplicationContext());
    }

    public int getCount() {
        return products.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder{

        public TextView title;
        public TextView price;
        public TextView textWide;
        public ImageView image;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View vi=convertView;
        ViewHolder holder;

        if(convertView==null){

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.listview_row, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new ViewHolder();
            holder.title = (TextView) vi.findViewById(R.id.name);
            holder.price = (TextView) vi.findViewById(R.id.price);
            holder.image = (ImageView) vi.findViewById(R.id.image);

            /************  Set holder with LayoutInflater ************/
            vi.setTag( holder );
        }
        else
            holder=(ViewHolder)vi.getTag();


        holder.title.setText(products.get(position).getName());
        holder.price.setText(products.get(position).getPrice() + " euros");
        ImageView image = holder.image;

        //DisplayImage function from ImageLoader Class
        imageLoader.DisplayImage(PIC_PROD_THUMBS + products.get(position).getId() + products.get(position).getPhotoExtension(), image);

        /******** Set Item Click Listner for LayoutInflater for each row ***********/
        vi.setOnClickListener(new OnItemClickListener(position));
        return vi;
    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub

    }


    /********* Called when Item click in ListView ************/
    private class OnItemClickListener  implements OnClickListener{
        private int mPosition;

        OnItemClickListener(int position){
            mPosition = position;
        }

        @Override
        public void onClick(View arg0) {

            JeCherche sct = (JeCherche)activity;
            sct.onItemClick(mPosition);

        }
    }
}