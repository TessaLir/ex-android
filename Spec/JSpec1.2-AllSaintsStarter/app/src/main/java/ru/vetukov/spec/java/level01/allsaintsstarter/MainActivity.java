package ru.vetukov.spec.java.level01.allsaintsstarter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static final String SAINT_RATING = "SAINT_RATING";
    public static final String SAINT_NAME = "SAINT_NAME";
    public static final String SAINT_ID = "SAINT_ID";

    public static final int RATING_REQUEST = 3471;

    private List<Saint> mSaints;
    private ListView mListView;
    private SaintAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSaints = new ArrayList<>();
        mListView = findViewById(R.id.main_list);

        InputSource mySints = new InputSource(getResources().openRawResource(R.raw.saints));
        XPath xPath = XPathFactory.newInstance().newXPath();

        // Запрос
        String expression = "/saints/saint";

        NodeList nodes;
        try {
            nodes = (NodeList) xPath.evaluate(expression, mySints, XPathConstants.NODESET);
            if(nodes != null) {
                int numSaints = nodes.getLength();
                for (int i = 0; i < numSaints; i++) {
                    Node saint = nodes.item(i);

                    String name = saint.getChildNodes().item(0).getTextContent();
                    String dob = saint.getChildNodes().item(1).getTextContent();
                    String dod = saint.getChildNodes().item(2).getTextContent();

                    mSaints.add(new Saint(name, dob, dod, 0f));
                }
            }

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        mAdapter = new SaintAdapter(this, R.layout.listviewitem, mSaints);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Вызывается при выборе элемента меню
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_up :
                Collections.sort(mSaints);
                mAdapter.notifyDataSetChanged();
//                mAdapter.refreshData(mSaints);
                return true;
            case R.id.menu_down :
                Collections.sort(mSaints, Collections.<Saint>reverseOrder());
                mAdapter.notifyDataSetChanged();
//                mAdapter.refreshData(mSaints);
                return true;
            case R.id.menu_add :
                getShowAddDialog();
                return true;
            ///
        }
        return super.onOptionsItemSelected(item);
    }

    private void getShowAddDialog() {
        View dialog = getLayoutInflater().inflate(R.layout.dialog_add,null);

        final EditText name = dialog.findViewById(R.id.dialog_add);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder
                .setView(dialog)
                .setTitle("Add a Saint!");

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String saint = name.getText().toString();
                mSaints.add(new Saint(saint, "", "", 0f));

                dialogInterface.dismiss();
            }
        });

        builder
                .create()
                .show();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, SaintDetail.class);

        intent.putExtra(SAINT_NAME, mSaints.get(position).getmName());
        intent.putExtra(SAINT_ID, position);
        intent.putExtra(SAINT_RATING, mSaints.get(position).getmRating());

        startActivityForResult(intent, RATING_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RATING_REQUEST && resultCode == RESULT_OK) {
            mSaints
                    .get(data.getIntExtra(SAINT_ID, 0))
                    .setmRating(data.getFloatExtra(SAINT_RATING, 0f));

            mAdapter.notifyDataSetChanged();
        }
    }
}
