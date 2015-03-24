package com.codepath.the_town_kitchen.activities;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RatingBar;

import com.codepath.the_town_kitchen.R;
import com.codepath.the_town_kitchen.models.Order;
import com.codepath.the_town_kitchen.models.OrderItem;

import java.util.List;

public class FeedbackActivity extends TheTownKitchenBaseActivity {
    RatingBar rbFeedback;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.dark_primary_red));
        
        rbFeedback = (RatingBar) findViewById(R.id.rbFeedback);
        rbFeedback.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {


            @Override
            public void onRatingChanged(RatingBar ratingBar, final float rating, boolean fromUser) {
                Order.getUsersLastOrder(new Order.IOrderReceivedListener() {
                    @Override
                    public void handle(Order order, List<OrderItem> orderItems) {
                        if (order != null) {
//                            Feedback feedback = new Feedback();
//                            feedback.setRating(Math.round(rating));
//                            feedback.put("parent", order);
//                            feedback.saveInBackground();

                            order.setFeedbackRating(Math.round(rating));
                            order.saveInBackground();

                            finishActivity();
                        }
                    }
                });




            }
        });
    }

    private void finishActivity(){
        this.finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_feedback, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
