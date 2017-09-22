package com.eyequeue.lolabilities.model;

import java.util.List;

public class AllData {
	
	List<ChampionRecord> champions;
	List<AbilityType> abilityTypes;
	long lastUpdated;
	
	public List<ChampionRecord> getChampions() {
		return champions;
	}
	public void setChampions(List<ChampionRecord> champions) {
		this.champions = champions;
	}
	public List<AbilityType> getAbilityTypes() {
		return abilityTypes;
	}
	public void setAbilityTypes(List<AbilityType> abilityTypes) {
		this.abilityTypes = abilityTypes;
	}
	public long getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	

}
