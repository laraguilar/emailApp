package com.example.emailapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // pega o elemento de interface
        Button btnEnviar = findViewById(R.id.btnEnviar);

        // ação ao executar o elemento de interface
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText etEmail = findViewById(R.id.etEmail);// pega o conteudo no campo de email
                String email = etEmail.getText().toString(); // transforma o conteudo em string

                EditText etAssunto = findViewById(R.id.etAssunto);
                String assunto = etAssunto.getText().toString();

                EditText etMensagem = findViewById(R.id.etMensagem);
                String mensagem = etMensagem.getText().toString();

                // cria a intenção de ir pra outra tela
                Intent i = new Intent(Intent.ACTION_SENDTO); // intenção implicita
                i.setData(Uri.parse("mailto:")); // tipo de app do action send

                // envia os valores para a proxima tela
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                i.putExtra(Intent.EXTRA_SUBJECT, assunto);
                i.putExtra(Intent.EXTRA_TEXT, mensagem);

                // inicia a intenção, com tratamento de erro
                try{
                    startActivity(i);
                }
                catch (ActivityNotFoundException e){
                    Toast.makeText(MainActivity.this, "Não há nenhuma app de e-mail instalada.", Toast.LENGTH_SHORT);
                }

            }
        });
    }
}