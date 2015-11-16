package j.index.core.enums;

/**
 * UserType 
 * @author Mark Huang
 * @version 2014/5/14
 */
public enum UserType {

	/**
	 * 系統管理員
	 */
	ADMIN("系統管理員"),
	
	;
	
	private String userType;
	
	private UserType(){
		
	}
	
	private UserType(String userType){
		this.userType = userType;
	}

	public String getUserType() {
		return userType;
	}

}
