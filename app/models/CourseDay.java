package models;
 
import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class CourseDay extends Model {

	public Date date;

	@ManyToOne
	public GeneralCourseDay generalCourseDay;
	
	@ManyToOne
	public CourseTimeSlot timeslot;

    public CourseDay(Date date, CourseTimeSlot timeslot, GeneralCourseDay g) {
        this.date = date;
        this.timeslot = timeslot;
        this.generalCourseDay = g;
    }



 
}