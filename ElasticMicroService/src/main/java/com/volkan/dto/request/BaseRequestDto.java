package com.volkan.dto.request;

import lombok.*;

@Builder // Builder, bir sınıftan nesne türetmek için özel oluşturulmuş bir method
@Data // Data,get, set methodlarını tanımlar
@NoArgsConstructor // Parametresiz constructor tanımlar
@AllArgsConstructor // 1....n kadar olan tüm parametreli constructorları tanımlar
public class BaseRequestDto {
    String token;
    /**
     * Her bir istekte göstermek istediğimiz kayıt adedi
     */
    Integer pageSize;
    /**
     * Şu an bulunduğumuz, talep ettiğimiz sayfa numarası
     */
    Integer currentPage;
    /**
     * Hangi alan a göre sıralama yapılacak ise o alanın adı
     */
    String sortParameter;
    /**
     * sıralamanın yönü, a..Z, ASC,DESC
     */
    String direction;

}
