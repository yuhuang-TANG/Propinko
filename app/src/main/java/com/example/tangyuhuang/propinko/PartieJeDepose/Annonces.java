package com.example.tangyuhuang.propinko.PartieJeDepose;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tangyuhuang.propinko.MyApplication;
import com.example.tangyuhuang.propinko.R;

import java.util.ArrayList;
import java.util.HashMap;

public class Annonces extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String ownerId;
    String mail;
    String password;
    String accountDate;
    String etat;
    int nombreDeAnnonces;

    // UI references.
    ListView lv;

    ArrayList<HashMap<String, Object>>listItem;

    TextView tvValueNombreDeAnnonces;
    Button btnDeposeAnnonces;

    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonces);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        ownerId = intent.getStringExtra("ownerId").toString();
        mail = intent.getStringExtra("mail").toString();
        password = intent.getStringExtra("password").toString();
        accountDate = intent.getStringExtra("accountDate").toString();
        etat = intent.getStringExtra("etat").toString();

        MyApplication app = (MyApplication) getApplicationContext();
        nombreDeAnnonces = app.getNombreDeAnnonces();
        //Toast.makeText(this, Integer.toString(nombreDeAnnonces), Toast.LENGTH_SHORT).show();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        btnDeposeAnnonces = (Button) findViewById(R.id.btnDeposeAnnonces);
        btnDeposeAnnonces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allerPageDepotAnnonce();
            }
        });

        /*
         * 新建一个类继承BaseAdapter，实现视图与数据的绑定
        */
        class MyAdapter extends BaseAdapter {

            private LayoutInflater mInflater;//得到一个LayoutInfalter对象用来导入布局

            /*构造函数*/
            public MyAdapter(Context context) {
                this.mInflater = LayoutInflater.from(context);
            }

            String titre = "Titre ";
            String description = "This is the description of annonce ";


            //TODO get the infos of the annonces

            /*添加一个得到数据的方法，方便使用*/
            private ArrayList<HashMap<String, Object>> getData(){

                ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
            /*为动态数组添加数据*/
                for(int i=1;i<=nombreDeAnnonces;i++)
                {
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("ItemTitle", "Annonce : "+ titre + i);
                    map.put("ItemDescription", "Description : "+ description + i);
                    listItem.add(map);
                }
                return listItem;

            }

            @Override
            public int getCount() {

                return getData().size();//返回数组的长度

            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                ViewHolder holder;
                //观察convertView随ListView滚动情况
                Log.v("MyListViewBase", "getView " + position + " " + convertView);
                if (convertView == null) {
                    convertView = mInflater.inflate(R.layout.list_annonces,
                            null);
                    holder = new ViewHolder();
                    /*得到各个控件的对象*/
                    holder.title = (TextView) convertView.findViewById(R.id.ItemTitle);
                    holder.Description = (TextView) convertView.findViewById(R.id.ItemDescription);
                    holder.bt = (Button) convertView.findViewById(R.id.ItemButton);
                    convertView.setTag(holder);//绑定ViewHolder对象
                }
                else{
                    holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
                }
            /*设置TextView显示的内容，即我们存放在动态数组中的数据*/
                holder.title.setText(getData().get(position).get("ItemTitle").toString());
                holder.Description.setText(getData().get(position).get("ItemDescription").toString());

            /*为Button添加点击事件*/
                holder.bt.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        allerPageAnnonceInfos();
                    }
                });

                return convertView;
            }

        }

        MyAdapter mAdapter = new MyAdapter(this);

        lv = (ListView) findViewById(R.id.lvAnnonces);
        lv.setAdapter(mAdapter);

    }



    /*存放控件*/
    public final class ViewHolder{
        public TextView title;
        public TextView Description;
        public Button   bt;
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
        getMenuInflater().inflate(R.menu.annonces, menu);
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
            closeOptionsMenu();
        } else if (id == R.id.nav_compte) {
            finish();
            Intent monIntent = new Intent(this, Compte.class);
            monIntent.putExtra("ownerId",ownerId);
            monIntent.putExtra("mail", mail);
            monIntent.putExtra("password", password);
            monIntent.putExtra("accountDate",accountDate);
            monIntent.putExtra("etat",etat);
            startActivity(monIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 动态设置ListView的高度
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        if(listView == null) return;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    private void allerPageConnexion(){
        finish();
        Intent monIntent = new Intent(this, Connexion.class);
        monIntent.putExtra("mail", mail);
        monIntent.putExtra("etat",etat);
        startActivity(monIntent);
    }

    private void allerPageDepotAnnonce(){
        finish();
        Intent monIntent = new Intent(this, DepotAnnonce.class);
        monIntent.putExtra("ownerId",ownerId);
        monIntent.putExtra("mail", mail);
        monIntent.putExtra("password", password);
        monIntent.putExtra("accountDate",accountDate);
        monIntent.putExtra("etat",etat);
        startActivity(monIntent);
    }

    private void allerPageAnnonceInfos(){
        finish();

        //int i = id;
        Intent monIntent = new Intent(this, AnnonceInfos.class);
        monIntent.putExtra("ownerId",ownerId);
        monIntent.putExtra("mail", mail);
        monIntent.putExtra("password", password);
        monIntent.putExtra("accountDate",accountDate);
        monIntent.putExtra("etat",etat);
        monIntent.putExtra("TitreDeAnnonce","Location Vidéoprojecteur acer h 5360 bd - Boissy Saint Leger");
        startActivity(monIntent);
        //TODO write the code for go to the page annonce with right informations

    }

    //stop the physical button 'back'
    public boolean onKeyDown(int keyCode,KeyEvent event){
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK:return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
