package com.example.mercadoesclavoentregable.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mercadoesclavoentregable.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.concurrent.Executor;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLogIn extends Fragment implements View.OnClickListener {

    private SignInButton botonRegisterGoogle;
    private Button botonLogOut;
    private Button botonLogInFirebase;
//    private GoogleSignInClient client;
   // private static final int RC_SIGN_IN_GOOGLE = 1;

    private EditText mail;
    private EditText password;
    private MaterialTextView textoRegistrarse;

    private FragmentLogInListener fragmentLogInListener;


    private FirebaseAuth mAuth;

    public FragmentLogIn() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.fragmentLogInListener = (FragmentLogInListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_log_in, container, false);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        findViewById(view);


        botonLogInFirebase.setOnClickListener(this);
        botonRegisterGoogle.setOnClickListener(this);
        botonRegisterGoogle.setSize(SignInButton.SIZE_WIDE);
        textoRegistrarse.setOnClickListener(this);


        return view;
    }


    private void findViewById(View view) {

        botonRegisterGoogle = view.findViewById(R.id.botonRegisterGoogle);
        botonLogOut = view.findViewById(R.id.botonLogOut);
        botonLogInFirebase = view.findViewById(R.id.botonLogIn);
        mail = view.findViewById(R.id.editTextMail);
        password = view.findViewById(R.id.editTextContraseña);
        textoRegistrarse = view.findViewById(R.id.textViewRegistrarse);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.botonLogIn:
                fragmentLogInListener.onClickSingInFirebase(mail.getText().toString(), password.getText().toString());

                break;
            case R.id.botonRegisterGoogle:


                fragmentLogInListener.onClickSingInGoogle();

                /*GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .requestProfile()
                        .build();

                client = GoogleSignIn.getClient(getActivity(), gso);


                Intent signInIntent = client.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN_GOOGLE);
*/
                break;

            case R.id.textViewRegistrarse:
                fragmentLogInListener.onClickBotonCrearCuenta();

                break;
        }
    }


  /*  @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case RC_SIGN_IN_GOOGLE:

                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                handleSignInResult(task);

                break;
        }


    }
*/
 /*   private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
*//*
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Toast.makeText(getContext(), "Login correcto con Google", Toast.LENGTH_SHORT).show();
*//*
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Log.d("GOOGLEFB", "firebaseAuthWithGoogle:" + account.getId());
            firebaseAuthWithGoogle(account.getIdToken());

            //updateUIGoogle(account);
        } catch (ApiException e) {
            Log.w("GOOGLE", "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(getContext(), "Error inesperado", Toast.LENGTH_SHORT).show();
            updateUIGoogle(null);
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                           // Log.d("Google", "signInWithCredential:success");
                          //  Toast.makeText(getContext(), "Login google firebase", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUIGoogle(user);
                        } else {
                            Toast.makeText(getContext(), "Error inesperado de google", Toast.LENGTH_SHORT).show();
                            // If sign in fails, display a message to the user.
                            //Log.w("Google", "signInWithCredential:failure", task.getException());
                           // Snackbar.make(mBinding.mainLayout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }
*/

    @Override
    public void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());
        //updateUIGoogle(account);

        //FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUIFirebase(currentUser);
    }

  /*  public void updateUIGoogle(FirebaseUser account) {
        if (account != null) {
            //Pasar a fragment con datos de cuenta
            Toast.makeText(getContext(), "Se logueo usuario de google", Toast.LENGTH_SHORT).show();

        }

    }
*/


    public interface FragmentLogInListener {
        public void onClickBotonCrearCuenta();

        public void onClickSingInFirebase(String mail, String password);

        public void onClickSingInGoogle();

    }


}
