package com.example.myfffd;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfffd.account.Profile;
import com.example.myfffd.models.User;
import com.example.myfffd.utility.Session;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Navigation menu activity.
 */
public class NavigationMenuActivity extends AppCompatActivity {
    /**
     * The User list.
     */
    /*creating the menu and assigning tasks for each item*/
    public List<User> userList = new ArrayList<>();
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem menu_signout =menu.findItem(R.id.menu_signout);
        menu_signout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                FirebaseAuth mAuth;
                mAuth = FirebaseAuth.getInstance();
                try {
                    mAuth.signOut();
                    Session.ActiveSession.logout();
                    /*Sign out the user from Firebase Auth and from Active session*/
                    Toast.makeText(NavigationMenuActivity.this, "Logged out successfully !", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(NavigationMenuActivity.this, Welcome.class));
                    finish(); // Destroy activity A and not exist in Back stack
                } catch (Exception exception) {
                    Toast.makeText(NavigationMenuActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show(); // show error, to be removed
                }
                return false;
            }
        });
        MenuItem menu_profile_pic = menu.findItem(R.id.menu_profile_pic);
        menu_profile_pic.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                /*Start next activity based on user choice*/
                startActivity(new Intent(NavigationMenuActivity.this, Profile.class));
                return false;
            }
        });

        /* Search function not fully implemented */
        MenuItem.OnActionExpandListener onActionExpandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Toast.makeText(NavigationMenuActivity.this,"Function not yet implemented !", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(NavigationMenuActivity.this,"Function not yet implemented !", Toast.LENGTH_SHORT).show();
                return true;
            }
        };
        menu.findItem(R.id.menu_search).setOnActionExpandListener(onActionExpandListener);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setQueryHint("Search users by Alias . . . ");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                User foundUser = getAlias(query);
                /*Check if the alias exists in the database */
                if(foundUser == null){
                    Toast.makeText(NavigationMenuActivity.this,"User not found",Toast.LENGTH_SHORT).show();
                }
                else {
                    /*Start next activity based on user choice*/
                    Intent i = new Intent(NavigationMenuActivity.this, Profile.class);
                    i.putExtra("OBJECT", foundUser);
                    startActivity(i);
                    Toast.makeText(NavigationMenuActivity.this,"User found",Toast.LENGTH_SHORT).show();
                    searchView.setQuery("", false);
                    searchView.clearFocus();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    /**
     * Get alias user.
     *
     * @param query the query
     * @return the user
     */
    public User getAlias(String query){
        /*Initiate Firebase instance*/
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("_user_");
        dbref.orderByChild("alias").equalTo(query).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            /*Check if the user exists in database*/
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot dss: snapshot.getChildren()){
                        User user = dss.getValue(User.class);
                        userList.add(user);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        /*Return the user which matches the alias searched*/
        for(User user: userList){
            String currentAlias = user.getAlias();
            if(currentAlias.equals(query)){
                return user;
            }
        }
        return null;
    }
}
