package j.index.core.security.secUser.service;

import j.index.core.GenericTest;
import j.index.core.enums.SystemStatus;
import j.index.core.model.DataSet;
import j.index.core.security.secUser.entity.SecUser;

import java.util.List;






//







import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * SecUserServiceTest
 * @author David Hsu
 * @version 2014/3/11
 */
public class SecUserServiceTest extends GenericTest {

	@Autowired
	private SecUserService service;
	
	@Autowired
	private DataSet<SecUser> ds;
	
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
		user1.setStatus(SystemStatus.Y);
		user1.setUserPassword("test");
		
		SecUser dbUser1 = service.save(user1, user1);
		final Long user1Id = dbUser1.getId();
		Assert.assertEquals(user1Code, dbUser1.getUserCode());
		
		//Save user 2
		SecUser user2 = new SecUser();
		user2.setUserCode(user2Code);
		user2.setUserName("Admin2");
		user2.setCreatedBy(1L);
		user2.setLastModifiedBy(1L);
		user2.setStatus(SystemStatus.Y);
		user2.setUserPassword("test");
		
		SecUser dbUser2 = service.save(user2, user1);
		final Long user2Id = dbUser2.getId();
		Assert.assertEquals(user2Code, dbUser2.getUserCode());
		
		//Query by id
		dbUser1 = service.getById(user1Id);
		Assert.assertEquals(user1Code, dbUser1.getUserCode());
		
		//update
		final String user1UpdName = "Admin_test";
		dbUser1.setUserName("Admin_test");
		dbUser1 = service.update(dbUser1, user1);
		Assert.assertEquals(user1UpdName, dbUser1.getUserName());
				
		//query by condition
		SecUser queryUser = new SecUser();
		queryUser.setUserName("min_te");
		ds.setEntity(queryUser);
		ds = service.getByRestrictions(ds);
		List<SecUser> users = ds.getResults();
		Assert.assertEquals(1, users.size());
		Assert.assertEquals(user1UpdName, users.get(0).getUserName());
				
		//delete by id
		boolean deleted = true;
		try {
			service.deleteById(user1Id);
			service.deleteById(user2Id);
		} catch (Exception e) {
			deleted = false;
		}
		Assert.assertTrue(deleted);
	}

}

