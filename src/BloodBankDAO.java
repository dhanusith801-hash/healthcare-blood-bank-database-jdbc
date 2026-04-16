import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BloodBankDAO {

    public void addDonor(Donor donor) {
        String query = "INSERT INTO donor(name, age, gender, blood_group, phone, address) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, donor.getName());
            pst.setInt(2, donor.getAge());
            pst.setString(3, donor.getGender());
            pst.setString(4, donor.getBloodGroup());
            pst.setString(5, donor.getPhone());
            pst.setString(6, donor.getAddress());

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Donor added successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewDonors() {
        String query = "SELECT * FROM donor";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            System.out.println("\n--- Donor List ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("donor_id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Gender: " + rs.getString("gender"));
                System.out.println("Blood Group: " + rs.getString("blood_group"));
                System.out.println("Phone: " + rs.getString("phone"));
                System.out.println("Address: " + rs.getString("address"));
                System.out.println("-----------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addRecipient(Recipient recipient) {
        String query = "INSERT INTO recipient(name, age, gender, blood_group, phone, disease) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, recipient.getName());
            pst.setInt(2, recipient.getAge());
            pst.setString(3, recipient.getGender());
            pst.setString(4, recipient.getBloodGroup());
            pst.setString(5, recipient.getPhone());
            pst.setString(6, recipient.getDisease());

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Recipient added successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewRecipients() {
        String query = "SELECT * FROM recipient";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            System.out.println("\n--- Recipient List ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("recipient_id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Gender: " + rs.getString("gender"));
                System.out.println("Blood Group: " + rs.getString("blood_group"));
                System.out.println("Phone: " + rs.getString("phone"));
                System.out.println("Disease: " + rs.getString("disease"));
                System.out.println("-----------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addOrUpdateBloodStock(BloodStock stock) {
        String checkQuery = "SELECT * FROM blood_stock WHERE blood_group = ?";
        String insertQuery = "INSERT INTO blood_stock(blood_group, units_available) VALUES (?, ?)";
        String updateQuery = "UPDATE blood_stock SET units_available = units_available + ? WHERE blood_group = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement checkPst = con.prepareStatement(checkQuery)) {

            checkPst.setString(1, stock.getBloodGroup());
            ResultSet rs = checkPst.executeQuery();

            if (rs.next()) {
                try (PreparedStatement updatePst = con.prepareStatement(updateQuery)) {
                    updatePst.setInt(1, stock.getUnitsAvailable());
                    updatePst.setString(2, stock.getBloodGroup());
                    updatePst.executeUpdate();
                    System.out.println("Blood stock updated successfully!");
                }
            } else {
                try (PreparedStatement insertPst = con.prepareStatement(insertQuery)) {
                    insertPst.setString(1, stock.getBloodGroup());
                    insertPst.setInt(2, stock.getUnitsAvailable());
                    insertPst.executeUpdate();
                    System.out.println("Blood stock added successfully!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewBloodStock() {
        String query = "SELECT * FROM blood_stock";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            System.out.println("\n--- Blood Stock ---");
            while (rs.next()) {
                System.out.println("Stock ID: " + rs.getInt("stock_id"));
                System.out.println("Blood Group: " + rs.getString("blood_group"));
                System.out.println("Units Available: " + rs.getInt("units_available"));
                System.out.println("-----------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void issueBlood(int recipientId, String bloodGroup, int units) {
        String checkStockQuery = "SELECT units_available FROM blood_stock WHERE blood_group = ?";
        String issueInsertQuery = "INSERT INTO blood_issue(recipient_id, blood_group, units_issued) VALUES (?, ?, ?)";
        String updateStockQuery = "UPDATE blood_stock SET units_available = units_available - ? WHERE blood_group = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement checkPst = con.prepareStatement(checkStockQuery)) {

            checkPst.setString(1, bloodGroup);
            ResultSet rs = checkPst.executeQuery();

            if (rs.next()) {
                int availableUnits = rs.getInt("units_available");

                if (availableUnits >= units) {
                    try (PreparedStatement issuePst = con.prepareStatement(issueInsertQuery);
                         PreparedStatement updatePst = con.prepareStatement(updateStockQuery)) {

                        issuePst.setInt(1, recipientId);
                        issuePst.setString(2, bloodGroup);
                        issuePst.setInt(3, units);
                        issuePst.executeUpdate();

                        updatePst.setInt(1, units);
                        updatePst.setString(2, bloodGroup);
                        updatePst.executeUpdate();

                        System.out.println("Blood issued successfully!");
                    }
                } else {
                    System.out.println("Not enough blood stock available!");
                }
            } else {
                System.out.println("Blood group not found in stock!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}