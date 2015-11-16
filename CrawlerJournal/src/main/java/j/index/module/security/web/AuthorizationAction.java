package j.index.module.security.web;

import j.index.core.model.DataSet;
import j.index.core.security.secUser.entity.SecUser;
import j.index.core.security.secUser.service.SecUserService;
import j.index.core.web.GenericWebAction;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * LoginAction
 * @author David Hsu
 * @version 2014/3/14
 */
@Controller
@SuppressWarnings("serial")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AuthorizationAction extends GenericWebAction<SecUser> {
	
	@Autowired
	private SecUserService userService;
	
	@Autowired
	private SecUser user;
	
	@Autowired
	private DataSet<SecUser> ds;
	
	public void validateLogin() throws Exception {
		boolean checkLogin = true;
		if(StringUtils.isEmpty(user.getUserCode())) {
			addActionError("請輸入帳號");
			checkLogin = false;
		}
		
		if(StringUtils.isEmpty(user.getUserPassword())) {
			addActionError("請輸入密碼");
			checkLogin = false;
		}
		
		if(checkLogin) { //帳號密碼皆有輸入時才進行檢核
			boolean isValidUser = false;
			try {
				isValidUser = userService.checkUser(user);
			} catch (Exception e) {
				log.error(ExceptionUtils.getStackTrace(e));
				throw new Exception(e);
			}
			
			if(!isValidUser) {
				addActionError("帳號密碼錯誤，請重新輸入");
			}
		}
	}

	/**
	 * 登入
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception {
		try {
			ds.setEntity(user);
			ds = userService.getByRestrictions(ds);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		getSession().put(LOGIN, ds.getResults().get(0));
		return INDEX;
	}
	
	/**
	 * 登出
	 * @return
	 * @throws Exception
	 */
	public String logout() throws Exception {
		if(getSession().get(LOGIN) != null) {
			getSession().put(LOGIN, null);
		}
		
		return LOGIN;
	}

	public SecUser getUser() {
		return user;
	}

	public void setUser(SecUser user) {
		this.user = user;
	}

	public DataSet<SecUser> getDs() {
		return ds;
	}

	public void setDs(DataSet<SecUser> ds) {
		this.ds = ds;
	}
	
}
