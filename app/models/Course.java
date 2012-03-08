package models;
 
import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;
import org.joda.time.*;

@Entity
public class Course extends Model {

	@OneToOne
	public Teacher teacher;

	@OneToMany
	public List<Student> students;

	public String name;

	@OneToMany
	public List<Activity> activities;

	@OneToMany
	public List<CourseTimeSlot> timeSlots;

	@OneToMany
	public List<GeneralCourseDay> courseDays;


    public Course(Teacher teacher, String name) {
        this.teacher = teacher;
        this.students = new ArrayList<Student>();
        this.name = name;
    }

    public double getGrade(Student student) {
    	List<Grade> grades = Grade.find("select g from Grade g where g.course.id = ? AND g.student.id = ?", this.id, student.id).fetch();
    	double weightTotal = 0;
    	for(Grade g : grades) { weightTotal += g.assignment.weight; }
    	double grade = 0;
    	for(Grade g : grades) { grade += ((double)g.grade)*(g.assignment.weight/weightTotal); }

    	return grade;
    }

    public List<Activity> getActivitiesDue(int day) {
    	List<Activity> activities = Activity.find("select a from Activity a where a.course.id = ? AND a.due.day = ?", this.id, day);
    	return activities;
    }

    public List<Activity> getActivitiesDue(CourseTimeSlot timeslot, Date date) {
    	if(timeSlots.indexOf(timeslot) == -1) { return null; }
    	CourseDay courseday = CourseDay.find("select c from CourseDay c where c.timeslot.id = ? AND c.date = ?", timeslot.id, date).first();
    	int day = courseday.generalCourseDay.day;
    	return getActivitiesDue(day);
    }

    public List<Activity> getActivitiesDueNextTime() { return null;

    }
}