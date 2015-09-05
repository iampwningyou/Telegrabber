package org.powerbot.iampwningyou.tasks;

import java.util.concurrent.Callable;

import org.powerbot.iampwningyou.Areas;
import org.powerbot.iampwningyou.InventoryHelper;
import org.powerbot.iampwningyou.TeleGrabber;
import org.powerbot.iampwningyou.resources.ids.ItemIds;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.TilePath;

public class MoveToFallyBank extends TaskRT4<ClientContext> {

	private Tile[] toBank = {
		new Tile(2962, 3381, 0),
		new Tile(2958, 3381, 0),
		new Tile(2952, 3380, 0),
		new Tile(2952, 3378, 0),
		new Tile(2949, 3376, 0),
		new Tile(2945, 3371, 0),
		new Tile(2945, 3368, 0)
	};
	private final TilePath to_Bank = ctx.movement.newTilePath(toBank);
	public MoveToFallyBank(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return Areas.FALADOR.contains(ctx.players.local().tile())
				&& ctx.players.local().animation() == -1
				&& TeleGrabber.inventoryCount == 28
				&& !ctx.bank.opened();
	}

	@Override
	public void execute() {
		TeleGrabber.task = "Moving to Fally Bank";
		
		Condition.wait(new Callable<Boolean>() {
			
			public Boolean call() throws Exception {
				to_Bank.traverse();
				return Areas.FALADOR_BANK.contains(ctx.players.local().tile());
			}
		}, 200, 10);
		ctx.objects.select().id(24101).nearest().poll().interact(true, "Bank");
	}

}
