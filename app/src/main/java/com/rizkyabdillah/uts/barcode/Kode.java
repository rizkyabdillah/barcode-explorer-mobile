package com.rizkyabdillah.uts.barcode;

public class Kode {

    private String[][] barcode =  {
            {"8999999049669", "REXONA MEN MOTION SENSE"},
            {"8998866107143", "POSH MEN COOL BLUE 150ML"},
            {"8999909085114", "SAMPOERNA KRETEK 12"},
            {"8997004301184", "OISHI UDANG PEDAS 70GR"},
            {"8999999000066", "TARO POTATO 40G"},
            {"8996196000257", "PIATTOS BBQ 50G"},
            {"653314504513", "KUSUKA SINGKONG RUMPUT LAUT 60G"},
            {"8997032680329", "TARO 3D JUNGLE CHIC 40G"},
            {"8996001304990", "ROMA SARI GANDUM SANWICH COKLAT"},
            {"8888166607415", "KHONG GUAN POTATO KRECKER"},
            {"7622300442507", "KRAFT OREO ICE CREAM 137G"},
            {"8888166606227", "KHONG GUAN MALKIST ABON"},
            {"8886015414061", "NYAM-NYAM BUBBLEPUFF CHOCO CUP 18G"},
            {"7622300335809", "OREO SOFT CAKE 16G"},
            {"8886015428136", "ARNOTS TIMTAM COKLAT 120G"},
            {"8992388112678", "ABC AYM PEDAS CUP 80g"},
            {"8992772586016", "ADEM SARI CK KLG 320mL"},

    };

    public String searchKode(String kode) {
        String name = "Name Barcode Tidak Ditemukan!";
        for(int i = 0; i < barcode.length; i++) {
            if(kode.equals(barcode[i][0])) {
                 name = barcode[i][1];
            }
        }
        return name;
    }

}
