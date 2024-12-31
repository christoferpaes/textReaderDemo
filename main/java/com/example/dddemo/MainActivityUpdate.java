import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dddemo.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ListView listView;
    private ArrayList<String> cardNames;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Load the data dynamically from the file
        String fileName = "Yugioh.txt";
        String fileContent = loadData(fileName);

        if (fileContent.isEmpty()) {
            // Show an error message if data couldn't be loaded
            Toast.makeText(this, "Failed to load data", Toast.LENGTH_LONG).show();
            return;
        }

        // Dynamically create Yugioh cards based on the loaded data
        int numberOfCards = loadYugiohData(fileContent); // Returns the number of cards
        Yugioh[] yugiohCards = new Yugioh[numberOfCards]; // Create array of exact size
        loadYugioh(yugiohCards, fileContent);

        // Set up the ListView to display card names
        listView = findViewById(R.id.listView);
        cardNames = new ArrayList<>();
        for (int i = 0; i < numberOfCards; ++i) {
            cardNames.add(yugiohCards[i].getName());
        }

        // Set up the ArrayAdapter for ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cardNames);
        listView.setAdapter(adapter);

        // Handle item clicks on the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                // Handle the item click event
                Toast.makeText(getApplicationContext(),
                        "Selected card: " + selectedItem,
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Dynamically determines the number of Yugioh cards based on the file content
    private int loadYugiohData(String fileContent) {
        String[] lines = fileContent.split("\n");
        return lines.length; // Return the number of items (lines) in the file
    }

    // Loads the Yugioh cards from the file content
    private void loadYugioh(Yugioh[] yugiohCards, String fileContent) {
        String[] lines = fileContent.split("\n");
        int index = 0;
        for (String line : lines) {
            String[] data = line.split(","); // Assuming comma-separated data for each card
            if (data.length >= 4) { // Ensure there's enough data for each card
                Yugioh yugiohCard = new Yugioh();
                yugiohCard.setName(data[0].trim()); // Assuming name is the first item
                yugiohCard.setType(data[1].trim()); // Assuming type is the second item
                yugiohCard.setAttackPower(Integer.parseInt(data[2].trim())); // Attack power is third
                yugiohCard.setDefensePower(Integer.parseInt(data[3].trim())); // Defense power is fourth
                yugiohCards[index++] = yugiohCard;
            }
        }
    }

    // Loads data from assets
    private String loadData(String fileName) {
        StringBuilder content = new StringBuilder();
        try {
            InputStream inputStream = getAssets().open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace(); // Log the error for debugging
        }
        return content.toString();
    }
}
