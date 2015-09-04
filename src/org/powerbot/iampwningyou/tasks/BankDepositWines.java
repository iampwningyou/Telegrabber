package org.powerbot.iampwningyou.tasks;

import org.powerbot.iampwningyou.TeleGrabber;
import org.powerbot.iampwningyou.Areas;
import org.powerbot.iampwningyou.resources.ids.ItemIds;
import org.powerbot.script.rt4.Bank.Amount;
import org.powerbot.script.rt4.ClientContext;

public class BankDepositWines extends TaskRT4<ClientContext> {
	
	public BankDepositWines(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return Areas.FALADOR_BANK.contains(ctx.players.local().tile())
				&& ctx.bank.opened();
	}

	@Override
	public void execute() {
		TeleGrabber.task = "Depositing Stuff";
		
		if (ctx.inventory.select().id(ItemIds.WINE_OF_ZAMORAK).count() > 0) {
			ctx.bank.deposit(ItemIds.WINE_OF_ZAMORAK, Amount.ALL);
		} else {
			ctx.bank.close();
		}
	}

}
