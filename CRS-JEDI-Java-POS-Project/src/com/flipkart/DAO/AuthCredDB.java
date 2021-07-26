package com.flipkart.DAO;

import com.flipkart.application.CRSApplicationMain;
import com.flipkart.bean.Student;
import com.flipkart.constant.Role;
import com.flipkart.constant.SQlQueriesConstants;
import com.flipkart.operations.AuthCredOperations;
import com.flipkart.utils.DBUtil;
import org.apache.log4j.Logger;
import java.sql.*;

public class AuthCredDB implements AuthCredDBInterface{

    Connection conn = null;
    PreparedStatement pdstmt = null;
    Statement stmt = null;
    private static final Logger logger = Logger.getLogger(String.valueOf(AuthCredOperations.class));
    public AuthCredDB(){
        conn = DBUtil.getConnection();

    }
    @Override
    public boolean authenticateUserDB(int userId, String password, Enum role) {
        try {
            String sql = "";
            if("STUDENT".equals(role.toString())){
                sql = SQlQueriesConstants.STUDENT_AUTHENTICATION;

            }
            else if("PROFESSOR".equals(role.toString())){
                sql = SQlQueriesConstants.PROFESSOR_AUTHENTICATION;
            }

            pdstmt = conn.prepareStatement(sql);
            pdstmt.setInt(1, userId);
            pdstmt.setString(2, password);
            ResultSet rs = pdstmt.executeQuery();

            int id = 0;
            if (rs != null) {
                rs.last();
                id = rs.getRow();
            }
            int count  = rs.getInt(id);

            if(count == 1){

                return true;
            }

        }
        catch (SQLException ex) {
            logger.error(CRSApplicationMain.space+ex.getMessage());
        }
        return false;
    }

    @Override
    public int registerStudentDB(Student student) {
        try {
            pdstmt = conn.prepareStatement(SQlQueriesConstants.REGISTER_STUDENT);
            pdstmt.setString(1, student.getName());
            pdstmt.setString(2, student.getEmail());
            pdstmt.setString(3, student.getPassword());
            pdstmt.setString(4, student.getAddress());
            pdstmt.setString(5, "F");
            pdstmt.setString(6,"F");

            int rs = pdstmt.executeUpdate();
            return rs;

        } catch (SQLException ex) {
           logger.error(CRSApplicationMain.space+ex.getMessage());
        }
        return 0;
    }
}
