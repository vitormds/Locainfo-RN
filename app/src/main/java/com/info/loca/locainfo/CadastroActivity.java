package com.info.loca.locainfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.info.loca.locainfo.Dao.PessoaDao;
import com.info.loca.locainfo.Model.Pessoa;

public class CadastroActivity extends AppCompatActivity {
    private EditText editUsuario, editSenha, editNome,editEmail, editCep, editRua, editBairro, editCidade, editEstado, editNumero;
    private Button botaoSalvar;
    Pessoa p, altpessoa;
    PessoaDao pDao;
    long retornoDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Intent i = getIntent();
        altpessoa = (Pessoa) i.getSerializableExtra("pessoa-enviada");
        p = new Pessoa();
        pDao = new PessoaDao(CadastroActivity.this);
        editUsuario = (EditText) findViewById(R.id.inputUsuario);
        editSenha = (EditText) findViewById(R.id.inputSenha);
        editNome = (EditText) findViewById(R.id.inputNome);
        editEmail = (EditText) findViewById(R.id.inputEmail);
        editCep = (EditText) findViewById(R.id.inputCep);
        editRua = (EditText) findViewById(R.id.inputRua);
        editBairro = (EditText) findViewById(R.id.inputBairro);
        editCidade = (EditText) findViewById(R.id.inputCidade);
        editEstado = (EditText) findViewById(R.id.inputEstado);
        editNumero = (EditText) findViewById(R.id.inputNumero);
        botaoSalvar = (Button) findViewById(R.id.btnPessoa);

        if(altpessoa != null){
            botaoSalvar.setText("alterar ");
        }else{
            botaoSalvar.setText("salvar");
        }
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        p.setUsuario(editUsuario.getText().toString());
        p.setSenha(editSenha.getText().toString());
        p.setNome(editNome.getText().toString());
        p.setEmail(editEmail.getText().toString());
        p.setCep(editCep.getText().toString());
        p.setRua(editRua.getText().toString());
        p.setBairro(editBairro.getText().toString());
        p.setCidade(editCidade.getText().toString());
        p.setEstado(editEstado.getText().toString());
        p.setNumero(editNumero.getText().toString());

        if(botaoSalvar.getText().toString().equalsIgnoreCase("salvar")){
           retornoDB =  pDao.salvarPessoa(p);

           if(retornoDB == -1){

               alert("Erro ao cadastrar");

           }else{
               alert("Cadastrou!");// errro aq!!
            }
        }
        finish();



            }
        });
    }
    public void alert(String s){
        Toast.makeText(CadastroActivity.this, s, Toast.LENGTH_LONG).show(); //pode ter erro aqui
    }
}
