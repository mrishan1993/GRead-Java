package com.example.gread.gread;import android.content.Context;import android.content.Intent;import android.graphics.Typeface;import android.os.Bundle;import android.support.v4.app.FragmentActivity;import android.view.View;import android.view.Window;import android.view.WindowManager;import android.widget.Button;import android.widget.EditText;import android.widget.Toast;import com.stormpath.sdk.Stormpath;import com.stormpath.sdk.StormpathCallback;import com.stormpath.sdk.StormpathConfiguration;import com.stormpath.sdk.models.StormpathError;//import com.facebook.CallbackManager;//import com.facebook.FacebookSdk;//import com.facebook.appevents.AppEventsLogger;//import com.facebook.login.widget.LoginButton;public class MainActivity extends FragmentActivity{    EditText userName, password;    protected void onCreate(Bundle savedInstanceState)    {        super.onCreate(savedInstanceState);        requestWindowFeature(Window.FEATURE_NO_TITLE);        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,                WindowManager.LayoutParams.FLAG_FULLSCREEN);        setContentView(R.layout.activity_main);        StormpathConfiguration stormpathConfiguration=new StormpathConfiguration.Builder()                .baseUrl("http://104.155.238.59:3000/")                .build();        Stormpath.init(this, stormpathConfiguration);        Button register = (Button) findViewById(R.id.register_button_login);        Typeface register_font= Typeface.createFromAsset(getAssets(), "fonts/Port.ttf");        register.setTypeface(register_font);        register.setOnClickListener(new View.OnClickListener()        {            public void onClick(View v)            {                Intent sub = new Intent(MainActivity.this, RegisterActivity.class);                startActivity(sub);                System.out.println("Done");                // Perform action on click            }        });}    public void validateLogin(View view)    {        userName = (EditText)findViewById(R.id.username_login);        password=(EditText)findViewById(R.id.password_login);        String uname=userName.getText().toString();        String pass=password.getText().toString();        Stormpath.login(uname, pass, new StormpathCallback<Void>() {            @Override            public void onSuccess(Void aVoid) {                Context context = getApplicationContext();                CharSequence charSequence = "Login Successful";                int duration = Toast.LENGTH_SHORT;                Toast.makeText(context, charSequence, duration).show();                Intent loginIntent = new Intent(MainActivity.this, HomeActivity.class);                startActivity(loginIntent);            }            @Override            public void onFailure(StormpathError error) {                Context context = getApplicationContext();                CharSequence charSequence = "Wrong Credentials!!";                int duration = Toast.LENGTH_SHORT;                Toast.makeText(context, charSequence, duration).show();                MainActivity.super.onRestart();            }        });    }}