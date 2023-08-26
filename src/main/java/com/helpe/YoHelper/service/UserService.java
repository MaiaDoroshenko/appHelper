package com.helpe.YoHelper.service;

import com.helpe.YoHelper.entity.UserEntity;
import com.helpe.YoHelper.model.request.UserRequest;
import com.helpe.YoHelper.model.response.UserResponse;
import com.helpe.YoHelper.repository.UserRepository;
import com.helpe.YoHelper.service.abstractService.IUserService;
import com.helpe.YoHelper.util.SortType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@AllArgsConstructor
@Builder
public class UserService implements IUserService {

    public final UserRepository userRepository;

    @Override
    public UserResponse create(UserRequest request) {
        try {
            if (userRepository.existsByEmail(request.getEmail())) {
                throw new RuntimeException("El correo electronico ya esta en uso ");
            }
            var userToPersit = UserEntity.builder()
                    .id(request.getId())
                    .name(request.getName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .rol(request.getRol())
                    .build();
            var userPersisted = userRepository.save(userToPersit);


            return entityToResponse(userPersisted);
        } catch (Exception ex) {
            throw new RuntimeException("Error al crear el usuario" + ex.getMessage());
        }
    }

    @Override
    public UserResponse read(Long id) {
        var userFromDB = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("El usuario no encontrado para el ID: " + id));
        return entityToResponse(userFromDB);
    }

    @Override
    public UserResponse update(UserRequest request, Long id) {
        var userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("El usuario no encontrado para el ID: " + id));
        if (userToUpdate.getEmail().equals(request.getEmail()) && userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El correo electronico ya esta en uso");
        }
        userToUpdate.setName(request.getName());
        userToUpdate.setLastName(request.getLastName());
        userToUpdate.setEmail(request.getEmail());
        userToUpdate.setPassword(request.getPassword());
        userToUpdate.setRol(request.getRol());

        var userUpdate = userRepository.save(userToUpdate);

        return entityToResponse(userUpdate);
    }
    @Override
    public Page<UserResponse> findAll(Integer page, Integer size, SortType sortType) throws Exception {
        List<UserEntity> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new Exception("la lista esta vacia");
        }
        String FILED_BY_SORT = "email";
        PageRequest pageRequest = null;

        switch (sortType) {
            case NONE:
                pageRequest = PageRequest.of(page, size);
                break;
            case LOWER:
                pageRequest = PageRequest.of(page, size, Sort.by(FILED_BY_SORT).ascending());
                break;
            case UPPER:
                pageRequest = PageRequest.of(page, size, Sort.by(FILED_BY_SORT).descending());
                break;
        }

        return userRepository.findAll(pageRequest).map(this::entityToResponse);
    }

    @Override
    public void delete(Long id) {
        var userToDeleted = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("El usuario no encontrado para el ID: " + id));
        userToDeleted.setDeleted(true);
        userRepository.delete(userToDeleted);

    }

    public UserResponse entityToResponse(UserEntity entity) {
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }


}
