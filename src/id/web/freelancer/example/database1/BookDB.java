package id.web.freelancer.example.database1;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Book database object handler. This class will handle all insert, update, and
 * delete Book. Will also help fetch all Books
 * 
 * @author Arief Bayu Purwanto
 * 
 */
public class BookDB {
	private Context context = null;
	private SQLiteDatabase database = null;
	private static final String BOOK_NAME = "bookList";

	public BookDB(Context context) {
		this.context = context;
		database = this.context.openOrCreateDatabase("book.db",
				SQLiteDatabase.CREATE_IF_NECESSARY, null);
		String sqlCreate = "CREATE TABLE IF NOT EXISTS " + BOOK_NAME + " ("
				+ "isbn TEXT PRIMARY KEY," + "judul TEXT,"
				+ "nama_penulis TEXT" + ");";
		DBUtil.createTable(database, BOOK_NAME, sqlCreate);
	}

	public ArrayList<Book> toArray() {
		Cursor cursor = null;
		ArrayList<Book> allBook = null;

		try {
			allBook = new ArrayList<Book>();

			cursor = database.rawQuery("SELECT * FROM " + BOOK_NAME, null);
			if (cursor.getCount() > 0) {
				int indexISBN = cursor.getColumnIndex("isbn");
				int indexJudul = cursor.getColumnIndex("judul");
				int indexNama = cursor.getColumnIndex("nama_penulis");
				cursor.moveToFirst();
				do {
					String judul = cursor.getString(indexJudul);
					String namaPenulis = cursor.getString(indexNama);
					String ISBN = cursor.getString(indexISBN);

					Book book = new Book();
					book.setJudul(judul);
					book.setNamaPenulis(namaPenulis);
					book.setISBN(ISBN);

					allBook.add(book);

					cursor.moveToNext();
				} while (!cursor.isAfterLast());
			}
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		return allBook;
	}

	public boolean insert(Book book) {
		try {
			database.beginTransaction();

			String insertSQL = "INSERT INTO " + BOOK_NAME
					+ " (isbn, judul, nama_penulis) VALUES(?,?,?)";
			database.execSQL(
					insertSQL,
					new Object[] { book.getISBN(), book.getJudul(),
							book.getNamaPenulis() });

			database.setTransactionSuccessful();
		} finally {
			database.endTransaction();
		}
		return true;
	}

	public boolean update(Book book) {
		try {
			database.beginTransaction();

			String updateSQL = "UPDATE " + BOOK_NAME
					+ " SET judul = ?, nama_penulis = ? " + " WHERE isbn = ?";
			database.execSQL(
					updateSQL,
					new Object[] { book.getJudul(), book.getNamaPenulis(),
							book.getISBN() });

			database.setTransactionSuccessful();
		} finally {
			database.endTransaction();
		}
		return true;
	}

	public boolean delete(Book book) {
		try {
			database.beginTransaction();

			// delete this record
			String delete = "DELETE FROM " + BOOK_NAME + " WHERE isbn='"
					+ book.getISBN() + "'";
			database.execSQL(delete);

			database.setTransactionSuccessful();
		} finally {
			database.endTransaction();
		}
		return true;
	}

	public Book get(String isbn) {
		Book returnBook = null;
		Cursor cursor = null;

		try {

			cursor = database.rawQuery("SELECT * FROM " + BOOK_NAME
					+ " WHERE isbn = '" + isbn + "'", null);
			if (cursor.getCount() > 0) {
				int indexISBN = cursor.getColumnIndex("isbn");
				int indexJudul = cursor.getColumnIndex("judul");
				int indexNama = cursor.getColumnIndex("nama_penulis");
				cursor.moveToFirst();
				do {
					String judul = cursor.getString(indexJudul);
					String namaPenulis = cursor.getString(indexNama);
					String ISBN = cursor.getString(indexISBN);

					returnBook = new Book();
					returnBook.setJudul(judul);
					returnBook.setNamaPenulis(namaPenulis);
					returnBook.setISBN(ISBN);

					cursor.moveToNext();
				} while (!cursor.isAfterLast());
			}
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}

		return returnBook;
	}
}
