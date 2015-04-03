package com.up_project;

import java.util.HashMap;
import java.util.Map;

import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends Activity {

	Button signup;
	EditText email;
	EditText username;
	EditText password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		email = (EditText) findViewById(R.id.etEmail);
		username = (EditText) findViewById(R.id.etUserName);
		password = (EditText) findViewById(R.id.etPass);
		signup = (Button) findViewById(R.id.btnSignUp1);

		signup.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				signUp();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
		return true;
	}

	protected void signUp() {
		ConnectionConfiguration connConfig = new ConnectionConfiguration(
				"127.0.0.1", 5222);
		XMPPConnection connection = new XMPPTCPConnection(connConfig);

		AccountManager accountManager;
		connConfig.setSecurityMode(SecurityMode.disabled);

		try {
			connection.connect();
			accountManager = AccountManager.getInstance(connection);

			Map<String, String> attributes = new HashMap<String, String>();
			attributes.put("username", username.getText().toString());
			attributes.put("password", password.getText().toString());
			attributes.put("email", email.getText().toString());
			try {
				accountManager.createAccount(username.getText().toString(),
						password.getText().toString(), attributes);
			} catch (XMPPErrorException err) {
				err.printStackTrace();
				Toast.makeText(getApplicationContext(), "Fail to signup", Toast.LENGTH_SHORT).show();
			}
		} catch (Exception err) {
			err.printStackTrace();
			Toast.makeText(getApplicationContext(), "Can't connect to server", Toast.LENGTH_SHORT).show();
		}
	}

}
