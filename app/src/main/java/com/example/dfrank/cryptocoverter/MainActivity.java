package com.example.dfrank.cryptocoverter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

/**
 * Created by dfrank on 1/30/18.
 */

public class MainActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    List<Btc> rates = new ArrayList<>();
    List<Btc> iniRates = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private FloatingActionButton fab;
    boolean checked[] = Data.getChecked();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        fab = findViewById(R.id.fab);
        initView();
//        setCards();
    }
//    private void setCards() {
//        if (isConnectionAvailable(this)) {
//            fab.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    addCards();
//                }
//            });
//        }else {
//            setSnackBar("Please On your Internet Connection");
//        }
//    }

    private void addCards(){
        String[] list = Data.shortcode;
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.dialogMessage)
                .setMultiChoiceItems(list, checked, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if (b){
                            checked[i] = true;
                            iniRates.add(rates.get(i));
                            recyclerViewAdapter.notifyItemInserted(recyclerViewAdapter.getItemCount());
                        }else {
                            checked[i] = false;
                            int index = iniRates.indexOf(rates.get(i));
                            iniRates.remove(index);
                            recyclerViewAdapter.notifyItemRemoved(index);
                        }
                    }

                }).setNeutralButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
        .create();
        alertDialog.show();
    }
    private void initView(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching Data");
        progressDialog.setCancelable(false);
        progressDialog.show();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));;
        recyclerView.smoothScrollToPosition(0);
        if (isConnectionAvailable(this)){
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addCards();
                }
            });
            getResponse();
        }else {
            progressDialog.dismiss();
            setSnackBar("Please On your Internet Connection");
        }
        //getResponse();
        //setCards();


    }
    private void getResponse(){
        Client client = new Client();
        Service service = client.getClient().create(Service.class);
        Call<JsonObject> call = service.getCryptoRate();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                fab.setVisibility(View.VISIBLE);
                progressDialog.dismiss();
                JsonObject jsonObject = response.body();
                for (int i=0;i<20;i++){
                    rates.add(new Btc(Data.country[i],Data.shortcode[i],
                            jsonObject.get("BTC").getAsJsonObject().get(Data.shortcode[i]).getAsDouble(),
                            jsonObject.get("ETH").getAsJsonObject().get(Data.shortcode[i]).getAsDouble()));
                }
                iniRates.add(rates.get(0));
                recyclerViewAdapter = new RecyclerViewAdapter(iniRates);
                recyclerView.setAdapter(recyclerViewAdapter);

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                progressDialog.dismiss();
                setSnackBar("No/Poor Internet");

            }
        });
    }
    private void setSnackBar(String message){
        Snackbar snackbar = Snackbar.make(findViewById(R.id.LinearLayout), message, Snackbar.LENGTH_INDEFINITE)
                .setAction("Refresh", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        initView();
                    }
                }); snackbar.show();

    }
    // checks if device is connected to the internet
    public static boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
//    public void refreshRecyclerViewItems() {
//        iniRates.clear();
//        if (iniRates.size() > 0) {
//            //we have new data
//           // rates.notifyDataSetChanged();
//        } else {
//            //we are offline the first time of opening the app  or we have empty data from the server
//        }
//    }
}
