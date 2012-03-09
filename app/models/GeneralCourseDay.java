package models;
 
import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class GeneralCourseDay extends Model {

	@ManyToOne
	public Course course;

	public int day;

	@OneToMany
	public List<Activity> due;

	public String description;

    public GeneralCourseDay(Course course, int day, String description) {
        this.course = course;
        this.day = day;
        this.description = description;
    }
}