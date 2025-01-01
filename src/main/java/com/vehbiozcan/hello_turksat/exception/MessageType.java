package com.vehbiozcan.hello_turksat.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum MessageType {

    // Genel Hata Türleri
    UNAUTHORIZED("401", "Yetkisiz Erişim", "Bu işlem için kullanıcı kimliği doğrulaması gereklidir.", HttpStatus.UNAUTHORIZED),
    FORBIDDEN("403", "Yasaklı Erişim", "Sunucu isteği anlıyor fakat yetkilendirmeyi reddediyor.", HttpStatus.FORBIDDEN),
    NOT_FOUND("404", "Kaynak Bulunamadı", "Sunucu, istenilen kaynağı bulamıyor. Lütfen URL'yi kontrol edin.", HttpStatus.NOT_FOUND),
    INTERNAL_SERVER_ERROR("500", "İç Sunucu Hatası", "Sunucu, isteği yerine getirememesiyle ilgili beklenmedik bir durumla karşılaştı.", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST("400", "Kötü İstek", "İstek, geçersiz veya eksik parametreler içeriyor. Lütfen tekrar deneyin.", HttpStatus.BAD_REQUEST),

    // JWT İlgili Hatalar
    JWT_EXPIRED("1001", "JWT Süresi Dolmuş", "JWT token süresi dolmuş. Lütfen token'ınızı yenileyin.", HttpStatus.UNAUTHORIZED),
    INVALID_JWT("1002", "Geçersiz JWT", "Sağlanan JWT token geçersiz, lütfen yeniden giriş yapın.", HttpStatus.UNAUTHORIZED),
    MISSING_JWT("1003", "Eksik JWT", "JWT token eksik veya hatalı. Lütfen giriş yapın ve token'ı sağlayın.", HttpStatus.UNAUTHORIZED),

    // Validation Hataları
    VALIDATION_ERROR("2001", "Doğrulama Hatası", "Bir doğrulama hatası oluştu. Lütfen girişinizi kontrol edin.", HttpStatus.BAD_REQUEST),
    FIELD_REQUIRED("2004", "Alan Zorunlu", "Bu alan boş bırakılamaz, lütfen tüm gerekli bilgileri doldurun.", HttpStatus.BAD_REQUEST),
    PASSWORD_TOO_SHORT("2005", "Şifre Çok Kısa", "Şifre en az 6 karakter olmalıdır.", HttpStatus.BAD_REQUEST),
    PASSWORD_MISMATCH("2006", "Şifre Uyuşmazlığı", "Girilen şifreler eşleşmiyor. Lütfen aynı şifreyi girin.", HttpStatus.BAD_REQUEST),

    // Kullanıcıya Ait Hatalar
    USER_NOT_FOUND("3001", "Kullanıcı Bulunamadı", "İstenilen kullanıcı bulunamadı. Lütfen kullanıcı adını kontrol edin.", HttpStatus.NOT_FOUND),
    USER_ALREADY_EXISTS("3002", "Kullanıcı Zaten Var", "Bu e-posta ile zaten bir kullanıcı mevcut. Lütfen farklı bir e-posta girin.", HttpStatus.CONFLICT),

    // Refresh Token İlgili Hatalar
    REFRESH_TOKEN_INVALID("4001", "Geçersiz Refresh Token", "Sağlanan refresh token geçersiz, lütfen yeniden giriş yapın.", HttpStatus.UNAUTHORIZED),
    REFRESH_TOKEN_EXPIRED("4002", "Süresi Dolmuş Refresh Token", "Refresh token süresi dolmuş. Lütfen yeni bir token almak için giriş yapın.", HttpStatus.UNAUTHORIZED),
    REFRESH_TOKEN_NOT_FOUND("4003", "Refresh Token Bulunamadı", "Sağlanan refresh token veritabanında bulunamadı.", HttpStatus.UNAUTHORIZED),

    // Rol İlgili Hatalar
    INSUFFICIENT_ROLE("5001", "Yetersiz Rol", "Bu kaynağa erişmek için yeterli yetkiniz yok. Lütfen yönetici ile iletişime geçin.", HttpStatus.FORBIDDEN);

    private final String code;
    private final String title;
    private final String message;
    private final HttpStatus status;

    MessageType(String code, String title, String message, HttpStatus status) {
        this.code = code;
        this.title = title;
        this.message = message;
        this.status = status;
    }

}
