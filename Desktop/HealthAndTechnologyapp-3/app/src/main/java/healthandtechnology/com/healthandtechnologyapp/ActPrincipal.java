package healthandtechnology.com.healthandtechnologyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import healthandtechnology.com.healthandtechnologyapp.modelos.DAOCitas;
import healthandtechnology.com.healthandtechnologyapp.modelos.DAOPaciente;

public class ActPrincipal extends AppCompatActivity {
    ArrayList<Paciente>listaPaciente;
    ArrayList<Citas> listaCita;
    Button btnNoAdminInic;
    DAOPaciente daoPaciente = new DAOPaciente(this);
    DAOCitas daoCitas = new DAOCitas(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setTheme(R.style.Theme_HealthAndTechnologyapp);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_principal);
        daoPaciente.openBD();
        daoCitas.openBD();
        btnNoAdminInic=(Button) findViewById(R.id.btnIniciarSesion);
        registerForContextMenu(btnNoAdminInic);
        recuperarData();

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_contextual,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.adminInic: Intent intent = new Intent(this, ActIniciarSesionAdmin.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("dataPaciente",listaPaciente);
                bundle.putSerializable("dataCitas", listaCita);
                intent.putExtras(bundle);
                startActivity(intent); return true;
            default:return super.onContextItemSelected(item);
        }
    }
    public void iniciarSesion(View view){
        Intent intent = new Intent(this, ActIniciarSesion.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("dataPaciente",listaPaciente);
        bundle.putSerializable("dataCitas", listaCita);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void redRegistrar(View view){
        Intent intent = new Intent(this, ActNuevoPaciente.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("dataPaciente",listaPaciente);
        bundle.putSerializable("dataCitas", listaCita);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void redIniciarSesionAdmin(View view){
        Intent intent = new Intent(this, ActIniciarSesionAdmin.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("dataPaciente", listaPaciente);
        bundle.putSerializable("dataCitas", listaCita);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void recuperarData() {
        Bundle bundle= getIntent().getExtras();
        if (bundle==null){
            listaPaciente=daoPaciente.getPaciente();
            listaCita = daoCitas.getCitas();
        }else{
            listaPaciente= (ArrayList<Paciente>) bundle.getSerializable("dataPaciente");
            listaCita = (ArrayList<Citas>) bundle.getSerializable("dataCitas");
        }
    }
}