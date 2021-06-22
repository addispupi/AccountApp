package com.gashadigital.exapp2;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ServiceTask extends AsyncTask<String,Void,String> {
    Context ctx;
    ServiceTask(Context ctx){
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String url_reg = "http://192.168.1.3/git/AccountApp/mod_register.php";
        String url_log = "http://192.168.1.3/git/AccountApp/mod_login.php";
        String method = params[0];
        if(method.equals("Register")) {
            String first_name = params[1];
            String second_name = params[2];
            String email = params[3];
            String password = params[4];
            try {
                URL url= new URL(url_reg);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os= httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
                String Data=URLEncoder.encode("firstName", "UTF-8") + "=" + URLEncoder.encode(first_name, "UTF-8") + "&" +
                        URLEncoder.encode("lastName", "UTF-8") + "=" + URLEncoder.encode(second_name, "UTF-8") + "&" +
                        URLEncoder.encode("userEmail", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("userPass", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(Data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();

                InputStream is= httpURLConnection.getInputStream();
                is.close();
                return "Registration Success !";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
    }
}
