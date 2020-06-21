import java.util.ArrayList;
import java.util.Collections;

public class VendingMachine {
	private ArrayList<Integer> coinChanger;
	private ArrayList<Integer> coinSlot;
	private ArrayList<SoftDrinkSlot> softDrinkSlots;
	public ArrayList<Integer> returnList;
	public int coinSlotTotal;
	public int numOfCoins;
	
	public VendingMachine() {
		coinChanger = new ArrayList<Integer>();
		coinSlot = new ArrayList<Integer>();
		softDrinkSlots = new ArrayList<SoftDrinkSlot>();
		returnList = new ArrayList<Integer>();
		coinSlotTotal = 0;
		numOfCoins = 0;
	}
	
	public void addCoinToCoinChanger(Integer c) {
		coinChanger.add(c);
	}
	
	public void addSoftDrinkSlot(SoftDrinkSlot s) {
		softDrinkSlots.add(s);
	}
	
	public void addCoinToCoinSlot(Integer c) {
		coinSlot.add(c);
		coinSlotTotal += c;
		numOfCoins++;
	}
	
	public int rejectCoin() {
		if(numOfCoins==0) {
			return -1;
		} else {
			int rejectedCoin = coinSlot.get(numOfCoins-1);
			coinSlot.remove(numOfCoins-1);
			numOfCoins--;
			coinSlotTotal -= rejectedCoin;
			return rejectedCoin;
		}
	}
	
	public SoftDrinkSlot DrinkGetter(String name) {
		for(SoftDrinkSlot drink : softDrinkSlots) {
			if(drink.NameGetter()==name) {
				return drink;
			}
		}
		return softDrinkSlots.get(0);
	}
	
	public boolean change(int change) {
		int cap = 11;
		int flag = 0;
		int actualChange = change;
		int coin;
		Collections.sort(coinChanger, Collections.reverseOrder());
		while(true) {
			for(int i = 0; i < coinChanger.size(); i++) {
				if(cap>coinChanger.get(i)) {
					cap = coinChanger.get(i);
					break;
				}
			}
			System.out.println("Cap equals "+cap);
			for(int i = 0; i < coinChanger.size(); i++) {
				coin = coinChanger.get(i);
				if(coin>cap)
					continue;
				if(coin>change) { 
					continue; 
				} else if (change>coin) {
					change-=coin;
					returnList.add(coin);
				} else {
					change = 0;
					returnList.add(coin);
					flag = 1;
				}
			}
			if(flag==1) {
				Collections.sort(returnList);
				for(Integer ch : returnList) {
					coinChanger.remove(ch);
				}
				return true;
			}
			else {
				cap--;
				if(!returnList.isEmpty()) {
					returnList.clear();
					change = actualChange;
				}
				if(cap==1) {
					return false;
				}
			}
		}
	}
	
	public void coinsFromSlotToChanger() {
		for(Integer coin : coinSlot) {
			coinChanger.add(coin);
		}
		coinSlotTotal = 0;
		numOfCoins = 0;
		if(!coinSlot.isEmpty()) {
			coinSlot.clear();
		}
	}
	
}
