package com.volkan.controller;

import static com.volkan.constants.EndPoints.*;

import com.volkan.dto.request.AddUserRequestDto;
import com.volkan.dto.request.BaseRequestDto;
import com.volkan.repository.entity.UserProfile;
import com.volkan.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ELASTIC+USER)
@RequiredArgsConstructor
public class UserProfileController {
    private final UserProfileService userProfileService;
    @PostMapping(SAVE)
    public ResponseEntity<Void> addUser(@RequestBody AddUserRequestDto dto) {
        userProfileService.saveDto(dto);
        return ResponseEntity.ok().build();
    }
    @GetMapping(GETALL)
    public ResponseEntity<Iterable<UserProfile>> findAll(){
        return ResponseEntity.ok(userProfileService.findAll());
    }
    @PostMapping(GETALLPAGE)
    public ResponseEntity<Page<UserProfile>> findAll (@RequestBody BaseRequestDto dto) {
        return ResponseEntity.ok(userProfileService.findAll(dto));
    }

}
