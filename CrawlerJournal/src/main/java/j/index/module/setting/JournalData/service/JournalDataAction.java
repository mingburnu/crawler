package j.index.module.setting.JournalData.service;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import j.index.core.model.DataSet;
import j.index.core.web.GenericCRUDAction;
import j.index.module.setting.JournalData.entity.JournalData;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

@Controller
@SuppressWarnings("serial")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class JournalDataAction extends GenericCRUDAction<JournalData> {

	@Autowired
	private JournalDataService journalDataService;

	@Override
	public void validateSave() throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void validateUpdate() throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void validateDelete() throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public String query() throws Exception {
		try {
			if (getEntity().getId() != null) {
				JournalData journalData = journalDataService
						.getById(getEntity().getId());
				setEntity(journalData);
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
			DataSet<JournalData> dsJournalData = journalDataService
					.getByRestrictions(initDataSet());
			setDs(dsJournalData);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		return LIST;
	}

	@Override
	public String save() throws Exception {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date dateStart = new Date();

			// crawl Storage Folder(Dev.)
			// String crawlStorageFolder = "C:\\root";

			// crawl Storage Folder(Pro.)
			File file = new File("position.tmp");
			String binPath = file.getAbsolutePath();
			System.out.println("binPath " + binPath);

			String[] pathSplit = binPath.split("bin");
			String crawlStorageFolder = pathSplit[0] + "scan";

			int numberOfCrawlers = 1;

			CrawlConfig config = new CrawlConfig();
			config.setCrawlStorageFolder(crawlStorageFolder);

			/*
			 * Instantiate the controller for this crawl.
			 */
			PageFetcher pageFetcher = new PageFetcher(config);
			RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
			RobotstxtServer robotstxtServer = new RobotstxtServer(
					robotstxtConfig, pageFetcher);
			CrawlController controller = new CrawlController(config,
					pageFetcher, robotstxtServer);
			/*
			 * For each crawl, you need to add some seed urls. These are the
			 * first URLs that are fetched and then the crawler starts following
			 * links which are found in these pages
			 */

			controller
					.addSeed("http://127.0.0.1/%E5%95%86%E6%A5%AD%E5%8F%8A%E7%AE%A1%E7%90%86%E5%AD%B8%E9%96%80/");

			/*
			 * Start the crawl. This is a blocking operation, meaning that your
			 * code will reach the line after this only when crawling is
			 * finished.
			 */
			controller.start(JournalDataCrawler.class, numberOfCrawlers);
			System.out.println("final datas size:"
					+ JournalDataCrawler.getJournalDatasSet().size());

			if (JournalDataCrawler.getJournalDatasSet().size() != 0) {
				ArrayList<JournalData> journalDatasCatch = new ArrayList<JournalData>();

				Iterator<JournalData> iterator = JournalDataCrawler
						.getJournalDatasSet().iterator();

				while (iterator.hasNext()) {
					journalDatasCatch.add(iterator.next());
				}

				ArrayList<JournalData> journalDatasBase = journalDataService
						.queryAllByCriteria();

				for (int i = 0; i < journalDatasCatch.size(); i++) {
					if (checkExist(journalDatasBase, journalDatasCatch.get(i))) {
						System.out.println("Object exist!!");
					} else {
						JournalData journalData = getEntity();
						journalData = journalDataService.save(
								journalDatasCatch.get(i), getLoginUser());
						setEntity(journalData);
					}
				}

				System.out.println(dateFormat.format(dateStart));
				Date dateEnd = new Date();
				System.out.println(dateFormat.format(dateEnd));

				if (JournalDataCrawler.getObejectRepeatList().size() != 0) {
					for (int j = 0; j < JournalDataCrawler
							.getObejectRepeatList().size(); j++) {
						addActionMessage(JournalDataCrawler
								.getObejectRepeatList().get(j));
					}
				}
				addActionMessage("儲存成功");
			} else {
				addActionError("指定目錄無論文");
			}
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		return LIST;
	}

	@Override
	public String update() throws Exception {
		try {
			JournalData journalData = journalDataService.update(getEntity(),
					getLoginUser());
			setEntity(journalData);
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
			journalDataService.deleteById(getEntity().getId());
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		disableAllInput();
		addActionMessage("檔案已刪除");
		return DELETE;
	}

	public String writeAll() throws Exception {
		List<JournalData> journalDatas = new ArrayList<JournalData>();

		for (int i = 0; i < journalDataService.queryAllByCriteria().size(); i++) {
			journalDatas.add(journalDataService.queryAllByCriteria().get(i));
		}

		// Blank workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet sheet = workbook.createSheet("Journal Data");

		// This data needs to be written (Object[])
		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		data.put("0", new Object[] { "研究生", "研究生(外文)", "論文名稱", "論文名稱(外文)",
				"指導教授", "指導教授(外文)", "學位類別", "校院名稱", "系所名稱", "論文出版年", "畢業學年度",
				"中文關鍵詞", "英文關鍵詞", "中文摘要", "英文摘要", "本論文永久網址" });

		for (int i = 0; i < journalDatas.size(); i++) {
			data.put(
					String.valueOf(i + 1),
					new Object[] {
							journalDatas.get(i).getAuthorChName(),
							journalDatas.get(i).getAuthorEnName(),
							journalDatas.get(i).getPaperChTitle(),
							journalDatas.get(i).getPaperEnTitle(),
							journalDatas.get(i).getProfessorChName(),
							journalDatas.get(i).getProfessorEnName(),
							journalDatas.get(i).getGraduateLevel().name(),
							journalDatas.get(i).getCollege(),
							journalDatas.get(i).getDepartment(),
							String.valueOf(journalDatas.get(i).getPublishYr()),
							String.valueOf(journalDatas.get(i).getGraduatedYr()),
							journalDatas.get(i).getChKeyWords(),
							journalDatas.get(i).getEnKeyWords(),
							journalDatas.get(i).getChAbstract(),
							journalDatas.get(i).getEnAbstract(),
							journalDatas.get(i).getUrl() });
		}

		// Iterate over data and write to sheet
		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object[] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Integer)
					cell.setCellValue((Integer) obj);
			}
		}

		try {
			// Write the workbook in file system(Dev.)
			// File xlsx = new File(
			// "C:\\JSP\\workspace\\Maven-Journal-Index\\src\\main\\webapp\\resources\\media\\files\\"
			// + "excel_all" + ".xlsx");

			// Write the workbook in file system(Pro.)
			File file = new File("position.tmp");
			String binPath = file.getAbsolutePath();
			System.out.println("binPath " + binPath);

			String[] pathSplit = binPath.split("bin");

			String generatePath = pathSplit[0]
					+ "webapps\\journal\\resources\\media\\files\\";
			System.out.println("generatePath " + generatePath);

			File xlsx = new File(generatePath + "excel_all" + ".xlsx");

			FileOutputStream out = new FileOutputStream(xlsx);
			workbook.write(out);
			out.close();
			System.out.println("xlsl written successfully on disk.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		addActionMessage("檔案已匯出(全部)");
		return LIST;
	}

	public String writeDate() throws Exception {
		List<JournalData> journalDatas = new ArrayList<JournalData>();
		String startDate = getRequest().getParameter("start");
		String endDate = getRequest().getParameter("end");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",
				Locale.TAIWAN);

		if (isDate(startDate, endDate, dateFormat)) {
			Date start = dateFormat.parse(startDate);
			Date preEnd = dateFormat.parse(endDate);

			Calendar c = Calendar.getInstance();
			c.setTime(preEnd);
			c.add(Calendar.DATE, 1);
			Date end = c.getTime();

			for (int i = 0; i < journalDataService.queryAllByCriteria().size(); i++) {
				if (journalDataService.queryAllByCriteria().get(i)
						.getCreatedDate().toDate().compareTo(start) >= 0
						&& journalDataService.queryAllByCriteria().get(i)
								.getCreatedDate().toDate().compareTo(end) == -1) {
					journalDatas.add(journalDataService.queryAllByCriteria()
							.get(i));
				}
			}

			// Blank workbook
			XSSFWorkbook workbook = new XSSFWorkbook();

			// Create a blank sheet
			XSSFSheet sheet = workbook.createSheet("Journal Data");

			// This data needs to be written (Object[])
			Map<String, Object[]> data = new TreeMap<String, Object[]>();
			data.put("0", new Object[] { "研究生", "研究生(外文)", "論文名稱", "論文名稱(外文)",
					"指導教授", "指導教授(外文)", "學位類別", "校院名稱", "系所名稱", "論文出版年",
					"畢業學年度", "中文關鍵詞", "英文關鍵詞", "中文摘要", "英文摘要", "本論文永久網址" });

			for (int i = 0; i < journalDatas.size(); i++) {
				data.put(
						String.valueOf(i + 1),
						new Object[] {
								journalDatas.get(i).getAuthorChName(),
								journalDatas.get(i).getAuthorEnName(),
								journalDatas.get(i).getPaperChTitle(),
								journalDatas.get(i).getPaperEnTitle(),
								journalDatas.get(i).getProfessorChName(),
								journalDatas.get(i).getProfessorEnName(),
								journalDatas.get(i).getGraduateLevel().name(),
								journalDatas.get(i).getCollege(),
								journalDatas.get(i).getDepartment(),
								String.valueOf(journalDatas.get(i)
										.getPublishYr()),
								String.valueOf(journalDatas.get(i)
										.getGraduatedYr()),
								journalDatas.get(i).getChKeyWords(),
								journalDatas.get(i).getEnKeyWords(),
								journalDatas.get(i).getChAbstract(),
								journalDatas.get(i).getEnAbstract(),
								journalDatas.get(i).getUrl() });
			}

			// Iterate over data and write to sheet
			Set<String> keyset = data.keySet();
			int rownum = 0;
			for (String key : keyset) {
				Row row = sheet.createRow(rownum++);
				Object[] objArr = data.get(key);
				int cellnum = 0;
				for (Object obj : objArr) {
					Cell cell = row.createCell(cellnum++);
					if (obj instanceof String)
						cell.setCellValue((String) obj);
					else if (obj instanceof Integer)
						cell.setCellValue((Integer) obj);
				}
			}

			try {

				// Write the workbook in file system(Dev.)
				// File xlsx = new File(
				// "C:\\JSP\\workspace\\Maven-Journal-Index\\src\\main\\webapp\\resources\\media\\files\\"
				// + "excel_date" + ".xlsx");

				// Write the workbook in file system(Pro.)
				File file = new File("position.tmp");
				String binPath = file.getAbsolutePath();
				System.out.println("binPath " + binPath);

				String[] pathSplit = binPath.split("bin");

				String generatePath = pathSplit[0]
						+ "webapps\\journal\\resources\\media\\files\\";
				System.out.println("generatePath " + generatePath);

				File xlsx = new File(generatePath + "excel_date" + ".xlsx");

				FileOutputStream out = new FileOutputStream(xlsx);
				workbook.write(out);
				out.close();
				System.out.println("xlsl written successfully on disk.");
			} catch (Exception e) {
				e.printStackTrace();
			}
			addActionMessage("檔案已匯出(日期)");
			return LIST;
		} else {
			addActionError("請填寫正確的開始和結束日期，開始日不得大於結束日期。");
			return LIST;
		}
	}

	public boolean isDate(String startDate, String endDate,
			DateFormat dateFormat) {
		try {
			Date start = dateFormat.parse(startDate);
			Date preEnd = dateFormat.parse(endDate);
			Calendar c = Calendar.getInstance();
			c.setTime(start);
			c.setTime(preEnd);
			if (start.compareTo(preEnd) <= 0) {
				return true;
			} else {
				return false;
			}
		} catch (ParseException e) {
			return false;
		}
	}

	public boolean checkExist(ArrayList<JournalData> journalDatasBase,
			JournalData journalData) {
		for (int i = 0; i < journalDatasBase.size(); i++) {
			if (journalData.getUrl().trim()
					.equals(journalDatasBase.get(i).getUrl().trim())) {
				return true;
			}
		}
		return false;
	}
}
