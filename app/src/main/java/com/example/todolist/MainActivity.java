package com.example.todolist;
import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText taskTitle, taskDescription, dueDate;
    private Spinner prioritySpinner, categorySpinner;
    private Button addTaskBtn;
    private ListView taskListView;
    private ArrayAdapter<String> taskAdapter;
    private ArrayList<String> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        taskTitle = findViewById(R.id.taskTitle);
        taskDescription = findViewById(R.id.taskDescription);
        prioritySpinner = findViewById(R.id.prioritySpinner);
        categorySpinner = findViewById(R.id.categorySpinner);
        dueDate = findViewById(R.id.dueDate);
        addTaskBtn = findViewById(R.id.addTaskBtn);
        taskListView = findViewById(R.id.taskListView);

        // Initialize task list and adapter
        taskList = new ArrayList<>();
        taskAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        taskListView.setAdapter(taskAdapter);

        // Set up spinners
        ArrayAdapter<CharSequence> priorityAdapter = ArrayAdapter.createFromResource(this,
                R.array.priority_levels, android.R.layout.simple_spinner_item);
        priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prioritySpinner.setAdapter(priorityAdapter);

        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.categories, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);

        // Set up button click listener
        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTask();
            }
        });

        // Set up list item click listener
        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Handle item click (e.g., mark as completed, update status)
                Toast.makeText(MainActivity.this, "Task clicked: " + taskList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addTask() {
        String title = taskTitle.getText().toString().trim();
        String description = taskDescription.getText().toString().trim();
        String priority = prioritySpinner.getSelectedItem().toString();
        String category = categorySpinner.getSelectedItem().toString();
        String date = dueDate.getText().toString().trim();
        String taskstatus="In progress";

        // Validate input
        if (title.isEmpty() || description.isEmpty() || date.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Build task string
        String task = "Title: " + title + "\nDescription: " + description +
                "\nPriority: " + priority + "\nCategory: " + category + "\nDue Date: " + date;
        MyDB myDB=new MyDB(MainActivity.this);
        myDB.insertData(title,description,priority,category,taskstatus,date);
        Intent i=new Intent(MainActivity.this, Result.class);
        i.putExtra("res",task);
        startActivity(i);

        // Add task to the list and update the adapter
        //taskList.add(task);
        taskAdapter.notifyDataSetChanged();

        // Clear input fields
        taskTitle.getText().clear();
        taskDescription.getText().clear();
        dueDate.getText().clear();

        //Toast.makeText(this, "Task added successfully", Toast.LENGTH_SHORT).show();
    }
}
