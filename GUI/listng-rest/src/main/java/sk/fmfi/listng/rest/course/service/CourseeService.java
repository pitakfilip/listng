package sk.fmfi.listng.rest.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.fmfi.listng.rest.proxy.UserApiProxy;
import sk.fmfi.listng.rest.proxy.UserrProxy;
import sk.fmfi.listng.user.dto.UserAuthDto;
import sk.fmfi.listng.user.dto.UserDto;

import java.util.List;

@Service
public class CourseeService {
    
//    @Autowired
//    private CourseApiProxy courseApi;
    
    @Autowired
    private UserApiProxy userApiProxy;
    
    @Autowired
    private  UserrProxy userrProxy;
    
    public void dummy(){
//        List<CourseDto> u = courseApi.getCourses();
//        System.out.println("JOOJ   " + u.size());
        
        UserAuthDto d = userApiProxy.getAuthUserByEmail("frantik1@uniba.sk");
        System.out.println(d.name);
        
//        UserDto dd = userrProxy.getUserByEmail(d.email);
//        System.out.println(dd.name);
        
//        userrProxy.getUserById(1L);
        System.out.println("JJJJJJ");
    }
}
