package sk.fmfi.listng.rest.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.fmfi.listng.rest.common.BaseController;
import sk.fmfi.listng.rest.course.service.CourseeService;
import sk.fmfi.listng.rest.proxy.UserApiProxy;
import sk.fmfi.listng.user.dto.UserAuthDto;

import java.util.List;


@RestController
@RequestMapping("/course")
public class CourseeController extends BaseController {
    
    @Autowired
    private UserApiProxy userProxy;
    
    @Autowired
    private CourseeService courseService;
    
    @Autowired
    DiscoveryClient discoveryClient;
    
    @GetMapping("dummy")
    public String jj(){
//        List<CourseDto> courses = courseApiProxy.getCourses();

        List<ServiceInstance> course = discoveryClient.getInstances("listng-course");
        List<ServiceInstance> userr = discoveryClient.getInstances("listng-user");
        System.out.println(course.size());

        UserAuthDto user = userProxy.getAuthUserByEmail("frantik1@uniba.sk");

        System.out.println(user.toString());
        
        courseService.dummy();
        
        return "debilko";
    }
    
}
