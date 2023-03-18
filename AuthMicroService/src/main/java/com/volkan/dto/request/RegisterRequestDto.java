package com.volkan.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Builder // Builder, bir sınıftan nesne türetmek için özel oluşturulmuş bir method
@Data // Data,get, set methodlarını tanımlar
@NoArgsConstructor // Parametresiz constructor tanımlar
@AllArgsConstructor // 1....n kadar olan tüm parametreli constructorları tanımlar
public class RegisterRequestDto {
    @NotBlank(message = "Kullanıcı adı boş geçilemez")
    @Size (min=3, max=32)
    String username;
    @Email(message = "Lütfen geçerli bir email adresi giriniz")
    String email;
    @NotBlank(message="Şifre boş geçilemez")
    @Size (min=8, max=64)
    @Pattern(
            message = "Şifre en az 8 karakter olmalı, içinde en az bir büyük bir küçük harf ve rakam olamalıdır.",
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$")
    String password;
    String repassword;
}
