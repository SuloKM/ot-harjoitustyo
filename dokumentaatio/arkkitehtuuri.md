# Arkkitehtuurikuvaus

## Rakenne

Luokkakaavio

<img src="https://raw.githubusercontent.com/SuloKM/ot-harjoitustyo/master/dokumentaatio/kuvat/luokkakaavio_uusi.png" width="300">

## Käyttöliittymä

KatalogiUi vastaa käyttöliittymästä. Käyttöliittymä muodostuu neljästä näkymästä: alku, lisäys, haku, tilasto.

## Sovelluslogiikka

KatalogiUi-olio käynnistää varsinaisesta sovellustoiminnasta vastaavan KatalogiService-olion. Levy-olio kuvaa yhtä
järjestelmään syötettävää musiikkialbumia.

## Tietojen pysyväistallennus

KatalogiService käynnistää puolestaan pysyväistallennuksesta vastaavan KatalogiDao-olion, joka toistaiseksi tallettaa
tekstitiedostoon.

## Tiedostot

Levyt.txt.

## Päätoiminnallisuudet

Levyn lisäys sekvenssikaaviona

<img src="https://raw.githubusercontent.com/SuloKM/ot-harjoitustyo/master/dokumentaatio/kuvat/sekvenssi.png" width="300">
