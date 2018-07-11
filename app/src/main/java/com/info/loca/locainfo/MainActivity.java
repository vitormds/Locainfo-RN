package com.info.loca.locainfo;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.ImageView;



public class MainActivity extends Activity {

    private ImageView buttonEmpresa;
    private ImageView buttonSuplimento;
    private ImageView buttonNotebook;
    private ImageView buttonOrcamento;
    private ImageView buttonServico;

  //  private ImageView buttonEmail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonEmpresa = (ImageView) findViewById(R.id.empresaId);
    buttonSuplimento = (ImageView) findViewById(R.id.suplimentoId);
        buttonOrcamento = (ImageView) findViewById(R.id.orcamentoId);
        buttonServico = (ImageView) findViewById(R.id.et_to);

        buttonEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, EmpresaActivity.class);
                ActivityOptionsCompat activityOptionsCompat =  ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.mover_direita);
                ActivityCompat.startActivity(MainActivity.this, intent, activityOptionsCompat.toBundle());
            }
        });

        buttonSuplimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent( MainActivity.this, LoginActivity.class);
                ActivityOptionsCompat activityOptionsCompat =  ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.mover_direita);
                intent.putExtra("supli", "s");
                ActivityCompat.startActivity(MainActivity.this, intent, activityOptionsCompat.toBundle());


              /*  startActivity(new Intent(MainActivity.this, LoginActivity.class));

                Intent i = new Intent(MainActivity.this, LoginActivity.class);

                i.putExtra("supli", "s");
                startActivity(i);*/
            }
        });


        buttonOrcamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, OrcamentoActivity.class));
                Intent intent = new Intent( MainActivity.this, OrcamentoActivity.class);
                ActivityOptionsCompat activityOptionsCompat =  ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.mover_direita);
                ActivityCompat.startActivity(MainActivity.this, intent, activityOptionsCompat.toBundle());
            }
        });

        buttonServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( MainActivity.this, LoginActivity.class);
                ActivityOptionsCompat activityOptionsCompat =  ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.mover_direita);
                intent.putExtra("supli", "se");
                ActivityCompat.startActivity(MainActivity.this, intent, activityOptionsCompat.toBundle());
               /* startActivity(new Intent(MainActivity.this, LoginActivity.class));
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                i.putExtra("supli", "se");
                startActivity(i);*/
            }
        });















    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
