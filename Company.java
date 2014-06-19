package Company.StockRate;

public class Company {
	private String name;
	private String year;
	private String month;
	private double price;

	public Company(String name, String year, String month, double price) {
		super();
		this.name = name;
		this.year = year;
		this.month = month;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}
