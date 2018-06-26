package lxc.nsu.edu.com.ssft.chapter03.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lxc.nsu.edu.com.ssft.chapter02.entity.ContactInfo;

/**
 * Created by 123 on 2017/7/11.
 */

public class ContactInfoParser {
    public static List<ContactInfo> getSystemContact(Context context) {
        ContentResolver resolver = context.getContentResolver();
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Uri datauri = Uri.parse("content://com.android.contacts/data");
        List<ContactInfo> infos = new ArrayList<ContactInfo>();
        Cursor cursor = resolver.query(uri, new String[]{"contact_id"}, null, null, null);
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            if (id!=null) {
                System.out.print("联系人id："+id);
                ContactInfo info = new ContactInfo();
                info.id = id;
                Cursor dataCursor = resolver.query(datauri, new String[]{"data1","mimetype"}, "raw_contact_id=?", new String[]{id}, null);
                while (dataCursor.moveToNext()) {
                    String data1 = dataCursor.getString(0);
                    String mimetype = dataCursor.getString(1);
                    if ("vnd.android.cursor.item/name".equals(mimetype)) {
                        System.out.println("姓名="+data1);
                        info.name = data1;
                    }else if ("vnd.android.cursor.item/phone_v2".equals(mimetype)) {
                        System.out.println("电话="+data1);
                        info.phone = data1;
                    }
                }
                infos.add(info);
                dataCursor.close();
            }
        }
        cursor.close();
        return infos;
    }

    public static String numFormat(String num) {
        Pattern pattern = Pattern.compile("[^0-9]+");
        Matcher matcher = pattern.matcher(num);
        num = matcher.replaceAll("");
        System.out.println(num);
        if (num.charAt(0)!='1') {
            num = num.substring(num.length()-11);
        }
        return num;
    }
}