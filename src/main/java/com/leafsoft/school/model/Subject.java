package com.leafsoft.school.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Subjects database table.
 * 
 */
@Entity
@Table(name="Subjects")
@NamedQuery(name="Subject.findAll", query="SELECT s FROM Subject s")
public class Subject implements Serializable {
	private static final long serialVersionUID = 1L;
	private int subjectid;
	private byte status;
	private String subjectname;
	private byte subjecttype;
	private List<StaffSubjectCourseHistory> staffSubjectCourseHistories;
	private List<StaffSubjectDetail> staffSubjectDetails1;
	private List<StaffSubjectDetail> staffSubjectDetails2;
	private List<StaffSubjectDetail> staffSubjectDetails3;

	public Subject() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	public int getSubjectid() {
		return this.subjectid;
	}

	public void setSubjectid(int subjectid) {
		this.subjectid = subjectid;
	}


	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}


	public String getSubjectname() {
		return this.subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}


	public byte getSubjecttype() {
		return this.subjecttype;
	}

	public void setSubjecttype(byte subjecttype) {
		this.subjecttype = subjecttype;
	}


	//bi-directional many-to-one association to StaffSubjectCourseHistory
	@OneToMany(mappedBy="subject")
	public List<StaffSubjectCourseHistory> getStaffSubjectCourseHistories() {
		return this.staffSubjectCourseHistories;
	}

	public void setStaffSubjectCourseHistories(List<StaffSubjectCourseHistory> staffSubjectCourseHistories) {
		this.staffSubjectCourseHistories = staffSubjectCourseHistories;
	}

	public StaffSubjectCourseHistory addStaffSubjectCourseHistory(StaffSubjectCourseHistory staffSubjectCourseHistory) {
		getStaffSubjectCourseHistories().add(staffSubjectCourseHistory);
		staffSubjectCourseHistory.setSubject(this);

		return staffSubjectCourseHistory;
	}

	public StaffSubjectCourseHistory removeStaffSubjectCourseHistory(StaffSubjectCourseHistory staffSubjectCourseHistory) {
		getStaffSubjectCourseHistories().remove(staffSubjectCourseHistory);
		staffSubjectCourseHistory.setSubject(null);

		return staffSubjectCourseHistory;
	}


	//bi-directional many-to-one association to StaffSubjectDetail
	@OneToMany(mappedBy="subject1")
	public List<StaffSubjectDetail> getStaffSubjectDetails1() {
		return this.staffSubjectDetails1;
	}

	public void setStaffSubjectDetails1(List<StaffSubjectDetail> staffSubjectDetails1) {
		this.staffSubjectDetails1 = staffSubjectDetails1;
	}

	public StaffSubjectDetail addStaffSubjectDetails1(StaffSubjectDetail staffSubjectDetails1) {
		getStaffSubjectDetails1().add(staffSubjectDetails1);
		staffSubjectDetails1.setSubject1(this);

		return staffSubjectDetails1;
	}

	public StaffSubjectDetail removeStaffSubjectDetails1(StaffSubjectDetail staffSubjectDetails1) {
		getStaffSubjectDetails1().remove(staffSubjectDetails1);
		staffSubjectDetails1.setSubject1(null);

		return staffSubjectDetails1;
	}


	//bi-directional many-to-one association to StaffSubjectDetail
	@OneToMany(mappedBy="subject2")
	public List<StaffSubjectDetail> getStaffSubjectDetails2() {
		return this.staffSubjectDetails2;
	}

	public void setStaffSubjectDetails2(List<StaffSubjectDetail> staffSubjectDetails2) {
		this.staffSubjectDetails2 = staffSubjectDetails2;
	}

	public StaffSubjectDetail addStaffSubjectDetails2(StaffSubjectDetail staffSubjectDetails2) {
		getStaffSubjectDetails2().add(staffSubjectDetails2);
		staffSubjectDetails2.setSubject2(this);

		return staffSubjectDetails2;
	}

	public StaffSubjectDetail removeStaffSubjectDetails2(StaffSubjectDetail staffSubjectDetails2) {
		getStaffSubjectDetails2().remove(staffSubjectDetails2);
		staffSubjectDetails2.setSubject2(null);

		return staffSubjectDetails2;
	}


	//bi-directional many-to-one association to StaffSubjectDetail
	@OneToMany(mappedBy="subject3")
	public List<StaffSubjectDetail> getStaffSubjectDetails3() {
		return this.staffSubjectDetails3;
	}

	public void setStaffSubjectDetails3(List<StaffSubjectDetail> staffSubjectDetails3) {
		this.staffSubjectDetails3 = staffSubjectDetails3;
	}

	public StaffSubjectDetail addStaffSubjectDetails3(StaffSubjectDetail staffSubjectDetails3) {
		getStaffSubjectDetails3().add(staffSubjectDetails3);
		staffSubjectDetails3.setSubject3(this);

		return staffSubjectDetails3;
	}

	public StaffSubjectDetail removeStaffSubjectDetails3(StaffSubjectDetail staffSubjectDetails3) {
		getStaffSubjectDetails3().remove(staffSubjectDetails3);
		staffSubjectDetails3.setSubject3(null);

		return staffSubjectDetails3;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Subject [subjectid=" + subjectid + ", status=" + status + ", subjectname=" + subjectname
				+ ", subjecttype=" + subjecttype + ", staffSubjectCourseHistories=" + staffSubjectCourseHistories + "]";
	}

}