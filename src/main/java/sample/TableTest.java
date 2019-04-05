package sample;

public class TableTest {
    private String R_name;
    private String R_virifed;
    private String P_email;
    private String P_phone;
    private String P_name;

    public TableTest() {
    }

    public TableTest(String r_name, String r_virifed, String p_email, String p_phone, String p_name) {
        R_name = r_name;
        R_virifed = r_virifed;
        P_email = p_email;
        P_phone = p_phone;
        P_name = p_name;
    }

    public String getR_name() {
        return R_name;
    }

    public void setR_name(String r_name) {
        R_name = r_name;
    }

    public String getR_virifed() {
        return R_virifed;
    }

    public void setR_virifed(String r_virifed) {
        R_virifed = r_virifed;
    }

    public String getP_email() {
        return P_email;
    }

    public void setP_email(String p_email) {
        P_email = p_email;
    }

    public String getP_phone() {
        return P_phone;
    }

    public void setP_phone(String p_phone) {
        P_phone = p_phone;
    }

    public String getP_name() {
        return P_name;
    }

    public void setP_name(String p_name) {
        P_name = p_name;
    }
}

