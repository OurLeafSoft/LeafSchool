package com.leafsoft.school.model;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.leafsoft.school.util.JsonDateSerializer;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the StaffDetails database table.
 * 
 */
@Entity
@Table(name="StaffDetails")
@NamedQuery(name="StaffDetail.findAll", query="SELECT s FROM StaffDetail s")
public class StaffDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	private int staffid;
	private BigInteger accountno;
	private String address;
	private String city;
	private BigInteger contactnumber;
	private Date dob;
	private String email;
	private byte gender;
	private Date joiningdate;
	private String nationality;
	private String primarylanguage;
	private String prvExperience;
	private String secondarylanguage;
	private String staffname;
	private String state;
	private byte status;
	private String zipcode;
	private Designation designation;
	private List<StaffQualification> staffQualifications;
	private List<StaffSubjectCourseHistory> staffSubjectCourseHistories;
	private List<StaffSubjectDetail> staffSubjectDetails;

	public StaffDetail() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getStaffid() {
		return this.staffid;
	}

	public void setStaffid(int staffid) {
		this.staffid = staffid;
	}


	public BigInteger getAccountno() {
		return this.accountno;
	}

	public void setAccountno(BigInteger accountno) {
		this.accountno = accountno;
	}


	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}


	public BigInteger getContactnumber() {
		return this.contactnumber;
	}

	public void setContactnumber(BigInteger contactnumber) {
		this.contactnumber = contactnumber;
	}


	@Temporal(TemporalType.DATE)
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}


	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public byte getGender() {
		return this.gender;
	}

	public void setGender(byte gender) {
		this.gender = gender;
	}


	@Temporal(TemporalType.DATE)
	public Date getJoiningdate() {
		return this.joiningdate;
	}

	public void setJoiningdate(Date joiningdate) {
		this.joiningdate = joiningdate;
	}


	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}


	public String getPrimarylanguage() {
		return this.primarylanguage;
	}

	public void setPrimarylanguage(String primarylanguage) {
		this.primarylanguage = primarylanguage;
	}


	@Column(name="prv_experience")
	public String getPrvExperience() {
		return this.prvExperience;
	}

	public void setPrvExperience(String prvExperience) {
		this.prvExperience = prvExperience;
	}


	public String getSecondarylanguage() {
		return this.secondarylanguage;
	}

	public void setSecondarylanguage(String secondarylanguage) {
		this.secondarylanguage = secondarylanguage;
	}


	public String getStaffname() {
		return this.staffname;
	}

	public void setStaffname(String staffname) {
		this.staffname = staffname;
	}


	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}


	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}


	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}


	//bi-directional many-to-one association to Designation
	@ManyToOne
	@JoinColumn(name="designationid")
	public Designation getDesignation() {
		return this.designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}


	//bi-directional many-to-one association to StaffQualification
	@OneToMany(mappedBy="staffDetail")
	public List<StaffQualification> getStaffQualifications() {
		return this.staffQualifications;
	}

	public void setStaffQualifications(List<StaffQualification> staffQualifications) {
		this.staffQualifications = staffQualifications;
	}

	public StaffQualification addStaffQualification(StaffQualification staffQualification) {
		getStaffQualifications().add(staffQualification);
		staffQualification.setStaffDetail(this);

		return staffQualification;
	}

	public StaffQualification removeStaffQualification(StaffQualification staffQualification) {
		getStaffQualifications().remove(staffQualification);
		staffQualification.setStaffDetail(null);

		return staffQualification;
	}


	//bi-directional many-to-one association to StaffSubjectCourseHistory
	@OneToMany(mappedBy="staffDetail")
	public List<StaffSubjectCourseHistory> getStaffSubjectCourseHistories() {
		return this.staffSubjectCourseHistories;
	}

	public void setStaffSubjectCourseHistories(List<StaffSubjectCourseHistory> staffSubjectCourseHistories) {
		this.staffSubjectCourseHistories = staffSubjectCourseHistories;
	}

	public StaffSubjectCourseHistory addStaffSubjectCourseHistory(StaffSubjectCourseHistory staffSubjectCourseHistory) {
		getStaffSubjectCourseHistories().add(staffSubjectCourseHistory);
		staffSubjectCourseHistory.setStaffDetail(this);

		return staffSubjectCourseHistory;
	}

	public StaffSubjectCourseHistory removeStaffSubjectCourseHistory(StaffSubjectCourseHistory staffSubjectCourseHistory) {
		getStaffSubjectCourseHistories().remove(staffSubjectCourseHistory);
		staffSubjectCourseHistory.setStaffDetail(null);

		return staffSubjectCourseHistory;
	}


	//bi-directional many-to-one association to StaffSubjectDetail
	@OneToMany(mappedBy="staffDetail")
	public List<StaffSubjectDetail> getStaffSubjectDetails() {
		return this.staffSubjectDetails;
	}

	public void setStaffSubjectDetails(List<StaffSubjectDetail> staffSubjectDetails) {
		this.staffSubjectDetails = staffSubjectDetails;
	}

	public StaffSubjectDetail addStaffSubjectDetail(StaffSubjectDetail staffSubjectDetail) {
		getStaffSubjectDetails().add(staffSubjectDetail);
		staffSubjectDetail.setStaffDetail(this);

		return staffSubjectDetail;
	}

	public StaffSubjectDetail removeStaffSubjectDetail(StaffSubjectDetail staffSubjectDetail) {
		getStaffSubjectDetails().remove(staffSubjectDetail);
		staffSubjectDetail.setStaffDetail(null);

		return staffSubjectDetail;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StaffDetail [staffid=" + staffid + ", accountno=" + accountno + ", address=" + address + ", city="
				+ city + ", contactnumber=" + contactnumber + ", dob=" + dob + ", email=" + email + ", gender=" + gender
				+ ", joiningdate=" + joiningdate + ", nationality=" + nationality + ", primarylanguage="
				+ primarylanguage + ", prvExperience=" + prvExperience + ", secondarylanguage=" + secondarylanguage
				+ ", staffname=" + staffname + ", state=" + state + ", status=" + status + ", zipcode=" + zipcode
				+ ", designation=" + designation + ", staffQualifications=" + staffQualifications
				+ ", staffSubjectCourseHistories=" + staffSubjectCourseHistories + ", staffSubjectDetails="
				+ staffSubjectDetails + "]";
	}

}