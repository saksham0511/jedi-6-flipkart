package com.flipkart.DAO;


import com.flipkart.bean.Course;
import com.flipkart.constant.SQlQueriesConstants;
import com.flipkart.operations.GradeCardOperations;
import com.flipkart.utils.DBUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

public class GradeCardDB implements GradeCardDBInterface{
    Connection conn = null;
    PreparedStatement pdstmt = null;
    private static final Logger logger = Logger.getLogger(String.valueOf(GradeCardOperations.class));
    public GradeCardDB(){
        conn = DBUtil.getConnection();
    }

    @Override
    public HashMap<Course, String> viewGradesDB(int studId) {
        try {
            pdstmt = conn.prepareStatement(SQlQueriesConstants.GET_GRADE_CARD);
            pdstmt.setInt(1,studId);
            ResultSet rs = pdstmt.executeQuery();
            HashMap<Course, String> courseGrade = new HashMap<Course, String>();
            while (rs.next()){
                int courseId = rs.getInt(1);
                String courseName = rs.getString(2);
                String grade = rs.getString(3);
                Course course = new Course(courseId,courseName);
                courseGrade.put(course,grade);
            }
        return courseGrade;
        }
        catch (SQLException ex) {

            logger.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateReportCardDB(int studId, int courseId, String grade) {
        return false;
    }
}
