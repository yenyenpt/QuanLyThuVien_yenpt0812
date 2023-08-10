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

import YENPTPH29053fpt.duanmau_ph29053_pty.DAO.LoaiSachDao;
import YENPTPH29053fpt.duanmau_ph29053_pty.DTO.LoaiSach;
import YENPTPH29053fpt.duanmau_ph29053_pty.DTO.Sach;
import YENPTPH29053fpt.duanmau_ph29053_pty.R;
import YENPTPH29053fpt.duanmau_ph29053_pty.fragment.SachFragment;



public class SachAdapter extends ArrayAdapter<Sach> {
    private Context context;
    SachFragment fragment;
    private ArrayList<Sach> list;
    TextView tvMaSach, tvTenSach, tvGiaThue, tvLoai;
    ImageView imgDel;

    public SachAdapter(@NonNull Context context, SachFragment fragment, ArrayList<Sach> list) {
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
            view = inflater.inflate(R.layout.layout_item_sach, null);
        }
        final Sach item = list.get(position);
        if (view!= null){
            LoaiSachDao loaiSachDao = new LoaiSachDao(context);
            LoaiSach loaiSach = loaiSachDao.getID( String.valueOf(item.maLoai));
            tvMaSach = view.findViewById(R.id.tvMaSach);
            tvTenSach = view.findViewById(R.id.tvTenSach);
            tvGiaThue = view.findViewById(R.id.tvGiaThue);
            tvLoai = view.findViewById(R.id.tvLoai);
            imgDel = view.findViewById(R.id.imgDeleteSach);

            tvMaSach.setText("Mã sách: "+item.maSach);
            tvTenSach.setText("Tên sách: "+item.tenSach);
            tvGiaThue.setText("Giá thuê: "+item.giaThue);
            tvLoai.setText("Loại sách: "+loaiSach.tenLoai);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.maSach));
            }
        });
        return view;
    }
}
