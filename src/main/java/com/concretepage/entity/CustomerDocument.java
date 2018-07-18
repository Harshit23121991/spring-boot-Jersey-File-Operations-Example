package com.concretepage.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "CUSTOMER_DOCUMENT")
@DiscriminatorValue("CUSTOMER_DOCUMENT")
public class CustomerDocument {

	@Id
	@Column(name = "ID_CUSTOMER_DOCUMENT", nullable = false, length = 36)
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@GeneratedValue(generator = "system-uuid")
	private String oid;

	@Column(name = "ID_RT_PROCESS", nullable = false, length = 36)
	private String processId;

	@Column(name = "COD_DOCUMENT_TYPE", nullable = false, length = 32)
	private String documentType;

	@Column(name = "NAM_FILE", nullable = false, length = 255)
	private String filename;

	@Column(name = "BIN_FILE", nullable = false, length = 10 * 1024 * 1024)
	@Type(type = "org.hibernate.type.MaterializedBlobType")
	private byte[] documentContent;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public byte[] getDocumentContent() {
		return documentContent;
	}

	public void setDocumentContent(byte[] documentContent) {
		this.documentContent = documentContent;
	}

}
