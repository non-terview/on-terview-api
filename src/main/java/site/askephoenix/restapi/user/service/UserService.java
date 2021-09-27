package site.askephoenix.restapi.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import site.askephoenix.restapi.user.dto.UserInfoDto;
import site.askephoenix.restapi.user.model.UserInfo;
import site.askephoenix.restapi.user.repository.UserRepository;


@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public Long save(UserInfoDto infoDto) {
        if (userRepository.findByEmail(infoDto.getEmail()).
                stream().findAny().isPresent()){
            return -1L;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        infoDto.setPassword(encoder.encode(infoDto.getPassword()));

        final UserInfo userInfo = userRepository.save(
                UserInfo.builder()
                        .email(infoDto.getEmail())
                        .auth(infoDto.getAuth())
                        .password(infoDto.getPassword())
                        .name(infoDto.getName())
                        .build()
        );

        return userInfo.getId();
    }


    public Long update(UserInfo userInfo, UserInfoDto infoDto){
        UserInfo modifyUser = userRepository.findById(userInfo.getId()).orElseGet(
                ()-> UserInfo.builder().build()
        );

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        infoDto.setPassword(encoder.encode(infoDto.getPassword()));

        userRepository.save(
                UserInfo.builder()
                        .id(modifyUser.getId())
                        .name(infoDto.getName())
                        .email(infoDto.getEmail())
                        .auth(modifyUser.getAuth())
                        .type(modifyUser.getType())
                        .password(infoDto.getPassword())
                        .build()
        );
        return infoDto.getId();
    }


}
