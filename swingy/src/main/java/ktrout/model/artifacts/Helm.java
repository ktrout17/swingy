package ktrout.model.artifacts;

public class Helm extends Artifact {

	public Helm(String name, int val) {
		super(name);
		switch(name) {
		case "MYRMIC HELM":
			this.hp = 9;
		case "DIY HELM":
			this.hp = 2;
		case "WELDING HELM":
			this.hp = 5;
		case "SPECIAL FORCES HELM":
			this.hp = 15;
		case "SSH-68":
			this.hp = 10;
		case "HEDGEHOG HELM":
			this.hp = 29;
		}
	}
}
