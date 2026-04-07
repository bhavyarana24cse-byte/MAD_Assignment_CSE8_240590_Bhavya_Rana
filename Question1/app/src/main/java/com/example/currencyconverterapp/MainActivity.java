package com.example.currencyconverterapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

    EditText amount;
    Spinner fromCurrency, toCurrency;
    Button convertBtn, lightBtn, darkBtn;
    TextView result;

    String[] currencies = {"INR", "USD", "EUR"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link all views (must match XML IDs exactly)
        amount = findViewById(R.id.amount);
        fromCurrency = findViewById(R.id.fromCurrency);
        toCurrency = findViewById(R.id.toCurrency);
        convertBtn = findViewById(R.id.convertBtn);
        lightBtn = findViewById(R.id.lightBtn);
        darkBtn = findViewById(R.id.darkBtn);
        result = findViewById(R.id.result);

        // Spinner setup
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                currencies
        );

        fromCurrency.setAdapter(adapter);
        toCurrency.setAdapter(adapter);

        // Convert button
        convertBtn.setOnClickListener(v -> {

            String input = amount.getText().toString();

            if (input.isEmpty()) {
                result.setText("Enter amount first");
                return;
            }

            double amt = Double.parseDouble(input);

            String from = fromCurrency.getSelectedItem().toString();
            String to = toCurrency.getSelectedItem().toString();

            double converted = convertCurrency(from, to, amt);

            result.setText("Converted: " + converted + " " + to);
        });

        // Light mode
        lightBtn.setOnClickListener(v ->
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        );

        // Dark mode
        darkBtn.setOnClickListener(v ->
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        );
    }

    private double convertCurrency(String from, String to, double amt) {

        if (from.equals(to)) return amt;

        if (from.equals("INR") && to.equals("USD")) return amt * 0.012;
        if (from.equals("USD") && to.equals("INR")) return amt * 83;

        if (from.equals("INR") && to.equals("EUR")) return amt * 0.011;
        if (from.equals("EUR") && to.equals("INR")) return amt * 90;

        if (from.equals("USD") && to.equals("EUR")) return amt * 0.92;
        if (from.equals("EUR") && to.equals("USD")) return amt * 1.08;

        return amt;
    }
}