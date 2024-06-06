package com.example.demospringboot.controllers;

import com.example.demospringboot.dto.*;
import com.example.demospringboot.exceptions.NotFoundException;
import com.example.demospringboot.models.User;
import com.example.demospringboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController // this annotation makes a class visible to the dispatcher servlet
public class UserController
{
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    // Backend accepting all the data in user request

    /**
     * Classes doing some action
     * Controller: defining all the endpoint/ API/contract that the client will adhere to
     * Service: Does actual Business logic (saving the data, retrieving the data)
     * Repository Class: Data management prt by interacting with the underlying datastore (db, cache, repository)
     *
     *
     * Classes containing data
     * Model - Repository uses for data management  with the underlying datastore
     * Request DTOs
     * Response DTOs
     * Flow
     * Controller --> Service --> Repository -->datastore
     *
     * Controller <-- Service <-- Repository <-- datastore
     * (Response DTO)              (model)              (model)
     * @param
     */
   /** public Integer createUser(UserRequest userrequest)
    Not a good coding practice
    {
        // JSON-> java Object (Spring Web classes)
        UserService userService=new UserService();
        return  userService.create(userrequest).getId();


    }
    */
    /**
     * CRUD:
     * Create:
     * Read
     * Update
     * Delete
     */
   private UserService userService;
    public UserController()
    {

        this.userService=new UserService();
    }

    /**
     * HTTP Request types:
     * GET: Retrieve some info
     * POST: Add some info
     * PUT: update some info
     * Patch:
     * Delete: Delete some info or data on the server
     * @param
     * @return
     */


   @PostMapping("/user/create")
    public UserCreateResponse createUser(@RequestBody UserCreateRequest userCreateRequest)
   {
       // @RequestBody: Deserialization of the http object to a java object
        //UserService userService=new UserService();
        User user=userService.create(userCreateRequest);
        return UserCreateResponse.from(user);
        // returns the userID
   }
   @GetMapping("/user/fetch")
   public UserResponse getUser(@RequestParam("id") Integer userId)
    {
    // Java obj-> Json (Spring Web classes)
        //UserService userservice=new UserService();
        User user=userService.get(userId);
        return UserResponse.from(user);
    }

    /**
     * Patch vs Update
     * User data -{id:1,name:ABC,age:20} already present
     *  Either client sends the entire data to be updated {id:1,name:bc,age=20}
     *      BE will save the incoming data blindly (in memory, db, cache)
     *          OR
     *  The client send only the attributes that needs to be updated {name:ac}
     *      BE will try to merge the incoming and already present data and save the result
     * Patch: Partial Update
     */
    /**
     * Inputs from FE/Client
     * 1. variables:
     *      a.Request param: Easy to decipher for the person
     *      b.Path variable:
     * JSON dataset: @requestBody
     *
     * /order/?espId=1&corporateId=12&orderId=100: //can be deciphered: Request param
     * /esp/1/corporateId/12/order/100 // cannot be deciphered: Path Variable
     *
     */
    /**
     * Unsafe Methods: that change/update the state of data on the server (POST, Patch, Put, delete)
     * Safe Methods: Get, Head, Options
     * @param id
     * @param userUpdateRequest
     * @return
     */
    @PutMapping("/user/update/{userId}")
    public UserUpdateResponse updateUser(@PathVariable("userId") Integer id,@RequestBody UserUpdateRequest userUpdateRequest) {
        User user = this.userService.update(id, userUpdateRequest);
        return UserUpdateResponse.from(user);
    }
    @DeleteMapping("/user/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable("userId") Integer id)
    {
        try {
            this.userService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch(NotFoundException e)
        {
            this.logger.error("deleteUser: userId{} not found,e={}",id,e);
            return new ResponseEntity(HttpStatus.NOT_FOUND); // user not present 404

        }
        catch(Exception e)
        {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
