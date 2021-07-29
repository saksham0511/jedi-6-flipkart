package com.flipkart.constant;

/**
 *
 * @author JEDI-06
 *
 */

public class SQlQueriesConstants {

    //for Admin
    public static final String UNAPPROVED_STUDENT_COUNT = "Select count(*) from Student where isApproved='F'";
    public static final String APPROVE_STUDENT_QUERY = "Update Student set isApproved='T'";
    public static final String REMOVE_PROFESSOR = "DELETE FROM Professor WHERE ProfessorId=?";
    public static final String ADD_PROFESSOR = "insert into Professor values (default,?,?,?,?)";
    public static final String ADD_NEW_COURSE = "insert into Course values (default,?,?,?)";
    public static final String DELETE_COURSE = "DELETE FROM Course WHERE CourseId=?";
    public static final String GET_UNAPPROVED_STUDENTS = "select StudentId,StudentName,StudentEmail,StudentAddress from Student where isApproved=?";
    public static final String IS_STUDENT_APPROVED = "select isApproved from Student where StudentId=?";

    //for Authentication
    public static final String STUDENT_AUTHENTICATION = "select count(*) from Student where StudentId=? and Password=?;";
    public static final String PROFESSOR_AUTHENTICATION = "select count(*) from Professor where ProfessorId=? and Password=?;";
    public static final String REGISTER_STUDENT = "insert into Student values (default,?,?,?,?,?,?)";

    //for Course Catalog
    public static final String VIEW_COURSE_CATALOG = "select * from Course";

    //for Course Registration
    public static final String GET_COURSE_COUNT = "select count(CourseId) from Course where CourseId=?";
    public static final String ADD_COURSE_FOR_STUDENT = "insert into GradeCard values (?,?,?)";
    public static final String REMOVE_COURSE_FOR_STUDENT = "DELETE FROM GradeCard WHERE CourseId=? AND StudentId=?";
    public static final String COUNT_OF_REGISTERED_COURSES_OF_STUDENT= "select count(*) from GradeCard where StudentId=?";

    // for Grade Card
    public static final String GET_GRADE_CARD = "select GradeCard.CourseId,Course.CourseName,GradeCard.Grade from GradeCard natural join Course where StudentId=?";

    //for Notification
    public static final String SEND_NOTIFICATION = "insert into Notification values (default,?,?)";
    public static final String UPDATE_PAYMENT_STATUS = "update Student set isFeePaid= ? where StudentId = ?";
    public static final String MAKE_PAYMENT = "insert into Payment values(?,?,?,?,?,?,?);";

    //for Payment
    public static final String CALCULATE_TOTAL_AMOUNT = "select sum(CourseFee) from Course where CourseId in (select CourseId from GradeCard where StudentId = ?)";

    //for Professor
    public static final String GET_PROFESSOR_DETAILS = "select ProfessorId from Course where CourseId = ?";
    public static final String UPDATE_PROFESSOR_COURSES = "update Course set ProfessorId = ? where CourseId = ?";
    public static final String GET_ENROLLED_STUDENTS = "select Student.StudentId, Student.StudentName from Student natural join GradeCard where CourseId = ?";
    public static final String UPDATE_GRADE_CARD = "update GradeCard set Grade = ? where CourseId = ? and StudentId = ?";
    public static final String PROFESSOR_LIST="select Professor.ProfessorId , Professor.ProfessorName,Course.CourseId,Course.CourseName from Professor natural join course\n";

    //for Student
    public static final String GET_STUDENT_COURSES = "select Course.CourseId,Course.CourseName from Course natural join GradeCard where GradeCard.StudentId = ?;";
    public static final String CHECK_PAYMENT_STATUS_FOR_STUDENT = "select isFeePaid from Student where StudentId=?";

}
