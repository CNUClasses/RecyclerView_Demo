package com.example.recyclerview_demo;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private static final int MAX_ROWS = 1000;


    // Pass in the contact array into the constructor
    public ContactsAdapter() {

    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.row_layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    private class CalculateValueTask extends AsyncTask<Integer, Void, Integer> {

        private ViewHolder myVh;    //ref to a viewholder
        private int origNumbToDouble; //since myVH may be recycled and reused
        //we have to verify that the result we are returning
        //is still what the viewholder wants

        public CalculateValueTask(ViewHolder myVh) {
            this.myVh = myVh;
            this.origNumbToDouble = myVh.myNumberToDouble;
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return integers[0] + integers[0];
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

            //got a result, if the following are NOT equal
            // then the view has been recycled and is being used by another
            // number DO NOT MODIFY
            if (this.myVh.myNumberToDouble == this.origNumbToDouble){
                //still valid
                //set the result (non threaded)
                String s1 = Integer.toString(2*myVh.myNumberToDouble);
                myVh.tv_result.setText(s1);
            }
        }
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ContactsAdapter.ViewHolder viewHolder, int position) {
        // Set item views based on your views and data model
        TextView tv_eq = viewHolder.tv_equation;
        String s1 = Integer.toString(position);

        viewHolder.myNumberToDouble=position;

        //set the first field
        tv_eq.setText(s1 + " + " + s1 + " = " );

        //the no launched threads way
//        TextView tv_res = viewHolder.tv_result;
//        tv_res.setText(Integer.toString(position*2));

        //and the second if doing threads field and you dont want the
        //old rubbish to appear
        viewHolder.tv_result.setText("?");

        CalculateValueTask myTask = new CalculateValueTask(viewHolder);
        myTask.execute(viewHolder.myNumberToDouble);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return MAX_ROWS;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_equation;
        public TextView tv_result;
        public int myNumberToDouble;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_equation =  (TextView) itemView.findViewById(R.id.tv_equation);
            tv_result = (TextView) itemView.findViewById(R.id.tv_result);
        }
    }
}