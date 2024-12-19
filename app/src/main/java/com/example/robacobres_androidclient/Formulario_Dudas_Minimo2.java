package com.example.robacobres_androidclient;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.robacobres_androidclient.callbacks.QuestionCallback;
import com.example.robacobres_androidclient.models.Question;
import com.example.robacobres_androidclient.services.ServiceBBDD;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Formulario_Dudas_Minimo2 extends AppCompatActivity implements QuestionCallback {
    Button btnSendQuestion;
    EditText textQuestionTitle;
    EditText textQuestionMessage;

    ServiceBBDD serviceREST;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_formulario_dudas_minimo2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnSendQuestion = findViewById(R.id.btn_submitQuest);
        textQuestionTitle=findViewById(R.id.questionTitle);
        textQuestionMessage=findViewById(R.id.questionMessage);

        context=Formulario_Dudas_Minimo2.this;
        serviceREST = ServiceBBDD.getInstance(context);
    }

    public void onClickSubmitQuest(View V){
        String questTitle = textQuestionTitle.getText().toString().trim();
        String questMessage = textQuestionMessage.getText().toString().trim();

        if (questTitle.isEmpty()||questMessage.isEmpty()) {
            Toast.makeText(context, "Rellene su consulta porfavor", Toast.LENGTH_SHORT).show();
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());

        serviceREST.submitQuestion(questTitle,questMessage,currentDate,this);
    }

    public void onClickBotonRetroceder(View V){
        finish();
    }

    @Override
    public void onSubmittedOK(Question q) {
        Toast.makeText(context, "Question "+q.getTitle()+" Correctamente Recibida", Toast.LENGTH_SHORT).show();
        textQuestionTitle.setText("");
        textQuestionMessage.setText("");
    }

    @Override
    public void onSubmittedError() {
        Toast.makeText(context, "Error al enviar la duda", Toast.LENGTH_SHORT).show();
    }
}