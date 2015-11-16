package j.index.core.enums;

public enum GraduateLevel {
	學士("學士"), 碩士("碩士"), 博士("博士"), ;
	private String graduteLevel;

	private GraduateLevel() {
	}

	private GraduateLevel(String graduteLevel) {
	}

	/**
	 * @return the graduteLevel
	 */
	public String getGraduteLevel() {
		return graduteLevel;
	}

	/**
	 * @param graduteLevel
	 *            the graduteLevel to set
	 */
	public void setGraduteLevel(String graduteLevel) {
		this.graduteLevel = graduteLevel;
	}
}
