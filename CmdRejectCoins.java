import java.util.ArrayList;
import java.util.Collections;

public class CmdRejectCoins implements Command {
	private ArrayList<Integer> rejectedCoins;
	public String str;
	public int total;
	
	CmdRejectCoins() {
		rejectedCoins = new ArrayList<Integer>();
		str = "";
		total = 0;
	}
	
	public String execute(VendingMachine v, String cmdPart) {
		int rejected = v.rejectCoin();
		if(!rejectedCoins.isEmpty()) {
			rejectedCoins.clear();
		}
		while(rejected!=-1) {
			rejectedCoins.add(rejected);
			rejected = v.rejectCoin();
		}
		if(rejectedCoins.isEmpty()) {
			str = "Rejected no coin!";
		} else {
			Collections.sort(rejectedCoins);
			str = "Rejected $";
			for(int i=0; i<rejectedCoins.size(); i++) {
				str += rejectedCoins.get(i);
				total += rejectedCoins.get(i);
				if(i<(rejectedCoins.size()-1)) {
					str += ", $";
				}
			}
			str += ". $"+total+" in Total.";
		}
		v.coinSlotTotal = 0;
		return str;
	}
}
