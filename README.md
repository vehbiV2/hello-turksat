# Merhaba Türksat!

<hr/>

Projenin OpenAPI Swagger Dökümanı: http://localhost:8080/swagger-ui/index.html

<hr/>

## İçindekiler

- ### [Görev 1](#görev-1--hello-türksat-)
- ### [Görev 2](#görev-2--basic-authentication)
- ### [Görev 3](#görev-3--jwt-ile-role-tabanlı-authentication)


# Görev 1 : Hello Türksat 

<details>
<summary><h3 style="display: inline-block">Detayları Görüntüle</h3></summary>

## Yapılacaklar:

- [x] Uygulama konsola "Merhaba Dünya!" çıktısını basacak şekilde çalıştırılacak.
- [x] Uygulama Rest Servis olarak dönüştürülecek.
- [x] İki tane metodunuz olacak biri GET diğeri POST. İsimlendirmelerini size bırakıyorum. Rest servis metod isimlendirme standartlarını gözden geçirebilirsiniz.
- [x] GET metoduna istek atıldığında servis sonucu "Merhaba Dünya!" olacak.
- [x] POST metodu bir parametre alacak. Aldığı parametreyi servis sonucuna yazacak. Parametre TÜRKSAT ise servis sonucu "Merhaba TÜRKSAT!" olacak.
- [x] Postman uygulaması kurulup çalışıtırılacak. Yazdığınız metodlara postman üzerinden istek atılacak. Servis sonuçları görülecek.
- [x] Swagger kullanılarak yazdığınız Rest API nin görselleştirmesi yapılacak. (Swagger'da görüntülenen HTTP Durum kodları farklı senaryolar ile deneyerek Postman'dan gelen veriye göre oluşturuldu

<br/><hr/>

## İçindekiler

- [Dökümantasyon](#dökümantasyon)
  - [Rest Api Nedir?](#rest-api-nedir)
  - [Spring Boot](#spring-boot)
  - [Kod Açıklamaları](#kod-açıklamaları)
  - [Uygulama Resimleri](#uygulama-görüntüleri)
      - [Konsol Resimleri](#konsol-görüntüsü)
      - [Postman Resimleri](#postman-görüntüleri)
      - [Swagger Resimleri](#swagger-görüntüleri)
  

## Dökümantasyon

### REST API Nedir?

Rest API, internet üzerinden bir sistemin diğer bir sisteme veri göndermesini ya da almasını sağlayan bir yapı. **REST** (Representational State Transfer) protokolüne dayanır. Bu yapı, istemci (client) ve sunucu (server) arasında veri alışverişi yaparken HTTP yöntemlerini (GET, POST, PUT, DELETE gibi) kullanır.

**Nasıl çalışır?**
1. **İstemci** (mesela bir web uygulaması ya da mobil app) bir **istek** (request) gönderir.
2. **Sunucu** gelen isteğe göre bir **yanıt** (response) döner.
3. Veri genellikle **JSON** ya da **XML** formatında gönderilir.

**Nerelerde kullanılır?**
- Web uygulamalarında, mobil uygulamalarda, dış servislerle iletişimde yani kısacası, farklı sistemlerin birbiriyle konuşması gereken her yerde kullanılır.

![REST Animation](/images/m1/rest.gif)

### Spring Boot

Spring Boot, Java programlama dilinde yazılmış bir framework'tür. Spring çatısının üzerine kuruludur ve hızlı bir şekilde web uygulamaları ve mikro servisler geliştirmeyi sağlar.

![Spring Boot](/images/m1/spring.png)

### Kod Açıklamaları

### Controller

#### `HelloControllerImpl` Sınıfı

Bu sınıf, Spring Boot uygulamasında HTTP isteklerine yanıt veren bir REST controller'dır. IHelloController arayüzünü implement eder ve 3 temel endpoint sunar: biri GET, ikisi ise POST isteği kabul eder.

#### Anotasyonlar:
- **@RestController**: Bu sınıf, RESTful servis olarak çalıştığı için bu anotasyon kullanılır. Yani bu sınıf, HTTP isteklerine cevap verir.
- **@RequestMapping("api/gorev1")**: Bu anotasyon, tüm sınıfın istek yolunu tanımlar. Sınıf içerisindeki her metodun başına bu yol eklenir. Yani, istekler için bir base url görevi görür. İsteklerin "api/gorev1" yolundan yapılması gerektiğini belirtir.
- **@GetMapping(/merhaba)**: /api/gorev1/merhaba altına gelen GET isteklerini karşılar
- **@PostMapping(/merhaba)**: /api/gorev1/merhaba altına gelen POST isteklerini karşılar
- **@PostMapping(/json-merhaba)**: /api/gorev1/merhaba altına gelen POST ve RequestBody ile JSON olarak gönderilen istekleri karşılar

#### Metodlar:
1. **GET /merhaba**
  - "Merhaba Dünya!" mesajını döndürür.
   ```java
   @GetMapping("/merhaba")
   public String getHello() {
       return "Merhaba Dünya!";
   }
   ```

2. **POST /merhaba** (Parametreli)
  - Parametre alır ve "TÜRKSAT" mesajı dışında gönderilen parametreyi döndürür.
   ```java
   @PostMapping("/merhaba")
   public String sayHello(@RequestParam(required = false) String parameter) {
       if (parameter == null || parameter.isEmpty()){
           throw new BaseException(new ErrorMessage(MessageType.FIELD_REQUIRED,null));
       }
       if("TÜRKSAT".equalsIgnoreCase(parameter.trim())){
           return "Merhaba TÜRKSAT!";
       }
       return "Gönderilen parametre: " + parameter.trim();
   }
   ```

3. **POST /json-merhaba** (JSON Parametreli)
  - JSON formatında gelen veriyi alır ve "TÜRKSAT" mesajına göre yanıt verir.
   ```java
   @PostMapping("/json-merhaba")
   public String sayHello(@RequestBody DtoHello parameter) {
       if (parameter == null || parameter.getMessage().isEmpty()){
           throw new BaseException(new ErrorMessage(MessageType.FIELD_REQUIRED,null));
       }
       if("TÜRKSAT".equalsIgnoreCase(parameter.getMessage().trim())){
           return "Merhaba TÜRKSAT!";
       }
       return "Gönderilen parametre: " + parameter.getMessage().trim();
   }
   ```
---

#### `OpenAPIConfig` Sınıfı

Bu sınıf, Swagger/OpenAPI yapılandırmasını sağlar ve API yanıtlarının dökümantasyonunu oluşturur.

#### Anotasyonlar:
- **@Configuration**: Spring’e bu sınıfın bir yapılandırma sınıfı olduğunu belirtir.
- **@OpenAPIDefinition**: OpenAPI tanımının kullanılacağını belirten anotasyon.

#### Yapılandırmalar:
1. **ApiResponse Tanımlamaları**:
  - **badRequest**: 400 hatası için açıklama ve JSON formatında yanıt şeması tanımlar.
  - **notFound**: 404 hatası için açıklama ve JSON formatında yanıt şeması tanımlar.
  - **internalServerError**: 500 hatası için açıklama ve JSON formatında yanıt şeması tanımlar.

2. **`@Bean` customOpenAPI Metodu**:
  - **Components**: Tanımlanan yanıtlar (badRequest, notFound, internalServerError) OpenAPI component’ine eklenir.
  - **API Bilgisi**: API başlığı, versiyonu ve açıklaması gibi bilgiler Swagger/OpenAPI dokümantasyonuna eklenir.

   ```java
   return new OpenAPI().components(components).info(
           new Info()
                   .title("Görev 1: Merhaba TÜRKSAT!")
                   .version("0.0.1-SNAPSHOT")
                   .description("Türksat Aday Mühendislik Görevi 1")
   );
   ```

Yani bu sınıf, Swagger için hata yanıtlarını (400, 404, 500) ve API’nin genel bilgilerini dökümante eder.

#### `ApiResponseSchema` Sınıfı

Bu sınıf, özel bir API yanıtı şeması oluşturmak için kullanılır. Swagger/OpenAPI dökümantasyonunda hata yanıtlarının yapısını tanımlar.

#### Metodlar:
1. **`getCustomResponseSchema` Metodu**:
  - Bu metod, hata yanıtları için özel bir `Schema` nesnesi oluşturur.
  - **Parametreler**:
    - **timestampExample**: Hata oluşma zamanının örneği.
    - **statusExample**: Hata durum kodunun örneği (örneğin, 400, 404).
    - **errorExample**: Hata mesajının örneği.
    - **pathExample**: Hatanın oluştuğu endpoint yolunun örneği.

  - Metod, `timestamp`, `status`, `error`, ve `path` gibi alanlarla yapılandırılmış bir `Schema` döner. Bu alanlar, Swagger/OpenAPI dokümantasyonunda hata yanıtları için açıklama ve örnekler sunar.

   ```java
   public static Schema<Object> getCustomResponseSchema(String timestampExample, int statusExample, String errorExample, String pathExample) {
       return new Schema<>()
               .addProperty("timestamp", new Schema<String>().type("string").description("Hata oluşma zamanı").example(timestampExample))
               .addProperty("status", new Schema<Integer>().type("integer").description("Hata HTTP durum kodu").example(statusExample))
               .addProperty("error", new Schema<String>().type("string").description("Hata mesajı").example(errorExample))
               .addProperty("path", new Schema<String>().type("string").description("Hatanın oluştuğu endpoint").example(pathExample));
   }
   ```



## Uygulama Görüntüleri

### Konsol Görüntüsü

![alt text](images/m1/console.PNG)

<hr/>

### Postman Görüntüleri
#### Get Request gorev1/merhaba

![alt text](images/m1/get.PNG)

#### Post Request gorev1/merhaba
- parameter = türksat
  ![alt text](images/m1/post1.PNG)

- parameter = Deneme
  ![alt text](images/m1/post2.PNG)

#### HTTP Kodları

- 400 Bad Request
  ![alt text](images/m1/400.PNG)

- 404 Not Found
  ![alt text](images/m1/404.PNG)

- 500 Internal Server Error
  ![alt text](images/m1/500.PNG)

<hr/>

### Swagger Görüntüleri
#### API Description
![alt text](images/m1/swagger1.PNG)

#### GET Endpoint
![alt text](images/m1/swagger-get.PNG)

#### POST Endpoint
![alt text](images/m1/swagger-post.PNG)

![alt text](images/m1/swagger-post2.PNG)

</details>


# Görev 2 : Basic Authentication

<details>
<summary><h3 style="display: inline-block">Detayları Görüntüle</h3></summary>

## Yapılacaklar:
- [x] Basic Authentication eklenmeli. Authentication bilgisi kullaniciAdi:şifre formatında olsun ve base64 encoded şekilde gönderilmeli.
  - kullaniciAdi testKullanici, şifre testSifre olacak şekilde ayarlayanabilir.

---

- [Dökümantasyon](#dökümantasyon-1)
    - [Spring Security Nedir?](#spring-security-nedir-ne-işe-yarar-)
    - [Kod Açıklamaları](#kod-açıklamaları-1)
    - [Uygulama Resimleri](#uygulama-görüntüleri-1)
        - [Konsol Resimleri](#konsol-görüntüsü-1)
        - [Postman Resimleri](#postman-görüntüleri-1)

## Dökümantasyon

### Spring Security Nedir Ne İşe Yarar ?
Spring Security, Spring uygulamalarında kimlik doğrulama (giriş) ve yetkilendirme (erişim kontrolü) işlemlerini yöneten bir güvenlik çerçevesidir. Kullanıcıların sisteme giriş yapmasını sağlar ve hangi kullanıcıların hangi kaynaklara erişebileceğini kontrol eder, Örneğin 3. Kısım olan JWT ile rol tabanlı authentication bölümünde olduğu gibi.

### Kod Açıklamaları

### `SecurityConfig` Sınıfı

```java 

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/gorev1/**").authenticated() // /gorev1 rotasına sadece doğrulama yapmış kullanıcılar erişebilir
                .anyRequest().permitAll() // belirlediğimiz rota dışındakiler için doğrulama istemez
                .and()
                .formLogin()
                .failureHandler((request, response, exception) -> {
                    // Hatalı giriş durumunda özel mesaj
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("KULLANICI ADI VEYA SIFRE HATALI");
                    response.getWriter().flush();
                })
                .and()

                .httpBasic(); // Base64 HTTP Basic Authentication ekliyoruz
    }
}

```

Bu sınıf, Spring Security ile güvenlik yapılandırmasını sağlar. `WebSecurityConfigurerAdapter` sınıfını extend eder ve HTTP güvenlik ayarlarını özelleştirir.

#### Anotasyonlar:
- **@EnableWebSecurity**: Bu anotasyon, Spring Security'yi etkinleştirir ve güvenlik yapılandırmasını sağlar.

#### Yapılandırmalar:
1. **`configure(HttpSecurity http)` Metodu**
    - Bu metod, HTTP güvenlik ayarlarını özelleştirir.

    - **`http.csrf().disable()`**: CSRF (Cross-Site Request Forgery) korumasını devre dışı bırakır. Genellikle API'ler için devre dışı bırakılır.

    - **`authorizeRequests()`**: İsteklere güvenlik kontrolleri ekler.
        - **`.antMatchers("/gorev1/**").authenticated()`**: `/gorev1/**` yolundaki isteklerin sadece doğrulama yapılmış kullanıcılar tarafından yapılabilmesini sağlar.
        - **`.anyRequest().permitAll()`**: Diğer tüm isteklere erişimi serbest bırakır, yani doğrulama gerektirmez.

    - **`formLogin()`**: Form tabanlı giriş yapılandırmasını sağlar.
        - **`failureHandler()`**: Hatalı giriş durumunda özel bir mesaj döndürür. Yanıt olarak "KULLANICI ADI VEYA SIFRE HATALI" mesajı ve 401 Unauthorized hatası gönderilir.

    - **`httpBasic()`**: HTTP Basic Authentication'ı etkinleştirir. Kullanıcı adı ve şifre base64 formatında gönderilir.

---

### `UserConfig` Sınıfı

Bu sınıf, Spring Security için kullanıcı yapılandırmasını sağlar. Kullanıcıları bellek üzerinde tanımlar ve şifreleri güvenli bir şekilde saklar.

```java 
@Configuration
public class UserConfig {

    @Bean
    protected UserDetailsService userDetailsService() {
        // Kullanıcı adı ve şifreyi bellek üzerinden tanımlıyoruz. Spring Security ilk aşamada burayı kontrol edecek
        return new InMemoryUserDetailsManager(
                User.withUsername("testKullanici")
                        .password(passwordEncoder().encode("testSifre")) //
                        .roles("USER")
                        .build()
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

#### Anotasyonlar:
- **@Configuration**: Spring’e bu sınıfın bir yapılandırma sınıfı olduğunu belirtir.

#### Yapılandırmalar:
1. **`userDetailsService()` Metodu**:
    - Bu metod, kullanıcı bilgilerini bellek üzerinde saklar.
    - **InMemoryUserDetailsManager** kullanarak, kullanıcı adı ve şifreyi tanımlar.
    - Kullanıcı adı **"testKullanici"** ve şifre **"testSifre"** ile tanımlanır. Şifre, **BCryptPasswordEncoder** ile şifrelenir.
    - Kullanıcıya **"USER"** rolü atanır.

2. **`passwordEncoder()` Metodu**:
    - Şifreleri güvenli bir şekilde saklamak için **BCryptPasswordEncoder** kullanılır. Aynı zamanda bu metot User.withUsername("testKullanici") ile oluşturduğumuz kullanıcımızın şifresi encode etmek için kullanılır.


## Uygulama Görüntüleri

### Konsol Görüntüsü
![konsol](images/m1/console.PNG)

### Postman Görüntüleri

#### Get Request gorev1/merhaba
![alt text](images/m2/ba1.PNG)

#### Post Request gorev1/merhaba
![alt text](images/m2/ba2.PNG)

![alt text](images/m2/ba3.PNG)

![alt text](images/m2/ba4.PNG)

#### Login Ekranı Başarılı Giriş Get gorev1/merhaba
![alt text](images/m2/ba5.PNG)

![alt text](images/m2/ba6.PNG)

#### Login Ekranı Başarısız (Hatalı Kullanıcı) Giriş Get gorev1/merhaba
![alt text](images/m2/ba8.PNG)

![alt text](images/m2/ba7.PNG)


</details>

# Görev 3 : JWT ile Role Tabanlı Authentication

<details>
<summary><h3 style="display: inline-block">Detayları Görüntüle</h3></summary>

## Yapılacaklar:

- [x] Basic Authentication güvenlik konusunda yeterli değildir. Bu yüzden projeye sektörde de yaygın kullanılan JWT authentication eklenmeli.
- [x] PostgreSql veri tabanı kullanılarak uygulamaya veri tabanı bağlantısı sağlanmalı.
- [x] Örnek kayıtlar eklenmeli.

---

- [Dökümantasyon](#dökümantasyon-2)
    - [JWT (JSON Web Token) Nedir ve Yapısı Nasıldır ?](#jwt-json-web-token-nedir-ve-yapısı-nasıldır-)
    - [JWT (JSON Web Token) Nasıl Çalışır ?](#jwt-json-web-token-nasıl-çalışır-)
    - [Access Token ve Refresh Token Nedir?](#access-token-ve-refresh-token-nedir)
    - [Kod Açıklamaları](#kod-açıklamaları-2)
      - [Entity Package](#entity-package)
      - [Repository Package](#repository-package)
      - [Jwt Package](#jwt-package)
      - [Service Package](#service-package)
      - [Config Package](#config-package)
      - [Exception Package](#exception-package)
      - [Handler Package](#handler-package)
      - [Dto Package](#dto-package)
      - [Controller Package](#controller-package)
    - [Uygulama Resimleri](#uygulama-görüntüleri-2)
      - [Konsol Resimleri](#konsol-görüntüsü-2)
      - [Postman Resimleri](#postman-görüntüleri-2)
      - [Swagger Resimleri](#swagger-görüntüleri-1)

## Dökümantasyon

### JWT (JSON Web Token) Nedir ve Yapısı Nasıldır ?

Web uygulamalarında kimlik doğrulama ve veri güvenliği, kullanıcı deneyiminin en kritik noktalarından biridir. Bu bağlamda **JSON Web Token (JWT)**, modern uygulamalarda yaygın olarak kullanılan, kolay taşınabilir ve güvenilir bir yol tanımlayan açık standart (RFC 7519) bir kimlik doğrulama mekanizmasıdır. JWT, kullanıcının kimliğini doğrulamak veya güvenli bir şekilde bilgi alışverişi yapmak amacıyla tasarlanmış, standartlara uygun bir token yapısıdır.

#### JWT'nin Yapısı
JWT, üç ana bölümden oluşur ve bu bölümler birbirinden nokta (`.`) ile ayrılır. Her biri base64URL formatında kodlanmış olan bu bölümler şu şekilde özetlenebilir:

1. **Header (Başlık):**  
   Header, token'ın hangi algoritma ile imzalandığını (`alg`) ve türünü (`typ`) belirtir. Genellikle HS256 (HMAC-SHA256) gibi bir imzalama algoritması kullanılır.
   ```json
   {
     "alg": "HS256",
     "typ": "JWT"
   }
   ```  

2. **Payload (Yük):**  
   Payload, taşınan verileri içerir. Bu veriler, kullanıcı bilgileri (örneğin `id` veya `email`) ve token ile ilgili meta bilgilerden oluşur. Aynı zamanda bu bölüm, yetkilendirme sırasında kullanıcının sistemdeki rolünü belirlemek için de kullanılabilir.  
   Örnek Payload:
   ```json
   {
     "sub": "1234567890",
     "name": "John Doe",
     "role": "admin",
     "exp": 1700000000
   }
   ```  

3. **Signature (İmza):**  
   İmza, Header ve Payload kısmının birleştirilerek bir gizli anahtar ile imzalanmasıyla oluşturulur. Bu bölüm, token'ın bütünlüğünü korur ve değiştirilip değiştirilmediğini doğrular. İmza şu şekilde oluşturulur:
   ```
   HMACSHA256(
     base64UrlEncode(header) + "." + base64UrlEncode(payload),
     secret
   )
   ```  

Tam bir JWT şu şekilde görünür:
```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwicm9sZSI6ImFkbWluIiwiZXhwIjoxNzAwMDAwMDAwfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
```

![JWT](/images/m3/jwt.PNG)

#### JWT'nin Avantajları
JWT, **stateless** bir yapıya sahiptir; yani sunucuda oturum bilgisi saklamaya gerek kalmaz. Ayrıca veritabanına kayıt etmeye de gerek yoktur, tüm doğrulama ve veri yükü token'ın içinde taşınır. Bu, sistemin ölçeklenebilirliğini artırır ve sunucu yükünü azaltmayla birlikte uygulama içinde extra veritabanı sorguları atmaya da ihtiyaç bırakmaz. Ayrıca JWT, HTTP header, cookie veya URL parametresi gibi çeşitli taşıma yöntemleriyle kullanılabilir, bu da uygulamalar arasında entegrasyonu kolaylaştırır.

Token'ın imzalanmış olması, içeriğinin değiştirilmesini engeller. Eğer biri JWT'nin içeriğini değiştirirse, imza doğrulama aşamasında bu tespit edilir. `Ancak unutulmaması gereken nokta, JWT'nin şifrelenmiş değil yalnızca imzalanmış olduğudur. Bu yüzden hassas bilgiler JWT'nin Payload kısmında taşınmamalıdır.` Bunu en güzel görebileceğimiz yer [JWT.io](https://jwt.io). Bu adreste herhangi bir jwt'nin payload kısmının kolayca okunabileceğini görebiliriz.

#### Kullanım Alanları
JWT'nin en yaygın kullanım alanlarından biri **API kimlik doğrulaması**dır. Kullanıcı giriş yaptıktan sonra sunucu, bir JWT oluşturur ve bu token'ı istemciye gönderir. İstemci daha sonra bu token'ı API talepleri sırasında sunucuya ileterek kimliğini doğrulatır. Bunun yanı sıra JWT, kullanıcının rolüne göre yetkilendirme işlemleri veya uygulamalar arasında güvenli bilgi paylaşımı için de kullanılır.

#### Dikkat Edilmesi Gerekenler
JWT kullanırken, bazı güvenlik önlemlerine dikkat etmek gerekir. Öncelikle, token'ların kısa bir yaşam süresi (`exp`) olması önemlidir. Ayrıca, gizli anahtarların güvenliği ve hassas verilerin Payload içinde taşınmaması, token'ın kötüye kullanılmasını önlemek için temel gerekliliklerdir.


### JWT (JSON Web Token) Nasıl Çalışır ?

![JWT](/images/m3/jwt2.png)


JWT'nin çalışma mantığı, istemci (client) ve sunucu (server) arasında güvenilir ve doğrulanabilir bir bilgi alışverişi sağlamak üzerine kuruludur. Yukarıdaki resimde nasıl çalıştığı görsel olarak basitçe anlatılmaya çalışılmıştır. Şimdi ise adım adım açıklayalım;

---

#### 1. **Kullanıcı Giriş Yapar**
Kullanıcı, bir kullanıcı adı ve şifre gibi kimlik bilgilerini sunucuya gönderir. Örneğin, bir giriş formu doldurur ve sunucuya bir **POST isteği** yapar. Yani sisteme Login olur.

---

#### 2. **Sunucu JWT Oluşturur**

Sunucu, kullanıcının kimlik bilgilerini doğrulandıktan sonra, bir kitaplık veya modül kullanılarak aşağıdaki adımlarla oluşturulur:

- Header (Başlık): İmza algoritması ve token türü belirtilir (örneğin, `HS256`).
- Payload (Yük): Kullanıcıya ait bilgiler (örneğin: `userID`, `role`), token’ın süresi (`exp`) gibi meta bilgiler tanımlanır.
- İmza (Signature): `Header` ve `Payload`, bir `gizli anahtar` kullanılarak belirlenen algoritmayla imzalanır.

Örnek bir Payload:

```json
{
  "userID": "12345",
  "role": "admin",
  "exp": 1700000000
}
```  

JWT, sunucuda saklanmaz; tüm bilgi token içinde taşınır ve istemciye gönderilir.

---

#### 3. **Token İstemciye Gönderilir**
Oluşturulan JWT, istemciye bir HTTP yanıtı içinde gönderilir. İstemci bu token’ı genellikle:
- **Tarayıcıdaki localStorage** veya **sessionStorage** içinde, veya **cookie** olarak saklar.

---

#### 4. **Token ile Talep Gönderilir**
İstemci, daha sonraki API isteklerinde bu token’ı sunucuya gönderir. Genellikle HTTP header’da şu şekilde iletilir:
```
Authorization: Bearer <token>
```  

Bu adımda, istemci artık tekrar giriş yapmak zorunda kalmaz. Sunucu, gelen isteklerin yetkilendirilmesini bu token aracılığıyla yapar.

---

#### 5. **Sunucu Token’ı Doğrular**
Sunucu, gelen JWT'yi şu adımlarla doğrular:
1. Token’ın Header ve Payload kısmını alır.
2. İmzanın sunucudaki gizli anahtar ile oluşturulup oluşturulmadığını kontrol eder.
3. Token’ın süresinin geçip geçmediğini (`exp`) kontrol eder.

Eğer doğrulama başarılıysa, istemcinin talebi işlenir ve gerekli veriler döndürülür. Başarısız doğrulama durumunda (örneğin: süre dolmuş veya token değiştirilmiş), sunucu isteği reddeder.

---

#### 6. **Erişim Sağlanır veya Reddedilir**

Eğer token geçerli ve doğrulanmışsa, sunucu istemcinin kimliğini kabul eder. İstemcinin sahip olduğu rol (`role`) veya izinler (`permissions`) kontrol edilerek erişim sağlanır. `Ama bu role veya permissions kullanıcıdan gelen token üzerindeki payloaddan değil doğrulanan token için veritabanından alınan kullanıcı bilgisine göre yapılır.`

Örneğin:
- `admin` rolüne sahip bir kullanıcı, yönetici paneline erişebilir.
- `user` rolüne sahip bir kullanıcı yalnızca kendi verilerini görebilir.

---

#### JWT'nin Çalışma Sürecinin Avantajları
- **Stateless (Durumsuz):** Sunucu tarafında oturum bilgisi saklamaz. Bu, sistemi daha ölçeklenebilir hale getirir.
- **Hızlı Doğrulama:** Sunucu, yalnızca token’ın imzasını ve süresini kontrol ederek doğrulama yapabilir.
- **Taşınabilirlik:** JWT, HTTP header, cookie veya URL parametresi ile taşınabilir.

---

### Access Token ve Refresh Token Nedir?

**Access Token** ve **Refresh Token**, kimlik doğrulama ve yetkilendirme süreçlerinde kullanılan iki farklı bileşendir. İkisi de güvenli bir şekilde istemci (örneğin, tarayıcı veya mobil uygulama) ile sunucu arasında bilgi alışverişi yapmaya yarar, ancak görev ve kullanım amaçları farklıdır.

---

#### **Access Token Nedir?**
Access Token, bir kullanıcının belirli bir süre boyunca sunucudaki belirli kaynaklara erişmesini sağlayan kısa ömürlü bir kimlik doğrulama belirtecidir.

- **Ne için kullanılır?**  
  Kullanıcının kimliği ve yetkileri doğrulanmışsa, sunucuya API talepleri yapmak için kullanılır.

- **Özellikleri:**
    - Süresi kısıtlıdır (örneğin, 15 dakika).
    - Doğrudan yetkilendirme taleplerinde taşınır.
    - Çalınması durumunda kısa sürede geçersiz hale gelir.

- **Kullanım Örneği:**  
  Bir kullanıcı uygulamada bir veri çekmek istediğinde, `Authorization` başlığıyla Access Token gönderilir:
  ```http
  Authorization: Bearer <access_token>
  ```
---

#### Refresh Token Nedir ve Nasıl Çalışır?

**Refresh Token**, Access Token'ın yani üretilip client'a gönderilen JWT'miz ile etle tırnak gibi ayrılmaz bir bütün gibidir. Kullanıcı oturumunun süresini uzatmak için kullanılan, uzun ömürlü bir kimlik doğrulama bileşenidir. Access Token (erişim belirteci) kısa süreli ve hızlıca geçersiz hale gelirken, Refresh Token daha uzun süre saklanır ve yeni bir Access Token almak için kullanılır. `Ama bu jwt nin tek başına kullanılamadığı manasına gelmez refresh token'ın amacı kullanıcı deneyimini iyileştirmektir token süresi geçince yeni token almaya yarar.`

#### Ne İşe Yarar?

- Access Token’ın süresi dolduğunda, kullanıcıyı yeniden giriş yapmaya zorlamadan yeni bir Access Token almayı sağlar.
- Güvenliği artırır; Access Token’lar kısa ömürlü olduğundan, çalınma riskine karşı etkili bir çözüm sunar.

#### Çalışma Mantığı

1. **Oturum Açma:** Kullanıcı giriş yaptığında hem Access Token hem de Refresh Token oluşturulur.
2. **Token Saklama:** Access Token istemcide (örneğin, tarayıcı belleğinde), Refresh Token ise genellikle daha güvenli bir yerde (örneğin, HttpOnly cookie) saklanır.
3. **Token Yenileme:** Access Token’ın süresi dolduğunda, istemci Refresh Token’ı sunucuya gönderir.
4. **Doğrulama ve Yeniden Oluşturma:** Sunucu, Refresh Token’ı doğrular ve geçerliyse yeni bir Access Token üretir.

- **Kullanım Örneği:**  
  Access Token’ın süresi dolduğunda istemci Refresh Token ile yeni bir Access Token talep eder:
  ```http
  POST /token/refresh
  Body: { "refresh_token": "<refresh_token>" }
  ```  

Bu süreç, hem kullanıcı deneyimini iyileştirir hem de güvenli bir kimlik doğrulama mekanizması sağlar.

---

#### **İkisinin Birlikte Çalışması**

1. **Oturum Başlatma:** Kullanıcı giriş yaptığında sunucu bir **Access Token** ve bir **Refresh Token** oluşturur.
2. **Token Kullanımı:**
    - İstemci, API taleplerinde Access Token’ı kullanır.
    - Access Token süresi dolarsa, Refresh Token ile yeni bir Access Token alınır.
3. **Güvenlik:** Refresh Token’ın kullanımı, Access Token çalınsa bile güvenliği artırır çünkü Access Token kısa sürede geçersiz hale gelir.

---

### Kod Açıklamaları

#### **`entity`** package

#### [`User Entity` Görüntüle](src/main/java/com/vehbiozcan/hello_turksat/entity/User.java)


User sınıfı bizim kullanıcı yapımızı tanımlayan entitimizdir, `UserDetails` arayüzünü implement eder. `UserDetails`, Spring Security'nin kullanıcı bilgilerini alıp doğrulama ve yetkilendirme işlemleri için kullandığı bir arayüzdür. Bu sayede, kullanıcı bilgileri güvenlik işlemlerine dahil edilir.

#### `getAuthorities()`
Kullanıcının rolünü bir liste şeklinde döndürür ve Spring Security'nin yetkilendirme mekanizmasında kullanılmak üzere `GrantedAuthority` nesnesi olarak iletilir.

#### `isAccountNonExpired()`, `isAccountNonLocked()`, `isCredentialsNonExpired()`, `isEnabled()`
Bu metodlar, kullanıcı hesabının durumunu kontrol eder. Başlangıçta hepsini `true` döndürür, yani kullanıcı aktif, kilitlenmemiş ve geçerli kabul edilir.

User enity sınıfımız, Spring Security ile kimlik doğrulama (authentication) ve yetkilendirme (authorization) işlemlerinde kullanılır. `UserDetails` arayüzü sayesinde, Spring Security kullanıcı verilerini alır ve güvenlik mekanizmalarıyla işler.

#### [`RootEntity Entity` Görüntüle](src/main/java/com/vehbiozcan/hello_turksat/entity/RootEntity.java)

Bu sınıf, API'lerden dönecek sonuçları daha ayrıntılı bir şekilde yönetir ve sonucu standardize etmemize yarayacak yapıyı oluşturur. `success`, `status`, `statusCode` ve `data` alanları, API yanıtlarının başarı durumu, HTTP durumu ve veri ile birlikte döndürülmesini sağlar. data kısmı da her geriye dönecek data farklı olabileceği için dinamik bir şekilde verimizi tutmaya yarar bu da bize esneklik sağlar.

#### `ok()` Metodu:
Bu metod, işlem başarılı olduğunda ve veri mevcut olduğunda kullanılır. `success` true, `status` ve `statusCode` HTTP OK (`200`) olarak belirlenir ve `data` parametre olarak sağlanan veri ile döndürülür.

#### `ok()` Metodu (Overload):
Bu metod, veri ve HTTP durumu belirleyerek API yanıtını döndürür. Eğer `status` parametresi sağlanmazsa, varsayılan olarak HTTP OK (`200`) durumu kullanılır. Bu metod, özelleştirilmiş HTTP durum kodları ile dönen yanıtları yönetmek için kullanılır.

#### `error()` Metodu:
Bu metod, hata durumunda çalışır ve `success` false, `status` ve `statusCode` sağlanan hata durumuna göre belirlenir. `data` alanı, hata verisiyle doldurulur.

RootEntity sınıfı, API yanıtlarını daha esnek bir şekilde yapılandırmak için kullanılır. Yanıtlar, başarı durumuna göre HTTP durum kodlarıyla birlikte döndürülür.

#### [`RefreshToken Entity` Görüntüle](src/main/java/com/vehbiozcan/hello_turksat/entity/RefreshToken.java)

 `refresh_token` tablosunu temsil eder ve bir kullanıcının refresh token'larını veritabanında saklamak için kullanılır.

#### `RefreshToken` Sınıfı:
- **`id`**: Refresh token'ın benzersiz kimliğini tutar.
- **`refreshToken`**: Kullanıcıya ait refresh token'ı tutar.
- **`expireDate`**: Refresh token'ın süresinin dolacağı tarihi belirtir.
- **`user`**: Refresh token ile ilişkilendirilen kullanıcıyı tutar. Bu, `User` sınıfına bir `ManyToOne` ilişki ile bağlanır.

#### [`Role Enum` Görüntüle](src/main/java/com/vehbiozcan/hello_turksat/entity/Role.java)

Kullanıcı rollerini tanımlar ve iki rol içerir: `USER` ve `ADMIN`.

#### `Role` Enum:
- **`USER`**: Normal kullanıcı rolünü temsil eder.
- **`ADMIN`**: Yönetici rolünü temsil eder.

Role enumu, sistemdeki kullanıcıların rollerini belirlemek ve yetkilendirme işlemleri için kullanılır. Spring Security de belli endpointlere belli kullanıcıların erişmesi için kullanıcıların erişim seviyelerini yönetmek amacıyla bu roller kullanılmaktadır.

---

#### **`repository` package**

#### [`User Repository` Görüntüle](src/main/java/com/vehbiozcan/hello_turksat/repository/UserRepository.java)

Bu sınıf, `User` nesneleriyle etkileşim kurmak için kullanılan bir repository'dir ve Spring Data JPA'nın `JpaRepository` sınıfından extend edilir. `UserRepository`, kullanıcı verilerini yönetmek için veritabanı işlemleri sağlar.

#### `findByUsername()` Metodu:
Verilen `username` ile eşleşen bir `User` nesnesini veritabanından arar. Eğer bulunursa, kullanıcıyı `Optional<User>` içinde döndürür. `Optional`, değer olup olmadığını kontrol etmek için kullanılır ve null değerle karşılaşmamak için güvenli bir şekilde veri işleme imkanı sunar. Bu metod UserDetailService'in loadByUsername metodunu override ederken kullanılır.



#### [`RefreshToken Repository` Görüntüle](src/main/java/com/vehbiozcan/hello_turksat/repository/RefreshTokenRepository.java)

Bu sınıf, `RefreshToken` nesneleriyle etkileşim kurmak için kullanılan bir repository'dir.

#### `findByRefreshToken()` Metodu:
Verilen `refreshToken` ile eşleşen bir `RefreshToken` nesnesini veritabanından arar. Eğer bulunursa, ilgili `RefreshToken` nesnesi `Optional<RefreshToken>` içinde döndürülür. `Optional`, null değeri kontrol etmek için kullanılır ve veritabanında eşleşen bir token olup olmadığını güvenli bir şekilde kontrol eder.

---

#### **`jwt` package**

#### [`JwtServiceImpl` Görüntüle](src/main/java/com/vehbiozcan/hello_turksat/jwt/impl/JwtServiceImpl.java)

Bu sınıf, `JwtService`'in implementasyonu olup, JWT token'ları oluşturma ve doğrulama işlemleri için gerekli metotları sağlar.

#### `JwtServiceImpl` Sınıfı:
- **`secretKey`**: `application.properties` dosyasından alınan gizli anahtar, JWT token'larını imzalamak için kullanılır.
- **`expirationTime`**: `application.properties` dosyasından alınan geçerlilik süresi.

#### Yöntemler:
1. **`generateToken(UserDetails userDetails)`**:
    - `UserDetails` nesnesi alır ve bir JWT token'ı üretir.
    - Kullanıcı adı (`username`) ve yetkiler (`authorities`) claim olarak eklenir.
    - Token oluşturulurken, oluşturulma tarihi ve sona erme tarihi de eklenir.

2. **`getClaimsFromToken(String token)`**:
    - Token'dan JWT claims bilgilerini alır.
    - `Jwts.parserBuilder()` ile token'ı çözerek claims'leri çıkarır.

3. **`exportToken(String token, Function<Claims, T> claimsResolver)`**:
    - Token'dan istenilen claim değerini döndürür.
    - Genel olarak, token'dan veri almak için kullanılan bir yardımcı metottur.

4. **`getUsernameByToken(String token)`**:
    - Token'dan kullanıcı adını alır.
    - `exportToken()` metodunu kullanarak subject (kullanıcı adı) bilgisini döndürür.

5. **`isTokenExpired(String token)`**:
    - Token'ın süresinin dolup dolmadığını kontrol eder.
    - Eğer token'ın son kullanma tarihi geçmişse, `true` döner.

6. **`isTokenValid(String token, String username)`**:
    - Token'ı verilen kullanıcı adıyla karşılaştırarak doğrular.
    - Ayrıca token'ın geçerliliği de kontrol edilir (expire olmamış olmalı).

7. **`getKey()`**:
    - `secretKey`'i base64 formatından decode eder ve `Keys.hmacShaKeyFor()` metodu ile JWT token'larını imzalarken kullanılacak `Key` nesnesini döndürür.

#### Kullanım Amacı:
Bu sınıf, JWT token oluşturma ve doğrulama işlemlerini gerçekleştirmek için kullanılmaktadır. Token üzerinden kullanıcı bilgilerini almak, token geçerliliğini kontrol etmek ve yeni token oluşturmak gibi işlemler için gereklidir. Bu yapı, uygulamanın kimlik doğrulama ve yetkilendirme işlevlerini düzgün şekilde yürütmesini sağlar.

#### [`RefreshTokenServiceImpl` Görüntüle](src/main/java/com/vehbiozcan/hello_turksat/jwt/impl/RefreshTokenServiceImpl.java)

RefreshTokenServiceImpl sınıfı, refresh token'ları oluşturmak, saklamak, doğrulamak ve süresinin dolup dolmadığını kontrol etmek için kullanılan iş mantığını içerir. JWT token'ları ile ilişkilendirilen refresh token işlemleri için gereklidir.

#### Metotlar:
1. **`createRefreshToken(User user)`**:
    - Bir `User` nesnesi alır ve ona ait yeni bir `RefreshToken` nesnesi oluşturur.
    - `refreshToken` UUID ile rastgele bir değerle oluşturulur.
    - `expireDate` olarak, geçerlilik süresi (`refreshExpirationTime`) eklenir.
    - Kullanıcı (`user`) bu token ile ilişkilendirilir.

2. **`saveRefreshToken(RefreshToken refreshToken)`**:
    - Verilen `RefreshToken` nesnesini veritabanına kaydeder.
    - `refreshTokenRepository.save()` metodunu kullanarak token'ı kalıcı hale getirir.

3. **`findByRefreshToken(String refreshToken)`**:
    - Verilen refresh token'ı veritabanında arar.
    - Eğer token varsa, `RefreshToken` nesnesi döndürülür; aksi takdirde `null` döner.

4. **`isExpireRefreshToken(RefreshToken refreshToken)`**:
    - Verilen refresh token'ın süresinin dolup dolmadığını kontrol eder.
    - Token'ın geçerlilik süresi geçmişse `true`, hala geçerliyse `false` döner.

#### Kullanım Amacı:
Bu sınıf, kullanıcıların kimlik doğrulama işlemi için kullanılan refresh token'ların yönetimini sağlar. Token'ın oluşturulması, saklanması ve süresinin kontrol edilmesi gibi işlemlerle ilgilenir. JWT token'larının süresi dolduğunda, bir refresh token kullanılarak yeni bir token alınabilmesi için gerekli altyapıyı oluşturur. Bu, kullanıcıların sürekli oturum açmalarını sağlamadan güvenli bir şekilde sistemde kalmalarını sağlar.

#### [`JwtAuthenticationFilter` Görüntüle](src/main/java/com/vehbiozcan/hello_turksat/jwt/impl/JwtAuthenticationFilter.java)

Bu sınıf, gelen HTTP isteklerinde JWT token'ının doğruluğunu kontrol eden bir filtre olarak çalışır. Spring Security'deki `OncePerRequestFilter` sınıfından türemektedir ve her istek için bir kez çalışarak token doğrulaması yapar. Eğer token geçerli ise, kullanıcı doğrulaması yapılır ve güvenlik bağlamı (`SecurityContext`) oluşturulur.

#### Metotlar:
1. **`doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)`**:
    - **Amaç**: HTTP isteklerinde token doğrulaması yaparak güvenlik bağlamı oluşturur.
    - Adımlar:
        1. İstekten token alınır (`getTokenFromRequest`).
        2. Eğer token varsa:
            - `JwtService` kullanılarak token'dan kullanıcı adı (`username`) çıkarılır.
            - Kullanıcı adı boş değilse ve güvenlik bağlamı (`SecurityContextHolder`) henüz oluşturulmamışsa:
                - `UserDetailsService` kullanılarak kullanıcı bilgileri yüklenir.
                - Eğer kullanıcı bilgisi varsa ve token geçerli ise:
                    - `UsernamePasswordAuthenticationToken` ile kimlik doğrulaması yapılır.
                    - Kimlik doğrulama bilgileri (`authorities`) ve detaylar (`WebAuthenticationDetailsSource`) eklenir.
                    - Güvenlik bağlamı (`SecurityContextHolder`) güncellenir.

2. **`getTokenFromRequest(HttpServletRequest request)`**:
    - **Amaç**: HTTP isteğinden token'ı almak.
    - Adımlar:
        - `Authorization` başlığından `Bearer ` ön ekiyle başlayan token'ı alır ve geri döner.
        - Eğer `Bearer` ön eki eksikse, `null` döner.

#### Kullanım Amacı:
Bu filtre, her gelen HTTP isteğinde token doğrulaması yapar. Eğer token geçerliyse, kullanıcının kimlik bilgileri yüklenir ve güvenlik bağlamı oluşturulur. Bu sayede, sadece geçerli token ile yapılmış istekler güvenli bir şekilde işlenebilir. Bu işlem, Spring Security'nin kimlik doğrulama ve yetkilendirme mekanizmalarını etkin bir şekilde çalıştırmasına yardımcı olur.

---

#### **`service` package**

#### [`AuthServiceImpl` Görüntüle](src/main/java/com/vehbiozcan/hello_turksat/service/impl/AuthServiceImpl.java)

Bu sınıf, kimlik doğrulama ve kullanıcı kaydı işlemlerini yönetmek için tasarlanmış bir servis sınıfıdır. Kullanıcı kaydı, oturum açma ve yenileme token işlemleri gibi temel işlevleri sağlar. Aşağıda her bir yöntemin amacı ve işleyişi açıklanmıştır:


#### Amaç:
Kullanıcıların güvenli bir şekilde kaydolmasını, kimlik doğrulaması yapmasını ve oturumlarını yenilemesini sağlar.

#### Metotlar:

#### **`register(AuthRequest authRequest)`**
- **Amaç**: Yeni bir kullanıcıyı sisteme kaydeder.
- **İşleyiş**:
    1. Kullanıcı adı kontrol edilir. Eğer zaten mevcutsa, bir `BaseException` fırlatılır.
    2. Parola, `BCryptPasswordEncoder` ile şifrelenir.
    3. Kullanıcı nesnesi oluşturulup veritabanına kaydedilir.
    4. Kullanıcı bilgileri bir `DtoUser` nesnesine dönüştürülür ve geri döner.



#### **`authenticate(AuthRequest authRequest)`**
- **Amaç**: Kullanıcının kimlik doğrulamasını gerçekleştirir.
- **İşleyiş**:
    1. Kullanıcı adı ve şifre ile bir `UsernamePasswordAuthenticationToken` oluşturulur.
    2. `AuthenticationProvider` aracılığıyla kimlik doğrulaması yapılır.
    3. Kullanıcı veritabanında aranır ve bulunursa JWT erişim token'ı oluşturulur.
    4. Yenileme token'ı (`RefreshToken`) oluşturulur ve veritabanına kaydedilir.
    5. Erişim token'ı ve yenileme token'ı `AuthResponse` ile geri döner.



#### **`refreshToken(RefreshTokenRequest refreshTokenRequest)`**
- **Amaç**: Geçerli bir yenileme token kullanarak yeni bir erişim token oluşturur.
- **İşleyiş**:
    1. Yenileme token kontrol edilir. Boşsa bir hata fırlatılır.
    2. Yenileme token veritabanında aranır.
    3. Eğer token geçersiz veya süresi dolmuşsa, uygun hata mesajı ile `BaseException` fırlatılır.
    4. Kullanıcı bilgilerine dayanarak yeni bir JWT erişim token oluşturulur.
    5. Yeni bir yenileme token oluşturulup veritabanına kaydedilir.
    6. Yeni erişim ve yenileme token'ları bir `AuthResponse` ile döndürülür.

    
#### Kullanılan Bileşenler (Autowired Enjeksiyonları ve Exception):
1. **`BCryptPasswordEncoder`**: Parolaların güvenli bir şekilde şifrelenmesini sağlar.
2. **`AuthenticationProvider`**: Kullanıcı kimlik doğrulamasını gerçekleştirir.
3. **`JwtService`**: Token oluşturma ve doğrulama işlemlerini yapar.
4. **`RefreshTokenService`**: Yenileme token'larını yönetir (oluşturma, kontrol ve kaydetme).
5. **Exception Yönetimi**: Özel hata mesajları ve istisna yönetimi sağlanır (`BaseException`).


#### Kullanım Amacı:
Bu sınıf, uygulamada kullanıcıların oturum açma, kaydolma ve token yenileme gibi işlemlerini ele alır. Güvenlik ve kullanıcı doğrulama gereksinimlerini karşılamak için JWT tabanlı bir çözüm sunar. Uygulama içinde merkezi bir kimlik doğrulama servisi olarak çalışır.

---

#### **`config` package**

#### [`SecurityConfig` Görüntüle](src/main/java/com/vehbiozcan/hello_turksat/config/SecurityConfig.java)

 Spring Security yapılandırmasını içeren ve uygulamanın güvenlik politikalarını belirleyen bir konfigürasyon sınıfıdır. REST API üzerinde kullanıcı kimlik doğrulama ve yetkilendirme işlemleri için gerekli olan filtreler, izinler ve kurallar bu sınıf aracılığıyla tanımlanır.


#### Alanlar ve Sabitler:
- **`AUTHENTICATE`, `REGISTER`, `REFRESH_TOKEN`:**  
  Kimlik doğrulama ve kullanıcı kaydı için açık olan uç noktaları temsil eder.
- **`SWAGGER_WHITELIST`:**  
  Swagger dokümantasyonu için izin verilen uç noktaların listesidir.
- **`ADMIN`, `USER`:**  
  Yöneticiler ve kullanıcılar için özelleştirilmiş uç noktaları temsil eder.


#### Bağımlılıklar:
- **`AuthenticationProvider`:** Kimlik doğrulama işlemlerini destekler.
- **`JwtAuthenticationFilter`:** JWT tabanlı kimlik doğrulama filtresi.
- **`AuthEntryPoint`:** Yetkilendirme hataları durumunda özelleştirilmiş yanıtlar döndürür.

#### Metot:

#### **`securityFilterChain(HttpSecurity http)`**
- **Amaç:** Güvenlik yapılandırmasını tanımlar ve `SecurityFilterChain` nesnesi döner.
- **İşleyiş:**
    1. **CSRF Koruması:** Devre dışı bırakılır (stateless yapı için gerekli).
    2. **İzinler:**
        - Kimlik doğrulama, kayıt ve refresh token uç noktaları tüm kullanıcılara açıktır.
        - Swagger ile ilgili uç noktalar herkese izinlidir.
        - `ADMIN` uç noktalarına sadece yöneticiler erişebilir.
        - `USER` uç noktalarına kullanıcılar ve yöneticiler erişebilir.
    3. **Kimlik Doğrulama ve Yetkilendirme:**
        - `JwtAuthenticationFilter`, `UsernamePasswordAuthenticationFilter` filtresinden önce eklenir.
        - Oturum yönetimi stateless olarak yapılandırılır.
        - `AuthEntryPoint`, yetkilendirme hatalarını yönetir.
    4. **Filtre Zinciri:** Tanımlanan yapılandırma döndürülür.

    
#### Kullanım Amacı:
Bu sınıf, Spring Security kullanarak uygulamada oturum yönetimini ve kaynak bazlı erişim kontrolünü sağlar. JWT tabanlı kimlik doğrulama mekanizmasını destekler ve REST API'yi güvenli hale getirir. Özellikle stateless yapı (JWT) ile çalışırken, kullanıcıların güvenli bir şekilde doğrulanmasını ve yetkilendirilmesini sağlar.

---

#### [`AppConfig` Görüntüle](src/main/java/com/vehbiozcan/hello_turksat/config/AppConfig.java)


Bu sınıf, Spring Security'nin kimlik doğrulama mekanizmasını desteklemek için gerekli bileşenleri sağlar. Kullanıcı doğrulama işlemleri, şifreleme ve kimlik doğrulama sağlayıcıları gibi temel yapı taşlarını içerir.


#### Metotlar:

#### **`userDetailsService()`**

- Spring Security'nin kullanıcı doğrulama işlemi sırasında kullanıcı bilgilerini yüklemek için bir `UserDetailsService` sağlar.
- **İşleyiş:**
    - Kullanıcı adı ile `userRepository` aracılığıyla kullanıcı bilgilerini arar.
    - Kullanıcı bulunursa, `UserDetails` döner.
    - Kullanıcı yoksa, `null` döner.
  Bu yöntem, kimlik doğrulama sırasında kullanıcı bilgilerini doğrulamak için kullanılır.

      
#### **`authenticationProvider()`**

- Kimlik doğrulama işlemi için bir `AuthenticationProvider` tanımlar.
- **İşleyiş:**
    - `DaoAuthenticationProvider` kullanır.
    - Kullanıcı doğrulama için `userDetailsService()` atanır.
    - Şifre doğrulama için `passwordEncoder()` atanır.
    Bu sayede kullanıcı şifrelerinin ve kullanıcı bilgilerinin doğrulanmasını sağlar.

      
#### **`authenticationManager(AuthenticationConfiguration)`**

- Spring Security'nin `AuthenticationManager` yapılandırmasını sağlar.
- **İşleyiş:**
  - Sağlanan `AuthenticationConfiguration` ile uyumlu bir `AuthenticationManager` döner.
  Uygulama genelinde kimlik doğrulama işlemlerini yönetmek için gereklidir.


#### **`passwordEncoder()`**

- Kullanıcı şifrelerini güvenli bir şekilde şifrelemek için bir `BCryptPasswordEncoder` sağlar.
- **İşleyiş:**
    - Şifreleri `BCrypt` algoritmasıyla şifreler ve doğrular. 
  Parolaların güvenliğini sağlamak için standart bir şifreleme yöntemidir.



#### Genel Kullanım:
Bu sınıf, kullanıcı doğrulama sürecinde ihtiyaç duyulan anahtar bileşenleri sağlar.
- `userDetailsService()`, kullanıcı bilgilerini yükler ve doğrular.
- `authenticationProvider()`, kullanıcı doğrulama sürecini destekler.
- `passwordEncoder()`, şifreleme ve doğrulama mekanizmasını sağlar.


#### [`OpenAPIConfig` Görüntüle](src/main/java/com/vehbiozcan/hello_turksat/config/OpenAPIConfig.java)

### OpenAPIConfig Sınıfı

Bu sınıf, Spring Boot uygulaması için OpenAPI (Swagger) yapılandırmasını içerir. API belgelerini otomatik olarak oluşturmak ve güvenlik şemalarını entegre etmek için kullanılır. JWT Authorization için Swagger UI üzerine gerekli komponentleri entegre eder.


#### Metotlar:

#### **`customOpenAPI()`**
 
  OpenAPI (Swagger) belgelerinin özelliklerini özelleştirmeye ve güvenlik yapılandırmasını eklemeye yarar.
- **İşleyiş:**
    - **API Bilgileri:**
        - `title`: API başlığı olarak `"Hello Türksat Rest API"` belirlenmiş.
        - `version`: `"1.0.0"` olarak API sürümü belirtilmiş.
        - `description`: API'nin açıklaması `"Hello Türksat Rest API"` şeklinde tanımlanmış.
    - **Güvenlik:**
        - `SecurityRequirement` ile API güvenlik şeması (`bearerAuth`) eklenir.
        - `SecurityScheme`: Bearer token ile doğrulama yapılacak şekilde HTTP tipi bir güvenlik şeması tanımlar. `JWT` biçiminde bir erişim token'ı bekler ve açıklama sağlar.

- **Özellikler:**
    - OpenAPI belgelerine erişim için Swagger UI gibi araçlarla uyumludur.
    - Güvenli bir yapı sunarak, JWT kullanımı ile doğrulama sürecini destekler.


#### Genel Kullanım:
Bu yapılandırma sayesinde:
1. API belgeleri dinamik olarak oluşturulur.
2. Swagger UI üzerinden API kolayca test edebilir.
3. Bearer token kullanımı gerektiren güvenlik yapısı desteklenir ve belgede belirtilir.

Spring uygulamalarında REST API'leri belgelemek ve doğrulama süreçlerini standartlaştırmak için kullanışlı bir çözüm sunar.

---

#### **`exception` package**

#### [`MessageType Enum` Görüntüle](src/main/java/com/vehbiozcan/hello_turksat/exception/MessageType.java)

Bu sınıf, uygulama genelinde kullanılacak hata mesajlarını ve hata kodlarını temsil eden bir enum yapısıdır. Her bir hata türü, önceden tanımlanmış bir mesaj, kod ve HTTP durum koduyla ilişkilendirilmiştir. Bu yapı, hata mesajlarının merkezi bir yerde tanımlanarak tutarlı bir şekilde kullanılmasını sağlar.

**Amaç ve Kullanım:**
- Uygulama boyunca meydana gelebilecek farklı hata senaryolarını yönetmek.
- Kullanıcı dostu hata mesajları sağlayarak uygulama ile etkileşimi kolaylaştırmak.
- Merkezi bir hata yönetimi mekanizması oluşturarak kod tekrarını azaltmak.

**Yapı:**
1. **Özellikler:**
    - `code`: Hata türüne özgü benzersiz bir kod.
    - `title`: Hatanın kısa açıklaması.
    - `message`: Kullanıcıya gösterilecek detaylı hata mesajı.
    - `status`: Hata için uygun HTTP durum kodu.

    
2. **Enum Öğeleri:**
    - Genel hatalar (ör. `UNAUTHORIZED`, `FORBIDDEN`, `NOT_FOUND`).
    - JWT ile ilgili hatalar (ör. `JWT_EXPIRED`, `INVALID_JWT`).
    - Doğrulama hataları (ör. `VALIDATION_ERROR`, `FIELD_REQUIRED`).
    - Kullanıcı hataları (ör. `USER_NOT_FOUND`, `USER_ALREADY_EXISTS`).
    - Refresh token hataları (ör. `REFRESH_TOKEN_INVALID`, `REFRESH_TOKEN_EXPIRED`).
    - Rol ile ilgili hatalar (ör. `INSUFFICIENT_ROLE`).


3. **Hata Mesajı ve HTTP Status Karşılıkları Tablosu**


| **HTTP Status Kodu** | **HTTP Mesajı**          | **MessageType Enum Code** | **MessageType Enum Title**             |
|----------------------|--------------------------|---------------------------|----------------------------------------|
| 400                  | Bad Request              | 400                       | Kötü İstek                             |
| 401                  | Unauthorized             | 401                       | Yetkisiz Erişim                        |
| 403                  | Forbidden                | 403                       | Yasaklı Erişim                         |
| 404                  | Not Found                | 404                       | Kaynak Bulunamadı                      |
| 500                  | Internal Server Error    | 500                       | İç Sunucu Hatası                       |
| 400                  | Bad Request              | 400                       | Kötü İstek                             |
| 401                  | Unauthorized             | 1001                      | JWT Süresi Dolmuş                      |
| 400                  | Bad Request              | 1002                      | Geçersiz JWT                           |
| 400                  | Bad Request              | 1003                      | Eksik JWT                              |
| 400                  | Bad Request              | 1004                      | Geçersiz JWT İmzası                    |
| 400                  | Bad Request              | 2001                      | Doğrulama Hatası                       |
| 400                  | Bad Request              | 2004                      | Alan Zorunlu                           |
| 400                  | Bad Request              | 2005                      | Şifre Çok Kısa                         |
| 400                  | Bad Request              | 2006                      | Şifre Uyuşmazlığı                      |
| 400                  | Bad Request              | 2007                      | Şifre Yanlış                           |
| 404                  | Not Found                | 3001                      | Kullanıcı Bulunamadı                   |
| 409                  | Conflict                 | 3002                      | Kullanıcı Zaten Var                    |
| 400                  | Bad Request              | 4001                      | Geçersiz Refresh Token                 |
| 400                  | Bad Request              | 4002                      | Süresi Dolmuş Refresh Token            |
| 404                  | Not Found                | 4003                      | Refresh Token Bulunamadı               |
| 400                  | Bad Request              | 4004                      | Refresh Token Değeri Boş               |
| 403                  | Forbidden                | 5001                      | Yetersiz Rol                           |


**Kullanım Amacı:**
- Hata kodlarını ve mesajlarını bir yerde toplamak, kodun bakımı ve genişletilebilirliğini kolaylaştırır.
- API cevaplarında hataların anlamlı bir şekilde iletilmesini sağlar. Örneğin, bir kullanıcı doğrulama hatasında bu enumdan uygun hata türü seçilerek kullanıcıya bilgi verilir.


#### [`ErrorMessage` Görüntüle](src/main/java/com/vehbiozcan/hello_turksat/exception/ErrorMessage.java)

`ErrorMessage` sınıfı, hata mesajlarını düzenlemek için kullanılan bir yardımcı sınıftır. Bu sınıfın temel işlevi, `MessageType` enum'undan alınan hata bilgilerini derlemek ve opsiyonel olarak ek bilgi (`extraInfo`) ekleyerek bir hata mesajı oluşturmaktır.

#### Yapısı ve İşlevi:
- **messageType:** `MessageType` enum türünde bir değişken, her hata için gerekli olan hata kodu, başlık, mesaj ve HTTP durum kodu gibi bilgileri içerir.
- **extraInfo:** Bu, hata mesajına eklenebilecek opsiyonel bir ek bilgi alanıdır (örneğin, daha fazla açıklama ya da detaylar).
- **prepareErrorMessage():** Bu metot, `messageType`'dan gelen bilgileri ve opsiyonel `extraInfo` bilgisini alarak bir hata mesajı oluşturur.

#### Kullanım Amacı:
- Bu sınıf, hata mesajlarını standart bir formatta döndüren ve gerektiğinde ek bilgi eklemeyi sağlayan bir yardımcı sınıf olarak kullanılır.
- `prepareErrorMessage()` metodu, tüm hata bilgilerini birleştirir ve sistemin hata yönetiminde kullanılabilir.

Bu sınıf, hata mesajlarını düzgün ve tutarlı bir şekilde formatlamak için faydalıdır.


#### [`BaseException` Görüntüle](src/main/java/com/vehbiozcan/hello_turksat/exception/BaseException.java)

`BaseException` sınıfı, özel bir hata türü olarak tasarlanmıştır ve `RuntimeException` sınıfından türemektedir. Bu sınıf, belirli hata mesajlarını içeren özel exception (istisna) nesneleri oluşturmak için kullanılır. `BaseException`, hata mesajlarını daha anlamlı hale getirmek ve hata yönetimini daha sistematik bir şekilde yapmak amacıyla kullanılır.

### Yapısı ve İşlevi:
- **messageType:** Bu değişken, `ErrorMessage` sınıfı aracılığıyla oluşturulan hata mesajının tipini (örneğin, `MessageType.USER_NOT_FOUND`) içerir. Bu, hata tipinin detaylarını tutar ve hata yönetimi sürecinde kullanılır.

- **Constructor (Yapıcı):**
    - Yapıcı metod, `ErrorMessage` nesnesini alır ve `super()` metodu ile `RuntimeException`'ın yapıcısını çağırarak, hatayı açıklayan mesajı `BaseException`'a iletir.
    - `ErrorMessage` nesnesinin `prepareErrorMessage()` metodu aracılığıyla hata mesajı formatlanarak üst sınıf olan `RuntimeException`'a iletilir.
    - `messageType` değeri, `ErrorMessage` nesnesinden alınarak sınıfın bir özelliği olarak saklanır. Bu, hata tipini ileride kullanmak üzere kaydeder.

### Kullanım Amacı:
- `BaseException`, hata mesajlarını içeren özel istisnalar (exceptions) oluşturulmasına olanak tanır.
- Hata mesajları `ErrorMessage` sınıfından alınır ve formatlanarak üst sınıfa iletilir.
- Hata yönetimi ve işleme işlemlerinde `messageType` kullanılarak spesifik hata türlerine göre işlemler yapmaya yarar. 


#### [`ApiError` Görüntüle](src/main/java/com/vehbiozcan/hello_turksat/exception/ApiError.java)

`ApiError<T>` sınıfı, API hatalarını temsil eder. İki ana alan içerir:

1. **status:** Hata durum kodunu (HTTP status) tutar.
2. **exceptionInfo:** Hata hakkında detaylı bilgi sağlayan `ExceptionInfo<T>` nesnesini tutar.

Bu sınıf, hata durumlarını ve mesajlarını API yanıtları olarak döndürmek için kullanılır.

#### [`ExceptionInfo` Görüntüle](src/main/java/com/vehbiozcan/hello_turksat/exception/ExceptionInfo.java)

`ExceptionInfo<T>` sınıfı, hata bilgilerini özelleştirilmiş şekilde tutmak için kullanılır. Bu sınıfın içerdiği alanlar:

1. **hostname:** Hata oluşan sunucunun ismini tutar.
2. **path:** Hata oluşan API yolunu (endpoint) belirtir.
3. **timeStamp:** Hata oluştuğu anın zaman damgası.
4. **message:** Hata mesajını dinamik bir şekilde tutar. Burada `T`, mesajın türünü belirtir ve esneklik sağlar (örneğin, String veya JSON).

Bu yapı, API yanıtlarında esnek hata mesajları sunulmasını sağlar.

---

#### **`handler` package**

#### [`GlobalExceptionHandler` Görüntüle](src/main/java/com/vehbiozcan/hello_turksat/config/GlobalExceptionHandler.java)

Bu sınıf, Spring uygulamanızda global hataları yönetmek ve her tür hatayı uygun bir şekilde ele almak için kullanılan `@ControllerAdvice` anotasyonu ile işaretlenmiş bir `GlobalExceptionHandler` sınıfıdır. `@ExceptionHandler` ile farklı hata türlerini yakalayarak özelleştirilmiş hata mesajları döndüren yöntemler içerir.

#### Genel Yapı ve İşleyiş

- **`@ControllerAdvice`**: Tüm uygulama genelinde hataları ele almak için kullanılan bir Spring anotasyonudur. Bu sınıf, uygulamanın herhangi bir yerinde oluşan hataları yakalar ve belirlenen işleme göre tepki verir.
- **`@ExceptionHandler`**: Belirli bir hata türü meydana geldiğinde bu türleri işleyen metodlardır. Her hata türü için farklı bir metod tanımlanmış ve uygun cevaplar dönülmüştür.

#### Metotlar

1. **`handleBaseException`**:
    - `BaseException` türündeki özel hataları yakalar ve uygun bir `ResponseEntity` döner.
    - Hata mesajı, `MessageType` enum'undan alınır ve hata kodu, başlık, mesaj, ve HTTP durum kodu ile birlikte döner.

2. **`handleMethodArgumentNotValidException`**:
    - `MethodArgumentNotValidException` hatasını yakalar. Bu hata, parametrelerin doğrulama sırasında geçersiz olması durumunda fırlatılır.
    - Hata mesajı, her bir doğrulama hatasını listeleyen bir map ile döner.

3. **`handleAccessDeniedException`**:
    - Kullanıcının yetkisiz erişim yapmaya çalıştığında fırlatılan `AccessDeniedException` hatasını yakalar.
    - `FORBIDDEN` (403) hata kodu ile döner.

4. **`handleAuthenticationException`**:
    - Kullanıcı doğrulama hatası (`AuthenticationException`) alındığında bu metod çalışır.
    - Hata mesajı, kullanıcı kimlik doğrulaması yapılmadığını belirten bir mesajla döner.

5. **`handleException`**:
    - Genel `Exception` türündeki hataları yakalar ve `INTERNAL_SERVER_ERROR` (500) hata kodu ile döner.

#### Yardımcı Metotlar

- **`addMapValue`**: Bu metod, hata mesajlarını birleştirmeye yarar. Aynı alan için birden fazla hata mesajı varsa, listeye ekler.
- **`customApiError`**: Hata mesajlarını hazırlayan ve `ApiError` nesnesini oluşturan yardımcı bir metoddur. Bu metod, `ApiError`'ın tüm yapı taşlarını oluşturur: durum kodu, zaman damgası, hostname, istek yolu ve hata mesajı.
- **`getHostname`**: Sunucunun adını (hostname) almak için kullanılan bir yardımcı metoddur. Eğer hostname alınamazsa "unknown" döner.

#### Kullanım Amacı

Bu sınıf, global hata yönetimini sağlayarak uygulamanızda merkezi bir hata işleme yapısı oluşturur. İstediğiniz hata durumları için özelleştirilmiş mesajlar ve durum kodları ile kullanıcıya anlaşılır bir şekilde hata mesajı sunar. Bu, özellikle REST API'lerinde daha tutarlı ve anlaşılır hata iletileri sağlamaya yardımcı olur.

---

#### **`dto` package**

#### [`DtoUser` Görüntüle](src/main/java/com/vehbiozcan/hello_turksat/dto/DtoUser.java)

### DtoUser Sınıfı

`DtoUser` sınıfı, kullanıcıyı temsil eden ve veri transferi amacıyla kullanılan bir **DTO (Data Transfer Object)** sınıfıdır. Bu sınıf kullanıcı verilerinden istenilen alanların taşınmasını ve transferini sağlar.

### Alanlar:
- **`Long id`**: Kullanıcının benzersiz kimlik numarasını tutar.
- **`String username`**: Kullanıcının kullanıcı adını tutar.
- **`Role role`**: Kullanıcının rolünü tutar. `Role` sınıfı, kullanıcının sahip olduğu rolün türünü belirtir (örneğin, admin, kullanıcı, vs.).

---

#### **`controller` package**

#### [`AuthControllerImpl` Görüntüle](src/main/java/com/vehbiozcan/hello_turksat/controller/AuthControllerImpl.java)

`AuthControllerImpl`, kullanıcılara yönelik kimlik doğrulama ve kayıt işlemlerini sağlayan bir REST API controller'dır. `IAuthController` arayüzünü implement eder ve çeşitli kimlik doğrulama ve refresh token işlemleri için endpoint'ler sunar.

#### Metotlar:

1. **`register(@RequestBody @Valid AuthRequest authRequest)`**:
    - **Açıklama**: Yeni bir kullanıcı kaydı yapar. `AuthRequest` sınıfı ile kullanıcının giriş bilgileri alınır ve doğrulama yapılır. `authService.register` metodu kullanılarak kullanıcı kaydedilir.
    - **Dönüş Değeri**: `RootEntity<DtoUser>` formatında, oluşturulan kullanıcı verisini döner. HTTP durum kodu olarak `201 CREATED` döner.

2. **`authenticate(@RequestBody AuthRequest authRequest)`**:
    - **Açıklama**: Kullanıcının kimlik doğrulama işlemini yapar. Kullanıcı adı ve şifresi `AuthRequest` içinde alınır, ardından `authService.authenticate` metodu çağrılır.
    - **Dönüş Değeri**: `RootEntity<AuthResponse>` formatında, başarılı giriş için token döner. HTTP durumu kodu olarak `200 OK` döner.

3. **`refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest)`**:
    - **Açıklama**: Kullanıcının süresi dolmuş olan refresh token'ını yeniler. `RefreshTokenRequest` ile yeni refresh token bilgileri alınır ve `authService.refreshToken` metodu çağrılır.
    - **Dönüş Değeri**: `RootEntity<AuthResponse>` formatında, yeni refresh token ile kimlik doğrulama sonucu döner. HTTP durumu kodu olarak `200 OK` döner.

### Kullanım Amacı:
Bu sınıf, kullanıcıların sisteme kaydını yapmayı, giriş yapmayı ve refresh token işlemlerini gerçekleştirmeyi sağlayan API endpoint'lerini içerir. Bu, özellikle kullanıcı kimlik doğrulama ve güvenliği için önemlidir. Uygulama, JWT tabanlı doğrulama mekanizmalarını kullanarak, kullanıcıların oturumlarını yönetir ve token yenileme işlemlerini sağlar.


#### [`AdminControllerImpl` Görüntüle](src/main/java/com/vehbiozcan/hello_turksat/controller/AdminControllerImpl.java)

`AdminControllerImpl`, admin kullanıcıları için belirli işlemleri sağlayabilecek bir REST API controller'dır. `IAdminController` arayüzünü implement eder ve admin ile ilgili bir test endpoint'i sunar.

#### Metotlar:

1. **`helloAdmin()`**:
    - **Açıklama**: Admin kullanıcılarına yönelik bir test endpoint'idir. Basitçe "Hello Admin :D" mesajını döner.
    - **Dönüş Değeri**: `RootEntity<String>` formatında, basit bir string mesajı döner. HTTP durumu kodu olarak `200 OK` döner.

#### Kullanım Amacı:
Bu sınıf, admin kullanıcıları için sistemdeki çeşitli admin işlemleriyle ilgili API endpoint'lerini yönetir. Şuan ki haliyle basit bir test endpoint'i sunulmaktadır. İlerleyen süreçte admin kullanıcıları için daha fazla yönetimsel işlem ve özellik eklenebilir.

#### [`UserControllerImpl` Görüntüle](src/main/java/com/vehbiozcan/hello_turksat/controller/UserControllerImpl.java)

`UserControllerImpl`, genel kullanıcılar için belirli işlemleri sağlayabilecek bir REST API controller'dır. `IUserController` arayüzünü implement eder ve kullanıcıya yönelik bir test endpoint'i sunar.

#### Metotlar:

1. **`helloUser()`**:
    - **Açıklama**: Genel kullanıcılar için bir test endpoint'idir. Basitçe "Hello User :D" mesajını döner.
    - **Dönüş Değeri**: `RootEntity<String>` formatında, basit bir string mesajı döner. HTTP durumu kodu olarak `200 OK` döner.

#### Kullanım Amacı:
Bu sınıf, kullanıcılar için sistemdeki çeşitli işlemlerle ilgili API endpoint'lerini yönetir. Örnekte yalnızca bir test endpoint'i bulunmaktadır, ilerleyen süreçte kullanıcılar için daha fazla işlem ve özellik eklenebilir.

---

### **Örnek Dönüş Değerleri**

- #### Başarılı Dönüş (Genel)

````json
{
    "success": true,
    "status": "String",
    "statusCode": "Integer",
    "data": {"Obje veya String"}
}
````

- #### Örnek Başarılı Dönüş (Authenticaiton)

````json
{
    "success": true,
    "status": "String",
    "statusCode": "Integer",
    "data": {
        "accessToken": "String",
        "refreshToken": "String"
    }
}
````
- #### Hata Mesajı Dönüşü (Genel)

````json
{
    "success": false,
    "status": "String",
    "statusCode": "Integer",
    "data": {
        "status": "Integer",
        "exceptionInfo": {
            "hostname": "String",
            "path": "String",
            "timeStamp": "String (Date Format)",
            "message": "String veya Obje"
        }
    }
}
````
- #### Örnek Hata Mesajı Dönüşü (Authenticaiton)

````json
{
    "success": false,
    "status": "CONFLICT",
    "statusCode": 409,
    "data": {
        "status": 409,
        "exceptionInfo": {
            "hostname": "hostname",
            "path": "/api/auth/authenticate",
            "timeStamp": "2025-01-04T21:08:34.815+00:00",
            "message": "3002 - Kullanıcı Zaten Var : Bu username ile zaten bir kullanıcı mevcut. Lütfen farklı bir username girin."
        }
    }
}
````


## Uygulama Görüntüleri

### Konsol Görüntüsü

![Konsol](/images/m3/console.PNG)

### Postman Görüntüleri

#### Register

![jwt2](/images/m3/jwt4.PNG)

#### Register Conflict Hatası (Aynı kullanıcı adı var)

![jwt](/images/m3/jwt3.PNG)

#### Authenticate User

![jwt3](/images/m3/jwt5.PNG)

#### Test-User Endpointi

![jwt4](/images/m3/jwt6.PNG)

#### Test-Admin Endpointi Doğrulanmamış (401 Unauthorized) 

![jwt5](/images/m3/jwt7.PNG)

#### Test-Admin Endpointi Yetkisiz (403 Forbidden)

![jwt5](/images/m3/jwt10e.PNG)

#### Admin Authenticate

![jwt6](/images/m3/jwt9.PNG)

#### Test-Admin Endpointi

![jwt7](/images/m3/jwt10.PNG)

![jwt8](/images/m3/jwt11.PNG)

#### Bazı Örnek Hata Responseları

- ![jwt9](/images/m3/jwt12e.PNG)

- ![jwt10](/images/m3/jwt13.PNG)

- ![jwt11](/images/m3/jwt14e.PNG)

### Swagger Görüntüleri

![swg1](/images/m3/swg1.PNG)

![swg2](/images/m3/swg2.PNG)

![swg3](/images/m3/swg3.PNG)

![swg4](/images/m3/swg4.PNG)

![swg5](/images/m3/swg5.PNG)

![swg6](/images/m3/swg6.PNG)

</details>