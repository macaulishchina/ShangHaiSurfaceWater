package com.sinoyd.Code.Until

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by shenchuanjiang on 2017/2/26.
 *
 *
 * 使用SharedPreferences存取删  数据
 */

object SharedPreferencesFactory {
    //存数据
    fun savedata(context: Context, key: String, info: String) {

        // 1.获得SharedPreferences对象
        val sp = context.getSharedPreferences(key, Context.MODE_PRIVATE)// 私有，不能追加
        // 2.获得Editor对象
        val et = sp.edit()
        // 3.存储数据
        et.putString(key, info)
        // 4.提交数据，将数据写在文件
        et.commit()


        //        if (info instanceof Serializable) {// obj必须实现Serializable接口，否则会出问题
        //            try {
        //                ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //                ObjectOutputStream oos = new ObjectOutputStream(baos);
        //                oos.writeObject(info);
        //                String string64 = new String(Base64.encode(baos.toByteArray(),
        //                        0));
        //                SharedPreferences.Editor editor = context.getSharedPreferences("userinfo", Context.MODE_PRIVATE).edit();
        //                editor.putString("userinfo", string64).commit();
        //            } catch (IOException e) {
        //                e.printStackTrace();
        //            }
        //
        //        } else {
        //            throw new IllegalArgumentException(
        //                    "the obj must implement Serializble");
        //        }


    }

    //取数据
    fun getdata(context: Context, key: String): String {
        val sp = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        val token = sp.getString(key, "")
        return if ("" != token) {
            token
        } else {
            ""
        }


        //        Userinfo info = null;
        //        try {
        //            String base64 = context.getSharedPreferences("userinfo", Context.MODE_PRIVATE).getString("userinfo", "");
        //            if (base64.equals("")) {
        //                return null;
        //            }
        //            byte[] base64Bytes = Base64.decode(base64.getBytes(), 1);
        //            ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
        //            ObjectInputStream ois = new ObjectInputStream(bais);
        //            info = (Userinfo) ois.readObject();
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //        }
        //        return info;


    }

    //清除数据
    fun cleardata(context: Context, key: String) {
        val sp = context.getSharedPreferences(key, Context.MODE_PRIVATE)
        sp.edit().clear().commit()
    }
}
