package fypproject.Activity.HomepageFragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import fypproject.Activity.HomepageActivity;
import fypproject.Entity.Task;
import fypproject.R;
import fypproject.TestCreator;

public class OngoingFragment extends android.support.v4.app.Fragment {
    //        public static final String ARG_OBJECT = "object";
    private static RecyclerView recyclerView;
    private static RecyclerView.Adapter taskListAdapter;
    private static RecyclerView.LayoutManager layoutManager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        loadRecyclerView(rootView);
        return rootView;
    }

    public void loadRecyclerView(View view){
        recyclerView = (RecyclerView) view.findViewById(R.id.taskRecyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        ArrayList<Task> taskList = TestCreator.createTestTasks1();
        ArrayList<Task> myDataset = taskList;
        taskListAdapter = new HomepageActivity.HomepageTaskListAdapter(myDataset);
        recyclerView.setAdapter(taskListAdapter);
    }
}
