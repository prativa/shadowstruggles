package com.shadowstruggles.battle;

import com.shadowstruggles.Controller;

public class BattleLogic {

	private BattlePlatform platform;
	private Controller controller;

	public BattleLogic(BattlePlatform platform, Controller controller) {
		super();
		this.platform = platform;
	}

	public BattlePlatform getPlatform() {
		return platform;
	}

	public void setPlatform(BattlePlatform platform) {
		this.platform = platform;
	}
	
	
}
