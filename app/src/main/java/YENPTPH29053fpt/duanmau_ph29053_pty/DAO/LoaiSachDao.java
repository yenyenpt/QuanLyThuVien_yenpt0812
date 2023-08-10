package YENPTPH29053fpt.duanmau_ph29053_pty.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import YENPTPH29053fpt.duanmau_ph29053_pty.DTO.LoaiSach;
import YENPTPH29053fpt.duanmau_ph29053_pty.DbHelper.DbHelper;


public class LoaiSachDao {
    SQLiteDatabase db;

    public LoaiSachDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insertLoaiSach(LoaiSach obj){
        ContentValues values = new ContentValues();
        values.put("tenLoai", obj.getTenLoai());
        return db.insert("LoaiSach", null, values);
    }
    public int updateLoaiSach( LoaiSach obj){
        ContentValues values = new ContentValues();
        values.put("tenLoai", obj.getTenLoai());
        return db.update("LoaiSach", values, "maLoai=?", new String[]{ obj.getMaLoai()+ ""});
    }
    public int deleteLoaiSach( String id){
        return db.delete("LoaiSach", "maLoai=?", new String[]{ id});
    }

    @SuppressLint("Range")
    private List< LoaiSach> getData(String sql, String...selectionArgs){
        List<LoaiSach> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext() ){
            LoaiSach obj = new LoaiSach();
            obj.maLoai = Integer.parseInt(c.getString( c.getColumnIndex("maLoai")));
            obj.tenLoai = c.getString( c.getColumnIndex("tenLoai"));
            list.add(obj);
        }
        return list;
    }

    public List<LoaiSach> getAll(){
        String sql = "select * from LoaiSach";
        return getData(sql);
    }

    public LoaiSach getID(String id){
        String sql = "select * from LoaiSach where maLoai=?";
        List<LoaiSach> list = getData(sql, id);
        return list.get(0);
    }
}
