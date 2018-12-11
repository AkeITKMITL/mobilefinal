package th.ac.kmitl.a59070083.mobilefinal.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import th.ac.kmitl.a59070083.mobilefinal.HomeFragment;
import th.ac.kmitl.a59070083.mobilefinal.R;

import static android.content.Context.MODE_PRIVATE;

public class LoginFragment extends Fragment {
    private SQLiteDatabase myDB;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myDB = getActivity().openOrCreateDatabase("foodland.db", MODE_PRIVATE, null);
        myDB.execSQL("CREATE TABLE IF NOT EXISTS user (user VARCHAR(50), name VARCHAR(50), age INTEGER, password VARCHAR(255))");
        check_login();
        goto_register();
    }

    private void check_login() {SharedPreferences prefs = getContext().getSharedPreferences("final", Context.MODE_PRIVATE);
        String user_login = prefs.getString("userId", "none");
        Log.d("Login", user_login);
        if (user_login.length() > 6 ) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_view, new HomeFragment())
                    .addToBackStack(null)
                    .commit();
        }
        Button _loginBtn = getView().findViewById(R.id.login_btn);
        _loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText _user = getActivity().findViewById(R.id.login_user);
                EditText _password = getActivity().findViewById(R.id.login_password);

                String _userStr = _user.getText().toString();
                String _passwordStr = _password.getText().toString();

                if (_userStr.isEmpty() || _passwordStr.isEmpty()) {
                    Toast.makeText(getActivity(), "Please fill out this form", Toast.LENGTH_SHORT).show();
                } else {
                    Cursor myCursor = myDB.rawQuery("SELECT * FROM user", null);
                    while (myCursor.moveToNext()) {
                        if (_userStr.equals(myCursor.getString(1)) && _passwordStr.equals(myCursor.getString(4))) {
                            SharedPreferences.Editor prefsEd = getContext().getSharedPreferences("final", MODE_PRIVATE).edit();
                            Log.d("Login",  myCursor.getString(1) + " - " + myCursor.getString(2) );
                            prefsEd.putString("userId", myCursor.getString(1));
                            prefsEd.putString("name", myCursor.getString(2));
                            prefsEd.apply();
                            getActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.main_view, new HomeFragment())
                                    .addToBackStack(null)
                                    .commit();
                        } else {
                            Toast.makeText(getActivity(), "Invalid user or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    public void goto_register() {
        TextView _textregis = getActivity().findViewById(R.id.login_register);
        _textregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
