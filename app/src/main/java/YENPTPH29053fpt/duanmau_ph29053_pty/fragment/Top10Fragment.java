package YENPTPH29053fpt.duanmau_ph29053_pty.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import YENPTPH29053fpt.duanmau_ph29053_pty.DAO.ThongKeDao;
import YENPTPH29053fpt.duanmau_ph29053_pty.DTO.Top;
import YENPTPH29053fpt.duanmau_ph29053_pty.R;
import YENPTPH29053fpt.duanmau_ph29053_pty.adapter.TopAdapter;



public class Top10Fragment extends Fragment {
    ListView lv;
    ArrayList<Top> list;
    TopAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_10, container, false);
        lv = view.findViewById(R.id.lvTop);
        ThongKeDao thongKeDao = new ThongKeDao(getActivity());
        list = (ArrayList<Top>) thongKeDao.getTop();
        adapter = new TopAdapter(getActivity(), this, list);
        lv.setAdapter(adapter);
        return view;
    }
}
