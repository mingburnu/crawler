package j.index.core.security.secUser.entity;

import j.index.core.entity.GenericEntity;
import j.index.core.enums.UserType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
/**
 * 使用者
 * @author David Hsu
 * @version 2014/3/7
 */
@Entity
@Table(name="SEC_USER")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SecUser extends GenericEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5804311089729989927L;

	/**
	 * 使用者代號
	 */
	@Column(name="USER_CODE")
	private String userCode;
	
	/**
	 * 使用者名稱
	 */
	@Column(name="USER_NAME")
	private String userName;
	
	/**
	 * 使用者密碼
	 */
	@Column(name="USER_PASSWORD")
	private String userPassword;
	
	/**
	 * 系所單位
	 */
	@Column(name="OFFICE")
	private String office;
		
	/**
	 * User類別
	 */
	@Column(name="USER_TYPE")
	@Enumerated(EnumType.STRING)
	private UserType userType;
	

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JSON(serialize=false)
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
	
}
