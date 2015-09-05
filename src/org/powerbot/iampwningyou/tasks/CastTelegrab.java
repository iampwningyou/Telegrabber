package org.powerbot.iampwningyou.tasks;

import java.util.concurrent.Callable;

import org.powerbot.iampwningyou.Areas;
import org.powerbot.iampwningyou.TeleGrabber;
import org.powerbot.iampwningyou.resources.ids.ItemIds;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.BasicQuery;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GroundItem;

public class CastTelegrab extends TaskRT4<ClientContext> {

	private BasicQuery<GroundItem> bQuery;
	
	public CastTelegrab(ClientContext ctx) {
		super(ctx);
	}
	
	@Override
	public boolean activate() {
		System.out.println(ctx.inventory.component().index());
		bQuery = ctx.groundItems.select().id(ItemIds.WINE_OF_ZAMORAK);
		return Areas.CHAOS_TEMPLE.contains(ctx.players.local().tile())
				&& bQuery.size() > 0
				&& ctx.widgets.component(218, 20).borderThickness() > 0
				&& TeleGrabber.inventoryCount < 28;
	}

	@Override
	public void execute() {
		TeleGrabber.task = "Casting Telegrab";
		GroundItem item = bQuery.nearest().poll();
		
		item.click("Cast", ItemIds.WINE_OF_ZAMORAK_STR);
	}

}
