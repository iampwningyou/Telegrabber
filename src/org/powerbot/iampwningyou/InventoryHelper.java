package org.powerbot.iampwningyou;

import org.powerbot.script.rt4.Item;

public class InventoryHelper {
	
	public static int get_inventory_count(Item[] items) {
		int inventory_count = 0;
		for (Item i : items) {
			if (i == null) {
//				System.out.println("Item is null:" + i);
				continue;
			} else if (i.valid() == false) {
//				System.out.println("invalid item.");
				continue;
			}
			inventory_count++;
		}
		return inventory_count;
	}
}
