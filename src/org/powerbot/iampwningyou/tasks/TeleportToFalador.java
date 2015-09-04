package org.powerbot.iampwningyou.tasks;

import org.powerbot.iampwningyou.Areas;
import org.powerbot.iampwningyou.TeleGrabber;
import org.powerbot.iampwningyou.resources.ids.ItemIds;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GroundItem;
import org.powerbot.script.rt4.Magic.Spell;

public class TeleportToFalador extends TaskRT4<ClientContext> {

	public TeleportToFalador(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		System.out.println("Checking if should teleport.");
		return Areas.ZAMORAK_PLACE.contains(ctx.players.local().tile())
				&& ctx.inventory.items().length >= 28;
	}

	@Override
	public void execute() {
		TeleGrabber.task = "Teleporting to Falador";
		ctx.magic.cast(Spell.FALADOR_TELEPORT);
	}

}
