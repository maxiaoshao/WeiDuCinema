package com.example.weiducinema.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.example.weiducinema.activity.WDDetailsActivity;
import com.example.weiducinema.bean.CommentBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * created by fxb
 * 2019/1/29 11:44
 */
public class ExAdapter extends BaseExpandableListAdapter {

    List<CommentBean> list;
    Context context;

    public ExAdapter(WDDetailsActivity wdDetailsActivity) {
        this.context = wdDetailsActivity;
        list = new ArrayList<>();
    }
    public void setData(List<CommentBean> result) {
        list = result;

    }
    @Override
    public int getGroupCount() {
        return list==null?0:list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.size();
    }



    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupView groupView;

        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.pupop_ping, null);
            groupView = new GroupView(convertView);
            convertView.setTag(groupView);
        } else {
            groupView = (GroupView) convertView.getTag();
        }
        groupView.sim.setImageURI(list.get(groupPosition).getCommentHeadPic());
        groupView.seller_cb.setText(list.get(groupPosition).getCommentUserName());
        groupView.seller_name_tv.setText(list.get(groupPosition).getCommentContent());
        return convertView;
    }

    //
    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        return null;
    }





    class GroupView{
        public TextView seller_cb;
        public TextView seller_name_tv;
        public SimpleDraweeView sim;
        public GroupView(View rootView) {
            this.seller_cb =  rootView.findViewById(R.id.ping_name);
            this.seller_name_tv = (TextView) rootView.findViewById(R.id.tt);
            this.sim = rootView.findViewById(R.id.ping_sim);
        }
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
