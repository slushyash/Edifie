package models;
 
import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;
import play.libs.Crypto;

import org.joda.time.*;

@Entity
public class Student extends Model {
 
    public String firstName;
    public String middleName;
	public String lastName;
    public String hashedPassword;
    public int gradeLevel; // like 9 for fishie, 10 for soph, etc
    @OneToMany
    public List<Course> courses;
    public String email;
    @OneToMany
    public List<CourseTimeSlot> schedule;
    @OneToMany
    public List<Grade> grades;

    
    public Student(String firstName, String middleName, String lastName, String password, String email) {
        String salt = ";MQ*/=ti$A;|av|"; // thank you wordpress generator. pretty darn random
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.hashedPassword = Crypto.passwordHash(salt + password); // only md5. blech
        this.courses = new ArrayList<Course>();
        this.email = email;
    }

    public HashMap getAllGrades() {
        HashMap grades = new HashMap<Integer, Double>(15);
        for(Course c : this.courses) { grades.put(c.id, this.getAverage(c.id)); }
        return grades;
    }

    public void addCourseToStudent(Course course, CourseTimeSlot timeslot) {
    	courses.add(course);
        schedule.add(timeslot);
    	this.save();
    }

    public void addCourse(Course course) {
        courses.add(course);
        this.save();
    }

    public String toString() {
        String toBeReturned = "Name: " + firstName + " " + middleName + " " + lastName;
        toBeReturned = toBeReturned + "\n" + "Grade: " + gradeLevel;
        toBeReturned = toBeReturned + "\n" + courses;
        toBeReturned = toBeReturned + "\n" + "Email: " + email;
        return toBeReturned;
    }

    public double getGrade(Course course) {
        return course.getGrade(this);
    }

    public List<Course> getCoursesOnDate(Date date) {
        // there HAS to be a better way to do this...
        String orQuery = "";
        for(int i = 0; i < schedule.size() - 1; i++) {
            orQuery += "c.timeslot.id = "
            orQuery += "" + schedule.get(i).id + " ";
            if(i != schedule.size() - 1) {
                orQuery += "or ";
            }
        }

        List<CourseDay> courseDays = CourseDay.find("select c from CourseDay c where " + orQuery + "AND c.date = ?", date).fetch();
        List<Course> courses = new ArrayList<Course>();
        for(CourseDay c : courseDays) {
            courses.add(c.timeslot.course);
        }

        return courses;
    }

    public List<Course> getCoursesOnTomorrow() {
        DateTime now = new DateTime();
        now = now.withTimeAtStartOfDay();
        Date tomorrow = now.plusDays(1).toDate();
        return this.getCoursesOnDate(tomorrow);
    }

    public List<Activity> getActivitiesDue(Course course, Date date) {
        CourseTimeSlot timeslot = CourseTimeSlot.find("select c from CourseTimeSlot c where c.course.id = ?", course.id).first();
        return course.getActivitiesDue(timeslot, date);
    }


 
}