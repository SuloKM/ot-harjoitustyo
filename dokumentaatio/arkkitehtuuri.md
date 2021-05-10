# Arkkitehtuurikuvaus

## Rakenne

Luokkakaavio

<img src="https://raw.githubusercontent.com/SuloKM/ot-harjoitustyo/master/dokumentaatio/kuvat/luokkakaavio_uusi.png" width="300">

Pakkausrakenne

<img src="https://raw.githubusercontent.com/SuloKM/ot-harjoitustyo/master/dokumentaatio/kuvat/pakkausrakenne.png" width="300">

Pakkaus _katalogi.ui_ sisältää JavaFX:llä toteutetun käyttöliittymän, _katalogi.domain_ sovelluslogiikan ja _katalogi.dao_ tietojen pysyväistallennuksesta vastaavan koodin.

## Käyttöliittymä

KatalogiUi vastaa käyttöliittymästä. Käyttöliittymä muodostuu neljästä, yksi kerrallaan näkyvästä näkymästä: alku, lisäys, haku ja tilasto. Jokaisella näkymällä on oma Scene-olio.

Sovellus pyrkii noudattamaan käyttöliittymän ja sovelluslogiikan toisistaan eriyttämisen periaatetta.

## Sovelluslogiikka

KatalogiUi-olio käynnistää varsinaisesta sovelluslogiikasta vastaavan KatalogiService-olion. Levy-olio kuvaa yhtä järjestelmään syötettävää musiikkialbumia.

## Tietojen pysyväistallennus

KatalogiService käynnistää puolestaan pysyväistallennuksesta vastaavan KatalogiDao-olion, joka toistaiseksi tallettaa tekstitiedostoon.

## Tiedostot

Levyt.txt, sijaitsee sovelluksen juuressa.

Albumit talletetaan muodossa
```
Esittaja;Nimi;Vuosi;Tyylilaji;Omistaja
```
Attribuutit siis erotellaan puolipistein.

## Päätoiminnallisuudet

Levyn lisäys sekvenssikaaviona

<img src="https://raw.githubusercontent.com/SuloKM/ot-harjoitustyo/master/dokumentaatio/kuvat/sekvenssi.png" width="300">
