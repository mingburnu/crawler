package j.index.module.setting.JournalData.service;

import java.util.ArrayList;
import java.util.Iterator;

import j.index.core.dao.GenericDao;
import j.index.core.dao.IiiRestrictions;
import j.index.core.model.DataSet;
import j.index.core.service.GenericService;
import j.index.core.util.IiiBeanFactory;
import j.index.module.setting.JournalData.entity.JournalData;
import j.index.module.setting.JournalData.entity.JournalDataDao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class JournalDataService extends GenericService<JournalData> {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private JournalDataDao dao;

	@Override
	public DataSet<JournalData> getByRestrictions(DataSet<JournalData> ds)
			throws Exception {
		Assert.notNull(ds);
		Assert.notNull(ds.getEntity());

		IiiRestrictions restrictions = IiiBeanFactory.getIiiRestrictions();
		return dao.findByRestrictions(restrictions, ds);
	}

	@Override
	protected GenericDao<JournalData> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@SuppressWarnings("rawtypes")
	public ArrayList<JournalData> queryAllByCriteria() throws Exception {
		ArrayList<JournalData> journalDataList = new ArrayList<JournalData>();
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(JournalData.class);
		Iterator iterator = criteria.list().iterator();
		while (iterator.hasNext()) {
			JournalData journalData = (JournalData) iterator.next();
			journalDataList.add(journalData);
		}
		return journalDataList;
	}
}
