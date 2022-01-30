package gmail.salokin1991.restbackend.controller;

import gmail.salokin1991.restbackend.domain.LoginInfo;
import gmail.salokin1991.restbackend.domain.UserInfo;
import gmail.salokin1991.restbackend.exception.InvalidUserNameException;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class BankController {

    private final Map<String, UserInfo> users;

    BankController(Map<String, UserInfo> user) {
        this.users = user;
        user.put("Dima", UserInfo.builder().userName("Dima").build());
        user.put("Olga", UserInfo.builder().userName("Olga").build());
        user.put("Ivan", UserInfo.builder().userName("Ivan").build());
    }

    @PostMapping("user/login")
    @ApiOperation("Авторизация")
    public UserInfo doLogin(@RequestBody LoginInfo loginInfo) {
        if (loginInfo.getUserName().equals("Dima")) {
            return UserInfo.builder()
                    .loginDate(new Date())
                    .userName(loginInfo.getUserName())
                    .build();
        } else {
            throw new InvalidUserNameException();
        }
    }

    @GetMapping("user/getAll")
    @ApiOperation("Получение всех юзеров")
    public List<UserInfo> getAllUsersInfo() {
        return users.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
