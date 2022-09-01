package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createEmployee(@RequestBody User employee){
        return userService.saveEmployee(employee);
    }

    @GetMapping
    public List<User> getAllEmployees(){
        return userService.getAllEmployees();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getEmployeeById(@PathVariable("id") long employeeId){
        return userService.getEmployeeById(employeeId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateEmployee(@PathVariable("id") long employeeId,
                                               @RequestBody User employee){
        return userService.getEmployeeById(employeeId)
                .map(savedEmployee -> {

                    savedEmployee.setFirstName(employee.getFirstName());
                    savedEmployee.setLastName(employee.getLastName());
                    savedEmployee.setEmail(employee.getEmail());

                    User updatedEmployee = userService.updateEmployee(savedEmployee);
                    return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long employeeId){

        userService.deleteEmployee(employeeId);

        return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);

    }

}
