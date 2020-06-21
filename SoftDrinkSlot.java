public class SoftDrinkSlot {
	
	private String name;
	private int price;
	private int quantity;
	
	public SoftDrinkSlot(String name, int price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	public String NameGetter() {
		return name;
	}
	
	public int PriceGetter() {
		return price;
	}
	
	public int QtyGetter() {;
		return quantity;
	}
	
	public SoftDrinkSlot Getter() {
		return this;
	}
	
	public void Purchased() {
		quantity--;
	}
}
