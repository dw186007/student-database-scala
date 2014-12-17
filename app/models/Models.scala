package models

import java.util.{Date}

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

import scala.language.postfixOps

case class Department(id: Option[Long] = None,
                      name: String)
case class Student(id: Option[Long] = None,
                   name: String,
                   debts: Int,
                   introduced: Option[Date],
                   discontinued: Option[Date],
                   departmentId: Option[Long])


/**
 * Helper for pagination.
 */
case class Page[A](items: Seq[A], page: Int, offset: Long, total: Long) {
  lazy val prev = Option(page - 1).filter(_ >= 0)
  lazy val next = Option(page + 1).filter(_ => (offset + items.size) < total)
}

object Student {
  
  // -- Parsers
  
  /**
   * Parse a Student from a ResultSet
   */
  val simple = {
    get[Option[Long]]("student.id") ~
    get[String]("student.name") ~
    get[Int]("student.debts") ~
    get[Option[Date]]("student.introduced") ~
    get[Option[Date]]("student.discontinued") ~
    get[Option[Long]]("student.department_id") map {
      case id~name~debts~introduced~discontinued~departmentId => Student(id, name, debts, introduced, discontinued, departmentId)
    }
  }
  
  /**
   * Parse a (Student,Department) from a ResultSet
   */
  val withDepartment = Student.simple ~ (Department.simple ?) map {
    case student~department => (student,department)
  }
  
  // -- Queries
  
  /**
   * Retrieve a student from the id.
   */
  def findById(id: Long): Option[Student] = {
    DB.withConnection { implicit connection =>
      SQL("select * from student where id = {id}").on('id -> id).as(Student.simple.singleOpt)
    }
  }
  
  /**
   * Return a page of (Student,Department).
   *
   * @param page Page to display
   * @param pageSize Number of students per page
   * @param orderBy Student property used for sorting
   * @param filter Filter applied on the name column
   */
  def list(page: Int = 0, pageSize: Int = 10, orderBy: Int = 1, filter: String = "%"): Page[(Student, Option[Department])] = {
    
    val offest = pageSize * page
    
    DB.withConnection { implicit connection =>
      
      val students = SQL(
        """
          select * from student 
          left join department on student.department_id = department.id
          where student.name like {filter}
          order by {orderBy}
          limit {pageSize} offset {offset}
        """
      ).on(
        'pageSize -> pageSize, 
        'offset -> offest,
        'filter -> filter,
        'orderBy -> orderBy
      ).as(Student.withDepartment *)

      val totalRows = SQL(
        """
          select count(*) from student 
          left join department on student.department_id = department.id
          where student.name like {filter}
        """
      ).on(
        'filter -> filter
      ).as(scalar[Long].single)

      Page(students, page, offest, totalRows)
      
    }
    
  }
  
  /**
   * Update a student.
   *
   * @param id The student id
   * @param student The student values.
   */
  def update(id: Long, student: Student) = {
    DB.withConnection { implicit connection =>
      SQL(
        """
          update student
          set name = {name}, debts = {debts}, introduced = {introduced}, discontinued = {discontinued}, department_id = {department_id}
          where id = {id}
        """
      ).on(
        'id -> id,
        'name -> student.name,
        'debts -> student.debts,
        'introduced -> student.introduced,
        'discontinued -> student.discontinued,
        'department_id -> student.departmentId
      ).executeUpdate()
    }
  }
  
  /**
   * Insert a new student.
   *
   * @param student The student values.
   */
  def insert(student: Student) = {
    DB.withConnection { implicit connection =>
      SQL(
        """
          insert into student (name, debts, introduced, discontinued, department_id) values (
            {name}, {debts}, {introduced}, {discontinued}, {department_id}
          )
        """
      ).on(
        'name -> student.name,
        'debts -> student.debts,
        'introduced -> student.introduced,
        'discontinued -> student.discontinued,
        'department_id -> student.departmentId
      ).executeUpdate()
    }
  }
  
  /**
   * Delete a student.
   *
   * @param id Id of the student to delete.
   */
  def delete(id: Long) = {
    DB.withConnection { implicit connection =>
      SQL("delete from student where id = {id}").on('id -> id).executeUpdate()
    }
  }

  /**
   * Expel all student with more than X debts.
   *
   * @param debts Maximum count of debts allowed.
   */
  def expel(debts: Int) = {
    DB.withConnection { implicit connection =>
      SQL("delete from student where debts > {debts}").on('debts -> debts).executeUpdate()
    }
  }

}

object Department {
    
  /**
   * Parse a Department from a ResultSet
   */
  val simple = {
    get[Option[Long]]("department.id") ~
    get[String]("department.name") map {
      case id~name => Department(id, name)
    }
  }
  
  /**
   * Construct the Map[String,String] needed to fill a select options set.
   */
  def options: Seq[(String,String)] = DB.withConnection { implicit connection =>
    SQL("select * from department order by name").as(Department.simple *).
      foldLeft[Seq[(String, String)]](Nil) { (cs, c) => 
        c.id.fold(cs) { id => cs :+ (id.toString -> c.name) }
      }
  }
  
}

