package controllers;
import play.jobs.*;
import play.test.*;
import models.*;
import play.*;

@OnApplicationStart
public class Bootstrap extends Job {
    public void doJob() {
            Fixtures.load("initial-data.yml");
    }
}