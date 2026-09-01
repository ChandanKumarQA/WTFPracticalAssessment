package DataProvider;

public class Logindata {

    @org.testng.annotations.DataProvider(name = "loginData")
    public Object[][] getLoginData() throws java.io.IOException {
        java.util.List<Object[]> data = new java.util.ArrayList<>();
        java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader("src/test/resources/login_data.csv"));
        String line = br.readLine(); // skip header
        while ((line = br.readLine()) != null) {
            String[] fields = line.split(",");
            String tcId = fields[0];
            String username = fields[1].equals("EMPTY") ? "" : fields[1];
            String password = fields[2].equals("EMPTY") ? "" : fields[2];
            String expected = fields[3];
            data.add(new Object[] { tcId, username, password, expected });
        }
        br.close();
        return data.toArray(new Object[0][]);
    }
}
