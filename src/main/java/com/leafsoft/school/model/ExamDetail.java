package com.leafsoft.school.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ExamDetails database table.
 * 
 */
@Entity
@Table(name="ExamDetails")
@NamedQuery(name="ExamDetail.findAll", query="SELECT e FROM ExamDetail e")
public class ExamDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int examid;

	private String examname;

	private byte status;

	//bi-directional many-to-one association to ExamType
	@ManyToOne
	@JoinColumn(name="examtypeid")
	private ExamType examType;

	//bi-directional many-to-one association to ExamSubjectMapping
	@OneToMany(mappedBy="examDetail")
	private List<ExamSubjectMapping> examSubjectMappings;

	public ExamDetail() {
	}

	public int getExamid() {
		return this.examid;
	}

	public void setExamid(int examid) {
		this.examid = examid;
	}

	public String getExamname() {
		return this.examname;
	}

	public void setExamname(String examname) {
		this.examname = examname;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public ExamType getExamType() {
		return this.examType;
	}

	public void setExamType(ExamType examType) {
		this.examType = examType;
	}

	public List<ExamSubjectMapping> getExamSubjectMappings() {
		return this.examSubjectMappings;
	}

	public void setExamSubjectMappings(List<ExamSubjectMapping> examSubjectMappings) {
		this.examSubjectMappings = examSubjectMappings;
	}

	public ExamSubjectMapping addExamSubjectMapping(ExamSubjectMapping examSubjectMapping) {
		getExamSubjectMappings().add(examSubjectMapping);
		examSubjectMapping.setExamDetail(this);

		return examSubjectMapping;
	}

	public ExamSubjectMapping removeExamSubjectMapping(ExamSubjectMapping examSubjectMapping) {
		getExamSubjectMappings().remove(examSubjectMapping);
		examSubjectMapping.setExamDetail(null);

		return examSubjectMapping;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExamDetail [examid=" + examid + ", examname=" + examname + ", status=" + status + ", examType="
				+ examType + ", examSubjectMappings=" + examSubjectMappings + "]";
	}

}