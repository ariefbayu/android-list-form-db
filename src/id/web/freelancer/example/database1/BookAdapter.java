package id.web.freelancer.example.database1;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BookAdapter extends BaseAdapter {
	private ArrayList<Book> listBook = null;
	private static LayoutInflater inflater = null;

	public BookAdapter(Activity activity) {
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void updateListBook(ArrayList<Book> newListBook) {
		this.listBook = newListBook;
	}

	@Override
	public int getCount() {
		return this.listBook.size();
	}

	@Override
	public Book getItem(int position) {
		return this.listBook.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup viewGroup) {
		View vi = view;
		if (vi == null) {
			vi = inflater.inflate(R.layout.list, null);
		}

		Book book = this.listBook.get(position);

		TextView txtISBN = (TextView) vi.findViewById(R.id.txtListISBN);
		TextView txtListJudul = (TextView) vi.findViewById(R.id.txtListJudulBuku);

		txtListJudul.setText(book.getJudul());
		txtISBN.setText(book.getISBN());

		return vi;
	}

}
