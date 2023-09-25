package healthandtechnology.com.healthandtechnologyapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ActInfoAdmin extends AppCompatActivity {
    ArrayList<Citas> listaCitas;
    ArrayList<Paciente> listaPaciente;
    ImageView you,face;
    String urlf="https://www.facebook.com/coemaeirl/posts/pfbid02v98ka5CErzx83GLbxYtznArPQaNTRgvQiwn8KQniETJ5Mv16PVtzZpXZ35Rf2FWZl?ref=embed_page",
            urly="https://www.youtube.com/channel/UCrQuxmcgsxyRlW5QkkYVt5A";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_hyt_nosotros_admin);
        recuperarData();
        face=findViewById(R.id.imgFace);
    }
    public void imageClickFacebook(View view) {
        Uri _linkF= Uri.parse(urlf);
        Intent i=new Intent(Intent.ACTION_VIEW, _linkF);
        startActivity(i);
    }
    public void imageClickYoutube(View view) {
        Uri _linkY= Uri.parse(urly);
        Intent k=new Intent(Intent.ACTION_VIEW, _linkY);
        startActivity(k);
    }
    public void redPrincipalAdmin(View view){
        Intent intent= new Intent(this,ActPrincipalAdmin.class);
        Bundle bundle= new Bundle();
        bundle.putSerializable("dataCitas",listaCitas);
        bundle.putSerializable("dataPaciente", listaPaciente);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal_nosotros_admin,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Bundle bundle=new Bundle();
        bundle.putSerializable("dataPaciente",listaPaciente);
        bundle.putSerializable("dataCitas", listaCitas);
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
    private void recuperarData(){
        Bundle bundle = getIntent().getExtras();
        if(bundle == null)
        {
            listaCitas = new ArrayList<>();
            listaPaciente = new ArrayList<>();
        }
        else
        {
            listaCitas = (ArrayList<Citas>)  bundle.getSerializable("dataCitas");
            listaPaciente = (ArrayList<Paciente>) bundle.getSerializable("dataPaciente");
        }
    }
}