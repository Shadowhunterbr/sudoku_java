package main.model;

public enum GameStatusEnum {

	NON_STARTED("Não iniciado"),
	INCOMPLETE("incompleto"),
	COMPLETE("completo");
	


	private String label;
	
	GameStatusEnum(final String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
