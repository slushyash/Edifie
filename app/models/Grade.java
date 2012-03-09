package models;
 
import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Grade extends Model {

	public double grade;

	@ManyToOne
	public Activity assignment;

	@ManyToOne
	public Student student;

	@ManyToOne
	public Course course;

    public Grade(Activity assignment, Student student, Course course, int grade) {
        this.assignment = assignment;
        this.student = student;
        this.course = course;
        this.grade = grade;
    }

    public String toString() {
    	return "" + this.grade;
    }



 
}