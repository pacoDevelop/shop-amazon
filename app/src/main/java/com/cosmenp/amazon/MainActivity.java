package com.cosmenp.amazon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
//    static class MyJavaScriptInterface {
//
//        private Context ctx;
//        public String html;
//
//        MyJavaScriptInterface(Context ctx) {
//            this.ctx = ctx;
//        }
//
//        @JavascriptInterface
//        public void showHTML(String html) {
//            this.html = html;
//        }
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//           String url="https://www.amazon.es/Bimbo-Mini-Tostas-Rectangulares-100/dp/B01LZB4W2H?ref_=Oct_BSellerC_6372372031_0&pf_rd_p=c430c8ab-12d4-5bde-a53f-a871ff325750&pf_rd_s=merchandised-search-6&pf_rd_t=101&pf_rd_i=6372372031&pf_rd_m=A1AT7YVPFBWXBL&pf_rd_r=A3A552ETYH39XZQQTEAG&pf_rd_r=A3A552ETYH39XZQQTEAG&pf_rd_p=c430c8ab-12d4-5bde-a53f-a871ff325750";
//
//            ScrapAmazon.populateProducto(url,this);
            final FloatingActionButton fab = findViewById(R.id.fab);

            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            NavigationView navigationView = findViewById(R.id.nav_view);
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home, R.id.nav_insert,R.id.nav_listado)
                    .setDrawerLayout(drawer)
                    .build();

            final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
            NavigationUI.setupWithNavController(navigationView, navController);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                navController.navigate(R.id.nav_carrito);

            }
        });
        }



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        //getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onPause() {
        super.onPause();
        WebView webView= findViewById(R.id.webView);
        webView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        WebView webView= findViewById(R.id.webView);
        webView.onResume();
    }
}

