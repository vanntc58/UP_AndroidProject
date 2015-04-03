package com.up_project;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends Activity {

	public static XMPPConnection connection;
	private boolean isSignIn = false;

	private Button signin;
	private EditText username = null;
	private EditText password = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);
		username = (EditText) findViewById(R.id.etUserName);
		password = (EditText) findViewById(R.id.etPass);
		signin = (Button) findViewById(R.id.btnSignIn1);
		signin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				connect();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_in, menu);
		return true;
	}
	
	private static class LoginMethod {

		// set up before connect
		public void setUp(String severHost, int port) {
			ConnectionConfiguration config = new ConnectionConfiguration(
					severHost, port);
			config.setSecurityMode(SecurityMode.disabled);
			connection = new XMPPTCPConnection(config);

		}

		// connect to server
		public void connect() throws Exception {
			try {
				connection.connect();
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}

		// login to server
		public void login(String userName, String password) throws Exception {
			try {
				connection.login(userName, password);
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
	}

	public boolean getStatusLogin() {
		return isSignIn;
	}

	public void connect() {
		LoginMethod loginMethod = new LoginMethod();
		// use my host like that
		loginMethod.setUp("127.0.0.1", 5222);
		boolean isConnected = false;
		try {
			loginMethod.connect();
			isConnected = true;
		} catch (Exception exp) {
			exp.printStackTrace();
			Toast.makeText(getApplicationContext(), "Can't connect to server",
					Toast.LENGTH_SHORT).show();
		}
		if (isConnected)
			try {
				loginMethod.login(username.getText().toString(), password
						.getText().toString());
				isSignIn = true;
				Toast.makeText(getApplicationContext(),
						"Login successful!", Toast.LENGTH_SHORT).show();
			} catch (Exception exp) {
				exp.printStackTrace();
				Toast.makeText(getApplicationContext(),
						"Fail to login!", Toast.LENGTH_SHORT).show();
			}

	}


}

