package j.index.core.enums;


/**
 * 
 * @author David Hsu
 * @version 2014/3/27
 */
public enum SystemStatus {

	/** 有效資料. */
	Y("有效資料", "啟用", "是"),

	/** 無效資料. */
	N("無效資料", "停用", "否");
	
	private String localName;
	
	private String localName2;
	
	private String localName3;
	
	private SystemStatus () {
		
	}
	
	private SystemStatus (String localName, String localName2, String localName3) {
		this.localName = localName;
		this.localName2 = localName2;
		this.localName3 = localName3;
	}
	
	public String getLocalName() {
		return localName;
	}
	
	public String getLocalName2() {
		return localName2;
	}
	
	public String getLocalName3(){
		return localName3;
	}
	
}
