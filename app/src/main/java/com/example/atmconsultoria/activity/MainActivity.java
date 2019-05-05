package com.example.atmconsultoria.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.atmconsultoria.R;
import com.example.atmconsultoria.fragment.CostumersFragment;
import com.example.atmconsultoria.fragment.HomeFragment;
import com.example.atmconsultoria.fragment.ServicesFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    private FloatingActionButton fab;

    private HomeFragment homeFragment;
    private ServicesFragment servicesFragment;
    private CostumersFragment costumersFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configurarToolBar();
        configurarNavDrawer();
        configurarFAB();

        inicializarFragments();
        abrirFragment(homeFragment);

    }

    private void inicializarFragments() {
        homeFragment = new HomeFragment();
        servicesFragment = new ServicesFragment();
        costumersFragment = new CostumersFragment();
    }

    private void configurarNavDrawer() {
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void configurarFAB() {
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarEmail();
            }
        });
    }

    private void configurarToolBar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void enviarEmail() {
        Intent Email = new Intent(Intent.ACTION_SEND);
        Email.setType("text/email");
        Email.putExtra(Intent.EXTRA_EMAIL,
                new String[]{"atm.consultoria@email.com"});
        Email.putExtra(Intent.EXTRA_SUBJECT, "Email para ATM Consultoria");

        startActivity(Intent.createChooser(Email, "Envie-nos uma mensagem:"));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void fecharNavDrawer(){
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }


    private void abrirFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_conteiner, fragment);
        transaction.commit();
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            abrirFragment(homeFragment);
        } else if (id == R.id.nav_services) {
            abrirFragment(servicesFragment);
        } else if (id == R.id.nav_costumers) {
            abrirFragment(costumersFragment);
        } else if (id == R.id.nav_contact) {
            enviarEmail();
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(this, AboutActivity.class));
        }

        fecharNavDrawer();
        return true;
    }
}

