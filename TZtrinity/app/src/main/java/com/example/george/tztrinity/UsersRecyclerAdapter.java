package com.example.george.tztrinity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.george.tztrinity.model.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by George on 08.08.2018.
 */

public class UsersRecyclerAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private Context mContext;
    private List<User> users;
    private Date currentDate;
    private String dates;
    SimpleDateFormat dateFormat;

    public UsersRecyclerAdapter(Context context, List<User> users) {
        mContext = context;
        this.users = users;
        currentDate = new Date();
        dateFormat = new SimpleDateFormat();
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {


        User user = users.get(position);
        holder.name.setText(user.getName());
        holder.age.setText(String.valueOf(user.getAge()));
        holder.sim.setText(String.valueOf(user.getSimilarity()));
        holder.unread.setText(String.valueOf(user.getUnreadMessages()));


        String string = user.getLastSeen().toString();  // получает серверное время
        DateFormat format = new SimpleDateFormat("MM.dd.yyyy hh:mm", Locale.ENGLISH);
        try {
            Date date = format.parse(string);
            GetTimeAgo getTimeAgo = new GetTimeAgo();
            long lastTime = date.getTime(); // получает время в миллисекундах
            String lastSeenTime = getTimeAgo.getTimeAgo(lastTime);  // получает готовую строку
            holder.last.setText(lastSeenTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }



        Glide.with(mContext)
                .load(user.getAvatar())
                .into(holder.avatar);


        switch (user.getStatus()) {
            case "dnd":
                holder.status.setImageResource(R.color.dnd);
                break;
            case "away":
                holder.status.setImageResource(R.color.away);
                break;
            default:
                holder.status.setImageResource(R.color.online);
                break;
        }


        if (user.getSimilarity()<40) {
            holder.sim.setTextColor(mContext.getResources().getColor(R.color.dnd));
        } else if ( user.getSimilarity()>40&& user.getSimilarity()<70) {
            holder.sim.setTextColor(mContext.getResources().getColor(R.color.away));
        } else if (user.getSimilarity()>70){
            holder.sim.setTextColor(mContext.getResources().getColor(R.color.online));
        }

    }

    @Override
    public int getItemCount() {
        return users.size();
    }


}
class UserViewHolder extends RecyclerView.ViewHolder {
    TextView name, age, last, sim, unread;
    ImageView avatar, status;

    public UserViewHolder(View itemView) {
        super(itemView);

        avatar = (ImageView) itemView.findViewById(R.id.avatar);
        status = (ImageView) itemView.findViewById(R.id.status);

        name = (TextView) itemView.findViewById(R.id.name);
        age = (TextView) itemView.findViewById(R.id.old);
        last = (TextView) itemView.findViewById(R.id.last_seen);
        sim = (TextView) itemView.findViewById(R.id.similar);
        unread = (TextView) itemView.findViewById(R.id.unread);
    }
}
