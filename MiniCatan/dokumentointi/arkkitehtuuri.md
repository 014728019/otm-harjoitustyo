# Arkkitehtuurikuvaus

## Rakenne

Ohjelmassa on seuraavar pakkaukset:
- _com.mycompany.gui_, sisältää JavaFX:llä toteutetun graafisen käyttöliittymän
- _com.mycompany.domain_, sisältää pelin komponentit
- _com.mycompany.logics_, sisältää pelin logiikasta vastaavat luokat
- _com.mycompany.database_, sisältää tietojen pysyväistallennuksesta vastaavat luokat

Luokat ja niiden yhteyksiä:

<img src="https://github.com/jokinen77/otm-harjoitustyo/blob/master/MiniCatan/dokumentointi/resurssit/UML.png" width="600">

(Kaaviosta puuttuu ainakin Player-Resource yhteys)



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

Sovelluksessa käytetään tietokantaa pelaajien ja pelitulosten tallentamiseen ja lukemiseen. Tarkemmin näistä vastaa luokat _PlayerDao_ ja _StatisticsDao_, jotka noudattavat _Data Access Object_ -suunnittelumallia. Luokat toteuttavat _Dao_ rajapinnan, joten tarvittaessa ne on helposti korvattavissa uusilla, jos esimerkiksi tallennustapa muuttuisi.

Sovellus luo automaattisesti uuden tyhjän tietokannan juurihakemistoon, jos se ei löydä tietokantaa sieltä. Tietokannan nimi on aina "MiniCatanDatabase.db". Halutessa tietokannan voi "tyhjentää" poistamalla sen tai sen voi myös nimetä toisin, jolloin vanha tietokanta säilyy. Tietokanta luodaan SQL-komennoilla:

<pre>
CREATE TABLE IF NOT EXISTS Player (name varchar(50) PRIMARY KEY);
CREATE TABLE IF NOT EXISTS GameTable (
    id integer PRIMARY KEY, 
    player varchar(50), 
    playersOnGame integer, 
    placement integer, 
    winPointLimit integer, 
    winPoints integer);
</pre>

## Päätoiminnallisuudet

Tässä vuoron vaihtoa kuvaava sekvenssikaavio:

<img src="https://github.com/jokinen77/otm-harjoitustyo/blob/master/MiniCatan/dokumentointi/resurssit/VuoronVaihtoSekv.png" width="800">

## Ohjelman rakenteeseen jääneet heikkoudet

* Solmuissa, eli _Node_:illa, olisi voinut olla suoraan yhteydet naapureihinsa eikä vain niiden nimiä. Verkkoa luodessa kuitenkin tällöin olisi pitänyt ensin luoda solmut, lisätä naapurit ja sitten vasta lisätä ne listalle. _NodeWeb_ ja _FieldWeb_ luokat olisi voinut jopa ehkä yhdistää, jolloin niiden välinen kommunikaatio olisi myös parantunut.

* Ohjelmassa olisi voinut olla jonkinlainen ilmoitus-luokka tai rajapinta tai yliluokka, jonka avulla olisi voinut generoida käyttäjälle kaikki ilmoitukset, esimerkiksi _Alert_ tyyppisinä. Tällöin ilmoituksia olisi voinut luoda helpommin, saman tyylisiksi ja helppouden takia niitä olisi jaksanut tunkea useampaan paikkaankin.

