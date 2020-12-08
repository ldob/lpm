package eu.ldob.lpm.be.api;

import eu.ldob.lpm.be.exception.LpmNotAllowedException;
import eu.ldob.lpm.be.model.UserModel;
import eu.ldob.lpm.be.repository.UserRepository;
import eu.ldob.lpm.be.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class AController {

    @Autowired
    UserRepository userRepository;

    protected UserModel getUserModel(UserDetailsImpl userDetails) throws LpmNotAllowedException {
        Optional<UserModel> model = userRepository.findById(userDetails.getId());

        if(model.isPresent()) {
            return model.get();
        }

        throw new LpmNotAllowedException("Request is not allowed");
    }
}
