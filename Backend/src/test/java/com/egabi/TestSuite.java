package com.egabi;


import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("University Database Test Suite")
@SelectClasses({
  UserTest.class,
  StudentTest.class,
  CourseTest.class,
  FacultyTest.class,
  AuthServiceTest.class,
  StudentServiceTest.class,
  CourseServiceTest.class,
  FacultyServiceTest.class,
  AuthControllerTest.class,
  StudentControllerTest.class,
  CourseControllerTest.class,
  FacultyControllerTest.class
})
public class TestSuite {
}
