package cn.itcast.librarydemo.adapter;

import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Book_inventory extends BmobObject  {

    BmobFile headImage;
    private String Bookname;
    private Integer inventory;
    private String id;
    public Book_inventory(){
        setTableName("Book_inventory");

    }

    public BmobFile getHeadImage() {
        return headImage;
    }

    public void setHeadImage(BmobFile headImageFile) {
        this.headImage = headImageFile;
    }
    public String getBookname() {
        return Bookname;
    }

    public void setBookname(String Bookname) {
        this.Bookname = Bookname;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public String getId() {
        return Bookname;
    }

    public void setId(String id) {
        this.id = id;
    }


    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}



