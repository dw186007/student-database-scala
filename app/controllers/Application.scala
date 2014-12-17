package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import views._
import models._

/**
 * Manage a database of students
 */
object Application extends Controller { 
  
  /**
   * This result directly redirect to the application home.
   */
  val Home = Redirect(routes.Application.list(0, 2, ""))
  
  /**
   * Describe the student form (used in both edit and create screens).
   */ 
  val studentForm = Form(
    mapping(
      "id" -> ignored(None:Option[Long]),
      "name" -> nonEmptyText,
      "debts" -> number,
      "introduced" -> optional(date("yyyy-MM-dd")),
      "discontinued" -> optional(date("yyyy-MM-dd")),
      "department" -> optional(longNumber)
    )(Student.apply)(Student.unapply)
  )
  
  // -- Actions

  /**
   * Handle default path requests, redirect to students list
   */  
  def index = Action { Home }
  
  /**
   * Display the paginated list of students.
   *
   * @param page Current page number (starts from 0)
   * @param orderBy Column to be sorted
   * @param filter Filter applied on student names
   */
  def list(page: Int, orderBy: Int, filter: String) = Action { implicit request =>
    Ok(html.list(
      Student.list(page = page, orderBy = orderBy, filter = ("%"+filter+"%")),
      orderBy, filter
    ))
  }
  
  /**
   * Display the 'edit form' of a existing Student.
   *
   * @param id Id of the student to edit
   */
  def edit(id: Long) = Action {
    Student.findById(id).map { student =>
      Ok(html.editForm(id, studentForm.fill(student), Department.options))
    }.getOrElse(NotFound)
  }
  
  /**
   * Handle the 'edit form' submission 
   *
   * @param id Id of the student to edit
   */
  def update(id: Long) = Action { implicit request =>
    studentForm.bindFromRequest.fold(
      formWithErrors => BadRequest(html.editForm(id, formWithErrors, Department.options)),
      student => {
        Student.update(id, student)
        Home.flashing("success" -> "Student %s has been updated".format(student.name))
      }
    )
  }
  
  /**
   * Display the 'new student form'.
   */
  def create = Action {
    Ok(html.createForm(studentForm, Department.options))
  }
  
  /**
   * Handle the 'new student form' submission.
   */
  def save = Action { implicit request =>
    studentForm.bindFromRequest.fold(
      formWithErrors => BadRequest(html.createForm(formWithErrors, Department.options)),
      student => {
        Student.insert(student)
        Home.flashing("success" -> "Student %s has been created".format(student.name))
      }
    )
  }
  
  /**
   * Handle student deletion.
   */
  def delete(id: Long) = Action {
    Student.delete(id)
    Home.flashing("success" -> "Student has been deleted")
  }

  /**
   * Handle removing students and display the paginated list of students.
   *
   * @param debts Maximum count of debts allowed.
   */
  def expel(debts: Int) = Action { implicit request =>
    Student.expel(debts)
    Home.flashing("success" -> "Razdolbays have been expelled")
  }

}
            
