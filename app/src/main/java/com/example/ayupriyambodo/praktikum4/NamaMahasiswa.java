package com.example.ayupriyambodo.praktikum4;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class NamaMahasiswa extends Activity {

    //penjelasan variabel
    private ListView mListView;
    private ProgressBar mProgressBar;
    private AddItemToListView mAddItemToListView;
    private Button mMulaiAsyncTask;

    //Array
    private String[] mUsers = {
            "Jono", "Maul", "Budi", "Mala", "Jajang", "Bolo", "Nifa",
            "Muhammad", "Ruli", "Nuri"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nama_mahasiswa);

        //definisi dari variabel
        mListView = (ListView) findViewById(R.id.listView);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        mMulaiAsyncTask = (Button) findViewById(R.id.btmulaiAsyncTask);

        //membuat progresbar
        mListView.setVisibility(View.GONE);

        //stup adapter
        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));

        //memulai asynctask
        mMulaiAsyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //proses adapter
                mAddItemToListView = new AddItemToListView();
                mAddItemToListView.execute();
            }
        });
    }

    private class AddItemToListView extends AsyncTask<Void, String, Void> {

        private ArrayAdapter<String > mAdapter;
        private int counter=1;
        ProgressDialog mProgressDialog = new ProgressDialog(NamaMahasiswa.this);

        @Override
        public void onPreExecute(){
            mAdapter = (ArrayAdapter<String>) mListView.getAdapter();

            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setTitle("Sedang Proses");
            mProgressDialog.setMessage("Menunggu....");
            mProgressDialog.setCancelable(false);
            mProgressDialog.setProgress(0);

            //jika terjadi cancel saat [roses
            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Proses Gagal", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    mAddItemToListView.cancel(true);
                    mProgressBar.setVisibility(View.VISIBLE);
                    dialog.dismiss();
                }
            });
            mProgressDialog.show();
        }
        //menjalankan proses asytask
        @Override
        protected Void doInBackground(Void... params) {
            for (String item : mUsers){
                publishProgress(item);

                try{
                    Thread.sleep(100);
                }catch (Exception e){
                    e.printStackTrace();
                }
                if (isCancelled()){
                    mAddItemToListView.cancel(true);
                }
            }
            return null;
        }

        //sesudah dijalankan
        @Override
        protected void onProgressUpdate (String... values){
            mAdapter.add(values[0]);

            Integer current_status = (int)((counter/(float)mUsers.length)*100);
            mProgressDialog.setProgress(current_status);

            mProgressDialog.setMessage(String.valueOf(current_status + "%"));
            counter++;
        }
        @Override
        protected void onPostExecute (Void aVoid){
            mProgressBar.setVisibility(View.GONE);

            mProgressDialog.dismiss();
            mListView.setVisibility(View.VISIBLE);
        }
    }
}
