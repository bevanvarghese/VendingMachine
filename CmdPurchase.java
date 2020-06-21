public class CmdPurchase implements Command {
	public String str;
	
	CmdPurchase() {
		str = "";
	}
	
	public String execute(VendingMachine v, String cmdPart) {
		SoftDrinkSlot drink = v.DrinkGetter(cmdPart);
		String name = drink.NameGetter();
		int price = drink.PriceGetter();
		int quantity = drink.QtyGetter();
		int reqChange = v.coinSlotTotal-price;
		str += "Purchasing "+name+"... ";
		if(quantity ==0) {
			str += "Out of stock!";
		} else if(v.coinSlotTotal<price) {
			str += "Insufficient amount! Inserted $"+v.coinSlotTotal+" but needs $"+price+".";
		} else if((reqChange>0)&&(!v.change(reqChange))) {
			System.out.println("Part two");
			str += "Insufficient change!";
		} else {
			drink.Purchased();
			str += "Success! Paid $"+v.coinSlotTotal+". ";
			if(v.coinSlotTotal==price) {
				str += "No change.";
			} else { 
				str += "Change: ";
				for(int i=0; i<v.returnList.size(); i++) {
					str += "$"+v.returnList.get(i);
					if(i!=v.returnList.size()-1) {
						str += ", ";
					}
				}
				str += ".";
			}
			v.coinsFromSlotToChanger();
		}
		return str;
	}
}