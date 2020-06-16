package com.example.mercadoesclavoentregable.view.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mercadoesclavoentregable.R;
import com.example.mercadoesclavoentregable.model.UserInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;


public class FragmentUserInfo extends Fragment {

    private EditText nombreCompleto;
    private EditText apodo;
    private EditText edad;
    private EditText ciudad;
    private Button registerFirebaseFirestore;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    public static final String USERINFO = "userInfo";


    private FragmentUserInfoListener fragmentUserInfoListener;

    public FragmentUserInfo() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.fragmentUserInfoListener = (FragmentUserInfoListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_info, container, false);

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();


        nombreCompleto = view.findViewById(R.id.editTextNombreCompleto);
        apodo = view.findViewById(R.id.editTextApodo);
        edad = view.findViewById(R.id.editTextEdad);
        ciudad = view.findViewById(R.id.editTextCiudad);

        registerFirebaseFirestore = view.findViewById(R.id.botonFirebaseFirestore);

        registerFirebaseFirestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserInfo userInfo = new UserInfo(nombreCompleto.getText().toString(), apodo.getText().toString(), edad.getText().toString(), ciudad.getText().toString(), null);
                agregarUserInfoAFirestone(userInfo);

                fragmentUserInfoListener.onClickFinalizarUserInfo();

            }
        });


        return view;


    }

    public void agregarUserInfoAFirestone(UserInfo userInfo) {

        db.collection(USERINFO)
                .document(firebaseUser.getUid())
                .set(userInfo)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getContext(), "Datos actualizados", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Error en la base de datos", Toast.LENGTH_SHORT).show();

                        }
                    }
                });





    }


    public interface FragmentUserInfoListener {
        public void onClickFinalizarUserInfo();

    }

}