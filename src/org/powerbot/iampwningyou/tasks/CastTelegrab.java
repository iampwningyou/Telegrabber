package org.powerbot.iampwningyou.tasks;

import java.awt.Point;

import org.powerbot.iampwningyou.Areas;
import org.powerbot.iampwningyou.TeleGrabber;
import org.powerbot.iampwningyou.resources.ids.ItemIds;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.BasicQuery;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GroundItem;
import org.powerbot.script.rt4.Inventory;
import org.powerbot.script.rt4.Item;

public class CastTelegrab extends TaskRT4<ClientContext> {

	private BasicQuery<GroundItem> bQuery;
	
	public CastTelegrab(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		System.out.println("Checking if should cast telegrab.");
		System.out.println("In Zamorak?:" + Areas.ZAMORAK_PLACE.contains(ctx.players.local().tile()));
		System.out.println("Widgets: " + ctx.groundItems.select().id(ItemIds.WINE_OF_ZAMORAK).nearest().poll());
		System.out.println("Border Thickness: " + ctx.widgets.component(218, 20).borderThickness());
		for (Item i : ctx.inventory.items()) {
			System.out.println("Inventory: " + i.valid());
		}
		return Areas.ZAMORAK_PLACE.contains(ctx.players.local().tile())
				&& ctx.groundItems.select().id(ItemIds.WINE_OF_ZAMORAK).nearest().poll() != null
				&& ctx.widgets.component(218, 20).borderThickness() > 0
				&& ctx.inventory.items().length < 28;
	}

	@Override
	public void execute() {
		TeleGrabber.task = "Casting Telegrab";
		GroundItem item = ctx.groundItems.select().id(ItemIds.WINE_OF_ZAMORAK).nearest().poll();
		item.click("Cast");
	}

}
