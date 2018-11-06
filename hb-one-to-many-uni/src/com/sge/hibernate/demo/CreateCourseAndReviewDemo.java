package com.sge.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sge.hibernate.demo.entity.Course;
import com.sge.hibernate.demo.entity.Instructor;
import com.sge.hibernate.demo.entity.InstructorDetail;
import com.sge.hibernate.demo.entity.Review;

public class CreateCourseAndReviewDemo {
	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();

// create session
		Session session = factory.getCurrentSession();

		try {

// start a transaction
			session.beginTransaction();


// create some courses
			Course tempCourse1 = new Course("HTML");
			Review tempReview1 = new Review("PHP fucking amo");
			Review tempReview2 = new Review("CSS a lo Styloxa");
			
			Course tempCourse2 = new Course("FilosofiaEntusiasta");
			Review tempReview3 = new Review("Platón del siglo XXI");
			Review tempReview4 = new Review("Los peripatéticos");

// add courses to instructor
			tempCourse1.add(tempReview1);
			tempCourse1.add(tempReview2);
			
			tempCourse2.add(tempReview3);
			tempCourse2.add(tempReview4);

// save the courses
			session.save(tempCourse1);
			session.save(tempCourse2);

// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		} finally {

// add clean up code
//session.close();

			factory.close();
		}
	}
}
