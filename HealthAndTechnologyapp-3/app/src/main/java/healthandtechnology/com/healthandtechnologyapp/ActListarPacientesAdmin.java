package healthandtechnology.com.healthandtechnologyapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import healthandtechnology.com.healthandtechnologyapp.modelos.DAOPaciente;

public class ActListarPacientesAdmin extends AppCompatActivity implements IListarPacienteAdmin {
    ArrayList<Paciente>listaPacientes;
    ArrayList<Citas> listaCita;

    FragmentPacienteItemAdmin fragmentPacienteItemAdmin;
    FragmentPacienteListaAdmin fragmentPacienteListaAdmin;

    DAOPaciente daoPaciente = new DAOPaciente(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_listar_pacientes_admin);
        asignarReferencia();
        daoPaciente.openBD();
        recuperarData();
    }



    private void recuperarData() {
        Bundle bundle=getIntent().getExtras();
        if(bundle==null){
            Toast.makeText(this, "Falta registrar datos",
                    Toast.LENGTH_SHORT).show();
        }else{
            listaPacientes=daoPaciente.getPaciente();
            listaCita = (ArrayList<Citas>)bundle.getSerializable("dataCitas");
            Bundle listaPacienteFragment = new Bundle();
            listaPacienteFragment.putSerializable("dataPaciente", listaPacientes);
            listaPacienteFragment.putSerializable("dataCitas", listaCita);
            fragmentPacienteListaAdmin.setArguments(listaPacienteFragment);
            fragmentPacienteItemAdmin.setArguments(listaPacienteFragment);

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal_paciente_admin,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Bundle bundle=new Bundle();
        bundle.putSerializable("dataPaciente",listaPacientes);
        bundle.putSerializable("dataCitas", listaCita);
        Intent intent;
        switch (item.getItemId()){
            case R.id.mnu02D:
                intent =new Intent(this, ActListarCitasAdmin.class);

                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            case R.id.mnu03D:
                intent = new Intent(this, ActInfoAdmin.class);

                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            case R.id.mnu04D:
                SharedPreferences sharedPref = this.getSharedPreferences("correo_electronico", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();

                editor.clear();
                editor.apply();

                intent = new Intent(this, ActPrincipal.class);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    private void asignarReferencia() {
        fragmentPacienteItemAdmin = (FragmentPacienteItemAdmin) getSupportFragmentManager().findFragmentById(R.id.fgtItemPacAdm);
        fragmentPacienteListaAdmin = (FragmentPacienteListaAdmin) getSupportFragmentManager().findFragmentById(R.id.fgtListPacAdm);
    }

    public void regresarMenuAdminCitas(View view){
        Intent intent= new Intent(this,ActPrincipalAdmin.class);
        Bundle bundle= new Bundle();
        bundle.putSerializable("dataPaciente",listaPacientes);
        bundle.putSerializable("dataCitas",listaCita);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void mostrarListadoPaciente(Paciente paciente){
        fragmentPacienteItemAdmin.mostrarPacientesAdmin(paciente);
    }

    @Override
    public void capturarId(Paciente paciente) {
        fragmentPacienteItemAdmin.capturarId(paciente);


    }


}
