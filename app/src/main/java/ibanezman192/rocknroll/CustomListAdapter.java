package ibanezman192.rocknroll;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by John on 4/1/2018.
 */

public class CustomListAdapter extends ArrayAdapter<Producer> {
    private final Activity context;
    private final Producer[] producers;

    public CustomListAdapter(Activity context, Producer[] producers) {
        super(context, R.layout.row, producers);

        this.context = context;
        this.producers = producers;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(R.layout.row, parent, false);
            holder = new ViewHolder();
            holder.tvName = (TextView) convertView.findViewById(R.id.name);
            holder.ivIcon = (ImageView) convertView.findViewById(R.id.icon);
            holder.tvShow = (TextView) convertView.findViewById(R.id.show);
            holder.tvShowHours = (TextView) convertView.findViewById(R.id.showHours);
            holder.tvBio = (TextView) convertView.findViewById(R.id.bio);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        int resourceId = context.getResources().
                getIdentifier(producers[position].getimageId(), "drawable", context.getPackageName());

        holder.ivIcon.setImageResource(resourceId);
        holder.tvName.setText(producers[position].getName());
        holder.tvShow.setText(producers[position].getShow());
        holder.tvShowHours.setText(producers[position].getshowHours());
        holder.tvBio.setText("Tap for more info");
        return convertView;

    }

    static class ViewHolder {
        private TextView tvName;
        private ImageView ivIcon;
        private TextView tvShow;
        private TextView tvShowHours;
        private TextView tvBio;
    }
}
