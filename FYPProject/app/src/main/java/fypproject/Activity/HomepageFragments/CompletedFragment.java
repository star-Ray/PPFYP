package fypproject.Activity.HomepageFragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import fypproject.Activity.HomepageActivity;
import fypproject.Activity.TaskActivity;
import fypproject.Entity.Task;
import fypproject.R;
import fypproject.TestCreator;

public class CompletedFragment extends android.support.v4.app.Fragment {
    private static final String TAG = "arnono/CompletedFrag";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        loadRecyclerView(rootView);
        return rootView;
    }

    public void loadRecyclerView(View view){
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.taskRecyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        ArrayList<Task> taskList = TestCreator.createTestTasks2();
        ArrayList<Task> myDataset = taskList;
        RecyclerView.Adapter taskListAdapter = new CompletedTaskListAdapter(myDataset);
        recyclerView.setAdapter(taskListAdapter);
    }

    public static class CompletedTaskListAdapter extends RecyclerView.Adapter<CompletedTaskListAdapter.ViewHolder> {
        private ArrayList<Task> dataSet;
        Gson gson = new GsonBuilder().setDateFormat(R.string.date_format).create();

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder {
            public View view;
            public TextView recipient;
            public TextView address;
            public TextView time;
            public TextView orderNo;

            public ViewHolder(View v) {
                super(v);
                view = v;

                recipient = (TextView)view.findViewById(R.id.content_receiver);
                address = (TextView)view.findViewById(R.id.content_address);
                time = (TextView)view.findViewById(R.id.content_time);
                orderNo = (TextView)view.findViewById(R.id.content_orderNo);

                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Context context = v.getContext();
                        int position = getPosition();
                        Task selectedTask = dataSet.get(position);
                        Log.d(TAG, "Position selected: " + position);

                        Intent intent = new Intent(context, TaskActivity.class);
                        intent.putExtra("task", gson.toJson(selectedTask));
                        context.startActivity(intent);
                    }
                });
            }
        }

        //        constructor
        public CompletedTaskListAdapter(ArrayList<Task> myDataset) {
            dataSet = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public CompletedTaskListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_completed_tasklist, parent, false); // create a new view
            ViewHolder vh = new ViewHolder(v); // set the view's size, margins, paddings and layout parameters
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element

            holder.recipient.setText(dataSet.get(position).getReceiverName());
            holder.address.setText(dataSet.get(position).getEndLocation());
//            holder.time.setText(dataSet.get(position).getPlanEndTime().toString());
            holder.orderNo.setText(String.valueOf(dataSet.get(position).getTaskId()));
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return dataSet.size();
        }

    }
}
