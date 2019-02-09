package org.usfirst.frc3244.Jupiter2019;

public class Constants {
	
	/**
	 * Which PID slot to pull gains from.  Starting 2018, you can choose 
	 * from 0,1,2 or 3.  Only the first two (0,1) are visible in web-based configuration.
	 */
	public static final int kSlotIdx = 0;
	
	/* Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops.  
	 * For now we just want the primary one.
	 */
	public static final int kPIDLoopIdx = 0;
	
	/*
	 * set to zero to skip waiting for confirmation, set to nonzero to wait
	 * and report to DS if action fails.
	 */
	public static final int kTimeoutMs = 10;
	
	public static final double kScissorToSetpointTimout_S = 8;
	
	// Game Data CharPostion 
	public static final int kGameDataOurSwitchChar = 0;
	public static final int kGameDataOurScaleChar = 1;
	public static final int kGameDataOpponetScaleChar = 2;
}
