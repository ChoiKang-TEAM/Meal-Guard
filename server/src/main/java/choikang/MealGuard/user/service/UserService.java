package choikang.MealGuard.user.service;

import choikang.MealGuard.user.entity.User;
import choikang.MealGuard.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public void createUser(String user_seq){
        User user = new User(user_seq);
        userRepository.save(user);

    }

}
