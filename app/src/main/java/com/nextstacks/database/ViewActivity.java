package com.nextstacks.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    }

    @Override
    public void onDeleteClicked(StudentDetail studentDetail) {
        dbHelper.deleteDataFromDatabase(dbHelper.getWritableDatabase(), studentDetail);
        loadDataToDatabase();
    }
}