package com.flipkart.DAO;

import com.flipkart.application.CRSApplicationMain;
import com.flipkart.bean.Course;
import com.flipkart.constant.SQlQueriesConstants;
import com.flipkart.operations.CourseCatalogOperations;
import com.flipkart.utils.DBUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseCatalogDB implements CourseCatalogDBInterface{
    Connection conn = null;
    PreparedStatement pdstmt = null;
    Statement stmt = null;
    private static final Logger logger = Logger.getLogger(String.valueOf(CourseCatalogOperations.class));
    public CourseCatalogDB(){
        conn = DBUtil.getConnection();
    }
    @Override
    public int addCoursesDB(int courseId, String courseName) {
        return 0;
    }

    @Override
    public boolean removeCourseDB(int courseId) {
        return false;
    }

    @Override
    public List<Course> viewCatalogDB() {
        List<Course> courseList = new ArrayList<>();
        try {
            pdstmt = conn.prepareStatement(SQlQueriesConstants.VIEW_COURSE_CATALOG);
            ResultSet rs = pdstmt.executeQuery();
            while(rs.next()){
                Course course = new Course();
                course.setCourseId(rs.getInt(1));
                course.setCourseName(rs.getString(2));
                course.setProfessorId(rs.getInt(3));
                courseList.add(course);
            }
            return courseList;
        }
        catch(SQLException ex){
            logger.error(CRSApplicationMain.space+ex.getMessage());

        }
        return courseList;
    }
}
