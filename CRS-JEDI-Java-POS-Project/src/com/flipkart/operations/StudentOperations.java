package com.flipkart.operations;

import com.flipkart.DAO.*;
import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

import java.util.List;

public class StudentOperations implements StudentInterface{

    StudentDBInterface studentDBInterface;

    @Override
    public List<Course> getRegisteredCourses(int studId) {
        studentDBInterface = new StudentDB();
        return studentDBInterface.getRegisteredCoursesDB(studId);
    }

    @Override
    public boolean isFeefeespaidDB(int studentId) {
        StudentDBInterface studentDBInterface = new StudentDB();
        return studentDBInterface.isFeefeespaidDB(studentId);
    }
}
