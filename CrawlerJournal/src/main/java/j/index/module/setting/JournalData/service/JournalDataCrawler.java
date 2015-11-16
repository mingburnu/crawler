package j.index.module.setting.JournalData.service;

import j.index.module.setting.JournalData.entity.JournalData;
import j.index.core.enums.GraduateLevel;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;

@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class JournalDataCrawler extends WebCrawler {

	@Autowired
	private JournalData journalData;

	@Autowired
	private JournalDataService journalDataService;

	private static LinkedHashSet<JournalData> journalDatasSet = new LinkedHashSet<JournalData>();
	private static LinkedHashSet<String> sitesSet = new LinkedHashSet<String>();
	private static LinkedHashSet<String> sitesRepeatSet = new LinkedHashSet<String>();
	private static ArrayList<String> objectRepeatList = new ArrayList<String>();

	private final static Pattern FILTERS = Pattern
			.compile(".*(\\.(css|js|bmp|gif|jpe?g"
					+ "|png|tiff?|mid|mp2|mp3|mp4"
					+ "|wav|avi|mov|mpeg|ram|m4v|pdf"
					+ "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

	/**
	 * You should implement this function to specify whether the given url
	 * should be crawled or not (based on your crawling logic).
	 */
	@Override
	public boolean shouldVisit(WebURL url) {
		String href = null;
		String urlStart = null;
		try {
			urlStart = URLDecoder
					.decode("http://127.0.0.1/%E5%95%86%E6%A5%AD%E5%8F%8A%E7%AE%A1%E7%90%86%E5%AD%B8%E9%96%80/",
							"utf-8");
			href = URLDecoder.decode(url.getURL().toLowerCase(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return !FILTERS.matcher(href).matches() && href.startsWith(urlStart);
	}

	/**
	 * This function is called when a page is fetched and ready to be processed
	 * by your program.
	 */
	@Override
	public void visit(Page page) {
		String passUrl = null;
		String webURL = page.getWebURL().getURL();
		try {
			passUrl = URLDecoder.decode(webURL, "utf8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] urlFragments = passUrl.split("/");

		if (urlFragments.length == 7 && passUrl.endsWith(".htm")) {
			if (sitesSet.contains(passUrl) == false
					&& sitesRepeatSet.contains(passUrl) == false) {
				Document doc;
				try {
					doc = Jsoup.connect(webURL).get();

					Elements authorChNameTh = doc.select(":containsOwn(研究生:)");
					Elements authorEnNameTh = doc
							.select(":containsOwn(研究生(外文):)");
					Elements paperChTitleTh = doc.select(":containsOwn(論文名稱:)");
					Elements paperEnTitleTh = doc
							.select(":containsOwn(論文名稱(外文):)");
					Elements professorChNameTh = doc
							.select(":containsOwn(指導教授:)");
					Elements professorEnNameTh = doc
							.select(":containsOwn(指導教授(外文):)");
					Elements graduateLevelTh = doc
							.select(":containsOwn(學位類別:)");
					Elements collegeTh = doc.select(":containsOwn(校院名稱:)");
					Elements departmentTh = doc.select(":containsOwn(系所名稱:)");
					Elements publishYrTh = doc.select(":containsOwn(論文出版年:)");
					Elements graduatedYrTh = doc.select(":containsOwn(畢業學年度:)");
					Elements chKeyWordsTh = doc.select(":containsOwn(中文關鍵詞:)");
					Elements enKeyWordsTh = doc.select(":containsOwn(外文關鍵詞:)");
					Elements AbstractDiv = doc
							.select("[style=padding:10px;text-align:justify;]");
					Elements urlInput = doc.select("#fe_text1");

					String authorChName;
					if (authorChNameTh.size() != 0) {
						authorChName = authorChNameTh.get(0).parent()
								.children().get(1).text();
					} else {
						authorChName = null;
					}

					String authorEnName;
					if (authorEnNameTh.size() != 0) {
						authorEnName = authorEnNameTh.get(0).parent()
								.children().get(1).text();
					} else {
						authorEnName = null;
					}

					String paperChTitle;
					if (paperChTitleTh.size() != 0) {
						paperChTitle = paperChTitleTh.get(0).parent()
								.children().get(1).text();
					} else {
						paperChTitle = null;
					}

					String paperEnTitle;
					if (paperEnTitleTh.size() != 0) {
						paperEnTitle = paperEnTitleTh.get(0).parent()
								.children().get(1).text();
					} else {
						paperEnTitle = null;
					}

					String professorChName;
					if (professorChNameTh.size() != 0) {
						professorChName = professorChNameTh.get(0).parent()
								.children().get(1).text();
					} else {
						professorChName = null;
					}

					String professorEnName;
					if (professorEnNameTh.size() != 0) {
						professorEnName = professorEnNameTh.get(0).parent()
								.children().get(1).text();
					} else {
						professorEnName = null;
					}

					String graduateLevel;
					if (graduateLevelTh.size() != 0) {
						graduateLevel = graduateLevelTh.get(0).parent()
								.children().get(1).text();
					} else {
						graduateLevel = null;
					}

					String college;
					if (collegeTh.size() != 0) {
						college = collegeTh.get(0).parent().children().get(1)
								.text();
					} else {
						college = null;
					}

					String department;
					if (departmentTh.size() != 0) {
						department = departmentTh.get(0).parent().children()
								.get(1).text();
					} else {
						department = null;
					}

					String publishYr;
					if (publishYrTh.size() != 0) {
						publishYr = publishYrTh.get(0).parent().children()
								.get(1).text();
					} else {
						publishYr = null;
					}

					String graduatedYr;
					if (graduatedYrTh.size() != 0) {
						graduatedYr = graduatedYrTh.get(0).parent().children()
								.get(1).text();
					} else {
						graduatedYr = null;
					}

					String url = urlInput.val();

					ArrayList<String> chKeyWords = new ArrayList<String>();
					String chKeyWordsString;
					if (chKeyWordsTh.size() != 0) {
						Elements chKeyWordsA = chKeyWordsTh.get(0).parent()
								.children().get(1).children();
						for (int i = 0; i < chKeyWordsA.size(); i++) {
							chKeyWords.add(chKeyWordsA.get(i).text());
						}
						chKeyWordsString = chKeyWords.toString();
					} else {
						chKeyWordsString = null;
					}

					ArrayList<String> enKeyWords = new ArrayList<String>();
					String enKeyWordsString;
					if (enKeyWordsTh.size() != 0) {
						Elements enKeyWordsA = enKeyWordsTh.get(0).parent()
								.children().get(1).children();
						for (int i = 0; i < enKeyWordsA.size(); i++) {
							enKeyWords.add(enKeyWordsA.get(i).text());
						}
						enKeyWordsString = enKeyWords.toString();
					} else {
						enKeyWordsString = null;
					}

					String[] abstractText = AbstractDiv.html().split("<br />");
					Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
					String chAbstract = "";
					if (abstractText != null) {
						for (int i = 0; i < abstractText.length; i++) {
							if (abstractText[i].trim().endsWith("。")
									|| abstractText[i].trim().endsWith(".")) {
								ArrayList<Boolean> list = new ArrayList<Boolean>();
								for (int j = 0; j < abstractText[i].trim()
										.length(); j++) {
									Matcher matcher = pattern
											.matcher(abstractText[i].trim()
													.substring(j, j + 1));
									if (matcher.matches()) {
										list.add(matcher.matches());
									}
								}
								if (list.size() > 0) {
									chAbstract = chAbstract + abstractText[i];
								}
							}
						}
					}

					String[] abstractHtml = AbstractDiv.html().split("<br />");
					String enAbstract = "";
					if (abstractHtml != null) {
						for (int i = 0; i < abstractHtml.length; i++) {
							if (abstractHtml[i].trim().endsWith(".")) {
								ArrayList<Boolean> list = new ArrayList<Boolean>();
								for (int j = 0; j < abstractHtml[i].trim()
										.length(); j++) {
									Matcher matcher = pattern
											.matcher(abstractText[i].trim()
													.substring(j, j + 1));
									if (matcher.matches()) {
										list.add(matcher.matches());
									}
								}
								if (list.size() == 0) {
									enAbstract = enAbstract + abstractHtml[i];
								}
							}
						}
					}

					journalDatasSet.add(new JournalData(authorChName,
							authorEnName, paperChTitle, paperEnTitle,
							professorChName, professorEnName, GraduateLevel
									.valueOf(graduateLevel), college,
							department, Integer.parseInt(publishYr), Integer
									.parseInt(graduatedYr), chKeyWordsString,
							enKeyWordsString, chAbstract, enAbstract, url));

					sitesSet.add(passUrl);
					System.out.println(passUrl);

					/**
					 * search repeat objects
					 */
					if (journalDatasSet.size() != sitesSet.size()) {
						JournalData[] journalDatasArray = new JournalData[journalDatasSet
								.size()];
						journalDatasSet.toArray(journalDatasArray);
						String[] sitesArray = new String[sitesSet.size()];
						sitesSet.toArray(sitesArray);

						for (int i = 0; i < journalDatasArray.length; i++) {
							if (journalDatasArray[i].getUrl().equals(url)) {
								objectRepeatList.add(sitesArray[i] + "與"
										+ sitesArray[sitesArray.length - 1]
										+ "內容重複");
							}
						}
						sitesRepeatSet.add(sitesArray[sitesArray.length - 1]);
						sitesSet.remove(sitesArray[sitesArray.length - 1]);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @return the journalDatasSet
	 */
	public static LinkedHashSet<JournalData> getJournalDatasSet() {
		return journalDatasSet;
	}

	/**
	 * @return the sitesList
	 */
	public static LinkedHashSet<String> getSitesSet() {
		return sitesSet;
	}

	/**
	 * @return the objectRepeatList
	 */
	public static ArrayList<String> getObejectRepeatList() {
		return objectRepeatList;
	}
}
