package ktrout.model.characters;

import ktrout.model.artifacts.Artifact;

public class Enemy extends Character {
	
	private Artifact artifact;
	
	public Enemy(String name, int atk, int def, int hp, Artifact artifact) {
		super(name, atk, def, hp);
		artifact = this.artifact;
	}
	
	public Artifact getArtifact() {
		return artifact;
	}

}
