package dev.local.todo.service;

import dev.local.todo.api.*;
import dev.local.todo.dao.UserRepository;
import dev.local.todo.model.User;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ApiResponse getUsers() {
        List<User> list = userRepository.findAll();
        if( list == null) {
            return ApiResponse.createFailure(ApiCode.Common.FAILURE);
        }

        return ApiResponse.createSuccess(ApiCode.Common.SUCCESS, JSONArray.fromObject(list));
    }

    public ApiResponse findUser(String name) {
        User find = userRepository.findUser(name);
        if( find == null) {
            return ApiResponse.createFailure(ApiCode.User.FINDFAILURE);
        }
        return ApiResponse.createSuccess(ApiCode.Common.SUCCESS, JSONObject.fromObject(find));
    }

    public ApiResponse adduser(String name, Integer age) {
        JSONObject response = new JSONObject();
        User find = userRepository.findUser(name);
        if(find != null) {
            return ApiResponse.createFailure(ApiCode.User.ADDFAILURE);
        }

        User user = new User();
        user.setAge(age);
        user.setName(name);
        response.put("name",user.getName());
        response.put("age",user.getAge());
        userRepository.save(user);

        return ApiResponse.createSuccess(ApiCode.User.ADDSUCCESS, response);
    }

    public ApiResponse deleteUser(String name) {
        List<User> list = userRepository.findAll();
        boolean find = false;
        for(int i = 0;i < list.size();) {
            if(list.get(i).getName().equals(name)) {
                find = true;
                userRepository.delete(list.get(i));
                list.remove(list.get(i));
            }else{
                i++;
            }
        }

        if(find) {
            return ApiResponse.createSuccess(ApiCode.User.DELETESUCCESS);
        }else {
            return ApiResponse.createFailure(ApiCode.User.DELETEFAILURE);
        }
    }
}
