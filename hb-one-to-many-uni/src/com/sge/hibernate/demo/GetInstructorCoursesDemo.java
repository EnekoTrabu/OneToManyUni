package com.sge.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sge.hibernate.demo.entity.Course;
import com.sge.hibernate.demo.entity.Instructor;
import com.sge.hibernate.demo.entity.InstructorDetail;

public class GetInstructorCoursesDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();

			// create session
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			int theId = 1;
			
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			for (Course cursito : tempInstructor.getCourses()) {
				System.out.println(cursito.toString());
				
			}
			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}

}
