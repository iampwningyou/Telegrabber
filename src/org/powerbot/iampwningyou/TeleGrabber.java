package org.powerbot.iampwningyou;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.powerbot.iampwningyou.tasks.BankDepositWines;
import org.powerbot.iampwningyou.tasks.CastTelegrab;
import org.powerbot.iampwningyou.tasks.MoveCamera;
import org.powerbot.iampwningyou.tasks.MoveToChaosTemple;
import org.powerbot.iampwningyou.tasks.MoveToFallyBank;
import org.powerbot.iampwningyou.tasks.PrepareTelegrab;
import org.powerbot.iampwningyou.tasks.TaskRT4;
import org.powerbot.iampwningyou.tasks.TeleportToFalador;
import org.powerbot.script.Condition;
import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

@Script.Manifest(name="TeleGrabber", description = "Does telegraph on something", properties="client=4;topic=1239912")

public class TeleGrabber extends PollingScript<ClientContext> implements PaintListener {

	private List <TaskRT4<ClientContext>> taskList = new ArrayList<TaskRT4<ClientContext>>();
	
	public static int thingsTelegrabbed = 0;
	public static Point UNINITIALIZED_POINT = new Point(-1, -1);
	public static Point lastItemPoint = UNINITIALIZED_POINT;
	public static String task = "";

	public static int inventoryCount;
	
	@SuppressWarnings("unchecked")
	public TeleGrabber() {
		taskList.addAll(Arrays.asList(
				new CastTelegrab(ctx),
				new TeleportToFalador(ctx),
				new MoveToFallyBank(ctx),
				new BankDepositWines(ctx),
				new MoveToChaosTemple(ctx),
				new MoveCamera(ctx),
				new PrepareTelegrab(ctx)
				));
	}

	@Override
	public void poll() {
		for (TaskRT4<ClientContext> task : taskList) {
			long startTime = System.nanoTime();
			boolean shouldActivate = task.activate();
			long endTime = System.nanoTime();
			
			System.out.println("Task Activate Time (" + task.getClass().getName() + "): " + (endTime - startTime) + "ns");
			
			if (shouldActivate){
				task.execute();
				System.out.println(TeleGrabber.task);
			}
		}
		
		System.out.println("");
	}

	public static void stop(ClientContext _ctx) {
		Condition.sleep(10000);
		_ctx.controller.stop();
	}
	
//	An estimate of the height of a single character.
	private static final int STR_HEIGHT = 16;
//	An estimate of the width of a single character.
	private static final int STR_WIDTH = 7;
//	Will hold the strings to be displayed in the paint.
	private List <String> paintStrs = new ArrayList<String>();

	/*
	 * 	This paint is dynamic to the number of strings displayed and the
	 * 	length of the strings. 
	 */
	public void repaint(Graphics g) {
//		Calculating values for status
		double secondRuntime = ctx.controller.script().getTotalRuntime()/1000;
		double minuteRuntime = secondRuntime/60;
		double hourRuntime = minuteRuntime / 60;

		int thingPerMinute = (int) (thingsTelegrabbed/minuteRuntime);
		int thingPerHour = (int) (thingsTelegrabbed/hourRuntime);
		
		paintStrs.clear();
		
		paintStrs.add("iampwningyou's Wine Grabber");
		
		paintStrs.add("Runtime: " + secondRuntime + "s");
		paintStrs.add("Inventory Count: " + inventoryCount);
		if (thingsTelegrabbed > 0) {
			paintStrs.add("Things telegrabbed: " + thingsTelegrabbed);
			paintStrs.add("Thing/Hour: " + thingPerHour);
			paintStrs.add("Thing/Min: " + thingPerMinute);
		}
		
		paintStrs.add("Current Task: " + task);
				
//		Calculates the longest strlen for bg width calc
		int longestStrLen = 0, strlen;
		for (String s : paintStrs) {
			strlen = s.length();
			if (strlen > longestStrLen) longestStrLen = strlen;
		}
		
//		Setting up the background.
		g.setColor(Color.BLACK);
		int height = ctx.game.dimensions().height - STR_HEIGHT*paintStrs.size();
		int width = longestStrLen * STR_WIDTH;
		g.drawRect(0, height, width, height);
		g.fillRect(0, height, width, height);
		
//		Drawing the text
		g.setColor(Color.WHITE);
		for (int i = 0; i < paintStrs.size(); i++) {
//			The i+1 is there because drawString's anchor is on the upper left
			int labelHeight = height + (i+1)*STR_HEIGHT; 
			g.drawString(paintStrs.get(i), 0, labelHeight);
		}
	}

}
