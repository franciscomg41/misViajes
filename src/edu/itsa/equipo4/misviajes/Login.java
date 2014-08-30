package edu.itsa.equipo4.misviajes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity{
	//Creacion del objeto auxiliar de SQLite
	public SQLiteHelper helper = new SQLiteHelper(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_login);
    	
    	//Creacion de variables en onCreate()
    	final Button btnSignIn = (Button)findViewById(R.id.buttonSignIn);
    	final TextView txtpass = (TextView)findViewById(R.id.editTextPassword);
    	
    	//Listeners
    	btnSignIn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				helper.open();
				if (helper.getPassword("admin").toString().equals(txtpass.getText().toString())){
					helper.close();
					Intent newIntent = new Intent(v.getContext(), MyTravels.class);
					newIntent.putExtra("rol", helper.getRol("admin").toString());
					startActivityForResult(newIntent,0);
				}else{
					helper.close();
					Toast.makeText(Login.this, "Contraseña incorrecta", Toast.LENGTH_LONG).show();
					txtpass.setText(""); txtpass.hasFocus();
				}
			}
		});
	}
	
	@Override
	protected void onStart(){
		super.onStart();
		final TextView txtpass = (TextView)findViewById(R.id.editTextPassword);
		txtpass.setText("");
	}
}