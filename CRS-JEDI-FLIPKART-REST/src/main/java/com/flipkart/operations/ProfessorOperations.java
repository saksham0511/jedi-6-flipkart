package com.flipkart.operations;

import com.flipkart.DAO.ProfessorDB;
import com.flipkart.DAO.ProfessorDBInterface;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.GradeNotAddedException;
import com.flipkart.exception.ProfessorAlreadyExistException;
import com.flipkart.pojo.StudentPojo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorOperations implements ProfessorInterface{
    @Override
    public boolean teachCourse(int profId, int courseId){
        try {

            ProfessorDBInterface profDb = new ProfessorDB();
            return profDb.teachCourseDB(profId, courseId);
        }
        catch(Exception e){
            throw e;
        }
    }

    @Override
    public List<StudentPojo> getEnrolledStudents(int courseId) {
        try {
            System.out.println("in prof op");

            ProfessorDBInterface profDb = new ProfessorDB();
            System.out.println("prof db object");
             return profDb.getEnrolledStudentsDB(courseId);

        }
        catch(Exception e){
            System.out.println("in operation exception");
            throw e;
        }
    }



    @Override

    public boolean addGrades(int studId, int courseId, String grade) throws GradeNotAddedException {
        try {
            ProfessorDBInterface profDb = new ProfessorDB();
            boolean status;
            status = profDb.addGradesDB(studId, courseId, grade);
            if (status == true){
                return true;
            }
            else{
                throw new GradeNotAddedException(String.valueOf(studId));
            }
        }
        catch(Exception e){
            throw new GradeNotAddedException(String.valueOf(studId));
        }
    }
}
