package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.math.RoundingMode;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.text.NumberFormat;
import java.math.BigDecimal;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


public class HelloController {
    private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percent = NumberFormat.getPercentInstance();
    private BigDecimal tipPercentageAmount = new BigDecimal(0.15);
    @FXML
    private TextField amountTextField;

    @FXML
    private TextField tipAmount;

    @FXML
    private Label tipPercentage;

    @FXML
    private TextField numberPeople;

    @FXML
    private Slider tipSlider;

    @FXML
    private TextField totalTextField;
    @FXML
    private void calculateButtonPressed(ActionEvent event) {
        try {
            BigDecimal amount = new BigDecimal(amountTextField.getText());
            BigDecimal tip = amount.multiply(tipPercentageAmount);
            BigDecimal number = new BigDecimal(numberPeople.getText());
            BigDecimal total = amount.add(tip).divide(number);
            tipAmount.setText(currency.format(tip));
            totalTextField.setText(currency.format(total));
        }
        catch (NumberFormatException ex) {
            amountTextField.setText("Enter amount");
            amountTextField.selectAll();
            amountTextField.requestFocus();
        }
    }
    public void initialize() {
        currency.setRoundingMode(RoundingMode.HALF_UP);
        tipSlider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
                        tipPercentageAmount =  BigDecimal.valueOf(newValue.intValue() / 100.0);
                        tipPercentage.setText(percent.format(tipPercentageAmount));
                    }
                }
        );
    }

    @FXML
    void calculateButtonPress(ActionEvent event) {


    }

}