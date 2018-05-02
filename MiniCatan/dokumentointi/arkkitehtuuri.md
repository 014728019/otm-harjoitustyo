# Arkkitehtuurikuvaus

## Rakenne

Pakkaus _com.mycompany.gui_ sisältää JavaFX:llä toteutetun graafisen käyttöliittymän, _com.mycompany.domain_ pelin komponentit, _com.mycompany.logics_ pelin logiikan ja _com.mycompany.dao_ tietojen pysyväistallennuksesta vastaavan koodin.

## Käyttöliittymä

Käyttöliittymässä on alkuvalikko, tilasto näkymä, uuden pelaajan luomis näkymä ja peli näkymä. Näkymät toteuttavat rajapinnan _View_, jossa on määritelty metodi _void show(Stage stage)_. Jokainen näkymä asettaa parametri olioon _stage_ uuden _Scene_ -olion tai käyttää _Dialog_ -oliota. Virheilmoitukset ovat pääosin toteutettu _Alert_ tyyppisillä _Dialog_ -olioilla.

Käyttöliittymä on pyritty eristämään täysin sovelluslogiikasta, esimerkiksi pelin aikana napit kutsuvat _Game_ -olion metodeja.

## Sovelluslogiikka

## Tietojen pysyväistallennus
