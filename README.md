# Merhaba Türksat! 

<hr/>

### Görev 1:
- [x] Uygulama konsola "Merhaba Dünya!" çıktısını basacak şekilde çalıştırılacak.
- [x] Uygulama Rest Servis olarak dönüştürülecek. 
- [x] İki tane metodunuz olacak biri GET diğeri POST. İsimlendirmelerini size bırakıyorum. Rest servis metod isimlendirme standartlarını gözden geçirebilirsiniz.
- [x] GET metoduna istek atıldığında servis sonucu "Merhaba Dünya!" olacak.
- [x] POST metodu bir parametre alacak. Aldığı parametreyi servis sonucuna yazacak. Parametre TÜRKSAT ise servis sonucu "Merhaba TÜRKSAT!" olacak.
- [x] Postman uygulaması kurulup çalışıtırılacak. Yazdığınız metodlara postman üzerinden istek atılacak. Servis sonuçları görülecek.
- [x] Swagger kullanılarak yazdığınız Rest API nin görselleştirmesi yapılacak. (Swagger'da görüntülenen HTTP Durum kodları farklı senaryolar ile deneyerek Postman'dan gelen veriye göre oluşturuldu)
- <br/><hr/>
Projenin OpenAPI Swagger Dökümanı:
http://localhost:8080/swagger-ui/index.html

<hr/>

## Uygulama Görüntüleri

### Konsol Görüntüsü

![alt text](images/console.PNG)

<hr/>

### Postman Görüntüleri
#### Get Request gorev1/merhaba

![alt text](images/get.PNG)

#### Post Request gorev1/merhaba
- parameter = türksat
![alt text](images/post1.PNG)

- parameter = Deneme
![alt text](images/post2.PNG)

#### HTTP Kodları

- 400 Bad Request
![alt text](images/400.PNG)

- 404 Not Found
![alt text](images/404.PNG)

- 500 Internal Server Error
![alt text](images/500.PNG)

<hr/>

### Swagger Görüntüleri
#### API Description
![alt text](images/swagger1.PNG)

#### GET Endpoint
![alt text](images/swagger-get.PNG)

#### POST Endpoint
![alt text](images/swagger-post.PNG)

![alt text](images/swagger-post2.PNG)