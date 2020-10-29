package com.random.login_app.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import com.random.login_app.HelperClasses.HomeAdapter.featuredAdapter;
import com.random.login_app.HelperClasses.HomeAdapter.featuredHelperClass;
import com.random.login_app.R;

import java.util.ArrayList;


public class UserDashboard extends AppCompatActivity {

    RecyclerView featuredRecycler;
    RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);


        //Hooks
        featuredRecycler = findViewById(R.id.featured_recycler);
        featuredRecycler();
    }

    private void featuredRecycler() {
        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        ArrayList<featuredHelperClass> featuredLocations = new ArrayList<>();

        featuredLocations.add(new featuredHelperClass(R.drawable.cplus,"visual Csharp","C# is a general-purpose, multi-paradigm programming language encompassing strong typing, lexically scoped."));
        featuredLocations.add(new featuredHelperClass(R.drawable.cpro,"Programming Languages","C# is a general-purpose, multi-paradigm programming language encompassing strong typing, lexically scoped."));
        featuredLocations.add(new featuredHelperClass(R.drawable.cplusplus,"C ++","C# is a general-purpose, multi-paradigm programming language encompassing strong typing, lexically scoped."));
        adapter = new featuredAdapter(featuredLocations);
        featuredRecycler.setAdapter(adapter);

    }
}