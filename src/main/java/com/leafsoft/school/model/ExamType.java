package com.leafsoft.school.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ExamType database table.
 * 
 */
@Entity
@NamedQuery(name="ExamType.findAll", query="SELECT e FROM ExamType e")
public class ExamType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int examtypeid;

	private String examtypename;

	private byte status;

	//bi-directional many-to-one association to ExamDetail
	@OneToMany(mappedBy="examType")
	private List<ExamDetail> examDetails;

	public ExamType() {
	}

	public int getExamtypeid() {
		return this.examtypeid;
	}

	public void setExamtypeid(int examtypeid) {
		this.examtypeid = examtypeid;
	}

	public String getExamtypename() {
		return this.examtypename;
	}

	public void setExamtypename(String examtypename) {
		this.examtypename = examtypename;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public List<ExamDetail> getExamDetails() {
		return this.examDetails;
	}

	public void setExamDetails(List<ExamDetail> examDetails) {
		this.examDetails = examDetails;
	}

	public ExamDetail addExamDetail(ExamDetail examDetail) {
		getExamDetails().add(examDetail);
		examDetail.setExamType(this);

		return examDetail;
	}

	public ExamDetail removeExamDetail(ExamDetail examDetail) {
		getExamDetails().remove(examDetail);
		examDetail.setExamType(null);

		return examDetail;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExamType [examtypeid=" + examtypeid + ", examtypename=" + examtypename + ", status=" + status
				+ ", examDetails=" + examDetails + "]";
	}

}