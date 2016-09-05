package ntu.com.mylife.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import ntu.com.mylife.R;
import ntu.com.mylife.view.SignInView;
import ntu.com.mylife.view.SignUpView;

public class AccountManagement extends AppCompatActivity {

    //defined all the declarations here
    //define button
    private ImageButton signInButton;
    private TextView registerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_management);


        signInButton = (ImageButton) findViewById(R.id.button_sign_in);
        registerButton = (TextView) findViewById(R.id.register_text);


        signInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnSignIn();
            }
        });

        registerButton.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                clickOnRegister();
            }
        });

    }


    //Click Register Button
    public void clickOnRegister(){
        startActivity(new Intent(AccountManagement.this, SignUpView.class));
    }

    //Click Sign In Button
    public void clickOnSignIn(){
       startActivity(new Intent(AccountManagement.this, SignInView.class));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_account_management, menu);
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
