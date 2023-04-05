package sk.fmfi.listng.course.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import sk.fmfi.listng.course.api.CourseApi;
import sk.fmfi.listng.course.application.proxy.UserApiProxy;
import sk.fmfi.listng.infrastructure.common.Response;

@RestController
public class CourseController implements CourseApi {

    @Autowired
    private UserApiProxy userApiProxy;
    
    @Override
    public String dummy() {
        
        Response response = userApiProxy.dummy();
        System.out.println(response.getPayload());
        return (String) response.getPayload();
//        return "ale ahoj ferko!";
    }
}
