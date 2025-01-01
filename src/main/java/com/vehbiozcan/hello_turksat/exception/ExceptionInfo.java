package com.vehbiozcan.hello_turksat.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionInfo<T> {
    /// Bu sınıf ApiError yapımıza ekleyeceğimiz özelleştirilmiş hata mesajlarımızı eklememizi sağlar.
    /// Mesaj kısmının dinamik olması bize esneklik sağlar ister Json istersek String vs gönderebilmemize olanak tanır.
    private String hostname;
    private String path;
    private Date timeStamp;
    private T message;
}
