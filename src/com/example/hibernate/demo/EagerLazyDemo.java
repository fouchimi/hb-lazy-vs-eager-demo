package com.example.hibernate.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.hibernate.demo.entity.Course;
import com.example.hibernate.demo.entity.Instructor;
import com.example.hibernate.demo.entity.InstructorDetail;

public class EagerLazyDemo {

	public static void main(String[] args) {
        
        // create session factory
        SessionFactory factory = new Configuration()
        		.configure("hibernate.cfg.xml")
        		.addAnnotatedClass(Instructor.class)
        		.addAnnotatedClass(InstructorDetail.class)
        		.addAnnotatedClass(Course.class)
                .buildSessionFactory();
 
        // create a session
        Session session = factory.getCurrentSession();
 
        try {
      
            // start a transaction
            session.beginTransaction();
            
            // get the instructor from db
            int theId = 1;
            Instructor tempInstructor = session.get(Instructor.class, theId);
            
            System.out.println("luv2code: Instructor: " + tempInstructor);
            
            System.out.println("Courses: " + tempInstructor.getCourses());
            
            // commit transaction
            session.getTransaction().commit();
            
            // close the session
            session.close();
           
            System.out.println("Session: the session is now closed !");
            
            // get course for instructor
            System.out.println("Courses: " + tempInstructor.getCourses());
            
            System.out.println("Done!");
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
        	//session.close();
            factory.close();
        }
    }
}
