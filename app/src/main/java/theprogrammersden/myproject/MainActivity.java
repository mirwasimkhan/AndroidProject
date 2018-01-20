package theprogrammersden.myproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.Duration;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private List<Task> itemsList;
    private ArrayAdapter<String> itemsAdapter;
    private ListView lvItems;
    private Toast removeAlert;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this,"Hint: Long Press items to Remove them", Toast.LENGTH_LONG).show();
        removeAlert = Toast.makeText(this,"Item Removed", Toast.LENGTH_LONG);

        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<String>();
        task = new Task("First Item");
        itemsList = task.read();
        if(itemsList != null) {
            for (Task temp : itemsList) {
                items.add(temp.getTask());
            }
        }
        else{
            items.add("Welcome");
        }
        
        itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);

        setupListViewListener();

        findViewById(R.id.btnAddItem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
                String itemText = etNewItem.getText().toString();
                if(itemText.contentEquals("")) {
                    Toast.makeText(v.getContext(),"Type Something First", Toast.LENGTH_SHORT).show();
                }
                else{
                    task = new Task(itemText);
                    itemsAdapter.add(itemText);
                    etNewItem.setText("");
                    Toast.makeText(v.getContext(),"Item Added", Toast.LENGTH_SHORT).show();
                    task.write(task);
                }
            }
        });
    }

    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        String x = itemsAdapter.getItem(pos);
                        Task.getTask(x).delete();
                        itemsAdapter.notifyDataSetChanged();
                        items.remove(pos);
                        removeAlert.show();
                        return true;
                    }
                });
    }
}
