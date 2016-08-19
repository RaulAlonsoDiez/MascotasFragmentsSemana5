package com.alonsodiez.mascotaspracticasemana5;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
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

public class ActivityContact extends AppCompatActivity {

    //Para poder probar esta sección, se deben poner los valores correctos de una cuenta real
    private String usuario = "usuario@prueba.com";
    private String password = "contrasena_prueba";
    private String host = "host.gmail.com(prueba)";

    private Session session = null;
    private ProgressDialog pDialog = null;
    private Context context = null;
    private TextInputEditText etNombre, etEmail, etAsunto, etMensaje;
    private String sNombre, sEmail, sAsunto, sMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Toolbar miActionBar = (Toolbar)findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = this;
        Button login = (Button) findViewById(R.id.btnMiBoton);
        etNombre = (TextInputEditText)findViewById(R.id.campo_nombre);
        etEmail = (TextInputEditText)findViewById(R.id.campo_email);
        etAsunto = (TextInputEditText)findViewById(R.id.campo_asunto);
        etMensaje = (TextInputEditText)findViewById(R.id.campo_mensaje);

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                sNombre = etNombre.getText().toString();
                sEmail = etEmail.getText().toString();
                sAsunto = etAsunto.getText().toString();
                sMensaje = etMensaje.getText().toString();
                enviarMensaje(sNombre, sEmail, sAsunto, sMensaje);
            }
        });
    }

    public void enviarMensaje(String nombre, String email, String asunto, String mensaje){

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.socketFactory.port", "587");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "587");

        session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, password);
            }
        });

        pDialog = ProgressDialog.show(context, "", "Enviando email...", true);

        RetreiveFeedTask task = new RetreiveFeedTask();
        task.execute();
    }

    class RetreiveFeedTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

            try {
                Message mensaje = new MimeMessage(session);

                mensaje.setFrom(new InternetAddress(usuario));
                mensaje.setRecipient(Message.RecipientType.TO, new InternetAddress(sEmail));
                mensaje.setSubject(sAsunto);
                mensaje.setContent(sMensaje, "text/html; charset=utf-8");

                Transport.send(mensaje);

            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(String aVoid) {
            pDialog.dismiss();
            etNombre.setText("");
            etEmail.setText("");
            etAsunto.setText("");
            etMensaje.setText("");

            Toast.makeText(getApplicationContext(), "Mensaje enviado", Toast.LENGTH_LONG).show();
        }
    }

    public void paginaFavoritos(View v){
        Intent intent = new Intent(this, MascotasFavoritas.class);
        startActivity(intent);
    }
}
