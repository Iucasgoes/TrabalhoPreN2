package com.zomercorporation.teste_lucas;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView nome;
    private TextView email;
    private ProgressDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadPessoa download = new DownloadPessoa();

        nome = (TextView)findViewById(R.id.textViewNome);
        email = (TextView)findViewById(R.id.textViewEmail);

        //Chama Async Task
        download.execute();
    }

    private class DownloadPessoa extends AsyncTask<Void, Void, Pessoa> {

        @Override
        protected void onPreExecute(){
            //inicia o dialog
            load = ProgressDialog.show(MainActivity.this,
                    "Aguarde ...", "Obtendo Informações...");
        }

        @Override
        protected Pessoa doInBackground(Void... params) {
            Conversor util = new Conversor();
            return util.getInformacao("https://reqres.in/api/users/2");
        }

        @Override
        protected void onPostExecute(Pessoa pessoa){
            nome.setText(pessoa.getNome());
            email.setText(pessoa.getEmail());
            load.dismiss(); //deleta o dialog
        }
    }
}
