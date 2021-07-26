package com.flipkart.DAO;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.constant.SQlQueriesConstants;
import com.flipkart.exception.*;
import com.flipkart.operations.AdminOperations;
import com.flipkart.utils.DBUtil;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdminDB implements AdminDBInterface {
    private static final Logger logger = Logger.getLogger(String.valueOf(AdminOperations.class));
    Connection conn = null;
    PreparedStatement pdstmt = null;
    Statement stmt = null;
    public AdminDB(){
        conn = DBUtil.getConnection();

    }
    @Override
    public boolean approveStudentDB() throws ApprovalFailedException {
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQlQueriesConstants.UNAPPROVED_STUDENT_COUNT);
            int id = 0;
            if (rs != null) {
                rs.last();
                id = rs.getRow();
            }

            int count =  rs.getInt(id);
            if(count > 0) {
                int rs1 = stmt.executeUpdate(SQlQueriesConstants.APPROVE_STUDENT_QUERY);
                stmt.close();
                return true;
            }
            else{
                throw new ApprovalFailedException("No Student is for approval");

            }


        } catch (SQLException ex) {
            ex.getMessage();
        }catch(ApprovalFailedException ex){
            throw ex;
        }
        return false;
    }

    @Override
    public boolean removeProfessorDB(int profId) throws ProfessorRemovalFailedException {
        try {
            pdstmt = conn.prepareStatement(SQlQueriesConstants.REMOVE_PROFESSOR);

            pdstmt.setInt(1,profId);
            int rs = pdstmt.executeUpdate();
            if(rs == 1){
                return true;
            }
            if(rs == 0){
                throw  new ProfessorRemovalFailedException("Professor not removed, check if it exist");
            }

        }
        catch (SQLException ex) {
            ex.getMessage();
        }
        catch(ProfessorRemovalFailedException ex){
            throw ex;
        }
        return false;
    }

    @Override
    public int addProfessorDB(String name, String email, String address,String Password) throws ProfessorAlreadyExistException {
        try {
            pdstmt = conn.prepareStatement(SQlQueriesConstants.ADD_PROFESSOR);
            pdstmt.setString(1, name);
            pdstmt.setString(2, email);
            pdstmt.setString(3, address);
            pdstmt.setString(4, Password);

            int rs = pdstmt.executeUpdate();
            if(rs == 0){
                throw new ProfessorAlreadyExistException("Professor not added, check if professor already exists");
            }
            return rs;

        } catch (SQLException ex) {
            ex.getMessage();
        }catch(ProfessorAlreadyExistException ex){
            throw ex;
        }
        return 0;
    }

    @Override
    public int addNewCourseDB( String courseName,int fees) throws CourseAlreadyExistException {
        try {
            pdstmt = conn.prepareStatement(SQlQueriesConstants.ADD_NEW_COURSE);
            pdstmt.setString(1, courseName);
            pdstmt.setInt(2,-1);
            pdstmt.setInt(3,fees);

            int rs = pdstmt.executeUpdate();
            if(rs == 0){
                throw  new CourseAlreadyExistException("Course not added, check if it already exists");
            }
            return rs;

        } catch (SQLException ex) {
            logger.error("Exception: "+ex.getMessage());

        }catch (CourseAlreadyExistException ex){
            throw ex;
        }
        return 0;

    }

    @Override
    public boolean removeCourseDB(int courseId) throws CourseRemovalFailedException {
        try {
            pdstmt = conn.prepareStatement(SQlQueriesConstants.DELETE_COURSE);

            pdstmt.setInt(1,courseId);
            int rs = pdstmt.executeUpdate();
            if(rs == 1){
                return true;
            }
            if(rs == 0){
                throw  new CourseRemovalFailedException("Course not removed, check if it exist");
            }

        }
        catch (SQLException ex) {
            logger.error("Exception: "+ex.getMessage());
        }catch(CourseRemovalFailedException ex){
            throw  ex;
        }
        return false;
    }

    @Override
    public List<Student> unApprovedStudent() {
        try {
            pdstmt = conn.prepareStatement(SQlQueriesConstants.GET_UNAPPROVED_STUDENTS);

            pdstmt.setString(1,"F");
            ResultSet rs = pdstmt.executeQuery();
            List<Student> studentList = new ArrayList<>();
            while (rs.next()){
                int studentId = rs.getInt(1);
                String studentName = rs.getString(2);
                String studentEmail = rs.getString(3);
                String studentAddress = rs.getString(4);
                Student student = new Student();
                student.setUserId(studentId);
                student.setName(studentName);
                student.setEmail(studentEmail);
                student.setAddress(studentAddress);
                studentList.add(student);
            }
            return studentList;
        }
        catch (SQLException ex) {
            logger.error("Exception: "+ex.getMessage());
        }
        return null;
    }


    @Override
    public boolean isApprovedDB(int studentId) {

        try {
            pdstmt = conn.prepareStatement(SQlQueriesConstants.IS_STUDENT_APPROVED);

            pdstmt.setInt(1,studentId);
            ResultSet rs = pdstmt.executeQuery();
            int id = 0;
            if (rs != null) {
                rs.last();
                id = rs.getRow();
            }

            String isApproved =  rs.getString(id);
            if(isApproved.equals("T")){
                return true;
            }
        }
        catch (SQLException ex) {
            logger.error("Exception: "+ex.getMessage());
        }
        return false;
    }

    @Override
    public HashMap<Course,Professor> viewProfessorDB() {
        try {
            HashMap<Course,Professor> courseProfessor = new HashMap<>();
            pdstmt = conn.prepareStatement(SQlQueriesConstants.PROFESSOR_LIST);
            ResultSet rs = pdstmt.executeQuery();
            while (rs.next()){
                int profid = rs.getInt(1);
                String profName = rs.getString(2);
                int courseId = rs.getInt(3);
                String courseName = rs.getString(4);
                Course course = new Course(courseId,courseName);
                Professor professor = new Professor();
                professor.setUserId(profid);
                professor.setName(profName);
                courseProfessor.put(course,professor);
                return courseProfessor;
            }

        } catch (SQLException ex){
            logger.error("Exception: "+ex.getMessage());
        }

        return null;
    }

}

