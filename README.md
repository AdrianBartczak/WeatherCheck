# WeatherCheck
simple weather forecast application for Polish Cities

# Zasada działania:

1.Pobranie aktualnej lokalizacji GPS użytkownika
2.Pobranie bazy wszystkich polskich miast wraz ze współrzędnymi ze strony:
    http://cybermoon.pl/wiedza/wspolrzedne_polskich_miast.html
3.Zlokalizowanie najbliższego miasta względem położenia GPS
4.Odpytanie strony:  http://api.openweathermap.org/data/2.5/weather?q=Kłodzko&mode=xml gdzie w GET podaję nazwę 
  lokalizacji uzyskanej wcześniej.
5.Przetworzenie uzyskanej informacji oraz prezentacja pogody.
