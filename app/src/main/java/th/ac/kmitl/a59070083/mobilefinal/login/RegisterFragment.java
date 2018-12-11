package th.ac.kmitl.a59070083.mobilefinal.login;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import th.ac.kmitl.a59070083.mobilefinal.R;

import static android.content.Context.MODE_PRIVATE;

public class RegisterFragment extends Fragment {
    private SQLiteDatabase myDB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register_fragment , container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myDB = getActivity().openOrCreateDatabase("foodland.db", MODE_PRIVATE, null);
        myDB.execSQL("CREATE TABLE IF NOT EXISTS user (user VARCHAR(50), name VARCHAR(50), age INTEGER, password VARCHAR(255))");
        regis_new_account();
    }

    private void regis_new_account() {
        Button _btn = getView().findViewById(R.id.regis_btn);
        _btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText _user = getView().findViewById(R.id.regis_user);
                EditText _name = getView().findViewById(R.id.regis_name);
                EditText _age = getView().findViewById(R.id.regis_age);
                EditText _password = getView().findViewById(R.id.regis_password);

                String _userStr = _user.getText().toString();
                String _nameStr = _name.getText().toString();
                Integer _ageInt = Integer.parseInt(_age.getText().toString());
                String _passwordStr = _password.getText().toString();

                if (_userStr.length() < 6 || _userStr.length() > 12){
                    Toast.makeText(getActivity(), "Error register user", Toast.LENGTH_SHORT).show();
                } else if( _nameStr.split(" ").length != 2 ){
                    Toast.makeText(getActivity(), "Error register name", Toast.LENGTH_SHORT).show();
                } else if (_ageInt < 10 || _ageInt > 80){
                    Toast.makeText(getActivity(), "Error register age", Toast.LENGTH_SHORT).show();
                } else if (_passwordStr.length() < 6) {
                    Toast.makeText(getActivity(), "Error register password", Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues values = new ContentValues();
                    values.put("user", _userStr);
                    values.put("name", _nameStr);
                    values.put("age", _ageInt);
                    values.put("password", _passwordStr);
                    myDB.insert("user", null, values);
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_view, new LoginFragment())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
    }
}
