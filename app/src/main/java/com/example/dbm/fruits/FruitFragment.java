package com.example.dbm.fruits;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.example.dbm.fruits.fruit.FruitContent;


public class FruitFragment extends Fragment implements AbsListView.OnItemClickListener {

    private AbsListView mListView;

    private ListAdapter mAdapter;

    public FruitFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: Change Adapter to display your content
        mAdapter = new FruitListAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fruit, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FruitContent.FruitItem item = FruitContent.ITEMS.get(position);
        String message = String.format("%s was pressed. Do something here.", item.name);
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }


    private class FruitListAdapter extends ArrayAdapter<FruitContent.FruitItem> {

        public FruitListAdapter(Context context) {
            super(context, 0, FruitContent.ITEMS);
        }

        @Override
        public int getCount() {
            return FruitContent.ITEMS.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null)
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_fruit, null);

            FruitContent.FruitItem fruit_item = FruitContent.ITEMS.get(position);

            ImageView fruit_image = (ImageView)convertView.findViewById(R.id.image_fruit);
            TextView fruit_name = (TextView)convertView.findViewById(R.id.text_fruit);

            fruit_image.setImageDrawable(getResources().getDrawable(fruit_item.image));
            fruit_name.setText(fruit_item.name);

            return convertView;
        }
    }
}
