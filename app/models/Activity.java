package models;
 
import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Activity extends Model {

    public Blob file;
    public String name; // Test 2: Animal Types
    public Course course;

    public double weight;

    @ManyToOne
    public GeneralCourseDay due;

    public Activity(String name, Course course, double weight) {
        this.name = name;
        this.course = course;
        this.weight = weight;
    }

    




 
}