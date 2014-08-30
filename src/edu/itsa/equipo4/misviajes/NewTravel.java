package edu.itsa.equipo4.misviajes;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class NewTravel extends Activity {
	//Creacion del objeto auxiliar de SQLite
	public SQLiteHelper helper = new SQLiteHelper(this);
	private String action = "X";
	private Integer travelId = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_newtravel);
    	
    	//Creacion de variables en onCreate() 
    	final TextView nombre = (TextView)findViewById(R.id.editTextNombre);
    	final TextView origen = (TextView)findViewById(R.id.editTextOrigen);
    	final TextView destino = (TextView)findViewById(R.id.editTextDestino);
    	final TextView fechainicial = (TextView)findViewById(R.id.editTextFechaInicial);
    	final TextView fechafinal = (TextView)findViewById(R.id.editTextFechaFinal);
    	final TextView transporte = (TextView)findViewById(R.id.editTextTransporte);
    	final TextView costo = (TextView)findViewById(R.id.editTextCosto);
    	final Button btnaddtravel = (Button)findViewById(R.id.buttonAddTravel);
    	final Button btnupdtravel = (Button)findViewById(R.id.buttonUpdTravel);
    	   	
    	Bundle extras = getIntent().getExtras();
    	if (extras != null){
    		action = extras.getString("action").toString();
    		travelId = Integer.parseInt(extras.getString("travelId"));
    	}

    	//Condicion para permisos de usuario
    	if(action.equals("new")){
    		btnupdtravel.setVisibility(4);
    	}
    	
    	//Listeners
    	btnaddtravel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				helper.open();
				helper.addTravel(nombre.getText().toString(), origen.getText().toString(), destino.getText().toString(), fechainicial.getText().toString(), fechafinal.getText().toString(), transporte.getText().toString(), costo.getText().toString());
		    	helper.close();
				finish();
			}
		});
    	btnupdtravel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				helper.open();
				helper.updTravel(travelId, nombre.getText().toString(), origen.getText().toString(), destino.getText().toString(), fechainicial.getText().toString(), fechafinal.getText().toString(), transporte.getText().toString(), costo.getText().toString());
		    	helper.close();
				finish();
			}
		});
	}
	
	@Override
	protected void onStart(){
		super.onStart();
		
		final TextView nombre = (TextView)findViewById(R.id.editTextNombre);
    	final TextView origen = (TextView)findViewById(R.id.editTextOrigen);
    	final TextView destino = (TextView)findViewById(R.id.editTextDestino);
    	final TextView fechainicial = (TextView)findViewById(R.id.editTextFechaInicial);
    	final TextView fechafinal = (TextView)findViewById(R.id.editTextFechaFinal);
    	final TextView transporte = (TextView)findViewById(R.id.editTextTransporte);
    	final TextView costo = (TextView)findViewById(R.id.editTextCosto);
    	final Button btnaddtravel = (Button)findViewById(R.id.buttonAddTravel);
    	
		if (action.equals("upd")){
    		btnaddtravel.setVisibility(4);
    		helper.open();
    		Cursor ctrl = helper.getTravelInfo(travelId);
    		helper.close();
    		if (ctrl.isFirst()) {
                //Loop de lectura del viaje 
                do {
                     nombre.setText(ctrl.getString(1));
                     origen.setText(ctrl.getString(2));
                     destino.setText(ctrl.getString(3));
                     fechainicial.setText(ctrl.getString(4));
                     fechafinal.setText(ctrl.getString(5));
                     transporte.setText(ctrl.getString(6));
                     costo.setText(ctrl.getString(7));
                } while (ctrl.moveToNext());
    		}
    	}
	}
}