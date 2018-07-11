package com.info.loca.locainfo;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.ImageView;
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

public class SuplimentoActivity extends Activity implements View.OnClickListener {
    String black2, ciano1, magento1,black1, yellow1, papel1, subject,c,d, carro;
    Session session = null;
    ProgressDialog pdialog = null;
    Context context = null;
    private ImageView sub;
    Spinner ciano, magento, yellow, black, papel,spinnerBlack;
    private PessoaDao pDao;
    private ArrayList<Pessoa> pessoaArrayList;
    private ListView listView;
   private Pessoa p;
  private  ArrayAdapter<Pessoa> arrayAdapter;
    int var, cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suplimento);
        context = this;


        Button login = (Button) findViewById(R.id.buttonSupId);
        spinnerBlack = (Spinner) findViewById(R.id.spinnerBlackId);
        magento= (Spinner) findViewById(R.id.magentoId);
        ciano= (Spinner) findViewById(R.id.cianoId);
        yellow= (Spinner) findViewById(R.id.yellowId);
        black= (Spinner) findViewById(R.id.blackId);
        papel = (Spinner) findViewById(R.id.papelId);
        listView = (ListView) findViewById(R.id.listViewIdSupli);


        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.ciano, android.R.layout.simple_spinner_item);
        ciano.setAdapter(adapter);

        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this,R.array.magento, android.R.layout.simple_spinner_item);
        magento.setAdapter(adapter1);

        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this,R.array.yellow, android.R.layout.simple_spinner_item);
        yellow.setAdapter(adapter2);

        ArrayAdapter adapter3 = ArrayAdapter.createFromResource(this,R.array.black, android.R.layout.simple_spinner_item);
        black.setAdapter(adapter3);

        ArrayAdapter adapter4 = ArrayAdapter.createFromResource(this,R.array.papel, android.R.layout.simple_spinner_item);
        papel.setAdapter(adapter4);
        ArrayAdapter adapter5 = ArrayAdapter.createFromResource(this,R.array.blackoutra, android.R.layout.simple_spinner_item);
        spinnerBlack.setAdapter(adapter5);

        login.setOnClickListener(this);

    }


    public void popularListaSuplli() {
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            c =extras.getString("var");
            d =extras.getString("varr");

        }
        p = new Pessoa();
        pDao = new PessoaDao(SuplimentoActivity.this);
        pessoaArrayList = pDao.selectAllPessoas(c,d);
        for(Pessoa p : pessoaArrayList){

            if(this.c.equals(p.getUsuario())&& this.d.equals(p.getSenha())){
            this.p.setNome(p.getNome());
            this.p.setEmail(p.getEmail());
            this.p.setCep(p.getCep());
            this.p.setRua(p.getRua());
            this.p.setBairro(p.getBairro());
            this.p.setCidade(p.getCidade());
            this.p.setEstado(p.getEstado());
            this.p.setNumero(p.getNumero());



            }else{
                startActivity(new Intent(SuplimentoActivity.this, LoginActivity.class));
            }
        }

        pDao.close();
        if (listView != null) {
            arrayAdapter = new ArrayAdapter<Pessoa>(SuplimentoActivity.this, android.R.layout.simple_list_item_1, pessoaArrayList);
            listView.setAdapter(arrayAdapter);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        popularListaSuplli();
    }

    @Override
    public void onClick(View v) {

        subject = "Suplimento";
        ciano1 = ciano.getSelectedItem().toString();
        magento1 = magento.getSelectedItem().toString();
        black1 = black.getSelectedItem().toString();
        yellow1 = yellow.getSelectedItem().toString();
        papel1 = papel.getSelectedItem().toString();
        black2 = spinnerBlack.getSelectedItem().toString();


        cont++;


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

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("testfrom354@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("vitormendes.souza@hotmail.com"));
                message.setSubject(subject);
                message.setContent(" <label style = 'Font-size: 30px;' 'color=' #D5D6C5;' 'font-family: Arial, Helvetica, sans-serif;'>LOCAINFORN</label>"+"<br/>"+

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
                                " <label style = 'Font-size: 20px;'>"+"PEDIDO "+cont+"</label>"+"<br/>"+
                                "<label style = 'Font-size: 18px;' 'Color: red;'>  Modelo - ES8473 MFP(Colorido)</label>"+
                                "<table style = 'border:1px solid black;' 'background: yellow;' >"+
                                "<tr style = 'background:#61F4F2 ;'>"+"<td>"+"CIANO"+"</td>"+"<td>"+ciano1+"</td>"+"</tr>"+
                                "<tr style = 'background: #FB7F81;'>"+"<td>"+"MAGENTO"+"</td>"+"<td>"+magento1+"</td>"+ "</tr>"+
                                "<tr  style = 'background:#FEFE06;' >"+"<td>"+"YELLOW"+"</td>"+"<td>"+yellow1+"</td>"+"</tr>"+
                                "<tr style = 'background: #D5D6C5;'>"+"<td>"+"BLACK"+"</td>"+"<td>"+black1+"</td>"+"</tr>"+"</table>"+

                                "</table>"+
                                " <label style = 'Font-size: 18px;' 'Color: red;'>  Modelo - MPS5502mb(P/B)</label>"+
                                "<table style = 'border:1px solid black;' 'background: yellow;' >"+
                                "<tr style = 'background: #D5D6C5;'>"+"<td>"+"BLACK"+"</td>"+"<td>"+black2+"</td>"+"</tr>"+ "</table>"+
                                "</table>"+
                                " <label style = 'Font-size: 20px;';'>Papel</label>"+
                                "<table style = 'border:1px solid black;' 'background: yellow;' >"+
                                "<tr style = 'background: #D5D6C5;'>"+"<td>"+"PAPEL"+"</td>"+"<td>"+papel1+"</td>"+"</tr>"+ "</table>"
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
            Toast.makeText(getApplicationContext(), "Mensagem enviada! Nossa equipe enviará o suprimento dentro de 24hrs", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SuplimentoActivity.this, SuplimentoActivity.class));
        }
    }

}
