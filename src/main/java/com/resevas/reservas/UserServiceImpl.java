package com.resevas.reservas;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserConverter.mapToUser(userDto);

        User savedUser = userRepository.save(user);
        return UserConverter.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException(
                "User not exists with a given id : " + userId));

        return UserConverter.mapToUserDto(existingUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> UserConverter.mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {

        User existingUser = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "User not exists with a given id : " + userDto.getId()));

        // convert EmployeeDto to Employee JPA entity
        User user = UserConverter.mapToUser(userDto);
        return UserConverter.mapToUserDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Employee not exists with a given id : " + userId));

        userRepository.deleteById(userId);
    }

}
