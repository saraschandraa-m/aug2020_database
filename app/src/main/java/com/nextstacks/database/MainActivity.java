package com.nextstacks.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mEtStudentName;
    private EditText mEtStudentEmail;
    private EditText mEtStudentContact;
    private EditText mEtStudentYear;
    private EditText mEtStudentCourse;

    private DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEtStudentName = findViewById(R.id.et_student_name);
        mEtStudentContact = findViewById(R.id.et_student_phone);
        mEtStudentEmail = findViewById(R.id.et_student_email);
        mEtStudentYear = findViewById(R.id.et_student_year);
        mEtStudentCourse = findViewById(R.id.et_student_course);


        dbHelper = new DBHelper(MainActivity.this);
    }


    public void onEnterClicked(View view) {

        String studentName = mEtStudentName.getText().toString();
        String studentContact = mEtStudentContact.getText().toString();
        String studentEmail = mEtStudentEmail.getText().toString();
        String studentYear = mEtStudentYear.getText().toString();
        String studentCourse = mEtStudentCourse.getText().toString();

        StudentDetail newStudent = new StudentDetail();
//        newStudent.studentName = studentName;
        newStudent.setStudentName(studentName);
        newStudent.setStudentEmail(studentEmail);
        newStudent.setStudentContact(studentContact);
        newStudent.setStudentYear(studentYear);
        newStudent.setStudentCourse(studentCourse);


        mEtStudentName.setText("");
        mEtStudentCourse.setText("");
        mEtStudentEmail.setText("");
        mEtStudentYear.setText("");
        mEtStudentContact.setText("");

        dbHelper.insertDataToDatabase(dbHelper.getWritableDatabase(), newStudent);


    }

    public void onViewStudentClicked(View view) {
        startActivity(new Intent(MainActivity.this, ViewActivity.class));
    }
}