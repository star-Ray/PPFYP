package fypproject.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;

import fypproject.R;

public class BallotActivity extends ActionBarActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter biddingListAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidding);

        recyclerView = (RecyclerView) findViewById(R.id.bidding_recycler);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        ArrayList<String> myDataset = new ArrayList<String>(Arrays.asList("bye1", "bye2", "bye3"));
        biddingListAdapter = new BiddingListAdapter(myDataset);
        recyclerView.setAdapter(biddingListAdapter);
    }

    public class BiddingListAdapter extends RecyclerView.Adapter<BiddingListAdapter.ViewHolder> {
        private ArrayList<String> mDataset;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public View view;
            public Button accept_button;
            public Button reject_button;

            public ViewHolder(View v) {
                super(v);
                view = v;
                accept_button = (Button)v.findViewById(R.id.button_accept);
                reject_button = (Button)v.findViewById(R.id.button_reject);

                accept_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getPosition();
                        mDataset.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, mDataset.size());
                    }
                });
                reject_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getPosition();
                        mDataset.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, mDataset.size());
                    }
                });
            }
//            public TextView getTitle(){
//                return textView2;
//            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public BiddingListAdapter(ArrayList<String> myDataset) {
            mDataset = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public BiddingListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_bidding_task, parent, false);

            // set the view's size, margins, paddings and layout parameters
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
//            holder.getTitle().setText(mDataset[position]);
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.size();
        }



    }
}
