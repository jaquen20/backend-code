package com.courses.internship.Controller;

import com.courses.internship.DTOs.CourseInstanceDTO;
import com.courses.internship.Model.Course;
import com.courses.internship.Model.CourseInstance;
import com.courses.internship.Service.CourseInstanceService;
import com.courses.internship.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin
@CrossOrigin(origins = "*")
@RequestMapping("/api/courseInstanceList")
public class CourseInstanceController {
    @Autowired
    public CourseInstanceService courseService;
    @Autowired
    public CourseService courseServiceOrg;

//    @PostMapping
//    public ResponseEntity<?> createCourseInstance( @RequestParam Long id, @RequestParam Integer year, @RequestParam Integer semester){
//        CourseInstance courseInstance=new CourseInstance();
//
//           Course course= courseServiceOrg.getDataById(id);
//           courseInstance.setYear(year);
//           courseInstance.setSemester(semester);
//           courseInstance.setCourse(course);
//
//            courseService.saveCourseInstance(courseInstance);
//            return ResponseEntity.ok("saved successfully");
//
////        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("empty data");
//    }


    @PostMapping
    public ResponseEntity<?> createCourseInstances( @RequestBody CourseInstanceDTO courseInstanceDTO){
        CourseInstance courseInstance=new CourseInstance();
        Course course=courseServiceOrg.getDetailsById(courseInstanceDTO.getId());
        courseInstance.setSemester(courseInstanceDTO.getSemester());
        courseInstance.setYear(courseInstanceDTO.getYear());
        courseInstance.setCourse(course);
        courseService.saveCourseInstance(courseInstance);
        return ResponseEntity.ok("saved successfully");
    }





    @GetMapping
    public ResponseEntity<?> getCourseList(){
        List<CourseInstance> courseInstanceList=courseService.findAllCoursesWithInstance();
        return new ResponseEntity<>(courseInstanceList,HttpStatus.OK);
    }

    @GetMapping("/{year}/{semester}/{id}")
    public  ResponseEntity<?> detailsById(@PathVariable Long id, @PathVariable Integer year, @PathVariable Integer semester ){
        CourseInstance courseInstance=courseService.getDetailsById(id);
        CourseInstance courseInstance1=courseService.findByQuery(id,year,semester);
        if (courseInstance1!=null){
            return ResponseEntity.ok(courseInstance1);
        }else return new ResponseEntity<>("not found",HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deleteById(@PathVariable Long id){
        courseService.deleteById(id);
        return new ResponseEntity<>("ok",HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchQuery(@RequestParam( value = "year", required = false) Integer year, @RequestParam(value = "semester", required = false) Integer semester){
        List<CourseInstance> courseInstanceList=courseService.searchQuery(year,semester);
        return ResponseEntity.ok(courseInstanceList);
    }


}
