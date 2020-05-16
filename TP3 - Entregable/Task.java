package entregable;

public class Task {
	private String name;
	private int duration;
	private String description;
	
	public Task(String name, int duration) {
		this.name = name;
		this.duration = duration;
	}
	
	public int getDuration() {
		return this.duration;
	}
	public String getDescription() {
		return this.description;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return this.name + " (" + this.duration + " hrs)";
	}
}
