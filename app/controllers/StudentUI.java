package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
@With(StudentLogin.class)
public class StudentUI extends Controller {

    public static void index() {
    	Student student = Student.find("byEmail", StudentLogin.connected()).first();
    }

}