package com.info.loca.locainfo;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;





public class OrcamentoActivity extends Activity implements View.OnClickListener {
    SeekBar seekbar;
    CheckBox check;
    private TextView qtdCopias;
    Session session = null;
    ProgressDialog pdialog = null;
    Context context = null;
    private  EditText reciep,  msg;
    private  String rec, subject, textMessage, qtd, chec;
    private ImageView imagemview;
    String checc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orcamento);

        context = this;

        Button login = (Button) findViewById(R.id.btn_submit);
        reciep = (EditText) findViewById(R.id.et_to);
        msg = (EditText) findViewById(R.id.et_text);
        seekbar = (SeekBar) findViewById(R.id.seekBarId);
        qtdCopias = (TextView) findViewById(R.id.qtdCopiaId);
        check = (CheckBox) findViewById(R.id.nuncaId);
        qtdCopias = (TextView) findViewById(R.id.qtdCopiaId);
        imagemview = (ImageView) findViewById(R.id.imgId);
        login.setOnClickListener(this);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int progresss = progress;


               if (progresss == 0) {

                   qtdCopias.setText(" ");
                   imagemview.setImageResource(R.drawable.branco);
               }else if(progresss == 1){

                   qtdCopias.setText("1000 Cópias");
                   imagemview.setImageResource(R.drawable.a);

                }else if (progresss == 2){
                   qtdCopias.setText("2500 Cópias");
                 imagemview.setImageResource(R.drawable.b);

                }else if (progresss == 3){
                   qtdCopias.setText("5000 Cópias");
                   imagemview.setImageResource(R.drawable.c);
                }else if (progress == 4){
                   qtdCopias.setText("7500 Cópias");
                   imagemview.setImageResource(R.drawable.d);
                }else if(progress == 5){
                   qtdCopias.setText("10000 Cópias");
                   imagemview.setImageResource(R.drawable.e);
               }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(OrcamentoActivity.this, "", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(OrcamentoActivity.this, "", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        rec = reciep.getText().toString();
        subject =  "Orçamento";
        textMessage = msg.getText().toString();
        qtd = qtdCopias.getText().toString();

        Boolean chec;

        chec = check.isChecked();
        if(chec == false){

            checc = "Já tive esse tipo de serviço anteriormente.";
        }else{
            checc = "Nunca tive esse tipo de serviço anteriormente.";
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

            try{
                Message message = new MimeMessage(session);
                message.addRecipients(Message.RecipientType.CC,InternetAddress.parse("vitormendes.souza@hotmail.com"));
                message.setFrom(new InternetAddress("testfrom354@gmail.com"));
                message.setSubject(subject.toString());
                message.setContent("<label style = 'Font-size: 45px;' 'color=' #D5D6C5;' 'font-family: Arial, Helvetica, sans-serif;'>LOCAINFORN</label>"+"<br/>"+
                        " <label style = 'Font-size: 20px;'>INFORMAÇÕES DO CLIENTE</label>"+
                                "<table style = 'border:1px solid black;' 'background: yellow;' >"+
                                "<tr style = 'background:#D5D6C5 ;'>"+"<td>"+"E-MAIL: "+"</td>"+"<td>"+rec+"</td>"+"</tr>"+
                                "<tr>"+"<td>"+"QUANTIDADE DE CÓPIAS"+"</td>"+"<td>"+qtd+"</td>"+ "</tr>"+
                                "<tr  style = 'background:#D5D6C5 ;'>"+"<td>"+"STATUS"+"</td>"+"<td>"+checc+"</td>"+ "</tr>"+
                                "</table>", "text/html; charset=utf-8");
                Transport.send(message);
            } catch(MessagingException e) {
                e.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            pdialog.dismiss();
            reciep.setText("");
            msg.setText("");
            //sub.setText("");
            Toast.makeText(getApplicationContext(), "Mensagem enviada! Nossa equipe entrará em contato dentro de 24hrs", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(OrcamentoActivity.this, OrcamentoActivity.class));


        }


    }

}
