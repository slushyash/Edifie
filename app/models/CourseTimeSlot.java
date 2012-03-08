package models;
 
import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class CourseTimeSlot extends Model {

	@OneToOne
	public Course course;

	@OneToMany
	public List<Student> students;

	@OneToMany
	public List<CourseDay> courseDays;

    public CourseTimeSlot(Course course) {
        this.course = course;
        this.students = new ArrayList<Student>();
        this.courseDays = new ArrayList<CourseDay>();
    }



 
}