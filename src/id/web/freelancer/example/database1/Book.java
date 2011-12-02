package id.web.freelancer.example.database1;

public class Book {
	private String judul = "";
	private String ISBN = "";
	private String namaPenulis = "";
	public void setJudul(String judul) {
		this.judul = judul;
	}
	public String getJudul() {
		return judul;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setNamaPenulis(String namaPenulis) {
		this.namaPenulis = namaPenulis;
	}
	public String getNamaPenulis() {
		return namaPenulis;
	}
	
	public String toString(){
		return "ISBN: " + getISBN() + "; Judul: " + getJudul() + "; Penulis: " + getNamaPenulis();
	}
}
