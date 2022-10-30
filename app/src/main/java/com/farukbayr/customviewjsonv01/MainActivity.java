package com.farukbayr.customviewjsonv01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String json_string = "{\n" +
            "  \"array\":[\n" +
            "    {\n" +
            "    \"CustomViewType\":\"Header\",\n" +
            "    \"Properties\":{\n" +
            "       \"Text\":\"Custom view örnek\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"CustomViewType\":\"TextInput\",\n" +
            "    \"Properties\":{\n" +
            "       \"Header\":\"Text Input\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"CustomViewType\":\"Combobox\",\n" +
            "    \"Properties\":{\n" +
            "       \"Header\":\"Combobox\",\n" +
            "       \"Code\":[\"a\",\"b\",\"c\"],\n" +
            "       \"Text\":[\"ilk\",\"ikinci\",\"üçüncü\"]\n" +
            "    }\n" +
            "  }\n" +
            "  ]}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            ListView listView = findViewById(R.id.list_view);

            List<String> items = new ArrayList<>();
            JSONObject root = new JSONObject(json_string);

            JSONArray array = root.getJSONArray("array");

            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                items.add(object.getString("CustomViewType"));

                if (object.getString("CustomViewType").equals("Header")) {

                    this.setTitle(object.getJSONObject("Properties").getString("Text"));

                }
                if (object.getString("CustomViewType").equals("TextInput")) {

                    RelativeLayout relativeLayout = findViewById(R.id.relative_layout);
                    RelativeLayout.LayoutParams mParams = new RelativeLayout.LayoutParams(RelativeLayout
                            .LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    EditText editText = new EditText(this);
                    editText.setLayoutParams(mParams);
                    relativeLayout.addView(editText);
                    editText.setHint(object.getJSONObject("Properties").getString("Header"));

                }
                if (object.getString("CustomViewType").equals("Combobox")) {

                    RelativeLayout relativeLayout = findViewById(R.id.relative_layout);
                    RelativeLayout.LayoutParams mParams = new RelativeLayout.LayoutParams(RelativeLayout
                            .LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    Spinner spinner = new Spinner(this);
                    spinner.setLayoutParams(mParams);
                    relativeLayout.addView(spinner);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                            android.R.layout.simple_spinner_dropdown_item,
                            Collections.singletonList(object.getJSONObject("Properties").getString("Header")));
                    spinner.setAdapter(adapter);
                    mParams.topMargin = 75;

                }
            }

        } catch (JSONException e) {

            e.printStackTrace();

        }
    }

}