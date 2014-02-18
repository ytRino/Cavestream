package net.nessness.android.cavestream.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import net.nessness.android.cavestream.R;
import net.nessness.android.cavestream.model.Channel;
import net.nessness.android.cavestream.net.IndexTxtRequest;
import net.nessness.android.cavestream.parser.IndexTxtParser;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.List;


/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the Callbacks
 * interface.
 */
public class ChannelFragment extends Fragment implements AbsListView.OnItemClickListener {

    private static final String TAG = ChannelFragment.class.getSimpleName();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    @Deprecated
    private static final String ARG_PARAM1 = "param1";
    @Deprecated
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    @Deprecated
    private String mParam1;
    @Deprecated
    private String mParam2;

    private OnChannelListSelectedListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;

    /** volley request queue */
    RequestQueue mQueue;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ChannelFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_channel_grid, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        mQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        mQueue.add(new IndexTxtRequest(
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        List<Channel> list = new IndexTxtParser().parse(response);

                        mAdapter = new ArrayAdapter<Channel>(getActivity(),
                                android.R.layout.simple_list_item_1, android.R.id.text1, list);
                        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "volley response error");
                    }
                }
        ));

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnChannelListSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnChannelListSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onChannelSelected((Channel)mAdapter.getItem(position));
        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyText instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnChannelListSelectedListener {
        // TODO: Update argument type and name
        public void onChannelSelected(Channel channel);
    }

}
