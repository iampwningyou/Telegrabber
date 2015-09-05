package org.powerbot.iampwningyou;

import org.powerbot.script.Area;
import org.powerbot.script.Tile;

public final class Areas {

	public static final Area FALADOR = 
			new Area(new Tile(2937, 3385), new Tile(2974, 3365));
	
	public static final Area FALADOR_BANK = 
			new Area(new Tile(2942, 3385),
								new Tile(2948, 3373),
										new Tile(2948, 3370),
												new Tile(2950, 3368),
												
												new Tile(2950, 3368),
					new Tile(2942, 3368));
	
	public static final Area CHAOS_TEMPLE = new Area(
			new Tile(2931, 3516, 0), new Tile(2936, 3517, 0),
								     new Tile(2936, 3517, 0),
		    new Tile(2931, 3514, 0));
//			new Tile(3200, 3200), new Tile(3300, 3300));
	
	private Areas() {
		
	}

}
