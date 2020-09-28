package ktrout.model.characters;

import ktrout.model.artifacts.Artifact;

public class Enemies extends Character {
    
    private Artifact artifact;

    public Enemies(String name, int atk, int def, int hp, Artifact artifact) {
        super(name, atk, def, hp);
        this.hp = hp;
        this.artifact = artifact;
        this.name = name;
    }

    public Artifact getArtifact() {
        return artifact;
    }

    public int getEnemyHp() {
        return hp;
    }

    public String getEnemyName() {
        return name;
    }
}