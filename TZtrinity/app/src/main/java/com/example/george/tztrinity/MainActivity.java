package com.example.george.tztrinity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.george.tztrinity.model.Api;
import com.example.george.tztrinity.model.ApiClient;
import com.example.george.tztrinity.model.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.user_list);

        realm = Realm.getDefaultInstance();
        Api api = ApiClient.getInstance().getApi();

        Observable<List<User>> dbObservable = Observable.create(e -> getDBUsers());

        if (isNetworkAvailable()){
            api.getData()
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.computation())
                    .map(repos ->{
                        Realm realm = Realm.getDefaultInstance();
                        realm.executeTransaction(trRealm->trRealm.where(User.class).findAll().deleteAllFromRealm());
                        realm.executeTransaction(trRealm->trRealm.copyToRealm(repos));
                        return repos;
                    } )
                    .mergeWith(dbObservable)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response->setAdapterData(response));
        }else{
            setAdapterData(getDBUsers());
        }

    }

    private List<User> getDBUsers() {
        return realm.copyFromRealm(realm.where(User.class).findAll());
    }

    void setAdapterData(List<User> users) {
        UsersRecyclerAdapter adapter = new UsersRecyclerAdapter(getApplicationContext(), users);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }




    /*private void loadJSON() {
        Toast.makeText(this, "Запрос отправлен", Toast.LENGTH_SHORT).show();

        try {
            Call<List<User>> mCall = UsersLab.getBookService().getData();
            mCall.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(retrofit2.Call<List<User>> call, Response<List<User>> response) {

                    if (response.isSuccessful()) {

                        List<User> users = response.body();
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        adapter = new UsersRecyclerAdapter(getApplicationContext(), users);
                        recyclerView.setAdapter(adapter);
                    }

                }

                @Override
                public void onFailure(retrofit2.Call<List<User>> call, Throwable t) {

                    Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}

