Feature: US1001 Kullanici database'e baglanip bilgileri okuyabilir
  @db

  Scenario: TC01 kullanici database baglanip istedigi bilgileri okuyabilmeli

    Given kullanici HMC veri tabanina baglanir
    #1.adim database baglanacagiz
    And kullanici "tHOTELROOM" tablosundaki "Price" verilerini alir
    #2.adim sorgu(Query) SELECT Price FROM tHotelRoom
    And kullanici "Price" sutunundaki verileri okur
    #database sorgusu sonunda bize dondurulen bilgiyi nasil kullanabilecegimizi