package com.leafsoft.school.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the StaffQualification database table.
 * 
 */
@Entity
@NamedQuery(name="StaffQualification.findAll", query="SELECT s FROM StaffQualification s")
public class StaffQualification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int id;

	private double grade;

	private String institution;

	private double percentage;

	private Timestamp qdate;

	private String type;

	//bi-directional many-to-one association to StaffDetail
	@ManyToOne
	@JoinColumn(name="staffid")
	private StaffDetail staffDetail;

	public StaffQualification() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getGrade() {
		return this.grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public String getInstitution() {
		return this.institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public double getPercentage() {
		return this.percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public Timestamp getQdate() {
		return this.qdate;
	}

	public void setQdate(Timestamp qdate) {
		this.qdate = qdate;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public StaffDetail getStaffDetail() {
		return this.staffDetail;
	}

	public void setStaffDetail(StaffDetail staffDetail) {
		this.staffDetail = staffDetail;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StaffQualification [id=" + id + ", grade=" + grade + ", institution=" + institution + ", percentage="
				+ percentage + ", qdate=" + qdate + ", type=" + type + ", staffDetail=" + staffDetail + "]";
	}

}