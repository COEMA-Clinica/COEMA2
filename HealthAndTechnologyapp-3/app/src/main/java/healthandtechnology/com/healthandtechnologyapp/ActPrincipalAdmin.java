package healthandtechnology.com.healthandtechnologyapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ActPrincipalAdmin extends AppCompatActivity {
    ArrayList<Paciente>listaPaciente;
    ArrayList<Citas> listaCita;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_admin_principal);
        recuperarData();
    }

    public void listarCitasAdmin(View view) {
        Intent intent = new Intent(this, ActListarCitasAdmin.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("dataPaciente",listaPaciente);
        bundle.putSerializable("dataCitas", listaCita);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    public void listarPacientesAdmin(View view) {
        Intent intent = new Intent(this, ActListarPacientesAdmin.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("dataPaciente",listaPaciente);
        bundle.putSerializable("dataCitas", listaCita);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    public void salirPrincipal(View view) {
        Intent intent = new Intent(this, ActPrincipal.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("dataPaciente", listaPaciente);
        bundle.putSerializable("dataCitas", listaCita);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    public void nosotrosPrincipalAdmin(View view) {
        Intent intent = new Intent(this, ActInfoAdmin.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("dataPaciente",listaPaciente);
        bundle.putSerializable("dataCitas", listaCita);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal_admin,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Bundle bundle=new Bundle();
        bundle.putSerializable("dataPaciente",listaPaciente);
        bundle.putSerializable("dataCitas", listaCita);
        Intent intent;
        switch (item.getItemId()){
            case R.id.mnu01D:
                intent = new Intent(this, ActListarPacientesAdmin.class);

                intent.putExtras(bundle);
                startActivity(intent);
                return true;
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
    private void recuperarData() {
        Bundle bundle= getIntent().getExtras();
        if (bundle==null){
            listaPaciente=new ArrayList<>();
            listaCita = new ArrayList<>();
        }else{
            listaPaciente= (ArrayList<Paciente>) bundle.getSerializable("dataPaciente");
            listaCita = (ArrayList<Citas>) bundle.getSerializable("dataCitas");
        }
    }
}
