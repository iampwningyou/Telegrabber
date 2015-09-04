package org.powerbot.iampwningyou.tasks;


import java.util.concurrent.Callable;

import org.powerbot.iampwningyou.TeleGrabber;
import org.powerbot.iampwningyou.Areas;
import org.powerbot.iampwningyou.resources.ids.ItemIds;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.Bank.Amount;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.TilePath;

public class MoveToChaosTemple extends TaskRT4<ClientContext> {
	
	public MoveToChaosTemple(ClientContext ctx) {
		super(ctx);
	}

	private Tile[] tiles = {
			new Tile(2946,3368,0), // bank
			new Tile(2946,3374,0), // entrance of bank
			new Tile(2951,3377,0), // 
			new Tile(3956,3380,0), // entrance of beer store
			new Tile(2962,3381,0), // near statue
			new Tile(2964,3386,0), //
			new Tile(2965,3390,0), // doors of falador
			new Tile(2965,3397,0),//
			new Tile(2964,3403,0),//
			new Tile(2966,3409,0),// crossway
			new Tile(2961,3415,0),//
			new Tile(2955,3419,0),//
			new Tile(2949,3424,0),// near wall
			new Tile(2948,3431,0),//
			new Tile(2947,3437,0),//
			new Tile(2947,3444,0),// wheat farm
			new Tile(2944,3452,0),//
			new Tile(2944,3460,0),//
			new Tile(2944,3467,0),//
			new Tile(2944,3474,0),// near fire remains
			new Tile(2943,3482,0),//
			new Tile(2943,3490,0),//
			new Tile(2943,3496,0),//
			new Tile(2941,3503,0),// near timber defence
			new Tile(2941,3511,0),//
			new Tile(2942,3517,0),// entrance of church
			new Tile(2935,3516,0),//
			new Tile(2931,3515,0)// at the wine
	};
	private final TilePath to_ChaosTemple = ctx.movement.newTilePath(tiles);
	@Override
	public boolean activate() {
		return Areas.FALADOR_BANK.contains(ctx.players.local().tile())
				&& ctx.inventory.items().length < 28;
	}

	@Override
	public void execute() {
		TeleGrabber.task = "Moving to Chaos Temple";
		
		Condition.wait(new Callable<Boolean>() {
			
			public Boolean call() throws Exception {
				to_ChaosTemple.traverse();
				return Areas.CHAOS_TEMPLE.contains(ctx.players.local().tile());
			}
		}, 1000, 100);
	}

}
