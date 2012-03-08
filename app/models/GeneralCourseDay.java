package models;
 
import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class GeneralCourseDay extends Model {

	@ManyToOne
	public Course course;

	public int day;

	@ManyToMany
	public List<Activity> due;

    public GeneralCourseDay(Course course, int day) {
        this.course = course;
        this.day = day;
    }
}