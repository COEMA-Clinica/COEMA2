package healthandtechnology.com.healthandtechnologyapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentItemCitas extends Fragment {
    TextView txtNom,txtApeP,txtApeM,txtSexo,txtFecha,txtClinica,txtEspecialidad,txtDocto;
    ImageView imgFotos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_item_cita,container,false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imgFotos= getView().findViewById(R.id.imgFoto);
        txtNom=getView().findViewById(R.id.txtNomItem);
        txtApeP=getView().findViewById(R.id.txtApePaItem);
        txtApeM=getView().findViewById(R.id.txtApeMaItem);
        txtSexo=getView().findViewById(R.id.txtSexoItem);
        txtClinica=getView().findViewById(R.id.txtClinItem);
        txtEspecialidad=getView().findViewById(R.id.txtEspeItem);
        txtDocto=getView().findViewById(R.id.txtDoctorItem);
        txtFecha=getView().findViewById(R.id.txtFechaItem);

    }


    public void mostrarLista(Citas citas){
        imgFotos.setImageResource(citas.getIdFoto());
        txtNom.setText(citas.getNom());
        txtApeM.setText(citas.getApeMat());
        txtApeP.setText(citas.getApePat());
        txtSexo.setText(citas.getSexo());
        txtClinica.setText(citas.getClin());
        txtEspecialidad.setText(citas.getEsp());
        txtDocto.setText(citas.getDoc());
        txtFecha.setText(citas.getFecCit());
    }
}
