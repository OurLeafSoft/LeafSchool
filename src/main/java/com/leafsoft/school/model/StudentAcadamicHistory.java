package com.leafsoft.school.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the StudentAcadamicHistory database table.
 * 
 */
@Entity
@NamedQuery(name="StudentAcadamicHistory.findAll", query="SELECT s FROM StudentAcadamicHistory s")
public class StudentAcadamicHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int studenthistoryid;

	private byte historytype;
	
	private double grade;
	
	private double mark;

	//bi-directional many-to-one association to StudentCourseHistory
	@ManyToOne
	@JoinColumn(name="studentcoursehistoryid")
	private StudentCourseHistory studentCourseHistory;

	//bi-directional many-to-one association to ExamSubjectMapping
	@ManyToOne
	@JoinColumn(name="examsubjectid")
	private ExamSubjectMapping examSubjectMapping;

	//bi-directional many-to-one association to StudentDetail
	@ManyToOne
	@JoinColumn(name="studentid")
	private StudentDetail studentDetail;

	public StudentAcadamicHistory() {
	}

	public int getStudenthistoryid() {
		return this.studenthistoryid;
	}

	public void setStudenthistoryid(int studenthistoryid) {
		this.studenthistoryid = studenthistoryid;
	}

	public byte getHistorytype() {
		return this.historytype;
	}

	public void setHistorytype(byte historytype) {
		this.historytype = historytype;
	}

	public double getGrade() {
		return this.grade;
	}
	
	public void setGrade(double grade) {
		this.grade = grade;
	}
	
	public double getMark() {
		return this.mark;
	}
	
	public void setMark(double mark) {
		this.mark = mark;
	}
	
	public StudentCourseHistory getStudentCourseHistory() {
		return this.studentCourseHistory;
	}

	public void setStudentCourseHistory(StudentCourseHistory studentCourseHistory) {
		this.studentCourseHistory = studentCourseHistory;
	}

	public ExamSubjectMapping getExamSubjectMapping() {
		return this.examSubjectMapping;
	}

	public void setExamSubjectMapping(ExamSubjectMapping examSubjectMapping) {
		this.examSubjectMapping = examSubjectMapping;
	}

	public StudentDetail getStudentDetail() {
		return this.studentDetail;
	}

	public void setStudentDetail(StudentDetail studentDetail) {
		this.studentDetail = studentDetail;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StudentAcadamicHistory [studenthistoryid=" + studenthistoryid + ", historytype=" + historytype
				+ ", grade=" + grade + ", mark=" + mark + ", studentCourseHistory=" + studentCourseHistory
				+ ", examSubjectMapping=" + examSubjectMapping + ", studentDetail=" + studentDetail + "]";
	}

}