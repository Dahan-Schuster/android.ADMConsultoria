package com.example.atmconsultoria.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.atmconsultoria.R;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutActivity extends AppCompatActivity {

    private View aboutPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        configurarContentView();

    }

    private void configurarContentView() {
        Element versionElement = new Element();
        versionElement.setTitle(getString(R.string.version));

        aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.logo)
                .setDescription(getString(R.string.text_about_1) + "\n\n" + getString(R.string.text_about_2))
                .addItem(versionElement)
                .addGroup(getString(R.string.about_page_contact))
                .addEmail(getString(R.string.atm_email), "Envie-nos um email")
                .addWebsite(getString(R.string.google_atm), "Acesse nosso site")
                .addGitHub("Dahan-Schuster", "Suporte o desenvolvedor no GitHub")
                .create();

        setContentView(aboutPage);
    }
}
