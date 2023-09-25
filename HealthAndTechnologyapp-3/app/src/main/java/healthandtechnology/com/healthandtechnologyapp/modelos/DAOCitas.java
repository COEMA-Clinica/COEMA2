package healthandtechnology.com.healthandtechnologyapp.modelos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import healthandtechnology.com.healthandtechnologyapp.Citas;
import healthandtechnology.com.healthandtechnologyapp.util.BDCitas;
import healthandtechnology.com.healthandtechnologyapp.util.ConstantesCitas;

public class DAOCitas {
    BDCitas dbCita;
    SQLiteDatabase database;
String pacActivo;
    public DAOCitas(Context context){
        dbCita = new BDCitas(context);
    }

    public void openBD()
    {
        database = dbCita.getWritableDatabase();
    }

    public void close(){
        dbCita.close();
        database.close();
    }


    public void registrarCita(Citas cita){

        try
        {

            ContentValues values = new ContentValues();
            values.put("nom", cita.getNom());
            values.put("doc", cita.getDoc());
            values.put("id_foto", cita.getIdFoto());
            values.put("clin", cita.getClin());
            values.put("esp", cita.getEsp());
            values.put("fecCit", cita.getFecCit());
            values.put("apePat", cita.getApePat());
            values.put("apeMat", cita.getApeMat());
            values.put("sexo", cita.getSexo());
            values.put("correo", cita.getCorreo());
            database.insert(ConstantesCitas.NOMBRETABLAC, null, values);
        }
        catch (Exception e)
        {


        }
    }

    public void editarCita(Citas cita){
        try
        {
            ContentValues values = new ContentValues();
            values.put("nom", cita.getNom());
            values.put("doc", cita.getDoc());
            values.put("id_foto", cita.getIdFoto());
            values.put("clin", cita.getClin());
            values.put("esp", cita.getEsp());
            values.put("fecCit", cita.getFecCit());
            values.put("apePat", cita.getApePat());
            values.put("apeMat", cita.getApeMat());
            values.put("sexo", cita.getSexo());
            database.update(ConstantesCitas.NOMBRETABLAC, values, "id = ?", new String[] {cita.getId() + ""});

        }
        catch (Exception e)
        {


        }
    }

    public void eliminarCita(int id){
        try
        {
            database.delete(ConstantesCitas.NOMBRETABLAC, "id=" + id, null);
        }
        catch (Exception e)
        {

        }
    }

    public ArrayList<Citas> getCitas(){
        ArrayList<Citas> listaCitas = new ArrayList<>();
        try
        {
            Cursor c = database.rawQuery("select * from " + ConstantesCitas.NOMBRETABLAC, null);
            while (c.moveToNext())
            {
                listaCitas.add(new Citas(c.getInt(0), c.getInt(1),c.getString(2), c.getString(3), c.getString(4), c.getString(5), c.getString(6), c.getString(7),c.getString(8),c.getString(9),c.getString(10)));
            }
        }
        catch (Exception e)
        {

        }

        return listaCitas;
    }
}
