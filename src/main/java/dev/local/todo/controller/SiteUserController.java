package dev.local.todo.controller;

import dev.local.todo.api.ApiBase;
import dev.local.todo.api.ApiResponse;
import dev.local.todo.dao.SiteUserRepository;
import dev.local.todo.service.SiteUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SiteUserController {

   @Autowired
   SiteUserRepository siteUserRepository;

   @Autowired
   SiteUserService siteUserService;

   @ApiOperation(value = "Register", response = Iterable.class)
   @ApiImplicitParams({
           @ApiImplicitParam(name = "username", value = "username, e.g. peter@gmail.comr", required = true, dataType = "String", paramType = "query"),
           @ApiImplicitParam(name = "password", value = "password, e.g. 12345678", required = true, dataType = "String", paramType = "query"),
   })
   @ApiResponses(value = {
           @io.swagger.annotations.ApiResponse(code = 200, message = "" +
                   "说明       | code码 <br/>" +
                   "成功       | 0 <br/>" +
                   "失败       | -1 <br/>"
           )
   }
   )
   @RequestMapping("/register")
   public @ResponseBody ApiResponse Register(final String username, final String password) {
      return new ApiBase() {
         @Override
         protected void validate() throws Exception {
            Validate.notNull(username,"username is empty");
            Validate.notNull(password,"password is empty");
         }

         @Override
         protected ApiResponse process() throws Exception {
            return siteUserService.register(username, password);
         }
      }.run();
   }

   @ApiOperation(value = "Login", response = Iterable.class)
   @ApiImplicitParams({
           @ApiImplicitParam(name = "username", value = "username, e.g. peter@gmail.comr", required = true, dataType = "String", paramType = "query"),
           @ApiImplicitParam(name = "password", value = "password, e.g. 12345678", required = true, dataType = "String", paramType = "query"),
   })
   @ApiResponses(value = {
           @io.swagger.annotations.ApiResponse(code = 200, message = "" +
                   "说明       | code码 <br/>" +
                   "成功       | 0 <br/>" +
                   "失败       | -1 <br/>"
           )
   }
   )
   @RequestMapping("/login")
   public @ResponseBody ApiResponse Login(final String username, final String password) {
      return new ApiBase() {
         @Override
         protected void validate() throws Exception {
            Validate.notNull(username,"username is empty");
            Validate.notNull(password,"password is empty");
         }

         @Override
         protected ApiResponse process() throws Exception {
            return siteUserService.login(username, password);
         }
      }.run();
   }
}
