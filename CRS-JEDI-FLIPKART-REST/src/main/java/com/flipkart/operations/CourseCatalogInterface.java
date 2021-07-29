package com.flipkart.operations;
import com.flipkart.bean.Course;

import java.util.List;

/**
 * @author JEDI-06
 * Interface for Course Catalog Operations
 *
 */
public interface CourseCatalogInterface {



    /**
     * This method is used to view all courses in the course catalog
     * @return List of courses
     */
    public List<Course> viewCatalog();




}
