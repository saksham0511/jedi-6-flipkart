package com.flipkart.operations;

import com.flipkart.DAO.GradeCardDB;
import com.flipkart.DAO.GradeCardDBInterface;
import com.flipkart.bean.Course;

import java.util.HashMap;

public class GradeCardOperations implements GradeCardInterface{
    @Override
    public HashMap<Course, String> viewGrades(int studId) {
        GradeCardDBInterface gradeCardDBInterface = new GradeCardDB();
        HashMap<Course, String> courseGrade = gradeCardDBInterface.viewGradesDB(studId);
        return courseGrade;
    }


}
