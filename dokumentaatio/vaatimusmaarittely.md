# Vaatimusmäärittely
## Sovelluksen tarkoitus
Sovellus mahdollistaa musiikkikokoelman hallinnan. Sillä voi lisätä, hakea ja poistaa levyjen tietoja sekä tarkistaa levykokoelman tiedot tilastona.
## Käyttäjät
Sovelluksella on alkuvaiheessa vain yksi (perustason) käyttäjärooli. Sovellusta voi tosin käyttää useampikin perustuen uuden levyn tietojen mukana talletettavaan "omistaja" -attribuuttiin.
## Perusversion tarjoama toiminnallisuus
### Levyjen hallinta (alkunäyttö)
* käyttäjä voi syöttää järjestelmään uuden levyn tiedot
* käyttäjä voi hakea levyjen tietoja tietyillä kriteereillä
* käyttäjä voi tutkia aiemmin tallennetun levykokoelman tilastoja
* muista näkymistä pääsee palaamaan takaisin alkunäytölle
### Uuden levyn rekisteröinti
* levystä tallennetaan Esittäjä, Nimi, Vuosi, Tyylilaji (Genre)  ja Omistaja
* jos samalle omistajalle on jo tallennettu lisättävä levy, annetaan ilmoitus eikä levyä lisätä
### Levyjen haku
* levyjen tietoja voi hakea valitulla kriteerillä eli levyn attribuutilla
* levyt listataan ja levyjen attribuutit näytetään omissa sarakkeissaan
* kunkin rivin kohdalla on erillinen valintaruutu jolla kyseisen levyn voi merkitä poistettavaksi
* oikeanpuoleinen sarakeotsikko toimii poistopainikkeena, jonka aktivoinnin myötä poistettavaksi merkityt levyt poistetaan listalta
### Tilastointi
* tilastonäkymä esittää levykokoelman numeroina
## Jatkokehitysideoita
* navigointi sujuvammaksi
* käyttäjän tunnistus alkuun
* levyjen listaus muokattavammaksi
