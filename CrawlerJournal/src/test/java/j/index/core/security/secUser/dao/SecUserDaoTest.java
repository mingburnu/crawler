package j.index.core.security.secUser.dao;

import j.index.core.GenericTest;
import j.index.core.dao.IiiRestrictions;
import j.index.core.enums.SystemStatus;
import j.index.core.security.secUser.entity.SecUser;
import j.index.core.security.secUser.entity.SecUserDao;
import j.index.core.util.IiiBeanFactory;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * SecUserDaoTest
 * @author David Hsu
 * @version 2014/3/11
 */
public class SecUserDaoTest extends GenericTest {

	@Autowired
	private SecUserDao dao;
	
	@Test
	public void testCRUD() throws Exception {
		
		final String user1Code = "0123";
		final String user2Code = "0456";
		
		//Save user 1
		SecUser user1 = new SecUser();
		user1.setUserCode(user1Code);
		user1.setUserName("Admin1");
		user1.setCreatedBy(1L);
		user1.setLastModifiedBy(1L);
		
		user1.initInsert(user1);
		user1.setStatus(SystemStatus.Y);
		
		SecUser dbUser1 = dao.save(user1);
		final Long user1Id = dbUser1.getId();
		Assert.assertEquals(user1Code, dbUser1.getUserCode());
		
		//Save user 2
		SecUser user2 = new SecUser();
		user2.setUserCode(user2Code);
		user2.setUserName("Admin2");
		user2.setCreatedBy(1L);
		user2.setLastModifiedBy(1L);
		
		user2.initInsert(user2);
		user2.setStatus(SystemStatus.Y);
		
		SecUser dbUser2 = dao.save(user2);
		final Long user2Id = dbUser2.getId();
		Assert.assertEquals(user2Code, dbUser2.getUserCode());
		
		//Query by id
		dbUser1 = dao.findById(user1Id);
		Assert.assertEquals(user1Code, dbUser1.getUserCode());
		
		//update
		final String user1UpdName = "Admin_test";
		dbUser1.setUserName("Admin_test");
		boolean updated = true;
		try {
			dao.update(dbUser1);
		} catch (Exception e) {
			updated = false;
		}
		Assert.assertTrue(updated);
		
		//query by condition
		IiiRestrictions restrictions = IiiBeanFactory.getIiiRestrictions();
		restrictions.eq("userName", user1UpdName);
		List<SecUser> users = dao.findByRestrictions(restrictions);
		Assert.assertEquals(1, users.size());
		Assert.assertEquals(user1UpdName, users.get(0).getUserName());
		
		//delete by id
		boolean deleted = true;
		try {
			dao.deleteById(user1Id);
			dao.deleteById(user2Id);
		} catch (Exception e) {
			deleted = false;
		}
		Assert.assertTrue(deleted);
	}

}
