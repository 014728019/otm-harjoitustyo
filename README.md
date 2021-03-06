# MiniCatan

MiniCatan on peli 2-4 pelaajalle. Se on yksinkertaistettu ja hieman muunneltu versio Catan lautapelistä. Ohjelmassa käyttäjä voi luoda itselleen pelinimen ja pelata. Ohjelma kerää ja näyttää tilastoa pelatuista peleistä.

## Dokumentaatio

[Käyttöohje](https://github.com/014728019/otm-harjoitustyo/blob/master/MiniCatan/dokumentointi/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/014728019/otm-harjoitustyo/blob/master/MiniCatan/dokumentointi/vaatimusmaarittely.md)

[Tuntikirjanpito](https://github.com/014728019/otm-harjoitustyo/blob/master/MiniCatan/dokumentointi/tuntikirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/014728019/otm-harjoitustyo/blob/master/MiniCatan/dokumentointi/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/014728019/otm-harjoitustyo/blob/master/MiniCatan/dokumentointi/testaus.md)

## Releaset

[Viikko7](https://github.com/014728019/otm-harjoitustyo/releases/tag/viikko7)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _MiniCatan-1.0-SNAPSHOT.jar_.

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/014728019/otm-harjoitustyo/blob/master/MiniCatan/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_
