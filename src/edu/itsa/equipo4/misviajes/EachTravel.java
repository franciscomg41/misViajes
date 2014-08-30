package edu.itsa.equipo4.misviajes;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class EachTravel extends Activity{
	//Creacion del objeto auxiliar de SQLite
	private SQLiteHelper helper = new SQLiteHelper(this);
	private String rol = "X";
	private Integer travelId = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_eachtravel);
    	
    	//Creacion de variables en onCreate()
    	final Button btndeltravel = (Button)findViewById(R.id.buttonDelTravel);
    	final Button btnmodtravel = (Button)findViewById(R.id.buttonModTravel);
    	
    	Bundle extras = getIntent().getExtras();
    	if (extras != null){
    		rol = extras.getString("rol").toString();
    		travelId = Integer.parseInt(extras.getString("travelId"));
    	}

    	//Condicion para permisos de usuario
    	if (!rol.equals("0")){
    		//Desaparece los botones
    		btndeltravel.setVisibility(4);
    		btnmodtravel.setVisibility(4);
    	}

		//Listeners
		btndeltravel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				helper.open();
				helper.delTravel(travelId);
				helper.close();
				finish();
			}
		});
		
		btnmodtravel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent newIntent = new Intent(v.getContext(), NewTravel.class);
				newIntent.putExtra("travelId",travelId.toString());
				newIntent.putExtra("action","upd");
				startActivityForResult(newIntent,0);
			}
		});
	}
	@Override
	protected void onStart(){
		super.onStart();
		
		final TextView txtnombre = (TextView)findViewById(R.id.TextViewNombre);
    	final TextView txtorigen = (TextView)findViewById(R.id.TextViewOrigen);
    	final TextView txtdestino = (TextView)findViewById(R.id.TextViewDestino);
    	final TextView txtfechainicial = (TextView)findViewById(R.id.TextViewFechaInicial);
    	final TextView txtfechafinal = (TextView)findViewById(R.id.TextViewFechaFinal);
    	final TextView txttransporte = (TextView)findViewById(R.id.TextViewTransporte);
    	final TextView txtcosto = (TextView)findViewById(R.id.TextViewCosto);
    	
    	helper.open();
		Cursor ctrl = helper.getTravelInfo(travelId);
		helper.close();
		if (ctrl.isFirst()) {
            //Loop de lectura del viaje 
            do {
                 txtnombre.setText(ctrl.getString(1));
                 txtorigen.setText(ctrl.getString(2));
                 txtdestino.setText(ctrl.getString(3));
                 txtfechainicial.setText(ctrl.getString(4));
                 txtfechafinal.setText(ctrl.getString(5));
                 txttransporte.setText(ctrl.getString(6));
                 txtcosto.setText("$"+ctrl.getString(7));
            } while (ctrl.moveToNext());
		}
	}
}