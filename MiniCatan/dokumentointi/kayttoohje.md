# Käyttöohje

Lataa tiedosto [MiniCatan.jar](https://github.com/014728019/otm-harjoitustyo/releases)

## Konfigurointi

Ohjelma osaa luoda tyhjän tietokannan automaattisesti, jollei se sitä löydä juurihakemistosta. Peliin tarvittavat parametrit syötetään pelikohtaisesti käyttöliittymästä.

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar MiniCatan.jar
```

## Alkuvalikko

Sovelluksen alkuvalikko näyttää seuraavalta:

<img src="https://github.com/014728019/otm-harjoitustyo/blob/master/MiniCatan/dokumentointi/resurssit/alkuvalikko.png" width="200">

## Uuden pelaajan luominen

Paina alkuvalikosta "Luo uusi pelaaja", jonka jälkeen syötä haluamasi nimi kohdan "Nimi:" alapuolelle.

<img src="https://github.com/014728019/otm-harjoitustyo/blob/master/MiniCatan/dokumentointi/resurssit/uusipelaaja.png" width="300">

Paina "Lisää pelaaja" nappia, jonka jälkeen saat ilmoituksen, että onnistuiko lisääminen. Ilmoituksessa saattaa olla myös lisäohjeita, jollei nimen lisääminen onnistunut. Voit palata alkuvalikkoon painamalla "Takaisin" nappia tai sulkemalla ikkunan.

## Pelin aloittaminen

Paina alkuvalikosta "Uusi peli".

<img src="https://github.com/014728019/otm-harjoitustyo/blob/master/MiniCatan/dokumentointi/resurssit/pelinaloittaminen.png" width="300">

Valitse voittopiste määrä, johon haluatte pelata.

Lisätkää pelaajat kohdasta "Lisää pelaaja".

Aloittakaa peli painamalla "Aloita uusi peli!" nappia.

## Pelaaminen

Pelinäkymästä löytyy "Info" nappula tarkempien peliohjeiden näyttämiseen, mutta tässä muutama ohje.

<img src="https://github.com/014728019/otm-harjoitustyo/blob/master/MiniCatan/dokumentointi/resurssit/solmujatie.png" width="300">

Kylän voit rakentaa painamalla "isompaa mustaa palloa" eli solmukohtaa. Kuvassa punainen pelaaja on rakentanut kylän(punainen pallo) yhteen solmuun. Kylän kehittäminen tapahtuu painamalla kylää uudelleen vuorollaan, jos resurssit riittävät.

Tien rakentaminen onnistuu painamalla "pienempää mustaa palloa" mustan viivan päältä. Kuvassa punainen pelaaja on rakentanut tien(punainen viiva) kylästään oikealle päin.

Vuoron vaihtuessa heitetään automaattisesti kahta kuutio noppaa, joista lasketaan summa(eli range 2-12 ja todennäköisin arvo 7). Tämän jälkeen pelaajat saavat kylistään resursseja, jos ne ovat tuottavan resurssikentän vieressä. Kuvan punainen pelaaja saisi 1 puu resurssin, kun heitettäisiin luku 7 tai 2 savi resurssia kun heitettäisiin luku 4. Tuotettujen resurssien määrä nousee, kun kylän tasoa nostetaan.

## Tilastojen tarkastelu

Paina alkuvalikosta "Tilastoja" nappia. Listassa näkyy pelaajien nimet, pelattujen pelien määrä ja voitto suhde pelatuista peleistä.
