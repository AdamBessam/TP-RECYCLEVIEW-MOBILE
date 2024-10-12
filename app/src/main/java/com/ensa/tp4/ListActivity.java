package com.ensa.tp4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;

import androidx.appcompat.widget.SearchView;

import com.ensa.tp4.adapter.PlayerAdater;
import com.ensa.tp4.beans.Player;
import com.ensa.tp4.service.PlayerService;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private static final String TAG = "ListActivity";
    private List<Player> players;
    private RecyclerView recycleView;
    private PlayerAdater playerAdater = null;
    private PlayerService service;
    Switch switcher;
    boolean nighMode;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("PLAYERS");
        players = new ArrayList<>();
        service = PlayerService.getInstance();
        init();
        recycleView = findViewById(R.id.recycle_view);
        playerAdater = new PlayerAdater(this, service.findAll());
        recycleView.setAdapter(playerAdater);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void init() {
        if (service.findAll().isEmpty()) {
            service.create(new Player("Yassine Bounou", "https://th.bing.com/th/id/R.c2818f19eca436455ef454407381f09a?rik=K3CpmRlkpVvKqA&pid=ImgRaw&r=0", 5));
            service.create(new Player("Achraf Hakimi", "https://farpost.co.za/wp-content/uploads/2022/12/Achraf-Hakimi-.jpg", 5));
            service.create(new Player("Romain Saïss", "https://th.bing.com/th/id/OIP.Q1fDKZfhuQeE9vkBcKP3FAHaHa?rs=1&pid=ImgDetMain", 4.5F));
            service.create(new Player("Nayef Aguerd", "https://biographygist.com/wp-content/uploads/2022/11/Nayef-Aguerd-1.jpg", 4.5F));
            service.create(new Player("Noussair Mazraoui", "https://fr.hespress.com/wp-content/uploads/2022/11/Noussair-Mazraoui-1.jpg", 5));
            service.create(new Player("Hakim Ziyech", "https://cdn.vox-cdn.com/thumbor/XVq-40hSh-pFS1Yn6vyjfcWBcP0=/0x0:6536x4358/1820x1213/filters:focal(2980x976:4024x2020)/cdn.vox-cdn.com/uploads/chorus_image/image/71641715/1441762340.0.jpg", 5));
            service.create(new Player("Sofiane Boufal", "https://th.bing.com/th/id/R.99e24630c5cf9fd071d091ce9d8bf13c?rik=WckVebkxF%2bRUmA&pid=ImgRaw&r=0", 4.5F));
            service.create(new Player("Azzedine Ounahi", "https://africafoot.com/wp-content/uploads/2022/12/Azzedine-Ounahi-5.jpg", 4.5F));
            service.create(new Player("Youssef En-Nesyri", "https://www.afrik-foot.com/wp-content/uploads/2022/12/fjp7cokx0aeq8wh.jpg", 4.5F));
            service.create(new Player("Abdelhamid Sabiri", "https://www.hollywoodsmagazine.com/wp-content/uploads/2022/11/89-3.jpg", 4.5F));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (playerAdater != null) {
                    playerAdater.getFilter().filter(newText);
                }
                return true;
            }
        });

        MenuItem shareItem = menu.findItem(R.id.action_share);
        shareItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                shareApp();
                return true;
            }
        });

        MenuItem switchItem = menu.findItem(R.id.action_switch);
        Switch switcher = switchItem.getActionView().findViewById(R.id.darkModeSwitch);
        sharedPreferences = getSharedPreferences("MODE", MODE_PRIVATE);
        nighMode = sharedPreferences.getBoolean("night", false);

        if (nighMode) {
            switcher.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            switcher.setChecked(false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        switcher.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                editor = sharedPreferences.edit();
                editor.putBoolean("night", true);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                editor = sharedPreferences.edit();
                editor.putBoolean("night", false);
            }
            editor.apply();
        });

        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        Drawable icon = searchItem.getIcon();
        if (icon != null) {
            int nightModeFlags = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
            if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
                icon.setTint(getResources().getColor(R.color.white));
            } else {
                icon.setTint(getResources().getColor(R.color.black));
            }
            searchItem.setIcon(icon);
        }

        return true;
    }

    private void shareApp() {
        String shareBody = "Découvrez cette application incroyable ! " +
                "https://play.google.com/store/apps/details?id=" + getPackageName();
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Partager l'application");
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }
}
