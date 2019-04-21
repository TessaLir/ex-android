package ru.vetukov.sb.tutorialrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] names = new String[100];

        for (int i = 0; i < names.length; i++) {
            if (i < 10) {
                names[i] = "Item #0" + i;
            } else {
                names[i] = "Item #" + i;
            }
        }

        final RecyclerView recyclerView = findViewById(R.id.main_view_list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(new LisrAdapter(this, names));
    }

    private static class LisrAdapter extends RecyclerView.Adapter<LisrAdapter.ViewHolder> {

        private final LayoutInflater mInflater;
        private String[] mData;

        public LisrAdapter(Context context, String[] mData) {
            mInflater = LayoutInflater.from(context);
            this.mData = mData;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = mInflater.inflate(android.R.layout.simple_expandable_list_item_1, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.textView.setText(mData[position]);
        }

        @Override
        public int getItemCount() {
            return mData.length;
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            public TextView textView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                textView = itemView.findViewById(android.R.id.text1);
            }
        }
    }
}
