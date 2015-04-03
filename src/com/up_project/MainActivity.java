package com.up_project;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	Button signIn;
	Button signUp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		signIn = (Button) findViewById(R.id.btnSignIn);
		signUp = (Button )findViewById(R.id.btnSignUp);
		
		signIn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SignIn();
			}
		});
		
		signUp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SignUp();
			}
		});
	}

	protected void SignUp() {
		// TODO Auto-generated method stub
		Intent signUp = new Intent(this, SignUpActivity.class);
		startActivity(signUp);
	}

	protected void SignIn() {
		// TODO Auto-generated method stub
		Intent signIn = new Intent(this, SignInActivity.class);
		startActivity(signIn);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
