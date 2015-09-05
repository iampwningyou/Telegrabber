package org.powerbot.iampwningyou.tasks;

import org.powerbot.script.rt4.ClientContext;

public class MoveCamera extends TaskRT4<ClientContext> {

	public MoveCamera(ClientContext ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean activate() {
		return ctx.camera.pitch() != 99 || ctx.camera.yaw() != 310;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		ctx.camera.pitch(99);
		ctx.camera.angle(310);
	}

}
