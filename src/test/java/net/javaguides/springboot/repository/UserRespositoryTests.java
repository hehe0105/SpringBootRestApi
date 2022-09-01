package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.User;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class UserRespositoryTests {

    @Autowired
    private UserRepository userRepository;

    private User employee;

    @BeforeEach
    public void setup(){
        employee = User.builder()
                .firstName("Ramesh")
                .lastName("Fadatare")
                .email("ramesh@gmail,com")
                .password("123456")
                .build();
    }
    // JUnit test for save employee operation
    //@DisplayName("JUnit test for save employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee(){

        //given - precondition or setup
        User employee = User.builder()
                .firstName("Ramesh")
                .lastName("Ramesh")
                .email("ramesh@gmail,com")
                .password("123456")
                .build();
        // when - action or the behaviour that we are going test
        User savedEmployee = userRepository.save(employee);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }


    // JUnit test for get all employees operation
    @DisplayName("JUnit test for get all employees operation")
    @Test
    public void givenEmployeesList_whenFindAll_thenEmployeesList(){
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ramesh")
//                .lastName("Ramesh")
//                .email("ramesh@gmail,com")
//                .build();

        User employee1 = User.builder()
                .firstName("John")
                .lastName("Cena")
                .email("cena@gmail,com")
                .password("123456")
                .build();

        userRepository.save(employee);
        userRepository.save(employee1);

        // when -  action or the behaviour that we are going test
        List<User> employeeList = userRepository.findAll();

        // then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);

    }

    // JUnit test for get employee by id operation
    @DisplayName("JUnit test for get employee by id operation")
    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject(){
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ramesh")
//                .lastName("Ramesh")
//                .email("ramesh@gmail,com")
//                .build();
        userRepository.save(employee);

        // when -  action or the behaviour that we are going test
        User employeeDB = userRepository.findById(employee.getId()).get();

        // then - verify the output
        assertThat(employeeDB).isNotNull();
    }

    // JUnit test for get employee by email operation
    @DisplayName("JUnit test for get employee by email operation")
    @Test
    public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject(){
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ramesh")
//                .lastName("Fadatare")
//                .email("ramesh@gmail,com")
//                .build();
        userRepository.save(employee);

        // when -  action or the behaviour that we are going test
        User employeeDB = userRepository.findByEmail(employee.getEmail()).get();

        // then - verify the output
        assertThat(employeeDB).isNotNull();
    }

    // JUnit test for update employee operation
    @DisplayName("JUnit test for update employee operation")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee(){
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ramesh")
//                .lastName("Fadatare")
//                .email("ramesh@gmail,com")
//                .build();
        userRepository.save(employee);

        // when -  action or the behaviour that we are going test
        User savedEmployee = userRepository.findById(employee.getId()).get();
        savedEmployee.setEmail("ram@gmail.com");
        savedEmployee.setFirstName("Ram");
        User updatedEmployee =  userRepository.save(savedEmployee);

        // then - verify the output
        assertThat(updatedEmployee.getEmail()).isEqualTo("ram@gmail.com");
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Ram");
    }

    // JUnit test for delete employee operation
    @DisplayName("JUnit test for delete employee operation")
    @Test
    public void givenEmployeeObject_whenDelete_thenRemoveEmployee(){
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ramesh")
//                .lastName("Fadatare")
//                .email("ramesh@gmail,com")
//                .build();
        userRepository.save(employee);

        // when -  action or the behaviour that we are going test
        userRepository.deleteById(employee.getId());
        Optional<User> employeeOptional = userRepository.findById(employee.getId());

        // then - verify the output
        assertThat(employeeOptional).isEmpty();
    }

    // JUnit test for custom query using JPQL with index
    @DisplayName("JUnit test for custom query using JPQL with index")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQL_thenReturnEmployeeObject(){
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ramesh")
//                .lastName("Fadatare")
//                .email("ramesh@gmail,com")
//                .build();
        userRepository.save(employee);
        String firstName = "Ramesh";
        String lastName = "Fadatare";

        // when -  action or the behaviour that we are going test
        User savedEmployee = userRepository.findByJPQL(firstName, lastName);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
    }

    // JUnit test for custom query using JPQL with Named params
    @DisplayName("JUnit test for custom query using JPQL with Named params")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQLNamedParams_thenReturnEmployeeObject(){
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ramesh")
//                .lastName("Fadatare")
//                .email("ramesh@gmail,com")
//                .build();
        userRepository.save(employee);
        String firstName = "Ramesh";
        String lastName = "Fadatare";

        // when -  action or the behaviour that we are going test
        User savedEmployee = userRepository.findByJPQLNamedParams(firstName, lastName);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
    }

    // JUnit test for custom query using native SQL with index
    @DisplayName("JUnit test for custom query using native SQL with index")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQL_thenReturnEmployeeObject(){
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ramesh")
//                .lastName("Fadatare")
//                .email("ramesh@gmail,com")
//                .build();
        userRepository.save(employee);
        // String firstName = "Ramesh";
        // String lastName = "Fadatare";

        // when -  action or the behaviour that we are going test
        User savedEmployee = userRepository.findByNativeSQL(employee.getFirstName(), employee.getLastName());

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
    }

    // JUnit test for custom query using native SQL with named params
    @DisplayName("JUnit test for custom query using native SQL with named params")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQLNamedParams_thenReturnEmployeeObject(){
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Ramesh")
//                .lastName("Fadatare")
//                .email("ramesh@gmail,com")
//                .build();
        userRepository.save(employee);
        // String firstName = "Ramesh";
        // String lastName = "Fadatare";

        // when -  action or the behaviour that we are going test
        User savedEmployee = userRepository.findByNativeSQLNamed(employee.getFirstName(), employee.getLastName());

        // then - verify the output
        assertThat(savedEmployee).isNotNull();
    }

}
