package com.bbayar.simpleweatherapp.activity;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bbayar.simpleweatherapp.R;
import com.bbayar.simpleweatherapp.adapter.CitiesAdapter;
import com.bbayar.simpleweatherapp.model.City;
import com.bbayar.simpleweatherapp.rest.ApiClient;
import com.bbayar.simpleweatherapp.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements AddItemDialogFragment.AddItemDialogCallbackInterface {

    public static final String API_KEY = "20506595c1c227a987bb75a5f0b26b1a";
    public static final String TAG = "ghjcnjnfr";
    public static final String KEY_LIST = "city_list";

    private List<City> mCityList = new ArrayList<>();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private MainFragment mMainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        int cityId = 524901;
        fetchData(cityId);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        showMainFragment();
    }

    private void showMainFragment() {
        Log.d(TAG, "showMainFragment(): ");

        mMainFragment = new MainFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(KEY_LIST, (ArrayList<? extends Parcelable>) mCityList);
        mMainFragment.setArguments(bundle);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, mMainFragment)
                .commit();
    }

    private void fetchData(int cityId) {
        Log.d(TAG, "fetchData(): ");

        ApiInterface apiService = ApiClient
                .getClient()
                .create(ApiInterface.class);

        Call<City> call = apiService.getCityWeather(cityId, API_KEY);
        call.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                City city = response.body();
                Log.d(TAG, "city: " + city);
                mCityList.add(city);
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {
                Log.d(TAG, "onFailure(): " + t.toString());
            }
        });
    }

    @OnClick(R.id.fab) void onItemClick(View view) {
        Snackbar.make(view, "asd", Snackbar.LENGTH_SHORT).show();
        AddItemDialogFragment fragment = AddItemDialogFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(fragment, "dialog")
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void addItem(int id) {
        fetchData(id);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /***
         * TODO:
         * Получить экзмепляр MainFragment
         * получить Адаптер и вызвать метод notify()
         */
        CitiesAdapter adapter = mMainFragment.getAdapter();
        adapter.notifyItemInserted(mCityList.size());
        adapter.notifyItemRangeChanged(mCityList.size(), mCityList.size());
    }
}
