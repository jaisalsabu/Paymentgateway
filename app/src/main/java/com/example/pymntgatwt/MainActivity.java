package com.example.pymntgatwt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {
    EditText txt1;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1 = findViewById(R.id.editText);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
startPayment();
            }
        });
    }
        public void startPayment() {
            /**
             * Instantiate Checkout
             */
            Checkout checkout = new Checkout();

            /**
             * Set your logo here
             */
           // checkout.setImage(R.drawable.logo);

            /**
             * Reference to current activity
             */
            final Activity activity = this;

            /**
             * Pass your payment options to the Razorpay Checkout as a JSONObject
             */
            try {
                JSONObject options = new JSONObject();

                /**
                 * Merchant Name
                 * eg: ACME Corp || HasGeek etc.
                 */
                options.put("name", "Merchant Name");

                /**
                 * Description can be anything
                 * eg: Reference No. #123123 - This order number is passed by you for your internal reference. This is not the `razorpay_order_id`.
                 *     Invoice Payment
                 *     etc.
                 */
                options.put("description", "Reference No. #123456");
               // options.put("order_id", "order_9A33XWu170gUtm");
                options.put("currency", "INR");

                /**
                 * Amount is always passed in currency subunits
                 * Eg: "500" = INR 5.00
                 */
                options.put("amount", Integer.parseInt(txt1.getText().toString())*100);

                checkout.open(activity, options);
            } catch(Exception e) {
               // Log.e(TAG, "Error in starting Razorpay Checkout", e);
            }
        }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(MainActivity.this,"PAYMENT SUCCESFULL",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(MainActivity.this,"PAYMENT UNSUCCESFULL",Toast.LENGTH_LONG).show();

    }
}

