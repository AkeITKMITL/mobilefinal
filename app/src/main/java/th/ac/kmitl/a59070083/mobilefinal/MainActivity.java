package th.ac.kmitl.a59070083.mobilefinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import th.ac.kmitl.a59070083.mobilefinal.login.LoginFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null){
            LoginFragment loginFragment = new LoginFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_view, loginFragment)
                    .commit();
        }
    }
}
