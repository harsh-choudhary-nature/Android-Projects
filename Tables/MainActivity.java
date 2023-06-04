package com.example.tables;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SeekBar seek_bar;
    TextView current_number;
    ListView table;
    public void setListView(int number){
        ArrayList<String> tables=new ArrayList<String>();
        for(int i=1;i<=10;i++){
            tables.add(number+" X "+i+" = "+number*i);
        }
        ArrayAdapter<String> array_adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,tables);
        table.setAdapter(array_adapter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        current_number=(TextView)findViewById(R.id.currentNumberId);
        table=(ListView)findViewById(R.id.listViewId);
        seek_bar=(SeekBar)findViewById(R.id.numberSeekId);
        seek_bar.setMax(19);
        seek_bar.setProgress(0);
        current_number.setText(Integer.toString(seek_bar.getProgress()+1));
        setListView(seek_bar.getProgress()+1);
        seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                current_number.setText(Integer.toString(i+1));
                setListView(i+1);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}