package br.edu.ifsp.pds.shadowstruggles.tools.model.profiles;

public interface AttributePointsFormula {
	public static enum PlayerField { MAX_HEALTH, MAX_ENERGY, ENERGY_RECOVERY, DECK_CAPACITY, MAX_CARD_POINTS, DOUBLE_DRAW };
	
	public abstract void attributePoints(Profile profile, PlayerField field);
}
