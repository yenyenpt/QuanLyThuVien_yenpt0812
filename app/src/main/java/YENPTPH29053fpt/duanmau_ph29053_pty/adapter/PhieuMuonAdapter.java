package YENPTPH29053fpt.duanmau_ph29053_pty.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import YENPTPH29053fpt.duanmau_ph29053_pty.DAO.SachDao;
import YENPTPH29053fpt.duanmau_ph29053_pty.DAO.ThanhVienDao;
import YENPTPH29053fpt.duanmau_ph29053_pty.DTO.PhieuMuon;
import YENPTPH29053fpt.duanmau_ph29053_pty.DTO.Sach;
import YENPTPH29053fpt.duanmau_ph29053_pty.DTO.ThanhVien;
import YENPTPH29053fpt.duanmau_ph29053_pty.R;
import YENPTPH29053fpt.duanmau_ph29053_pty.fragment.PhieuMuonFragment;


public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuon> {
    private Context context;
    PhieuMuonFragment fragment;
    private ArrayList<PhieuMuon> list;
    TextView tvMaPM, tvTenTV, tvTenSach, tvTienThue, tvNgay, tvTraSach;
    ImageView imgDel;
    ThanhVienDao thanhVienDao;

    SachDao sachDao;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public PhieuMuonAdapter(@NonNull Context context, PhieuMuonFragment fragment, ArrayList<PhieuMuon> list) {
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
            view = inflater.inflate(R.layout.layout_item_phieu_muon, null);
        }
        final PhieuMuon item = list.get(position);
        if (view!= null){
            tvMaPM = view.findViewById(R.id.tvMaPM);
            tvMaPM.setText("Mã phiếu: "+item.maPM);

            sachDao = new SachDao(context);
            Sach sach = sachDao.getID( String.valueOf(item.maSach));

            tvTenSach = view.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên sách: "+sach.tenSach);

            thanhVienDao = new ThanhVienDao(context);
            ThanhVien thanhVien = thanhVienDao.getID( String.valueOf( item.maTV));
            tvTenTV = view.findViewById(R.id.tvTenTV);
            tvTenTV.setText("Thành viên: "+thanhVien.hoTen);

            tvTienThue = view.findViewById(R.id.tvTienThue);
            tvTienThue.setText("Tiền thuê: "+item.tienThue);

            tvNgay = view.findViewById(R.id.tvNgayPM);
            tvNgay.setText("Ngày thuê: "+item.ngay);

            tvTraSach = view.findViewById(R.id.tvTraSach);
            if (item.traSach ==1){
                tvTraSach.setText("Đã trả sách");
                tvTraSach.setTextColor(Color.BLUE);
            }else {
                tvTraSach.setText("Chưa trả sách");
                tvTraSach.setTextColor(Color.RED);
            }

            imgDel = view.findViewById(R.id.imgDeletePM);

        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf( item.maPM ));
            }
        });
        return view;
    }
}
