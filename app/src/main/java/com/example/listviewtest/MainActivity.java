package com.example.listviewtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {
    private static final int REQ_CODE = 2048;
    int score = 0;
    TextView scoreDisp;
    Button resetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreDisp = (TextView) findViewById(R.id.score);
        resetBtn = (Button) findViewById(R.id.resetBtn);

        //String[] topicsMAN = getResources().getStringArray(R.array.topics);
        final String[] topicsArray = getResources().getStringArray(R.array.QuizTopics);
        final String[] questions1 = getResources().getStringArray(R.array.Questions1);
        final String[] answers1 = getResources().getStringArray(R.array.Answers1);
        final String[] questions2 = getResources().getStringArray(R.array.Questions2);
        final String[] answers2 = getResources().getStringArray(R.array.Answers2);
        final String[] questions3 = getResources().getStringArray(R.array.Questions3);
        final String[] answers3 = getResources().getStringArray(R.array.Answers3);

        ListAdapter myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, topicsArray);
        ListView myListView = (ListView) findViewById(R.id.myList);
        myListView.setAdapter(myAdapter);

        //Create Second Activity
        final Intent forwardIntent = new Intent(MainActivity.this, QuestionsActivity.class);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                String word = parent.getItemAtPosition(position).toString();
                if(word.equals(topicsArray[0])){
                    //Pass question, answer, score to activity
                    forwardIntent.putExtra("Questions", questions1);
                    forwardIntent.putExtra("Answers",answers1);
                    //Start Activity
                    //so a result comes back
                    startActivityForResult(forwardIntent, REQ_CODE);

                }
                if(word.equals(topicsArray[1])){
                    //Pass question, answer, score tp activity
                    forwardIntent.putExtra("Questions", questions2);
                    forwardIntent.putExtra("Answers",answers2);
                    //Start Activity
                    //so a result comes back
                    startActivityForResult(forwardIntent, REQ_CODE);

                }

                if(word.equals(topicsArray[2])){
                    //Pass question, answer, score tp activity
                    forwardIntent.putExtra("Questions", questions3);
                    forwardIntent.putExtra("Answers",answers3);
                    //Start Activity
                    //so a result comes back
                    startActivityForResult(forwardIntent, REQ_CODE);
                }
            }
            });
    }

    public void resetAction(View view){
        score = 0;
        scoreDisp.setText(Integer.toString(score));
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent){

        String message;
        if(requestCode == REQ_CODE){
            long ret_ans = intent.getLongExtra("correct", -1);
            String userRtn = intent.getStringExtra("ans");
            if (ret_ans == 1){
                //add 1 to score
                score++;
                scoreDisp.setText(Integer.toString(score));
                //Toast with message correct!
                message = "Correct Answer!";
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
            if (ret_ans == 0){
                //No points added
                //Toast with message wrong!
                message = "Wrong Answer!";
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

            }
        }
    }
}
