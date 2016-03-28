package com.lanou.tong.fun.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lanou.tong.fun.R;
import com.lanou.tong.fun.bean.TopicContentBean;


/**
 * Created by zt on 2016/3/15.
 */
public class TopicContentAdapter extends RecyclerView.Adapter {
    final int TYPE_0 = 0;
    final int TYPE_1 = 1;
    final int TYPE_2 = 2;
    private TopicContentBean data;
    private Context context;
    int type;

    public TopicContentAdapter(TopicContentBean data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_0;
        } else if (position == 1) {
            return TYPE_1;
        } else {
            return TYPE_2;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case TYPE_0:
                // 类型0 个人信息
                View viewPer = LayoutInflater.from(context).inflate(R.layout.topic_content_item_per, null);
                viewHolder = new PersonalViewHolder(viewPer);
                break;
            case TYPE_1:
                // 类型1 数据状态
                View viewState = LayoutInflater.from(context).inflate(R.layout.topic_content_item_states, null);
                viewHolder = new StatesViewHolder(viewState);
                break;
            case TYPE_2:
                // 类型3 问题回复
                View viewQues = LayoutInflater.from(context).inflate(R.layout.topic_content_item_question, null);
                viewHolder = new QuestionViewHolder(viewQues);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        type = getItemViewType(position);
        switch (type) {
            case TYPE_0:
                PersonalViewHolder personalViewHolder = (PersonalViewHolder) holder;
                GenericDraweeHierarchy hierarchy = GenericDraweeHierarchyBuilder.newInstance(Resources.getSystem()).setRoundingParams(RoundingParams.asCircle()).build();
                personalViewHolder.perInfoHeadPic.setImageURI(Uri.parse(data.getData().getExpert().getHeadpicurl()));
                personalViewHolder.perInfoHeadPic.setHierarchy(hierarchy);
                personalViewHolder.perInfoNameTv.setText(data.getData().getExpert().getName());
                personalViewHolder.perInfoJobTv.setText(data.getData().getExpert().getTitle());
                personalViewHolder.perInfoDetailTv.setText("        " + data.getData().getExpert().getDescription());
                break;
            case TYPE_1:
                StatesViewHolder statesViewHolder = (StatesViewHolder) holder;
                statesViewHolder.questionNumTv.setText(data.getData().getExpert().getQuestionCount() + "提问");
                statesViewHolder.answerNumTv.setText(data.getData().getExpert().getAnswerCount() + "回复");
                break;
            case TYPE_2:
                QuestionViewHolder questionViewHolder = (QuestionViewHolder) holder;
                questionViewHolder.questionHeadIv.setImageURI(Uri.parse(data.getData().getHotList().get(position).getQuestion().getUserHeadPicUrl()));
                questionViewHolder.questionNameTv.setText(data.getData().getHotList().get(position).getQuestion().getUserName());
                questionViewHolder.questionContentTv.setText("        " + data.getData().getHotList().get(position).getQuestion().getContent());
                questionViewHolder.replyHeadIv.setImageURI(Uri.parse(data.getData().getHotList().get(position).getAnswer().getSpecialistHeadPicUrl()));
                questionViewHolder.replyNameTv.setText(data.getData().getHotList().get(position).getAnswer().getSpecialistName());
                questionViewHolder.replyContentTv.setText("        " + data.getData().getHotList().get(position).getAnswer().getContent());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.getData().getHotList().size();
    }

    // 个人信息缓存类
    public class PersonalViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView perInfoHeadPic;
        TextView perInfoNameTv, perInfoJobTv, perInfoDetailTv;

        public PersonalViewHolder(View itemView) {
            super(itemView);
            perInfoHeadPic = (SimpleDraweeView) itemView.findViewById(R.id.topic_recycler_personal_headpic);
            perInfoNameTv = (TextView) itemView.findViewById(R.id.topic_recycler_personal_name);
            perInfoJobTv = (TextView) itemView.findViewById(R.id.topic_recycler_personal_job);
            perInfoDetailTv = (TextView) itemView.findViewById(R.id.topic_recycler_personal_introduce);
        }
    }

    // 数据缓存类
    public class StatesViewHolder extends RecyclerView.ViewHolder {
        TextView questionNumTv, answerNumTv, statesTv;

        public StatesViewHolder(View itemView) {
            super(itemView);
            questionNumTv = (TextView) itemView.findViewById(R.id.topic_recycler_states_questionnumber);
            answerNumTv = (TextView) itemView.findViewById(R.id.topic_recycler_states_answernumber);
            statesTv = (TextView) itemView.findViewById(R.id.topic_recycler_states_ing);
        }
    }

    // 问题回复 缓存类
    public class QuestionViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView questionHeadIv, replyHeadIv;
        TextView questionNameTv, questionContentTv, replyNameTv, replyContentTv;

        public QuestionViewHolder(View itemView) {
            super(itemView);
            questionHeadIv = (SimpleDraweeView) itemView.findViewById(R.id.topic_recycler_question_quehead);
            replyHeadIv = (SimpleDraweeView) itemView.findViewById(R.id.topic_recycler_question_anshead);
            questionNameTv = (TextView) itemView.findViewById(R.id.topic_recycler_question_quename);
            questionContentTv = (TextView) itemView.findViewById(R.id.topic_recycler_question_quebody);
            replyNameTv = (TextView) itemView.findViewById(R.id.topic_recycler_question_ansname);
            replyContentTv = (TextView) itemView.findViewById(R.id.topic_recycler_question_ansbody);
        }

    }
}
