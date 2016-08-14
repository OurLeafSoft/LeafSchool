CREATE TABLE `StudentContactDetails` (
  `familyid` int(11) NOT NULL AUTO_INCREMENT,
  `guardianname` varchar(100) NOT NULL,
  `guardiantype` tinyint(4) NOT NULL DEFAULT '-1',
  `fathername` varchar(100) NOT NULL,
  `mothername` varchar(100) NOT NULL,
  `caste` varchar(100) NOT NULL,
  `countrycode` varchar(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `state` varchar(75) DEFAULT NULL,
  `city` varchar(75) DEFAULT NULL,
  `zipcode` varchar(50) DEFAULT NULL,
  `contactnumber` bigint(20) DEFAULT '-1',
  `status` BOOLEAN DEFAULT 0,
  PRIMARY KEY (`familyid`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=latin1;

CREATE TABLE `StudentDetails` (
  `studentid` int(11) NOT NULL AUTO_INCREMENT,
  `studentname` varchar(100) NOT NULL,
  `dob` date DEFAULT NULL,
  `gender` tinyint(4) NOT NULL DEFAULT '-1',
  `status` BOOLEAN DEFAULT 0,
  `regdate` bigint(20) NOT NULL,
  `familyid` int(11) NOT NULL,
  PRIMARY KEY (`studentid`),
  KEY `StudentDetails_fid` (`familyid`),
  CONSTRAINT `StudentDetails_fk_familyid` FOREIGN KEY (`familyid`) REFERENCES `StudentContactDetails` (`familyid`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=latin1;

CREATE TABLE `DesignationGroup` (
  `designationgroupid` int(11) NOT NULL AUTO_INCREMENT,
  `designationgroupname` varchar(100) NOT NULL,
  `status` BOOLEAN DEFAULT 0,
  PRIMARY KEY (`designationgroupid`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=latin1;

CREATE TABLE `Designations` (
  `designationid` int(11) NOT NULL AUTO_INCREMENT,
  `designationname` varchar(100) NOT NULL,
  `designationgroupid` int(11) NOT NULL,
  `status` BOOLEAN DEFAULT 0,
  PRIMARY KEY (`designationid`),
  KEY `fk_did` (`designationgroupid`),
  CONSTRAINT `Designations_fk_designationgroupid` FOREIGN KEY (`designationgroupid`) REFERENCES `DesignationGroup` (`designationgroupid`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=latin1;

CREATE TABLE `Subjects` (
  `subjectid` int(11) NOT NULL AUTO_INCREMENT,
  `subjectname` varchar(100) NOT NULL,
  `subjecttype` tinyint(4) NOT NULL DEFAULT '-1',
  `status` BOOLEAN DEFAULT 0,
  PRIMARY KEY (`subjectid`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=latin1;

CREATE TABLE `StaffDetails` (
  `staffid` int(11) NOT NULL AUTO_INCREMENT,
  `staffname` varchar(100) NOT NULL,
  `contactnumber` bigint(20) DEFAULT '-1',
  `email` varchar(100) NOT NULL,
  `joiningdate` date DEFAULT NULL,
  `prv_experience` varchar(200) DEFAULT NULL,
  `gender` tinyint(4) NOT NULL DEFAULT '-1',
  `dob` date DEFAULT NULL,
  `designationid` int(11) NOT NULL,
  `status` BOOLEAN DEFAULT 0,
  `address` varchar(255) DEFAULT NULL,
  `state` varchar(75) DEFAULT NULL,
  `city` varchar(75) DEFAULT NULL,
  `zipcode` varchar(50) DEFAULT NULL,
  `accountno` bigint(19) NOT NULL,
  `nationality` varchar(50) DEFAULT NULL,
  `primarylanguage` varchar(30) NOT NULL,
  `secondarylanguage` varchar(30) NOT NULL,
  PRIMARY KEY (`staffid`),
  CONSTRAINT `StaffDetails_fk_designationid` FOREIGN KEY (`designationid`) REFERENCES `Designations` (`designationid`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=latin1;

CREATE TABLE `StaffQualification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `qdate` TIMESTAMP DEFAULT 0,
  `institution` varchar(255) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `grade` double NOT NULL DEFAULT '0',
  `percentage` double NOT NULL DEFAULT '0',
  `staffid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `StaffQualification_fk_staffid` FOREIGN KEY (`staffid`) REFERENCES `StaffDetails` (`staffid`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=latin1;

CREATE TABLE `StaffSubjectDetails` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `staffid` int(11) NOT NULL,
  `primarysubjectid` int(11) DEFAULT NULL,
  `secondarysubjectid` int(11) DEFAULT NULL,
  `othersubjectid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `StaffSubjectDetails_fk_staffid` FOREIGN KEY (`staffid`) REFERENCES `StaffDetails` (`staffid`),
  CONSTRAINT `StaffSubjectDetails_fk_primarysubjectid` FOREIGN KEY (`primarysubjectid`) REFERENCES `Subjects` (`subjectid`),
  CONSTRAINT `StaffSubjectDetails_fk_secondarysubjectid` FOREIGN KEY (`secondarysubjectid`) REFERENCES `Subjects` (`subjectid`),
  CONSTRAINT `StaffSubjectDetails_fk_othersubjectid` FOREIGN KEY (`othersubjectid`) REFERENCES `Subjects` (`subjectid`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=latin1;

CREATE TABLE `Courses` (
  `courseid` int(11) NOT NULL AUTO_INCREMENT,
  `course` varchar(50) NOT NULL,
  `section` varchar(20) NOT NULL,
  `status` BOOLEAN DEFAULT 0,
  PRIMARY KEY (`courseid`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=latin1;

CREATE TABLE `StaffSubjectCourseHistory` (
  `staffsubjectcourseid` int(11) NOT NULL AUTO_INCREMENT,
  `staffid` int(11) NOT NULL,
  `subjectid` int(11) NOT NULL,
  `acadamicyear` year(4) DEFAULT NULL,
  `courseid` int(11) NOT NULL,
  PRIMARY KEY (`staffsubjectcourseid`),
  KEY `StaffSubjectCourseHistory_sid` (`staffid`),
  KEY `StaffSubjectCourseHistory_desid` (`subjectid`),
  KEY `StaffSubjectCourseHistory_courseid` (`courseid`),
  CONSTRAINT `StaffSubjectCourseHistory_fk_staffid` FOREIGN KEY (`staffid`) REFERENCES `StaffDetails` (`staffid`),
  CONSTRAINT `StaffSubjectCourseHistory_fk_subjectid` FOREIGN KEY (`subjectid`) REFERENCES `Subjects` (`subjectid`),
  CONSTRAINT `StaffSubjectCourseHistory_fk_courseid` FOREIGN KEY (`courseid`) REFERENCES `Courses` (`courseid`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=latin1;

CREATE TABLE `StudentCourseHistory` (
  `studentcoursehistoryid` int(11) NOT NULL AUTO_INCREMENT,
  `studentid` int(11) NOT NULL,
  `courseid` int(11) NOT NULL,
  `acadamicyear` year(4) DEFAULT NULL,
  PRIMARY KEY (`studentcoursehistoryid`),
  KEY `StudentCourseHistory_courseid` (`courseid`),
  KEY `StudentCourseHistory_sid` (`studentid`),
  CONSTRAINT `StudentCourseHistory_fk_courseid` FOREIGN KEY (`courseid`) REFERENCES `Courses` (`courseid`),
  CONSTRAINT `StudentCourseHistory_fk_studentid` FOREIGN KEY (`studentid`) REFERENCES `StudentDetails` (`studentid`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=latin1;

CREATE TABLE `ExamType` (
  `examtypeid` int(11) NOT NULL AUTO_INCREMENT,
  `examtypename` varchar(100) NOT NULL,
  `status` BOOLEAN DEFAULT 0,
  PRIMARY KEY (`examtypeid`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=latin1;

CREATE TABLE `ExamDetails` (
  `examid` int(11) NOT NULL AUTO_INCREMENT,
  `examname` varchar(100) NOT NULL,
  `examtypeid` int(11) NOT NULL,
  `status` BOOLEAN DEFAULT 0,
  PRIMARY KEY (`examid`),
  KEY `ExamDetails_examtypeid` (`examtypeid`),
  CONSTRAINT `ExamDetails_fk_examtypeid` FOREIGN KEY (`examtypeid`) REFERENCES `ExamType` (`examtypeid`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=latin1;

 CREATE TABLE `ExamSubjectMapping` (
  `examsubjectid` int(11) NOT NULL AUTO_INCREMENT,
  `staffsubjectcourseid` int(11) NOT NULL,
  `examid` int(11) NOT NULL,
  `acadamicyear` year(4) DEFAULT NULL,
  `status` tinyint(1) DEFAULT '0',
  `total_mark` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`examsubjectid`),
  KEY `ExamSubjectMapping_courseid` (`staffsubjectcourseid`),
  KEY `ExamSubjectMapping_examid` (`examid`),
  CONSTRAINT `ExamSubjectMapping_fk_examid` FOREIGN KEY (`examid`) REFERENCES `ExamDetails` (`examid`),
  CONSTRAINT `ExamSubjectMapping_fk_staffsubjectcourseid` FOREIGN KEY (`staffsubjectcourseid`) REFERENCES `StaffSubjectCourseHistory` (`staffsubjectcourseid`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=latin1;

CREATE TABLE `StudentAcadamicHistory` (
  `studenthistoryid` int(11) NOT NULL AUTO_INCREMENT,
  `historytype` tinyint(4) NOT NULL DEFAULT '-1',
  `examsubjectid` int(11) NOT NULL,
  `studentid` int(11) NOT NULL,
  `studentcoursehistoryid` int(11) NOT NULL,
  `mark` double NOT NULL DEFAULT '0',
  `grade` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`studenthistoryid`),
  KEY `ExamSubjectMapping_examsubjectid` (`examsubjectid`),
  KEY `StudentAcadamicHistory_sid` (`studentid`),
  KEY `ExamSubjectMapping_fk_studentcoursehistoryid` (`studentcoursehistoryid`),
  CONSTRAINT `ExamSubjectMapping_fk_examsubjectid` FOREIGN KEY (`examsubjectid`) REFERENCES `ExamSubjectMapping` (`examsubjectid`),
  CONSTRAINT `ExamSubjectMapping_fk_studentcoursehistoryid` FOREIGN KEY (`studentcoursehistoryid`) REFERENCES `StudentCourseHistory` (`studentcoursehistoryid`),
  CONSTRAINT `StudentAcadamicHistory_fk_studentid` FOREIGN KEY (`studentid`) REFERENCES `StudentDetails` (`studentid`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=latin1;

CREATE TABLE `LoginDetails` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `role` varchar(20) NOT NULL,
  `status` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),UNIQUE (username)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;