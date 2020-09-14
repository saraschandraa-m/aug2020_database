package com.nextstacks.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DynamicActivity extends AppCompatActivity {


    private LinearLayout mLinearLayout;
    private Button mAddListItem;
    private Button mAddData;

    private int itemID;

    ArrayList<ItemValue> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);

        mLinearLayout = findViewById(R.id.ll_dynamic);

        mAddListItem = findViewById(R.id.btn_add_new_list);

        mAddData = findViewById(R.id.btn_add);


        mAddListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addNewListItem();
            }
        });

        mAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
            }
        });

        items = new ArrayList<>();

    }


    /**
     * [
     * {
     * "itemID" :0,
     * "itemName" : "Stationary Pen",
     * "isChecked" : false
     * }
     * ]
     */


    private void addData() {
        if (items.size() > 0) {
//            for (ItemValue itemValue : items) {
//
//            }
//            for (int i=0; i<items.size(); i++){
//
//
//            }

            JSONArray itemsArray = new JSONArray();

            for (ItemValue itemValue : items) {
                try {
                    JSONObject itemObject = new JSONObject();
                    itemObject.put("itemID", itemValue.itemID);
                    itemObject.put("itemName", itemValue.itemName);
                    itemObject.put("isChecked", itemValue.isChecked);

                    itemsArray.put(itemObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            itemsArray.toString();
        }
    }


    private void addNewListItem() {

        mAddListItem.setEnabled(false);
        mAddListItem.setAlpha(0.5f);

        mAddData.setEnabled(false);
        mAddData.setAlpha(0.5f);

        itemID++;

        View newView = LayoutInflater.from(DynamicActivity.this).inflate(R.layout.cell_insert_data, null);
        CheckBox ch = newView.findViewById(R.id.ch_insert);
        final EditText mEtListItem = newView.findViewById(R.id.et_list_item);
        final ImageView mIvDone = newView.findViewById(R.id.iv_done);

        mIvDone.setVisibility(View.GONE);

        mEtListItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    mIvDone.setVisibility(View.VISIBLE);
                } else {
                    mIvDone.setVisibility(View.GONE);
                }
            }
        });

        mIvDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIvDone.setVisibility(View.GONE);
                mAddListItem.setEnabled(true);
                mAddData.setEnabled(true);
                mAddData.setAlpha(1.0f);
                mAddListItem.setAlpha(1.0f);

                ItemValue value = new ItemValue();
                value.itemID = itemID;
                value.itemName = mEtListItem.getText().toString();

                items.add(value);
            }
        });

        mLinearLayout.addView(newView);
    }


    class ItemValue {
        public int itemID;
        public String itemName;
        public boolean isChecked;
    }
}