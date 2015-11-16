package j.index.module.setting.JournalData.entity;

import j.index.core.entity.GenericEntity;
import j.index.core.enums.GraduateLevel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

@Entity
@Table(name = "JOURNAL_DATA")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class JournalData extends GenericEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8359789887877536098L;

	public JournalData() {
	}

	public JournalData(String authorChName, String authorEnName,
			String paperChTitle, String paperEnTitle, String professorChName,
			String professorEnName, GraduateLevel graduateLevel,
			String college, String department, int publishYr, int graduatedYr,
			String chKeyWords, String enKeyWords, String chAbstract,
			String enAbstract, String url
	// , String theme, String subTheme, String depTheme
	) {
		this.authorChName = authorChName;
		this.authorEnName = authorEnName;
		this.paperChTitle = paperChTitle;
		this.paperEnTitle = paperEnTitle;
		this.professorChName = professorChName;
		this.professorEnName = professorEnName;
		this.graduateLevel = graduateLevel;
		this.college = college;
		this.department = department;
		this.publishYr = publishYr;
		this.graduatedYr = graduatedYr;
		this.chKeyWords = chKeyWords;
		this.enKeyWords = enKeyWords;
		this.chAbstract = chAbstract;
		this.enAbstract = enAbstract;
		this.url = url;
		// this.theme = theme;
		// this.subTheme = subTheme;
		// this.depTheme = depTheme;
	}

	// 研究生
	@Column(name = "AUTHOR_CH_NAME")
	private String authorChName;

	// 研究生(外文)
	@Column(name = "AUTHOR_EN_NAME")
	private String authorEnName;

	// 論文名稱
	@Column(name = "PAPER_CH_TITLE")
	private String paperChTitle;

	// 論文名稱(外文)
	@Column(name = "PAPER_EN_TITLE")
	private String paperEnTitle;

	// 指導教授
	@Column(name = "PROFESSOR_CH_NAME")
	private String professorChName;

	// 指導教授(外文)
	@Column(name = "PROFESSOR_EN_NAME")
	private String professorEnName;

	// 學位類別
	@Column(name = "GRADUATE_LEVEL")
	@Enumerated(EnumType.STRING)
	private GraduateLevel graduateLevel;

	// 校院名稱
	@Column(name = "COLLEGE")
	private String college;

	// 系所名稱
	@Column(name = "DEPARTMENT")
	private String department;

	// 論文出版年
	@Column(name = "PUBLISH_YR")
	private int publishYr;

	// 畢業學年度
	@Column(name = "GRADUATE_YR")
	private int graduatedYr;

	// 中文關鍵詞
	@Column(name = "CH_KEY_WORDS")
	@Type(type = "text")
	private String chKeyWords;

	// 英文關鍵詞
	@Column(name = "EN_KEY_WORDS")
	@Type(type = "text")
	private String enKeyWords;

	// 中文摘要
	@Column(name = "CH_ABSTRACT")
	@Type(type = "text")
	private String chAbstract;

	// 英文摘要
	@Column(name = "EN_ABSTRACT")
	@Type(type = "text")
	private String enAbstract;

	// 本論文永久網址
	@Column(name = "URL", unique = true)
	private String url;

	// 學科主題
	// @Column(name = "THEME")
	// private String theme;

	// 學科主題細項
	// @Column(name = "SUB_THEME")
	// private String subTheme;

	// 學科系所
	// @Column(name = "DEP_THEME")
	// private String depTheme;

	/**
	 * @return the authorChName
	 */
	public String getAuthorChName() {
		return authorChName;
	}

	/**
	 * @param authorChName
	 *            the authorChName to set
	 */
	public void setAuthorChName(String authorChName) {
		this.authorChName = authorChName;
	}

	/**
	 * @return the authorEnName
	 */
	public String getAuthorEnName() {
		return authorEnName;
	}

	/**
	 * @param authorEnName
	 *            the authorEnName to set
	 */
	public void setAuthorEnName(String authorEnName) {
		this.authorEnName = authorEnName;
	}

	/**
	 * @return the paperChTitle
	 */
	public String getPaperChTitle() {
		return paperChTitle;
	}

	/**
	 * @param paperChTitle
	 *            the paperChTitle to set
	 */
	public void setPaperChTitle(String paperChTitle) {
		this.paperChTitle = paperChTitle;
	}

	/**
	 * @return the paperEnTitle
	 */
	public String getPaperEnTitle() {
		return paperEnTitle;
	}

	/**
	 * @param paperEnTitle
	 *            the paperEnTitle to set
	 */
	public void setPaperEnTitle(String paperEnTitle) {
		this.paperEnTitle = paperEnTitle;
	}

	/**
	 * @return the professorChName
	 */
	public String getProfessorChName() {
		return professorChName;
	}

	/**
	 * @param professorChName
	 *            the professorChName to set
	 */
	public void setProfessorChName(String professorChName) {
		this.professorChName = professorChName;
	}

	/**
	 * @return the professorEnName
	 */
	public String getProfessorEnName() {
		return professorEnName;
	}

	/**
	 * @param professorEnName
	 *            the professorEnName to set
	 */
	public void setProfessorEnName(String professorEnName) {
		this.professorEnName = professorEnName;
	}

	/**
	 * @return the graduateLevel
	 */
	public GraduateLevel getGraduateLevel() {
		return graduateLevel;
	}

	/**
	 * @param graduateLevel
	 *            the graduateLevel to set
	 */
	public void setGraduateLevel(GraduateLevel graduateLevel) {
		this.graduateLevel = graduateLevel;
	}

	/**
	 * @return the college
	 */
	public String getCollege() {
		return college;
	}

	/**
	 * @param college
	 *            the college to set
	 */
	public void setCollege(String college) {
		this.college = college;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return the publishYr
	 */
	public int getPublishYr() {
		return publishYr;
	}

	/**
	 * @param publishYr
	 *            the publishYr to set
	 */
	public void setPublishYr(int publishYr) {
		this.publishYr = publishYr;
	}

	/**
	 * @return the graduatedYr
	 */
	public int getGraduatedYr() {
		return graduatedYr;
	}

	/**
	 * @param graduatedYr
	 *            the graduatedYr to set
	 */
	public void setGraduatedYr(int graduatedYr) {
		this.graduatedYr = graduatedYr;
	}

	/**
	 * @return the chKeyWords
	 */
	public String getChKeyWords() {
		return chKeyWords;
	}

	/**
	 * @param chKeyWords
	 *            the chKeyWords to set
	 */
	public void setChKeyWords(String chKeyWords) {
		this.chKeyWords = chKeyWords;
	}

	/**
	 * @return the enKeyWords
	 */
	public String getEnKeyWords() {
		return enKeyWords;
	}

	/**
	 * @param enKeyWords
	 *            the enKeyWords to set
	 */
	public void setEnKeyWords(String enKeyWords) {
		this.enKeyWords = enKeyWords;
	}

	/**
	 * @return the chAbstract
	 */
	public String getChAbstract() {
		return chAbstract;
	}

	/**
	 * @param chAbstract
	 *            the chAbstract to set
	 */
	public void setChAbstract(String chAbstract) {
		this.chAbstract = chAbstract;
	}

	/**
	 * @return the enAbstract
	 */
	public String getEnAbstract() {
		return enAbstract;
	}

	/**
	 * @param enAbstract
	 *            the enAbstract to set
	 */
	public void setEnAbstract(String enAbstract) {
		this.enAbstract = enAbstract;
	}

	/**
	 * @return the site
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param site
	 *            the site to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JournalData [authorChName=" + authorChName + ", authorEnName="
				+ authorEnName + ", paperChTitle=" + paperChTitle
				+ ", paperEnTitle=" + paperEnTitle + ", professorChName="
				+ professorChName + ", professorEnName=" + professorEnName
				+ ", graduateLevel=" + graduateLevel + ", college=" + college
				+ ", department=" + department + ", publishYr=" + publishYr
				+ ", graduatedYr=" + graduatedYr + ", chKeyWords=" + chKeyWords
				+ ", enKeyWords=" + enKeyWords + ", chAbstract=" + chAbstract
				+ ", enAbstract=" + enAbstract + ", url=" + url + "]";
	}
}
