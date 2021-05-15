# Arkkitehtuurikuvaus

## Rakenne

Luokkakaavio

<img src="https://raw.githubusercontent.com/SuloKM/ot-harjoitustyo/master/dokumentaatio/kuvat/luokkakaavio_uusi.png" width="300">

Pakkausrakenne

<img src="https://raw.githubusercontent.com/SuloKM/ot-harjoitustyo/master/dokumentaatio/kuvat/pakkausrakenne.png" width="300">

Pakkaus _katalogi.ui_ sisältää JavaFX:llä toteutetun käyttöliittymän, _katalogi.domain_ sovelluslogiikan ja _katalogi.dao_ tietojen pysyväistallennuksesta vastaavan koodin.

## Käyttöliittymä

KatalogiUi vastaa käyttöliittymästä, joka muodostuu neljästä, yksi kerrallaan näkyvästä näkymästä: alku, lisäys, haku ja tilasto. Jokaisella näkymällä on oma Scene-olio.

Sovellus pyrkii noudattamaan käyttöliittymän ja sovelluslogiikan toisistaan eriyttämisen periaatetta.

## Sovelluslogiikka

KatalogiUi-olio käynnistää varsinaisesta sovelluslogiikasta vastaavan KatalogiService-olion. Levy-olio kuvaa yhtä järjestelmään syötettävää musiikkialbumia.

## Tietojen pysyväistallennus

KatalogiService käynnistää puolestaan pysyväistallennuksesta vastaavan KatalogiDao-olion, joka tallettaa tietokantaan.

## Tietokantaa

Albums.db, joka sijaitsee sovelluksen juuressa. Käyttää SQLite-ajuria.

Albumit talletetaan tauluun Albums, joka luodaan SQL-lauseella
```
CREATE TABLE Albums (id INTEGER PRIMARY KEY, artist TEXT, name TEXT, year TEXT, genre TEXT, owner TEXT)
```

## Päätoiminnallisuudet

Levyn lisäys sekvenssikaaviona

<img src="https://raw.githubusercontent.com/SuloKM/ot-harjoitustyo/master/dokumentaatio/kuvat/sekvenssi_uusilevy.png" width="300">

Levyjen haku sekvenssikaaviona

<img src="https://raw.githubusercontent.com/SuloKM/ot-harjoitustyo/master/dokumentaatio/kuvat/sekvenssi_haku.png" width="300">

Levykokoelman tilasto sekvenssikaaviona

<img src="https://raw.githubusercontent.com/SuloKM/ot-harjoitustyo/master/dokumentaatio/kuvat/sekvenssi_tilasto.png" width="300">


