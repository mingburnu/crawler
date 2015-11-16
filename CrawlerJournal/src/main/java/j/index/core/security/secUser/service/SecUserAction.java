package j.index.core.security.secUser.service;

import j.index.core.model.DataSet;
import j.index.core.security.secUser.entity.SecUser;
import j.index.core.web.GenericCRUDAction;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 使用者
 * @author David Hsu
 * @version 2014/3/17
 */
@Controller
@SuppressWarnings("serial")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SecUserAction extends GenericCRUDAction<SecUser> {
	
	@Autowired
	private SecUserService userService;

	@Override
	public void validateSave() throws Exception {
		validateUserCode();
		
		if(StringUtils.isEmpty(getEntity().getUserPassword())) {
			addActionError("請輸入使用者密碼");
		}
	}

	@Override
	public void validateUpdate() throws Exception {
		validateUserCode();
	}

	@Override
	public void validateDelete() throws Exception {
		// TODO Auto-generated method stub
	}
	
	public void validateUserCode() {
		if(StringUtils.isEmpty(getEntity().getUserCode())) {
			addActionError("請輸入使用者代號");
		}
	}
	
	@Override
	public String query() throws Exception {
		try {
			if(getEntity().getId() != null) {
				SecUser user = userService.getById(getEntity().getId());
				setEntity(user);
			}
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		return EDIT;
	}

	@Override
	public String list() throws Exception {
		try {
			DataSet<SecUser> ds = userService.getByRestrictions(initDataSet());
			setDs(ds);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		return LIST;
	}

	@Override
	public String save() throws Exception {
		try {
			SecUser user = userService.save(getEntity(), getLoginUser());
			setEntity(user);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		addActionMessage("儲存成功");
		return EDIT;
	}

	@Override
	public String update() throws Exception {
		try {
			SecUser user = userService.update(getEntity(), getLoginUser(), "userPassword");
			setEntity(user);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		addActionMessage("檔案已修改");
		return EDIT;
	}

	@Override
	public String delete() throws Exception {
		try {
			userService.deleteById(getEntity().getId());
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		disableAllInput();
		addActionMessage("檔案已刪除");
		return DELETE;
	}
	
}
