package YENPTPH29053fpt.duanmau_ph29053_pty.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import YENPTPH29053fpt.duanmau_ph29053_pty.DAO.LoaiSachDao;
import YENPTPH29053fpt.duanmau_ph29053_pty.DTO.LoaiSach;
import YENPTPH29053fpt.duanmau_ph29053_pty.R;
import YENPTPH29053fpt.duanmau_ph29053_pty.adapter.LoaiSachAdapter;


public class LoaiSachFragment extends Fragment {
    ListView lv;
    ArrayList<LoaiSach> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaLoaiSach, edTenLoaiSach;
    Button btnLuu, btnHuy;

    static LoaiSachDao dao;
    LoaiSachAdapter adapter;
    LoaiSach item;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loai_sach, container, false);
        lv = view.findViewById(R.id.lvLoaiSach);
        fab = view.findViewById(R.id.fab_add_LoaiSach);
        dao = new LoaiSachDao(getActivity());
        capNhatLv();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity(), 0);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = list.get(i);
                openDialog(getActivity(), 1);
                return false;
            }
        });
        return view;
    }

    protected void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_loai_sach);

        edMaLoaiSach = dialog.findViewById(R.id.edMaLoaiSach);
        edTenLoaiSach = dialog.findViewById(R.id.edTenLoaiSach);
        btnHuy = dialog.findViewById(R.id.btnHuyLS);
        btnLuu = dialog.findViewById(R.id.btnLuuLS);
        //type = 0 là insert, = 1 là update
        edMaLoaiSach.setEnabled(false);
        if (type != 0){
            edMaLoaiSach.setText( String.valueOf(item.maLoai));
            edTenLoaiSach.setText( item.tenLoai );
        }
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item = new LoaiSach();
                item.tenLoai = edTenLoaiSach.getText().toString();
                if (validate() >0){
                    if (type == 0){
                        if ( dao.insertLoaiSach(item) >0){
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Thêm không thành công", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        item.maLoai = Integer.parseInt( edMaLoaiSach.getText().toString());
                        if (dao.updateLoaiSach(item) >0){
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Sửa không thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public void xoa(final String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có chắc chắn muốn xóa?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (dao.deleteLoaiSach(id) >0){
                    capNhatLv();
                    Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getContext(), "Xóa không thành công", Toast.LENGTH_SHORT).show();
                }
                dialogInterface.cancel();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        builder.show();
    }
    void capNhatLv(){
        list = (ArrayList<LoaiSach>) dao.getAll();
        adapter = new LoaiSachAdapter(getActivity(), this, list);
        lv.setAdapter(adapter);
    }
    public int validate(){
        int check = 1;
        if (edTenLoaiSach.getText().toString().length() == 0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}
