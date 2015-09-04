package org.powerbot.iampwningyou.tasks;

import java.awt.Point;

import org.powerbot.iampwningyou.Areas;
import org.powerbot.iampwningyou.TeleGrabber;
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
		System.out.println("Checking if should prepare for telegrab.");
		System.out.println("Inv Count: " + ctx.inventory.items().length);
		return Areas.CHAOS_TEMPLE.contains(ctx.players.local().tile()) // Player is in Chaos Temple
				&& ctx.widgets.component(218, 20).borderThickness() == 0 // Telegrab not selected
				&& ctx.inventory.items().length < 28;
	}

	@Override
	public void execute() {
		TeleGrabber.task = "Preparing for Telegrab";
//		ctx.widgets.component(218, 20).click();
		ctx.magic.cast(Spell.TELEKINETIC_GRAB);
		
		Condition.sleep();
		
		final Tile wineLocation = new Tile(2930, 3515);
		Point point = wineLocation.matrix(ctx).centerPoint();
		System.out.println("Point: " + point);
		ctx.input.move(point);
	}

}
