package fypproject.Activity;

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
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

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

//        initializing
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

    //android.support.v4.app.Fragment
    public static class ItemListFragment extends android.support.v4.app.Fragment {

        private static final String TAG = "arnono/ItemListFragment";

        private static RecyclerView recyclerView;
        private static RecyclerView.Adapter itemListAdapter;
        private static RecyclerView.LayoutManager layoutManager;

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            Log.d(TAG, "OnCreateView");
            View rootView = inflater.inflate(R.layout.fragment_recyclerview, container, false);
            Log.d(TAG, "rootview context: " + rootView.getContext());

            Log.d(TAG, "loadRecyclerView");
            recyclerView = (RecyclerView) rootView.findViewById(R.id.taskRecyclerView);
            Log.d(TAG, "recyclerView: " + recyclerView);
            recyclerView.setHasFixedSize(false);
            layoutManager = new LinearLayoutManager(getActivity());

            System.out.println("activity: " + getActivity());
            recyclerView.setLayoutManager(layoutManager);
//            ArrayList<Item> dataSet = TestCreator.createTestItems();
            ArrayList<String> dataSet = new ArrayList<>();
            dataSet.add("apples");
            dataSet.add("banana");
            System.out.println("Dataset: " + dataSet);
            itemListAdapter = new TaskPageItemListAdapter(dataSet);
            System.out.println("itemlistadapter: " + itemListAdapter);
            recyclerView.setAdapter(itemListAdapter);
            Log.d(TAG, "recyclerview string: " + recyclerView.toString());

            Log.d(TAG, "returnRootView");
            return rootView;
        }
    }

    public static class TaskPageItemListAdapter extends RecyclerView.Adapter<TaskPageItemListAdapter.ViewHolder> {
        private ArrayList<String> dataSet;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder {
            public View view;

            public ViewHolder(View v) {
                super(v);
                view = v;
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public TaskPageItemListAdapter(ArrayList<String> myDataset) {
            dataSet = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public TaskPageItemListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_itemlist, parent, false); // create a new view
            ViewHolder vh = new ViewHolder(v); // set the view's size, margins, paddings and layout parameters
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
//            return dataSet.size();
            return 5;
        }

    }
}
