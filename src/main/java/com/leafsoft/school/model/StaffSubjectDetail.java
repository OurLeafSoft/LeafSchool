package com.leafsoft.school.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the StaffSubjectDetails database table.
 * 
 */
@Entity
@Table(name="StaffSubjectDetails")
@NamedQuery(name="StaffSubjectDetail.findAll", query="SELECT s FROM StaffSubjectDetail s")
public class StaffSubjectDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	//bi-directional many-to-one association to StaffDetail
	@ManyToOne
	@JoinColumn(name="staffid")
	private StaffDetail staffDetail;

	//bi-directional many-to-one association to Subject
	@ManyToOne
	@JoinColumn(name="primarysubjectid")
	private Subject subject1;

	//bi-directional many-to-one association to Subject
	@ManyToOne
	@JoinColumn(name="secondarysubjectid")
	private Subject subject2;

	//bi-directional many-to-one association to Subject
	@ManyToOne
	@JoinColumn(name="othersubjectid")
	private Subject subject3;

	public StaffSubjectDetail() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StaffDetail getStaffDetail() {
		return this.staffDetail;
	}

	public void setStaffDetail(StaffDetail staffDetail) {
		this.staffDetail = staffDetail;
	}

	public Subject getSubject1() {
		return this.subject1;
	}

	public void setSubject1(Subject subject1) {
		this.subject1 = subject1;
	}

	public Subject getSubject2() {
		return this.subject2;
	}

	public void setSubject2(Subject subject2) {
		this.subject2 = subject2;
	}

	public Subject getSubject3() {
		return this.subject3;
	}

	public void setSubject3(Subject subject3) {
		this.subject3 = subject3;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StaffSubjectDetail [id=" + id + ", staffDetail=" + staffDetail + ", subject1=" + subject1
				+ ", subject2=" + subject2 + ", subject3=" + subject3 + "]";
	}

}