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

import YENPTPH29053fpt.duanmau_ph29053_pty.DTO.LoaiSach;
import YENPTPH29053fpt.duanmau_ph29053_pty.R;
import YENPTPH29053fpt.duanmau_ph29053_pty.fragment.LoaiSachFragment;


public class LoaiSachAdapter extends ArrayAdapter<LoaiSach> {
    private Context context;
    LoaiSachFragment fragment;
    private ArrayList<LoaiSach> list;
    TextView tvMaLoai, tvTenLoai;
    ImageView imgDel;

    public LoaiSachAdapter(@NonNull Context context, LoaiSachFragment fragment, ArrayList<LoaiSach> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
        this.fragment =fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_item_loai_sach, null);

        }
        final LoaiSach item = list.get(position);
        if (item != null){
            tvMaLoai = view.findViewById(R.id.tvMaLoaiSach);
            tvTenLoai = view.findViewById(R.id.tvTenLoaiSach);
            imgDel = view.findViewById(R.id.imgDeleteLS);
            tvMaLoai.setText("Mã loại: "+item.maLoai);
            tvTenLoai.setText("Tên loại: "+item.tenLoai);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.xoa(String.valueOf(item.maLoai));
            }
        });
        return view;
    }
}
