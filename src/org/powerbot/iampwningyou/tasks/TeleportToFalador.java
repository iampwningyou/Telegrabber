package org.powerbot.iampwningyou.tasks;

import org.powerbot.iampwningyou.Areas;
import org.powerbot.iampwningyou.InventoryHelper;
import org.powerbot.iampwningyou.TeleGrabber;
import org.powerbot.iampwningyou.resources.ids.ItemIds;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GroundItem;
import org.powerbot.script.rt4.Magic.Spell;

public class TeleportToFalador extends TaskRT4<ClientContext> {

	public TeleportToFalador(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return Areas.CHAOS_TEMPLE.contains(ctx.players.local().tile())
				&& TeleGrabber.inventoryCount >= 28;
	}

	@Override
	public void execute() {
		TeleGrabber.task = "Teleporting to Falador";
		if (ctx.magic.spell() == Spell.TELEKINETIC_GRAB) {
//			Just try to move to get out of stop the spell.
			ctx.movement.newTilePath(new Tile(2936, 3512)).traverse();
		}
		Condition.sleep();
		ctx.magic.cast(Spell.FALADOR_TELEPORT);
		Condition.sleep();
	}

}
