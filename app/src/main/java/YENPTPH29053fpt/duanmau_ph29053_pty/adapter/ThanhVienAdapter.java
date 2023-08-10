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

import YENPTPH29053fpt.duanmau_ph29053_pty.DTO.ThanhVien;
import YENPTPH29053fpt.duanmau_ph29053_pty.R;
import YENPTPH29053fpt.duanmau_ph29053_pty.fragment.ThanhVienFragment;


public class ThanhVienAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    ThanhVienFragment fragment;
    private ArrayList<ThanhVien> list;
    TextView tvMaTV, tvTenTV, tvNamSinh;
    ImageView imgDel;

    public ThanhVienAdapter(@NonNull Context context, ThanhVienFragment fragment, ArrayList<ThanhVien> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_item_thanh_vien, null);
        }
        final ThanhVien item = list.get(position);
        if ( item != null){
            tvMaTV = view.findViewById(R.id.tvMaTV);
            tvTenTV = view.findViewById(R.id.tvTenTV);
            tvNamSinh = view.findViewById(R.id.tvNamSinh);
            imgDel = view.findViewById(R.id.imgDeleteTV);

            tvMaTV.setText("Mã thành viên: "+item.maTV);
            tvTenTV.setText("Tên thành viên: "+item.hoTen);
            tvNamSinh.setText("Năm sinh: "+item.namSinh);

        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.maTV));
            }
        });
        return view;
    }
}
