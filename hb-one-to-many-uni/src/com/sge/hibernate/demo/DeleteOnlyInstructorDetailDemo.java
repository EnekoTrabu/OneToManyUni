package com.sge.hibernate.demo;

import javax.persistence.CascadeType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sge.hibernate.demo.entity.Instructor;
import com.sge.hibernate.demo.entity.InstructorDetail;

public class DeleteOnlyInstructorDetailDemo {

	public static void main(String[] args) {
		// create session factory
				SessionFactory factory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(InstructorDetail.class)
										.buildSessionFactory();
				
				// create session
				Session session = factory.getCurrentSession();
				
				try {			
					
					// start a transaction
					session.beginTransaction();

					// get instructor by primary key / id
					int theId = 9;
					InstructorDetail tempInstructorD = 
							session.get(InstructorDetail.class, theId);
//					InstructorDetail tempInstructorDetail = 
//							session.get(InstructorDetail.class, theId);
					System.out.println("Found instructor: " + tempInstructorD);

					System.out.println("The associated instructor: " + tempInstructorD.getInstructor());
					
					
					// delete the instructors
					
					
						System.out.println("Deleting: " + tempInstructorD);
						
						
						// Note: will ALSO delete associated "details" object
						// because of CascadeType.ALL
						//
						tempInstructorD.getInstructor().setInstructorDetail(null);
						
						session.delete(tempInstructorD);			
					
					
					// commit transaction
					session.getTransaction().commit();
					
					System.out.println("Done!");
				}
				finally {
					factory.close();
				}
			}
}
