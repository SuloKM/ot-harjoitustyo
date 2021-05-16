# Arkkitehtuurikuvaus

## Rakenne

Luokkakaavio

<img src="https://raw.githubusercontent.com/SuloKM/ot-harjoitustyo/master/dokumentaatio/kuvat/luokkakaavio_uusi.png" width="300">

Pakkausrakenne

<img src="https://raw.githubusercontent.com/SuloKM/ot-harjoitustyo/master/dokumentaatio/kuvat/pakkausrakenne.png" width="300">

Pakkaus _katalogi.ui_ sisältää JavaFX:llä toteutetun käyttöliittymän, _katalogi.domain_ sovelluslogiikan ja _katalogi.dao_ tietojen pysyväistallennuksesta vastaavan koodin.

## Käyttöliittymä

_KatalogiUi_ (_katalogi.ui_) vastaa käyttöliittymästä, joka muodostuu neljästä, yksi kerrallaan näkyvästä näkymästä: alku, lisäys, haku ja tilasto. Jokaisella näkymällä on oma Scene-olio.

Sovellus pyrkii noudattamaan käyttöliittymän ja sovelluslogiikan toisistaan eriyttämisen periaatetta.

## Sovelluslogiikka

KatalogiUi-olio käynnistää varsinaisesta sovelluslogiikasta vastaavan _KatalogiService_-olion (_katalogi.domain_). Levy-olio kuvaa yhtä järjestelmään syötettävää musiikkialbumia.

## Tietojen pysyväistallennus

KatalogiService käynnistää puolestaan pysyväistallennuksesta vastaavan _KatalogiDBDao_-olion (_katalogi.dao_), joka tallettaa tietokantaan.

KatalogiDBDao noudattaa Data Access Object -suunnittelumallia ja on eristetty sovelluslogiikasta _KatalogiDao_-rajapinnan (_katalogi.domain_) taakse. Tämä mahdollistaa verrattain vaivattoman tallennustyyppivaihdoksen tarvittaessa.

## Tietokanta

Albums.db, joka sijaitsee sovelluksen juuressa. Käyttää SQLite-ajuria.

Albumit talletetaan tauluun Albums, joka luodaan SQL-lauseella
```
CREATE TABLE Albums (id INTEGER PRIMARY KEY, artist TEXT, name TEXT, year TEXT, genre TEXT, owner TEXT)
```

### Päätoiminnallisuudet sekvenssikaavioina

#### Levyn lisäys

Aloitusnäkymässä klikataan "Uusi levy" -painiketta, minkä seurauksena tapahtumankäsittelijä laittaa näkyviin lisäys-näkymän (_newScene_). Lisäysnäkymässä, kun lisättävän levyn tiedot on syötetty niille varattuihin kenttiin, klikataan "Lisää" -painiketta, minkä seurauksena käyttöliittymä luo uuden albumin ja asettaa syötetyt tiedot sen attribuuteiksi.

Käyttöliittymä pyytää sovelluslogiikkaa lisäämään levyn kokoelmaan. Sovelluslogiikka tarkistaa, ettei levyn attribuuteissa ole virheitä ja ettei samaa levyä ole vielä järjestelmässä. Jos huomautettavaa ei löydy, levy lisätään muistissa olevaan levykokoelmaan. Lopuksi tallennuspalvelua pyydetään kirjaamaan levy tietokantaan.

<img src="https://raw.githubusercontent.com/SuloKM/ot-harjoitustyo/master/dokumentaatio/kuvat/sekvenssi_uusilevy.png" width="300">

Levyjen haku sekvenssikaaviona

<img src="https://raw.githubusercontent.com/SuloKM/ot-harjoitustyo/master/dokumentaatio/kuvat/sekvenssi_haku.png" width="300">

Hakunäkymässä klikataan "Haku" -painiketta. Hakuparametreja ei anneta. Käyttöliittymä pyytää sovelluslogiikalta tyhjien parametrien hakutulosta eli kaikkia levyjä. Sovelluslogiikka palauttaa pyydetyn levykokoelman.

Levykokoelman tilasto sekvenssikaaviona

<img src="https://raw.githubusercontent.com/SuloKM/ot-harjoitustyo/master/dokumentaatio/kuvat/sekvenssi_tilasto.png" width="300">

Alkunäkymässä klikataan "Tilasto" -painiketta, minkä seurauksena käyttöliittymä pyytää sovelluslogiikalta levykokoelman tilastoja _getStatistics_-metodilla. Sovelluslogiikka palauttaa kokoelman _HashMap_-oliona. Käyttöliittymä asettaa näkyviin _statScene_-näkymän HashMap-olion sisältämillä tiedoilla.
