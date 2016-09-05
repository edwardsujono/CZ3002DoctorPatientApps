package ntu.com.mylife.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.firebase.client.Firebase;

import ntu.com.mylife.R;
import ntu.com.mylife.common.data.UserType;
import ntu.com.mylife.common.service.DatabaseDaoUserImpl;
import ntu.com.mylife.controller.SignUpController;

public class SignUpView extends AppCompatActivity {

    //defined all the attribute here
    private EditText emailEditText,fullNameEditText,passwordEditText,userNameEditText,reenterPasswordEditText;
    private ImageButton patientRoleButton,doctorRoleButton,registerButton;
    private UserType.Type TYPE;
    private SignUpController signUpController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_view);
        Firebase.setAndroidContext(this);
        //instanstiate here
        //EDIT TEXT
        emailEditText = (EditText) findViewById(R.id.email_input_register);
        fullNameEditText = (EditText)  findViewById(R.id.fullname_input_register);
        passwordEditText = (EditText) findViewById(R.id.password_input_register);
        userNameEditText = (EditText) findViewById(R.id.username_input_register);
        reenterPasswordEditText = (EditText) findViewById(R.id.reenter_password_input_register);
        //BUTTON
        patientRoleButton = (ImageButton) findViewById(R.id.patient_role_button_register);
        doctorRoleButton = (ImageButton) findViewById(R.id.doctor_role_button_register);
        registerButton = (ImageButton) findViewById(R.id.registerButton);


        signUpController = new SignUpController(this);

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

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(signUpController.processSignUp(userNameEditText.getText().toString(),fullNameEditText.getText().toString(),emailEditText.getText().toString(),
                        passwordEditText.getText().toString(),reenterPasswordEditText.getText().toString(),TYPE)){
                    Intent intent = new Intent(SignUpView.this,MainPageView.class);
                    startActivity(intent);
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up_view, menu);
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
