package com.example.spring.validations.and.DTO.controller;

import com.example.spring.validations.and.DTO.model.UserV1;
import com.example.spring.validations.and.DTO.model.dto.UserDtoV1;
import com.example.spring.validations.and.DTO.model.group.UserRegisterAdvancedInfo;
import com.example.spring.validations.and.DTO.model.group.UserRegisterBasicInfo;
import com.example.spring.validations.and.DTO.model.request.UserRequest;
import com.example.spring.validations.and.DTO.service.UserService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
@Validated : validate different fields of same bean/model in different steps, so we assign fields of same model to different groups.
@valid : validation of fields.


description :

@valid : The @Valid annotation ensures the validation of the whole object. Importantly, it performs the validation of the whole object graph. However, this creates issues for scenarios needing only partial validation.
@validated : On the other hand, we can use @Validated for group validation, including the above partial validation. However, in this instance, the validated entities have to know the validation rules for all the groups or use-cases they’re used in. Here we’re mixing concerns, which can result in an anti-pattern.


Disadvantages:
anti pattern : The anti-pattern is a commonly-used process, structure or pattern of action that, despite initially appearing to be an appropriate and effective response to a problem, has more bad consequences than good ones.
1. Mixing Concerns: Entities (such as domain objects or DTOs) should ideally focus on representing data and business logic related to that data, rather than being aware of multiple validation contexts or use-cases. When entities directly contain validation rules for different groups or use-cases, they become tightly coupled with validation concerns, violating the single responsibility principle.
2. Increased Complexity and Maintenance: As the entity grows and includes validation rules for multiple groups, it becomes more complex and harder to maintain. It becomes challenging to manage and modify validation rules, especially when they're scattered across various groups and contexts within the entity.
3. Reduced Reusability: Entities tightly coupled with validation rules for multiple groups might become less reusable in different contexts. They might not fit well in scenarios where a different set of validation rules or groups is required.
4. Potential Code Duplication: Embedding validation rules for multiple groups within an entity might lead to code duplication if similar rules need to be applied across different entities or use-cases.

Disadvantages of @Valid:

Whole Object Validation: When used without specifying groups, @Valid triggers validation for the entire object graph. This can be problematic in scenarios requiring partial validation or when certain fields need exclusion from validation.
Limited Control: It might lack finer control over which fields or groups to validate in complex object graphs. For instance, in cases where nested objects need selective validation, @Valid might trigger validation for all nested objects.


Disadvantages of @Validated:
Tight Coupling with Spring: @Validated is a Spring-specific annotation, which might lead to coupling your codebase with Spring. This can potentially limit code portability to other frameworks or environments.

Less Support for Complex Validation: While @Validated is useful for group validation, it might be less suitable for complex validation scenarios involving multiple groups or nested objects.

Less Granular Control: It might not offer as fine-grained control over validation groups as required in intricate validation scenarios. This can lead to limitations when trying to validate specific groups of fields in complex object structures.




what to do :

Separation of Concerns: Consider separating validation concerns from the entities by using separate validator classes or service components responsible for validating specific groups or use-cases. This keeps entities focused on representing data only.
Validation as a Service: Implement a validation service or component that handles validation logic based on different groups or use-cases. This promotes code reuse, maintainability, and a cleaner separation of concerns.
Custom Annotations and Groups: Utilize custom annotations and validation groups effectively. Define clear and specific groups that reflect distinct validation scenarios or contexts. Then, apply these groups selectively where needed without burdening the entities with excessive validation logic.


*/

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper; // Autowire the ModelMapper bean

    @Autowired
    private Validator validator;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        UserV1 user = userService.getUserById(id);
        UserDtoV1 userDTO = modelMapper.map(user, UserDtoV1.class); // Use ModelMapper for mapping
        return ResponseEntity.ok(userDTO);
    }


    /*
     * Step 1: validation of signup form [Group 1]
     */
    @PostMapping("/add1")
    ResponseEntity<?> addUserStep1(@Validated(UserRegisterBasicInfo.class) @RequestBody UserRequest userRequest, BindingResult bindingResult) {
        /* Show message to user */
        if(bindingResult.hasErrors()){
            Map<String,String> errorMap = new HashMap<>();
            for(FieldError error : bindingResult.getFieldErrors()){
                errorMap.put(error.getField(),error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errorMap);
        }
        System.out.println(userRequest.toString());
        // persisting the user
        return ResponseEntity.ok("User is valid");
    }

    /*
     * Step 2: validation of signup form [Group 2]
     */
    @PostMapping("/add2")
    ResponseEntity<?> addUserStep2(@Validated(UserRegisterAdvancedInfo.class) @RequestBody UserRequest userRequest, BindingResult bindingResult) {
        /* Show message to user */
        if(bindingResult.hasErrors()){
            Map<String,String> errorMap = new HashMap<>();
            for(FieldError error : bindingResult.getFieldErrors()){
                errorMap.put(error.getField(),error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errorMap);
        }
        System.out.println(userRequest.toString());
        // persisting the user
        return ResponseEntity.ok("User is valid");
    }

    /*
     * validation of both groups together
     */
    @PostMapping("/add3")
    ResponseEntity<?> addUserStep3(@RequestBody UserRequest userRequest) {

        Set<ConstraintViolation<UserRequest>> violationsGroup1 = validator.validate(userRequest, UserRegisterBasicInfo.class);
        Set<ConstraintViolation<UserRequest>> violationsGroup2 = validator.validate(userRequest, UserRegisterAdvancedInfo.class);


        // Create a map to store field-level validation errors
        Map<String, String> errorMap = new HashMap<>();

        // Process violations for Group 1
        for (ConstraintViolation<UserRequest> violation : violationsGroup1) {
            errorMap.put(violation.getPropertyPath().toString(), violation.getMessage());
        }

        // Process violations for Group 2
        for (ConstraintViolation<UserRequest> violation : violationsGroup2) {
            errorMap.put(violation.getPropertyPath().toString(), violation.getMessage());
        }

        // Check if there are any violations and handle them accordingly
        if (!errorMap.isEmpty()) {
            // Handle violations (return errors, log, etc.)
            return ResponseEntity.badRequest().body(errorMap);
        }

        System.out.println(userRequest.toString());
        // persisting the user
        return ResponseEntity.ok("User is valid");
    }

    /*
     * separate validation for no group fields.
     */

    @PostMapping("/add4")
    ResponseEntity<?> addUserStep4(@RequestBody UserRequest userRequest) {
        /* Show message to user */
        // Perform validation for the field not in any group
        Set<ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);

        Map<String, String> errorMap = new HashMap<>();
        for (ConstraintViolation<UserRequest> violation : violations) {
            errorMap.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        if (!errorMap.isEmpty()) {
            return ResponseEntity.badRequest().body(errorMap);
        }
        System.out.println(userRequest.toString());
        // persisting the user
        return ResponseEntity.ok("User is valid");
    }
}
