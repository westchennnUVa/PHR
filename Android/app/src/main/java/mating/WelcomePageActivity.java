package mating;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.westchen.phr.R;

public class WelcomePageActivity extends Activity {

    private Button zhuyebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        zhuyebutton = (Button)findViewById(R.id.jinruyingyong);

        zhuyebutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomePageActivity.this, MainPage.class);
                startActivity(intent);


            }
        });
    }

}
