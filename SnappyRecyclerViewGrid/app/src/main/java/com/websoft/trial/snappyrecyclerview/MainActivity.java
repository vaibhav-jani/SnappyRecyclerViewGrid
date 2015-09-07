package com.websoft.trial.snappyrecyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    private RecyclerView recyclerViewH;

    private HAdapter hAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initViews();

        setData();
    }

    private void setData() {

        hAdapter.setData(getSampleData());
    }

    private void initViews() {

        recyclerViewH =  (RecyclerView) findViewById(R.id.recyclerViewH);

        final LinearLayoutManager layoutManagerH = new SnappyLinearLayoutManager(this);
        layoutManagerH.setOrientation(LinearLayoutManager.HORIZONTAL);
        //layoutManagerH.setMeasuredDimension(200, 150);

        recyclerViewH.setLayoutManager(layoutManagerH);

        hAdapter = new HAdapter(this);
        recyclerViewH.setAdapter(hAdapter);

        /*recyclerViewH.setOnScrollListener(new RecyclerView.OnScrollListener() {

            private boolean scrollingRight;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                super.onScrollStateChanged(recyclerView, newState);

                // Make sure scrolling has stopped before snapping
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {

                    // layoutManager is the recyclerview's layout manager which you need to have reference in advance
                    int visiblePosition = scrollingRight ? layoutManagerH.findFirstVisibleItemPosition()
                            : layoutManagerH.findLastVisibleItemPosition();

                    int completelyVisiblePosition = scrollingRight ? layoutManagerH.findFirstCompletelyVisibleItemPosition()
                            : layoutManagerH.findLastCompletelyVisibleItemPosition();

                    // Check if we need to snap
                    if (visiblePosition != completelyVisiblePosition) {

                        recyclerView.smoothScrollToPosition(visiblePosition);
                        return;
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                super.onScrolled(recyclerView, dx, dy);
                scrollingRight = dx < 0;
            }
        });*/
    }

    public static class HAdapter extends RecyclerView.Adapter<HAdapter.HViewHolder> {

        private Activity activity;

        private LayoutInflater layoutInflater;

        private ArrayList<HEntity> hEntities = new ArrayList<HEntity>();

        public HAdapter(Activity activity) {

            this.activity = activity;

            layoutInflater = LayoutInflater.from(activity);
        }

        public void setData(ArrayList<HEntity> hEntities) {

            this.hEntities = hEntities;
            notifyDataSetChanged();
        }

        @Override
        public HViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = layoutInflater.inflate(R.layout.item_h, parent, false);

            HViewHolder viewHolder = new HViewHolder(view);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(HViewHolder holder, int position) {

            HEntity hEntity = hEntities.get(position);

            holder.textView.setText(hEntity.getName());
            holder.vAdapter.setData(hEntity.getChildren());
        }

        @Override
        public int getItemCount() {

            return hEntities.size();
        }

        public static class HViewHolder extends RecyclerView.ViewHolder {

            public TextView textView;
            public RecyclerView recyclerView;

            private final VAdapter vAdapter;

            public HViewHolder(View view) {

                super(view);

                textView = (TextView) view.findViewById(R.id.textView);

                recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewV);

                final LinearLayoutManager layoutManagerV = new SnappyLinearLayoutManager(view.getContext());
                layoutManagerV.setOrientation(LinearLayoutManager.VERTICAL);
                //layoutManagerH.setMeasuredDimension(200, 150);

                recyclerView.setLayoutManager(layoutManagerV);

                vAdapter = new VAdapter((Activity) view.getContext());
                recyclerView.setAdapter(vAdapter);

                /*recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

                    private boolean scrollingUp;

                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                        super.onScrollStateChanged(recyclerView, newState);

                        // Make sure scrolling has stopped before snapping
                        if (newState == RecyclerView.SCROLL_STATE_IDLE) {

                            // layoutManager is the recyclerview's layout manager which you need to have reference in advance
                            int visiblePosition = scrollingUp ? layoutManagerV.findFirstVisibleItemPosition()
                                                              : layoutManagerV.findLastVisibleItemPosition();

                            int completelyVisiblePosition = scrollingUp ? layoutManagerV.findFirstCompletelyVisibleItemPosition()
                                                                        : layoutManagerV.findLastCompletelyVisibleItemPosition();

                            // Check if we need to snap
                            if (visiblePosition != completelyVisiblePosition) {

                                recyclerView.smoothScrollToPosition(visiblePosition);
                                return;
                            }
                        }
                    }

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                        super.onScrolled(recyclerView, dx, dy);
                        scrollingUp = dy < 0;
                    }
                });*/
            }
        }
    }

    public static class VAdapter extends RecyclerView.Adapter<VAdapter.VViewHolder> {

        private Activity activity;

        private LayoutInflater layoutInflater;

        private ArrayList<VEntity> vEntities = new ArrayList<VEntity>();

        public VAdapter(Activity activity) {

            this.activity = activity;

            layoutInflater = LayoutInflater.from(activity);
        }

        public void setData(ArrayList<VEntity> entities) {

            this.vEntities = entities;
            notifyDataSetChanged();
        }

        @Override
        public VViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = layoutInflater.inflate(R.layout.item_v, parent, false);

            VViewHolder viewHolder = new VViewHolder(view);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(VViewHolder holder, int position) {

            VEntity hEntity = vEntities.get(position);

            holder.textView.setText(hEntity.getName());
        }

        @Override
        public int getItemCount() {

            return vEntities.size();
        }

        public static class VViewHolder extends RecyclerView.ViewHolder {

            public TextView textView;

            public VViewHolder(View view) {

                super(view);

                textView = (TextView) view.findViewById(R.id.textView);
            }
        }
    }

    private ArrayList<HEntity> getSampleData() {

        ArrayList<HEntity> hEntities = new ArrayList<HEntity>();

        for (int i = 0; i < 12 ; i++) {

            HEntity hEntity = new HEntity();
            hEntity.setName("" + i);

            ArrayList<VEntity> vEntities = new ArrayList<VEntity>();

            for (int j = 0; j < 15; j++) {

                VEntity vEntity = new VEntity();
                vEntity.setName(i + " # " + j);

                vEntities.add(vEntity);
            }

            hEntity.setChildren(vEntities);

            hEntities.add(hEntity);
        }

        return hEntities;
    }

    public static class HEntity {

        private String name;

        private ArrayList<VEntity> children = new ArrayList<VEntity>();

        public String getName() {

            return name;
        }

        public void setName(String name) {

            this.name = name;
        }

        public ArrayList<VEntity> getChildren() {

            return children;
        }

        public void setChildren(ArrayList<VEntity> children) {

            this.children = children;
        }
    }

    public static class VEntity {

        private String name;

        public String getName() {

            return name;
        }

        public void setName(String name) {

            this.name = name;
        }
    }
}
