package j.index.core.security.secUser.service;

import j.index.core.dao.GenericDao;
import j.index.core.dao.IiiRestrictions;
import j.index.core.model.DataSet;
import j.index.core.security.secUser.entity.SecUser;
import j.index.core.security.secUser.entity.SecUserDao;
import j.index.core.service.GenericService;
import j.index.core.util.EncryptorUtil;
import j.index.core.util.IiiBeanFactory;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 使用者 Service
 * @author David Hsu
 * @version 2014/3/11
 */
@Service
public class SecUserService extends GenericService<SecUser> {

	@Autowired
	private SecUserDao dao;
	
	@Override
	protected GenericDao<SecUser> getDao() {
		return dao;
	}
	
	@Override
	public DataSet<SecUser> getByRestrictions(DataSet<SecUser> ds) throws Exception {
		Assert.notNull(ds);
		Assert.notNull(ds.getEntity());
		
		SecUser entity = ds.getEntity();
		IiiRestrictions restrictions = IiiBeanFactory.getIiiRestrictions();
		if(StringUtils.isNotEmpty(entity.getUserCode())) {
			restrictions.eq("userCode", entity.getUserCode());
		}
		if(StringUtils.isNotEmpty(entity.getUserName())) {
			restrictions.likeIgnoreCase("userName", entity.getUserName(), MatchMode.ANYWHERE);
		}
		if(entity!=null && entity.getUserType()!=null) {
			restrictions.eq("userType", entity.getUserType());
		}
		
		return dao.findByRestrictions(restrictions, ds);
	}

	@Override
	public SecUser save(SecUser entity, SecUser user) throws Exception {
		Assert.notNull(entity);
		//entity.setTimeToSystime();
		entity.initInsert(user);
		if(StringUtils.isNotEmpty(entity.getUserPassword())) { //密碼非空則進行加密
			final String encryptedPassword = EncryptorUtil.encrypt(entity.getUserPassword());
			entity.setUserPassword(encryptedPassword);
		}
		
		SecUser dbEntity = dao.save(entity);
		makeUserInfo(Arrays.asList(dbEntity));
		
		return dbEntity;
	}
	
	/**
	 * 檢查登入帳密
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public Boolean checkUser(SecUser entity) throws Exception {
		Assert.notNull(entity);
		
		IiiRestrictions restrictions = IiiBeanFactory.getIiiRestrictions();
		restrictions.eq("userCode", entity.getUserCode());
		
		List<SecUser> secUsers = dao.findByRestrictions(restrictions);
		if(secUsers == null || secUsers.isEmpty()) {
			return false;
		}
		SecUser secUser = secUsers.get(0);
		
		return EncryptorUtil.checkPassword(entity.getUserPassword(), secUser.getUserPassword());
	}

}

