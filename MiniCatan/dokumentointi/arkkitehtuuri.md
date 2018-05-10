# Arkkitehtuurikuvaus

## Rakenne

Ohjelmassa on seuraavar pakkaukset:
- _com.mycompany.gui_, sisältää JavaFX:llä toteutetun graafisen käyttöliittymän
- _com.mycompany.domain_, sisältää pelin komponentit
- _com.mycompany.logics_, sisältää pelin logiikasta vastaavat luokat
- _com.mycompany.database_, sisältää tietojen pysyväistallennuksesta vastaavat luokat

## Käyttöliittymä

Käyttöliittymä sisältää 5 erilaista näkymää:
- Alkuvalikko
- Uuden pelaajan lisäys
- Info
- Peli
- Tilasto

Näkymät toteuttavat rajapinnan _View_, jossa on määritelty metodi _void show(Stage stage)_. Jokainen näkymä asettaa parametri olioon _stage_ uuden _Scene_ -olion tai käyttää _Dialog_ -oliota. Virheilmoitukset ovat pääosin toteutettu _Alert_ tyyppisillä _Dialog_ -olioilla.

Käyttöliittymä on pyritty eristämään täysin sovelluslogiikasta, esimerkiksi pelin aikana napit kutsuvat _Game_ -olion metodeja. Näykymät sijaitsevat _com.mycompany.gui_ -pakkauksessa.

## Sovelluslogiikka

Luokka _com.mycompany.logics.Game_ metodit toteuttavat pelin aikana sen toiminnallisuuden. _Game_ oliolla on vähintään välillinen pääsy kaikkiin _com.mycompany.domain_ pakkauksen pelin aikaisiin olioihin. _Game_ oliolla on muutama apuluokka _Dices_ ja _Turn_ samassa pakkauksessa. _Dices_ toteuttaa noppien heiton toiminnallisuuden ja _Turn_ vastuulla on ylläpitää vuorojärjestys listaa.

Sovelluksessa on kolme rajapintaa _com.mycompany.database.Dao_, _com.mycompany.database.DaoResources_ ja _com.mycompany.gui.View_. _Dao_ ja _View_ rajapinnat määrittävät olioille toiminnallisuuksia. Eli olio, joka perii toisen näistä rajapinnoista, on erikoistapaus tästä rajapinnasta. _DaoResources_ rajapintaa käytetään luokkien _Database_, _PlayerDao_ ja _StatisticsDao_ oliomuuttujien helppoon kuljetukseen näitä olioita tarvitseviin luokkiin. Tällöin rajapinta luo nämä _final static_ olioina, kun rajapintaa käytetään ensimmäisen kerran.

## Tietojen pysyväistallennus
