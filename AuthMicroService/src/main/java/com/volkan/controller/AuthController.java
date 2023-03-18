package com.volkan.controller;


import com.volkan.dto.request.DoLoginRequestDto;
import com.volkan.dto.request.RegisterRequestDto;
import com.volkan.exception.AuthServiceException;
import com.volkan.exception.EErrorType;
import com.volkan.repository.entity.Auth;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.volkan.constants.EndPoints.*;
import com.volkan.service.AuthService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(REGISTER)
    public ResponseEntity<Auth> register(@RequestBody @Valid RegisterRequestDto dto) {
        if(!dto.getPassword().equals(dto.getRepassword()))
            throw new AuthServiceException(EErrorType.REGISTER_ERROR_PASSWORD_UNMATCH);
        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping(LOGIN)
    public ResponseEntity<String> doLogin(@RequestBody DoLoginRequestDto dto) {
        return ResponseEntity.ok(authService.doLogin(dto));
    }

    @GetMapping(GETALL)
    public ResponseEntity<List<Auth>> findAll(String token) {
        return ResponseEntity.ok(authService.findAll(token));
    }

    @GetMapping("/message")
    public ResponseEntity<String> getMessage() {
        return ResponseEntity.ok("Genel bir mesajjj");
    }

}
