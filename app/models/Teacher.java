package models;
 
import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;
import play.libs.Crypto;

@Entity
public class Teacher extends Model {
 
    public String firstName;
    public String middleName;
	public String lastName;
    public String hashedPassword;
    @OneToMany // should be manytomany. for simplicity
    public List<Course> coursesTeaching;
    //public String id; // int?
    public String email;

    
    public Teacher(String firstName, String middleName, String lastName, String password, String email) {
        String salt = ";MQ*/=ti$A;|av|"; // thank you wordpress generator. pretty darn random
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.hashedPassword = Crypto.passwordHash(salt + password); // only md5. blech
        this.coursesTeaching = new ArrayList<Course>();
        //this.id = id;
        this.email = email;
    }

    public void addCourse(Course course) {
        coursesTeaching.add(course);
        this.save();
    }
    



 
}