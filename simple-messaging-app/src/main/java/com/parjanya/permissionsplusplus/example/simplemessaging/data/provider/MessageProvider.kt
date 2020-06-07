package com.parjanya.permissionsplusplus.example.simplemessaging.data.provider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.CursorWrapper
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteQueryBuilder
import com.parjanya.permissionsplusplus.example.simplemessaging.data.MessageDatabase
import java.lang.RuntimeException
import java.lang.UnsupportedOperationException
import java.net.UnknownServiceException

class MessageProvider : ContentProvider() {

    companion object {
        const val AUTHORITY = "com.parjanya.permissionsplusplus.example.simplemessaging.MessageProvider"
        val URI_MESSAGES = Uri.parse("content://$AUTHORITY/${MessageDatabase.MESSAGE_TABLE_NAME}")

        private const val CODE_ALL_MESSAGES = 1
        private val MATCHER = UriMatcher(UriMatcher.NO_MATCH)

        init {
            MATCHER.addURI(AUTHORITY, MessageDatabase.MESSAGE_TABLE_NAME, CODE_ALL_MESSAGES)
        }

    }

    private lateinit var database: SupportSQLiteDatabase

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor {
        val queryBuilder = SupportSQLiteQueryBuilder.builder(MessageDatabase.MESSAGE_TABLE_NAME)
            .columns(projection)
            .selection(selection, selectionArgs)
            .orderBy(sortOrder)
        return database.query(queryBuilder.create())
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        throw UnsupportedOperationException("No one but myself is allowed to insert")
    }

    override fun onCreate(): Boolean {
        database = MessageDatabase.getDatabase(context!!).openHelper.writableDatabase

        return true
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        throw UnsupportedOperationException("No one but myself is allowed to update")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        throw UnsupportedOperationException("No one but myself is allowed to delete")
    }

    override fun getType(uri: Uri): String? {
        return when(MATCHER.match(uri)) {
            CODE_ALL_MESSAGES -> "vnd.android.cursor.dir/vnd.permissionsplusplus.example.messages"
            else -> throw UnknownServiceException("Unknown URI")
        }
    }


}