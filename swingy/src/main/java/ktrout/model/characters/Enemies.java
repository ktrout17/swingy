package ktrout.model.characters;

public class Enemies extends Character {
    
    private Artifact artifact;

    public Enemies(String name, int atk, int def, int hp, Artifact artifact) {
        super(name, atk, def, hp);
        artifact = this.artifact;
    }

    public Artifact getArtifact() {
        return artifact;
    }
}