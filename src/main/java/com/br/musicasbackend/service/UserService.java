package com.br.musicasbackend.service;

import com.br.musicasbackend.entity.model.User;
import com.br.musicasbackend.entity.request.UserRequest;
import com.br.musicasbackend.entity.response.UserResponse;
import com.br.musicasbackend.repository.UserRepository;
import com.br.musicasbackend.utils.Utils;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    private final ModelMapper mapper = new ModelMapper();
    private final Utils removeAcentos = new Utils();


    public User saveUser(@Valid UserRequest userRequest) {
        User user = new User(userRequest.uuid(), removeAcentos.removerAcentos(userRequest.name()), removeAcentos.removerAcentos(userRequest.cargo()));
        return userRepository.save(user);


    }

    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponse(user.getUuid(), user.getName(), user.getCargo()))
                .collect(Collectors.toList());

    }

    public List<UserResponse> findByName(String nome) {
        return userRepository.findByName(nome)
                .stream()
                .map(user -> new UserResponse(user.getUuid(), user.getName(), user.getCargo()))
                .collect(Collectors.toList());
    }


    public List<UserResponse> findByCargo(String cargo) {
        return userRepository.findByCargo(cargo)
                .stream()
                .map(user -> new UserResponse(user.getUuid(), user.getName(), user.getCargo()))
                .collect(Collectors.toList());
    }

    public UserResponse updateUser(String uuid, UserRequest userRequest) {
        User user = userRepository.findById(uuid)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        user.setName(userRequest.name());
        user.setCargo(userRequest.cargo());

        userRepository.save(user);
        return new UserResponse(user.getUuid(), user.getName(), user.getCargo());
    }
}

