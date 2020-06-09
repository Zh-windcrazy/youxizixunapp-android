package cn.edu.scujcc.youxizixun;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class GameRvAdapter extends RecyclerView.Adapter<GameRvAdapter.GameRowHolder> {
    private GameLab lab = GameLab.getInstance();
    private GameClickListener listener;
    private Context context;

    public GameRvAdapter(Context context, GameClickListener lis) {
        this.context = context;
        this.listener = lis;
    }

    /**
     * 当需要新的一行时，此方法负责创建这一行对应的对象，即ChannelRowHolder对象。
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public GameRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_row, parent, false);
        GameRowHolder holder = new GameRowHolder(rowView);
        return holder;
    }

    /**
     * 用于确定列表总共有几行(即多少个ChannelRowHolder对象）
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return lab.getSize();
    }

    /**
     * 用于确定每一行的内容是什么，即填充行中各个视图的内容。
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull GameRowHolder holder, int position) {
        Game c = lab.getGame(position);
        holder.bind(c);
    }

    //自定义新接口
    public interface GameClickListener {
        public void onGameClick(int position);
    }

    /**
     * 单行布局对应的Java控制类
     */
    public class GameRowHolder extends RecyclerView.ViewHolder {
        private TextView title; //频道标题
        private TextView price; //频道清晰度
        private ImageView cover;

        public GameRowHolder(@NonNull View row) {
            super(row);
            this.title = row.findViewById(R.id.game_title);
            this.price = row.findViewById(R.id.game_price);
            this.cover = row.findViewById(R.id.game_cover);
            row.setOnClickListener((v) -> {
                int position = getLayoutPosition();
                Log.d("Game", position + "行被点击啦！");
                listener.onGameClick(position);
            });
        }

        /**
         * 自定义方法，用于向内部的title提供数据
         */
        public void bind(Game c) {
            this.title.setText(c.getTitle());
            this.price.setText(c.getPrice());
            //弃用本地图片的方式，改为从网络加载图片
//            this.cover.setImageResource(c.getCover());
            Log.d("Game", c.getTitle() + "：准备从网络加载封面：" + c.getCover());
            Glide.with(context)
                    .load(c.getCover())
                    .into(this.cover);
        }
    }
}
