package dbStepDefinitions;

import io.cucumber.java.en.Given;

import java.sql.*;

public class StepDefinitions {
    String url="jdbc:sqlserver://184.168.194.58:1433;databaseName=hotelmycamp ; user=techproed;password=P2s@rt65";
    String username="techproed";
    String password="P2s@rt65";

    Connection connection;
    Statement statement;
    ResultSet resultSet;

    @Given("kullanici HMC veri tabanina baglanir")
    public void kullanici_hmc_veri_tabanina_baglanir() throws SQLException {
        // database'e baglanti kuruyoruz
        connection= DriverManager.getConnection(url,username,password);
        statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }
    @Given("kullanici {string} tablosundaki {string} verilerini alir")
    public void kullanici_tablosundaki_verilerini_alir(String table, String field) throws SQLException {
        //Query calistiriyoruz
        //SELECT Price FROM tHotelRoom ama bu sekilde dinamik olmaz
        //dinamik icin altta
        String readQuery="SELECT" + field +  " FROM" + table;
        resultSet=statement.executeQuery(readQuery);//sorgu sonucunun hepsunu ResultSete atadik
    }
    @Given("kullanici {string} sutunundaki verileri okur")
    public void kullanici_sutunundaki_verileri_okur(String field) throws SQLException {
        //resultSet bizim suana kadar kullandigimiz set yapisinda degildir
        //resultSet iterator ile calisir
        //resultSetdeki bilgileri okumak istersek
        //once iteratory istediginiz yere gondermelisiniz
        resultSet.first();//bu komut iteratory ilk elemente goturur, gitti ise true gidemezse false doner

        System.out.println(resultSet.getString(field));
        //2.oda fiyatini gormek istersek iteratory yollamak lazim
        resultSet.next();
        System.out.println(resultSet.getString(field));

        System.out.println(resultSet.next());//true

        System.out.println(resultSet.getString(field));//next nerede olursa olsun iteratoru bir sonrakine

        //tum price sutununu yazdirmak istersek
        System.out.println("======================");
        //resultSet ile calisirken iterator konumunu kontrol etmek zorundayiz
        //eger 1.elemana donmeden listeyi yezdirmaya devam edersem
        // kaldigi yerden devam eder, sonrasini yazdirir

        resultSet.absolute(0);

        while (resultSet.next()){
            System.out.println(resultSet.getString(field));
        }
        //price sutununda kac data oldugunu bulalim
        //while loop ile resultSet.next() false donunceye kadar gittik
        //dolayisiyla artik iterator sonda
        resultSet.last();
        System.out.println(resultSet.getRow());
    }

}
