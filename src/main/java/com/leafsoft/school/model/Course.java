package com.leafsoft.school.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Courses database table.
 * 
 */
@Entity
@Table(name="Courses")
@NamedQuery(name="Course.findAll", query="SELECT c FROM Course c")
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int courseid;

	private String course;

	private String section;

	private byte status;

	//bi-directional many-to-one association to StaffSubjectCourseHistory
	@OneToMany(mappedBy="cours")
	private List<StaffSubjectCourseHistory> staffSubjectCourseHistories;

	//bi-directional many-to-one association to StudentCourseHistory
	@OneToMany(mappedBy="cours")
	private List<StudentCourseHistory> studentCourseHistories;

	public Course() {
	}

	public int getCourseid() {
		return this.courseid;
	}

	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}

	public String getCourse() {
		return this.course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getSection() {
		return this.section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public List<StaffSubjectCourseHistory> getStaffSubjectCourseHistories() {
		return this.staffSubjectCourseHistories;
	}

	public void setStaffSubjectCourseHistories(List<StaffSubjectCourseHistory> staffSubjectCourseHistories) {
		this.staffSubjectCourseHistories = staffSubjectCourseHistories;
	}

	public StaffSubjectCourseHistory addStaffSubjectCourseHistory(StaffSubjectCourseHistory staffSubjectCourseHistory) {
		getStaffSubjectCourseHistories().add(staffSubjectCourseHistory);
		staffSubjectCourseHistory.setCours(this);

		return staffSubjectCourseHistory;
	}

	public StaffSubjectCourseHistory removeStaffSubjectCourseHistory(StaffSubjectCourseHistory staffSubjectCourseHistory) {
		getStaffSubjectCourseHistories().remove(staffSubjectCourseHistory);
		staffSubjectCourseHistory.setCours(null);

		return staffSubjectCourseHistory;
	}

	public List<StudentCourseHistory> getStudentCourseHistories() {
		return this.studentCourseHistories;
	}

	public void setStudentCourseHistories(List<StudentCourseHistory> studentCourseHistories) {
		this.studentCourseHistories = studentCourseHistories;
	}

	public StudentCourseHistory addStudentCourseHistory(StudentCourseHistory studentCourseHistory) {
		getStudentCourseHistories().add(studentCourseHistory);
		studentCourseHistory.setCours(this);

		return studentCourseHistory;
	}

	public StudentCourseHistory removeStudentCourseHistory(StudentCourseHistory studentCourseHistory) {
		getStudentCourseHistories().remove(studentCourseHistory);
		studentCourseHistory.setCours(null);

		return studentCourseHistory;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Course [courseid=" + courseid + ", course=" + course + ", section=" + section + ", status=" + status
				+ ", staffSubjectCourseHistories=" + staffSubjectCourseHistories + ", studentCourseHistories="
				+ studentCourseHistories + "]";
	}

}