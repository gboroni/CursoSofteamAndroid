package com.cursoandroid.login;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    private EditText login;
    private EditText senha;
    private Button entrar;
    private Switch salvar_login;

    public static final String PREFERENCES = "preferences";
    public static final String LOGIN_PREFERENCES = "login";

    SharedPreferences settings;
    SharedPreferences.Editor prefEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Usuario user = new Usuario("123456","guilherme");

        login = (EditText) findViewById(R.id.login);
        senha = (EditText) findViewById(R.id.senha);
        entrar = (Button) findViewById(R.id.entrar);
        salvar_login = (Switch) findViewById(R.id.salvar_login);

        if (!getSharedLogin().trim().equals("")){
            login.setText(getSharedLogin());
            salvar_login.setChecked(true);
        }


        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new LoginAsyncTask(MainActivity.this).execute("http://10.27.168.168/uploads/login.php?usuario="
//                        +login.getText()+"&senha="+senha.getText()+"");

                LoginAsyncTask login_async = new LoginAsyncTask(MainActivity.this);
                login_async.execute("http://10.27.168.168/uploads/login.php?usuario="
                        +login.getText()+"&senha="+senha.getText()+"");
            }
        });


    }

                //VARIAVEIS
//    public static final String PREFERENCES = "preferences";
//    public static final String LOGIN_PREFERENCES = "login";
//
//    SharedPreferences settings;
//    SharedPreferences.Editor prefEditor;
    private void setTextLoginPreferences(String value){
        //ABRE A PREFERENCIA CRIADA
        settings = getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        //ACAO DE EDICAO
        prefEditor = settings.edit();
        //INFORMA A ACAO DE SALVAR O VALOR RECEBIDO DE PARAMETRO NA CHAVE CRIADA
        prefEditor.putString(LOGIN_PREFERENCES, value);
        //SALVA AS ALTERACOES
        prefEditor.commit();
    }

    private String getSharedLogin(){
        //ABRE A PREFERENCIA CRIADA
        settings = getSharedPreferences(PREFERENCES,MODE_PRIVATE);
        if (settings.contains(LOGIN_PREFERENCES))
            return settings.getString(LOGIN_PREFERENCES,""); //em vazio default value
        else
            return "";
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
}
