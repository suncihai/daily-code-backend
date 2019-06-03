package dev.local.todo.service;

import dev.local.todo.api.ApiCode;
import dev.local.todo.api.ApiResponse;
import dev.local.todo.dao.SiteUserRepository;
import dev.local.todo.model.SiteUser;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteUserService {
    @Autowired
    private SiteUserRepository siteUserRepository;

    public ApiResponse register(String username, String password) {
        JSONObject response = new JSONObject();
        SiteUser find = siteUserRepository.findUser(username);
        if(find != null) {
            return ApiResponse.createFailure(ApiCode.User.REGISTERFAILURE);
        }

        SiteUser user = new SiteUser();
        user.setUserName(username);
        user.setPassword(password);
        response.put("name",user.getUserName());
        response.put("age",user.getPassword());
        siteUserRepository.save(user);

        return ApiResponse.createSuccess(ApiCode.User.ADDSUCCESS, response);
    }

    public ApiResponse login(String username, String password) {
        JSONObject response = new JSONObject();
        SiteUser find = siteUserRepository.findUser(username);
        if(find == null) {
            return ApiResponse.createFailure(ApiCode.User.LOGIN_USERNAME_FAILURE);
        }

        if(find.getPassword().equals(password)) {
            return ApiResponse.createSuccess(ApiCode.User.ADDSUCCESS, response);
        }else{
            return ApiResponse.createFailure(ApiCode.User.LOGIN_PASSWORD_FAILURE);
        }
    }

}
