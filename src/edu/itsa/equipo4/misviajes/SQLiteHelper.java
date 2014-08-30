package edu.itsa.equipo4.misviajes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper{
	
	/*Constructor*/
	public SQLiteHelper(Context ctx) {		
		super(ctx, "misviajes.sqlite", null, 1);				
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String qUsuario = "CREATE TABLE usuario("
				+"nombre VARCHAR(30) PRIMARY KEY NOT NULL,"
				+"password VARCHAR(32) NOT NULL,"
				+"rol VARCHAR(1) NOT NULL);";
		db.execSQL(qUsuario);
		String qViaje = "CREATE TABLE viaje("
				+"id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+"nombre VARCHAR(40) NOT NULL,"
				+"origen VARCHAR(80) NOT NULL,"
				+"destino VARCHAR(80) NOT NULL,"
			    +"fechainicial DATE NOT NULL,"
			    +"fechafinal DATE NOT NULL,"
			    +"transporte VARCHAR(30) NOT NULL,"
			    +"costo REAL NOT NULL);";
		db.execSQL(qViaje);
		db.execSQL("INSERT INTO usuario VALUES ('admin', '123456', '0')");
		db.execSQL("INSERT INTO usuario VALUES ('guest', 'guest', '1')");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int version1, int version2 ) {
		db.execSQL("DROP TABLE IF EXISTS usuario");
		db.execSQL("DROP TABLE IF EXISTS viaje");
		onCreate(db);
	}
	
	//Funciones para identificar al usuario
	public String getPassword(String user){
		String columnas[]={"password"};
		Cursor ctrl = this.getReadableDatabase().query("usuario",columnas,"nombre=?",new String[]{String.valueOf(user)},null,null,null);
		ctrl.moveToFirst();
		return ctrl.getString(ctrl.getColumnIndex("password"));
	}
	public String getRol(String user){
		String columnas[]={"rol"};
		Cursor ctrl = this.getReadableDatabase().query("usuario",columnas,"nombre=?",new String[]{String.valueOf(user)},null,null,null);
		ctrl.moveToFirst();
		return ctrl.getString(ctrl.getColumnIndex("rol"));
	}
	
	//Funciones de los viajes
	public int getNumOfTravels(){
		Cursor ctrl = this.getReadableDatabase().query("viaje",null,null,null,null,null,null);
		return ctrl.getCount();
	}
	public void addTravel(String nombre, String origen, String destino, String fechainicial, String fechafinal, String transporte, String costo){
		this.getWritableDatabase().execSQL("INSERT INTO viaje VALUES(NULL,'"
				+nombre.toString()+"','"
				+origen.toString()+"','"
				+destino.toString()+"','"
				+fechainicial.toString()+"','"
				+fechafinal.toString()+"','"
				+transporte.toString()+"',"
				+Double.parseDouble(costo)+")");
	}
	public void delTravel(Integer id){
		this.getWritableDatabase().execSQL("DELETE FROM viaje WHERE id="+id);
	}
	public Cursor getTravels(){
		Cursor ctrl = this.getReadableDatabase().query("viaje",null,null,null,null,null,null);
		ctrl.moveToFirst();
		return ctrl;		
	}
	public Cursor getTravelInfo(Integer id){
		Cursor ctrl = this.getReadableDatabase().query("viaje",null,"id=?",new String[]{String.valueOf(id)},null,null,null);
		ctrl.moveToFirst();
		return ctrl;		
	}
	public void updTravel(Integer id, String nombre, String origen, String destino, String fechainicial, String fechafinal, String transporte, String costo){
		ContentValues values = new ContentValues();
		values.put("nombre",nombre);
		values.put("origen",origen);
		values.put("destino",destino);
		values.put("fechainicial",fechainicial);
		values.put("fechafinal",fechafinal);
		values.put("transporte",transporte);
		values.put("costo",costo);
		this.getWritableDatabase().update("viaje", values, "id=?", new String[]{String.valueOf(id)});			
	}
	
	//Abrir y cerrar conexion
	public void open() {
		this.getWritableDatabase();	
	}
}