package ktrout.model.artifacts;

public class Helm extends Artifact {

	public Helm(String name, int points) {
		super(name, points);
		switch(name) {
		case "MYRMIC HELM":
			points = 9;
		case "DIY HELM":
			points = 2;
		case "WELDING HELM":
			points = 5;
		case "SPECIAL FORCES HELM":
			points = 15;
		case "SSH-68":
			points = 10;
		case "HEDGEHOG HELM":
			points = 29;
		case "MINECRAFT HELM":
			points = 12;
		}
	}
}
