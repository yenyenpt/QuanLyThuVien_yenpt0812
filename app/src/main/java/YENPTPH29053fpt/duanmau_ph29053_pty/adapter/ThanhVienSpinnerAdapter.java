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

import YENPTPH29053fpt.duanmau_ph29053_pty.DTO.ThanhVien;
import YENPTPH29053fpt.duanmau_ph29053_pty.R;



public class ThanhVienSpinnerAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    private ArrayList<ThanhVien> list;
    TextView tvMaTV, tvTenTV;

    public ThanhVienSpinnerAdapter(@NonNull Context context, ArrayList<ThanhVien> list) {
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
            view = inflater.inflate(R.layout.thanh_vien_item_spinner, null);

        }
        final ThanhVien item = list.get(position);
        if (view != null){
            tvMaTV = view.findViewById(R.id.tvMaTVSp);
            tvTenTV = view.findViewById(R.id.tvTenTVSp);

            tvMaTV.setText( item.maTV +". ");
            tvTenTV.setText( item.hoTen);
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.thanh_vien_item_spinner, null);

        }
        final ThanhVien item = list.get(position);
        if (view != null){
            tvMaTV = view.findViewById(R.id.tvMaTVSp);
            tvTenTV = view.findViewById(R.id.tvTenTVSp);

            tvMaTV.setText( item.maTV +". ");
            tvTenTV.setText( item.hoTen);
        }
        return view;
    }
}
