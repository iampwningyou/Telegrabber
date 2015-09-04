package org.powerbot.iampwningyou.tasks;

import org.powerbot.iampwningyou.Areas;
import org.powerbot.iampwningyou.TeleGrabber;
import org.powerbot.script.rt4.ClientContext;

public class OpenMagicTab extends TaskRT4<ClientContext> {

	public OpenMagicTab(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		System.out.println("Checking if should open magic tab.");
		return Areas.CHAOS_TEMPLE.contains(ctx.players.local().tile())
				&& ctx.widgets.component(161, 47).textureId() == -1; // Magic tab is not opened
	}

	@Override
	public void execute() {
		TeleGrabber.task = "Opening Magic Tab";
		ctx.widgets.component(161, 47).click();
	}

}
