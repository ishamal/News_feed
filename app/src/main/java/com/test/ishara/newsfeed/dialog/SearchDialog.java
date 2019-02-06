package com.test.ishara.newsfeed.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.test.ishara.newsfeed.R;
import com.test.ishara.newsfeed.dto.SearchDto;
import com.test.ishara.newsfeed.interfaces.SearchCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class SearchDialog extends Dialog {

    SearchCallback searchCallback;
    EditText keyWord,fromDate,toDate;
    Button searchBtn,clearBtn;
    Spinner sortBy;
    Context context;
    final Calendar myCalendar = Calendar.getInstance();

    public SearchDialog(Context context) {
        super(context);
    }

    public SearchDialog(Context context, SearchCallback searchCallback) {
        super(context);
        this.searchCallback = searchCallback;
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.search_dialog);
        keyWord = (EditText) findViewById(R.id.key_word);
        fromDate = (EditText) findViewById(R.id.date_from);
        toDate = (EditText) findViewById(R.id.date_to);
        searchBtn = (Button) findViewById(R.id.search_btn);
        clearBtn = (Button) findViewById(R.id.cancel_btn);
        sortBy = (Spinner) findViewById(R.id.sort_by);

        final DatePickerDialog.OnDateSetListener dateFrom = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateFrom();
            }

        };

        final DatePickerDialog.OnDateSetListener dateTo = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateTo();
            }

        };

        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(context, dateFrom, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(context, dateTo, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        initSpinner();

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchCallback.onOkBtnClicked(createObj());
                dismiss();
            }
        });



    }

    private void updateFrom() {
        String myFormat = " YYYY-MM-DD"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        fromDate.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateTo() {
        String myFormat = " YYYY-MM-DD"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        toDate.setText(sdf.format(myCalendar.getTime()));
    }

    public SearchDto createObj(){
        SearchDto searchDto = new SearchDto();
        searchDto.setKeyWord(keyWord.getText().toString() + "");
        searchDto.setFromDate(fromDate.getText().toString() + "");
        searchDto.setToDate(toDate.getText().toString() + "");
        searchDto.setSortBy(sortBy.getSelectedItem().toString() + "");
        Log.d("search-dt0", searchDto.toString());
        return searchDto;
    }

    public void initSpinner(){
        List<String> categories = new ArrayList<String>();
        categories.add("Relevancy");
        categories.add("popularity");
        categories.add("publishedAt");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortBy.setAdapter(dataAdapter);
    }
}
