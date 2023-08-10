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

import YENPTPH29053fpt.duanmau_ph29053_pty.DTO.LoaiSach;
import YENPTPH29053fpt.duanmau_ph29053_pty.R;


public class LoaiSachSpinnerAdapter extends ArrayAdapter<LoaiSach> {
    private Context context;
    private ArrayList<LoaiSach> list;
    TextView tvMaLoaiSach, tvTenLoaiSach;

    public LoaiSachSpinnerAdapter(@NonNull Context context, ArrayList<LoaiSach> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.loai_sach_item_spinner, null);

        }
        final LoaiSach item = list.get(position);
        if (item!= null){
            tvMaLoaiSach = view.findViewById(R.id.tvMaLoaiSachSp);
            tvTenLoaiSach = view.findViewById(R.id.tvTenLoaiSachSp);
            tvMaLoaiSach.setText(item.maLoai+". ");
            tvTenLoaiSach.setText(item.tenLoai);
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.loai_sach_item_spinner, null);
        }
        final LoaiSach item = list.get(position);
        if (item!= null){
            tvMaLoaiSach = view.findViewById(R.id.tvMaLoaiSachSp);
            tvTenLoaiSach = view.findViewById(R.id.tvTenLoaiSachSp);
            tvMaLoaiSach.setText(item.maLoai+". ");
            tvTenLoaiSach.setText(item.tenLoai );
        }
        return view;
    }
}
