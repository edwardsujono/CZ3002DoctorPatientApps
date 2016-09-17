package ntu.com.mylife.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.firebase.client.Firebase;

import ntu.com.mylife.R;
import ntu.com.mylife.common.entity.databaseentity.UserType;
import ntu.com.mylife.controller.SignInController;

public class SignInView extends AppCompatActivity {

    //declaration please defined here
    private ImageButton patientRoleButton,doctorRoleButton,signInButtonToEnter;
    private EditText editTextUserName,editTextPassword;
    private SignInController signInController;
    private UserType.Type TYPE = UserType.Type.PATIENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_view);
        Firebase.setAndroidContext(this);

        //instanstiate all the attribute here
        //Button
        patientRoleButton = (ImageButton) findViewById(R.id.patient_role_button);
        doctorRoleButton = (ImageButton) findViewById(R.id.doctor_role_button);
        signInButtonToEnter = (ImageButton) findViewById(R.id.sign_in_button_to_enter);
        //EditText
        editTextUserName = (EditText) findViewById(R.id.username_enter);
        editTextPassword = (EditText) findViewById(R.id.password_enter);


        signInController = new SignInController(this);

        //when the patientRoleButton is clicked
        patientRoleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patientRoleButton.setBackground(getResources().getDrawable(R.drawable.patient_role_button_selected));
                doctorRoleButton.setBackground(getResources().getDrawable(R.drawable.doctor_role_button));
                TYPE = UserType.Type.PATIENT;
            }
        });


        doctorRoleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patientRoleButton.setBackground(getResources().getDrawable(R.drawable.patient_role_button));
                doctorRoleButton.setBackground(getResources().getDrawable(R.drawable.doctor_role_button_selected));
                TYPE = UserType.Type.DOCTOR;
            }
        });


        signInButtonToEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(signInController.processSignIn(TYPE,editTextUserName.getText().toString(),editTextPassword.getText().toString())){
                    Intent intent = new Intent(SignInView.this,MainPageView.class);
                    startActivity(intent);
                }
            }
        });

    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_in_view, menu);
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
