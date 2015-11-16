package j.index.core.service;

import j.index.core.entity.Entity;
import j.index.core.model.DataSet;
import j.index.core.security.secUser.entity.SecUser;

/**
 * Service
 * @author David Hsu
 * @version 2014/3/11
 */
public interface Service<T extends Entity> {

	public T save(T entity, SecUser user)  throws Exception;
	
	public T getById(Long id) throws Exception;
	
	public DataSet<T> getByRestrictions(DataSet<T> ds) throws Exception;
	
	public T update(T entity, SecUser user) throws Exception;
	
	public T update(T entity, SecUser user, String... ignoreProperties) throws Exception;
	
	public void deleteById(Long id) throws Exception;
	
}
