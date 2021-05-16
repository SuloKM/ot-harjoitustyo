# Käyttöohje

## _! HUOM ! Sovelluksen kehitysvaiheessa esiintyi Cubbli-ympäristössä ajonaikaisia ongelmia SQLite-tietokantayhteydessä tietyissä hakemistoissa ajettaessa._

Lataa tiedosto [Musakatalogi-1.0-SNAPSHOT.jar](https://github.com/SuloKM/ot-harjoitustyo/releases/tag/viikko5)

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla

```
java -jar Musakatalogi-1.0-SNAPSHOT.jar
```

Sovellus käynnistyy aloitusnäkymään:

<img src="https://raw.githubusercontent.com/SuloKM/ot-harjoitustyo/master/dokumentaatio/kuvat/alkunakyma.png" width="400">

## Uusi levy

Aloitusnäkymästä siirrytään "Uusi levy" -painikkeella näkymään, jossa syötetään järjestelmään lisättävän
levyn tunnistetiedot niille varattuihin kenttiin. Lisää-painikkeella vaaditut ja virheettömät syötteet 
saadaan tallennettua.

<img src="https://raw.githubusercontent.com/SuloKM/ot-harjoitustyo/master/dokumentaatio/kuvat/uusilevy.png" width="400">

Jos syötteissä on virheitä/puutteita, näkymään tulee virheilmoitus.

Myös tietojen onnistunut tallennus kuitataan ilmoituksella.

Alkuun-painikkeella päästään takaisin alkunäkymään.

## Haku

Aloitusnäkymästä siirrytään "Haku" -painikkeella näkymään, jossa voidaan määrittää haettavien levyjen hakuehdon. Vasemmalla välittömästi "Hakukriteeri" -otsikon alapuolella on alasvetovalikko, josta valitaan haluttu levyattribuutti. Valikon oikealla puolella olevaan tekstikenttään syötetään attribuutin arvo. "Hae" -panikkeella käynnistetään varsinainen hakutoiminto. Hakutulokset listataan näkymään allekkain alkaen keskiosasta.

<img src="https://raw.githubusercontent.com/SuloKM/ot-harjoitustyo/master/dokumentaatio/kuvat/hakunakyma.png" width="400">

<img src="https://raw.githubusercontent.com/SuloKM/ot-harjoitustyo/master/dokumentaatio/kuvat/hakunakyma2.png" width="400">

Jos hakukriteerit jätetään tyhjiksi, haetaan kaikki tallennetut levyt.

<img src="https://raw.githubusercontent.com/SuloKM/ot-harjoitustyo/master/dokumentaatio/kuvat/hakutulokset.png" width="400">

Hakutuloksissa mukana olevia levyjä voidaan poistaa kokoelmasta klikkaamalla tuosrivin oikealla laidalla olevaa "Poista" -ruutua.

## Tilasto

Aloitusnäkymästä siirrytään "Tilasto" -painikkeella näkymään, jossa levykokoelman sisältö esitellään lukuina.

<img src="https://raw.githubusercontent.com/SuloKM/ot-harjoitustyo/master/dokumentaatio/kuvat/tilastonakyma.png" width="400">
