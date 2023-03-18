package com.volkan.rabbitmq.model;

import lombok.*;

import java.io.Serializable;

/**
 * ÖNEMLİ!!!!!!
 * Mutlaka modeller serileştirilmelidir.
 * Ayrıca paket ismi dahil olmak üzere bu sınıfın aynısı, iletilen serviste de olmalıdır.
 */

@Builder // Builder, bir sınıftan nesne türetmek için özel oluşturulmuş bir method
@Data // Data,get, set methodlarını tanımlar
@NoArgsConstructor // Parametresiz constructor tanımlar
@AllArgsConstructor // 1....n kadar olan tüm parametreli constructorları tanımlar
public class SaveAuthModel implements Serializable {
    Long authid;
    String username;
    String email;
}
