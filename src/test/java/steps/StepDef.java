package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.sql.*;

public class StepDef {
    private String recordingId;

    @Given("^the procedure has \"([^\"]*)\"$")
    public void getRecordId(String recordId) {
        recordingId = recordId;

    }

    @When("^it runs with the corresponding input values from the file")
    public void runProcedure(String filename) {
        callProcedureWithRecordId(recordingId);
    }



    @Then("^the result is successful$")
    public void theResultIsSuccessful() throws Throwable {
        String expectedResult = "OK";
        String actualResult = getResultValue();
        Assert.assertEquals(expectedResult, actualResult);

    }

    //TODO: Create random generator for error or success
    private String getResultValue() throws ClassNotFoundException, SQLException {
        String queryString = "SELECT REC_VALUE FROM results WHERE REC_ID = '"+recordingId+"' && REC_TYPE = 'T'";
        Class.forName("com.mysql.jdbc.Driver");
        Connection dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_engine", "root", "C0mplexPwd");
        Statement sqlStatement = dbConnection.createStatement();
        ResultSet queryResult = sqlStatement.executeQuery(queryString);
        while (queryResult.next()) {
//            System.out.println(queryResult.getString(1));
            return queryResult.getString(1);
        }
    return null;
    }

    private void callProcedureWithRecordId(String recordId) {
        //TODO: Trigger IF COBOL procedure and pass input
    }

}
