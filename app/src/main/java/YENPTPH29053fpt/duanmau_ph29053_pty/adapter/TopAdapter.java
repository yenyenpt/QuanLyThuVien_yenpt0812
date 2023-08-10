package YENPTPH29053fpt.duanmau_ph29053_pty.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import YENPTPH29053fpt.duanmau_ph29053_pty.DTO.Top;
import YENPTPH29053fpt.duanmau_ph29053_pty.R;
import YENPTPH29053fpt.duanmau_ph29053_pty.fragment.Top10Fragment;


public class TopAdapter extends ArrayAdapter<Top> {
    private Context context;
    Top10Fragment fragment;
    private ArrayList<Top> list;
    TextView tvSach, tvSL;
    ImageView imgDel;

    public TopAdapter(@NonNull Context context, Top10Fragment fragment, ArrayList<Top> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view== null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_item_top, null);
        }
        final Top item = list.get(position);
        if (item != null){
            tvSach = view.findViewById(R.id.tvSach);
            tvSL = view.findViewById(R.id.tvSL);
            tvSach.setText("Sách: "+item.tenSach);
            tvSL.setText("Số lượng: "+item.soLuong);
        }
        return view;
    }
}
