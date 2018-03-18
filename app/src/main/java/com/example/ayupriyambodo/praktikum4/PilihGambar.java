package com.example.ayupriyambodo.praktikum4;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Binder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class PilihGambar extends Activity {

    private EditText mURLgambar;
    private ImageView mGambar;
    private Button mCari;
    private ProgressDialog mMenunggu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_gambar);

        mURLgambar = (EditText) findViewById(R.id.URL);
        mGambar = (ImageView) findViewById(R.id.gambar);
        mCari = (Button) findViewById(R.id.cari);
    }

    public void cari(View view) {
        loadImageInit();
    }

    private void loadImageInit() {
        String ImgUrl = mURLgambar.getText().toString();
        new loadImage().execute(ImgUrl);
    }
    private class loadImage extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();

            //Membuat Dialog
            mMenunggu = new ProgressDialog(PilihGambar.this);

            //judul dialog
            mMenunggu.setTitle("Mengambil Gambar");

            //Message dialog
            mMenunggu.setMessage("Menunggu...");

            //Menempilkan dialog
            mMenunggu.show();
        }
        //proses asynctask berjalan
        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap a = null;
            try {
                URL x = new URL(params[0]); //medownload gambar
                a = BitmapFactory.decodeStream((InputStream) x.getContent()); //konversi gabar
            } catch (Exception e){
                e.printStackTrace();
            }
            return a;
        }
        //asynctack sudah dijalankan
        @Override
        protected void onPostExecute (Bitmap a){
            super.onPostExecute(a);
            mGambar.setImageBitmap(a); //menampung gambar yang ditampilkan
            mMenunggu.dismiss(); //mengjilangkan progress dialog
        }
    }
}
