package com.info.loca.locainfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.info.loca.locainfo.Dao.PessoaDao;
import com.info.loca.locainfo.Model.Pessoa;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    EditText login, senha;
    Button botao;
    String s;
    String lg, sh;
    Pessoa p;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (EditText) findViewById(R.id.loginId);
        senha = (EditText) findViewById(R.id.senhaId);
        botao = (Button) findViewById(R.id.botaoId);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lg = login.getText().toString();
                sh = senha.getText().toString();

                if(lg.equalsIgnoreCase("admin" )&& sh.equalsIgnoreCase("admin")) {

                    startActivity(new Intent(LoginActivity.this, CadastroActivity.class));
                    Toast.makeText(LoginActivity.this, "ADMINISTRADOR LOGADO COM SUCESSO!!", Toast.LENGTH_LONG).show();


                }else{

                    Bundle extra = getIntent().getExtras();
                    s = extra.getString("supli");
                    if (s.equals("s")) {
                        Intent i = new Intent(LoginActivity.this, SuplimentoActivity.class);
                        i.putExtra("var", lg);
                        i.putExtra("varr", sh);
                        startActivity(i);
                        finish();

                    } else {


                            Intent ii = new Intent(LoginActivity.this, ServicoActivity.class);
                            ii.putExtra("var", lg);
                            ii.putExtra("varr", sh);
                            startActivity(ii);
                        finish();



                    }
                }
             /*   else{
                    Toast.makeText(LoginActivity.this, "LOGIN OU SENHA INCORRETA", Toast.LENGTH_LONG).show();

                }*/

            }

        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
