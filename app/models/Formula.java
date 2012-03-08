package models;
 
import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Formula extends Model {

	public Activity assignment;
	public int amountToAdd;



    public Formula(Activity assignment, int amountToAdd) {
    	this.assignment = assignment;
    	this.amountToAdd = amountToAdd;
    }

    public int calculate(int rawGrade) {
    	return rawGrade + amountToAdd;
    }



 
}