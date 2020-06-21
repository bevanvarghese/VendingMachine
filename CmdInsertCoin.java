public class CmdInsertCoin implements Command {
	public int coin;
	public String str;
	
	CmdInsertCoin() {
		coin = 0;
		str = "";
	}
	
	public String execute(VendingMachine v, String cmdPart) {
		coin = Integer.valueOf(cmdPart);
		v.addCoinToCoinSlot(coin);
		str = "Inserted a $"+cmdPart+" coin. $"+v.coinSlotTotal+" in Total.";
		return str;
	}
}
