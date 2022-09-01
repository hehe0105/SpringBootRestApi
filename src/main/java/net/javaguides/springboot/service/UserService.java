package net.javaguides.springboot.service;

import net.javaguides.springboot.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveEmployee(User employee);
    List<User> getAllEmployees();
    Optional<User> getEmployeeById(long id);
    User updateEmployee(User updatedEmployee);
    void deleteEmployee(long id);
}
