package com.gl.tmdb.content.model;

import android.database.Cursor;

import com.gl.tmdb.content.local.DataContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents basic Person information as returned in a list of people.
 */
public class PersonItem extends PersonBase {

    public static List<PersonItem> fromCursor(Cursor cursor) {
        if (cursor != null && cursor.moveToFirst()) {
            List<PersonItem> personItems = new ArrayList<>(cursor.getCount());
            do {
                PersonItem item = new PersonItem();
                item.id = cursor.getInt(cursor.getColumnIndex(DataContract.PersonItems.ID));
                item.name = cursor.getString(cursor.getColumnIndex(DataContract.PersonItems.NAME));
                item.profilePath = cursor.getString(cursor.getColumnIndex(DataContract.PersonItems.PROFILE_PATH));
                //TODO
                personItems.add(item);
            } while (cursor.moveToNext());
            return personItems;
        }
        return null;
    }


}
