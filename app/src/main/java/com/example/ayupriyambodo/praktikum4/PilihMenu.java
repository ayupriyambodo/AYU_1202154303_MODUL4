package com.example.ayupriyambodo.praktikum4;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PilihMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_menu);
    }

    public void pilihNama(View view) {
        //untuk menyambungkan ke aktivitas yang dituju
        Intent intent = new Intent(getApplication(), NamaMahasiswa.class);
        startActivity(intent);
    }

    public void pilihGambar(View view) {
        Intent intenta = new Intent(getApplication(), PilihGambar.class);
        startActivity(intenta);
    }
}
