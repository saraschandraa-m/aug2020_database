package com.nextstacks.database;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity implements StudentDetailAdapter.StudentClickListener {

    private DBHelper dbHelper;
    private RecyclerView mRcStudentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        mRcStudentList = findViewById(R.id.rc_student_data);
/**
 * 1. Linear
 * 2. Grid
 * 3. Staggered Grid
 */
        mRcStudentList.setLayoutManager(new LinearLayoutManager(ViewActivity.this, RecyclerView.VERTICAL, false));

//        mRcStudentList.setLayoutManager(new GridLayoutManager(ViewActivity.this, 3));
//        mRcStudentList.setLayoutManager(new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL));


        dbHelper = new DBHelper(ViewActivity.this);

        loadDataToDatabase();
    }

    private void loadDataToDatabase() {
        ArrayList<StudentDetail> details = dbHelper.getDataFromDatabase(dbHelper.getWritableDatabase());


        StudentDetailAdapter adapter = new StudentDetailAdapter(ViewActivity.this, details);
        adapter.setListener(this);
        mRcStudentList.setAdapter(adapter);
    }

    @Override
    public void onUpdateClicked(StudentDetail studentDetail) {
        Intent updateIntent = new Intent(ViewActivity.this, MainActivity.class);
        updateIntent.putExtra("Student", studentDetail);
        updateIntent.putExtra("is_update", true);
        startActivityForResult(updateIntent, 1000);

    }

    @Override
    public void onDeleteClicked(StudentDetail studentDetail) {
        dbHelper.deleteDataFromDatabase(dbHelper.getWritableDatabase(), studentDetail);
        loadDataToDatabase();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == Activity.RESULT_OK) {
            loadDataToDatabase();
        }
    }
}