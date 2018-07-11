package com.info.loca.locainfo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.info.loca.locainfo.Dao.PessoaDao;
import com.info.loca.locainfo.Model.Pessoa;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class ServicoActivity extends Activity implements View.OnClickListener {
    String modelomarca1;
    String codigoproblemaOKI;
    String problemacodigoRICOH;
    String c;
    String d;
    Session session = null;
    ProgressDialog pdialog = null;
    Context context = null;
    private String carro, subject;
    private TextView sub;
    private Button logi;
    Spinner  modelomarca, codigoproblema, problemacodigo ;
    ListView adaptador;
    PessoaDao pDao;
    ArrayAdapter<Pessoa> arrayAdapter;
    ArrayList<Pessoa> pessoaArrayList;
    String peguei,pegueii;
    Pessoa p;
    int var;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servico);
        context = this;
        // As findView
        logi = (Button) findViewById(R.id.buttonId);

        modelomarca = (Spinner) findViewById(R.id.modelo_marcaId);
        codigoproblema = (Spinner) findViewById(R.id.codigoproblemaId);
        problemacodigo = (Spinner) findViewById(R.id.codigoproblemaId);
        adaptador = (ListView) findViewById(R.id.listViewId);


        ArrayAdapter adapter5 = ArrayAdapter.createFromResource(this, R.array.modelo_marca, android.R.layout.simple_spinner_item);
        modelomarca.setAdapter(adapter5);

        AdapterView.OnItemSelectedListener escolha = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                modelomarca1 = modelomarca.getSelectedItem().toString();
                if (modelomarca1.equalsIgnoreCase("MPS5502mb(P/B)")) {
                    ArrayAdapter adapter6 = ArrayAdapter.createFromResource(ServicoActivity.this, R.array.codigo_problema, android.R.layout.simple_spinner_item);
                    codigoproblema.setAdapter(adapter6);
                 //   codigoproblemaOKI = codigoproblema.getSelectedItem().toString();


                    //Toast.makeText(ServicoActivity.this, codigoproblema1, Toast.LENGTH_LONG).show();

                } else {
                    ArrayAdapter adapter7 = ArrayAdapter.createFromResource(ServicoActivity.this, R.array.problema_codigo2, android.R.layout.simple_spinner_item);
                    problemacodigo.setAdapter(adapter7);
                //    problemacodigoRICOH = problemacodigo.getSelectedItem().toString();


                //    Toast.makeText(ServicoActivity.this, problemacodigo1, Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        };
       modelomarca.setOnItemSelectedListener(escolha);

        logi.setOnClickListener(this);


    }
    public void popularLista(){
        Bundle extra = getIntent().getExtras();
        if(extra != null) {
            pegueii = extra.getString("var");
            peguei = extra.getString("varr");
        }
        p = new Pessoa();
       pDao = new PessoaDao(ServicoActivity.this);
        pessoaArrayList =  pDao.selectAllPessoas(pegueii, peguei);
        for(Pessoa p : pessoaArrayList){
          //nm = pessoaArrayList.get(i);
            this.p.setId(p.getId());
            this.p.setNome(p.getNome());
            this.p.setEmail(p.getEmail());
            this.p.setCep(p.getCep());
            this.p.setRua(p.getRua());
            this.p.setBairro(p.getBairro());
            this.p.setCidade(p.getCidade());
            this.p.setEstado(p.getEstado());
            this.p.setNumero(p.getNumero());
        }
        pDao.close();
        if(adaptador != null) {
            arrayAdapter = new ArrayAdapter<Pessoa>(ServicoActivity.this, android.R.layout.simple_list_item_1, pessoaArrayList);
            adaptador.setAdapter(arrayAdapter);
        }

   }
   // public void testar(){
    //    Intent i = new Intent(ServicoActivity.this, LoginActivity.class);
   //     startActivity(i);
  //     Toast.makeText(ServicoActivity.this, "Login ou senha incorretos!!", Toast.LENGTH_LONG).show();
   // }
    @Override
    protected void onResume() {
        super.onResume();
        popularLista();

    }



    @Override
    public void onClick(View v) {
        subject = "Serviços";



        if (modelomarca1.equalsIgnoreCase("OKI-100")) {
            codigoproblemaOKI = codigoproblema.getSelectedItem().toString();
        } else {
            problemacodigoRICOH = problemacodigo.getSelectedItem().toString();
        }



        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("locainforn10@gmail.com", "vitor9855");
            }
        });

        pdialog = ProgressDialog.show(context, "", "Enviando Email...", true);

        RetreiveFeedTask task = new RetreiveFeedTask();
        task.execute();
    }

    class RetreiveFeedTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            if(codigoproblemaOKI == null){
                codigoproblemaOKI ="";
            }else {
                problemacodigoRICOH = "";
            }
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("testfrom354@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("vitormendes.souza@hotmail.com"));
                message.setSubject(subject);
                message.setContent(" <label style = 'Font-size: 45px;' 'color=' #D5D6C5;' 'font-family: Arial, Helvetica, sans-serif;'>LOCAINFORN</label>"+"<br/>"+

                                "<table style = 'border:1px solid black;' >"+
                                "<label style = 'Font-size: 20px;'>ENDEREÇO</label>"+
                                "<tr style = 'background: #D5D6C5;'>"+"<td>"+"NOME"+"</td>"+"<td>"+p.getNome()+"</td>"+"</tr>"+
                                "<tr>"+"<td>"+"E-MAIL"+"</td>"+"<td>"+p.getEmail()+"</td>"+ "</tr>"+
                                "<tr style = 'background: #D5D6C5;'>"+"<td>"+"BAIRRO"+"</td>"+"<td>"+p.getBairro()+"</td>"+"</tr>"+
                                "<tr>"+"<td>"+"RUA"+"</td>"+"<td>"+p.getRua()+"</td>"+"</tr>"+
                                "<tr style = 'background: #D5D6C5;'>"+"<td>"+"CEP"+"</td>"+"<td>"+ p.getCep()+"</td>"+"</tr>"+
                                "<tr>"+"<td>"+"CIDADE"+"</td>"+"<td>"+p.getCidade()+"</td>"+"</tr>"+
                                "<tr style = 'background:#D5D6C5;'>"+"<td>"+"ESTADO"+"</td>"+"<td>"+  p.getEstado()+"</td>"+"</tr>"+
                                "<tr>"+"<td>"+"NÚMERO"+"</td>"+"<td>"+p.getNumero()+"</td>"+"</tr>"+
                                "</table>"+
                                " <label style = 'Font-size: 20px;'>PROBLEMA</label>"+
                                "<table style = 'border:1px solid black;' 'background: yellow;' >"+
                                "<tr style = 'background:#D5D6C5 ;'>"+"<td>"+"MODELO"+"</td>"+"<td>"+modelomarca1+"</td>"+"</tr>"+
                                "<tr>"+"<td>"+"PROBLEMA"+"</td>"+"<td>"+codigoproblemaOKI+problemacodigoRICOH+"</td>"+ "</tr>"+
                                "</table>"
                        ,"text/html; charset=utf-8");
                Transport.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            pdialog.dismiss();
            //reciep.setText("");
          //  msg.setText("");
           // sub.setText("");
            Toast.makeText(getApplicationContext(), "Mensagem enviada! Será enviado um técnico dentro de 24hrs", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ServicoActivity.this, ServicoActivity.class));

        }
    }

}
