package YENPTPH29053fpt.duanmau_ph29053_pty.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import YENPTPH29053fpt.duanmau_ph29053_pty.DTO.ThanhVien;
import YENPTPH29053fpt.duanmau_ph29053_pty.DbHelper.DbHelper;


public class ThanhVienDao {
    SQLiteDatabase db;

    public ThanhVienDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insertThanhVien(ThanhVien obj){
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.getHoTen());
        values.put("namSinh", obj.getNamSinh());
        return db.insert("ThanhVien", null, values);
    }
    public int updateThanhVien( ThanhVien obj){
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.getHoTen());
        values.put("namSinh", obj.getNamSinh());
        return db.update("ThanhVien", values, "maTV=?", new String[]{ obj.getMaTV()+ ""});
    }
    public int deleteThanhVien( String id){
        return db.delete("ThanhVien", "maTV=?", new String[]{ id });
    }

    @SuppressLint("Range")
    private List< ThanhVien> getData(String sql, String...selectionArgs){
        List<ThanhVien> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext() ){
            ThanhVien obj = new ThanhVien();
            obj.maTV = Integer.parseInt(c.getString( c.getColumnIndex("maTV")));
            obj.hoTen = c.getString( c.getColumnIndex("hoTen"));
            obj.namSinh = c.getString( c.getColumnIndex("namSinh"));
            list.add(obj);
        }
        return list;
    }

    public List<ThanhVien> getAll(){
        String sql = "select * from ThanhVien";
        return getData(sql);
    }

    public ThanhVien getID(String id){
        String sql = "select * from ThanhVien where maTV=?";
        List<ThanhVien> list = getData(sql, id);
        return list.get(0);
    }
}
