package fypproject.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import fypproject.Adapter.MyLinearLayoutManager;
import fypproject.Entity.Item;
import fypproject.Entity.Task;
import fypproject.R;
import fypproject.TestCreator;

public class TaskActivity extends ActionBarActivity {

    private static final String TAG = "arnono/TaskActivity";

    public static Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        if(savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.item_list_container, new ItemListFragment());
            fragmentTransaction.commit();
        }

        gson = new GsonBuilder().setDateFormat(getString(R.string.date_format)).create();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent receivedIntent = getIntent();
        String jsonTask = receivedIntent.getStringExtra("task");
        Task task = gson.fromJson(jsonTask, Task.class);

        setViews(task);
    }

    private void setViews(Task task){
        TextView receiver = (TextView)findViewById(R.id.content_receiver);
        TextView contactNo = (TextView)findViewById(R.id.content_contactNumber);
        TextView address = (TextView)findViewById(R.id.content_address);

        receiver.setText(task.getReceiverName());
        contactNo.setText(task.getReceiverContact());
        address.setText(task.getEndLocation());
        Log.i(TAG, "TextViews are set.");
    }


    public static class ItemListFragment extends android.support.v4.app.Fragment {

        private static RecyclerView recyclerView;
        private static RecyclerView.Adapter itemListAdapter;
        private static RecyclerView.LayoutManager layoutManager;

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_recyclerview, container, false);

            recyclerView = (RecyclerView) rootView.findViewById(R.id.taskRecyclerView);
            recyclerView.setHasFixedSize(true);
            layoutManager = new MyLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
//            layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);

            ArrayList<Item> dataSet = TestCreator.createTestItems();
            itemListAdapter = new TaskPageItemListAdapter(dataSet);
            recyclerView.setAdapter(itemListAdapter);
            return rootView;
        }
    }


    public static class TaskPageItemListAdapter extends RecyclerView.Adapter<TaskPageItemListAdapter.ViewHolder> {
        private ArrayList<Item> dataSet;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder {
            public View view;
            public TextView desc;
            public TextView itemId;
            public TextView tagId;
            public Button button;

            public ViewHolder(View v) {
                super(v);
                view = v;

                desc = (TextView) v.findViewById(R.id.content_description);
                itemId = (TextView) v.findViewById(R.id.content_itemId);
                tagId = (TextView) v.findViewById(R.id.content_tagId);
                button = (Button)v.findViewById(R.id.button_load);

                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Context context = v.getContext();
                        int position = getPosition();
                        Item selectedItem = dataSet.get(position);
                        Log.d(TAG, "Position selected: " + position);

                        Intent intent = new Intent(context, ItemActivity.class);
                        intent.putExtra("item", gson.toJson(selectedItem));
                        context.startActivity(intent);
                    }
                });

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Context context = v.getContext();
                        int position = getPosition();
                        Item selectedItem = dataSet.get(position);
                        Log.d(TAG, "Position selected: " + position);

                        Intent intent = new Intent(context, WriteNFCActivity.class);
                        intent.putExtra("item", gson.toJson(selectedItem));
                        context.startActivity(intent);
                    }
                });
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public TaskPageItemListAdapter(ArrayList<Item> myDataset) {
            dataSet = myDataset;
        }

        @Override // Create new views (invoked by the layout manager)
        public TaskPageItemListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_itemlist, parent, false); // create a new view
            ViewHolder vh = new ViewHolder(v); // set the view's size, margins, paddings and layout parameters
            return vh;
        }

        @Override // Replace the contents of a view (invoked by the layout manager)
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element

            holder.desc.setText(dataSet.get(position).getDesc());
            holder.itemId.setText(String.valueOf(dataSet.get(position).getID()));
            holder.tagId.setText(dataSet.get(position).getNfcTag());
        }

        @Override // Return the size of your dataset (invoked by the layout manager)
        public int getItemCount() {
            return dataSet.size();
        }
    }

}
