package realexp.realexp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CheckList_Activity extends ActionBarActivity implements View.OnClickListener {

    ImageButton addToDo;
    private ArrayList<ToDo> whattheheck = new ArrayList<>();
    public static final String STATE_TODO = "state_quest";
    private ArrayList listDate;
    private ListView test;
    private CheckList mainList;
    //CheckList mainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist);

        addToDo = (ImageButton) findViewById(R.id.addToDoItem);
        addToDo.setOnClickListener(this);
        ToDo quest = new ToDo(1, "Quynh","Nguyen", "Hard", 1, 31, 2016, 0, 0, 3, 100, 200, 1);
        ToDo quest1 = new ToDo(1, "Q","N", "Hard", 1, 21, 2016, 0, 0, 3, 100, 200, 1);
        whattheheck.add(quest);
        whattheheck.add(quest1);

        test = (ListView) findViewById(R.id.listView);
        mainList = new CheckList(this, whattheheck);
        test.setAdapter(mainList);
        test.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = test.getItemAtPosition(position);
                ToDo selectedQuest = (ToDo) o;
                //Toast.makeText(this, "Selected: " + selectedQuest, Toast.LENGTH_SHORT).show();
            }
        });

        if (whattheheck.isEmpty()) {
            Toast.makeText(this, "ArrayList is empty", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "ArrayList is NOT empty", Toast.LENGTH_SHORT).show();
        }

    }

    protected void onRestart()
    {
        super.onRestart();

        if (whattheheck.isEmpty()) {
            Toast.makeText(this, "ArrayList is empty on RESTART", Toast.LENGTH_SHORT).show();
        }
        else  Toast.makeText(this, "ArrayList is not empty on RESTART", Toast.LENGTH_SHORT).show();



        test.setAdapter(mainList);
        test.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = test.getItemAtPosition(position);
                ToDo selectedQuest = (ToDo) o;
                //Toast.makeText(this, "Selected: " + selectedQuest, Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onStop()
    {
        super.onStop(); //always call the superclass method first

        //write some code that saves the quests onto the database.
    }

    /*Used only when setResult is used*/
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 10)
        {
            Toast.makeText(this, "RequestCode was 10", Toast.LENGTH_SHORT).show();
            if (resultCode == RESULT_OK) //Did the intent go through?
            {
                Toast.makeText(this, "onActivityResult", Toast.LENGTH_SHORT).show();
                ToDo task;
                Intent i;
                i = getIntent();

                task = i.getParcelableExtra("quest");
                mainList.addToDo(task);

                //ToDo quest = new ToDo(1, "Quynh","Nguyen", "Hard", 1, 31, 2016, 0, 0, 3, 100, 200, 1);
                //mainList.addToDo(quest);

            }
            if (resultCode == RESULT_CANCELED)
            {
                //write code if there is no result
                Toast.makeText(this, "CANCELED", Toast.LENGTH_SHORT).show();
            }
        }
    }
    //protected void onRestart(Bundle savedInstanceState)
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.addToDoItem:
                startActivityForResult(new Intent(this, createToDo_Activity.class), 10);
                break;

        }
    }

}
