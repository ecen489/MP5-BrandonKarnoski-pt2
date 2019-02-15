package com.example.listviewtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Random;

public class QuestionsActivity extends AppCompatActivity {

    TextView questionText;
    EditText userText;
    Button submitBtn;

    int random;
    long correct = 0;
    String[] answers;

    Random rand = new Random();
    Intent returnIntent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        questionText = (TextView) findViewById(R.id.question);
        userText = (EditText) findViewById(R.id.userInput);
        submitBtn = (Button) findViewById(R.id.submitBtn);

        Bundle intent = getIntent().getExtras();

        if (intent != null) {
            String[] question = intent.getStringArray("Questions");
            answers = intent.getStringArray("Answers");
            random = rand.nextInt(3);
            questionText.setText(question[random]);

        }
    }
        public void submitAnswer(View view){
            String userAnswer = userText.getText().toString();
            if (userAnswer.equals(answers[random])){
                //Answer Correct
                //Return positive flag
                correct = 1;
                returnIntent.putExtra("correct",correct);
                setResult(RESULT_OK,returnIntent);
                finish();
        } else if (!answers[random].equals(userAnswer)){
                //Answer NOT correct
                //Return negative flag
                correct = 0;
                returnIntent.putExtra("correct",correct);
                returnIntent.putExtra("ans",userAnswer);
                setResult(RESULT_OK,returnIntent);
                finish();
            }





    }

}
