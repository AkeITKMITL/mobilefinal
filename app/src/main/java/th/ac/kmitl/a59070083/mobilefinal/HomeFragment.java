package th.ac.kmitl.a59070083.mobilefinal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import th.ac.kmitl.a59070083.mobilefinal.login.LoginFragment;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initText();
        profile();
        friend();
        logout();
    }

    private void initText() {
        SharedPreferences prefs = getContext().getSharedPreferences("final", Context.MODE_PRIVATE);
        String user_name = prefs.getString("name", "none");
        TextView _textfirrst = getView().findViewById(R.id.home_first);
        _textfirrst.setText("Hello " + user_name);
    }

    private void profile() {
        Button _profile = getView().findViewById(R.id.home_setup);
        _profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new ProfileFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void friend() {
        Button _friend = getView().findViewById(R.id.home_friend);
        _friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new FriendFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void logout() {
        Button _logout = getView().findViewById(R.id.home_logout);
        _logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor prefsEd = getContext().getSharedPreferences("final", MODE_PRIVATE).edit();
                prefsEd.clear();
                prefsEd.apply();
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new LoginFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }
}
