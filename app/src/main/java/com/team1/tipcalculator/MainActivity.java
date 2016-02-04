package com.team1.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.awt.font.TextAttribute;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();
    private double billAmount = 0.0;
    private double percent = 0.15;
    private TextView amountTextView;
    private TextView percentTextView;
    private TextView tipTextView;
    private TextView totalTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this, "checking", Toast.LENGTH_SHORT).show();
        //get references to programmatically manipulated views
        amountTextView = (TextView) findViewById(R.id.amountTextView);
        percentTextView = (TextView) findViewById(R.id.percentTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);
        tipTextView.setText(currencyFormat.format(0)); //set text to zero
        totalTextView.setText(currencyFormat.format(0)); // set text to zero

        //set amountEditText 's textWatcher
       EditText amountEditText = (EditText) findViewById(R.id.amountEditText);
       amountEditText.addTextChangedListener(amountEditTextWatcher);

        //set percentSeekBar's OnSeekBarChangedListener
        SeekBar percentSeekBar = (SeekBar) findViewById(R.id.percentSeekBar);
        percentSeekBar.setOnSeekBarChangeListener(seekBarListener);

    }
    private void calculate(){
        //format percent and display in percentView
        percentTextView.setText(percentFormat.format(percent));
        double tip = billAmount*percent;
        double total = billAmount + tip;

        //display the tip and the total formatted as currency
        tipTextView.setText(currencyFormat.format(tip));
        totalTextView.setText(currencyFormat.format(total));

    }
    private final OnSeekBarChangeListener seekBarListener = new OnSeekBarChangeListener(){
        //update percent then call calculate
        @Override
        public void onProgressChanged(SeekBar seekBar , int progress, boolean fromUser){
            percent = progress/100.0; // set percent based on progress
            calculate();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }

    };
    private final TextWatcher amountEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                billAmount = Double.parseDouble(s.toString())/100.0;
                amountTextView.setText(currencyFormat.format(billAmount));
            }catch(NumberFormatException e){
                amountTextView.setText("");
                billAmount = 0.0;
            }
            calculate();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
