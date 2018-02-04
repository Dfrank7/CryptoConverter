package com.example.dfrank.cryptocoverter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by dfrank on 2/1/18.
 */

public class Conversion extends AppCompatActivity {
    EditText btcEditText, ethEditText, baseEditText;
    TextView shortcode, mShortcode, mBtc,mCountry,mEth;
    DecimalFormat decimalFormat;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversion);
        bindViews();
        setEditTextChangedListeners();
        decimalFormat = new DecimalFormat("#");
        decimalFormat.setMinimumIntegerDigits(1);
        decimalFormat.setMaximumFractionDigits(4);
    }
    TextWatcher base = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            ethEditText.removeTextChangedListener(eth);
            btcEditText.removeTextChangedListener(btc);
            String input = editable.toString();
            if (isAValidNumber(input)){
                btcEditText.setText(decimalFormat.format(baseToBtc(Double.parseDouble(input))));
                ethEditText.setText(decimalFormat.format(baseToEth(Double.parseDouble(input))));
            }
            btcEditText.addTextChangedListener(btc);
            ethEditText.addTextChangedListener(eth);

        }
    };
    TextWatcher btc = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            ethEditText.removeTextChangedListener(eth);
            baseEditText.removeTextChangedListener(base);
            String input = editable.toString();
            if (isAValidNumber(input)){
                baseEditText.setText(decimalFormat.format(btcToBase(Double.parseDouble(input))));
                ethEditText.setText(decimalFormat.format(btcToEth(Double.parseDouble(input))));
            }
            baseEditText.addTextChangedListener(base);
            ethEditText.addTextChangedListener(eth);

        }
    };
    TextWatcher eth = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            btcEditText.removeTextChangedListener(btc);
            baseEditText.removeTextChangedListener(base);
            String input = editable.toString();
            if (isAValidNumber(input)){
                btcEditText.setText(decimalFormat.format(ethToBtc(Double.parseDouble(input))));
                baseEditText.setText(decimalFormat.format(ethToBase(Double.parseDouble(input))));
            }
            btcEditText.addTextChangedListener(btc);
            baseEditText.addTextChangedListener(base);
        }
    };
    void setEditTextChangedListeners() {
        baseEditText.addTextChangedListener(base);
        btcEditText.addTextChangedListener(btc);
        ethEditText.addTextChangedListener(eth);
    }

    private void bindViews(){
        btcEditText = findViewById(R.id.btcText);
        ethEditText = findViewById(R.id.ethText);
        baseEditText = findViewById(R.id.baseEditText);
        shortcode = findViewById(R.id.shortcodeConversion);
        mShortcode =findViewById(R.id.shortcode);
        mCountry = findViewById(R.id.country);
        mBtc = findViewById(R.id.btcvalue);
        mEth = findViewById(R.id.ethvalue);
        mShortcode.setText(getIntent().getExtras().getString("shortcode"));
        mCountry.setText(getIntent().getExtras().getString("country"));
        mBtc.setText(String.valueOf(getIntent().getExtras().getDouble("btc")));
        mEth.setText(String.valueOf(getIntent().getExtras().getDouble("eth")));
        shortcode.setText(getIntent().getExtras().getString("shortcode"));
    }
    double btcToBase(double btcValue) {
        return getIntent().getExtras().getDouble("btc") * btcValue;

    }

    double ethToBase(double ethValue) {
        return getIntent().getExtras().getDouble("eth") * ethValue;
    }

    double baseToBtc(double baseCurrencyValue) {
        return baseCurrencyValue / getIntent().getExtras().getDouble("btc");
    }

    double baseToEth(double baseCurrencyValue) {
        return baseCurrencyValue / getIntent().getExtras().getDouble("eth");
    }

    double ethToBtc(double ethValue) {
        return ethValue * getIntent().getExtras().getDouble("eth") / getIntent().getExtras().getDouble("btc");
    }

    double btcToEth(double btcValue) {
        return btcValue * getIntent().getExtras().getDouble("btc") / getIntent().getExtras().getDouble("eth");
    }

    public static  boolean isAValidNumber(String number){
        return !TextUtils.isEmpty(number) && !number.equalsIgnoreCase(".");
    }
}
