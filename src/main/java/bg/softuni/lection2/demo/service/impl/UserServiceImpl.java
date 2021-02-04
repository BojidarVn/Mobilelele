package bg.softuni.lection2.demo.service.impl;

import bg.softuni.lection2.demo.model.UserEntity;
import bg.softuni.lection2.demo.model.UserRoleEntity;
import bg.softuni.lection2.demo.model.enums.UserRoleEnum;
import bg.softuni.lection2.demo.repository.UserRepository;
import bg.softuni.lection2.demo.security.CurrentUser;
import bg.softuni.lection2.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private CurrentUser currentUser;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository
            , CurrentUser currentUser) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }

    @Override
    public boolean authenticate(String userName, String password) {

        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(userName);

        if (userEntityOptional.isEmpty()) {
            return false;
        } else {
            return passwordEncoder.matches(password, userEntityOptional.get().getPassword());

        }
    }

    @Override
    public void loginUser(String userName) {

        UserEntity user = userRepository.findByUsername(userName).orElseThrow();

        List<UserRoleEnum> userRoles = user
                .getUserRoles()
                .stream()
                .map(UserRoleEntity::getRole)
                .collect(Collectors.toList());

        currentUser
                .setAnonymous(false)
                .setName(user.getUsername())
                .setUserRole(userRoles);
    }

    @Override
    public void logoutCurrantUser() {
        currentUser.setAnonymous(true);
    }
}
























