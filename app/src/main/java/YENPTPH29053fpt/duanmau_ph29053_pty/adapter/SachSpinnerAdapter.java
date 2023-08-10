package YENPTPH29053fpt.duanmau_ph29053_pty.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import YENPTPH29053fpt.duanmau_ph29053_pty.DTO.Sach;
import YENPTPH29053fpt.duanmau_ph29053_pty.R;


public class SachSpinnerAdapter extends ArrayAdapter<Sach> {
    private Context context;
    private ArrayList<Sach> list;
    TextView tvMaSach, tvTenSach;

    public SachSpinnerAdapter(@NonNull Context context, ArrayList<Sach> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.sach_item_spinner, null);
        }
        final Sach item = list.get(position);
        if ( view != null ){
            tvMaSach = view.findViewById(R.id.tvMaSachSp);
            tvTenSach = view.findViewById(R.id.tvTenSachSp);

            tvMaSach.setText(item.maSach +". ");
            tvTenSach.setText(item.tenSach);
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.sach_item_spinner, null);
        }
        final Sach item = list.get(position);
        if ( view != null ){
            tvMaSach = view.findViewById(R.id.tvMaSachSp);
            tvTenSach = view.findViewById(R.id.tvTenSachSp);

            tvMaSach.setText(item.maSach +". ");
            tvTenSach.setText(item.tenSach);
        }
        return view;
    }
}
