package com.example.android.justjava;
import java.text.NumberFormat;
/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
    This is the increment method to increase the quantity ordered
     */
    public void increment(View view)
    {
        if(quantity<100)
        {
            quantity += 1;
            displayQuantity(quantity);
        }
        else
        {
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
        }
    }

    /*
    This is the decrement method to decrease the quantity ordered
     */
    public void decrement(View view)
    {
        if(quantity>1)
        {
            quantity -= 1;
            displayQuantity(quantity);
        }
        else
        {
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox)findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox)findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        EditText enterNameField = (EditText)findViewById(R.id.name_view);
        String nameOfCustomer = enterNameField.getText().toString();
        /* EditText enterNameField = (EditText)findViewById(R.id.name_view);
        Editable nameOfCustomer = enterNameField.getText(); */

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        displayMessage(createOrderSummary(price, hasWhippedCream, hasChocolate, nameOfCustomer));
    }


    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate)
    {
        //return quantity*5;
        int priceOfCoffee = 5;
        int priceOfWhippedCream = 0;
        int priceOfChocolate = 0;
        int totalPrice = 0;
        if(hasWhippedCream){
            priceOfWhippedCream = 1;
        }
        if(hasChocolate){
            priceOfChocolate = 2;
        }
        totalPrice = (priceOfCoffee + priceOfWhippedCream + priceOfChocolate)*quantity;
        return totalPrice;
    }


    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate, String nameOfCustomer)
    {
        String message = "Name: "+nameOfCustomer;
        message += "\nAdd Whipped Cream? "+hasWhippedCream;
        message += "\nAdd Chocolate? "+hasChocolate;
        message += "\nQuantity: " +quantity;
        message += "\nTotal: $" +price;
        message += "\nThank You!!!";
        return message;
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}