package controllers;

import play.*;
import play.mvc.*;
import play.libs.Crypto;

import java.util.*;

import models.*;

 public class StudentLogin extends Secure.Security {
    
    static boolean authenticate(String username, String password) {
        Student student = Student.find("byEmail", username).first();
        return student != null && student.hashedPassword.equals(Crypto.passwordHash(";MQ*/=ti$A;|av|" + password));
    }
}