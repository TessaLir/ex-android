package ru.vetukov.spec.java.level01.allsaintsstarter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public class MainActivity extends AppCompatActivity {

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
    }
}
