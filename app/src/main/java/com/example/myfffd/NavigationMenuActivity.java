package com.example.myfffd;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfffd.utility.Session;
import com.google.firebase.auth.FirebaseAuth;

public class NavigationMenuActivity extends AppCompatActivity {
    /*creating the menu and assigning tasks for each item*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem menu_settings = menu.findItem(R.id.menu_settings);
        menu_settings.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startActivity(new Intent(NavigationMenuActivity.this, Profile.class));
                return false;
            }
        });
        MenuItem menu_signout =menu.findItem(R.id.menu_signout);
        menu_signout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                FirebaseAuth mAuth;
                mAuth = FirebaseAuth.getInstance();
                try {
                    mAuth.signOut();
                    Session.ActiveSession.logout();
                    Toast.makeText(NavigationMenuActivity.this, "Logged out successfully !", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(NavigationMenuActivity.this, Login.class));
                    finish(); // Destroy activity A and not exist in Back stack
                } catch (Exception exception) {
                    Toast.makeText(NavigationMenuActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show(); // show error, to be removed
                }
                return false;
            }
        });
        /* Search function not yet implemented */
        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Toast.makeText(NavigationMenuActivity.this,"Funtion not yet implemented !", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(NavigationMenuActivity.this,"Funtion not yet implemented !", Toast.LENGTH_SHORT).show();
                return true;
            }
        };

        menu.findItem(R.id.menu_search).setOnActionExpandListener(onActionExpandListener);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setQueryHint("Search bar not yet implemented . . . ");
        return super.onCreateOptionsMenu(menu);
    }
}
