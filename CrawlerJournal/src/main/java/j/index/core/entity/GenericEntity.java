/**
 * 
 */
package j.index.core.entity;

import j.index.core.enums.SystemStatus;
import j.index.core.security.secUser.entity.SecUser;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shock
 *
 */
@MappedSuperclass
public abstract class GenericEntity implements Entity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8739689797669392643L;

	/** The log. */
	@Transient
	protected final transient Logger log = LoggerFactory.getLogger(getClass());

	/** The id. */
	@Id  
	@GeneratedValue(strategy=GenerationType.IDENTITY)   
	@Column(name="ID", unique=true, nullable=false, insertable=true, updatable=false, precision=20)
	private Long id;

	/** The created by. */
	@Column(name="created_By", updatable = false)
	private Long createdBy;
	
	@ManyToOne
	@Transient
	private SecUser createdUser;

	/** The created date. */
	@Column(name="created_Date", updatable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime createdDate;

	/** The last modified by. */
	@Column(name="last_Modified_By")
	private Long lastModifiedBy;

	@ManyToOne
	@Transient
	private SecUser lastModifiedUser;
	
	/** The last modified date. */
	@Column(name="last_Modified_Date")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime lastModifiedDate;

	/** The status. */
	@Column(name="status")
	@Enumerated(EnumType.STRING)
	private SystemStatus status;

	/** The version no. */
	@Column(name="version_No")
	private Long versionNo;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@JSON(serialize=false) 
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	@JSON(serialize=false) 
	public Long getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(Long lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	@JSON(serialize=false) 
	public LocalDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public SystemStatus getStatus() {
		return status;
	}

	public void setStatus(SystemStatus status) {
		this.status = status;
	}

	public Long getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Long versionNo) {
		this.versionNo = versionNo;
	}

	public SecUser getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(SecUser createdUser) {
		this.createdUser = createdUser;
	}

	public SecUser getLastModifiedUser() {
		return lastModifiedUser;
	}

	public void setLastModifiedUser(SecUser lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}

	/**
	 * check entity is new or not.
	 * 
	 * @return true, if is new
	 */
	public boolean isNew() {
		return id == null;
	}

	/**
	 * initial insert
	 * <p>
	 * initial id, tpmId, create, modify and status when insert <br>
	 * <p>
	 * .
	 * 
	 * @param user
	 *            the user
	 */
	public void initInsert(SecUser user) {
		if (status == null)
			initInsert(user, SystemStatus.Y);
		else if (status != null)
			initInsert(user, status);
	}

	/**
	 * initial insert
	 * <p>
	 * initial id, tpmId, create, modify and status when insert <br>
	 * <p>
	 * .
	 * 
	 * @param user
	 *            the user
	 * @param sysStatus
	 *            the sys status
	 * @param bpmId
	 *            the bpm id
	 */
	public void initInsert(SecUser user, SystemStatus sysStatus) {
		this.setVersionNo(1L);
		this.setStatus(sysStatus);
		this.setCreatedBy(user.getId());
		this.setCreatedDate(new LocalDateTime());
		this.setLastModifiedBy(getCreatedBy());
		this.setLastModifiedDate(getCreatedDate());
	}

	/**
	 * initial insert
	 * <p>
	 * initial id, tpmId, create, modify and status from main entity when insert
	 * <br>
	 * <p>
	 * .
	 * 
	 * @param main
	 *            entity
	 */
//	public <T extends GenericEntity> void initInsert(T entity) {
//		initInsert(entity, SystemStatus.Y);
//	}

	/**
	 * initial insert
	 * <p>
	 * initial id, tpmId, create, modify and status from main entity when insert
	 * <br>
	 * <p>
	 * .
	 * 
	 * @param main
	 *            entity
	 * @param sysStatus
	 *            the sys status
	 * @param bpmId
	 *            the bpm id
	 */
//	public <T extends GenericEntity> void initInsert(T entity, SystemStatus sysStatus) {
//		this.setVersionNo(1L);
//		this.setStatus(sysStatus);
//		this.setCreatedBy(entity.getCreatedBy());
//		this.setCreatedDate(new LocalDateTime());
//		this.setLastModifiedBy(getCreatedBy());
//		this.setLastModifiedDate(getCreatedDate());
//	}

	/**
	 * initial update
	 * <p>
	 * initial modify, status when update <br>
	 * <p>
	 * .
	 * 
	 * @param user
	 *            the user
	 */
	public void initUpdate(SecUser user) {
		initUpdate(user, status);
	}

	/**
	 * initial update
	 * <p>
	 * initial modify, status when update <br>
	 * <p>
	 * .
	 * 
	 * @param user
	 *            the user
	 * @param sysStatus
	 *            the sys status
	 */
	public void initUpdate(SecUser user, SystemStatus sysStatus) {
		this.setStatus(sysStatus);
		this.setVersionNo(versionNo == null ? 1 : versionNo + 1);
		this.setLastModifiedBy(user.getId());
		this.setLastModifiedDate(new LocalDateTime());
	}

	/**
	 * initial update
	 * <p>
	 * initial modify, status from main entity when update <br>
	 * <p>
	 * .
	 * 
	 * @param main
	 *            entity
	 */
	public <T extends GenericEntity> void initUpdate(T entity) {
		initUpdate(entity, status);
	}

	/**
	 * initial update
	 * <p>
	 * initial modify, status from main entity when update <br>
	 * <p>
	 * .
	 * 
	 * @param main
	 *            entity
	 * @param sysStatus
	 *            the sys status
	 */
	public <T extends GenericEntity> void initUpdate(T entity, SystemStatus sysStatus) {
		this.setStatus(sysStatus);
		this.setVersionNo(entity.getVersionNo() == null ? 1 : entity.getVersionNo() + 1);
		this.setLastModifiedBy(entity.getLastModifiedBy());
		this.setLastModifiedDate(new LocalDateTime());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object other) {
		return EqualsBuilder.reflectionEquals(this, other);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.DEFAULT_STYLE);
	}
	
}
