package com.flipkart.DAO;

import com.flipkart.application.CRSApplicationMain;
import com.flipkart.bean.Course;
import com.flipkart.constant.SQlQueriesConstants;
import com.flipkart.operations.CourseRegistrationOperations;
import com.flipkart.utils.DBUtil;
import org.apache.log4j.Logger;

import java.sql.*;

public class CourseRegistrationDB implements CourseRegistrationDBInterface{
    Connection conn = null;
    PreparedStatement pdstmt = null;
    PreparedStatement pdstmtForCourse = null;
    PreparedStatement pdstmtAlready = null;
    private static final Logger logger = Logger.getLogger(String.valueOf(CourseRegistrationOperations.class));
    public CourseRegistrationDB(){
        conn = DBUtil.getConnection();
    }

    @Override
    public int addCourseDB(int courseId,int studentId) {
        try {
            pdstmtForCourse = conn.prepareStatement(SQlQueriesConstants.GET_COURSE_COUNT);
            pdstmtForCourse.setInt(1,courseId);
            ResultSet resultSet = pdstmtForCourse.executeQuery();
            int id = 0;
            if (resultSet == null){
                System.out.println("Result Set NULL");
                return 0;
            }
            int check = -9;
            if(resultSet.next()) {
                check = resultSet.getInt(1);
            }
            if(check !=0 ) {
                pdstmt = conn.prepareStatement(SQlQueriesConstants.ADD_COURSE_FOR_STUDENT);
                pdstmt.setInt(1, courseId);
                pdstmt.setInt(2, studentId);
                pdstmt.setString(3, "X");

                int rs = pdstmt.executeUpdate();
                if (rs == 0) {

                    System.out.println("Course Not Added");
                }
                return rs;
            }

        } catch (SQLException ex) {
            logger.error(CRSApplicationMain.space+ex.getMessage());
        }
        return 0;
    }

    @Override
    public boolean removeCourseDB(int courseId,int studId) {
        try {
            pdstmt = conn.prepareStatement(SQlQueriesConstants.REMOVE_COURSE_FOR_STUDENT);

            pdstmt.setInt(1,courseId);
            pdstmt.setInt(2,studId);
            int rs = pdstmt.executeUpdate();
            if(rs == 1){
                return true;
            }
        }
        catch (SQLException ex) {
            logger.error(CRSApplicationMain.space+ex.getMessage());
        }
        return false;
    }

    @Override
    public Course[] registeredCourseDB(int userId) {
        return new Course[0];
    }

    @Override
    public int makePaymentDB(int courseId) {
        return 0;
    }

    @Override
    public int numOfRegisteredCourses(int studId) {
        try {
            pdstmt = conn.prepareStatement(SQlQueriesConstants.COUNT_OF_REGISTERED_COURSES_OF_STUDENT);
            pdstmt.setInt(1, studId);
            ResultSet rs = pdstmt.executeQuery();
            int id = 0;
            if (rs != null) {
                rs.last();
                id = rs.getRow();
            }

            int numOfCourse =  rs.getInt(id);
            return numOfCourse;
        } catch (SQLException ex) {
            logger.error(CRSApplicationMain.space+ex.getMessage());
        }
        return 0;
    }
}
