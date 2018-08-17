# Testausdokumentti

Ohjelmaa on testattu sekä automatisoiduin yksikkö- ja integraatiotestein JUnitilla sekä manuaalisesti tapahtunein järjestelmätason testein.

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Testit testaavat pelin komponentit kohtuun suoraviivaisesti, eli pakkauksen _com.mycompany.domain_ luokat. _GameTest_ testistä löytyy myös laajemman kokonaisuuden testi pienestä peliskenaariosta.

### Testauskattavuus

Käyttöliittymäkerrosta lukuunottamatta sovelluksen testauksen rivikattavuus on 93% ja haarautumakattavuus 71%

<img src="https://github.com/jokinen77/otm-harjoitustyo/blob/master/MiniCatan/dokumentointi/resurssit/testikattavuus.png" width="800">

Testaamatta jäivät _com.mycompany.database_ pakkauksen luokat suurimmaksi osaksi.

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti. Sovellus on haettu ja testattu [käyttöohjeen](https://github.com/014728019/otm-harjoitustyo/blob/master/MiniCatan/dokumentointi/kayttoohje.md) ohjeita noudattaen OSX-ympäristössä. 

Sovellus on testattu tilanteissa, jossa juurihakemistossa ei ole valmista tietokantaa ja myös valmiilla tietokannalla.

### Toiminnallisuudet

Kaikki [määrittelydokumentin](https://github.com/014728019/otm-harjoitustyo/blob/master/MiniCatan/dokumentointi/vaatimusmaarittely.md) ja käyttöohjeen listaamat toiminnallisuudet on käyty läpi. Kaikkien toiminnallisuuksien yhteydessä on syötekentät yritetty täyttää myös virheellisillä arvoilla kuten tyhjillä.

## Sovellukseen jääneet laatuongelmat

Sovellus ei anna tällä hetkellä järkeviä virheilmoituksia ihan kaikissa tapauksissa.
