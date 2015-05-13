package com.example.usuario.codigoqr2;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * Created by Usuario on 12/05/2015.
 */
public class AndroidRest extends AsyncTask <Integer, Integer,  String> {
    String url = "http://productsmaster.herokuapp.com/rest/pedidofac/";
    private TextView RespuestaPeticion;
    private JSONObject object;
    private String resp;
    private int tienda;
    private int pedido;
    @Override
    protected String doInBackground(Integer... arg0) {
        BufferedReader in = null;
        String data = null;

        try {
            HttpClient client = new DefaultHttpClient();
            URI website;
            website = new URI(url+pedido+"/tiendafac/"+tienda);
            HttpGet request = new HttpGet();
            request.setURI(website);
            HttpResponse response = client.execute(request);

            response.getStatusLine().getStatusCode();
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String l = "";
            String nl = System.getProperty("line.separator");

            while ((l = in.readLine()) != null) {
                sb.append(l + nl);
            }

            in.close();
            data = sb.toString();

            return data;

        }catch (Exception e) {
            e.printStackTrace();
            return " ";
        }
    }

    @Override
    protected void onPreExecute() {
//lo que se hace antes de crar el hilo secundario. En este caso, no es
//necesario hacer nada.
    }


    protected void onPostExecute(String result) {
        //En este método se procesa el resultado del hilo secundario.
//En este caso, se debe actualizar la vista con el resultado del servicio Web.
//Tenga en cuenta que para esto deben tener la referencia de la Actividad
//para poder hacer el ‘findViewById‘
        JSONObject json;
        String pedidox = null;
        String tiendax = null;

        try{
            json = new JSONObject(result);
            pedidox = json.getString("Pedido");
            tiendax = json.getString("Tienda");
           // RespuestaPeticion.setText("el pedido"+pedido+"pertence a la tienda"+tienda);
            this.resp="el pedido"+pedidox+"pertence a la tienda"+tiendax;
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void setTextViewResult(TextView tv) {

        this.RespuestaPeticion = tv;
    }

    public void setTienda(int t) {

        this.tienda = t;
    }
    public void setPedido(int p) {

        this.pedido = p;
    }

    public TextView getRespuesta(){
        return this.RespuestaPeticion;
    }
    public String getResp(){
        return this.resp;
    }


}
