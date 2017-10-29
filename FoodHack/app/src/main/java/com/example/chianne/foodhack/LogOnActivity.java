package com.example.chianne.foodhack;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class LogOnActivity extends AppCompatActivity implements View.OnClickListener {

    private Button back;

    private EditText emailEdit;
    private EditText passwordEdit;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    private Button loginButton;

    /*
        Request Code
    */
    private static final int RC_SIGN_IN = 9001;

    /*
        Google API Client
    */
    private GoogleApiClient mGoogleApiClient;

    private static final String TAG = "GoogleActivity";

    /*
    Firebase Authentication
     */
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private LogOnActivity self = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_on);

        back = (Button) findViewById(R.id.login_back_btn);
        back.setOnClickListener(this);

        emailEdit = (EditText) findViewById(R.id.login_email_edit);
        passwordEdit = (EditText) findViewById(R.id.login_pw_edit);

        loginButton = (Button) findViewById(R.id.login_btn);
        loginButton.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();





            /*
                Get Firebase mAuth instance
            */
            mAuth = FirebaseAuth.getInstance();


            // Build a GoogleApiClient with
            // access to the Google Sign-In API and the
            // options specified by gso.
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this /* FragmentActivity */, new GoogleApiClient.OnConnectionFailedListener() {
                        @Override
                        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                            Toast.makeText(LogOnActivity.this, "Something went wrong", Toast.LENGTH_SHORT);
                        }
                    } /* OnConnectionFailedListener */)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build();

            mAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull
                                                       FirebaseAuth firebaseAuth) {
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    if (user != null) {
                        // RegisteredUser is signed in
                        Log.d("Authentication", "onAuthStateChanged:signed_in:"
                                + user.getUid());
                        //startActivity(new Intent(LogOnActivity.this, ProfileActivity.class));
                    } else {
                        // RegisteredUser is signed out
                        Log.d("Authentication",
                                "onAuthStateChanged:signed_out");
                    }
                    // ...
                }
            };



    }

    @Override
    public void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                Toast.makeText(LogOnActivity.this, "Authorization went wrong", Toast.LENGTH_SHORT);
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LogOnActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

    }


    private void login() {

        //Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        //startActivityForResult(signInIntent, RC_SIGN_IN);
//        String email = emailEdit.getText().toString().trim();
//        String password = passwordEdit.getText().toString().trim();
//
//        if (TextUtils.isEmpty(email)) {
//            //email is empty
//
//            Toast.makeText(this, "Please enter an email address", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        if (TextUtils.isEmpty(password)) {
//            //password is empty
//            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        progressDialog.setMessage("Signing in...");
//        progressDialog.show();
//
//        firebaseAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        progressDialog.dismiss();
//
//                        if (task.isSuccessful()) {
//                            //start profile activity
//                            startActivity(new Intent(getApplicationContext(), FoodListActivity.class));
//                            finish();
//                        } else {
//                            AlertDialog alertDialog = new AlertDialog.Builder(LogOnActivity.this).create();
//                            alertDialog.setTitle("Log In Attempt Fail!");
//                            alertDialog.setMessage("User email and password do not match.");
//
//                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                                    new DialogInterface.OnClickListener() {
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            dialog.dismiss();
//                                        }
//                                    });
//                            alertDialog.show();
//                        }
//                    }
//                });


    }



    @Override
    public void onClick(View v) {
        if (v == back) {
            //Goes back to the main splash screen
            Intent intent = new Intent(LogOnActivity.this, WelcomePage.class);
            LogOnActivity.this.startActivity(intent);
            finish();
        }

        if (v == loginButton) {
            signIn();
//            Intent intent = new Intent(LogOnActivity.this, FoodListActivity.class);
//            LogOnActivity.this.startActivity(intent);
//            finish();
        }
    }
}