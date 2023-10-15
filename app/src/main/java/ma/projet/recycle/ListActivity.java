package ma.projet.recycle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.core.app.ShareCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import ma.projet.recycle.adapter.StarAdapter;
import ma.projet.recycle.beans.Star;
import ma.projet.recycle.service.StarService;



public class ListActivity extends AppCompatActivity {
    private List<Star> stars;
    private RecyclerView recyclerView;
    private StarAdapter starAdapter = null;

    private StarService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        stars = new ArrayList<>();
        service = StarService.getInstance();
        init();
        recyclerView = findViewById(R.id.recycle_view);
        starAdapter = new StarAdapter(this, service.findAll());
        recyclerView.setAdapter(starAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        if (menuItem != null) {
            SearchView searchView = (SearchView) menuItem.getActionView();
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    if (starAdapter != null) {
                        starAdapter.getFilter().filter(newText);
                    }
                    return true;
                }
            });
        }
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.share){
            String txt = "Stars";
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(mimeType)
                    .setChooserTitle("Stars")
                    .setText(txt)
                    .startChooser();
        }
        return super.onOptionsItemSelected(item);
    }


    public void init() {
        service.create(new Star("ryan reynolds", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/14/Deadpool_2_Japan_Premiere_Red_Carpet_Ryan_Reynolds_%28cropped%29.jpg/1200px-Deadpool_2_Japan_Premiere_Red_Carpet_Ryan_Reynolds_%28cropped%29.jpg", 5));
        service.create(new Star("kate bosworth", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/49/Kate_Bosworth_%288078979442%29.jpg/800px-Kate_Bosworth_%288078979442%29.jpg", 3.5f));
        service.create(new Star("blake lively", "https://resizing.flixster.com/e4WD78AWi-fFQ7MiTuRg_uiPSPg=/ems.ZW1zLXByZC1hc3NldHMvY2VsZWJyaXRpZXMvNjRlNzA3NTYtMzc2YS00NWJlLWFiM2UtZDcxMGRhZTY2ZTU1LmpwZw==", 5));
        service.create(new Star("george clooney", "https://fr.web.img4.acsta.net/c_310_420/pictures/16/05/12/17/04/136865.jpg", 3));
        service.create(new Star("michelle rodriguez", "https://upload.wikimedia.org/wikipedia/commons/e/e0/Michelle_Rodriguez_2010_cropped.jpg", 5));
        service.create(new Star("van diesel", "https://fr.web.img6.acsta.net/pictures/15/10/14/11/30/335169.jpg", 1));
        service.create(new Star("louise bourgoin", "https://fr.web.img4.acsta.net/pictures/15/10/06/10/23/291029.jpg", 5));

    }
}