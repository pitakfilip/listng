package sk.fmfi.listng.user.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sk.fmfi.listng.domain.user.User;
import sk.fmfi.listng.user.assembler.UserAssembler;
import sk.fmfi.listng.user.entity.UserEntity;
import sk.fmfi.listng.user.repository.UserRepository;
import sk.fmfi.listng.user.api.UserApi;
import sk.fmfi.listng.user.service.CourseApiProxy;

import java.util.Optional;

@RestController
public class UserController implements UserApi {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseApiProxy courseApiProxy;


    @ApiOperation("Returns user with id")
    @GetMapping(value = "/id")
    public UserEntity findUser (@RequestParam Long userId){
        Optional<UserEntity> response = userRepository.findById(userId);

        return response.orElse(null);
    }


}
