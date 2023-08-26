package com.helpe.YoHelper.controller;


import com.helpe.YoHelper.model.request.UserRequest;
import com.helpe.YoHelper.model.response.UserResponse;
import com.helpe.YoHelper.service.abstractService.IUserService;
import com.helpe.YoHelper.util.SortType;
import lombok.AllArgsConstructor;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final IUserService userService;

    @PostMapping("create")
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request) {
        if (request.getName() == null || request.getLastName() == null || request.getEmail() == null || request.getPassword() == null) {
            return ResponseEntity.badRequest().build();
        }
        try {
            UserResponse response = userService.create(request);
            return ResponseEntity.ok(response);
        } catch (InvalidDataAccessApiUsageException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<UserResponse> read(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(userService.read(id));
    }

    @GetMapping("all")
    public ResponseEntity<Page<UserResponse>> readAll(@RequestParam Integer page,
                                                      @RequestParam Integer size,
                                                      @RequestHeader(required = false) SortType sortType) throws Exception {
        if (Objects.isNull(sortType)) sortType = SortType.NONE;

        var response = userService.findAll(page, size, sortType);
        return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);

    }

    @PutMapping("update/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.update(request, id));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
