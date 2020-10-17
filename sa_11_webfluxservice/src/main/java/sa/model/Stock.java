package sa.model;

public class Stock {

	private long stockId;
	private int value;
	public Stock() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Stock(long stockId, int value) {
		super();
		this.stockId = stockId;
		this.value = value;
	}
	public long getStockId() {
		return stockId;
	}
	public void setStockId(long stockId) {
		this.stockId = stockId;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	
}
