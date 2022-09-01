package net.javaguides.springboot.service.impl;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveEmployee(User employee) {

        Optional<User> savedEmployee = userRepository.findByEmail(employee.getEmail());
        if(savedEmployee.isPresent()){
            throw new ResourceNotFoundException("Employee already exist with given email:" + employee.getEmail());
        }
        return userRepository.save(employee);
    }

    @Override
    public List<User> getAllEmployees() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getEmployeeById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateEmployee(User updatedEmployee) {
        return userRepository.save(updatedEmployee);
    }

    @Override
    public void deleteEmployee(long id) {
        userRepository.deleteById(id);
    }
}
