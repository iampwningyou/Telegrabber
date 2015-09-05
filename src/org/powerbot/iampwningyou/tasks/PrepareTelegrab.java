package org.powerbot.iampwningyou.tasks;

import java.awt.Point;
import java.util.concurrent.Callable;

import org.powerbot.iampwningyou.Areas;
import org.powerbot.iampwningyou.InventoryHelper;
import org.powerbot.iampwningyou.TeleGrabber;
import org.powerbot.iampwningyou.resources.ids.ItemIds;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Magic.Spell;

public class PrepareTelegrab extends TaskRT4<ClientContext> {

	public PrepareTelegrab(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
//		System.out.println("Checking if should prepare for telegrab.");
		
//		System.out.println("Inv Count: " + InventoryHelper.get_inventory_count(ctx.inventory.items()));
		return Areas.CHAOS_TEMPLE.contains(ctx.players.local().tile()) // Player is in Chaos Temple
				&& ctx.widgets.component(218, 20).borderThickness() == 0 // Telegrab not selected
				&& TeleGrabber.inventoryCount < 28;
	}

	@Override
	public void execute() {
		TeleGrabber.task = "Preparing for Telegrab";
//		ctx.widgets.component(218, 20).click();
		ctx.input.send("{VK_ESCAPE down}");
		Condition.sleep();
		ctx.input.send("{VK_ESCAPE up}");
		Condition.sleep();
		TeleGrabber.inventoryCount = InventoryHelper.get_inventory_count(ctx.inventory.items());
		Condition.sleep();
		
		if (TeleGrabber.inventoryCount >= 28) {
			return;
		}
		
		ctx.movement.newTilePath(new Tile(2931, 3515)).traverse();
		Condition.sleep();
		ctx.magic.cast(Spell.TELEKINETIC_GRAB);
		Condition.sleep();
		
		if (TeleGrabber.lastItemPoint.equals(TeleGrabber.UNINITIALIZED_POINT)) {
			final Tile wineLocation = new Tile(2930, 3515);
			Point point = wineLocation.matrix(ctx).centerPoint();
			System.out.println("Point: " + point);
			ctx.input.move(point);
		} else {
			TeleGrabber.task = "Moving mouse to last point";
			ctx.input.move(TeleGrabber.lastItemPoint);
		}

//		Condition.wait(new Callable<Boolean>() {
//			
//			public Boolean call() throws Exception {
//				
//				return ctx.groundItems.select().id(ItemIds.WINE_OF_ZAMORAK).size() > 0;
//			}
//		}, 1, 10);
	}

}
