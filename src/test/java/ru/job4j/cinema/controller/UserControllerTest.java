package ru.job4j.cinema.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.model.User;
import ru.job4j.cinema.service.user.UserService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private UserService userService;

    private UserController userController;

    @BeforeEach
    public void initService() {
        userService = mock(UserService.class);
        userController = new UserController(userService);
    }

    @Test
    public void whenRequestRegisterThenGetRegisterPage() {
        assertThat(userController.getRegistrationPage()).isEqualTo("/users/register");
    }

    @Disabled
    public void whenRequestPostRegisterUserThenRedirectToSessions() {
        var user = new User(1, "name", "email", "password");
        var userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        when(userService.save(userArgumentCaptor.capture())).thenReturn(Optional.of(user));
        var request = mock(HttpServletRequest.class);

        var model = new ConcurrentModel();
        var view = userController.register(user, model, request);
        var actualUser = userArgumentCaptor.getValue();

        assertThat(actualUser).isEqualTo(user);
        assertThat(view).isEqualTo("redirect:/sessions");
    }

    @Test
    public void whenRequestRegisterExistedUserThenGetErrorPage() {
        var user = new User(1, "name", "email", "password");
        when(userService.save(any(User.class))).thenReturn(Optional.empty());
        var request = mock(HttpServletRequest.class);

        var model = new ConcurrentModel();
        var view = userController.register(user, model, request);
        var error = model.get("error");
        var errorMessage = String.format("User with email: %s already exists.", user.getEmail());

        assertThat(error).isEqualTo(errorMessage);
        assertThat(view).isEqualTo("errors/404");
    }

    @Test
    public void whenRequestLoginPageThenGetLoginPage() {
        assertThat(userController.getLoginPage()).isEqualTo("/users/login");
    }

    @Test
    public void whenLoginUserThenGetTheSameDataAndRedirectToSessions() {
        var user = new User(1, "name", "email", "password");
        var emailArgumentCaptor = ArgumentCaptor.forClass(String.class);
        var passwordArgumentCaptor = ArgumentCaptor.forClass(String.class);
        when(userService.findUserByUsername(emailArgumentCaptor.capture(), passwordArgumentCaptor.capture()))
                .thenReturn(Optional.of(user));
        var request = mock(HttpServletRequest.class);
        var session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        var nameArgumentCaptor = ArgumentCaptor.forClass(String.class);
        var userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        doNothing().when(session).setAttribute(nameArgumentCaptor.capture(), userArgumentCaptor.capture());

        var model = new ConcurrentModel();
        var view = userController.login(user, model, request);
        var actualEmail = emailArgumentCaptor.getValue();
        var actualPassword = passwordArgumentCaptor.getValue();
        var name = nameArgumentCaptor.getValue();
        var actualUser = userArgumentCaptor.getValue();

        assertThat(actualEmail).isEqualTo(user.getEmail());
        assertThat(actualPassword).isEqualTo(user.getPassword());
        assertThat(name).isEqualTo("user");
        assertThat(actualUser).isEqualTo(user);
        assertThat(view).isEqualTo("redirect:/sessions");
    }

    @Test
    public void whenLoginUserThenGetSomeErrorPage() {
        var user = new User(1, "name", "email", "password");
        when(userService.findUserByUsername(any(String.class), any(String.class))).thenReturn(Optional.empty());

        var model = new ConcurrentModel();
        var request = mock(HttpServletRequest.class);
        var view = userController.login(user, model, request);
        var error = model.get("error");
        var errorMessage = "The email or password incorrect.";

        assertThat(error).isEqualTo(errorMessage);
        assertThat(view).isEqualTo("errors/404");
    }

    @Test
    public void whenLogoutThenRedirectToLoginPage() {
        var session = mock(HttpSession.class);
        doNothing().when(session).invalidate();

        var view = userController.logout(session);

        assertThat(view).isEqualTo("redirect:/users/login");
    }
}