package org.powerbot.iampwningyou.tasks;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.ClientAccessor;

public abstract class TaskRT4 <C extends ClientContext> extends ClientAccessor {

	public abstract boolean activate();
	public abstract void execute();
	
	public TaskRT4(C ctx) {
		super(ctx);
	}

}