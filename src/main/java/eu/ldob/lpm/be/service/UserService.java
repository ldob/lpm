package eu.ldob.lpm.be.service;

import eu.ldob.lpm.be.converter.UserConverter;
import eu.ldob.lpm.be.exception.LpmNoResultException;
import eu.ldob.lpm.be.model.UserModel;
import eu.ldob.lpm.be.model.type.ERole;
import eu.ldob.lpm.be.repository.UserRepository;
import eu.ldob.lpm.be.request.UserRequest;
import eu.ldob.lpm.be.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserConverter converter;
    @Autowired
    UserRepository userRepository;

    public UserResponse update(UserRequest request) {
        Optional<UserModel> model = userRepository.findById(request.getId());

        if(model.isPresent()) {
            UserModel user = model.get();
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            return converter.modelToResponse(user);
        }

        throw new LpmNoResultException("User " + request.getId() + " not found");
    }

    public List<UserResponse> findAll() {
        List<UserResponse> responseList = new ArrayList<>();

        for(UserModel user : userRepository.findAll()) {
            responseList.add(converter.modelToResponse(user));
        }

        return responseList;
    }

    public UserResponse findById(Long id) throws LpmNoResultException {
        Optional<UserModel> model = userRepository.findById(id);
        if (model.isPresent()) {
            return converter.modelToResponse(model.get());
        }

        throw new LpmNoResultException("User " + id + " not found");
    }

    public void addRole(Long userId, ERole role) {
        Optional<UserModel> model = userRepository.findById(userId);
        if(model.isEmpty()) {
            throw new LpmNoResultException("User " + userId + " not found");
        }

        model.get().addRole(role);
    }

    public void removeRole(Long userId, ERole role) {
        Optional<UserModel> model = userRepository.findById(userId);
        if(model.isEmpty()) {
            throw new LpmNoResultException("User " + userId + " not found");
        }

        model.get().removeRole(role);
    }
}
