package j.index.core.service;

import j.index.core.dao.GenericDao;
import j.index.core.entity.GenericEntity;
import j.index.core.security.secUser.entity.SecUser;
import j.index.core.security.secUser.entity.SecUserDao;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * GenericService
 * @author David Hsu
 * @version 2014/3/11
 */
public abstract class GenericService<T extends GenericEntity> implements Service<T> {

	protected final transient Logger log = Logger.getLogger(getClass());
	
	protected abstract GenericDao<T> getDao();
	
	@Autowired
	private SecUserDao userDao;
	
	@Override
	public T save(T entity, SecUser user) throws Exception {
		Assert.notNull(entity);
		
		entity.initInsert(user);
		
		T dbEntity = getDao().save(entity);
		makeUserInfo(Arrays.asList(dbEntity));
		
		return dbEntity;
	}

	@Override
	public T getById(Long id) throws Exception {
		Assert.notNull(id);
		
		T entity = getDao().findById(id);
		makeUserInfo(Arrays.asList(entity));
		
		return entity;
	}

	@Override
	public T update(T entity, SecUser user) throws Exception {
		Assert.notNull(entity);
		
		entity.initUpdate(user);
		
		T dbEntity = update(entity, user, new String[0]);
		makeUserInfo(Arrays.asList(dbEntity));
		
		return dbEntity;
	}
	
	@Override
	public T update(T entity, SecUser user, String... ignoreProperties) throws Exception {
		Assert.notNull(entity);
		
		entity.initUpdate(user);
		
		T dbEntity = getDao().findById(entity.getId());
		
		if(ignoreProperties.length == 0) {
			BeanUtils.copyProperties(entity, dbEntity);
		} else {
			BeanUtils.copyProperties(entity, dbEntity, ignoreProperties);
		}
		
		getDao().update(dbEntity);
		makeUserInfo(Arrays.asList(dbEntity));
		
		return dbEntity;
	}

	@Override
	public void deleteById(Long id) throws Exception {
		Assert.notNull(id);
		
		getDao().deleteById(id);
	}
	
	/**
	 * 取得使用者資訊
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public void makeUserInfo(List<T> entitys) throws Exception {
		for(T entity : entitys) {
			if(entity.getCreatedBy() != null && entity.getLastModifiedBy() != null) {
				SecUser user = getUserInfo(entity.getCreatedBy());
				entity.setCreatedUser(user);
				
				if(entity.getCreatedBy().equals(entity.getLastModifiedBy())) {
					entity.setLastModifiedUser(user);
				} else {
					entity.setLastModifiedUser(getUserInfo(entity.getLastModifiedBy()));
				}
			}
		}
	}
	
	/**
	 * 取得使用者資訊
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public SecUser getUserInfo(Long id) throws Exception {
		Assert.notNull(id);
		
		return userDao.findById(id);
	}

}
