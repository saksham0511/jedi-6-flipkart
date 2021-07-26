package com.flipkart.operations;

import com.flipkart.DAO.CourseCatalogDB;
import com.flipkart.DAO.CourseCatalogDBInterface;
import com.flipkart.bean.Course;
import java.util.ArrayList;
import java.util.List;

public class CourseCatalogOperations implements CourseCatalogInterface{
    static List<Course> courseCatalog = new ArrayList<Course>();


    @Override
    public List<Course> viewCatalog() {
        List courseList = new ArrayList<>();
        CourseCatalogDBInterface courseCatalogDB = new CourseCatalogDB();
        courseList = courseCatalogDB.viewCatalogDB();
        return courseList;
    }
}
