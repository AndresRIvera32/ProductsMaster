package com.example.usuario.codigoqr2;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.codigoqr2.IntentIntegrator;
import com.example.usuario.codigoqr2.IntentResult;
import com.example.usuario.codigoqr2.R;


public class MyActivity extends AppCompatActivity {

    //declaramos nuestro boton
    private Button bt_scan;
    private AndroidRest peticionHttp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        //Boton en el XML
        bt_scan=(Button)findViewById(R.id.bt_scan);

        //AÃ±adimos Listener, al clickar...
        bt_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Lanzamos la activity del escaner
                IntentIntegrator.initiateScan(MyActivity.this);
            }
        });
    }
    public void buscarPedido(int i){
        TextView textoSalidaRespuesta = (TextView)findViewById(R.id.textoRespuesta);
//		textoSalidaRespuesta.setText(String.valueOf(id));
        int id = Integer.parseInt(textoSalidaRespuesta.getText().toString());
        peticionHttp = new AndroidRest();
        peticionHttp.setPedido(i);
        peticionHttp.setTienda(id);
       // peticionHttp.setTextViewResult(textoSalidaRespuesta);
        peticionHttp.execute();
    }

    //Marcamos lo que queremos que haga una vez haya leido el cÃ³digo
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case IntentIntegrator.REQUEST_CODE:
            {
                if (resultCode == RESULT_CANCELED){
                }
                else
                {
                    //Recogemos los datos   que nos envio el cÃ³digo Qr/codigo de barras
                    IntentResult scanResult = IntentIntegrator.parseActivityResult(
                            requestCode, resultCode, data);
                    String UPCScanned = scanResult.getContents();
                    int foo = Integer.parseInt(UPCScanned);
                  //metodo que me permite saber si un pedido pertenece a una tienda
                    buscarPedido(foo);
                    //cOMO ES SOLO UN EJEMPLO LO SACAREMOS POR PANTALLA.
                    Toast.makeText(getApplicationContext(),peticionHttp.getResp(),Toast.LENGTH_LONG
                    ).show();
                }
                break;
            }
        }
    }

}

