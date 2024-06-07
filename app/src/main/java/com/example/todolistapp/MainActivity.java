package com.example.todolistapp;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button add;
    AlertDialog dialog;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the views by their IDs
        add = findViewById(R.id.add);
        layout = findViewById(R.id.container);

        // Build the dialog for adding tasks
        buildDialog();

        // Set onClickListener for the add button to show the dialog
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }

    public void buildDialog() {
        // Create an AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Inflate the custom dialog view
        View view = getLayoutInflater().inflate(R.layout.dialog, null);

        // Find the EditText within the custom dialog view
        final EditText name = view.findViewById(R.id.nameEdit);

        // Set the view and buttons for the dialog
        builder.setView(view);
        builder.setTitle("Enter your task")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Add the task card with the entered name
                        addCard(name.getText().toString());
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing, just close the dialog
                    }
                });

        // Create the dialog from the builder
        dialog = builder.create();
    }

    private void addCard(String name) {
        // Inflate the custom card view
        final View view = getLayoutInflater().inflate(R.layout.card, null);

        // Find the TextView and Button within the card view
        TextView nameView = view.findViewById(R.id.name);
        Button delete = view.findViewById(R.id.delete);

        // Set the task name in the TextView
        nameView.setText(name);

        // Set onClickListener for the delete button to remove the card
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.removeView(view);
            }
        });

        // Add the card view to the container layout
        layout.addView(view);
    }
}
