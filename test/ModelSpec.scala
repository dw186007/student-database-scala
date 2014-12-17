import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._

@RunWith(classOf[JUnitRunner])
class ModelSpec extends Specification {
  
  import models._

  // -- Date helpers
  
  def dateIs(date: java.util.Date, str: String) = new java.text.SimpleDateFormat("yyyy-MM-dd").format(date) == str
  
  // --
  
  "Student model" should {
    
    "be retrieved by id" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        
        val Some(macintosh) = Student.findById(21)
      
        macintosh.name must equalTo("Macintosh")
        macintosh.introduced must beSome.which(dateIs(_, "1984-01-24"))  
        
      }
    }
    
    "be listed along its companies" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        
        val students = Student.list()

        students.total must equalTo(574)
        students.items must have length(10)

      }
    }
    
    "be updated if needed" in {
      running(FakeApplication(additionalConfiguration = inMemoryDatabase())) {
        
        Student.update(21, Student(name="The Macintosh", debts=0, introduced=None, discontinued=None, departmentId=Some(1)))
        
        val Some(macintosh) = Student.findById(21)
        
        macintosh.name must equalTo("The Macintosh")
        macintosh.introduced must beNone
        
      }
    }
    
  }
  
}